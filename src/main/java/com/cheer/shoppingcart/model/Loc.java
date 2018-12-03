package com.cheer.shoppingcart.model;

public class Loc {

    private Integer locid;
    private String phone;
    private String location;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Loc{");
        sb.append("locid=").append(locid);
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", location='").append(location).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Integer getLocid() {
        return locid;
    }

    public void setLocid(Integer locid) {
        this.locid = locid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
