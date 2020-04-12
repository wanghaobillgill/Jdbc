package com.atguigu.java1;

import com.atguigu.util.JDBCUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @Author Hao Wang
 * @Email wanghaobillgill@hotmail.com
 * @Create 2020/3/24 20:56
 * @Project Jdbc
 */
public class CustomerForQuery {
    @Test
    public void testCustomer(){
        String sql="select id,name,email,birth from customers where name=?";
        Customer customer = queryCustomer(sql, "Jack");
        System.out.println(customer);
    }

    public Customer queryCustomer(String sql,Object ...args){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection("jdbc.properties");
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            if (resultSet.next()){
                Customer customer = new Customer();
                for (int i = 0; i < columnCount; i++) {
                    Object object = resultSet.getObject(i + 1);
//                    String columnName = metaData.getColumnName(i + 1);
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Field declaredField = Customer.class.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(customer,object);
                }
                return customer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(preparedStatement,connection,resultSet);
        }
        return null;
    }
}
