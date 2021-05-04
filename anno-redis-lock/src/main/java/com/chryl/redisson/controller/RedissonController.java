package com.chryl.redisson.controller;

import com.chryl.entity.User;
import com.chryl.redisson.anno.RedisLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Created by Chr.yl on 2020/6/5.
 *
 * @author Chr.yl
 */
@RequestMapping("/redisson")
@RestController
public class RedissonController {


    @Autowired
    private RedissonClient redissonClient;


    int anInt = 1;

    @GetMapping("/get1")//appConnect.id ,appConnect.bizUserId 都是实体类的属性
    @RedisLock(lockName = "insertUser", key = "#user.id+ ':' + #user.username")
    public Object show1(User user) {
        System.out.println(user.getId() + ":" + anInt++);
        return "success";
    }


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
}