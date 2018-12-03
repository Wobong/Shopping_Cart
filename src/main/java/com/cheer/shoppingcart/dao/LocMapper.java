package com.cheer.shoppingcart.dao;

import com.cheer.shoppingcart.model.Loc;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LocMapper {
    /**
     * 根据用户id和地址id查询收货地址
     */
    Loc getloc(@Param("tablename") String tablename,@Param("id") Integer locid);
    /**
     * 新增收货地址
     */
    void addloc(@Param("tablename") String tablename,@Param("loc") Loc loc);
    /**
     * 删除已有收货地址
     */
    void deleteloc(@Param("tablename") String tablename,@Param("locid") Integer locid);
    /**
     * 查询该地址id是否已被使用
     */
    int idquery(@Param("tablename") String tablename,@Param("id") Integer id);
    /**
     * 创建地址表
     */
    int createloc(@Param("tablename") String tablename);
    List<Map<String,Loc>> getall(@Param("tablename") String tablename);
}
