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

public class GeneratorList {

    private static final String LINEUP_FILE_PATH = "src/main/resources/generatedLineUps.csv";

    private ArrayList<Generator> lineUps;

    public GeneratorList() {
        lineUps = new ArrayList<>();
    }

    public ArrayList<Generator> getListOfLineUps() {
        return lineUps;
    }

    public Generator getLineUpByOrdersId(String id) throws TRUCKerListError {
        Generator lineUpWeGet = null;
//        getListOfLineUps();
        printLineUps();
        for (Generator lineUp : lineUps) {
            //
            if (lineUp.getOrderId().equals(id)) {
                lineUpWeGet = lineUp;
                System.out.println("------------");
                System.out.println(lineUpWeGet);
            }
        }
        return lineUpWeGet;
    }

    public ArrayList<Generator> getListLineUpsWhoMachesDriverTruckTrailer(String driver, String truck, String trailer) throws TRUCKerListError {
//        lineUps = new ArrayList();
        ArrayList<Generator> lineUpsWeNeed = new ArrayList();
//        getListOfLineUps();
        printLineUps();
        for (Generator lineUp : lineUps) {
            //
            if (lineUp.getDriver().equals(driver) && lineUp.getTruck().equals(truck) && lineUp.getTrailer().equals(trailer)) {
                lineUpsWeNeed.add(lineUp);
                System.out.println(lineUpsWeNeed);
            }
        }
        return lineUpsWeNeed;
    }

    //
    public void setLineUp(int newId, String newOrderId, String newOrderProduct, String newOrderUnits, String newOrderQuantity, String newOrderContact,
                          String newOrderPhone, String newOrderDelAddress, String newOrderCity, String newOrderCountry,
                          String newOrderGoogleN, String newOrderGoogleE,

                          String newDriver, String newTruck, String newTrailer,

                          String newGeneratedDate, String newStartDeliveryDate, String newDeliveredDate, String newAcceptedDate,
                          String newFinishedDate,

                          String newStatus) throws TRUCKerListError {
        getLineUps();
        for (Generator lineUp : lineUps) {
            if (lineUp.getId() == newId &&
                    lineUp.getOrderProduct().equals(newOrderProduct) &&
                    lineUp.getDriver().equals(newDriver) && lineUp.getTruck().equals(newTruck)) {

                removeFromList(lineUp);
                Generator newLineUp = new Generator(newId, newOrderId, newOrderProduct, newOrderUnits, newOrderQuantity, newOrderContact,
                        newOrderPhone, newOrderDelAddress, newOrderCity, newOrderCountry, newOrderGoogleN, newOrderGoogleE,
                        newDriver, newTruck, newTrailer, newGeneratedDate, newStartDeliveryDate, newDeliveredDate, newAcceptedDate,
                        newFinishedDate, newStatus);
                addLineUpToList(newLineUp);
            }
        }
        reloadFile();
        getLineUps();
    }
    public void setLineUp(Generator newLineUp) throws TRUCKerListError {
        getLineUps();
        for (Generator lineUp : lineUps) {
            if (lineUp.getId() == newLineUp.getId()) {

                lineUp.setOrderId(newLineUp.getOrderId());
                lineUp.setOrderProduct(newLineUp.getOrderProduct());
                lineUp.setOrderUnits(newLineUp.getOrderUnits());
                lineUp.setOrderQuantity(newLineUp.getOrderQuantity());
                lineUp.setOrderContact(newLineUp.getOrderContact());
                lineUp.setOrderPhone(newLineUp.getOrderPhone());
                lineUp.setOrderDelAddress(newLineUp.getOrderDelAddress());
                lineUp.setOrderCity(newLineUp.getOrderCity());
                lineUp.setOrderCountry(newLineUp.getOrderCountry());
                lineUp.setOrderGoogleN(newLineUp.getOrderGoogleN());
                lineUp.setOrderGoogleE(newLineUp.getOrderGoogleE());
                lineUp.setDriver(newLineUp.getDriver());
                lineUp.setTruck(newLineUp.getTruck());
                lineUp.setTrailer(newLineUp.getTrailer());
                lineUp.setGeneratedDate(newLineUp.getGeneratedDate());
                lineUp.setStartedDeliveryDate(newLineUp.getStartedDeliveryDate());
                lineUp.setDeliveredDate(newLineUp.getDeliveredDate());
                lineUp.setAcceptedDate(newLineUp.getAcceptedDate());
                lineUp.setFinishedDate(newLineUp.getFinishedDate());
                lineUp.setStatus(newLineUp.getStatus());

            }
        }
        reloadFile();
        getLineUps();
    }

    public void sorting() throws TRUCKerListError {
        getLineUps();
        System.out.println("Sorting Line-Ups by ID.");
        Collections.sort(lineUps, (Comparator.comparing(Generator::getId)));
        lineUps.forEach(lineUp -> System.out.println(lineUp));
    }

    public void removeFromList(Generator lineUp) throws TRUCKerListError {

        if (lineUp == null) {
            throw new TRUCKerListError("You need to show egsisting Line-Up to remove it!");
        }
        getLineUps();
        lineUps.removeIf(o -> o.getId() == lineUp.getId() &&
                o.getOrderId().equals(lineUp.getOrderId()) &&
                o.getOrderProduct().equals(lineUp.getOrderProduct()) && o.getOrderUnits().equals(lineUp.getOrderUnits()) &&
                o.getOrderQuantity().equals(lineUp.getOrderQuantity()) && o.getOrderContact().equals(lineUp.getOrderContact()) &&
                o.getOrderPhone().equals(lineUp.getOrderPhone()) && o.getOrderDelAddress().equals(lineUp.getOrderDelAddress()) &&
                o.getOrderCity().equals(lineUp.getOrderCity()) && o.getOrderCountry().equals(lineUp.getOrderCountry()) &&
                o.getOrderGoogleN().equals(lineUp.getOrderGoogleN()) && o.getOrderGoogleE().equals(lineUp.getOrderGoogleE()) &&

                o.getDriver().equals(lineUp.getDriver()) && o.getTruck().equals(lineUp.getTruck()) &&
                o.getTrailer().equals(lineUp.getTrailer()) &&

                o.getGeneratedDate().equals(lineUp.getGeneratedDate()) && o.getStartedDeliveryDate().equals(lineUp.getStartedDeliveryDate()) &&
                o.getDeliveredDate().equals(lineUp.getDeliveredDate()) && o.getAcceptedDate().equals(lineUp.getAcceptedDate()) &&
                o.getFinishedDate().equals(lineUp.getFinishedDate()) &&

                o.getStatus().equals(lineUp.getStatus()));
        reloadFile();
        getLineUps();
        System.out.println("Line-Up was removed.");
    }

    public void addLineUpToList(Generator lineUp) throws TRUCKerListError {
        if (lineUp == null) {
            throw new TRUCKerListError("Fill all the requiered fields to add Line-Up!");
        }
        lineUps.add(lineUp);
        reloadFile();
        getLineUps();
        System.out.println("Line-Up was added.");
    }

    public void reloadFile() throws TRUCKerListError {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(LINEUP_FILE_PATH));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

            for (Generator lineUpFromList : lineUps) {
                csvPrinter.printRecord(Arrays.asList(lineUpFromList.getId(), lineUpFromList.getOrderId(),
                        lineUpFromList.getOrderProduct(), lineUpFromList.getOrderUnits(),
                        lineUpFromList.getOrderQuantity(), lineUpFromList.getOrderContact(), lineUpFromList.getOrderPhone(),
                        lineUpFromList.getOrderDelAddress(), lineUpFromList.getOrderCity(), lineUpFromList.getOrderCountry(),
                        lineUpFromList.getOrderGoogleN(), lineUpFromList.getOrderGoogleE(),

                        lineUpFromList.getDriver(), lineUpFromList.getTruck(), lineUpFromList.getTrailer(),

                        lineUpFromList.getGeneratedDate(), lineUpFromList.getStartedDeliveryDate(), lineUpFromList.getDeliveredDate(),
                        lineUpFromList.getAcceptedDate(), lineUpFromList.getFinishedDate(),

                        lineUpFromList.getStatus()));
                csvPrinter.flush();
            }

            csvPrinter.close();
            writer.close();
        } catch (IOException e) {
            throw new TRUCKerListError("Sorry, we can't save Line-Up to file.", e);
        }
    }

    public void printLineUps() throws TRUCKerListError {
        getLineUps();
        lineUps.forEach(lineUp -> System.out.println(lineUp));
    }

    public void getLineUps() throws TRUCKerListError {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(LINEUP_FILE_PATH));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            readFromFile(csvParser);
            csvParser.close();
            reader.close();
        } catch (IOException | TRUCKerListError e) {
            throw new TRUCKerListError("Sorry, we can't read Generated Line-Ups file!", e);
        }
    }

    public void readFromFile(CSVParser csvParser) throws TRUCKerListError {
        lineUps = new ArrayList<>();

        for (CSVRecord csvRecord : csvParser) {

            Integer id = Integer.valueOf(csvRecord.get(0));
            String orderId = csvRecord.get(1);
            String orderProduct = csvRecord.get(2);
            String orderUnits = csvRecord.get(3);
            String orderQuantity = csvRecord.get(4);
            String orderContact = csvRecord.get(5);
            String orderPhone = csvRecord.get(6);
            String orderDelAddress = csvRecord.get(7);
            String orderCity = csvRecord.get(8);
            String orderCountry = csvRecord.get(9);
            String orderGoogleN = csvRecord.get(10);
            String orderGoogleE = csvRecord.get(11);

            String driver = csvRecord.get(12);
            String truck = csvRecord.get(13);
            String trailer = csvRecord.get(14);

            String generatedDate = csvRecord.get(15);
            String startedDeliveryDate = csvRecord.get(16);
            String deliveredDate = csvRecord.get(17);
            String acceptedDate = csvRecord.get(18);
            String finishedDate = csvRecord.get(19);

            String status = csvRecord.get(20);


            lineUps.add(new Generator(id, orderId, orderProduct, orderUnits, orderQuantity, orderContact, orderPhone, orderDelAddress,
                    orderCity, orderCountry, orderGoogleN, orderGoogleE, driver, truck, trailer, generatedDate, startedDeliveryDate,
                    deliveredDate, acceptedDate, finishedDate, status));
        }
    }
}
