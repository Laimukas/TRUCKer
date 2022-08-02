package com.example.trucker;

import com.example.trucker.exceptions.TRUCKerListError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// ** tvOrders reik sutvarkyti,reik kad ismestu jau ivykdytus vairuotojo pristatymus,nepriklausomai pagal pasirinkta technika
//    cia svarbiausia koks vairuotojas prisijunge
// ** btnDelete sutvarkyti,galimybe istrinti ivykdyta uzsakyma.

public class DriversPage implements Initializable {

    private Driver selectedDriver;
    private TruckList truckList;
    private TrailerList trailerList;

    @FXML
    private Label lblName;
    @FXML
    private Label lblError;
    @FXML
    private TextField tfTruck;
    @FXML
    private TextField tfTrailer;
    @FXML
    private ChoiceBox<String> cbTrucks;
    @FXML
    private ChoiceBox<String> cbTrailers;
    @FXML
    private TableView<Order> tvMadeOrders;
    @FXML
    private Button btnBegin;
    @FXML
    private Button btnDelete;
    @FXML
    private Button logout;

    public void initData(Driver driver)
    {
        selectedDriver = driver;
        lblName.setText(selectedDriver.getName());
    }

    public void letsBegin(ActionEvent event) throws TRUCKerListError, IOException {

        String driver = lblName.getText();
        String truck = tfTruck.getText();
        String trailer = tfTrailer.getText();

        if (driver.isEmpty() || truck.isEmpty()||trailer.isEmpty()) {
            lblError.setText("Please fill all required fields!");

        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("driversPage2.fxml"));
            Parent driverPageViewParent = loader.load();

            Scene driverPageViewScene = new Scene(driverPageViewParent);

            //access the controller and call a method
            DriversPage2 controller = loader.getController();
            controller.initData2(driver,truck,trailer);
            controller.insertLineUpToTable2(driver,truck,trailer);

            //This line gets the Stage information

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(driverPageViewScene);
            window.setWidth(1210);
            window.setHeight(620);
            window.centerOnScreen();
            window.show();
        }
    }

    public void driverLogOut(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("hello-view.fxml",600,300);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        truckList = new TruckList();
        trailerList=new TrailerList();
        try {
            cbTrucks.getItems().addAll(truckList.ListOfTrucksShortInfo());
            cbTrailers.getItems().addAll(trailerList.ListOfTrailersShortInfo());
        } catch (TRUCKerListError e) {
            e.printStackTrace();
        }
        cbTrucks.setOnAction(this::getTruck);
        cbTrailers.setOnAction(this::getTrailer);
//        insertMadeOrdersToTable();
    }

    public void getTruck(ActionEvent event){
        String myTruck = cbTrucks.getValue();
        tfTruck.setText(myTruck);
    }
    public void getTrailer(ActionEvent event){
        String myTrailer = cbTrailers.getValue();
        tfTrailer.setText(myTrailer);
    }
}
