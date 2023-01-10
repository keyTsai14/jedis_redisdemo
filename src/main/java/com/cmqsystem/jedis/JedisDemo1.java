package com.cmqsystem.jedis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * @Description TODO
 * @Author admin
 * @Date 2023/1/8
 **/
public class JedisDemo1 {

    public static void main(String[] args) {

        // 创建 Jedis 对象
        Jedis jedis = new Jedis("43.139.108.162",6379);
        jedis.auth("root");
        String ping = jedis.ping();
//        System.out.println(ping);

//        jedis.set("k1","v1");
//        jedis.set("k2","v2");
//        jedis.set("k3","v3");
        jedis.mset("k1","v1","k2","v2","k3","v3");
        List<String> mget = jedis.mget("k1", "k2");
        System.out.println(mget);

//        jedis.flushDB();
//        Set<String> keys = jedis.keys("*");
//        for (String key : keys) {
//            System.out.println(key);
//        }

    }
}
