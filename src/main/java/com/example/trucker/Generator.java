package com.example.trucker;

public class Generator implements Comparable<Generator>{

    private static int nextId = 1;

    private int id;
    private String orderId;
    private String orderProduct;
    private String orderUnits;
    private String orderQuantity;
    private String orderContact;
    private String orderPhone;
    private String orderDelAddress;
    private String orderCity;
    private String orderCountry;
    private String orderGoogleN;
    private String orderGoogleE;

    private String driver;
    private String truck;
    private String trailer;

    private String generatedDate;
    private String startedDeliveryDate;
    private String deliveredDate;
    private String acceptedDate;
    private String finishedDate;
    private String status;

    public Generator(int id,String orderId, String orderProduct, String orderUnits, String orderQuantity, String orderContact, String orderPhone, String orderDelAddress, String orderCity, String orderCountry, String orderGoogleN, String orderGoogleE, String driver, String truck, String trailer, String generatedDate, String startedDeliveryDate, String deliveredDate, String acceptedDate, String finishedDate, String status) {
        this.id = id;
        if (this.id >= nextId) {
            nextId = this.id + 1;
        }
        this.orderId = orderId;
        this.orderProduct = orderProduct;
        this.orderUnits = orderUnits;
        this.orderQuantity = orderQuantity;
        this.orderContact = orderContact;
        this.orderPhone = orderPhone;
        this.orderDelAddress = orderDelAddress;
        this.orderCity = orderCity;
        this.orderCountry = orderCountry;
        this.orderGoogleN = orderGoogleN;
        this.orderGoogleE = orderGoogleE;
        this.driver = driver;
        this.truck = truck;
        this.trailer = trailer;
        this.generatedDate = generatedDate;
        this.startedDeliveryDate = startedDeliveryDate;
        this.deliveredDate = deliveredDate;
        this.acceptedDate = acceptedDate;
        this.finishedDate = finishedDate;
        this.status = status;
    }

    public Generator(String orderId, String orderProduct, String orderUnits, String orderQuantity, String orderContact, String orderPhone, String orderDelAddress, String orderCity, String orderCountry, String orderGoogleN, String orderGoogleE, String driver, String truck, String trailer, String generatedDate, String startedDeliveryDate, String deliveredDate, String acceptedDate, String finishedDate, String status) {
        this.id = nextId++;
        this.orderId = orderId;
        this.orderProduct = orderProduct;
        this.orderUnits = orderUnits;
        this.orderQuantity = orderQuantity;
        this.orderContact = orderContact;
        this.orderPhone = orderPhone;
        this.orderDelAddress = orderDelAddress;
        this.orderCity = orderCity;
        this.orderCountry = orderCountry;
        this.orderGoogleN = orderGoogleN;
        this.orderGoogleE = orderGoogleE;
        this.driver = driver;
        this.truck = truck;
        this.trailer = trailer;
        this.generatedDate = generatedDate;
        this.startedDeliveryDate = startedDeliveryDate;
        this.deliveredDate = deliveredDate;
        this.acceptedDate = acceptedDate;
        this.finishedDate = finishedDate;
        this.status = status;
    }

    public String getOrderId() {return orderId;}

    public void setOrderId(String orderId) {this.orderId = orderId; }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Generator.nextId = nextId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(String orderProduct) {
        this.orderProduct = orderProduct;
    }

    public String getOrderUnits() {
        return orderUnits;
    }

    public void setOrderUnits(String orderUnits) {
        this.orderUnits = orderUnits;
    }

    public String getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(String orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getOrderContact() {
        return orderContact;
    }

    public void setOrderContact(String orderContact) {
        this.orderContact = orderContact;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    public String getOrderDelAddress() {
        return orderDelAddress;
    }

    public void setOrderDelAddress(String orderDelAddress) {
        this.orderDelAddress = orderDelAddress;
    }

    public String getOrderCity() {
        return orderCity;
    }

    public void setOrderCity(String orderCity) {
        this.orderCity = orderCity;
    }

    public String getOrderCountry() {
        return orderCountry;
    }

    public void setOrderCountry(String orderCountry) {
        this.orderCountry = orderCountry;
    }

    public String getOrderGoogleN() {
        return orderGoogleN;
    }

    public void setOrderGoogleN(String orderGoogleN) {
        this.orderGoogleN = orderGoogleN;
    }

    public String getOrderGoogleE() {
        return orderGoogleE;
    }

    public void setOrderGoogleE(String orderGoogleE) {
        this.orderGoogleE = orderGoogleE;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getTruck() {
        return truck;
    }

    public void setTruck(String truck) {
        this.truck = truck;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(String generatedDate) {
        this.generatedDate = generatedDate;
    }

    public String getStartedDeliveryDate() {
        return startedDeliveryDate;
    }

    public void setStartedDeliveryDate(String startedDeliveryDate) {
        this.startedDeliveryDate = startedDeliveryDate;
    }

    public String getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(String deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public String getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(String acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    public String getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(String finishedDate) {
        this.finishedDate = finishedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Generator{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", orderProduct='" + orderProduct + '\'' +
                ", orderUnits='" + orderUnits + '\'' +
                ", orderQuantity='" + orderQuantity + '\'' +
                ", orderContact='" + orderContact + '\'' +
                ", orderPhone='" + orderPhone + '\'' +
                ", orderDelAddress='" + orderDelAddress + '\'' +
                ", orderCity='" + orderCity + '\'' +
                ", orderCountry='" + orderCountry + '\'' +
                ", orderGoogleN='" + orderGoogleN + '\'' +
                ", orderGoogleE='" + orderGoogleE + '\'' +
                ", driver='" + driver + '\'' +
                ", truck='" + truck + '\'' +
                ", trailer='" + trailer + '\'' +
                ", generatedDate='" + generatedDate + '\'' +
                ", startedDeliveryDate='" + startedDeliveryDate + '\'' +
                ", deliveredDate='" + deliveredDate + '\'' +
                ", acceptedDate='" + acceptedDate + '\'' +
                ", finishedDate='" + finishedDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public int compareTo(Generator o) {
        return 0;
    }
}
