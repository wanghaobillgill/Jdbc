package com.atguigu.util;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @Author Hao Wang
 * @Email wanghaobillgill@hotmail.com
 * @Create 2020/3/26 20:36
 * @Project Jdbc
 */
public class SQLUtil {
    @Test
    public void test1(){
//        Connection connection = JDBCUtil.getConnection("jdbc.properties");
        String sql="";
//        SQLUtil.update(connection,sql,);

    }
    public static int update(String sql,Object ...args){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection("jdbc.properties");
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }

            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(preparedStatement,connection);
        }
        return 0;
    }
    public static int update(Connection conn,String sql,Object ...args){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }

            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(preparedStatement,null);
        }
        return 0;
    }
}
