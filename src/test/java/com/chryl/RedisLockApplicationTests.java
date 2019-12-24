package com.chryl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisLockApplicationTests {

    @Test
    public void contextLoads() {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
    }


    //1  HashMap()

/*
    // 1.无参构造方法、
    // 构造一个空的HashMap，初始容量为16，负载因子为0.75
    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
    }

    // 2.构造一个初始容量为initialCapacity，负载因子为0.75的空的HashMap，
    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }


    // 3.构造一个空的初始容量为initialCapacity，负载因子为loadFactor的HashMap
    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) throw new IllegalArgumentException("Illegal initial capacity: " +
                initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) throw new IllegalArgumentException("Illegal load factor: " +
                loadFactor);
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }    //最大容量
    //static final int MAXIMUM_CAPACITY = 1 << 30;

*/
    //Returns a power of two size for the given target capacity.
    static final int tableSizeFor(int cap) {
        final int MAXIMUM_CAPACITY = 1 << 30;
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }


}
