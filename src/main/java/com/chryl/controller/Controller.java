package com.chryl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 低并发;非一线互联网
 * Created by Chryl on 2019/8/12.
 */
@RestController
public class Controller {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/redis-lock")
    public Object show() {
        //t-f保证一定会删除redisKey
        String redisLockKey = "product-id";
        String clientId = UUID.randomUUID().toString();

        try {
            //每次进去.都会setnx 查看时候有该key,如果有,setnx则不作任何操作,返回false,如果没有返回true
            //防止redis 宕机,设置redisLockKey有效期,但是有效时间该如何设置呢????
            Boolean chryl = stringRedisTemplate.opsForValue().setIfAbsent(redisLockKey, clientId, 15, TimeUnit.SECONDS);

            if (!chryl) {
                return "error";
            }
            //
            //减库存操作
            //
        } finally {
            //如果没有这个key值 ,setnx就会 set这个key值,在此处删除,redis简单的分布式锁就可以了.但是有bug
            if (clientId.equals(stringRedisTemplate.opsForValue().get(redisLockKey))) {//查看释放的锁是否为自己加的锁,防止进程之间相互释放对方的锁
                stringRedisTemplate.delete(redisLockKey);
            }
        }


        return "ok";
    }
}
