package com.example.chuabaikiemtra.model;

import java.util.Objects;

public class Animal {
    private final int idPhoto;
    private final String name;
    private final int idSound;

    public Animal(int idPhoto,int idSound, String name) {
        this.idPhoto = idPhoto;
        this.idSound = idSound;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public int getIdPhoto() {
        return idPhoto;
    }

    public int getIdSound() {
        return idSound;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return idPhoto == animal.idPhoto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPhoto);
    }
}
