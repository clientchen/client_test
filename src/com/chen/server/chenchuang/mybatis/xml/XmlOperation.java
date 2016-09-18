package com.chen.server.chenchuang.mybatis.xml;

import com.chen.client.myjdbc.User;
import com.chen.server.chenchuang.mybatis.XmlInit;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by Administrator on 2016/9/18.
 */

/**
 * 直接通过xml中配置的字符窜来得到要执行的sql
 */
public class XmlOperation {


    /**
     * 查询所有
     * @return
     */
    public static List<User> seletAll() {

        SqlSession session = XmlInit.getSqlSession();
        List<User> user_list = session.selectList("com.chen.client.myjdbc.selectAll");
        System.out.println(user_list);
        return null;
    }

    public static void main(String[] args) {
        seletAll();
    }
}
