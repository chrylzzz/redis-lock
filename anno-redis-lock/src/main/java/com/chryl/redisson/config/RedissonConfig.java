package com.chryl.redisson.config;/*
package com.chryl.redis.redis.config;

*/
/**
 * Created by Chr.yl on 2020/6/4.
 *
 * @author Chr.yl
 *//*


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

import static org.apache.tomcat.jni.SSL.setPassword;

@Configuration
public class RedissonConfig {


//    @Bean
//    public RedissonClient redissonClient() {
//        Config config = new Config();
//        config.useClusterServers()//集群配置
//                .setScanInterval(2000)
//                .addNodeAddress("redis://10.0.29.30:6379", "redis://10.0.29.95:6379")
//                .addNodeAddress("redis://10.0.29.205:6379")
//                .setPassword("123456");
//
//        RedissonClient redisson = Redisson.create(config);
//
//        return redisson;
//    }


//        @Bean
//    public RedissonClient redissonClient() {
//        Config config = new Config();
//        config.useMasterSlaveServers()//主从模式
//                .setMasterAddress("")
//                .setSlaveAddresses()
//                .setPassword("123456");
//
//        RedissonClient redisson = Redisson.create(config);
//
//        return redisson;
//    }


    //------
//    @Bean
//    public Redisson redisson() {
//        Config config = new Config();
//        config.useSingleServer()//单机模式
//                .setAddress("redis://192.168.228.128:6379")//redisson连接redis
//                .setPassword("123456")
//                .setDatabase(0);
//        return (Redisson) Redisson.create(config);
//    }


    @Bean
    public RedissonClient redisson() {
        Config config = new Config();
        config.useSingleServer()//单机模式
                .setAddress("redis://192.168.228.128:6379")//redisson连接redis
                .setPassword("123456")
                .setDatabase(0);
        return Redisson.create(config);
    }

}*/
