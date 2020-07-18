package com.chryl.controller.test;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Chr.yl on 2020/7/18.
 *
 * @author Chr.yl
 */
@RestController
@RequestMapping("/test")
public class TestRedissonController {

    @Autowired
    private Redisson redisson;

    int anInt = 1;

    @GetMapping("/show")
    public Object show() {
        String redisLockKey = "testLock";
        RLock lock = redisson.getLock(redisLockKey);
        try {
            boolean tryLock = lock.tryLock();
//            synchronized (this) {
            if (tryLock) {
                anInt++;
            }
//            }
            System.out.println(anInt);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return anInt;
    }

}
