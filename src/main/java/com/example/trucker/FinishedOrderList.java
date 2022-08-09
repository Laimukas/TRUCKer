package com.example.trucker;

import com.example.trucker.exceptions.TRUCKerListError;
import javafx.scene.image.Image;
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

public class FinishedOrderList {

    private static final String FINISHED_ORDERS_FILE_PATH = "src/main/resources/finishedOrders.csv";

    private ArrayList<FinishedOrder> finishedOrders;

    public FinishedOrderList() {
        finishedOrders = new ArrayList<>();
    }

    public ArrayList<FinishedOrder> getListOfFinishedOrders() {
        return finishedOrders;
    }

    public ArrayList<FinishedOrder> getListFinishedOrdersWhoMachesDriver(String driver) throws TRUCKerListError {
        ArrayList<FinishedOrder> ordersWeNeed = new ArrayList();
        getListOfFinishedOrders();
//        printFinishedOrders();
        for (FinishedOrder lineUp : finishedOrders) {
            //
            if (lineUp.getDriver().equals(driver)) {
                ordersWeNeed.add(lineUp);
//                System.out.println(ordersWeNeed);
            }
        }
        return ordersWeNeed;
    }

    public void setFinishedOrderByData(int newId, String newGeneratorId, String newOrderId, String newOrderProduct, String newOrderUnits,
                                 String newOrderQuantity, String newCompany, String newCompanyId, String newEmail,
                                 String newOrderContact, String newOrderPhone, String newOrderDelAddress, String newOrderCity,
                                 String newState, String newOrderCountry, String newOrderGoogleN, String newOrderGoogleE,

                                 String newDriver, String newTruck, String newTrailer,

                                 String newGeneratedDate, String newStartDeliveryDate, String newDeliveredDate, String newAcceptedDate,
                                 String newFinishedDate, String newStatus,

                                 String newSignedName, String newSignedPosition, String newNote

    ) throws TRUCKerListError {
        getFinishedOrders();
        for (FinishedOrder lineUp : finishedOrders) {
            if (lineUp.getId() == newId &&
                    lineUp.getCompany().equals(newCompany) &&
                    lineUp.getDriver().equals(newDriver) && lineUp.getTruck().equals(newTruck)) {

                removeFromList(lineUp);
                FinishedOrder newLineUp = new FinishedOrder(newId, newGeneratorId, newOrderId, newOrderProduct, newOrderUnits,
                        newOrderQuantity, newCompany, newCompanyId, newEmail, newOrderContact, newOrderPhone, newOrderDelAddress,
                        newOrderCity, newState, newOrderCountry, newOrderGoogleN, newOrderGoogleE,
                        newDriver, newTruck, newTrailer, newGeneratedDate, newStartDeliveryDate, newDeliveredDate, newAcceptedDate,
                        newFinishedDate, newStatus, newSignedName, newSignedPosition, newNote);
                addFinishedOrderToList(newLineUp);
            }
        }
        reloadFile();
        getFinishedOrders();
    }

    public void setFinishedOrderByObject(FinishedOrder newFinishedOrder) throws TRUCKerListError {
        getFinishedOrders();
        for (FinishedOrder lineUp : finishedOrders) {
            if (lineUp.getId() == newFinishedOrder.getId() || lineUp.getGeneratorId().equals(newFinishedOrder.getGeneratorId()) ||
                    lineUp.getOrderId().equals(newFinishedOrder.getOrderId())) {

                lineUp.setProduct(newFinishedOrder.getProduct());
                lineUp.setUnits(newFinishedOrder.getUnits());
                lineUp.setQuantity(newFinishedOrder.getQuantity());
                lineUp.setCompany(newFinishedOrder.getCompany());
                lineUp.setCompanyId(newFinishedOrder.getCompanyId());
                lineUp.setEmail(newFinishedOrder.getEmail());
                lineUp.setContact(newFinishedOrder.getContact());
                lineUp.setPhone(newFinishedOrder.getPhone());

                lineUp.setDeliveryAddress(newFinishedOrder.getDeliveryAddress());
                lineUp.setCity(newFinishedOrder.getCity());
                lineUp.setState(newFinishedOrder.getState());
                lineUp.setCountry(newFinishedOrder.getCountry());
                lineUp.setGooglN(newFinishedOrder.getGooglN());
                lineUp.setGooglE(newFinishedOrder.getGooglE());

                lineUp.setDriver(newFinishedOrder.getDriver());
                lineUp.setTruck(newFinishedOrder.getTruck());
                lineUp.setTrailer(newFinishedOrder.getTrailer());
                lineUp.setGeneratedDate(newFinishedOrder.getGeneratedDate());
                lineUp.setStartedDeliveryDate(newFinishedOrder.getStartedDeliveryDate());
                lineUp.setDeliveredDate(newFinishedOrder.getDeliveredDate());
                lineUp.setAcceptedDate(newFinishedOrder.getAcceptedDate());
                lineUp.setFinishedDate(newFinishedOrder.getFinishedDate());
                lineUp.setStatus(newFinishedOrder.getStatus());

                lineUp.setSignedName(newFinishedOrder.getSignedName());
                lineUp.setSignedPosition(newFinishedOrder.getSignedPosition());
                lineUp.setNote(newFinishedOrder.getNote());

            }
        }
        reloadFile();
        getFinishedOrders();
    }

    public void sorting() throws TRUCKerListError {
        getFinishedOrders();
        System.out.println("Sorting Finished Orders by ID.");
        Collections.sort(finishedOrders, (Comparator.comparing(FinishedOrder::getId)));
        finishedOrders.forEach(finishedOrder -> System.out.println(finishedOrder));
    }

    public void removeFromList(FinishedOrder finishedOrder) throws TRUCKerListError {

        if (finishedOrder == null) {
            throw new TRUCKerListError("You need to show egsisting Finished Order to remove it!");
        }
        getFinishedOrders();
        finishedOrders.removeIf(o -> o.getId() == finishedOrder.getId() && o.getGeneratorId().equals(finishedOrder.getGeneratorId()) &&
                o.getOrderId().equals(finishedOrder.getOrderId()) && o.getProduct().equals(finishedOrder.getProduct()) &&
                o.getUnits().equals(finishedOrder.getUnits()) && o.getQuantity().equals(finishedOrder.getQuantity()) &&
                o.getCompany().equals(finishedOrder.getCompany()) && o.getCompanyId().equals(finishedOrder.getCompanyId()) &&
                o.getEmail().equals(finishedOrder.getEmail()) && o.getContact().equals(finishedOrder.getContact()) &&
                o.getPhone().equals(finishedOrder.getPhone()) && o.getDeliveryAddress().equals(finishedOrder.getDeliveryAddress()) &&
                o.getCity().equals(finishedOrder.getCity()) && o.getState().equals(finishedOrder.getState()) &&
                o.getCountry().equals(finishedOrder.getCountry()) && o.getGooglN().equals(finishedOrder.getGooglN()) &&
                o.getGooglE().equals(finishedOrder.getGooglE()) &&

                o.getDriver().equals(finishedOrder.getDriver()) && o.getTruck().equals(finishedOrder.getTruck()) &&
                o.getTrailer().equals(finishedOrder.getTrailer()) &&

                o.getGeneratedDate().equals(finishedOrder.getGeneratedDate()) && o.getStartedDeliveryDate().equals(finishedOrder.getStartedDeliveryDate()) &&
                o.getDeliveredDate().equals(finishedOrder.getDeliveredDate()) && o.getAcceptedDate().equals(finishedOrder.getAcceptedDate()) &&
                o.getFinishedDate().equals(finishedOrder.getFinishedDate()) && o.getStatus().equals(finishedOrder.getStatus()) &&

                o.getSignedName().equals(finishedOrder.getSignedName()) && o.getSignedPosition().equals(finishedOrder.getSignedPosition()) &&
                o.getNote().equals(finishedOrder.getNote()));
        reloadFile();
        getFinishedOrders();
        System.out.println("Finished Order was removed.");
    }

    public void addFinishedOrderToList(FinishedOrder finishedOrder) throws TRUCKerListError {
        getFinishedOrders();
        if (finishedOrder == null) {
            throw new TRUCKerListError("Fill all the requiered fields to add Line-Up!");
        }
        finishedOrders.add(finishedOrder);
        reloadFile();
        getFinishedOrders();
        System.out.println("Finished Order was added.");
    }

    public void reloadFile() throws TRUCKerListError {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(FINISHED_ORDERS_FILE_PATH));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

            for (FinishedOrder orderFromList : finishedOrders) {
                csvPrinter.printRecord(Arrays.asList(orderFromList.getId(), orderFromList.getGeneratorId(),
                        orderFromList.getOrderId(), orderFromList.getProduct(), orderFromList.getUnits(),
                        orderFromList.getQuantity(), orderFromList.getCompany(), orderFromList.getCompanyId(),
                        orderFromList.getEmail(), orderFromList.getContact(), orderFromList.getPhone(),
                        orderFromList.getDeliveryAddress(), orderFromList.getCity(), orderFromList.getState(),
                        orderFromList.getCountry(), orderFromList.getGooglN(), orderFromList.getGooglE(),

                        orderFromList.getDriver(), orderFromList.getTruck(), orderFromList.getTrailer(),

                        orderFromList.getGeneratedDate(), orderFromList.getStartedDeliveryDate(), orderFromList.getDeliveredDate(),
                        orderFromList.getAcceptedDate(), orderFromList.getFinishedDate(), orderFromList.getStatus(),

                        orderFromList.getSignedName(), orderFromList.getSignedPosition(), orderFromList.getNote()));
                csvPrinter.flush();
            }

            csvPrinter.close();
            writer.close();
        } catch (IOException e) {
            throw new TRUCKerListError("Sorry, we can't save Line-Up to file.", e);
        }
    }

    public void printFinishedOrders() throws TRUCKerListError {
        getFinishedOrders();
        finishedOrders.forEach(finishedOrder -> System.out.println(finishedOrder));
    }

    public void getFinishedOrders() throws TRUCKerListError {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(FINISHED_ORDERS_FILE_PATH));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            readFromFile(csvParser);
            csvParser.close();
            reader.close();
        } catch (IOException | TRUCKerListError e) {
            throw new TRUCKerListError("Sorry, we can't read Finished Orders file!", e);
        }
    }

    public void readFromFile(CSVParser csvParser) throws TRUCKerListError {
        finishedOrders = new ArrayList<>();

        for (CSVRecord csvRecord : csvParser) {

            Integer id = Integer.valueOf(csvRecord.get(0));
            String generatorId = csvRecord.get(1);
            String orderId = csvRecord.get(2);
            String orderProduct = csvRecord.get(3);
            String orderUnits = csvRecord.get(4);
            String orderQuantity = csvRecord.get(5);
            String orderCompany = csvRecord.get(6);
            String orderCompanyId = csvRecord.get(7);
            String orderEmail = csvRecord.get(8);
            String orderContact = csvRecord.get(9);
            String orderPhone = csvRecord.get(10);
            String orderDelAddress = csvRecord.get(11);
            String orderCity = csvRecord.get(12);
            String orderState = csvRecord.get(13);
            String orderCountry = csvRecord.get(14);
            String orderGoogleN = csvRecord.get(15);
            String orderGoogleE = csvRecord.get(16);

            String driver = csvRecord.get(17);
            String truck = csvRecord.get(18);
            String trailer = csvRecord.get(19);

            String generatedDate = csvRecord.get(20);
            String startedDeliveryDate = csvRecord.get(21);
            String deliveredDate = csvRecord.get(22);
            String acceptedDate = csvRecord.get(23);
            String finishedDate = csvRecord.get(24);
            String status = csvRecord.get(25);

            String signedName = csvRecord.get(26);
            String signedPosition = csvRecord.get(27);
//            Image signiture = Image.value;
            String note = csvRecord.get(29);


            finishedOrders.add(new FinishedOrder(id, generatorId, orderId, orderProduct, orderUnits, orderQuantity,
                    orderCompany, orderCompanyId, orderEmail, orderContact, orderPhone, orderDelAddress, orderCity,
                    orderState, orderCountry, orderGoogleN, orderGoogleE, driver, truck, trailer, generatedDate,
                    startedDeliveryDate, deliveredDate, acceptedDate, finishedDate, status, signedName, signedPosition, note));
        }
    }
}
