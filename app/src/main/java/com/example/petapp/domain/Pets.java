package com.example.petapp.domain;

import com.google.gson.annotations.Expose;

public class Pets {
    @Expose private int id;
    @Expose private String name;
    @Expose private String img;
    @Expose private int bones;

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public int getBones() {
        return bones;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setBones(int bones) {
        this.bones = bones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
