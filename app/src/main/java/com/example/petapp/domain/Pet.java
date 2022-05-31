package com.example.petapp.domain;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Pet {
    @Expose private List<Pets> pets;

    public List<Pets> getPets() {
        return pets;
    }
}
