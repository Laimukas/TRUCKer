package com.example.trucker;

import com.example.trucker.exceptions.TRUCKerListError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DriversPage3 implements Initializable {

    private String selectedDriver;
    private String selectedTruck;
    private String selectedTrailer;
    private String selectedStart;
    private String selectedCompany;
    private String selectedContact;
    private String selectedPhone;
    private String selectedAddress;
    private String selectedCity;
    private String selectedCountry;

    GeneratorList lineUp;
    OrderList orders;

    @FXML
    private Label lblError;
    @FXML
    private Label lblOrderId;
    @FXML
    private Label lblDriver;
    @FXML
    private Label lblTruck;
    @FXML
    private Label lblTrailer;
    @FXML
    private Label lblStart;
    @FXML
    private Label lblCompany;
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

    public void arrival(ActionEvent event) throws TRUCKerListError, IOException {

        String orderId = lblOrderId.getText();
        String driver = lblDriver.getText();
        String truck = lblTruck.getText();
        String trailer = lblTrailer.getText();
        String contact = lblContact.getText();
        String address = lblAddress.getText();

        if (orderId.isEmpty() || driver.isEmpty() || truck.isEmpty() || trailer.isEmpty() ||
                contact.isEmpty() || address.isEmpty()) {
            lblError.setText("We missing info to make Delivery Arrived,contact the Manager!");

        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("driversPage4.fxml"));
            Parent driverPageViewParent = loader.load();

            Scene driverPageViewScene = new Scene(driverPageViewParent);

            //access the controller and call a method
            DriversPage4 controller = loader.getController();
            controller.initData4(orderId);


            //This line gets the Stage information

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(driverPageViewScene);
            window.setWidth(1210);
            window.setHeight(630);
            window.centerOnScreen();
            window.show();
        }
    }

    public void initData3 (String id) throws TRUCKerListError {

        Generator lineUpWeGet = lineUp.getLineUpByOrdersId(id);
        Order orderWeGet = orders.getOrderById(id);

        DataClass data = new DataClass();
        String laikas = String.format("" + data.getDataNow());
        lineUpWeGet.setStartedDeliveryDate(laikas);
        lineUpWeGet.setStatus("on the way");
        System.out.println(lineUpWeGet);
        lineUp.setLineUp(lineUpWeGet);

        lblOrderId.setText(id);
        selectedDriver = lineUpWeGet.getDriver();
        lblDriver.setText(selectedDriver);
        selectedTruck = lineUpWeGet.getTruck();
        lblTruck.setText(selectedTruck);
        selectedTrailer = lineUpWeGet.getTrailer();
        lblTrailer.setText(selectedTrailer);
        selectedStart = lineUpWeGet.getStartedDeliveryDate();
        lblStart.setText(selectedStart);
        selectedCompany = orderWeGet.getCompany();
        lblCompany.setText(selectedCompany);
        selectedContact = lineUpWeGet.getOrderContact();
        lblContact.setText(selectedContact);
        selectedPhone = lineUpWeGet.getOrderPhone();
        lblPhone.setText(selectedPhone);
        selectedAddress = lineUpWeGet.getOrderDelAddress();
        lblAddress.setText(selectedAddress);
        selectedCity = lineUpWeGet.getOrderCity();
        lblCity.setText(selectedCity);
        selectedCountry = lineUpWeGet.getOrderCountry();
        lblCountry.setText(selectedCountry);

    }

    public void initialize (URL url, ResourceBundle resourceBundle){
        lineUp = new GeneratorList();
        orders = new OrderList();

    }
}
