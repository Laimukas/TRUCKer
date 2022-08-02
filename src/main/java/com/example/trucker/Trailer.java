package com.example.trucker;

public class Trailer implements Comparable<Trailer>{

    private static int nextId = 1;

    private int id;
    private String name;
    private String model;
    private String plate;
    private String mot;
    private String vin;
    private String insurance;
    private String other;

    public Trailer(int id, String name, String model, String plate, String mot, String vin, String insurance, String other) {
        this.id = id;
        if (this.id >= nextId) {
            nextId = this.id + 1;
        }
        this.name = name;
        this.model = model;
        this.plate = plate;
        this.mot = mot;
        this.vin = vin;
        this.insurance = insurance;
        this.other = other;
    }
    public Trailer(String name, String model, String plate, String mot, String vin, String insurance, String other) {
        this.id = nextId++;
        this.name = name;
        this.model = model;
        this.plate = plate;
        this.mot = mot;
        this.vin = vin;
        this.insurance = insurance;
        this.other = other;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Trailer.nextId = nextId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getMot() {
        return mot;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "Trailer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", plate='" + plate + '\'' +
                ", mot='" + mot + '\'' +
                ", vin='" + vin + '\'' +
                ", insurance='" + insurance + '\'' +
                ", Other='" + other + '\'' +
                '}';
    }

    @Override
    public int compareTo(Trailer o) {
        return 0;
    }
}
