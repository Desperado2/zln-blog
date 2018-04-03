package com.base.mysqlpool;

public class MyPoolManger {
    /**
     * 多个线程在加载静态内部类时是互斥的
     */
    private static class CreatePool{
        private static MyPoolImpl myPool = new MyPoolImpl();
    }

    public static MyPoolImpl getPoolInstace(){
        return CreatePool.myPool;
    }
}
