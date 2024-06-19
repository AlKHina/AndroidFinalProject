package com.example.test;

public class CarClass {

    private String key;
    private String ownerKey;
    private String nameCar;
    private String descriptionCar;
    private String priceCar;
    private String numberUser;
    private String photo;
    private String miniDescription;

    public CarClass() {
    }

    public CarClass(String key, String ownerKey, String nameCar, String descriptionCar, String priceCar, String numberUser, String photo, String miniDescription) {
        this.key = key;
        this.ownerKey = ownerKey;
        this.nameCar = nameCar;
        this.descriptionCar = descriptionCar;
        this.priceCar = priceCar;
        this.numberUser = numberUser;
        this.photo = photo;
        this.miniDescription = miniDescription;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOwnerKey() {
        return ownerKey;
    }

    public void setOwnerKey(String ownerKey) {
        this.ownerKey = ownerKey;
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

    public String getNumberUser() {
        return numberUser;
    }

    public void setNumberUser(String numberUser) {
        this.numberUser = numberUser;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMiniDescription() {
        return miniDescription;
    }

    public void setMiniDescription(String miniDescription) {
        this.miniDescription = miniDescription;
    }
}