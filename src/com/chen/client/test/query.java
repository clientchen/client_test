package com.chen.client.test;

import com.chen.client.myjdbc.Dome;
import com.chen.client.myjdbc.User;
import org.junit.Test;

import java.util.List;

/**
 * Created by 陈奇 on 2016/9/16 0016.
 */
public class query {
    @Test
    public  void qe() throws Exception {

        List<User> users= Dome.readmytable();;

        for (User user:users
             ) {
            System.out.println(user.getName());
        }
    }
}
