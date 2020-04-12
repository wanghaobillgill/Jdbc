package com.production.util;

import org.junit.Test;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @Author Hao Wang
 * @Email wanghaobillgill@hotmail.com
 * @Create 2020/4/8 20:45
 * @Project Jdbc
 */
public class JDBCUtil {
    public static Connection getConn(String filePath) throws Exception {
        InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream(filePath);
        Properties properties = new Properties();
        properties.load(systemResourceAsStream);
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String classDriver = properties.getProperty("classDriver");

        Class.forName(classDriver);
        return DriverManager.getConnection(url, user, password);
    }

    public static void closeResource(Connection conn, Statement statement, ResultSet resultSet) {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (statement != null)
                statement.close();
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
