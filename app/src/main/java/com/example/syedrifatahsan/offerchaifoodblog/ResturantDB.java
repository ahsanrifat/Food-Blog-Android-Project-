package com.example.syedrifatahsan.offerchaifoodblog;

public class ResturantDB {

    String key;
    String sResName;
    String sResAddress;
    String sResEmail;
    String sOwner;
    String sPass;
    String sCity;
    String sArea;
    String image;

    public ResturantDB(String key,String sResName, String sResAddress, String sResLicense, String sOwner, String sPass, String sCity, String sArea,String image) {
        this.sResName = sResName;
        this.sResAddress = sResAddress;
        this.sResEmail = sResLicense;
        this.sOwner = sOwner;
        this.sPass = sPass;
        this.sCity = sCity;
        this.sArea = sArea;
        this.key=key;
        this.image=image;
    }

    public ResturantDB() {
    }

    public String getsResName() {
        return sResName;
    }

    public String getsResAddress() {
        return sResAddress;
    }

    public String getsResEmail() {
        return sResEmail;
    }

    public String getsOwner() {
        return sOwner;
    }

    public String getsCity() {
        return sCity;
    }

    public String getsArea() {
        return sArea;
    }


}
