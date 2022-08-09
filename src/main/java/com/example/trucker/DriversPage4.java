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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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
    @FXML
    private Label lblError;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfPosition;
    @FXML
    private TextArea taNote;

    public void initData4(String id) throws TRUCKerListError {

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

    public void signTheOrder(ActionEvent event) throws TRUCKerListError, IOException {


        String orderId = lblOrderId.getText();
        String driver = lblDriver.getText();
        String truck = lblTruck.getText();
        String trailer = lblTrailer.getText();
        Generator lineUpWeGet = lineUps.getLineUpByOrdersId(orderId);
        Order orderWeGet = orders.getOrderById(orderId);
        DataClass data = new DataClass();
        String laikas = String.format("" + data.getDataNow());

        //Kuriam nauja Finished Order irasa
        finishedOrders.getFinishedOrders();

        FinishedOrder newFinishedOrder= new FinishedOrder(String.valueOf(lineUpWeGet.getId()),String.valueOf(orderWeGet.getId()),
                lineUpWeGet.getOrderProduct(),lineUpWeGet.getOrderUnits(),lineUpWeGet.getOrderQuantity(),orderWeGet.getCompany(),
                orderWeGet.getCompanyId(),orderWeGet.getEmail(),orderWeGet.getContact(),orderWeGet.getPhone(),lineUpWeGet.getOrderDelAddress(),
                lineUpWeGet.getOrderCity(),orderWeGet.getState(),lineUpWeGet.getOrderCountry(),lineUpWeGet.getOrderGoogleN(),
                lineUpWeGet.getOrderGoogleE(),lineUpWeGet.getDriver(),lineUpWeGet.getTruck(),lineUpWeGet.getTrailer(),lineUpWeGet.getGeneratedDate(),
                lineUpWeGet.getStartedDeliveryDate(),lineUpWeGet.getDeliveredDate(),laikas,laikas,"finished",
                tfName.getText(),tfPosition.getText(),taNote.getText());

        finishedOrders.addFinishedOrderToList(newFinishedOrder);
        finishedOrders.reloadFile();
        finishedOrders.getFinishedOrders();
        lblError.setText("Finished Order was Added! Reloading file!");

        lineUps.getLineUps();
        lineUps.removeFromList(lineUpWeGet);  //pirmiau susikursim finished order ir tada patikrinus sita atidarysim
        lineUps.reloadFile();
        lineUps.getLineUps();
        orders.getOrders();
        orders.removeFromList(orderWeGet);  //pirmiau susikursim finished order ir tada patikrinus sita atidarysim
        orders.reloadFile();
        orders.getOrders();


        if (orderId.isEmpty() || driver.isEmpty() || truck.isEmpty() || trailer.isEmpty()) {
            lblError.setText("Please fill requaered fields!");

        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("driversPage2.fxml"));
            Parent driverPageViewParent = loader.load();

            Scene driverPageViewScene = new Scene(driverPageViewParent);

            //access the controller and call a method
            DriversPage2 controller = loader.getController();
            controller.initData2(driver, truck, trailer);
            controller.insertLineUpToTable2(driver, truck, trailer);

            //This line gets the Stage information

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(driverPageViewScene);
            window.setWidth(1210);
            window.setHeight(620);
            window.centerOnScreen();
            window.show();
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

        finishedOrders = new FinishedOrderList();
        lineUps = new GeneratorList();
        orders = new OrderList();

    }


}
