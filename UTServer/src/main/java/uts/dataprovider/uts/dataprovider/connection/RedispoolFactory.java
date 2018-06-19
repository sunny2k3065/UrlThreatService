package uts.dataprovider.uts.dataprovider.connection;

public class RedispoolFactory {
    private static RedispoolFactory ourInstance = new RedispoolFactory();

    public static RedispoolFactory getInstance() {
        return ourInstance;
    }

    private RedisPool pool = new RedisPool();

    private RedispoolFactory() {

    }

    public RedisPool getPool() {
        return pool;
    }
}
