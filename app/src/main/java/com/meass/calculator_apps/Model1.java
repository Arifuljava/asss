package com.meass.calculator_apps;

public class Model1 {
    String productsname,price,time,c_name;

    public Model1() {
    }

    public String getProductsname() {
        return productsname;
    }

    public void setProductsname(String productsname) {
        this.productsname = productsname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public Model1(String productsname, String price, String time, String c_name) {
        this.productsname = productsname;
        this.price = price;
        this.time = time;
        this.c_name = c_name;
    }
}
