package com.meass.calculator_apps;

public class MemoNoteModel {
    String companyname,productsname,price,pices,details,expered,date,time,email,uuid;

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
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

    public String getPices() {
        return pices;
    }

    public void setPices(String pices) {
        this.pices = pices;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getExpered() {
        return expered;
    }

    public void setExpered(String expered) {
        this.expered = expered;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public MemoNoteModel() {
    }

    public MemoNoteModel(String companyname, String productsname, String price,
                         String pices, String details, String expered, String date, String time, String email, String uuid) {
        this.companyname = companyname;
        this.productsname = productsname;
        this.price = price;
        this.pices = pices;
        this.details = details;
        this.expered = expered;
        this.date = date;
        this.time = time;
        this.email = email;
        this.uuid = uuid;
    }
}
