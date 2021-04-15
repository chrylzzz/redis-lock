package com.chryl.controller.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 问题:缓存和db双写不一致场景
 * 1.读多写少场景:
 * redisson读写锁的使用:
 * 读写锁:只读的时候不加锁,只写的时候不加锁,读写操作一起执行就加锁,转为串行执行
 * <p>
 * Created by Chr.yl on 2021/1/16.
 *
 * @author Chr.yl
 */
@RestController
@RequestMapping("rw")
public class RWLockController {

    @Autowired
    private Redisson redisson;

    String read_write_lock = "read_write_lock_flag";

    @GetMapping("read")
    public void read() {

        RReadWriteLock readWriteLock = redisson.getReadWriteLock(read_write_lock);
        RLock rLock = readWriteLock.readLock();//获取读锁
        try {
            rLock.lock();
            //模拟查询redis操作


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();//释放读锁
        }

    }


    @GetMapping("write")
    public void write() {
        RReadWriteLock readWriteLock = redisson.getReadWriteLock(read_write_lock);
        RLock rLock = readWriteLock.writeLock();
        try {
            rLock.lock();//写锁加锁
            //模拟更改redis操作


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();//释放写锁
        }
    }




}
