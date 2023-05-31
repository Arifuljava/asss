package com.meass.calculator_apps;

public class BakiMainModel {
    String c_name,c_number,joma,p_name,p_quan,p_price,mesaage,totalproducts,totalamount,bakidibe,date,time,uuid,email;

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_number() {
        return c_number;
    }

    public void setC_number(String c_number) {
        this.c_number = c_number;
    }

    public String getJoma() {
        return joma;
    }

    public void setJoma(String joma) {
        this.joma = joma;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_quan() {
        return p_quan;
    }

    public void setP_quan(String p_quan) {
        this.p_quan = p_quan;
    }

    public String getP_price() {
        return p_price;
    }

    public void setP_price(String p_price) {
        this.p_price = p_price;
    }

    public String getMesaage() {
        return mesaage;
    }

    public void setMesaage(String mesaage) {
        this.mesaage = mesaage;
    }

    public String getTotalproducts() {
        return totalproducts;
    }

    public void setTotalproducts(String totalproducts) {
        this.totalproducts = totalproducts;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }

    public String getBakidibe() {
        return bakidibe;
    }

    public void setBakidibe(String bakidibe) {
        this.bakidibe = bakidibe;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BakiMainModel(String c_name, String c_number, String joma, String p_name, String p_quan, String p_price, String mesaage, String totalproducts,
                         String totalamount, String bakidibe, String date, String time, String uuid, String email) {
        this.c_name = c_name;
        this.c_number = c_number;
        this.joma = joma;
        this.p_name = p_name;
        this.p_quan = p_quan;
        this.p_price = p_price;
        this.mesaage = mesaage;
        this.totalproducts = totalproducts;
        this.totalamount = totalamount;
        this.bakidibe = bakidibe;
        this.date = date;
        this.time = time;
        this.uuid = uuid;
        this.email = email;
    }

    public BakiMainModel() {
    }
}
