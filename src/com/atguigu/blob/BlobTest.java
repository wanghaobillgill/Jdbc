package com.atguigu.blob;

import com.atguigu.java1.Customer;
import com.atguigu.util.JDBCUtil;
import org.junit.Test;

import java.io.*;
import java.sql.*;

/**
 * @Author Hao Wang
 * @Email wanghaobillgill@hotmail.com
 * @Create 2020/3/26 21:36
 * @Project Jdbc
 */
public class BlobTest {
    @Test
    public void testInsert() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        FileInputStream fileInputStream = null;
        try {
            connection = JDBCUtil.getConnection("jdbc.properties");
            String sql = "insert into customers(name,email,birth,photo) values (?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, "王浩");
            preparedStatement.setObject(2, "billgill@hotmail.com");
            preparedStatement.setObject(3, "1998-11-04");
            fileInputStream = new FileInputStream("批注 2020-02-18 102915.png");
            preparedStatement.setObject(4, fileInputStream);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            JDBCUtil.closeResource(preparedStatement, connection);
        }
    }

    @Test
    public void test2() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        InputStream binaryStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            connection = JDBCUtil.getConnection("jdbc.properties");
            String sql = "select id,name,email,birth,photo from customers where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,23);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Object id = resultSet.getObject("id");
                Object name = resultSet.getObject("name");
                Object email = resultSet.getObject("email");
                Object birth = resultSet.getObject("birth");
                Customer customer = new Customer((int) id, (String) name, (String) email, (Date) birth);
                Blob photo = resultSet.getBlob("photo");
                binaryStream = photo.getBinaryStream();
                fileOutputStream = new FileOutputStream("temp.png");
                byte[] b = new byte[1024];
                int lenth;
                while ((lenth = binaryStream.read(b)) != -1) {
                    fileOutputStream.write(b, 0, lenth);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(preparedStatement, connection, resultSet);
            try {
                if (binaryStream != null)
                    binaryStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileOutputStream != null)
                    fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
