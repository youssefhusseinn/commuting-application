package sample;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Main.*;

public class CustomerProfile implements Initializable {
    @FXML  Label firstNameLabel;
    @FXML  Label lastNameLabel;
    @FXML  Label emailLabel;
    @FXML  Label phoneNumberLabel;
    @FXML ListView listView;
    @FXML Button viewTripsButton;
    @FXML Button saveButton;
    @FXML Button cancelTripButton;

    ObservableList<String> data = FXCollections.observableArrayList();
    static Trip bookedTrip;
    private static Customer customer;
   // private static Customer customerCopy;
    ErrorWindow errorWindow = new ErrorWindow();
    public void initialize(URL location, ResourceBundle resources)
    {
           // customerCopy=customer;
            displayInfo();
    }
    public void displayInfo()
    {
        //TODO: display booked trips
        System.out.println(customer.getNumberOfBookedTrips());
        if(customer.getNumberOfBookedTrips()==0)
            cancelTripButton.setDisable(true);
        else
            cancelTripButton.setDisable(false);
        saveButton.setDisable(false);
        firstNameLabel.setText("First Name: "+ customer.getFirstName());
        lastNameLabel.setText("Last Name: "+ customer.getLastName());
        emailLabel.setText("Email: "+ customer.getEmail());
        phoneNumberLabel.setText("Phone Number: "+ customer.getPhoneNumber());
        listView.setVisible(false);
    }
    public static void setCustomer(Customer c)
    {
        customer = c;
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
        listView.setItems(data);
        if(customer.getNumberOfBookedTrips()!=0)
        for(int i = 0;i<customer.getNumberOfBookedTrips();i++)
        {
            System.out.println(customer.bookedTrips[i].getTripID()+"    "+Algorithms.getTripIndex(customer.bookedTrips[i]));
            listView.getItems().add(i,trips[Algorithms.getTripIndex(customer.bookedTrips[i])].route() +"  "+ " Tickets: "+ customer.tickets[i] +"\n" + trips[Algorithms.getTripIndex(customer.bookedTrips[i])].dateAndTime());
        }
        listView.setVisible(true);
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                // firstNameLabel.setText(newValue);

            }
        });
        viewTripsButton.setDisable(true);
    }
    public void bookTripButtonClicked(ActionEvent event)throws Exception
    {
        BookTrip.customer = customer;
        Parent customerScene = FXMLLoader.load(getClass().getResource("book trip.fxml"));
        Scene scene = new Scene(customerScene,600,400);
        currentStage.setTitle("Book Trip");
        currentStage.setScene(scene);
        currentStage.show();
    }
    public void cancelTripButtonClicked(ActionEvent event)throws Exception
    {
        CancelTrip.customer = customer;
        Parent customerScene = FXMLLoader.load(getClass().getResource("cancel trip.fxml"));
        Scene scene = new Scene(customerScene,600,400);
        currentStage.setTitle("Cancel Trip");
        currentStage.setScene(scene);
        currentStage.show();
    }
    public boolean checkRegisterInfo(String firstName, String lastName, String email, String userName, String password,String phoneNumber)
    {
        if(firstName.equals("")||lastName.equals("")||email.equals("")||userName.equals("")||password.equals("")||phoneNumber.equals(""))
            return false;
        return true;
    }
    public boolean registerCustomer(String newCustomer,String newUsername, String newEmail) {
        boolean flag = true;
        for (int i = 0; i < customerCount; i++)
            if ((customers[i].getEmail().equals(newEmail)) || (customers[i].getUserName().equals(newUsername))) {
                flag = false;
                break;
            }
        if (flag) {
            customers[customerCount] = new Customer(newCustomer);
            customerCount++;
            return true;
        } else {
            errorWindow.displayErrorWindow("Error!", "Email or username already registered.");
        }
        return false;
    }
        public void changeInfoButtonClicked(ActionEvent event) throws Exception
    {
        ChangeInfo.setCustomer(customer);
        Parent customerScene = FXMLLoader.load(getClass().getResource("change info.fxml"));
        Scene scene = new Scene(customerScene,600,400);
        currentStage.setTitle("Update Information");
        currentStage.setScene(scene);
        currentStage.show();
    }

    public void reportButtonClicked(ActionEvent event)throws Exception{
        Report.setCustomer(customer);
        Report.setDriver(null);
        Parent customerScene = FXMLLoader.load(getClass().getResource("report .fxml"));
        Scene scene = new Scene(customerScene,400,300);
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Report A Problem");
        window.setScene(scene);
        window.showAndWait();
    }
    public void viewAllTripsButtonClicked(ActionEvent event) throws Exception{
        Parent customerScene = FXMLLoader.load(getClass().getResource("view all trips.fxml"));
        Scene scene = new Scene(customerScene,600,400);
        currentStage.setTitle("All Trips");
        currentStage.setScene(scene);
        currentStage.show();
    }
}
