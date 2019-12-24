/*
package com.chryl.controller;

import java.util.HashMap;
import java.util.Map;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import static com.sun.xml.internal.fastinfoset.util.ValueArray.MAXIMUM_CAPACITY;

*/
/**
 * Created by Chryl on 2019/10/14.
 *//*

public class HashMapTest {


    //1  HashMap()

    */
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

    *//*

    //Returns a power of two size for the given target capacity.
    static final int tableSizeFor(int cap) {
        final int MAXIMUM_CAPACITY = 1 << 30;
        int n = cap - 1;
//        int n = cap ;
//        首先，int n = cap -1是为了防止cap已经是2的幂时，执行完后面的几条无符号右移操作之后，
//        返回的capacity是这个cap的2倍，因为cap已经是2的幂了，就已经满足条件了。
//        如果不懂可以往下看完几个无符号移位后再回来看。（建议自己在纸上画一下）

        System.out.println(n >>> 1);
        n |= n >>> 1;
        System.out.println(n >>> 2);
        n |= n >>> 2;
        System.out.println(n >>> 4);
        n |= n >>> 4;
        System.out.println(n >>> 8);
        n |= n >>> 8;
        System.out.println(n >>> 16);
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    // 4. 构造一个和指定Map有相同mappings的HashMap，初始容量能充足的容下指定的Map,负载因子为0.75
    public HashMap(Map<? extends K, ? extends V> m) {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        putMapEntries(m, false);
    }

    */
/**
     * 将m的所有元素存入本HashMap实例中
     *//*

    final void putMapEntries(Map<? extends K, ? extends V> m, boolean evict) {        //得到 m 中元素的个数
        int s = m.size();        //当 m 中有元素时，则需将map中元素放入本HashMap实例。
        if (s > 0) {            // 判断table是否已经初始化，如果未初始化，则先初始化一些变量。（table初始化是在put时）
            if (table == null) { // pre-size
                // 根据待插入的map 的 size 计算要创建的　HashMap 的容量。
                float ft = ((float) s / loadFactor) + 1.0F;
                int t = ((ft < (float) MAXIMUM_CAPACITY) ?
                        (int) ft : MAXIMUM_CAPACITY);                // 把要创建的　HashMap 的容量存在　threshold　中
                if (t > threshold)
                    threshold = tableSizeFor(t);
            }            // 如果table初始化过，因为别的函数也会调用它，所以有可能HashMap已经被初始化过了。
            // 判断待插入的　map 的 size,若　size 大于　threshold，则先进行　resize()，进行扩容
            else if (s > threshold)
                resize();            //然后就开始遍历 带插入的 map ，将每一个 <Key ,Value> 插入到本HashMap实例。
            for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
                K key = e.getKey();
                V value = e.getValue();                // put(K,V)也是调用　putVal　函数进行元素的插入
                putVal(hash(key), key, value, false, evict);
            }
        }
    }


    public static void main(String[] args) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        System.out.println(tableSizeFor(0));
    }
}
*/
