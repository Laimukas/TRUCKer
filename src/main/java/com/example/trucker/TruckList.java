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

public class TruckList {
    private static final String TRUCKS_FILE_PATH = "src/main/resources/trucks.csv";

    private ArrayList<Truck> trucks;

    private ArrayList<String> trucksFullInfo;

    private ArrayList<String> trucksInfo;

    public TruckList() {
        trucks = new ArrayList<>();
    }

    public ArrayList<Truck> getListOfTrucks() {
        return trucks;
    }

    //

    public ArrayList<String> ListOfTrucksFullInfo() throws TRUCKerListError {
        trucksFullInfo = new ArrayList<>();
        getTrucks();
        for (int i = 0; i < trucks.size(); i++) {
            trucksFullInfo.add(i, trucks.get(i).toString());
            System.out.println(trucksFullInfo.get(i));
        }
        return trucksFullInfo;
    }

    public ArrayList<String> ListOfTrucksShortInfo() throws TRUCKerListError {
        trucksInfo = new ArrayList<>();
        getTrucks();
        for (int i = 0; i < trucks.size(); i++) {
            trucksInfo.add(i, trucks.get(i).getName() + " " + trucks.get(i).getModel() + " " + trucks.get(i).getPlate());
            System.out.println(trucksInfo.get(i));
        }
        return trucksInfo;
    }


    public void setTruck(int newId, String newName, String newModel, String newPlate, String newMot, String newVin,
                         String newInsurance, String newDriver, String newOther) throws TRUCKerListError {
        getTrucks();
        for (Truck truck : trucks) {
            if (truck.getId() == newId && truck.getName().equals(newName) && truck.getModel().equals(newModel) &&
                    truck.getPlate().equals(newPlate)) {
                removeFromList(truck);
                Truck newTruck = new Truck(newId, newName, newModel, newPlate, newMot, newVin,
                        newInsurance, newDriver, newOther);
                addTruckToList(newTruck);
            }
        }
        reloadFile();
        getTrucks();
    }

    public void sorting() throws TRUCKerListError {
        getTrucks();
        System.out.println("Sorting Trucks by ID.");
        Collections.sort(trucks, (Comparator.comparing(Truck::getId)));
        trucks.forEach(truck -> System.out.println(truck));
    }

    public void removeFromList(Truck truck) throws TRUCKerListError {

        if (truck == null) {
            throw new TRUCKerListError("You need to show egsisting truck to remove it!");
        }
        getTrucks();
        trucks.removeIf(o -> o.getId() == truck.getId() && o.getName().equals(truck.getName()) &&
                o.getModel().equals(truck.getModel()) && o.getPlate().equals(truck.getPlate()) &&
                o.getMot().equals(truck.getMot()) && o.getVin().equals(truck.getVin()) &&
                o.getInsurance().equals(truck.getInsurance()) && o.getDriver().equals(truck.getDriver()) && o.getOther().equals(truck.getOther()));
        reloadFile();
        getTrucks();
        System.out.println("Truck was removed.");
    }

    public void addTruckToList(Truck truck) throws TRUCKerListError {
        if (truck == null) {
            throw new TRUCKerListError("Fill all the requiered fields to add truck!");
        }
        trucks.add(truck);
        reloadFile();
        getTrucks();
        System.out.println("Truck was added.");
    }

    public void reloadFile() throws TRUCKerListError {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(TRUCKS_FILE_PATH));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

            for (Truck truckFromList : trucks) {
                csvPrinter.printRecord(Arrays.asList(truckFromList.getId(), truckFromList.getName(), truckFromList.getModel(),
                        truckFromList.getPlate(), truckFromList.getMot(), truckFromList.getVin(), truckFromList.getInsurance(),
                        truckFromList.getDriver(), truckFromList.getOther()));
                csvPrinter.flush();
            }

            csvPrinter.close();
            writer.close();
        } catch (IOException e) {
            throw new TRUCKerListError("Sorry, we can't save truck to file.", e);
        }
    }

    public void printTrucks() throws TRUCKerListError {
        getTrucks();
        trucks.forEach(truck -> System.out.println(truck));
    }

    public void getTrucks() throws TRUCKerListError {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(TRUCKS_FILE_PATH));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            readFromFile(csvParser);
            csvParser.close();
            reader.close();
        } catch (IOException | TRUCKerListError e) {
            throw new TRUCKerListError("Sorry, we can't read trucks list file!", e);
        }
    }

    public void readFromFile(CSVParser csvParser) throws TRUCKerListError {
        trucks = new ArrayList<>();

        for (CSVRecord csvRecord : csvParser) {

            Integer id = Integer.valueOf(csvRecord.get(0));
            String name = csvRecord.get(1);
            String model = csvRecord.get(2);
            String plate = csvRecord.get(3);
            String mot = csvRecord.get(4);
            String vin = csvRecord.get(5);
            String insurance = csvRecord.get(6);
            String driver = csvRecord.get(7);
            String other = csvRecord.get(8);

            trucks.add(new Truck(id, name, model, plate, mot, vin, insurance, driver, other));
        }
    }
}
