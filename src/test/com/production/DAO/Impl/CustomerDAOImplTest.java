package test.com.production.DAO.Impl;

import com.production.DAO.Impl.CustomerDAOImpl;
import com.production.bean.Customer;
import com.production.util.JDBCUtil;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * CustomerDAOImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>4ÔÂ 10, 2020</pre>
 */
public class CustomerDAOImplTest {
    CustomerDAOImpl dao = new CustomerDAOImpl();

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: insert(Connection conn, com.production.bean.Customer customer)
     */
    @Test
    public void testInsert() {
//TODO: Test goes here...
        Connection conn = null;
        try {
            conn = JDBCUtil.getConn("jdbc.properties");
            dao.insert(conn, new Customer(100, "Jack", "jack@1231.com", new Date(12323213133L)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(conn, null, null);
        }

    }

    /**
     * Method: delete(Connection conn, int id)
     */
    @Test
    public void testDelete() throws Exception {
//TODO: Test goes here...
        Connection conn = null;
        try {
            conn = JDBCUtil.getConn("jdbc.properties");
            dao.delete(conn,1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(conn, null, null);
        }
    }

    /**
     * Method: update(Connection conn, com.production.bean.Customer customer)
     */
    @Test
    public void testUpdate() {
//TODO: Test goes here...
        Connection conn = null;
        try {
            conn = JDBCUtil.getConn("jdbc.properties");
            dao.update(conn,new Customer(4,"Tom","tomcat@email.com",new Date(213132133213L)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(conn, null, null);
        }
    }

    /**
     * Method: selectById(Connection conn, int id)
     */
    @Test
    public void testSelectById() {
//TODO: Test goes here...
        Connection conn = null;
        try {
            conn = JDBCUtil.getConn("jdbc.properties");
            Customer customer = dao.selectById(conn, 2);
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(conn, null, null);
        }
    }

    /**
     * Method: selectAll(Connection conn)
     */
    @Test
    public void testSelectAll() {
//TODO: Test goes here...
        Connection conn = null;
        try {
            conn = JDBCUtil.getConn("jdbc.properties");
            List<Customer> customers = dao.selectAll(conn);
            customers.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(conn, null, null);
        }
    }

    /**
     * Method: getCount(Connection conn)
     */
    @Test
    public void testGetCount() {
//TODO: Test goes here...
        Connection conn = null;
        try {
            conn = JDBCUtil.getConn("jdbc.properties");
            long count = dao.getCount(conn);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(conn, null, null);
        }
    }

    /**
     * Method: getMaxBirth(Connection conn)
     */
    @Test
    public void testGetMaxBirth() {
//TODO: Test goes here...
        Connection conn = null;
        try {
            conn = JDBCUtil.getConn("jdbc.properties");
            Date maxBirth = dao.getMaxBirth(conn);
            System.out.println(maxBirth);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(conn, null, null);
        }
    }


} 
