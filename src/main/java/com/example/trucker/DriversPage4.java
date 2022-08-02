package com.example.trucker;

import com.example.trucker.exceptions.TRUCKerListError;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DriversPage4 implements Initializable {

    FinishedOrderList finishedOrders;
    GeneratorList lineUps;
    OrderList orders;

    @FXML
    private Label lblOrderId;
    @FXML
    private Label lblProduct;
    @FXML
    private Label lblUnits;
    @FXML
    private Label lblQuantity;
    @FXML
    private Label lblCompany;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblContact;
    @FXML
    private Label lblPhone;
    @FXML
    private Label lblDriver;
    @FXML
    private Label lblTruck;
    @FXML
    private Label lblTrailer;
    @FXML
    private Label lblAddress;
    @FXML
    private Label lblCity;
    @FXML
    private Label lblCountry;
    @FXML
    private Label lblGeneratedDate;
    @FXML
    private Label lblStartedDeliveryDate;
    @FXML
    private Label lblDeliveredDate;

    public void initData4 (String id) throws TRUCKerListError {

        Generator lineUpWeGet = lineUps.getLineUpByOrdersId(id);
        Order orderWeGet = orders.getOrderById(id);

        DataClass data = new DataClass();
        String laikas = String.format("" + data.getDataNow());
        lineUpWeGet.setDeliveredDate(laikas);
        lineUpWeGet.setStatus("delivered");
        System.out.println(lineUpWeGet);
        lineUps.setLineUp(lineUpWeGet);

        lblOrderId.setText(lineUpWeGet.getOrderId());
        lblProduct.setText(lineUpWeGet.getOrderProduct());
        lblUnits.setText(lineUpWeGet.getOrderUnits());
        lblQuantity.setText(lineUpWeGet.getOrderQuantity());
        lblCompany.setText(orderWeGet.getCompany());
        lblEmail.setText(orderWeGet.getEmail());
        lblContact.setText(lineUpWeGet.getOrderContact());
        lblPhone.setText(lineUpWeGet.getOrderPhone());
        lblAddress.setText(lineUpWeGet.getOrderDelAddress());
        lblCity.setText(lineUpWeGet.getOrderCity());
        lblCountry.setText(lineUpWeGet.getOrderCountry());
        lblGeneratedDate.setText(lineUpWeGet.getGeneratedDate());
        lblStartedDeliveryDate.setText(lineUpWeGet.getStartedDeliveryDate());
        lblDeliveredDate.setText(lineUpWeGet.getDeliveredDate());
        lblDriver.setText(lineUpWeGet.getDriver());
        lblTruck.setText(lineUpWeGet.getTruck());
        lblTrailer.setText(lineUpWeGet.getTrailer());

    }

    public void initialize (URL url, ResourceBundle resourceBundle){

        finishedOrders = new FinishedOrderList();
        lineUps = new GeneratorList();
        orders = new OrderList();

    }


}
