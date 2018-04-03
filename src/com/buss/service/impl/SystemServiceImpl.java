package com.buss.service.impl;

import com.base.anno.Service;
import com.base.mysqlpool.MyConn;
import com.base.mysqlpool.MyPoolImpl;
import com.base.mysqlpool.MyPoolManger;
import com.buss.service.SystemService;

import java.sql.ResultSet;

@Service("systemService")
public class SystemServiceImpl implements SystemService {
    @Override
    public ResultSet execSql(String sql) throws Exception {
        ResultSet rs = null;
        MyPoolImpl pool = MyPoolManger.getPoolInstace();
        MyConn conn = pool.getConnection();
        rs = conn.execSql(sql);
        return rs;
    }
}
