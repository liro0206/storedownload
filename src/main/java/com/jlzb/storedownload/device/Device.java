package com.jlzb.storedownload.device;

public class Device {

    private String model;
    private String version;
    private String brand;
    private String width;
    private String height;
    private String imei;
    private String androidcode;

    public Device(String model, String version, String brand, String width, String height) {
        this.model = model;
        this.version = version;
        this.brand = brand;
        this.width = width;
        this.height = height;
        this.imei = DeviceRandom.randomImei();
        this.androidcode = DeviceRandom.randomAndroidCode();
    }

    public String getAndroidcode() {
        return androidcode;
    }

    public void setAndroidcode(String androidcode) {
        this.androidcode = androidcode;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Override
    public String toString() {
        return "["+model+"]," + "["+brand+"],"+ "["+width+"],"+ "["+height+"]";
    }
}
