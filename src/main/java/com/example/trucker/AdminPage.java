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

public class AdminPage implements Initializable {

    private OrderList orderList;
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfProduct;
    @FXML
    private TextField tfUnits;
    @FXML
    private TextField tfQuantity;
    @FXML
    private TextField tfCompany;
    @FXML
    private TextField tfCompanyID;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfCity;
    @FXML
    private TextField tfDeliveryAddress;
    @FXML
    private TextField tfState;
    @FXML
    private TextField tfZip;
    @FXML
    private TextField tfCountry;
    @FXML
    private TextField tfGooglN;
    @FXML
    private TextField tfGooglE;
    @FXML
    private TextField tfContact;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextArea taOther;
    @FXML
    private TableView<Order> tvOrders;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colProduct;
    @FXML
    private TableColumn colUnits;
    @FXML
    private TableColumn colQuantity;
    @FXML
    private TableColumn colCompany;
    @FXML
    private TableColumn colCompanyId;
    @FXML
    private TableColumn colEmail;
    @FXML
    private TableColumn colDeliveryAddress;
    @FXML
    private TableColumn colCity;
    @FXML
    private TableColumn colState;
    @FXML
    private TableColumn colZip;
    @FXML
    private TableColumn colCountry;
    @FXML
    private TableColumn colGooglN;
    @FXML
    private TableColumn colGooglE;
    @FXML
    private TableColumn colContact;
    @FXML
    private TableColumn colPhone;
    @FXML
    private TableColumn colOther;
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
    private Button btnUsers;
    @FXML
    private Button btnGenerator;
    @FXML
    private Label lbError;

    public AdminPage() {
    }

    @FXML
    private void handleButtonAction(ActionEvent event)throws IOException{
        System.out.println("You clicked me!");
        lbError.setText("Hello World!");
    }

    public static ObservableList<Order> listOfOrders;

    public void insertOrdersToTable() {
        orderList = new OrderList();
        try {
            orderList.getOrders();
            orderList.sorting();
        } catch (TRUCKerListError e) {
            throw new RuntimeException(e);
        }

        System.out.println("Loading table");

        orderList.getListOfOrders().forEach(ol -> System.out.println(ol.getId() + "," + ol.getProduct() + "," +
                ol.getUnits()+ "," + ol.getQuantity() + "," + ol.getCompany()+ "," + ol.getCompanyId() + "," +
                ol.getEmail()+ "," + ol.getDeliveryAddress() + "," + ol.getCity()+ "," + ol.getState() + "," +
                ol.getZip()+ "," + ol.getCountry() + "," + ol.getGooglN()+ "," + ol.getGooglE() + "," + ol.getContact()+ "," +
                ol.getPhone() + "," + ol.getOther()));

        colId.setCellValueFactory(new PropertyValueFactory<Order, String>("id"));
        colProduct.setCellValueFactory(new PropertyValueFactory<Order, String>("product"));
        colUnits.setCellValueFactory(new PropertyValueFactory<Order, String>("units"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<Order, String>("quantity"));
        colCompany.setCellValueFactory(new PropertyValueFactory<Order, String>("company"));
        colCompanyId.setCellValueFactory(new PropertyValueFactory<Order, String>("companyId"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Order, String>("email"));
        colDeliveryAddress.setCellValueFactory(new PropertyValueFactory<Order, String>("deliveryAddress"));
        colCity.setCellValueFactory(new PropertyValueFactory<Order, String>("city"));
        colState.setCellValueFactory(new PropertyValueFactory<Order, String>("state"));
        colZip.setCellValueFactory(new PropertyValueFactory<Order, String>("zip"));
        colCountry.setCellValueFactory(new PropertyValueFactory<Order, String>("country"));
        colGooglN.setCellValueFactory(new PropertyValueFactory<Order, String>("googlN"));
        colGooglE.setCellValueFactory(new PropertyValueFactory<Order, String>("googlE"));
        colContact.setCellValueFactory(new PropertyValueFactory<Order, String>("contact"));
        colPhone.setCellValueFactory(new PropertyValueFactory<Order, String>("phone"));
        colOther.setCellValueFactory(new PropertyValueFactory<Order, String>("other"));

        listOfOrders = FXCollections.observableArrayList(orderList.getListOfOrders());
        tvOrders.setItems(listOfOrders);
    }

    @FXML
    private void handleMouseAction(MouseEvent event){
        Order mouseOrder = tvOrders.getSelectionModel().getSelectedItem();
        tfId.setText(mouseOrder.getId());
        tfProduct.setText(mouseOrder.getProduct());
        tfUnits.setText(mouseOrder.getUnits());
        tfQuantity.setText(mouseOrder.getQuantity());
        tfCompany.setText(mouseOrder.getCompany());
        tfCompanyID.setText(mouseOrder.getCompanyId());
        tfEmail.setText(mouseOrder.getEmail());
        tfDeliveryAddress.setText(mouseOrder.getDeliveryAddress());
        tfCity.setText(mouseOrder.getCity());
        tfState.setText(mouseOrder.getState());
        tfZip.setText(mouseOrder.getZip());
        tfCountry.setText(mouseOrder.getCountry());
        tfGooglN.setText(mouseOrder.getGooglN());
        tfGooglE.setText(mouseOrder.getGooglE());
        tfContact.setText(mouseOrder.getContact());
        tfPhone.setText(mouseOrder.getPhone());
        taOther.setText(mouseOrder.getOther());
    }

    // Update Button action

    public void updateOrder(ActionEvent event) throws TRUCKerListError, IOException {
        editOrder();
    }

    private void editOrder() throws TRUCKerListError, IOException {
        orderList.getOrders();
        Order newOrder = new Order(tfId.getText(), tfProduct.getText(), tfUnits.getText(),tfQuantity.getText(),
                tfCompany.getText(), tfCompanyID.getText(),tfEmail.getText(), tfDeliveryAddress.getText(), tfCity.getText(),
                tfState.getText(), tfZip.getText(), tfCountry.getText(),tfGooglN.getText(), tfGooglE.getText(),
                tfContact.getText(),tfPhone.getText(), taOther.getText());
        if (newOrder == null) {
            throw new TRUCKerListError("Fill requaered fields!");
        }
        lbError.setText("Making changes for.\negsiting user!");
        orderList.setOrder(tfId.getText(), tfProduct.getText(), tfUnits.getText(),tfQuantity.getText(),
                tfCompany.getText(), tfCompanyID.getText(),tfEmail.getText(), tfDeliveryAddress.getText(), tfCity.getText(),
                tfState.getText(), tfZip.getText(), tfCountry.getText(),tfGooglN.getText(), tfGooglE.getText(),
                tfContact.getText(),tfPhone.getText(), taOther.getText());
        orderList.reloadFile();
        orderList.getOrders();
        makeTextEmpty();
        insertOrdersToTable();
    }

    // Delete Button action

    public void deleteOrder(ActionEvent event) throws TRUCKerListError, IOException {
        removeOrder();
    }

    private void removeOrder() throws TRUCKerListError, IOException {
        orderList.getOrders();
        Order order = new Order(tfId.getText(), tfProduct.getText(), tfUnits.getText(),tfQuantity.getText(),
                tfCompany.getText(), tfCompanyID.getText(),tfEmail.getText(), tfDeliveryAddress.getText(), tfCity.getText(),
                tfState.getText(), tfZip.getText(), tfCountry.getText(),tfGooglN.getText(), tfGooglE.getText(),
                tfContact.getText(),tfPhone.getText(), taOther.getText());
        if (order == null) {
            throw new TRUCKerListError("You need to show egsisting order to remove it!");
        }
        lbError.setText("Removing egsisting user.\nReload file!");
        orderList.removeFromList(order);
        orderList.reloadFile();
        orderList.getOrders();
        makeTextEmpty();
        insertOrdersToTable();
    }

    public void makeTextEmpty() {
        tfId.setText("");tfProduct.setText("");tfUnits.setText("");tfQuantity.setText("");tfCompany.setText("");
        tfCompanyID.setText("");tfEmail.setText("");tfDeliveryAddress.setText("");tfCity.setText("");tfState.setText("");
        tfZip.setText("");tfCountry.setText("");tfGooglN.setText("");tfGooglE.setText("");tfContact.setText("");
        tfPhone.setText(""); taOther.setText("");
    }

    // Insert Button action

    public void insertOrder(ActionEvent event) throws TRUCKerListError, IOException {
        addOrder();

    }

    private void addOrder() throws TRUCKerListError, IOException {
        orderList.getOrders();
        Order order = new Order(tfId.getText(), tfProduct.getText(), tfUnits.getText(),tfQuantity.getText(),
                tfCompany.getText(), tfCompanyID.getText(),tfEmail.getText(), tfDeliveryAddress.getText(), tfCity.getText(),
                tfState.getText(), tfZip.getText(), tfCountry.getText(),tfGooglN.getText(), tfGooglE.getText(),
                tfContact.getText(),tfPhone.getText(), taOther.getText());
        if (order == null) {
            throw new TRUCKerListError("Fill all the requiered fields to add order!");
        }
        orderList.addOrderToList(order);
        orderList.reloadFile();
        orderList.getOrders();
        lbError.setText("Order was Added! Reload file!");
        makeTextEmpty();
        insertOrdersToTable();
    }

    public void adminUsers(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("adminUsers.fxml",630,490);
    }
    public void adminTrailers(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("adminTrailers.fxml",1308, 450);
    }

    public void adminTrucks(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("adminTrucks.fxml",1174, 543);
    }

    public void adminDrivers(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("adminDrivers.fxml",1118,450);
    }

    public void adminGenerator(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("adminGenerator.fxml", 1600, 800);
    }

    public void adminLogOut(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("hello-view.fxml",600,300);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderList = new OrderList();
        insertOrdersToTable();
    }
}
