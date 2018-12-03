package com.cheer.shoppingcart.util;

public class Tableutil {
    public String cart(String username){
        StringBuffer sb=new StringBuffer(username);
        sb.append("cart");
        return sb.toString();
    }
    public String loctable(String username){
        StringBuffer sb=new StringBuffer(username);
        sb.append("loc");
        return  sb.toString();
    }
    public String recordtable(String username){
        StringBuffer sb=new StringBuffer(username);
        sb.append("record");
        return sb.toString();
    }
}
