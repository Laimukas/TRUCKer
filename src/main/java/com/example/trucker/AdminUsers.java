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

public class AdminUsers implements Initializable {

    private UserList userList;
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfPassword;
    @FXML
    private TableView<User> tvUsers;
    @FXML
    private TableColumn colID;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colPass;
    @FXML
    private Button logout;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnDrivers;
    @FXML
    private Button btnTrucks;
    @FXML
    private Button btnTrailers;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnGenerator;
    @FXML
    private Label lblnote;

    private String ID;

    public static ObservableList<User> listOfUsers;

    public void insertUsersToTable() {
        userList = new UserList();
        try {
            userList.getUsers();
            userList.sorting();
        } catch (TRUCKerListError e) {
            throw new RuntimeException(e);
        }

        System.out.println("Loading table");

        userList.getUserList().forEach(el -> System.out.println(el.getId() + "," + el.getUserName() + "," + el.getPassword()));

        colID.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        colPass.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        listOfUsers = FXCollections.observableArrayList(userList.getUserList());
        tvUsers.setItems(listOfUsers);
    }


// Update Button action

    public void updateUser(ActionEvent event) throws TRUCKerListError, IOException {
        editUser();
    }

    private void editUser() throws TRUCKerListError, IOException {
        userList.getUsers();
        User newUser = new User(Integer.parseInt(ID.toString()), tfUsername.getText(), tfPassword.getText());
        if (newUser == null) {
            throw new TRUCKerListError("Fill requaered fields!");
        }
        lblnote.setText("Making changes for egsisting user!\nKeep in mind this doesn't change Drivers list.");
        userList.setUser(Integer.parseInt(ID.toString()), tfUsername.getText(), tfPassword.getText());
        userList.reloadFile();
        userList.getUsers();
        userList.sorting();
        makeTextEmpty();
        insertUsersToTable();
    }

    // Delete Button action

    public void deleteUser(ActionEvent event) throws TRUCKerListError, IOException {
        removeUser();
    }

    private void removeUser() throws TRUCKerListError, IOException {
        userList.getUsers();
        User user = new User(Integer.parseInt(ID.toString()), tfUsername.getText(), tfPassword.getText());
        if (user == null) {
            throw new TRUCKerListError("You need to show egsisting user to remove it!");
        }
        lblnote.setText("Removing egsisting user.\nKeep in mind this doesn't change Drivers list.");
        userList.removeFromList(user);
        userList.reloadFile();
        userList.getUsers();
        userList.sorting();
        makeTextEmpty();
        insertUsersToTable();
    }

    public void makeTextEmpty() {
        tfId.setText("");
        tfUsername.setText("");
        tfPassword.setText("");
    }

    // Insert Button action

    public void insertUser(ActionEvent event) throws TRUCKerListError, IOException {
        addUser();
    }
    private void addUser() throws TRUCKerListError, IOException {
        userList.getUsers();
        userList.sorting();
        User user = new User(tfUsername.getText(), tfPassword.getText());
        if (user == null) {
            throw new TRUCKerListError("Fill all the requiered fields to add user!");
        }
        userList.addUserToList(user);
        userList.reloadFile();
        userList.getUsers();
        userList.sorting();
        lblnote.setText("User was Added! Reloading file!" +
                "\nKeep in mind this doesn't change Drivers list.");
        makeTextEmpty();
        insertUsersToTable();
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

    public void adminDrivers(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("adminDrivers.fxml", 1118, 450);
    }

    public void adminOrders(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("adminPage.fxml", 1389, 754);
    }

    public void adminGenerator(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("adminGenerator.fxml", 1600, 800);
    }

    @FXML
    private void handleMouseAction(MouseEvent event){
       User mouseUser = tvUsers.getSelectionModel().getSelectedItem();
        ID=String.valueOf(mouseUser.getId());
       tfId.setText(String.valueOf(mouseUser.getId()));
       tfUsername.setText(mouseUser.getUserName());
       tfPassword.setText(mouseUser.getPassword());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        userList = new UserList();
        insertUsersToTable();

    }
}
