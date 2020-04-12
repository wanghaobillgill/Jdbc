package com.atguigu.java;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author Hao Wang
 * @Email wanghaobillgill@hotmail.com
 * @Create 2020/3/20 20:05
 * @Project Jdbc
 */
public class JdbcTest {
    @Test
    public void testConnection1() throws SQLException {
        Driver driver=new com.mysql.jdbc.Driver();

        String url="jdbc:mysql://localhost:3306/mysql";

        Properties properties=new Properties();

        properties.setProperty("user","root");

        properties.setProperty("password","12345678");

        Connection connection=driver.connect(url,properties);
        //如果数据库报错""@localhost就是properties的值没有传进来

        System.out.println(connection);
    }

    @Test
    public void testConnection2() throws Exception {
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Object o = clazz.newInstance();
        Driver driver= (Driver) o;
        String url="jdbc:mysql://localhost:3306/mysql";
        Properties info=new Properties();
        info.setProperty("user","root");
        info.setProperty("password","12345678");
        Connection connect = driver.connect(url, info);
        System.out.println(connect);
    }

    @Test
    public void testConnection3() throws Exception{
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();
        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "12345678");
        System.out.println(connection);
    }

    @Test
    public void testConnection4() throws Exception{
        //在加载类com.mysql.jdbc.Driver的时候有静态代码块自动注册了驱动
        //相较于Oracle，MySQL的类的加载也可以省略
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "12345678");
        System.out.println(connection);
    }
    @Test
    public void testConnection5() throws Exception{
        InputStream resourceAsStream = JdbcTest.class.getClassLoader().getResourceAsStream("jdbc.properties");

        Properties properties=new Properties();
        properties.load(resourceAsStream);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String classDriver = properties.getProperty("classDriver");

        Class.forName(classDriver);

        Connection connection = DriverManager.getConnection(url,user, password);

        System.out.println(connection);


    }
    @Test
    public void testConnection6() throws Exception{
        InputStream resourceAsStream = JdbcTest.class.getClassLoader().getResourceAsStream("jdbc.properties");

        Properties properties=new Properties();
        properties.load(resourceAsStream);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String classDriver = properties.getProperty("classDriver");

        Class.forName(classDriver);

        Connection connection = DriverManager.getConnection(url,user, password);

        System.out.println(connection);


    }
}
