package com.chen.client.myjdbc;

import java.sql.*;

/**
 * Created by
 * on 2016/6/1 0001.
 */
public  class JdbcTool2 {
   private   static String url = "jdbc:mysql://localhost:3306/spdb";
   private   static String user = "root";
   private   static String password = "123";

    public JdbcTool2(){}


    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection()throws Exception{
        return DriverManager.getConnection(url,user,password);
    }

    public static void free(ResultSet rs,Statement st,Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();

            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
