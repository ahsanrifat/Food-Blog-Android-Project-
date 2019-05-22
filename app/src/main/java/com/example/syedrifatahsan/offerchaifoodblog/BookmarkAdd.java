package com.example.syedrifatahsan.offerchaifoodblog;

public class BookmarkAdd {
    String key, addKey, userKey;

    public BookmarkAdd(String key, String addKey, String userKey) {
        this.key = key;
        this.addKey = addKey;
        this.userKey = userKey;
    }

    public BookmarkAdd() {
    }

    public String getKey() {
        return key;
    }

    public String getAddKey() {
        return addKey;
    }

    public String getUserKey() {
        return userKey;
    }
}
