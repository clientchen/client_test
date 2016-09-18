package com.chen.server.chenchuang.jdbc.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Administrator on 2016/9/18.
 */
public class DBFactory {

    //数据库驱动对象
    private static Driver db_driver;

    public static void init() {

        String driver = DBConfig.getDriver();
        Class driver_class = null;
        try {
            driver_class = Class.forName(driver);
            db_driver = (Driver) driver_class.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("加载数据库驱动失败！！");
        }
    }


    /**
     * 创建数据库连接
     *
     * @return
     */
    public static Connection createConnection() {

        if (null == db_driver) {
            init();
        }
        Properties info = new Properties();
        info.put("user", DBConfig.getUser());
        info.put("password", DBConfig.getPass());

        try {
            return db_driver.connect(DBConfig.getUrl(), info);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("获取数据库连接失败！！");
        }

    }


}
