package com.example.trucker;

import com.example.trucker.exceptions.TRUCKerListError;
import javafx.application.Application;
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

public class AdminDrivers implements Initializable {

    private DriverList driverList;
    private UserList userList;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfSurname;
    @FXML
    private TextField tfBirth;
    @FXML
    private TextArea taAddress;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPassword;
    @FXML
    private TableView<Driver> tvDrivers;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colSurname;
    @FXML
    private TableColumn colDateOfBirth;
    @FXML
    private TableColumn colAddress;
    @FXML
    private TableColumn colPhone;
    @FXML
    private TableColumn colEmail;
    @FXML
    private TableColumn colPassword;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnTrucks;
    @FXML
    private Button btnTrailers;
    @FXML
    private Button btnUsers;
    @FXML
    private Button btnGenerate;
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
    private String userID;

    public AdminDrivers() {

    }

    public static ObservableList<Driver> listOfDrivers;

    public void insertDriversToTable() {
        driverList = new DriverList();
        try {
            driverList.getDrivers();
            driverList.sorting();
        } catch (TRUCKerListError e) {
            throw new RuntimeException(e);
        }

        System.out.println("Loading table");

        driverList.getListOfDrivers().forEach(dl -> System.out.println(dl.getId() + "," + dl.getName() + "," +
                dl.getSurname() + "," + dl.getBirth() + "," + dl.getAddress() + "," + dl.getPhone() + "," +
                dl.getEmail() + "," + dl.getPsw()));

        colId.setCellValueFactory(new PropertyValueFactory<Driver, String>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Driver, String>("name"));
        colSurname.setCellValueFactory(new PropertyValueFactory<Driver, String>("surname"));
        colDateOfBirth.setCellValueFactory(new PropertyValueFactory<Driver, String>("birth"));
        colAddress.setCellValueFactory(new PropertyValueFactory<Driver, String>("address"));
        colPhone.setCellValueFactory(new PropertyValueFactory<Driver, String>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Driver, String>("email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<Driver, String>("psw"));


        listOfDrivers = FXCollections.observableArrayList(driverList.getListOfDrivers());
        tvDrivers.setItems(listOfDrivers);
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Driver mouseDriver = tvDrivers.getSelectionModel().getSelectedItem();
        ID = String.valueOf(mouseDriver.getId());
        tfName.setText(mouseDriver.getName());
        tfSurname.setText(mouseDriver.getSurname());
        tfBirth.setText(mouseDriver.getBirth());
        taAddress.setText(mouseDriver.getAddress());
        tfPhone.setText(mouseDriver.getPhone());
        tfEmail.setText(mouseDriver.getEmail());
        tfPassword.setText(mouseDriver.getPsw());
    }

    // Update Button action

    public void updateDriver(ActionEvent event) throws TRUCKerListError, IOException {
        editDriver();
    }

    private void editDriver() throws TRUCKerListError, IOException {
        driverList.getDrivers();
        Driver driver = driverList.getOneDriver(tfName.getText(), tfSurname.getText(), tfBirth.getText(),
                taAddress.getText(), tfPhone.getText(), tfEmail.getText(), tfPassword.getText());
        if (driver == null) {
            throw new TRUCKerListError("Fill requaered fields!");
        }

        // it doesnt make any sence to try make changes in user list either because you need at least id of user
        // and we dont get it from this table,maybe later we can try to sort this

//        userList.getUsers();
//        User newUser = userList.getOneUser(tfSurname.getText(), tfPassword.getText());
//        if (newUser == null) {
//            throw new TRUCKerListError("Fill requaered fields!");
//        }
        driverList.setDriver(Integer.parseInt(ID.toString()), tfName.getText(), tfSurname.getText(), tfBirth.getText(),
                taAddress.getText(), tfPhone.getText(), tfEmail.getText(), tfPassword.getText());
        driverList.reloadFile();
        driverList.getDrivers();
//        userList.setUser(Integer.parseInt(userID.toString()), tfSurname.getText(), tfPassword.getText());
//        userList.reloadFile();
//        userList.getUsers();
        lblError.setText("Making changes for egsisting driver, keep in mind if you change username or password you need to change them in user menu either!");
        makeTextEmpty();
        insertDriversToTable();
    }

    // Delete Button action

    public void deleteDriver(ActionEvent event) throws TRUCKerListError, IOException {
        removeDriver();
    }

    private void removeDriver() throws TRUCKerListError, IOException {
        driverList.getDrivers();
        Driver driver = driverList.getOneDriver(tfName.getText(), tfSurname.getText(), tfBirth.getText(),
                taAddress.getText(), tfPhone.getText(), tfEmail.getText(), tfPassword.getText());
        if (driver == null) {
            throw new TRUCKerListError("You need to show egsisting driver to remove it!");
        }
        userList.getUsers();
        User user = userList.getOneUser(tfSurname.getText(), tfPassword.getText());
        if (user == null) {
            throw new TRUCKerListError("You need to show egsisting user to remove it!");
        }
        driverList.removeFromList(driver);
        driverList.reloadFile();
        driverList.getDrivers();
        userList.removeFromList(user);
        userList.reloadFile();
        userList.getUsers();
        userList.sorting();
        lblError.setText("Removing egsisting driver and user. Reload file!");
        makeTextEmpty();
        insertDriversToTable();
    }

    // to remove text from textField use makeTextEmpty

    public void makeTextEmpty() {
        tfName.setText("");
        tfSurname.setText("");
        tfBirth.setText("");
        taAddress.setText("");
        tfPhone.setText("");
        tfEmail.setText("");
        tfPassword.setText("");
    }

    // Insert Button action

    public void insertDriver(ActionEvent event) throws TRUCKerListError, IOException {
        addDriver();
    }

    private void addDriver() throws TRUCKerListError, IOException {
        driverList.getDrivers();
        Driver driver = new Driver(tfName.getText(), tfSurname.getText(), tfBirth.getText(),
                taAddress.getText(), tfPhone.getText(), tfEmail.getText(), tfPassword.getText());
        if (driver == null) {
            throw new TRUCKerListError("Fill all the requiered fields to add driver!");
        }
        userList.getUsers();
        User user = new User(tfSurname.getText(), tfPassword.getText());
        if (user == null) {
            throw new TRUCKerListError("Fill all the requiered fields to add user!");
        }
        driverList.addDriverToList(driver);
        driverList.reloadFile();
        driverList.getDrivers();
        userList.addUserToList(user);
        userList.reloadFile();
        userList.getUsers();

        makeTextEmpty();
        lblError.setText("Driver and User was Added! Reloading file!");
        insertDriversToTable();
    }

    // Buttons with link, for ex. LogOut, Open Trailers Page, etc.

    public void adminLogOut(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("hello-view.fxml", 600, 300);
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

    public void adminGenerator(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("adminGenerator.fxml", 1600, 800);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        driverList = new DriverList();
        userList = new UserList();
        insertDriversToTable();
    }
}
