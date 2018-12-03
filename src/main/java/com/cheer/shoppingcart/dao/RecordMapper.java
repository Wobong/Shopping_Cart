package com.cheer.shoppingcart.dao;

import com.cheer.shoppingcart.model.Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RecordMapper {
    /**
     * 添加消费记录
     */
    void addrecord(@Param("tablename") String tablename,@Param("record") Record record);
    /**
     * 根据userid和所购商品名删除消费记录
     */
    void deleterecord(@Param("tablename") String tablename,@Param("cname") String cname);
    /**
     * 创建记录表
     */
    List<Map<String,Record>> getall(@Param("tablename") String tablename);
    void createrecord(@Param("tablename") String tablename);
    void deleteall(@Param("tablename") String tablename);
    int cnamequery(@Param("tablename") String tablename,@Param("cname") String cname);
}
