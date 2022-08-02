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

public class AdminGenerator implements Initializable {

    private GeneratorList lineUpList;

    private DriverList driverList;

    private TruckList truckList;

    private TrailerList trailerList;

    private OrderList orderList;

    @FXML
    private TextField tfOrderId;
    @FXML
    private TextField tfProduct;
    @FXML
    private TextField tfUnits;
    @FXML
    private TextField tfQuantity;
    @FXML
    private TextField tfContact;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfAddress;
    @FXML
    private TextField tfCity;
    @FXML
    private TextField tfCountry;
    @FXML
    private TextField tfGoogleN;
    @FXML
    private TextField tfGoogleE;
    @FXML
    private TextField tfDriver;
    @FXML
    private TextField tfTruck;
    @FXML
    private TextField tfTrailer;
    @FXML
    private TextField tfGenerated;
    @FXML
    private TextField tfStartDelivery;
    @FXML
    private TextField tfDelivered;
    @FXML
    private TextField tfAccepted;
    @FXML
    private TextField tfDead;
    @FXML
    private TextField tfStatus;
    @FXML
    private ChoiceBox<String> cbOrder;
    @FXML
    private ChoiceBox<String> cbDriver;
    @FXML
    private ChoiceBox<String> cbTruck;
    @FXML
    private ChoiceBox<String> cbTrailer;
    @FXML
    private ChoiceBox<String> cbStatus;

    private String[] status = {"generated", "loaded", "left the warehouse", "on the way", "delivered", "signed"};
    @FXML
    private TableView<Generator> tvLineUps;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colOrderId;
    @FXML
    private TableColumn colOrderProduct;
    @FXML
    private TableColumn colUnits;
    @FXML
    private TableColumn colQuantity;
    @FXML
    private TableColumn colContact;
    @FXML
    private TableColumn colPhone;
    @FXML
    private TableColumn colDelAddress;
    @FXML
    private TableColumn colCity;
    @FXML
    private TableColumn colCountry;
    @FXML
    private TableColumn colGoogleN;
    @FXML
    private TableColumn colGoogleE;
    @FXML
    private TableColumn colDriver;
    @FXML
    private TableColumn colTruck;
    @FXML
    private TableColumn colTrailer;
    @FXML
    private TableColumn colGenerated;
    @FXML
    private TableColumn colStartDel;
    @FXML
    private TableColumn colDelivered;
    @FXML
    private TableColumn colAccepted;
    @FXML
    private TableColumn colEnd;
    @FXML
    private TableColumn colStatus;

    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnDrivers;
    @FXML
    private Button btnTrucks;
    @FXML
    private Button btnTrailers;
    @FXML
    private Button btnUsers;
    @FXML
    private Button btnInsertData;
    @FXML
    private Button logout;
    @FXML
    private Label lblError;

    private String ID;

    public static ObservableList<Generator> listOfLineUps;

    public void insertLineUpsToTable() {
        lineUpList = new GeneratorList();
        try {
            lineUpList.getLineUps();
            lineUpList.sorting();
        } catch (TRUCKerListError e) {
            throw new RuntimeException(e);
        }

        System.out.println("Loading table");

        lineUpList.getListOfLineUps().forEach(lu -> System.out.println(lu.getId() + "," + lu.getOrderId() + "," +
                lu.getOrderProduct() + "," + lu.getOrderUnits() + "," + lu.getOrderQuantity() + "," +
                lu.getOrderContact() + "," + lu.getOrderPhone() + "," + lu.getOrderDelAddress() + "," +
                lu.getOrderCity() + "," + lu.getOrderCountry() + "," + lu.getOrderGoogleN() + "," +
                lu.getOrderGoogleE() + "," + lu.getDriver() + "," + lu.getTruck() + "," + lu.getTrailer() + "," +
                lu.getGeneratedDate() + "," + lu.getStartedDeliveryDate() + "," + lu.getDeliveredDate() + "," +
                lu.getAcceptedDate() + "," + lu.getFinishedDate() + "," + lu.getStatus()));

        colId.setCellValueFactory(new PropertyValueFactory<Generator, String>("id"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<Generator, String>("orderId"));
        colOrderProduct.setCellValueFactory(new PropertyValueFactory<Generator, String>("orderProduct"));
        colUnits.setCellValueFactory(new PropertyValueFactory<Generator, String>("orderUnits"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<Generator, String>("orderQuantity"));
        colContact.setCellValueFactory(new PropertyValueFactory<Generator, String>("orderContact"));
        colPhone.setCellValueFactory(new PropertyValueFactory<Generator, String>("orderPhone"));
        colDelAddress.setCellValueFactory(new PropertyValueFactory<Generator, String>("orderDelAddress"));
        colCity.setCellValueFactory(new PropertyValueFactory<Generator, String>("orderCity"));
        colCountry.setCellValueFactory(new PropertyValueFactory<Generator, String>("orderCountry"));
        colGoogleN.setCellValueFactory(new PropertyValueFactory<Generator, String>("orderGoogleN"));
        colGoogleE.setCellValueFactory(new PropertyValueFactory<Generator, String>("orderGoogleE"));

        colDriver.setCellValueFactory(new PropertyValueFactory<Generator, String>("driver"));
        colTruck.setCellValueFactory(new PropertyValueFactory<Generator, String>("truck"));
        colTrailer.setCellValueFactory(new PropertyValueFactory<Generator, String>("trailer"));

        colGenerated.setCellValueFactory(new PropertyValueFactory<Generator, String>("generatedDate"));
        colStartDel.setCellValueFactory(new PropertyValueFactory<Generator, String>("startedDeliveryDate"));
        colDelivered.setCellValueFactory(new PropertyValueFactory<Generator, String>("deliveredDate"));
        colAccepted.setCellValueFactory(new PropertyValueFactory<Generator, String>("acceptedDate"));
        colEnd.setCellValueFactory(new PropertyValueFactory<Generator, String>("finishedDate"));

        colStatus.setCellValueFactory(new PropertyValueFactory<Generator, String>("status"));

        listOfLineUps = FXCollections.observableArrayList(lineUpList.getListOfLineUps());
        tvLineUps.setItems(listOfLineUps);
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Generator mouseGenerator = tvLineUps.getSelectionModel().getSelectedItem();

        ID = String.valueOf(mouseGenerator.getId());
        tfOrderId.setText(mouseGenerator.getOrderId());
        tfProduct.setText(mouseGenerator.getOrderProduct());
        tfUnits.setText(mouseGenerator.getOrderUnits());
        tfQuantity.setText(mouseGenerator.getOrderQuantity());
        tfContact.setText(mouseGenerator.getOrderContact());
        tfPhone.setText(mouseGenerator.getOrderPhone());
        tfAddress.setText(mouseGenerator.getOrderDelAddress());
        tfCity.setText(mouseGenerator.getOrderCity());
        tfCountry.setText(mouseGenerator.getOrderCountry());
        tfGoogleN.setText(mouseGenerator.getOrderGoogleN());
        tfGoogleE.setText(mouseGenerator.getOrderGoogleE());

        tfDriver.setText(mouseGenerator.getDriver());
        tfTruck.setText(mouseGenerator.getTruck());
        tfTrailer.setText(mouseGenerator.getTrailer());

        tfGenerated.setText(mouseGenerator.getGeneratedDate());
        tfStartDelivery.setText(mouseGenerator.getStartedDeliveryDate());
        tfDelivered.setText(mouseGenerator.getDeliveredDate());
        tfAccepted.setText(mouseGenerator.getAcceptedDate());
        tfDead.setText(mouseGenerator.getFinishedDate());

        tfStatus.setText(mouseGenerator.getStatus());

    }

    // Update Button action

    public void updateLineUp(ActionEvent event) throws TRUCKerListError {
        editLineUp();
    }

    private void editLineUp() throws TRUCKerListError {
        lineUpList.getLineUps();
        Generator lineUp = new Generator(Integer.parseInt(ID.toString()), tfOrderId.getText(), tfProduct.getText(),
                tfUnits.getText(), tfQuantity.getText(), tfContact.getText(), tfPhone.getText(), tfAddress.getText(),
                tfCity.getText(), tfCountry.getText(), tfGoogleN.getText(), tfGoogleE.getText(), tfDriver.getText(),
                tfTruck.getText(), tfTrailer.getText(), tfGenerated.getText(), tfStartDelivery.getText(),
                tfDelivered.getText(), tfAccepted.getText(), tfDead.getText(), tfStatus.getText());
        if (lineUp == null) {
            throw new TRUCKerListError("Fill requaered fields!");
        }
        lblError.setText("Making changes for egsisting Line-Up!");
        lineUpList.setLineUp(Integer.parseInt(ID.toString()), tfOrderId.getText(), tfProduct.getText(),
                tfUnits.getText(), tfQuantity.getText(), tfContact.getText(), tfPhone.getText(), tfAddress.getText(),
                tfCity.getText(), tfCountry.getText(), tfGoogleN.getText(), tfGoogleE.getText(), tfDriver.getText(),
                tfTruck.getText(), tfTrailer.getText(), tfGenerated.getText(), tfStartDelivery.getText(),
                tfDelivered.getText(), tfAccepted.getText(), tfDead.getText(), tfStatus.getText());
        lineUpList.reloadFile();
        lineUpList.getLineUps();
        makeTextEmpty();
        insertLineUpsToTable();
    }

    // Delete Button action

    public void deleteLineUp(ActionEvent event) throws TRUCKerListError {
        removeLineUp();
    }

    private void removeLineUp() throws TRUCKerListError {
        lineUpList.getLineUps();
        Generator lineUp = new Generator(Integer.parseInt(ID.toString()), tfOrderId.getText(), tfProduct.getText(),
                tfUnits.getText(), tfQuantity.getText(), tfContact.getText(), tfPhone.getText(), tfAddress.getText(),
                tfCity.getText(), tfCountry.getText(), tfGoogleN.getText(), tfGoogleE.getText(), tfDriver.getText(),
                tfTruck.getText(), tfTrailer.getText(), tfGenerated.getText(), tfStartDelivery.getText(),
                tfDelivered.getText(), tfAccepted.getText(), tfDead.getText(), tfStatus.getText());
        if (lineUp == null) {
            throw new TRUCKerListError("You need to show egsisting Line-Up to remove it!");
        }
        lblError.setText("Removing egsisting Line-Up. Reloading file!");
        lineUpList.removeFromList(lineUp);
        lineUpList.reloadFile();
        lineUpList.getLineUps();
        makeTextEmpty();
        insertLineUpsToTable();
    }

    public void makeTextEmpty() {
        tfOrderId.setText("");
        tfProduct.setText("");
        tfUnits.setText("");
        tfQuantity.setText("");
        tfContact.setText("");
        tfPhone.setText("");
        tfAddress.setText("");
        tfCity.setText("");
        tfCountry.setText("");
        tfGoogleN.setText("");
        tfGoogleE.setText("");
        tfDriver.setText("");
        tfTruck.setText("");
        tfTrailer.setText("");
        tfGenerated.setText("");
        tfStartDelivery.setText("");
        tfDelivered.setText("");
        tfAccepted.setText("");
        tfDead.setText("");
        tfStatus.setText("");
    }
    // Insert Button action

    public void insertLineUp(ActionEvent event) throws TRUCKerListError {
        addLineUp();
    }

    private void addLineUp() throws TRUCKerListError {
        lineUpList.getLineUps();
        Generator lineUp = new Generator(tfOrderId.getText(), tfProduct.getText(), tfUnits.getText(), tfQuantity.getText(),
                tfContact.getText(), tfPhone.getText(), tfAddress.getText(), tfCity.getText(), tfCountry.getText(),
                tfGoogleN.getText(), tfGoogleE.getText(), tfDriver.getText(), tfTruck.getText(),
                tfTrailer.getText(), tfGenerated.getText(), tfStartDelivery.getText(), tfDelivered.getText(),
                tfAccepted.getText(), tfDead.getText(), tfStatus.getText());
        if (lineUp == null) {
            throw new TRUCKerListError("Fill all the requiered fields to add Line-Up!");
        }
        lineUpList.addLineUpToList(lineUp);
        lineUpList.reloadFile();
        lineUpList.getLineUps();
        lblError.setText("Line-Up was Added! Reloading file!");
        makeTextEmpty();
        insertLineUpsToTable();
    }

    public void insertData(ActionEvent event) {
        addData();
    }

    private void addData() {
        DataClass data = new DataClass();
        String laikas = String.format("" + data.getDataNow());
        tfGenerated.setText(laikas);
    }

    public void LogOut(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("hello-view.fxml", 600, 300);
    }

    public void adminDrivers(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("adminDrivers.fxml", 1118, 450);
    }

    public void adminTrailers(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("adminTrailers.fxml", 1308, 450);
    }

    public void adminTrucks(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("adminTrucks.fxml", 1174, 543);
    }

    public void adminOrders(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("adminPage.fxml", 1389, 764);
    }

    public void adminUsers(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("adminUsers.fxml", 630, 490);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lineUpList = new GeneratorList();
        orderList = new OrderList();
        driverList = new DriverList();
        truckList = new TruckList();
        trailerList = new TrailerList();
        try {
            cbOrder.getItems().addAll(orderList.ListOfOrdersFullInfo());
            cbDriver.getItems().addAll(driverList.ListOfDriversName());
            cbTruck.getItems().addAll(truckList.ListOfTrucksShortInfo());
            cbTrailer.getItems().addAll(trailerList.ListOfTrailersShortInfo());
            cbStatus.getItems().addAll(status);
        } catch (TRUCKerListError e) {
            e.printStackTrace();
        }

        cbOrder.setOnAction(this::getOrder);
        cbDriver.setOnAction(this::getDriver);
        cbTruck.setOnAction(this::getTruck);
        cbTrailer.setOnAction(this::getTrailer);
        cbStatus.setOnAction(this::getStatus);
        insertLineUpsToTable();
    }

    public void getOrder(ActionEvent event)  {

        String myOrder = cbOrder.getValue();
        String[] orderInfo = myOrder.split(",");

        tfOrderId.setText(orderInfo[0].trim());
        tfProduct.setText(orderInfo[1].trim());
        tfUnits.setText(orderInfo[2].trim());
        tfQuantity.setText(orderInfo[3].trim());
        tfContact.setText(orderInfo[4].trim());
        tfPhone.setText(orderInfo[5].trim());
        tfAddress.setText(orderInfo[6].trim());
        tfCity.setText(orderInfo[7].trim());
        tfCountry.setText(orderInfo[8].trim());
        tfGoogleN.setText(orderInfo[9].trim());
        tfGoogleE.setText(orderInfo[10].trim());
    }

    public void getDriver(ActionEvent event) {
        String myDriver = cbDriver.getValue();
        tfDriver.setText(myDriver);
    }

    public void getTruck(ActionEvent event) {
        String myTruck = cbTruck.getValue();
        tfTruck.setText(myTruck);
    }

    public void getTrailer(ActionEvent event) {
        String myTrailer = cbTrailer.getValue();
        tfTrailer.setText(myTrailer);
    }

    public void getStatus(ActionEvent event) {
        String myStatus = cbStatus.getValue();
        tfStatus.setText(myStatus);
    }
}
