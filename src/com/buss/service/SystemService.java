package com.buss.service;

import com.base.anno.Service;
import com.base.mysqlpool.MyConn;
import com.base.mysqlpool.MyPoolImpl;
import com.base.mysqlpool.MyPoolManger;

import java.sql.ResultSet;

public interface SystemService {

    public  ResultSet execSql(String sql) throws Exception;
}
