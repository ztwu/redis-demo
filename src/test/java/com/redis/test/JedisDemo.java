package com.redis.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDemo {
    private Jedis jedis = null;

    /**
     * 单连接
     */
    @Test
    public void jedisSingleConn() {
        String host = "192.168.1.101";
        int port = 6379;
        Jedis jedis = new Jedis(host, port);
        jedis.auth("123456");
        jedis.set("name", "张三");
        jedis.set("age", "18");
        String s = jedis.get("name");
        String s1 = jedis.get("age");
        System.out.println(s);
        System.out.println(s1);
    }

    /**
     * 连接池连接
     */
    @Test
    public void jedisPoolConn() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1000);
        jedisPoolConfig.setMinIdle(10);
        jedisPoolConfig.setMaxIdle(15);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.1.100", 6379);
        Jedis jedis = jedisPool.getResource();
        jedis.auth("123456");
        //取数据
        String s = jedis.get("name");
        String s1 = jedis.get("age");
        System.out.println(s);
        System.out.println(s1);
    }

    /**
     * 连接池连接
     * 工具类
     */
    @Test
    public void jedisPoolConn1() {
        Jedis jedis1 = JedisPoolUntil.getJedis();
        //取数据
        String s = jedis1.get("name");
        String s1 = jedis1.get("age");
        System.out.println(s);
        System.out.println(s1);
    }
}