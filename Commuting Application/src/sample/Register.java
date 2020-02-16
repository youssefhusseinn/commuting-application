package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;

import javafx.scene.control.*;

import static sample.Main.*;
public class Register {

    @FXML TextField firstNameText;
    @FXML TextField lastNameText;
    @FXML TextField emailText;
    @FXML TextField phoneNumberText;
    @FXML TextField usernameText;
    @FXML PasswordField passwordText;
    CustomerProfile cp = new CustomerProfile();
    ErrorWindow errorWindow = new ErrorWindow();
    public void cancelButtonClicked(ActionEvent event)throws Exception{
        Parent customerScene = FXMLLoader.load(getClass().getResource("main menu.fxml"));
        Scene scene = new Scene(customerScene,600,400);
        currentStage.setTitle("Bus Station");
        currentStage.setScene(scene);
        currentStage.show();
    }
    public void registerButtonClicked(ActionEvent event)throws Exception{
        try{
            boolean register;
            String newCustomer;
            register=cp.checkRegisterInfo(firstNameText.getText(),lastNameText.getText(),emailText.getText(),usernameText.getText(),passwordText.getText(),phoneNumberText.getText());
            if(register)
            {
                newCustomer = firstNameText.getText() + "," + lastNameText.getText() + "," + usernameText.getText() + "," + passwordText.getText() + "," + emailText.getText() + "," + (customers[customerCount - 1].getCustomerID() + 1) + "," + phoneNumberText.getText() + "," + "0";
                register = cp.registerCustomer(newCustomer, usernameText.getText(), emailText.getText());
                if (register) {
                    Algorithms.save();
                    cancelButtonClicked(event);
                }
            }
            else
                errorWindow.displayErrorWindow("Error!","Missing data required!");

        }
        catch (Exception e)
        {
            errorWindow.displayErrorWindow("Error!","Invalid Entry!");
        }
    }
}