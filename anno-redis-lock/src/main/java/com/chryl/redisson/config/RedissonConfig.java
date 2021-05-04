//package com.chryl.redisson.config;
//
//
///**
// * Created by Chr.yl on 2020/6/4.
// *
// * @author Chr.yl
// */
//
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 可以使用配置类或者配置文件来配置
// */
//@Configuration
//public class RedissonConfig {
//
//
//    @Bean(destroyMethod = "shutdown")
//    public RedissonClient redisson() {
//        Config config = new Config();
//
////        config.useClusterServers().addNodeAddress("127.0.0.1:6379");
////        config.useSingleServer().setAddress("rediss://127.0.0.1:6379");//安全连接
//        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
//
//        return Redisson.create(config);
//    }
//
//    /**
//     * 集群
//     */
////    @Bean
////    public RedissonClient redissonClient() {
////        Config config = new Config();
////        config.useClusterServers()//集群配置
////                .setScanInterval(2000)
////                .addNodeAddress("redis://10.0.29.30:6379", "redis://10.0.29.95:6379")
////                .addNodeAddress("redis://10.0.29.205:6379")
////                .setPassword("123456");
////
////        RedissonClient redisson = Redisson.create(config);
////
////        return redisson;
////    }
//
//
//    /**
//     * 主从模式
//     *
//     * @return
//     */
////    @Bean
////    public RedissonClient redissonClient() {
////        Config config = new Config();
////        config.useMasterSlaveServers()//主从模式
////                .setMasterAddress("")
////                .setSlaveAddresses()
////                .setPassword("123456");
////
////        RedissonClient redissonClient = Redisson.create(config);
////
////        return redissonClient;
////    }
//
//
//}
