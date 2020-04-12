package com.atguigu.java1;

import com.atguigu.java.JdbcTest;
import com.atguigu.util.JDBCUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 使用PreparedStatement实现针对不同表的通用的查询操作
 * @Author Hao Wang
 * @Email wanghaobillgill@hotmail.com
 * @Create 2020/3/25 19:19
 * @Project Jdbc
 */
public class PreparedStatementQueryTest {

    @Test
    public void test(){
        String sql="select order_id orderId,order_name orderName,order_date orderDate\n" +
                "from `order` where order_id>?";
        List<Order> forList = getForList(Order.class, sql, 1);
        forList.forEach(System.out::println);

        sql = "select id,name,email,birth from customers where id > ?";
        List<Customer> forList1 = getForList(Customer.class, sql, 1);
        forList1.forEach(System.out::println);
    }


    /**
     *
     * @param clazz
     *
     * @param sql
     * @param args
     * @param <T>
     * @return ts
     */
    public <T> List<T> getForList(Class<T> clazz, String sql, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection("jdbc.properties");
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            resultSet = preparedStatement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();

            int columnCount = metaData.getColumnCount();
            List<T> ts = new ArrayList<>();
            while (resultSet.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Field declaredField = clazz.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    Object object = resultSet.getObject(i + 1);
                    declaredField.set(t, object);
                }
                ts.add(t);
            }
            return ts;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(preparedStatement, connection, resultSet);
        }
        return null;
    }

    /**
     *
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return t
     */
    public <T> T getT(Class<T> clazz,String sql,Object ...args){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection("jdbc.properties");
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i + 1]);
            }

            resultSet = preparedStatement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();

            int columnCount = metaData.getColumnCount();
            if (resultSet.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Field declaredField = clazz.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    Object object = resultSet.getObject(i + 1);
                    declaredField.set(t, object);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(preparedStatement, connection, resultSet);
        }
        return null;
    }


}
