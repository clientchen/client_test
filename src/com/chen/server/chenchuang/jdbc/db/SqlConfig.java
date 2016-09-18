package com.chen.server.chenchuang.jdbc.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2016/9/18.
 */
public class SqlConfig {

    /**
     * sql的配置文件
     */
    private static Properties sql_config;

    static {
        InputStream in = DBConfig.class.getClassLoader().getResourceAsStream("sql.properties");

        sql_config = new Properties();
        try {
            sql_config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getSqlConfig() {

        return sql_config;
    }


}
