package com.example.test;

public class CarClass {
    private String nameCar;
    private String descriptionCar;
    private String priceCar;
    private String foto;
    private String key;

    public CarClass(String nameCar, String descriptionCar, String priceCar, String foto, String key) {
        this.nameCar = nameCar;
        this.descriptionCar = descriptionCar;
        this.priceCar = priceCar;
        this.foto = foto;
        this.key = key;
    }

    public CarClass() {
    }

    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }

    public String getDescriptionCar() {
        return descriptionCar;
    }

    public void setDescriptionCar(String descriptionCar) {
        this.descriptionCar = descriptionCar;
    }

    public String getPriceCar() {
        return priceCar;
    }

    public void setPriceCar(String priceCar) {
        this.priceCar = priceCar;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}