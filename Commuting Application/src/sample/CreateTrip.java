package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javax.print.DocFlavor;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static sample.Main.*;

public class CreateTrip implements Initializable {
    @FXML ListView vehicleTypeList;
    @FXML Label vehicleTypeLabel;
    @FXML TextField fromText;
    @FXML Button cancelButton;
    @FXML TextField toText;
    @FXML TextField priceText;
    @FXML TextField timeText;
    @FXML TextField typeText;
    @FXML TextField stopsText;
    @FXML TextField routeText;
   // @FXML TextField driverNameText;
    @FXML DatePicker datePicker;
    @FXML ListView driversListView;
    @FXML Label driverNameLabel;
    ObservableList<String> data = FXCollections.observableArrayList("bus","minibus","limousine");
    ManagerProfile m = new ManagerProfile();
    ErrorWindow errorWindow = new ErrorWindow();
    ObservableList<String> driversdata = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources)
    {
        driversListView.setItems(driversdata);
        for(int i = 0;i<driverCount;i++)
        {
            driversListView.getItems().add(i,drivers[i].getUserName());
        }
        //driverNameLabel.setVisible(false);
        driversListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                driverNameLabel.setText(newValue);
            }
        });
        vehicleTypeLabel.setVisible(false);
        vehicleTypeList.setItems(data);
        vehicleTypeList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                vehicleTypeLabel.setText(newValue);
            }
        });

    }

    public void assignTripButtonClicked(ActionEvent event)throws Exception
    {
        String newTrip=null;
        int vacantSeats;

        try {
            if (vehicleTypeLabel.getText().equals("bus"))
                vacantSeats = 50;
            else if (vehicleTypeLabel.getText().equals("minibus"))
                vacantSeats = 14;
            else
                vacantSeats = 4;
            String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                newTrip = fromText.getText() + "," + toText.getText() + "," + priceText.getText() + "," + timeText.getText() + "," + date + "," + vehicleTypeLabel.getText() + "," + stopsText.getText() + "," + typeText.getText() + "," + routeText.getText() + "," + String.valueOf(vacantSeats) + "," + driverNameLabel.getText() + "," + generalTripID;
            if(driverNameLabel.getText().equals("name"))
                errorWindow.displayErrorWindow("Error!","Choose a driver!");
            else
                if(newTrip!=null) {
                m.createTrip(newTrip);
                cancelButtonClicked(event);
            }
        }catch(Exception e){
            errorWindow.displayErrorWindow("Error!","Invalid Entry!");
        }


    }
    public void cancelButtonClicked(ActionEvent event)throws Exception
    {
        Parent customerScene = FXMLLoader.load(getClass().getResource("manager profile.fxml"));
        Scene scene = new Scene(customerScene,600,400);
        currentStage.setTitle("Manager Profile");
        currentStage.setScene(scene);
        currentStage.show();
    }
}