package com.meass.calculator_apps;

public class CompanyProductsModel {
    String cusname,number,products,cash,qua,baki,emai,time;

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getQua() {
        return qua;
    }

    public void setQua(String qua) {
        this.qua = qua;
    }

    public String getBaki() {
        return baki;
    }

    public void setBaki(String baki) {
        this.baki = baki;
    }

    public String getEmai() {
        return emai;
    }

    public void setEmai(String emai) {
        this.emai = emai;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public CompanyProductsModel(String cusname, String number, String products, String cash, String qua, String baki, String emai, String time) {
        this.cusname = cusname;
        this.number = number;
        this.products = products;
        this.cash = cash;
        this.qua = qua;
        this.baki = baki;
        this.emai = emai;
        this.time = time;
    }

    public CompanyProductsModel() {
    }
}
