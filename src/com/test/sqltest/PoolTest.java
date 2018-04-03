package com.test.sqltest;

import com.base.mysqlpool.MyConn;
import com.base.mysqlpool.MyPoolImpl;
import com.base.mysqlpool.MyPoolManger;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PoolTest {

    @Test
    public void test1() throws SQLException {
        MyPoolImpl myPoolManger = MyPoolManger.getPoolInstace();
        MyConn conn = myPoolManger.getConnection();
        ResultSet rs =conn.execSql("select * from  tb_czl_customer");
        while (rs.next()){
            System.out.println(rs.getString("id"));;
        }
    }

}
