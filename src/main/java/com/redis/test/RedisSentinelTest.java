package com.redis.test;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class RedisSentinelTest {

    public static void main(String[] args) {

        Set<String> sentinels = new HashSet<String>();
        String hostAndPort1 = "192.168.1.100:26379";
        String hostAndPort2 = "192.168.1.101:26379";
        String hostAndPort3 = "192.168.1.102:26379";
        sentinels.add(hostAndPort1);
        sentinels.add(hostAndPort2);
        sentinels.add(hostAndPort3);

        String clusterName = "mymaster";
        String password = "123456";

        JedisSentinelPool redisSentinelJedisPool = new JedisSentinelPool(clusterName,sentinels,password);

        Jedis jedis = null;
        try {
            jedis = redisSentinelJedisPool.getResource();
            jedis.set("key", "value");
            System.out.println(jedis.get("key"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redisSentinelJedisPool.returnBrokenResource(jedis);
        }
        redisSentinelJedisPool.close();
    }

}
