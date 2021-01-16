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
 * 2.读多写多场景:
 * (1)必要时可以牺牲数据一致性,保全高并发可靠,只要确保下单时读取的库存是db的数据就可以,
 * 缓存设置缓存时间可以根据场景不同,缓存时间短一点,缓存和db数据不一致时间短一点,等待下次更新就可以一致,牺牲数据一致性
 * (2)不用缓存~
 * (3)
 * <p>
 * Created by Chr.yl on 2021/1/16.
 *
 * @author Chr.yl
 */
@RestController
@RequestMapping("rw")
public class RWLockController2 {



}
