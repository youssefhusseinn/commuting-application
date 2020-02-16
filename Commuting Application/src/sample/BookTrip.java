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

import java.net.URL;
import java.util.ResourceBundle;

import static sample.Main.*;

public class BookTrip implements Initializable{
    @FXML Label deleteLabel;
    @FXML ListView tripsListView;
    @FXML Label tripLabel;
    @FXML Button bookButton;
    @FXML TextField numberOfTicketsTextField;
    @FXML Label totalLabel;
    ObservableList<String> tripsdata = FXCollections.observableArrayList();
    ErrorWindow errorWindow = new ErrorWindow();
    public static Customer customer;
    int price;
    Trip trip;
    int customerIndex,index,numberOfTickets;
    public void initialize(URL location, ResourceBundle resources)
    {
        if(tripCount==0)
            bookButton.setDisable(true);
        tripsListView.setItems(tripsdata);
        for(int i = 0;i<tripCount;i++)
            tripsListView.getItems().add(i,trips[i].route() + "\n" + trips[i].dateAndTime());

        tripsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                tripLabel.setText(newValue);
                index = tripsListView.getItems().indexOf(tripLabel.getText());
                customerIndex = Algorithms.getCustomerIndex(customer);
                price = trips[index].getPrice();
                trip = trips[index];
            }
        });
        tripLabel.setVisible(false);
    }
    public void cancelButtonClicked(ActionEvent event)throws Exception
    {
        Parent customerScene = FXMLLoader.load(getClass().getResource("customer profile.fxml"));
        Scene scene = new Scene(customerScene,600,400);
        currentStage.setTitle("Customer Profile");
        currentStage.setScene(scene);
        currentStage.show();
    }
    public void bookButtonClicked (ActionEvent event)throws Exception
    {
        try{
            index = tripsListView.getItems().indexOf(tripLabel.getText());
            numberOfTickets = Integer.valueOf(numberOfTicketsTextField.getText());
            if(numberOfTickets>trip.getVacantSeats())
                errorWindow.displayErrorWindow("Error!","Not enough seats available!");
            else{
            customers[customerIndex].bookTrip(trips[index],numberOfTickets);
            cancelButtonClicked(event);
        }}
        catch (Exception e){
            errorWindow.displayErrorWindow("Error!","Please choose a trip to book.");
        }

    }
    public void calculateTotalButtonClicked(ActionEvent event)throws Exception
    {
        try {
            String text = numberOfTicketsTextField.getText();
            totalLabel.setText("Total: " + Integer.valueOf(text)*price);
        }catch(Exception e)
        {
            errorWindow.displayErrorWindow("Error!","Please choose a trip to book.");
        }
    }
}