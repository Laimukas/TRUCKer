package com.example.trucker;

import com.example.trucker.exceptions.TRUCKerListError;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminTrucks implements Initializable {

    private DriverList driverList;

    private TruckList truckList;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfModel;
    @FXML
    private TextField tfPlate;
    @FXML
    private TextField tfMot;
    @FXML
    private TextField tfVin;
    @FXML
    private TextField tfInsurance;
    @FXML
    private ChoiceBox<String> cbDriver;
    @FXML
    private TextField tfDriver;
    @FXML
    private TextArea taOther;
    @FXML
    private TableView<Truck> tvTrucks;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colModel;
    @FXML
    private TableColumn colPlate;
    @FXML
    private TableColumn colMot;
    @FXML
    private TableColumn colVin;
    @FXML
    private TableColumn colInsurance;
    @FXML
    private TableColumn colDriver;
    @FXML
    private TableColumn colOther;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnTrucks;
    @FXML
    private Button btnTrailers;
    @FXML
    private Button btnUsers;
    @FXML
    private Button btnGenerator;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button logout;
    @FXML
    private Label lblError;

    private String ID;

    public static ObservableList<Truck> listOfTrucks;

    public void insertTrucksToTable() {
        truckList = new TruckList();
        try {
            truckList.getTrucks();
            truckList.sorting();
        } catch (TRUCKerListError e) {
            throw new RuntimeException(e);
        }

        System.out.println("Loading table");

        truckList.getListOfTrucks().forEach(tl -> System.out.println(tl.getId() + "," + tl.getName() + "," +
                tl.getModel()+ "," + tl.getPlate() + "," + tl.getMot()+ "," + tl.getVin() + "," +
                tl.getInsurance()+ "," + tl.getDriver()+ "," + tl.getOther()));

        colId.setCellValueFactory(new PropertyValueFactory<Truck, String>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Truck, String>("name"));
        colModel.setCellValueFactory(new PropertyValueFactory<Truck, String>("model"));
        colPlate.setCellValueFactory(new PropertyValueFactory<Truck, String>("plate"));
        colMot.setCellValueFactory(new PropertyValueFactory<Truck, String>("mot"));
        colVin.setCellValueFactory(new PropertyValueFactory<Truck, String>("vin"));
        colInsurance.setCellValueFactory(new PropertyValueFactory<Truck, String>("insurance"));
        colDriver.setCellValueFactory(new PropertyValueFactory<Truck, String>("driver"));
        colOther.setCellValueFactory(new PropertyValueFactory<Truck, String>("other"));


        listOfTrucks = FXCollections.observableArrayList(truckList.getListOfTrucks());
        tvTrucks.setItems(listOfTrucks);
    }

    @FXML
    private void handleMouseAction(MouseEvent event){
        Truck mouseTruck = tvTrucks.getSelectionModel().getSelectedItem();

        ID=String.valueOf(mouseTruck.getId());
        tfName.setText(mouseTruck.getName());
        tfModel.setText(mouseTruck.getModel());
        tfPlate.setText(mouseTruck.getPlate());
        tfMot.setText(mouseTruck.getMot());
        tfVin.setText(mouseTruck.getVin());
        tfInsurance.setText(mouseTruck.getInsurance());
        tfDriver.setText(mouseTruck.getDriver());
        taOther.setText(mouseTruck.getOther());
    }

    // Update Button action

    public void updateTruck(ActionEvent event) throws TRUCKerListError, IOException {
        editTruck();
    }

    private void editTruck() throws TRUCKerListError, IOException {
        truckList.getTrucks();
        Truck truck = new Truck(Integer.parseInt(ID.toString()),tfName.getText(), tfModel.getText(),tfPlate.getText(),
                tfMot.getText(), tfVin.getText(),tfInsurance.getText(), tfDriver.getText(), taOther.getText());
        if (truck == null) {
            throw new TRUCKerListError("Fill requaered fields!");
        }
        lblError.setText("Making changes for egsisting truck!");
        truckList.setTruck(Integer.parseInt(ID.toString()), tfName.getText(), tfModel.getText(),tfPlate.getText(),
                tfMot.getText(), tfVin.getText(),tfInsurance.getText(), tfDriver.getText(),taOther.getText());
        truckList.reloadFile();
        truckList.getTrucks();
        makeTextEmpty();
        insertTrucksToTable();
    }

    // Delete Button action

    public void deleteTruck(ActionEvent event) throws TRUCKerListError, IOException {
        removeTruck();
    }

    private void removeTruck() throws TRUCKerListError, IOException {
        truckList.getTrucks();
        Truck truck = new Truck(Integer.parseInt(ID.toString()),tfName.getText(), tfModel.getText(),tfPlate.getText(),
                tfMot.getText(), tfVin.getText(),tfInsurance.getText(), tfDriver.getText(), taOther.getText());
        if (truck == null) {
            throw new TRUCKerListError("You need to show egsisting truck to remove it!");
        }
        lblError.setText("Removing egsisting truck. Reloading file!");
        truckList.removeFromList(truck);
        truckList.reloadFile();
        truckList.getTrucks();
        makeTextEmpty();
        insertTrucksToTable();
    }

    public void makeTextEmpty() {
        tfName.setText("");tfModel.setText("");tfPlate.setText("");tfMot.setText("");
        tfVin.setText("");tfInsurance.setText("");tfDriver.setText("");taOther.setText("");
    }

    // Insert Button action

    public void insertTruck(ActionEvent event) throws TRUCKerListError, IOException {
        addTruck();
    }

    private void addTruck() throws TRUCKerListError, IOException {
        truckList.getTrucks();
        Truck truck = new Truck(tfName.getText(), tfModel.getText(),tfPlate.getText(),
                tfMot.getText(), tfVin.getText(),tfInsurance.getText(), tfDriver.getText(), taOther.getText());
        if (truck == null) {
            throw new TRUCKerListError("Fill all the requiered fields to add truck!");
        }
        truckList.addTruckToList(truck);
        truckList.reloadFile();
        truckList.getTrucks();
        lblError.setText("Truck was Added! Reloading file!");
        makeTextEmpty();
        insertTrucksToTable();
    }


    public void adminLogOut(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("hello-view.fxml", 600, 300);
    }

    public void adminTrailers(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("adminTrailers.fxml", 1308, 450);
    }

    public void adminDrivers(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("adminDrivers.fxml",1118,450);
    }

    public void adminOrders(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("adminPage.fxml", 1389, 764);
    }

    public void adminUsers(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("adminUsers.fxml", 630, 490);
    }

    public void adminGenerator(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("adminGenerator.fxml", 1600, 800);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        driverList = new DriverList();
        truckList = new TruckList();
        try {
            cbDriver.getItems().addAll(driverList.ListOfDriversName());
        } catch (TRUCKerListError e) {
            e.printStackTrace();
        }
        cbDriver.setOnAction(this::getDriver);
        insertTrucksToTable();
    }

    public void getDriver(ActionEvent event){
        String myDriver = cbDriver.getValue();
        tfDriver.setText(myDriver);
    }
}
