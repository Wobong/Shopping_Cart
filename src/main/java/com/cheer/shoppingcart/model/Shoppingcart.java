package com.cheer.shoppingcart.model;

import java.util.List;

public class Shoppingcart {

    private String cname;
    private double price;
    private Integer num;
    private double tprice;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Shoppingcart{");
        sb.append("cname='").append(cname).append('\'');
        sb.append(", price=").append(price);
        sb.append(", num=").append(num);
        sb.append(", tprice=").append(tprice);
        sb.append('}');
        return sb.toString();
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice( double price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public double getTprice() {
        return tprice;
    }

    public void setTprice() {
        this.tprice = this.price*this.num;
    }
}
