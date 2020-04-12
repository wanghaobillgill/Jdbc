package com.atguigu.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @Author Hao Wang
 * @Email wanghaobillgill@hotmail.com
 * @Create 2020/3/21 13:45
 * @Project Jdbc
 */
public class JDBCUtil {
    public static Connection getConnection(String filePath) throws Exception {

        InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream(filePath);

        Properties properties = new Properties();

        properties.load(resourceAsStream);

        String classDriver = properties.getProperty("classDriver");

        Class.forName(classDriver);

        String url = properties.getProperty("url");

        String user = properties.getProperty("user");

        String password = properties.getProperty("password");

        Connection connection = DriverManager.getConnection(url, user, password);

        return connection;

    }

    public static void closeResource(Statement statement,Connection connection){
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void closeResource(Statement statement,Connection connection,ResultSet resultSet){
        try {
            if (statement != null)
                statement.close();
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
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
