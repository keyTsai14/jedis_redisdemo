package com.cmqsystem.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

/**
 * @Description TODO
 * @Author admin
 * @Date 2023/1/16
 **/
public class JedisClusterTest {
    public static void main(String[] args) {
        // 创建对象
        HostAndPort hostAndPort = new HostAndPort("43.139.108.162", 6379);
        JedisCluster jedisCluster = new JedisCluster(hostAndPort);

        jedisCluster.set("b1","value1");
        String value = jedisCluster.get("b1");
        System.out.println("value:"+value);

        jedisCluster.close();
    }

}
