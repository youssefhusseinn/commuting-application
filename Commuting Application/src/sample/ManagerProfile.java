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

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Main.*;
import static sample.Main.trips;

public class ManagerProfile implements Initializable {
    private static Manager manager;
    @FXML Label firstNameLabel;
    @FXML  Label lastNameLabel;
    @FXML  Label emailLabel;
    @FXML ListView listView;
    @FXML Button viewTripsButton;
    @FXML Button saveButton;
@FXML Button viewComplaintsButton;
    ObservableList<String> tripsdata = FXCollections.observableArrayList();
    ErrorWindow errorWindow = new ErrorWindow();
    public void initialize(URL location, ResourceBundle resources)
    {
        displayInfo();
        File complaintsFile = new File("C:\\Users\\HP\\IdeaProjects\\Bus Station\\src\\sample\\complaints");
        if(complaintsFile.length()<1)
            viewComplaintsButton.setDisable(true);
    }

    public void displayInfo(){
        saveButton.setDisable(false);
        firstNameLabel.setText("First Name: "+ manager.getFirstName());
        lastNameLabel.setText("Last Name: "+ manager.getLastName());
        emailLabel.setText("Email: "+ manager.getEmail());
        listView.setVisible(false);
    }

    public static void setManager(Manager m) {
        manager = m;
    }
    public void logoutButtonClicked(ActionEvent event)throws Exception
    {
        Parent customerScene = FXMLLoader.load(getClass().getResource("main menu.fxml"));
        Scene scene = new Scene(customerScene,600,400);
        currentStage.setTitle("Bus Station");
        currentStage.setScene(scene);
        currentStage.show();
    }
    public void saveButtonClicked(ActionEvent event)throws Exception
    {
        Algorithms.save();
        saveButton.setDisable(true);
    }
    public void viewTripsButtonClicked(ActionEvent event)throws Exception
    {
        listView.setVisible(true);
        listView.setItems(tripsdata);
        for(int i = 0;i<tripCount;i++)
        {
            listView.getItems().add(i,trips[i].route() + "\n" + trips[i].dateAndTime());
        }
        viewTripsButton.setDisable(true);
    }
    public void createTripButtonClicked(ActionEvent event)throws Exception
    {
        Parent customerScene = FXMLLoader.load(getClass().getResource("create trip.fxml"));
        Scene scene = new Scene(customerScene,600,400);
        currentStage.setTitle("Create Trip");
        currentStage.setScene(scene);
        currentStage.show();
    }
    public void createTrip(String line)
    {
        manager.createTrip(line);
    }
    public void deleteTripsButtonClicked(ActionEvent event)throws Exception
    {
        Parent customerScene = FXMLLoader.load(getClass().getResource("delete trip.fxml"));
        Scene scene = new Scene(customerScene,600,400);
        currentStage.setTitle("Delete Trip");
        currentStage.setScene(scene);
        currentStage.show();
    }
    public void viewComplaintsButtonClicked(ActionEvent event)throws Exception
    {
        Parent customerScene = FXMLLoader.load(getClass().getResource("view complaints.fxml"));
        Scene scene = new Scene(customerScene,600,400);
        currentStage.setTitle("Complaints");
        currentStage.setScene(scene);
        currentStage.show();
    }
}
