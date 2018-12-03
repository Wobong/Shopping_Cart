package com.cheer.shoppingcart.util;

import com.cheer.shoppingcart.dao.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MapperUtil {

    InputStream inputStream=null;
    SqlSession sqlSession=null;
    public UserMapper userMapper=null;
    public CommodityMapper commodityMapper=null;
    public ShoppingcartMapper shoppingcartMapper=null;
    public LocMapper locMapper=null;

    public RecordMapper recordMapper=null;
    public void before() {
        try {
            SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
            sqlSession=sqlSessionFactory.openSession();
            userMapper=sqlSession.getMapper(UserMapper.class);
            commodityMapper=sqlSession.getMapper(CommodityMapper.class);
            shoppingcartMapper=sqlSession.getMapper(ShoppingcartMapper.class);
            locMapper=sqlSession.getMapper(LocMapper.class);
            recordMapper=sqlSession.getMapper(RecordMapper.class);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void after(){
        //事务提交
        this.sqlSession.commit();
        this.sqlSession.close();
        if(null!=inputStream){
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
