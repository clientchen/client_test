package com.chen.client.test;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.util.Properties;

/**
 * Created by 陈奇 on 2016/9/17 0017.
 */
public class JdbcTest {

    public Connection getConnection() throws Exception {
        String driverClass = null;
        String jdbcUrl = null;
        String user = null;
        String pwd = null;
        InputStream in = getClass().getClassLoader().getResourceAsStream("com/chen/client/myjdbc/sql.properties");
        System.out.println("文件地址:"+getClass().getClassLoader().getResource("com/chen/client/myjdbc/sql.properties"));
        System.out.println("文件地址:"+getClass().getClassLoader().getSystemResource("sql.properties"));
        Properties properties = new Properties();
        properties.load(in);
        driverClass = properties.getProperty("driver");
        jdbcUrl = properties.getProperty("url");
        user = properties.getProperty("user");
        pwd = properties.getProperty("pwd");
        //forName 返回一个类，newInstance创建一个对象
        Driver driver = (Driver) Class.forName(driverClass).newInstance();
        Properties info = new Properties();
        info.put("user", user);
        info.put("password", pwd);
        Connection connection = driver.connect(jdbcUrl, info);
        return connection;
    }

    @Test
    public void testConnection() throws Exception {
        System.out.println(getConnection());
    }
}