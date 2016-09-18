package com.chen.server.chenchuang.mybatis.clas;

import com.chen.client.myjdbc.User;

import java.util.List;

/**
 * Created by Administrator on 2016/9/18.
 */
public interface UsersMapper {

    List<User> selectAll();
}
