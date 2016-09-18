package com.chen.server.chenchuang.jdbc.db;

import com.chen.client.myjdbc.User;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

/**
 * Created by Administrator on 2016/9/18.
 */
public class JDBCTool {

    /**
     * 查询操作
     * @param sql
     * @param target
     * @param connection
     * @param <T>
     * @return
     */
    public static <T> List<T> select(String sql, Class<T> target, Connection connection) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            //获取列信息
            String[] column = null;
            ResultSetMetaData metaData = rs.getMetaData();
            int column_count = metaData.getColumnCount();
            column = new String[column_count];
            for (int i = 0; i<column_count; i++) {
                column[i] = metaData.getColumnLabel(i+1);
            }

            List<T> target_list = new ArrayList<>();

            while (rs.next()) {

                T target_obj = target.newInstance();
                for(int i = 0; i<column.length; i++){

                    Object db_value = rs.getObject(column[i]);

                    String set_method = "set" + column[i].substring(0, 1).toUpperCase() + column[i].substring(1);

                    Class set_method_param_type = null;
                    if (db_value instanceof java.sql.Date || db_value instanceof java.sql.Timestamp) {
                        set_method_param_type = java.util.Date.class;
                    }else {
                        set_method_param_type = db_value.getClass();
                    }
                    Method reflect_method = target.getMethod(set_method,set_method_param_type);
                    reflect_method.invoke(target_obj, rs.getObject(column[i]));
                }
                target_list.add(target_obj);
            }

            return target_list;

        } catch (Exception e) {

            throw new RuntimeException("数据库查询过程出现异常", e);

        }finally {
            closePSandRS(ps, rs);
            releaseConnection(connection);
        }

    }


    /**
     * 执行更新操作
     * @param sql
     * @param connection
     * @return
     */
    public static int update(String sql,Connection connection) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(sql);
            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("数据库更新操作失败");
        }finally {
            closePSandRS(ps, rs);
            releaseConnection(connection);
        }
    }


    /**
     * 执行inset操作
     * @param connection
     */

    public static int insert(String sql,Connection connection) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                Object number = resultSet.getObject(1);
                return (int)Double.parseDouble(number.toString());
            }
            return 0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("数据库更新操作失败");
        }finally {
            closePSandRS(ps, rs);
            releaseConnection(connection);
        }
    }


    /**
     * 释放连接到连接池中
     * @param connection
     */
    private static void releaseConnection(Connection connection) {
        DBPool.freeConnection(connection);
    }

    /**
     * 关闭rs 和 ps
     * @param ps
     * @param rs
     */
    private static void closePSandRS(PreparedStatement ps, ResultSet rs) {
        if (rs != null) {

            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {


        System.out.println(select(SqlConfig.getSqlConfig().getProperty("select_all"),User.class, DBPool.getConnection()));


       /* System.out.println(select("select * from users",User.class,DBPool.getConnection()).get(1));

        String in = "insert into users VALUES(" +
                " 0,'helloghfgg','hhhw','hhhw',1)";

        System.out.println(insert(in,DBPool.getConnection()));


        String update = "update users set user_name = 'chenchuang' where user_id = 34 ";
        update(update, DBPool.getConnection());*/
    }

}
