package com.production.DAO.Base;

import com.production.util.JDBCUtil;
import com.sun.org.apache.bcel.internal.generic.FieldOrMethod;
import sun.security.jca.GetInstance;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Hao Wang
 * @Email wanghaobillgill@hotmail.com
 * @Create 2020/4/8 21:09
 * @Project Jdbc
 */
public abstract class BaseDAO<T> {
    private Class<T> clazz = null;

    {
        //获取父类方法的中的参数的泛型
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        clazz = (Class<T>) actualTypeArguments[0];
    }

    public void updateCom(Connection conn, String sql, Object... args) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(null, preparedStatement, null);
        }
    }

    public T getInstance(Connection conn, String sql, Object... args) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            if (resultSet.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object value = resultSet.getObject(i + 1);
                    String columnLabel = metaData.getColumnLabel(i + 1);

                    Field declaredField = clazz.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(t, value);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(null, preparedStatement, resultSet);
        }
        return null;
    }

    public List<T> getForList(Connection conn, String sql, Object... args) {
        PreparedStatement preparedStatement = null;
        List<T> list = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            list = new ArrayList<>();
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object objectValue = resultSet.getObject(i + 1);
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Field declaredField = clazz.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(t, objectValue);
                }
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(null, preparedStatement, resultSet);
        }
        return list;
    }

    public <E> E getOtherValue(Connection conn, String sql, Object... args) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return (E) resultSet.getObject(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(null, preparedStatement, resultSet);
        }
        return null;
    }

}
