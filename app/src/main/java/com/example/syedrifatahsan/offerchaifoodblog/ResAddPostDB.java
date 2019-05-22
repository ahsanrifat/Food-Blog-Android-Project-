package com.example.syedrifatahsan.offerchaifoodblog;

public class ResAddPostDB {

    String rName;
    String texts;
    String title;
    String price;
    String imageUrl;
    String key;
    String resKey;

    public ResAddPostDB() {
    }

    public ResAddPostDB(String rName, String texts, String title, String price,String imageUrl,String key,String resKey) {
        this.rName = rName;

        this.texts = texts;
        this.title = title;
        this.price = price;
        this.imageUrl=imageUrl;
        this.key=key;
        this.resKey=resKey;
    }

    public String getrName() {
        return rName;
    }

    public String getTexts() {
        return texts;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getImageUrl() { return imageUrl; }

    public String getKey() {
        return key;
    }
}
