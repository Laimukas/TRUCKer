package com.example.trucker;

import com.example.trucker.exceptions.TRUCKerListError;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class OrderList {
    private static final String ORDERS_FILE_PATH ="src/main/resources/orders.csv";

    private ArrayList<Order> orders;

    private ArrayList<String> ordersFullInfo;

    private ArrayList<String> ordersShortInfo;

    public OrderList() {
        orders = new ArrayList<>();
    }

    public  ArrayList<Order> getListOfOrders(){
        return orders;
    }

    public ArrayList<String> ListOfOrdersFullInfo() throws TRUCKerListError {
        ordersFullInfo = new ArrayList<>();
        getOrders();
        for (int i =0;i< orders.size();i++){
            ordersFullInfo.add(i, orders.get(i).getId() + "," + orders.get(i).getProduct() + "," +
                    orders.get(i).getUnits()+","+orders.get(i).getQuantity()+","+orders.get(i).getContact()+","+
                    orders.get(i).getPhone()+","+orders.get(i).getDeliveryAddress()+","+orders.get(i).getCity()+","+
                    orders.get(i).getCountry()+","+orders.get(i).getGooglN()+","+orders.get(i).getGooglE());
            System.out.println(ordersFullInfo.get(i));
        }
        return ordersFullInfo;
    }

    public ArrayList<String> ListOfOrdersShortInfo() throws TRUCKerListError {
        ordersShortInfo = new ArrayList<>();
        getOrders();
        for (int i = 0; i < orders.size(); i++) {
            ordersShortInfo.add(i, orders.get(i).getId() + "," + orders.get(i).getProduct() + "," +
                    orders.get(i).getUnits()+","+orders.get(i).getQuantity()+","+orders.get(i).getCompany());
            System.out.println(ordersShortInfo.get(i));
        }
        return ordersShortInfo;
    }

    public Order getOrderById(String Id) throws TRUCKerListError {
        Order orderWeNeed = null;
        getOrders();
        if (Id != null && Id.equals("")) {
            throw new TRUCKerListError("Order doesn't excist!");
        }
        System.out.println("We check the egsisting order to mach.");
        for (Order order : orders) {
            if (order.getId().equals(Id)) {
                orderWeNeed = order;
            }
        }
        System.out.println(orderWeNeed);
        return orderWeNeed;
    }

    //
    public void setOrder(String newId, String newProduct, String newUnits, String newQuantity, String newCompany, String newCompanyID,
                         String newEmail, String newDeliveryAddress, String newCity, String newState,String newZip, String newCountry, String newGooglN,
                         String newGooglE, String newContact, String newPhone, String newOther) throws TRUCKerListError {
        getOrders();
        for (Order order : orders) {
            if (order.getId().equals(newId) && order.getProduct().equals(newProduct) && order.getUnits().equals(newUnits) &&
                    order.getQuantity().equals(newQuantity) && order.getCompanyId().equals(newCompanyID) &&
                    order.getDeliveryAddress().equals(newDeliveryAddress)){
                removeFromList(order);
                Order newOrder=new Order(newId,newProduct,newUnits,newQuantity,newCompany,newCompanyID,
                        newEmail,newDeliveryAddress,newCity,newState,newZip,newCountry,newGooglN,
                        newGooglE,newContact,newPhone,newOther);
                addOrderToList(newOrder);
            }
        }
        reloadFile();
        getOrders();
    }

    public void sorting() throws TRUCKerListError {
        getOrders();
        System.out.println("Sorting Users by ID.");
        Collections.sort(orders, (Comparator.comparing(Order::getId)));
        orders.forEach(order -> System.out.println(order));
    }

    public void removeFromList(Order order) throws TRUCKerListError {

        if (order == null) {
            throw new TRUCKerListError("You need to show egsisting order to remove it!");
        }
        getOrders();
        orders.removeIf(o -> o.getProduct().equals(order.getProduct()) && o.getUnits().equals(order.getUnits()) &&
                o.getQuantity().equals(order.getQuantity()) && o.getCompany().equals(order.getCompany()) &&
                o.getCompanyId().equals(order.getCompanyId()) && o.getDeliveryAddress().equals(order.getDeliveryAddress()) &&
                o.getCity().equals(order.getCity()) && o.getCountry().equals(order.getCountry())&& o.getContact().equals(order.getContact()));
        reloadFile();
        getOrders();
        System.out.println("Order was removed.");
    }

    public void addOrderToList(Order order) throws TRUCKerListError {
        if (order == null) {
            throw new TRUCKerListError("Fill all the requiered fields to add order!");
        }
        orders.add(order);
        reloadFile();
        getOrders();
        System.out.println("Order was added.");
    }

    public void reloadFile() throws TRUCKerListError {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(ORDERS_FILE_PATH));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

            for (Order orderFromList : orders) {
                csvPrinter.printRecord(Arrays.asList(orderFromList.getId(), orderFromList.getProduct(), orderFromList.getUnits(),
                        orderFromList.getQuantity(),orderFromList.getCompany(),orderFromList.getCompanyId(),orderFromList.getEmail(),
                        orderFromList.getDeliveryAddress(),orderFromList.getCity(),orderFromList.getState(),orderFromList.getZip(),
                        orderFromList.getCountry(),orderFromList.getGooglN(),orderFromList.getGooglE(),orderFromList.getContact(),
                        orderFromList.getPhone(),orderFromList.getOther()));
                csvPrinter.flush();
            }

            csvPrinter.close();
            writer.close();
        } catch (IOException e) {
            throw new TRUCKerListError("Sorry, we can't save order to file.", e);
        }
    }

    public void printOrders() throws TRUCKerListError {
        getOrders();
        orders.forEach(order -> System.out.println(order));
    }

    public void getOrders() throws TRUCKerListError {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(ORDERS_FILE_PATH));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            readFromFile(csvParser);
            csvParser.close();
            reader.close();
        } catch (IOException | TRUCKerListError e) {
            throw new TRUCKerListError("Sorry, we can't read orders list file!", e);
        }
    }

    public void readFromFile(CSVParser csvParser) throws TRUCKerListError {
        orders = new ArrayList<>();

        for (CSVRecord csvRecord : csvParser) {

            String id = csvRecord.get(0);
            String product = csvRecord.get(1);
            String units = csvRecord.get(2);
            String quantity = csvRecord.get(3);
            String company = csvRecord.get(4);
            String companyID = csvRecord.get(5);
            String email = csvRecord.get(6);
            String deliveryAddress = csvRecord.get(7);
            String city = csvRecord.get(8);
            String state = csvRecord.get(9);
            String zipCode = csvRecord.get(10);
            String country = csvRecord.get(11);
            String googlN = csvRecord.get(12);
            String googlE = csvRecord.get(13);
            String contactName = csvRecord.get(14);
            String phone = csvRecord.get(15);
            String other = csvRecord.get(16);

            orders.add(new Order(id,product,units,quantity,company,companyID,email,deliveryAddress,city,state,zipCode,country,googlN,googlE,contactName,phone,other));
        }
    }
}
