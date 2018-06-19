package uts.dataprovider.uts.dataprovider.connection;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import uts.config.BootConfigLoader;

import java.time.Duration;
import java.util.Optional;

public class RedisPool {

    private static String REDIS_HOST = getRedisHostFromBootCOnfig().orElseGet(() -> "ssslc10wss.us.oracle.com");

    final JedisPoolConfig poolConfig = buildPoolConfig();
    private JedisPool jedisPool = new JedisPool(poolConfig, REDIS_HOST, 6379);

    //TODO  need to externalize these to  config
    private JedisPoolConfig buildPoolConfig() {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        return poolConfig;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }
    private static Optional<String> getRedisHostFromBootCOnfig(){

        return Optional.ofNullable(BootConfigLoader.bootstrapConfig.entrySet().iterator().next().getValue().getRedisHost());

    }
}
