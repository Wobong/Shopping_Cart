package com.cheer.shoppingcart.dao;

import com.cheer.shoppingcart.model.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CommodityMapper {
    /**
     * 根据商品名取商品
     */
    Commodity getCommodity(String cname);
    /**
     * 上架商品
     */
    void addcommodity(Commodity commodity);
    /**
     * 根据商品名下架商品
     */
    void deletecommodity(String cname);

    /**
     * 修改商品数量
     */
    void addcount(@Param("cname") String cname,@Param("count") Integer count);
    /**
     * 修改价格
     */
    void changeprice(@Param("cname") String cname,@Param("price") double price);
    /**
     * 查看所有商品
     */
    List<Map<String,Commodity>> getall();
    /**
     * 根据商品名查看是否有该商品
     */
    int chakan(@Param("cname") String cname);
    /**
     * 根据商品名查询数量
     */
    int chakan2(@Param("cname") String cname);
}
