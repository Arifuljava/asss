package com.meass.calculator_apps;

public class ContactModel {
    String name,number,details,email,time,uuid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public ContactModel(String name, String number, String details, String email, String time, String uuid) {
        this.name = name;
        this.number = number;
        this.details = details;
        this.email = email;
        this.time = time;
        this.uuid = uuid;
    }

    public ContactModel() {
    }
}
