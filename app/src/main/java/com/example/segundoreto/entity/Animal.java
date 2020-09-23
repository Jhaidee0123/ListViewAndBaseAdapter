package com.example.segundoreto.entity;

public class Animal {
    private int image;
    private String name;
    private String description;
    private int sound;

    public Animal(int image, String name, String description, int sound) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.sound = sound;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getSound() {
        return sound;
    }
}
