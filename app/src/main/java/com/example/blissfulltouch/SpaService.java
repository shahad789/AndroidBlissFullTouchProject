package com.example.blissfulltouch;

public class SpaService {

    private String serviceName;
    private String description;
    private double price;

    public SpaService(String serviceName, String description, double price) {
        this.serviceName = serviceName;
        this.description = description;
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SpaService{" +
                "serviceName='" + serviceName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

}
