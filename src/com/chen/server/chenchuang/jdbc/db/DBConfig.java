package com.chen.server.chenchuang.jdbc.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2016/9/18.
 */
public class DBConfig {

    private static Properties db_config ;

    static {
        InputStream in = DBConfig.class.getClassLoader().getResourceAsStream("db.properties");

        db_config = new Properties();
        try {
            db_config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库的host:port
     * @return
     */
    public static String getUrl() {
        return db_config.getProperty("url");
    }


    /**
     * 获取数据库驱动
     * @return
     */
    public static String getDriver() {
        return db_config.getProperty("driver");
    }

    /**
     * 获取用户名
     * @return
     */
    public static String getUser() {
        return db_config.getProperty("user");
    }


    /**
     * 获取密码
     * @return
     */
    public static String getPass() {
        return db_config.getProperty("pwd");


    }

}
