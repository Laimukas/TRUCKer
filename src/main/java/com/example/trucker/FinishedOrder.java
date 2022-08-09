package com.example.trucker;

public class FinishedOrder implements Comparable<FinishedOrder>{

    private static int nextId = 1;

    private int id;
    private String generatorId;
    private String orderId;
    private String product;
    private String units;
    private String quantity;
    private String company;
    private String companyId;
    private String email;
    private String contact;
    private String phone;

    private String deliveryAddress;
    private String city;
    private String state;
    private String country;
    private String googlN;
    private String googlE;

    private String driver;
    private String truck;
    private String trailer;

    private String generatedDate;
    private String startedDeliveryDate;
    private String deliveredDate;
    private String acceptedDate;
    private String finishedDate;
    private String status;

    private String signedName;
    private String signedPosition;
//    private Image signiture;
    private String note;

    public FinishedOrder(){

    }

    public FinishedOrder(int id, String generatorId, String orderId, String product, String units, String quantity,
                         String company, String companyId, String email, String contact, String phone,
                         String deliveryAddress, String city, String state, String country, String googlN, String googlE,
                         String driver, String truck, String trailer, String generatedDate, String startedDeliveryDate,
                         String deliveredDate, String acceptedDate, String finishedDate, String status, String signedName,
                         String signedPosition,  String note) { //Image signiture, turi but pries String note,
        //reik issiaiskint kaip su juo tvarkytis
        this.id = id;
        if (this.id >= nextId) {
            nextId = this.id + 1;
        }
        this.generatorId = generatorId;
        this.orderId = orderId;
        this.product = product;
        this.units = units;
        this.quantity = quantity;
        this.company = company;
        this.companyId = companyId;
        this.email = email;
        this.contact = contact;
        this.phone = phone;
        this.deliveryAddress = deliveryAddress;
        this.city = city;
        this.state = state;
        this.country = country;
        this.googlN = googlN;
        this.googlE = googlE;
        this.driver = driver;
        this.truck = truck;
        this.trailer = trailer;
        this.generatedDate = generatedDate;
        this.startedDeliveryDate = startedDeliveryDate;
        this.deliveredDate = deliveredDate;
        this.acceptedDate = acceptedDate;
        this.finishedDate = finishedDate;
        this.status = status;
        this.signedName = signedName;
        this.signedPosition = signedPosition;
//        this.signiture = null;
        this.note = note;
    }
    public FinishedOrder(String generatorId, String orderId, String product, String units, String quantity,
                         String company, String companyId, String email, String contact, String phone,
                         String deliveryAddress, String city, String state, String country, String googlN, String googlE,
                         String driver, String truck, String trailer, String generatedDate, String startedDeliveryDate,
                         String deliveredDate, String acceptedDate, String finishedDate, String status, String signedName,
                         String signedPosition, String note) {//Image signiture, turi but pries String note,
        //reik issiaiskint kaip su juo tvarkytis
        this.id = nextId++;
        this.generatorId = generatorId;
        this.orderId = orderId;
        this.product = product;
        this.units = units;
        this.quantity = quantity;
        this.company = company;
        this.companyId = companyId;
        this.email = email;
        this.contact = contact;
        this.phone = phone;
        this.deliveryAddress = deliveryAddress;
        this.city = city;
        this.state = state;
        this.country = country;
        this.googlN = googlN;
        this.googlE = googlE;
        this.driver = driver;
        this.truck = truck;
        this.trailer = trailer;
        this.generatedDate = generatedDate;
        this.startedDeliveryDate = startedDeliveryDate;
        this.deliveredDate = deliveredDate;
        this.acceptedDate = acceptedDate;
        this.finishedDate = finishedDate;
        this.status = status;
        this.signedName = signedName;
        this.signedPosition = signedPosition;
//        this.signiture = null;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGeneratorId() {
        return generatorId;
    }

    public void setGeneratorId(String generatorId) {
        this.generatorId = generatorId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getSignedName() {
        return signedName;
    }

    public void setSignedName(String signedName) {
        this.signedName = signedName;
    }

    public String getSignedPosition() {
        return signedPosition;
    }

    public void setSignedPosition(String signedPosition) {
        this.signedPosition = signedPosition;
    }

//    public Image getSigniture() {
//        return signiture;
//    }
//
//    public void setSigniture(Image signiture) {
//        this.signiture = signiture;
//    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "FinishedOrder{" +
                "id=" + id +
                ", generatorId='" + generatorId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", product='" + product + '\'' +
                ", units='" + units + '\'' +
                ", quantity='" + quantity + '\'' +
                ", company='" + company + '\'' +
                ", companyId='" + companyId + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", phone='" + phone + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", googlN='" + googlN + '\'' +
                ", googlE='" + googlE + '\'' +
                ", driver='" + driver + '\'' +
                ", truck='" + truck + '\'' +
                ", trailer='" + trailer + '\'' +
                ", generatedDate='" + generatedDate + '\'' +
                ", startedDeliveryDate='" + startedDeliveryDate + '\'' +
                ", deliveredDate='" + deliveredDate + '\'' +
                ", acceptedDate='" + acceptedDate + '\'' +
                ", finishedDate='" + finishedDate + '\'' +
                ", status='" + status + '\'' +
                ", signedName='" + signedName + '\'' +
                ", signedPosition='" + signedPosition + '\'' +
//                ", signiture=" + signiture +
                ", note='" + note + '\'' +
                '}';
    }

    @Override
    public int compareTo(FinishedOrder o) {
        return 0;
    }
}