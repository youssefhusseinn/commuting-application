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
import javafx.scene.control.RadioButton;

import java.net.URL;
import java.util.ResourceBundle;

import static sample.Main.tripCount;
import static sample.Main.trips;
import static sample.Main.*;
public class ViewAllTrips implements Initializable {

    @FXML RadioButton roundRadioButton;
    @FXML RadioButton onewayRadioButton;
    @FXML RadioButton allTripsRadioButton;
    @FXML Button backButton;
    @FXML ListView tripsListView;
    @FXML Label tripLabel;
    ObservableList<String> tripsdata = FXCollections.observableArrayList();
    ErrorWindow errorWindow = new ErrorWindow();
    Trip trip;
    public void initialize(URL location, ResourceBundle resources)
    {
        if(tripCount==0)
            tripsListView.setItems(tripsdata);
        for(int i = 0;i<tripCount;i++)
            tripsListView.getItems().add(i,trips[i].route() + "\n" + trips[i].dateAndTime());
        tripLabel.setVisible(false);
        radioButtonClicked();
    }
    public void radioButtonClicked(){
        int j;
        int i;
        if(roundRadioButton.isSelected()) {
            j = 0;
            tripsListView.setItems(tripsdata);
            tripsListView.getItems().clear();
            for (i = 0; i < tripCount; i++)
                if (trips[i].getRoute().equals("round")) {
                    tripsListView.getItems().add(j, trips[i].route() + "\n" + trips[i].dateAndTime());
                    j++;
                }
        }
        else if (onewayRadioButton.isSelected()) {
            j=0;
            tripsListView.setItems(tripsdata);
            tripsListView.getItems().clear();
            for (i = 0; i < tripCount; i++)
                if (trips[i].getRoute().equals("one-way")) {
                    tripsListView.getItems().add(j, trips[i].route() + "\n" + trips[i].dateAndTime());
                    j++;
                }
        }
        else
        {
            tripsListView.setItems(tripsdata);
            tripsListView.getItems().clear();
            for(i = 0;i<tripCount;i++)
                tripsListView.getItems().add(i,trips[i].route() + "\n" + trips[i].dateAndTime());
        }
    }
    public void backButtonClicked(ActionEvent event)throws Exception{
        Parent customerScene = FXMLLoader.load(getClass().getResource("customer profile.fxml"));
        Scene scene = new Scene(customerScene,600,400);
        currentStage.setTitle("Customer Profile");
        currentStage.setScene(scene);
        currentStage.show();
    }
}