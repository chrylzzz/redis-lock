package com.chryl.redisson.controller;

import com.chryl.entity.User;
import com.chryl.redisson.anno.RedisLock;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 注意,锁的力度一定要细
 * Created by Chr.yl on 2020/6/5.
 *
 * @author Chr.yl
 */
@RequestMapping("/redisson")
@RestController
public class RedissonController {

    /**
     * 解决缓存一致性:
     * 双写模式: 改完db,改redis
     * 失效模式: 改完db,删掉redis
     * 上面两种方法,都无法保证,数据一致性,只有加锁,才能保证.但是加锁会导致系统笨重,
     * 所以,如果写多,可以牺牲数据一致性.或者干脆不要用缓存了,直接用db.如果要用缓存,
     * 最好的办法就是,缓存的时候加上过期时间.来保证数据最终一致性
     */

    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    int anInt = 1;

    /**
     * 注解分布式锁
     *
     * @param user
     * @return
     */
    @GetMapping("/get1")//appConnect.id ,appConnect.bizUserId 都是实体类的属性
    @RedisLock(lockName = "insertUser", key = "#user.id+ ':' + #user.username")
    public Object show1(User user) {
        System.out.println(user.getId() + ":" + anInt++);
        return "success";
    }


    /**
     * 总结分布式锁
     */
    @GetMapping("/hello")
    public void show() {
        /**
         * redisson
         * 自带 ttl :默认30s
         * 自带锁的自动续期:不用担心业务时间长,锁过期
         * 即使锁续期,那么如果没后解锁,那也会自动解锁
         */
        RLock myLock = redissonClient.getLock("myLock");

        //1
        myLock.lock();//阻塞等待,while(true)

        //2
        myLock.lock(10, TimeUnit.SECONDS);//10s自动解锁,不会锁续期,锁时间一定要大于业务时间
        //如果传递了锁超时时间,那么直接把时间传递给lua脚本,过期就超时
        //如果没传,就是用看门狗的 30*1000 ,看门狗结就是定时任务 ,定时任务每隔三分之一的看门狗时间
        //但是,我们通常都指定一个锁超时时间,省掉续期,但是设置的时间要足够大
        try {
            System.out.println("...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myLock.unlock();
        }

    }

    /**
     * 公平锁
     */
    public void myFairLock() {
        //公平锁,线程排队,一次获取锁
        RLock myFairLock = redissonClient.getFairLock("myFairLock");
    }

    ////////////////////////////////////////////////////////////////////////////////////

    /**
     * 写入值
     * 读写锁: 保证一定能读到最新数据,修改期间,不允许进行读取,写锁是一个互斥锁(排它锁),读锁是一个共享锁
     * 也就是说,写锁没释放,读锁就必须等待
     * 读 + 读 : 相当于无锁,只会在redis记录加锁成功
     * 读 + 写 : 等待写释放
     * 写 + 写 : 等待写锁释放
     * 读 + 写 : 有读锁,写也要加锁
     * 总结 : 只要有写,就必须等待写锁释放
     * 改数据加写锁,读数据加读锁
     */
    @GetMapping("wVal")
    public void wVal() {
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("rw-Lock");

        String uuid;
        //加写锁
        RLock rLock = readWriteLock.writeLock();
        try {
            rLock.lock();
            uuid = UUID.randomUUID().toString();
            stringRedisTemplate.opsForValue().set("rwVal", uuid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();
        }
    }

    /**
     * 读取值
     * 读写锁
     */
    @GetMapping("rVal")
    public String rVal() {
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("rw-Lock");
        //加读锁
        RLock rLock = readWriteLock.writeLock();
        String uuid = "";
        try {
            rLock.lock();
            uuid = UUID.randomUUID().toString();
            stringRedisTemplate.opsForValue().get("rwVal");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();
        }
        return uuid;
    }
    ////////////////////////////////////////////////////////////////////////////////////

    /**
     * 停车,共3个车位:信号量原理沾满了,就需要等有人走才能进,否则一直阻塞
     * 信号量: 可以做分布式限流
     */
    @GetMapping("pack")
    public String pack() throws InterruptedException {
        RSemaphore semaphore = redissonClient.getSemaphore("pack");
        semaphore.acquire();//获取一个信号,获取一个值,获取成功,继续执行,获取不成功,一直阻塞,直到成功
        boolean tryAcquire = semaphore.tryAcquire();//尝试获取一下信号量,获取不到就返回false

        return "ok";
    }

    @GetMapping("go")
    public String go() throws InterruptedException {
        RSemaphore semaphore = redissonClient.getSemaphore("pack");
        semaphore.release();//释放一个信号

        return "ok";
    }


}