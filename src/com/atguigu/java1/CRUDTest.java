package com.atguigu.java1;

import com.atguigu.util.JDBCUtil;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * @Author Hao Wang
 * @Email wanghaobillgill@hotmail.com
 * @Create 2020/3/21 13:18
 * @Project Jdbc
 */
public class CRUDTest {
    @Test
    public void testQuery1(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection("jdbc.properties");
            String sql="select id,name,email,birth from customers where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,1);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);

                Customer customer=new Customer(id,name,email,birth);

                System.out.println(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(preparedStatement,connection,resultSet);
        }



    }
    @Test
    public void testCommonUpdate(){
        String sql="update `order` set order_name= ? where order_id= ? ";
        update(sql,"DD","2");
    }
    public static void update(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection("jdbc.properties");
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1, args[i]);
            }
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JDBCUtil.closeResource(preparedStatement, connection);
        }


    }

    @Test
    public void test2() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection("jdbc.properties");

            String sql = "update customers set name=? where id=?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setObject(1, "张学友");

            preparedStatement.setObject(2, 10);

            preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(preparedStatement, connection);
        }


    }

    @Test
    public void test1() {
        InputStream resourceAsStream = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

            Properties properties = new Properties();

            properties.load(resourceAsStream);

            String user = properties.getProperty("user");

            String password = properties.getProperty("password");

            String classDriver = properties.getProperty("classDriver");

            String url = properties.getProperty("url");

            connection = DriverManager.getConnection(url, user, password);

            String sql = "insert into customers(name,email,birth)value(?,?,?)";//预编译sql

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, "Tom");

            preparedStatement.setString(2, "tom@gmail.com");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date date = simpleDateFormat.parse("2001-02-03");

            preparedStatement.setDate(3, new Date(date.getTime()));

            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (resourceAsStream != null)
                    resourceAsStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
