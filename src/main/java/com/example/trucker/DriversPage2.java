package com.example.trucker;

import com.example.trucker.exceptions.TRUCKerListError;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DriversPage2 implements Initializable {

    public DriversPage2() {
    }

    private String selectedDriver;
    private String selectedTruck;
    private String selectedTrailer;

    private ArrayList<Generator> lineUps;
    GeneratorList lineUp;

    @FXML
    private Label lblDriver;
    @FXML
    private Label lblTruck;
    @FXML
    private Label lblTrailer;
    @FXML
    private Label lblOrderId;
    @FXML
    private Label lblProduct;
    @FXML
    private Label lblUnits;
    @FXML
    private Label lblQuantity;
    @FXML
    private Label lblContact;
    @FXML
    private Label lblPhone;
    @FXML
    private Label lblAddress;
    @FXML
    private Label lblCity;
    @FXML
    private Label lblCountry;
    @FXML
    private Label lblError;
    @FXML
    private TableView<Generator> tvLineUps;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colOrderId;
    @FXML
    private TableColumn colProduct;
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
    private Button btnStart;

    public static ObservableList<Generator> listOfLineUps;

    public void startDelivery(ActionEvent event) throws TRUCKerListError, IOException {

        String orderId = lblOrderId.getText();
        String driver = lblDriver.getText();
        String truck = lblTruck.getText();
        String trailer = lblTrailer.getText();
        String product = lblProduct.getText();
        String contact = lblContact.getText();
        String address = lblAddress.getText();

        if (orderId.isEmpty() || driver.isEmpty() || truck.isEmpty() || trailer.isEmpty() || product.isEmpty() ||
                contact.isEmpty() || address.isEmpty()) {
            lblError.setText("Please select the order to deliver!");

        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("driversPage3.fxml"));
            Parent driverPageViewParent = loader.load();

            Scene driverPageViewScene = new Scene(driverPageViewParent);

            //access the controller and call a method
            DriversPage3 controller = loader.getController();
            controller.initData3(orderId);


            //This line gets the Stage information

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(driverPageViewScene);
            window.setWidth(1210);
            window.setHeight(620);
            window.centerOnScreen();
            window.show();
        }
    }

        @FXML
        private void handleMouseAction (MouseEvent event){
            Generator mouseGenerator = tvLineUps.getSelectionModel().getSelectedItem();

            lblOrderId.setText(mouseGenerator.getOrderId());
            lblProduct.setText(mouseGenerator.getOrderProduct());
            lblUnits.setText(mouseGenerator.getOrderUnits());
            lblQuantity.setText(mouseGenerator.getOrderQuantity());
            lblContact.setText(mouseGenerator.getOrderContact());
            lblPhone.setText(mouseGenerator.getOrderPhone());
            lblAddress.setText(mouseGenerator.getOrderDelAddress());
            lblCity.setText(mouseGenerator.getOrderCity());
            lblCountry.setText(mouseGenerator.getOrderCountry());

        }


        public void initData2 (String driver, String truck, String trailer) throws TRUCKerListError {
            selectedDriver = driver;
            lblDriver.setText(selectedDriver);
            selectedTruck = truck;
            lblTruck.setText(selectedTruck);
            selectedTrailer = trailer;
            lblTrailer.setText(selectedTrailer);

        }

        public void initialize (URL url, ResourceBundle resourceBundle){
            lineUp = new GeneratorList();

            insertLineUpToTable();

        }

        public void insertLineUpToTable () {

        }

        public void insertLineUpToTable2 (String driver, String truck, String trailer) throws TRUCKerListError {

            System.out.println("Loading table");

            lineUp.getListLineUpsWhoMachesDriverTruckTrailer(driver, truck, trailer).forEach(
                    lu -> System.out.println(lu.getId() + "," + lu.getOrderId() + "," + lu.getOrderProduct() + "," +
                            lu.getOrderUnits() + "," + lu.getOrderQuantity() + "," + lu.getOrderContact() + "," +
                            lu.getOrderPhone() + "," + lu.getOrderDelAddress() + "," + lu.getOrderCity() + "," +
                            lu.getOrderCountry()));

            colId.setCellValueFactory(new PropertyValueFactory<Generator, String>("id"));
            colOrderId.setCellValueFactory(new PropertyValueFactory<Generator, String>("orderId"));
            colProduct.setCellValueFactory(new PropertyValueFactory<Generator, String>("orderProduct"));
            colUnits.setCellValueFactory(new PropertyValueFactory<Generator, String>("orderUnits"));
            colQuantity.setCellValueFactory(new PropertyValueFactory<Generator, String>("orderQuantity"));
            colContact.setCellValueFactory(new PropertyValueFactory<Generator, String>("orderContact"));
            colPhone.setCellValueFactory(new PropertyValueFactory<Generator, String>("orderPhone"));
            colDelAddress.setCellValueFactory(new PropertyValueFactory<Generator, String>("orderDelAddress"));
            colCity.setCellValueFactory(new PropertyValueFactory<Generator, String>("orderCity"));
            colCountry.setCellValueFactory(new PropertyValueFactory<Generator, String>("orderCountry"));

            listOfLineUps = FXCollections.observableArrayList(lineUp.getListLineUpsWhoMachesDriverTruckTrailer(driver, truck, trailer));

            tvLineUps.setItems(listOfLineUps);
        }
    }
