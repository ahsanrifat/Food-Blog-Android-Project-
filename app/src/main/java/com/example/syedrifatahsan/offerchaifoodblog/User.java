package com.example.syedrifatahsan.offerchaifoodblog;

import android.net.Uri;

public class User {
    String name;
    String email;
    String password;
    String key;
    String image;


    public User(){}

    public User(String name,String email,String password,String key,String image){

        this.email=email;
        this.name=name;
        this.password=password;
        this.key=key;
        this.image=image;

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getKey() {
        return key;
    }

    public String getImage() {
        return image;
    }
}
