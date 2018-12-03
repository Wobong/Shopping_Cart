package com.cheer.shoppingcart.model;

public class Commodity {
    private String cname;
    private double price;
    private String state;
    private Integer num;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }
    public void setState() {
        if(this.num>0){
            this.state="有货";
        }else {
            this.state="没货";
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Commodity{");
        sb.append("cname='").append(cname).append('\'');
        sb.append(", price=").append(price);
        sb.append(", state='").append(state).append('\'');
        sb.append(", num=").append(num);
        sb.append('}');
        return sb.toString();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
