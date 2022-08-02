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

public class TrailerList {
    private static final String TRAILER_FILE_PATH = "src/main/resources/trailers.csv";

    private ArrayList<Trailer> trailers;

    private ArrayList<String> trailersFullInfo;

    private ArrayList<String> trailersInfo;

    public TrailerList() {
        trailers = new ArrayList<>();
    }

    public ArrayList<Trailer> getListOfTrailers() {
        return trailers;
    }

    //

    public ArrayList<String> ListOfTrailersFullInfo() throws TRUCKerListError {
        trailersFullInfo = new ArrayList<>();
        getTrailers();
        for (int i = 0; i < trailers.size(); i++) {
            trailersFullInfo.add(i, trailers.get(i).toString());
            System.out.println(trailersFullInfo.get(i));
        }
        return trailersFullInfo;
    }

    public ArrayList<String> ListOfTrailersShortInfo() throws TRUCKerListError {
        trailersInfo = new ArrayList<>();
        getTrailers();
        for (int i = 0; i < trailers.size(); i++) {
            trailersInfo.add(i, trailers.get(i).getName() + " " + trailers.get(i).getModel() + " " + trailers.get(i).getPlate());
            System.out.println(trailersInfo.get(i));
        }
        return trailersInfo;
    }

    public void setTrailer(int newId, String newName, String newModel, String newPlate, String newMot, String newVin,
                           String newInsurance, String newOther) throws TRUCKerListError {
        getTrailers();
        for (Trailer trailer : trailers) {
            if (trailer.getId() == newId && trailer.getName().equals(newName) && trailer.getModel().equals(newModel) &&
                    trailer.getPlate().equals(newPlate)) {
                removeFromList(trailer);
                Trailer newTrailer = new Trailer(newId, newName, newModel, newPlate, newMot, newVin,
                        newInsurance, newOther);
                addTrailerToList(newTrailer);
            }
        }
        reloadFile();
        getTrailers();
    }

    public void sorting() throws TRUCKerListError {
        getTrailers();
        System.out.println("Sorting Trailers by ID.");
        Collections.sort(trailers, (Comparator.comparing(Trailer::getId)));
        trailers.forEach(trailer -> System.out.println(trailer));
    }

    public void removeFromList(Trailer trailer) throws TRUCKerListError {

        if (trailer == null) {
            throw new TRUCKerListError("You need to show egsisting trailer to remove it!");
        }
        getTrailers();
        trailers.removeIf(o -> o.getId() == trailer.getId() && o.getName().equals(trailer.getName()) &&
                o.getModel().equals(trailer.getModel()) && o.getPlate().equals(trailer.getPlate()) &&
                o.getMot().equals(trailer.getMot()) && o.getVin().equals(trailer.getVin()) &&
                o.getInsurance().equals(trailer.getInsurance()) && o.getOther().equals(trailer.getOther()));
        reloadFile();
        getTrailers();
        System.out.println("Trailer was removed.");
    }

    public void addTrailerToList(Trailer trailer) throws TRUCKerListError {
        if (trailer == null) {
            throw new TRUCKerListError("Fill all the requiered fields to add trailer!");
        }
        trailers.add(trailer);
        reloadFile();
        getTrailers();
        System.out.println("Trailer was added.");
    }

    public void reloadFile() throws TRUCKerListError {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(TRAILER_FILE_PATH));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

            for (Trailer trailerFromList : trailers) {
                csvPrinter.printRecord(Arrays.asList(trailerFromList.getId(), trailerFromList.getName(), trailerFromList.getModel(),
                        trailerFromList.getPlate(), trailerFromList.getMot(), trailerFromList.getVin(), trailerFromList.getInsurance(),
                        trailerFromList.getOther()));
                csvPrinter.flush();
            }

            csvPrinter.close();
            writer.close();
        } catch (IOException e) {
            throw new TRUCKerListError("Sorry, we can't save trailer to file.", e);
        }
    }

    public void printTrailers() throws TRUCKerListError {
        getTrailers();
        trailers.forEach(trailer -> System.out.println(trailer));
    }

    public void getTrailers() throws TRUCKerListError {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(TRAILER_FILE_PATH));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            readFromFile(csvParser);
            csvParser.close();
            reader.close();
        } catch (IOException | TRUCKerListError e) {
            throw new TRUCKerListError("Sorry, we can't read trailers list file!", e);
        }
    }

    public void readFromFile(CSVParser csvParser) throws TRUCKerListError {
        trailers = new ArrayList<>();

        for (CSVRecord csvRecord : csvParser) {

            Integer id = Integer.valueOf(csvRecord.get(0));
            String name = csvRecord.get(1);
            String model = csvRecord.get(2);
            String plate = csvRecord.get(3);
            String mot = csvRecord.get(4);
            String vin = csvRecord.get(5);
            String insurance = csvRecord.get(6);
            String other = csvRecord.get(7);

            trailers.add(new Trailer(id, name, model, plate, mot, vin, insurance, other));
        }
    }
}
