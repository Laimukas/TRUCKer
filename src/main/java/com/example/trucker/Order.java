package com.example.trucker;

public class Order {
    private String id;
    private String product;
    private String units;
    private String quantity;
    private String company;
    private String companyId;
    private String email;
    private String deliveryAddress;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String googlN;
    private String googlE;
    private String contact;
    private String phone;
    private String other;

    public Order(String id, String product, String units, String quantity, String company, String companyId, String email, String deliveryAddress, String city, String state, String zip, String country, String googlN, String googlE, String contact, String phone, String other) {
        this.id = id;
        this.product = product;
        this.units = units;
        this.quantity = quantity;
        this.company = company;
        this.companyId = companyId;
        this.email = email;
        this.deliveryAddress = deliveryAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.googlN = googlN;
        this.googlE = googlE;
        this.contact = contact;
        this.phone = phone;
        this.other = other;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGooglN() {
        return googlN;
    }

    public void setGooglN(String googlN) {
        this.googlN = googlN;
    }

    public String getGooglE() {
        return googlE;
    }

    public void setGooglE(String googlE) {
        this.googlE = googlE;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return String.format("Info about Order-> id: %s, Product: %s, unit: %s, quantity: %s, Company: %s, " +
                "Company ID: %s, Email: %s, Delivery Address: %s, City: %s, State: %s, ZipCode: %s, Country: %s, GooglMapN: %s, " +
                "GooglMapE: %s, Contact Name: %s, Phone: %s, Other: %s",id,product,units,quantity,company,companyId,
                email,deliveryAddress,city,state,zip,country,googlN,googlE,contact,phone,other);

    }
}
