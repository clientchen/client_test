package com.chen.server.chenchuang.jdbc.db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/9/18.
 */
public class DBPool {

    //存放连接池的数组
    private static Connection[] connection_pool = new Connection[20];
    //对池中的连接是否空闲进行标记
    private static boolean[] connection_free = new boolean[20];


    static{
        for (int i = 0; i < connection_free.length; i++) {
            connection_free[i]=true;
        }

    }

     public synchronized  static void close() {
         for (int i = 0; i < connection_free.length; i++) {
             try {
                 connection_pool[i].close();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
    }

    /**
     * 从池中获取连接，池已满而且没有空闲的则等待，
     * @return
     */
    public static Connection getConnection() {
        while (true) {

            synchronized (DBPool.class) {
                for(int i = 0; i< connection_free.length; i++) {
                    if (connection_free[i]) {
                        if (null != connection_pool[i]) {
                            return connection_pool[i];
                        }else {
                            connection_pool[i]=DBFactory.createConnection();
                            return connection_pool[i];

                        }
                    }


                }

                //如果没有空闲的连接，则等待一会儿，十亿分之一秒
                try {
                    Thread.sleep(0, 1);
                } catch (InterruptedException e) {
                    throw new RuntimeException("Thread main has be interrupted.");
                }

            }
        }

    }


    /**
     *把某个连接设置为空闲状态
     * @param connection
     */
    public static void freeConnection(Connection connection) {

        for (int i = 0; i < connection_free.length; i++) {
            if (connection == connection_pool[i]) {
                connection_free[i]=true;
            }
        }

    }





}
