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

public class Login implements Initializable {

    public Login() {

    }

    private UserList userList;
    private DriverList driverList;
    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Label wrongLogin;

    public void userLogin(ActionEvent event) throws TRUCKerListError, IOException {

        HelloApplication m = new HelloApplication();

        String name = tfUsername.getText();
        String passw = pfPassword.getText();
        String status = userList.returnStatus(name, passw);
        if (name.isEmpty() || passw.isEmpty()) {
            wrongLogin.setText("Please fill all required fields!");

        } else if (status == "admin") {

            m.changeScene("adminPage.fxml", 1389, 764);


        } else if (status == "driver") {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("driversPage1.fxml"));
            Parent driverPageViewParent = loader.load();

            Scene driverPageViewScene = new Scene(driverPageViewParent);

            Driver driver = driverList.getDriverByUserName(name);
            //access the controller and call a method
            DriversPage controller = loader.getController();
            controller.initData(driver);
            controller.insertFinishedOrdersToTable(driver.getName());
            System.out.println(driver.getName());

            //This line gets the Stage information

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(driverPageViewScene);
            window.setWidth(1210);
            window.setHeight(600);
            window.centerOnScreen();
            window.show();
        } else {
            wrongLogin.setText("Wrong Username or Password!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        userList = new UserList();
        driverList = new DriverList();
    }
}
