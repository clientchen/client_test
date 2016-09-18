package com.chen.server.chenchuang.mybatis.clas;

import com.chen.client.myjdbc.User;
import com.chen.server.chenchuang.mybatis.XmlInit;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by Administrator on 2016/9/18.
 */

/**
 * 通过类来映射得到要执行的sql
 */
public class ClassOperation {

    public static List<User> seletAll() {

        SqlSession session = XmlInit.getSqlSession();
        UsersMapper users_mapper = session.getMapper(UsersMapper.class);

        List<User> user_list = users_mapper.selectAll();
        System.out.println(user_list);
        return null;
    }

    public static void main(String[] args) {
        seletAll();
    }
}
