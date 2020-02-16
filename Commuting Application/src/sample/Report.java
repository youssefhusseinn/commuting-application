package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileWriter;


public class Report {

    @FXML TextArea textArea;
    @FXML Button sendButton;
    private static Customer customer;
    private static Drivers driver;
    public void sendButtonClicked(ActionEvent event)throws Exception{
        String text = textArea.getText();
        File complaintsFile = new File("complaints");
        FileWriter fw = new FileWriter(complaintsFile,true);
        if(driver==null)
            fw.write(customer.getFirstName()+" "+ customer.getLastName()+"==========");
        else
            fw.write(driver.getFirstName()+" "+driver.getLastName()+"==========");
        fw.write(text+"====================");
        fw.close();
        sendButton.setDisable(true);
    }
    public static void setCustomer(Customer c)
    {
        customer = c;
    }
    public static void setDriver(Drivers d)
    {
        driver = d;
    }
}
