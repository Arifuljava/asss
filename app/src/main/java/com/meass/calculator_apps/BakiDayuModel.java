package com.meass.calculator_apps;

public class BakiDayuModel {
    String email, date,amount,time;

    public BakiDayuModel() {
    }

    public BakiDayuModel(String email, String date, String amount, String time) {
        this.email = email;
        this.date = date;
        this.amount = amount;
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
