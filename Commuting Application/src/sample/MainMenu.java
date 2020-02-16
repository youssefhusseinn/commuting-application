package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import static sample.ErrorWindow.*;
import javafx.event.ActionEvent;
import static sample.Main.*;

public class MainMenu {
    @FXML TextField userNameField;
    @FXML PasswordField passwordField;
    @FXML RadioButton employeeRadioButton;
    @FXML RadioButton passengerRadioButton;


    public void registerButtonClicked(ActionEvent event)throws Exception{
        Parent customerScene = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene scene = new Scene(customerScene,600,400);
        currentStage.setTitle("Register an account");
        currentStage.setScene(scene);
        currentStage.show();
    }
    public void loginButtonClicked(ActionEvent event) throws Exception
    {
        String name = userNameField.getText();
        String pass = passwordField.getText();
        if(passengerRadioButton.isSelected()) {
            Customer customer;
            customer = Algorithms.searchCustomers(name, pass);
            if (customer == null)
                displayErrorWindow("Login Failed", "Incorrect name or password!");
            else {

                CustomerProfile.setCustomer(customer);
                Parent customerScene = FXMLLoader.load(getClass().getResource("customer profile.fxml"));
                Scene scene = new Scene(customerScene,600,400);
                currentStage.setTitle("Customer Profile");
                currentStage.setScene(scene);
                currentStage.show();
            }
        }
        else if(employeeRadioButton.isSelected())
        {
            Drivers driver;
            driver = Algorithms.searchDrivers(name,pass);
            if(driver!=null)
            {
                DriverProfile.setDriver(driver);
                Parent driverScene = FXMLLoader.load(getClass().getResource("driver profile.fxml"));
                Scene scene = new Scene(driverScene,600,400);
                currentStage.setTitle("Driver Profile");
                currentStage.setScene(scene);
                currentStage.show();
            }
            else
            {
                Manager manager = Algorithms.searchManagers(name,pass);
                if(manager == null)
                    displayErrorWindow("Login Failed", "Incorrect name or password!");
                else
                {
                    ManagerProfile.setManager(manager);
                    Parent driverScene = FXMLLoader.load(getClass().getResource("manager profile.fxml"));
                    Scene scene = new Scene(driverScene,600,400);
                    currentStage.setTitle("Manager Profile");
                    currentStage.setScene(scene);
                    currentStage.show();
                }
            }
        }
    }
}
