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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
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
    private FinishedOrderList finishedOrderList;

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
    private TableView<FinishedOrder> tvMadeOrders;
    @FXML
    private TableColumn col_id;
    @FXML
    private TableColumn col_gen_id;
    @FXML
    private TableColumn col_ord_id;
    @FXML
    private TableColumn col_product;
    @FXML
    private TableColumn col_units;
    @FXML
    private TableColumn col_quantity;
    @FXML
    private TableColumn col_company;
    @FXML
    private TableColumn col_contact;
    @FXML
    private TableColumn col_phone;
    @FXML
    private TableColumn col_address;
    @FXML
    private TableColumn col_city;
    @FXML
    private TableColumn col_country;
    @FXML
    private TableColumn col_started;
    @FXML
    private TableColumn col_finished;
    @FXML
    private TableColumn col_signed;
    @FXML
    private TableColumn col_person_signed;
    @FXML
    private TableColumn col_truck;
    @FXML
    private TableColumn col_trailer;
    @FXML
    private TableColumn col_status;
    @FXML
    private Button btnBegin;
    @FXML
    private Button btnDelete;
    @FXML
    private Button logout;

    public static ObservableList<FinishedOrder> listOfFinishedOrders;



    public void initData(Driver driver) throws TRUCKerListError {
        selectedDriver = driver;
        lblName.setText(selectedDriver.getName());
        insertFinishedOrdersToTable(selectedDriver.getName());
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
        insertLineUpToTable();
//        insertMadeOrdersToTable();
    }

    public void insertLineUpToTable () {

    }

    public void getTruck(ActionEvent event){
        String myTruck = cbTrucks.getValue();
        tfTruck.setText(myTruck);
    }
    public void getTrailer(ActionEvent event){
        String myTrailer = cbTrailers.getValue();
        tfTrailer.setText(myTrailer);
    }
    public void insertFinishedOrdersToTable(String driver) throws TRUCKerListError {
        finishedOrderList = new FinishedOrderList();
        try {
            finishedOrderList.getListFinishedOrdersWhoMachesDriver(driver);
        } catch (TRUCKerListError e) {
            throw new RuntimeException(e);
        }

        System.out.println("Loading table");

        finishedOrderList.getListFinishedOrdersWhoMachesDriver(driver).forEach(tl -> System.out.println(tl.getId() + "," + tl.getGeneratorId() + "," +
                tl.getOrderId()+ "," + tl.getProduct() + "," + tl.getUnits()+ "," + tl.getQuantity() + "," +
                tl.getCompany()+ "," + tl.getContact()+ "," + tl.getPhone()+ "," + tl.getDeliveryAddress()+ "," + tl.getCity()+ "," +
                tl.getCountry()+ "," + tl.getStartedDeliveryDate()+ "," + tl.getFinishedDate()+ "," + tl.getAcceptedDate()+ "," +
                tl.getSignedName()+ "," + tl.getTruck()+ "," + tl.getTrailer()+ "," + tl.getStatus()));

        col_id.setCellValueFactory(new PropertyValueFactory<FinishedOrder, String>("id"));
        col_gen_id.setCellValueFactory(new PropertyValueFactory<FinishedOrder, String>("generatorId"));
        col_ord_id.setCellValueFactory(new PropertyValueFactory<FinishedOrder, String>("orderId"));
        col_product.setCellValueFactory(new PropertyValueFactory<FinishedOrder, String>("product"));
        col_units.setCellValueFactory(new PropertyValueFactory<FinishedOrder, String>("units"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<FinishedOrder, String>("quantity"));
        col_company.setCellValueFactory(new PropertyValueFactory<FinishedOrder, String>("company"));
        col_contact.setCellValueFactory(new PropertyValueFactory<FinishedOrder, String>("contact"));
        col_phone.setCellValueFactory(new PropertyValueFactory<FinishedOrder, String>("phone"));
        col_address.setCellValueFactory(new PropertyValueFactory<FinishedOrder, String>("deliveryAddress"));
        col_city.setCellValueFactory(new PropertyValueFactory<FinishedOrder, String>("city"));
        col_country.setCellValueFactory(new PropertyValueFactory<FinishedOrder, String>("country"));
        col_started.setCellValueFactory(new PropertyValueFactory<FinishedOrder, String>("startedDeliveryDate"));
        col_finished.setCellValueFactory(new PropertyValueFactory<FinishedOrder, String>("acceptedDate"));
        col_signed.setCellValueFactory(new PropertyValueFactory<FinishedOrder, String>("finishedDate"));
        col_person_signed.setCellValueFactory(new PropertyValueFactory<FinishedOrder, String>("signedName"));
        col_truck.setCellValueFactory(new PropertyValueFactory<FinishedOrder, String>("truck"));
        col_trailer.setCellValueFactory(new PropertyValueFactory<FinishedOrder, String>("trailer"));
        col_status.setCellValueFactory(new PropertyValueFactory<FinishedOrder, String>("status"));



        listOfFinishedOrders = FXCollections.observableArrayList(finishedOrderList.getListFinishedOrdersWhoMachesDriver(driver));
        tvMadeOrders.setItems(listOfFinishedOrders);
    }
}
