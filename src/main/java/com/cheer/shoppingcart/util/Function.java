package com.cheer.shoppingcart.util;

import com.cheer.shoppingcart.model.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Function {
    Scanner sc=new Scanner(System.in);
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Tableutil tableutil= new Tableutil();
     //注册方法
    public void register(){
        MapperUtil mapperUtil=new MapperUtil();
        mapperUtil.before();
        System.out.println("************注册账号***************");

        User user=new User();

        System.out.println("请输入用户名");
        String username=sc.next();
        while(0!=mapperUtil.userMapper.chakan2(username)){
            System.out.println("该用户名已被使用，请重新输入");
            username=sc.next();
        }
        user.setUsername(username);
        System.out.println("请输入密码");
        user.setPassword(sc.next());
        mapperUtil.userMapper.register(user);
        String tablename=tableutil.loctable(username);
        System.out.println(tablename);
        mapperUtil.locMapper.createloc(tablename);
        mapperUtil.shoppingcartMapper.createcart(tableutil.cart(username));
        mapperUtil.recordMapper.createrecord(tableutil.recordtable(username));
        mapperUtil.after();
        System.out.println("注册成功");
    }
    //登录方法
    public User sigin(){
        MapperUtil mapperUtil=new MapperUtil();
        mapperUtil.before();
        System.out.println("------------登录-----------");
        User user=new User();
        System.out.print("用户名:");
        String usename=sc.next();
        System.out.println();
        System.out.print("密码:");
        String password=sc.next();
        System.out.println();
        while (1!=mapperUtil.userMapper.chakan3(usename,password)){
            System.out.println("账号或密码错误！");
            System.out.print("用户名:");
            usename=sc.next();
            System.out.println();
            System.out.print("密码:");
            password=sc.next();
            System.out.println();
        }
        user=mapperUtil.userMapper.signin(usename,password);
        mapperUtil.after();
        System.out.println("登陆成功");
        return user;
    }
    public List<Map<String,Commodity>> getall(){
        MapperUtil mapperUtil=new MapperUtil();
        mapperUtil.before();
       /* SqlSession ss= null;
        try {
            ss = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Map<String,Object>> mapList=ss.selectList("");*/
       List<Map<String,Commodity>> hashMap=new ArrayList<>();
       hashMap=mapperUtil.commodityMapper.getall();
       mapperUtil.after();
        return hashMap;
    }
    //添加商品到购物车方法
    public void addshoppingcartlist(User user){
        MapperUtil mapperUtil=new MapperUtil();
        mapperUtil.before();
        System.out.print("输入商品名:");
        String cname=sc.next();
        while (1!=mapperUtil.commodityMapper.chakan(cname)){
            System.out.println("该商品不存在，请重新输入");
            cname=sc.next();
        }
        if(1==mapperUtil.shoppingcartMapper.cnamequery(tableutil.cart(user.getUsername()),cname)){
            System.out.println("购物车内已有该商品，虽然你能继续添加数量，但是我任性，不让你继续添加");
            mapperUtil.after();
            userhome(user);
           /* if(sc.next().equals("否")){

            }
            System.out.print("添加数量:");
            int now=sc.nextInt();
            int cart=mapperUtil.shoppingcartMapper.getcount(tableutil.cart(user.getUsername()),cname);
            int commo=mapperUtil.commodityMapper.chakan2(cname);
            while(now+cart>commo){
                System.out.println("你购物车内已有"+cart+"件商城共有"+commo+"件，不够呀，换个数或者回去吧");
                System.out.println("回去输入（是）");
                if(sc.next().equals("s是")){
                    System.out.println("拜拜");
                    mapperUtil.after();
                    userhome(user);
                }
                System.out.print("输入数量:");
                now=sc.nextInt();
            }
            now=now+cart;*/

        }
        Commodity commodity =mapperUtil.commodityMapper.getCommodity(cname);
        Shoppingcart shoppingcart=new Shoppingcart();
        shoppingcart.setCname(commodity.getCname());
        System.out.print("添加数量:");
        int i=sc.nextInt();
        while (i>mapperUtil.commodityMapper.chakan2(cname)){
            System.out.println("错误！该商品没有"+i+"件");
            System.out.print("重新输入:");
            i=sc.nextInt();
            System.out.println();
        }
        shoppingcart.setNum(i);
        shoppingcart.setPrice(commodity.getPrice());
        shoppingcart.setTprice();
        mapperUtil.shoppingcartMapper.addlist(tableutil.cart(user.getUsername()),shoppingcart);
        mapperUtil.after();
    }
    //添加收货地址方法
    public void addloc(User user){
        MapperUtil mapperUtil=new MapperUtil();
        mapperUtil.before();
        Loc loc=new Loc();
        int locid;
        System.out.print("请输入id:");
        locid=sc.nextInt();
        System.out.println();
        while(0!=mapperUtil.locMapper.idquery(tableutil.loctable(user.getUsername()),locid)){
            System.out.print("该id已被使用，请重新输入:");
            locid=sc.nextInt();
        }
        loc.setLocid(locid);
        System.out.print("请输入收货地址:");
        loc.setLocation(sc.next());
        System.out.println();
        System.out.print("请输入手机号码:");
        loc.setPhone(sc.next());
        mapperUtil.locMapper.addloc(tableutil.loctable(user.getUsername()),loc);
        mapperUtil.after();
        System.out.println("添加地址成功");
    }


    public  String buy(User user){
        String ddd=null;
        MapperUtil mapperUtil=new MapperUtil();
        mapperUtil.before();
        System.out.print("请输入要添加到订单的商品名:");
        String cname=sc.next();
        while (1!=mapperUtil.shoppingcartMapper.cnamequery(tableutil.cart(user.getUsername()),cname)){
            System.out.print("你的购物车内没有该商品，请重新输入:");
            cname=sc.next();
            System.out.println();
        }
        System.out.println("正在生成订单");
        Shoppingcart shoppingcart=mapperUtil.shoppingcartMapper.getcart(tableutil.cart(user.getUsername()),cname);
        if(user.getMoney()<shoppingcart.getTprice()){
            ddd="否";
            System.out.println("你的钱不够，滚吧");
            mapperUtil.after();
            return ddd;
        }
        System.out.println(shoppingcart);
        System.out.println("是否确认购买y/n:");
        String star=sc.next();
        if(star.equals("y")){
            double money=user.getMoney()-shoppingcart.getTprice();
            int count=shoppingcart.getNum();
            count=mapperUtil.commodityMapper.chakan2(shoppingcart.getCname())-count;
            mapperUtil.commodityMapper.addcount(shoppingcart.getCname(),count);
            mapperUtil.userMapper.changemoney(user.getUsername(),money);
            mapperUtil.shoppingcartMapper.deleteshoppingcart(tableutil.cart(user.getUsername()),cname);
            Record record=new Record();
            record.setOrderdate(df.format(new Date()));
            record.setCname(shoppingcart.getCname());
            record.setNum(shoppingcart.getNum());
            record.setPrice(shoppingcart.getPrice());
            record.setTprice(shoppingcart.getTprice());
            mapperUtil.recordMapper.addrecord(tableutil.recordtable(user.getUsername()),record);
            mapperUtil.after();
            System.out.println("付款成功");
        }else {
            System.out.println("已取消订单");
            mapperUtil.after();
        }
        System.out.println("是否继续购买其他购物车内商品？（是/否）");
        ddd=sc.next();
        return  ddd;
    }

    //给购物车内商品付款方法
    public void buygoog(User user){
        MapperUtil mapperUtil=new MapperUtil();
        mapperUtil.before();
        if(0==mapperUtil.shoppingcartMapper.getcount1(tableutil.cart(user.getUsername()))){
            mapperUtil.after();
            System.out.println("购物车内没有商品，自动返回上一级菜单");
            userhome(user);
        }
        System.out.println("打开购物车");
        List<Map<String,Shoppingcart>> mapList=mapperUtil.shoppingcartMapper.getall(tableutil.cart(user.getUsername()));
        for (Map<String,Shoppingcart> map: mapList) {
            for (Map.Entry<String,Shoppingcart> entry: map.entrySet()) {
                System.out.printf("  %s,  %s ",entry.getKey(),entry.getValue() );
            }
            System.out.println();
        }
        System.out.println();
        System.out.print("要购买购物车内商品吗？（是/否）");
        String ddd=sc.next();
        while (ddd.equals("是")){
           ddd=buy(user);
        }
        mapperUtil.after();
        System.out.println("返回上一级菜单");
    }


    //收货地址方法
    public void loc(User user){

        int i;
        System.out.println("********收货地址管理系统**************\n\t1.添加收货地址\n\t2.删除收货地址\n\t3.查看所有地址\n\t4.返回上一级菜单");
        i=sc.nextInt();
        switch (i){
            case 1:
                addloc(user);
                System.out.println("自动返回上一菜单");
                loc(user);
                break;
            case 2:
                MapperUtil mapperUtil=new MapperUtil();
                mapperUtil.before();
                System.out.print("请输入要删除的地址id:");
                int locname=sc.nextInt();
               mapperUtil.locMapper.deleteloc(tableutil.loctable(user.getUsername()),locname);
               mapperUtil.after();
                System.out.println("删除成功，自动返回上一菜单");
                loc(user);
                break;
            case 3:
                MapperUtil mapperUtil1=new MapperUtil();
                mapperUtil1.before();
                List<Map<String,Loc>> mapList=mapperUtil1.locMapper.getall(tableutil.loctable(user.getUsername()));
                for (Map<String,Loc> map: mapList) {
                    for (Map.Entry<String,Loc> entry: map.entrySet()) {
                        System.out.printf("%s,  %s",entry.getKey(),entry.getValue() );
                    }
                    System.out.println();
                }
                mapperUtil1.after();
                System.out.println("查询成功，自动返回上一级菜单");
                loc(user);
                break;
             default:
                 System.out.println("返回上一级菜单");
                 break;
        }

    }

    //主界面
    public void shoppinghome(){
        System.out.println("欢迎光临大意是搞丢阿虎电话横跨回城购物车系统");
        System.out.println("\t1.注册账号\n\t2.登录\n\t3.退出系统");
        int i=sc.nextInt();
        switch (i){
            case 1:
                register();
                System.out.println("自动返回上一级菜单");
                shoppinghome();
                break;
            case 2:
                User user=sigin();
                userhome(user);
                shoppinghome();
                break;
            default:
                System.out.println("拜拜");
                break;
        }
    }


    //消费记录系统
    public void record(User user){
        MapperUtil mapperUtil=new MapperUtil();
        mapperUtil.before();
        System.out.println("消费记录系统");
        System.out.println("\t\n1.查看消费记录\n\t2.删除全部消费记录\n\t3.删除单条消费记录\n\t4.返回上一级菜单");
        int i=sc.nextInt();
        switch (i){
            case 1:
                List<Map<String,Record>> mapList=mapperUtil.recordMapper.getall(tableutil.recordtable(user.getUsername()));
                for (Map<String,Record> map: mapList) {
                    for (Map.Entry<String,Record> entry: map.entrySet()) {
                        System.out.printf("%s,  %s",entry.getKey(),entry.getValue() );
                    }
                    System.out.println();
                }
                mapperUtil.after();
                System.out.println("返回上一级菜单");
                record(user);
                break;
            case 2:
                mapperUtil.recordMapper.deleteall(tableutil.recordtable(user.getUsername()));
                mapperUtil.after();
                System.out.println("返回上一级菜单");
                record(user);
                break;
            case 3:
                System.out.print("请输入要删除记录的商品名:");
                String cname=sc.next();
                while (0!=mapperUtil.recordMapper.cnamequery(tableutil.recordtable(user.getUsername()),cname)){
                    System.out.print("输入错误，记录中没有该商品，重新输入请按（y），返回上一级菜单请按（n）");
                    if(sc.next().equals("n")){
                        record(user);
                        mapperUtil.after();
                        System.out.println("返回上一级菜单");
                    }
                    cname=sc.next();
                }
                mapperUtil.recordMapper.deleterecord(tableutil.recordtable(user.getUsername()),cname);
                mapperUtil.after();
                System.out.println("删除成功，返回上一级菜单");
                record(user);
                break;
            default:
                mapperUtil.after();
                System.out.println("返回上一级菜单");
                userhome(user);
        }
    }


    //充值
    public void rech(User user){
        MapperUtil mapperUtil=new MapperUtil();
        mapperUtil.before();
        System.out.print("请输入充值金额:");
        double money=sc.nextDouble();
        money=money+mapperUtil.userMapper.getmoney(user.getUsername());
        mapperUtil.userMapper.changemoney(user.getUsername(),money);
        mapperUtil.after();
        System.out.println("充值成功，自动返回上一级菜单");
        userhome(user);
    }


    //用户主界面
    public void userhome(User user){

        System.out.println("gap骄傲水暖红碎茶那ui荟萃啊是悲催购物车主菜单");
        System.out.println("\t1.进入商城\n\t2.查看购物车\n\t3.收货地址\n\t4.消费记录\n\t5.充值\n\t或按任意数字键返回主菜单");
        switch (sc.nextInt()){
            case 1:
                MapperUtil mapperUtil=new MapperUtil();
                mapperUtil.before();
                List<Map<String,Commodity>> mapList=getall();
                for (Map<String,Commodity> map: mapList) {
                    for (Map.Entry<String,Commodity> entry: map.entrySet()) {
                        System.out.printf("%s,  %s",entry.getKey(),entry.getValue() );
                    }
                    System.out.println();
                }
                System.out.println();
                System.out.print("是否添加商品到购物车(是/否):");
                String ddd=sc.next();
                while (ddd.equals("是")){
                    addshoppingcartlist(user);
                    System.out.print("是否继续添加其它商品(是/否):");
                    ddd=sc.next();
                }
                mapperUtil.after();
                System.out.println("返回上一级菜单");
                userhome(user);
                break;
            case 2:
                buygoog(user);
                userhome(user);
                break;
            case 3:
                loc(user);
                userhome(user);
                break;
            case 4:
                record(user);
                break;
            case 5:
                rech(user);
                break;
            default:
                System.out.println("返回上一级菜单");
                shoppinghome();
        }
    }


}
