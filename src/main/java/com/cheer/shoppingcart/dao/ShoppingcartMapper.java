package com.cheer.shoppingcart.dao;

import com.cheer.shoppingcart.model.Shoppingcart;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ShoppingcartMapper {
    /**
     * 添加商品到购物车
     */
    void addlist(@Param("tablename")String tablename, @Param("shoppingcart") Shoppingcart shoppingcart);
    /**
     * 根据userid和商品名称删除购物车内商品
     */
    void deleteshoppingcart(@Param("tablename") String tablename,@Param("cname") String cname);
    /**
     * 根据userid删除所有购物车内商品
     */
    void deleteall(@Param("tablename") String tablename);
    /**
     * 根据userid和商品名修改购物车内商品数量
     */
    void changecount(@Param("tablename") String tablename,@Param("cname") String cname,@Param("count") Integer count);
    /**
     * 根据userid查看购物车
     */
    List<Map<String,Shoppingcart>> getall(@Param("tablename") String tablename);
    /**
     * 根据商品名查询该user购物车内是否有该商品
     */
    int cnamequery(@Param("tablename") String tablename,@Param("cname") String cname);
    /**
     * 根据商品名取出该user购物车内商品
     */
    Shoppingcart getcart(@Param("tablename") String tablename,@Param("cname") String cname);
    /**
     * 创建购物车表
     */
    void createcart(@Param("tablename") String tablename);
    /**
     * 查询购物车内该商品数量
     */
    int getcount(@Param("tablename") String tablename,@Param("cname") String cname);
    int getcount1(@Param("tablename") String tablename);
}
