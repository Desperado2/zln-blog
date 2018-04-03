package com.base.mysqlpool;

import com.mysql.jdbc.Driver;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

public class MyPoolImpl implements MyPoolI{
    private  String jdbcDriver="";
    private  String jdbcUrl="";
    private  String userName="";
    private  String password="";
    private  Integer initCount=0;
    private  Integer stepCount=0;
    private  Integer maxConnCount=0;


    private Vector<MyConn> myConns = new Vector<>();
    public MyPoolImpl(){
        init();
    }

    private void init(){
       InputStream in= getClass().getClassLoader().getResourceAsStream("dbconfig.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
            jdbcDriver= properties.getProperty("jdbcDriver");
            jdbcUrl= properties.getProperty("jdbcUrl");
            userName= properties.getProperty("userName");
            password= properties.getProperty("password");
            initCount=Integer.parseInt( properties.getProperty("initCount"));
            stepCount=Integer.parseInt( properties.getProperty("stepCount"));
            maxConnCount=Integer.parseInt( properties.getProperty("maxConnCount"));
            Driver driver= (Driver)Class.forName(jdbcDriver).newInstance();
            DriverManager.registerDriver(driver);

            createConnection(initCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public MyConn getConnection() {
       if(myConns.size() == 0){
           new RuntimeException("连接池没有可用资源");
       }
       MyConn myConn = getRealConn();
       while(myConn == null){
           createConnection(stepCount);
           myConn = getRealConn();
       }
        return myConn;
    }

    private synchronized  MyConn getRealConn(){
        for (MyConn conn:myConns) {
            if(!conn.isBusy()){
                Connection connection = conn.getConn();
                try {
                    if(connection.isValid(3000)){
                        Connection validConn = DriverManager.getConnection(jdbcUrl,userName,password);
                        conn.setConn(validConn);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                conn.setBusy(true);
                return conn;
            }
        }
        return null;
    }
    @Override
    public void createConnection(int number) {
        if(maxConnCount > 0 && myConns.size()+number > maxConnCount){
            new RuntimeException("连接池大小已达上限");
        }
        for(int i=0;i<number;i++){
            try {
                Connection connection = DriverManager.getConnection(jdbcUrl,userName,password);
                MyConn myConn = new MyConn(connection,false);
                myConns.add(myConn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
