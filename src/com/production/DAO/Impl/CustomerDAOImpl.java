package com.production.DAO.Impl;

import com.production.bean.Customer;
import com.production.DAO.Base.BaseDAO;
import com.production.DAO.CustomerDAO;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * @Author Hao Wang
 * @Email wanghaobillgill@hotmail.com
 * @Create 2020/4/8 21:11
 * @Project Jdbc
 */
public class CustomerDAOImpl extends BaseDAO<Customer> implements CustomerDAO {
    @Override
    public void insert(Connection conn, com.production.bean.Customer customer) {
        String sql = "insert into customers(name,email,birth) value(?,?,?)";
        updateCom(conn, sql, customer.getName(), customer.getEmail(), customer.getBirth());
        System.out.println("插入成功");
    }

    @Override
    public void delete(Connection conn, int id) {
        String sql = "delete from customers where id = ?";
        updateCom(conn, sql, id);
        System.out.println("删除成功");
    }

    @Override
    public void update(Connection conn, com.production.bean.Customer customer) {
        String sql = "update customers set name = ?,email = ?,birth = ? where id = ?";
        updateCom(conn, sql, customer.getName(), customer.getEmail(), customer.getBirth(), customer.getId());
        System.out.println("更新成功");
    }

    @Override
    public com.production.bean.Customer selectById(Connection conn, int id) {
        String sql = "select id,name,email,birth from customers where id = ?";
        return (Customer) getInstance(conn, sql, id);
    }

    @Override
    public List<com.production.bean.Customer> selectAll(Connection conn) {
        String sql = "select id,name,email,birth from customers";
        return getForList(conn, sql);
    }

    @Override
    public long getCount(Connection conn) {
        String sql = "select count(*) from customers";
        return (long) getOtherValue(conn, sql);
    }

    @Override
    public Date getMaxBirth(Connection conn) {
        String sql = "select max(birth) from customers";
        return (Date) getOtherValue(conn, sql);
    }
}
