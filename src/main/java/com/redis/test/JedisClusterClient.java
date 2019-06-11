package com.redis.test;

import redis.clients.jedis.*;
import java.util.HashSet;
import java.util.Set;

public class JedisClusterClient {

    private static int count = 0;

    private static final JedisClusterClient redisClusterClient = new JedisClusterClient();

    /**
     * 私有构造函数
     */

    private JedisClusterClient() {
    }

    public static JedisClusterClient getInstance() {

        return redisClusterClient;

    }

    private JedisPoolConfig getPoolConfig() {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1000);
        config.setMaxIdle(100);
        config.setTestOnBorrow(true);
        return config;

    }

    public void SaveRedisCluster() {

        Set jedisClusterNodes = new HashSet();

        jedisClusterNodes.add(new HostAndPort("192.168.1.100", 36379));
        jedisClusterNodes.add(new HostAndPort("192.168.1.101", 36379));
        jedisClusterNodes.add(new HostAndPort("192.168.1.102", 36379));

        JedisCluster jc = new JedisCluster(jedisClusterNodes,3000,2000,100,"123456",getPoolConfig());

        jc.set("cluster", "this is a redis cluster");

        String result = jc.get("cluster");

        System.out.println(result);

    }

    public static void main(String[] args) {

        JedisClusterClient jedisClusterClient = JedisClusterClient.getInstance();
        jedisClusterClient.SaveRedisCluster();

    }
}
