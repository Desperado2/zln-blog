package com.base.mysqlpool;

public interface MyPoolI {

    public MyConn getConnection();

    public void createConnection(int number);
}
