package com.production.DAO;

import com.production.bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * @Author Hao Wang
 * @Email wanghaobillgill@hotmail.com
 * @Create 2020/4/8 21:10
 * @Project Jdbc
 */
public interface CustomerDAO {
    /**
     *
     * @param conn
     * @param customer
     */
    void insert(Connection conn,Customer customer);

    /**
     *
     * @param conn
     * @param id
     */
    void delete(Connection conn,int id);

    /**
     *
     * @param conn
     * @param customer
     */
    void update(Connection conn,Customer customer);

    /**
     *
     * @param conn
     * @param id
     * @return customer
     */
    Customer selectById(Connection conn,int id);

    /**
     *
     * @param conn
     * @return list
     */
    List<Customer> selectAll(Connection conn);

    /**
     *
     * @param conn
     * @return count;
     */
    long getCount(Connection conn);

    /**
     *
     * @param conn
     * @return value
     */
    Date getMaxBirth(Connection conn);
}
