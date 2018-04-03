package com.base.mysqlpool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConn {

    private Connection conn;
    private boolean isBusy = false;

    public MyConn(Connection conn, boolean isBusy) {
        this.conn = conn;
        this.isBusy = isBusy;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public void close(){
        this.isBusy = false;
    }

    public ResultSet execSql(String sql){
        ResultSet rs = null;
        Statement statement = null;
        try{
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rs;
    }
}
