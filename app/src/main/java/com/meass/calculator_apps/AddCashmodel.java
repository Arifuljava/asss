package com.meass.calculator_apps;

public class AddCashmodel {
    String cus_name,cnumber,joma,p_name,p_quantity,dam,cashjoma,time,email,kenadam,sell_dam;

    public String getCus_name() {
        return cus_name;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    public String getCnumber() {
        return cnumber;
    }

    public void setCnumber(String cnumber) {
        this.cnumber = cnumber;
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

    public String getP_quantity() {
        return p_quantity;
    }

    public void setP_quantity(String p_quantity) {
        this.p_quantity = p_quantity;
    }

    public String getDam() {
        return dam;
    }

    public void setDam(String dam) {
        this.dam = dam;
    }

    public String getCashjoma() {
        return cashjoma;
    }

    public void setCashjoma(String cashjoma) {
        this.cashjoma = cashjoma;
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

    public String getKenadam() {
        return kenadam;
    }

    public void setKenadam(String kenadam) {
        this.kenadam = kenadam;
    }

    public String getSell_dam() {
        return sell_dam;
    }

    public void setSell_dam(String sell_dam) {
        this.sell_dam = sell_dam;
    }

    public AddCashmodel(String cus_name, String cnumber, String joma, String p_name, String p_quantity, String dam, String cashjoma, String time, String email, String kenadam, String sell_dam) {
        this.cus_name = cus_name;
        this.cnumber = cnumber;
        this.joma = joma;
        this.p_name = p_name;
        this.p_quantity = p_quantity;
        this.dam = dam;
        this.cashjoma = cashjoma;
        this.time = time;
        this.email = email;
        this.kenadam = kenadam;
        this.sell_dam = sell_dam;
    }

    public AddCashmodel() {
    }
}
