package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static sample.Main.*;

public class DriverProfile implements Initializable {
    private static Drivers driver;
    @FXML Label firstNameLabel;
    @FXML  Label lastNameLabel;
    @FXML  Label emailLabel;
    @FXML  Label phoneNumberLabel;
    @FXML Label vehicleLabel;
    @FXML ListView listView;
    @FXML Button viewAssignedTripsButton;
    ObservableList<String> assignedtripsdata = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources)
    {
        displayInfo();
    }

    public void displayInfo(){
        firstNameLabel.setText("First Name: "+ driver.getFirstName());
        lastNameLabel.setText("Last Name: "+ driver.getLastName());
        emailLabel.setText("Email: "+ driver.getEmail());
        phoneNumberLabel.setText("Phone Number: "+ driver.getPhoneNumber());
        vehicleLabel.setText("Vehicle: "+driver.getVehicleType());
        listView.setVisible(false);
    }
    public void logoutButtonClicked(ActionEvent event)throws Exception
    {
        Parent customerScene = FXMLLoader.load(getClass().getResource("main menu.fxml"));
        Scene scene = new Scene(customerScene,600,400);
        currentStage.setTitle("Bus Station");
        currentStage.setScene(scene);
        currentStage.show();
    }
    public static void setDriver(Drivers d)
    {
        driver = d;
    }

    public void viewAssignedTripsButtonClicked(ActionEvent event)throws Exception
    {
        listView.setVisible(true);
        int index = 0;
        for(int i =0;i<tripCount;i++) {
            if (driver.getUserName().equals(trips[i].getDriverName())) {
                listView.getItems().add(index, trips[i].route() + "\n" + trips[i].dateAndTime());
                index++;
            }
        }
        viewAssignedTripsButton.setDisable(true);
    }
    public void reportButtonClicked(ActionEvent event)throws Exception
    {
        Report.setCustomer(null);
        Report.setDriver(driver);
        Parent customerScene = FXMLLoader.load(getClass().getResource("report .fxml"));
        Scene scene = new Scene(customerScene,400,300);
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Report A Problem");
        window.setScene(scene);
        window.showAndWait();
    }
}
