package com.chryl.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 高并发,互联网:
 * 解决redisLockKey 设置时间不确定问题:
 * 在设置了redisLockKey之后,立刻开启一个分线程,timer用来监测该主线程代码是否执行完毕,
 * 一段时间执行一次监测,执行完毕,则可以过期和删除该redisLockKey,现在用redisson框架来实现,
 * 如果监测到锁持有的锁还在,则会重新刷新设置redisLock过期时间,其他线程则会一只等待这把锁.
 * <p></p>
 * 但是:::::如果redis主从节点(多节点redis,单节点redis不考虑),主节点挂掉,以前的锁在主,从节点未及时同步到主节点的锁,又会出现问题
 * Created by Chryl on 2019/8/12.
 */
@RestController
public class Controller2 {
    @Autowired
    private Redisson redisson;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/redis-lock")
    public Object show() throws InterruptedException {
        //t-f保证一定会删除redisKey
        String redisLockKey = "product-id";
        String clientId = UUID.randomUUID().toString();
        /**
         *        redisson
         */
        RLock lock = redisson.getLock(redisLockKey);

        try {
            //每次进去.都会setnx 查看时候有该key,如果有,setnx则不作任何操作,返回false,如果没有返回true
            //防止redis 宕机,设置redisLockKey有效期,但是有效时间该如何设置呢????
//            Boolean chryl = stringRedisTemplate.opsForValue().setIfAbsent(redisLockKey, clientId, 15, TimeUnit.SECONDS);
//
//            if (!chryl) {
//                return "error";
//            }
            /**
             *        redisson
             */
            lock.tryLock(30, TimeUnit.SECONDS);
            //
            //减库存操作
            //
        } finally {
            //如果没有这个key值 ,setnx就会 set这个key值,在此处删除,redis简单的分布式锁就可以了.但是有bug
//            if (clientId.equals(stringRedisTemplate.opsForValue().get(redisLockKey))) {//查看释放的锁是否为自己加的锁,防止进程之间相互释放对方的锁
//                stringRedisTemplate.delete(redisLockKey);
//            }
            /**
             *        redisson
             */
            lock.unlock();
        }


        return "ok";
    }
}
