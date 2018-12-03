package com.cheer.shoppingcart.dao;

import com.cheer.shoppingcart.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     * 获取user表数据总数
     */
    int getcount();
    /**
     * 登录方法
     */
    User signin(@Param("usename") String usename,@Param("password") String password);
    /**
     * 注册方法
     */
    void register(User user);
    /**
     * 查看该id和用户名是否被应用
     */

    int chakan2(@Param("username") String username);
    int chakan3(@Param("username") String username,@Param("password") String password);
    /**
     * 处理money
     */
    void changemoney(@Param("username") String username,@Param("money") double money);
    double getmoney(@Param("username") String username);
}
