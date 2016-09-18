package com.chen.client.myjdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by 陈奇
 * on 2016/6/2 0002.
 */
public class Dome {
    public static void main(String[] args) throws Exception {

        read("");
    }

    public static String read(String name) throws Exception {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = JdbcTool2.getConnection();
//创建SQL语句

            String sql = "select user_id, name, pass_word from user";
            ps = conn.prepareStatement(sql);
//            ps.setString(1, name);


//            String sql = "delete from user where id>2";
//            执行语句
            rs = ps.executeQuery();
            while (rs.next()) {

                System.out.println(rs.getObject("userId") + "\t  "
                        + rs.getObject("username") + "\t" + rs.getObject("passwd"));

                Object ob = rs.getObject("passwd");
                System.out.println(ob.toString());
                return ob.toString();
            }
            return null;
        } finally {
            JdbcTool2.free(rs, ps, conn);
        }
    }

@Test
    public static List<User> readmytable() throws Exception {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = JdbcTool2.getConnection();
//创建SQL语句

            String sql = "select * from users ";
            ps = conn.prepareStatement(sql);
//            ps.setString(1, name);


//            String sql = "delete from user where id>2";
//            执行语句
            rs = ps.executeQuery();

            List<User> res = new ArrayList<User>();
            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("userId"));
//                user.setName(rs.getString(2));
                user.setEmail(rs.getString(4));
                user.setGrade(rs.getInt(5));

                res.add(user);
            }


            return res;
        } finally {
            JdbcTool2.free(rs, ps, conn);
        }
    }
}