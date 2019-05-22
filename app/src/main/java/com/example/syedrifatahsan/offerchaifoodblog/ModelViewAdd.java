package com.example.syedrifatahsan.offerchaifoodblog;

public class ModelViewAdd {

    String name,title,price,image,key;

    public ModelViewAdd() {
    }

    public ModelViewAdd(String name, String title, String price, String image, String key) {
        this.name = name;
        this.title = title;
        this.price = price;
        this.image = image;
        this.key=key;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getKey() {
        return key;
    }
}
