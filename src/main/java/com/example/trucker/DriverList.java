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
import java.util.*;

public class DriverList {
    private static final String DRIVERS_FILE_PATH = "src/main/resources/drivers.csv";

    private ArrayList<Driver> drivers;

//    private static List<Driver> list = null;

    private ArrayList<String> driversFullInfo;

    private ArrayList<String> driversName;

    public DriverList() {
        drivers = new ArrayList<>();
    }

    public ArrayList<String> ListOfDriversFullInfo() throws TRUCKerListError {
        driversFullInfo = new ArrayList<>();
        getDrivers();
        for (int i = 0; i < drivers.size(); i++) {
            driversFullInfo.add(i, drivers.get(i).toString());
            System.out.println(driversFullInfo.get(i));
        }
        return driversFullInfo;
    }

//    static {
//        list = new ArrayList();
//
//    }

//    public static List<Driver> getList() {
//        return list;
//    }

    public ArrayList<String> ListOfDriversName() throws TRUCKerListError {
        driversName = new ArrayList<>();
        getDrivers();
        for (int i = 0; i < drivers.size(); i++) {
            driversName.add(i, drivers.get(i).getName());
            System.out.println(driversName.get(i));
        }
        return driversName;
    }

    public ArrayList<Driver> getListOfDrivers() {
        return drivers;
    }

    //    public void getListOfDriversName() throws TRUCKerListError {
//        getDrivers();
//        drivers.forEach(driver -> System.out.println(driver.getName()));
//    }
    public Driver getDriverByUserName(String username) throws TRUCKerListError {
        Driver driverWeNeed = null;
        getDrivers();
        if (username != null && username.equals("")) {
            throw new TRUCKerListError("Driver doesn't excist!");
        }
        System.out.println("We check the egsisting drivers to mach.");
        for (Driver driver : drivers) {
            if (driver.getSurname().equals(username)) {
                driverWeNeed = driver;
            }
        }
        System.out.println(driverWeNeed);
        return driverWeNeed;
    }

    public void setDriver(int newId, String newName, String newSurname, String newBirth, String newAddress,
                          String newPhone, String newEmail, String newPSW) throws TRUCKerListError {
        getDrivers();
        for (Driver driver : drivers) {
            if (driver.getName().equals(newName) && driver.getBirth().equals(newBirth)) {
                driver.setId(newId);
                driver.setName(newName);
                driver.setSurname(newSurname);
                driver.setBirth(newBirth);
                driver.setAddress(newAddress);
                driver.setPhone(newPhone);
                driver.setEmail(newEmail);
                driver.setPsw(newPSW);
                //if this doesnt work we will use this down.
//                removeFromList(driver);
//                Driver newDriver=new Driver(newId,newName,newSurname,newBirth,newAddress,newPhone,
//                        newEmail,newPSW);
//                addDriverToList(newDriver);
            }
        }
        reloadFile();
        getDrivers();
    }

    public void sorting() throws TRUCKerListError {
        getDrivers();
        System.out.println("Sorting Drivers by ID.");
        Collections.sort(drivers, (Comparator.comparing(Driver::getId)));
        drivers.forEach(driver -> System.out.println(driver));
    }

    public void removeFromList(Driver driver) throws TRUCKerListError {

        if (driver == null) {
            throw new TRUCKerListError("You need to show egsisting driver to remove it!");
        }
        getDrivers();
        drivers.removeIf(d -> d.getId() == (driver.getId()) && d.getName().equals(driver.getName()) &&
                d.getSurname().equals(driver.getSurname()) && d.getBirth().equals(driver.getBirth()) &&
                d.getAddress().equals(driver.getAddress()) && d.getPhone().equals(driver.getPhone()) &&
                d.getEmail().equals(driver.getEmail()) && d.getPsw().equals(driver.getPsw()));
        reloadFile();
        getDrivers();
        System.out.println("Driver was removed.");
    }

    public void addDriverToList(Driver driver) throws TRUCKerListError {
        if (driver == null) {
            throw new TRUCKerListError("Fill all the requiered fields to add driver!");
        }
        drivers.add(driver);
        reloadFile();
        getDrivers();
        System.out.println("Driver was added.");
    }

    public void reloadFile() throws TRUCKerListError {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(DRIVERS_FILE_PATH));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

            for (Driver driverFromList : drivers) {
                csvPrinter.printRecord(Arrays.asList(driverFromList.getId(), driverFromList.getName(), driverFromList.getSurname(),
                        driverFromList.getBirth(), driverFromList.getAddress(), driverFromList.getPhone(), driverFromList.getEmail(),
                        driverFromList.getPsw()));
                csvPrinter.flush();
            }

            csvPrinter.close();
            writer.close();
        } catch (IOException e) {
            throw new TRUCKerListError("Sorry, we can't save driver to file.", e);
        }
    }

    public void printDrivers() throws TRUCKerListError {
        getDrivers();
        drivers.forEach(driver -> System.out.println(driver));
    }

    public Driver getOneDriver(String newName, String newSurname, String newBirth, String newAddress,
                               String newPhone, String newEmail, String newPSW) throws TRUCKerListError {
        Driver driverWeNeed = null;
        getDrivers();
        if (newName != null && newName.equals("") || newSurname != null && newSurname.equals("")
                || newBirth != null && newBirth.equals("") || newPhone != null && newPhone.equals("")) {
            throw new TRUCKerListError("Driver doesn't excist!");
        }
        System.out.println("We check the egsisting drivers to mach.");
        for (Driver driver : drivers) {
            if (driver.getName().equals(newName) && driver.getBirth().equals(newBirth)) {
                driverWeNeed = driver;
            }
        }
        System.out.println(driverWeNeed);
        return driverWeNeed;
    }

    public void getDrivers() throws TRUCKerListError {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(DRIVERS_FILE_PATH));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            readFromFile(csvParser);
            csvParser.close();
            reader.close();
        } catch (IOException | TRUCKerListError e) {
            throw new TRUCKerListError("Sorry, we can't read drivers list file!", e);
        }
    }

    public void readFromFile(CSVParser csvParser) throws TRUCKerListError {
        drivers = new ArrayList<>();

        for (CSVRecord csvRecord : csvParser) {

            int id = Integer.parseInt(csvRecord.get(0));
            String name = csvRecord.get(1);
            String surname = csvRecord.get(2);
            String birth = csvRecord.get(3);
            String address = csvRecord.get(4);
            String phone = csvRecord.get(5);
            String email = csvRecord.get(6);
            String psw = csvRecord.get(7);

            drivers.add(new Driver(id, name, surname, birth, address, phone, email, psw));
        }
    }
}
