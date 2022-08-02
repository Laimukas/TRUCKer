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

public class AdminTrailers implements Initializable {

    private TrailerList trailerList;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfModel;
    @FXML
    private TextField tfPlate;
    @FXML
    private TextField tfMot;
    @FXML
    private TextField tfVin;
    @FXML
    private TextField tfInsurance;
    @FXML
    private TextArea taOther;
    @FXML
    private TableView<Trailer> tvTrailers;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colModel;
    @FXML
    private TableColumn colPlate;
    @FXML
    private TableColumn colMot;
    @FXML
    private TableColumn colVin;
    @FXML
    private TableColumn colInsurance;
    @FXML
    private TableColumn colOther;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnTrucks;
    @FXML
    private Button btnDrivers;
    @FXML
    private Button btnUsers;
    @FXML
    private Button btnLineUp;
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

    public static ObservableList<Trailer> listOfTrailers;

    public void insertTrailersToTable() {
        trailerList = new TrailerList();
        try {
            trailerList.getTrailers();
            trailerList.sorting();
        } catch (TRUCKerListError e) {
            throw new RuntimeException(e);
        }

        System.out.println("Loading table");

        trailerList.getListOfTrailers().forEach(tl -> System.out.println(tl.getId() + "," + tl.getName() + "," +
                tl.getModel() + "," + tl.getPlate() + "," + tl.getMot() + "," + tl.getVin() + "," +
                tl.getInsurance() + "," + tl.getOther()));

        colId.setCellValueFactory(new PropertyValueFactory<Truck, String>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Truck, String>("name"));
        colModel.setCellValueFactory(new PropertyValueFactory<Truck, String>("model"));
        colPlate.setCellValueFactory(new PropertyValueFactory<Truck, String>("plate"));
        colMot.setCellValueFactory(new PropertyValueFactory<Truck, String>("mot"));
        colVin.setCellValueFactory(new PropertyValueFactory<Truck, String>("vin"));
        colInsurance.setCellValueFactory(new PropertyValueFactory<Truck, String>("insurance"));
        colOther.setCellValueFactory(new PropertyValueFactory<Truck, String>("other"));


        listOfTrailers = FXCollections.observableArrayList(trailerList.getListOfTrailers());
        tvTrailers.setItems(listOfTrailers);
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Trailer mouseTrailer = tvTrailers.getSelectionModel().getSelectedItem();

        ID = String.valueOf(mouseTrailer.getId());
        tfName.setText(mouseTrailer.getName());
        tfModel.setText(mouseTrailer.getModel());
        tfPlate.setText(mouseTrailer.getPlate());
        tfMot.setText(mouseTrailer.getMot());
        tfVin.setText(mouseTrailer.getVin());
        tfInsurance.setText(mouseTrailer.getInsurance());
        taOther.setText(mouseTrailer.getOther());
    }

    // Update Button action

    public void updateTrailer(ActionEvent event) throws TRUCKerListError, IOException {
        editTrailer();
    }

    private void editTrailer() throws TRUCKerListError, IOException {
        trailerList.getTrailers();
        Trailer trailer = new Trailer(Integer.parseInt(ID.toString()), tfName.getText(), tfModel.getText(), tfPlate.getText(),
                tfMot.getText(), tfVin.getText(), tfInsurance.getText(), taOther.getText());
        if (trailer == null) {
            throw new TRUCKerListError("Fill requaered fields!");
        }
        lblError.setText("Making changes for egsisting trailer!");
        trailerList.setTrailer(Integer.parseInt(ID.toString()), tfName.getText(), tfModel.getText(), tfPlate.getText(),
                tfMot.getText(), tfVin.getText(), tfInsurance.getText(), taOther.getText());
        trailerList.reloadFile();
        trailerList.getTrailers();
        makeTextEmpty();
        insertTrailersToTable();
    }

    // Delete Button action

    public void deleteTrailer(ActionEvent event) throws TRUCKerListError, IOException {
        removeTrailer();
    }

    private void removeTrailer() throws TRUCKerListError, IOException {
        trailerList.getTrailers();
        Trailer trailer = new Trailer(Integer.parseInt(ID.toString()), tfName.getText(), tfModel.getText(), tfPlate.getText(),
                tfMot.getText(), tfVin.getText(), tfInsurance.getText(), taOther.getText());
        if (trailer == null) {
            throw new TRUCKerListError("You need to show egsisting trailer to remove it!");
        }
        lblError.setText("Removing egsisting truck. Reloading file!");
        trailerList.removeFromList(trailer);
        trailerList.reloadFile();
        trailerList.getTrailers();
        makeTextEmpty();
        insertTrailersToTable();
    }

    public void makeTextEmpty() {
        tfName.setText("");
        tfModel.setText("");
        tfPlate.setText("");
        tfMot.setText("");
        tfVin.setText("");
        tfInsurance.setText("");
        taOther.setText("");
    }

    // Insert Button action

    public void insertTrailer(ActionEvent event) throws TRUCKerListError, IOException {
        addTrailer();
    }

    private void addTrailer() throws TRUCKerListError, IOException {
        trailerList.getTrailers();
        Trailer trailer = new Trailer(tfName.getText(), tfModel.getText(), tfPlate.getText(),
                tfMot.getText(), tfVin.getText(), tfInsurance.getText(), taOther.getText());
        if (trailer == null) {
            throw new TRUCKerListError("Fill all the requiered fields to add trailer!");
        }
        trailerList.addTrailerToList(trailer);
        trailerList.reloadFile();
        trailerList.getTrailers();
        lblError.setText("Trailer was Added! Reloading file!");
        makeTextEmpty();
        insertTrailersToTable();
    }


    public void adminLogOut(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("hello-view.fxml", 600, 300);
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
        trailerList = new TrailerList();
        insertTrailersToTable();
    }
}
