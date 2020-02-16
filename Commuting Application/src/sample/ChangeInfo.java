package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javax.xml.soap.Text;

import java.net.URL;
import java.util.ResourceBundle;

import static sample.BookTrip.customer;
import static sample.Main.currentStage;
import static sample.CustomerProfile.*;
import static sample.Main.customers;

public class ChangeInfo{
    @FXML TextField firstNameText;
    @FXML TextField lastNameText;
    @FXML TextField emailText;
    @FXML TextField phoneNumberText;
    @FXML PasswordField passwordText;
    @FXML TextField userNameText;
    private static Customer customer;
    public void cancelButtonClicked(ActionEvent event)throws Exception
    {
        Parent customerScene = FXMLLoader.load(getClass().getResource("customer profile.fxml"));
        Scene scene = new Scene(customerScene,600,400);
        currentStage.setTitle("Customer Profile");
        currentStage.setScene(scene);
        currentStage.show();
    }
    public void saveButtonClicked(ActionEvent event)throws Exception{
        System.out.println(firstNameText.getText()+lastNameText.getText()+emailText.getText()+phoneNumberText.getText()+passwordText.getText());
        try {
            int index = Algorithms.getCustomerIndex(customer);
            int i = Algorithms.getCustomerIndex(customer);
            customers[i].changeInfo(firstNameText.getText(), lastNameText.getText(), emailText.getText(), phoneNumberText.getText(), passwordText.getText(), userNameText.getText());
            customers[index]=customer;
        }
        catch (Exception e)
        {
            System.out.println("ERROR");
        }
        cancelButtonClicked(event);
    }

    public static void setCustomer(Customer c)
    {
        customer = c;
    }
}