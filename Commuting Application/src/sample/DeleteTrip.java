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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

import static sample.Main.*;

public class DeleteTrip implements Initializable{
    @FXML Label deleteLabel;
    @FXML ListView tripsListView;
    @FXML Label tripLabel;
    @FXML Button deleteButton;
    ObservableList<String> tripsdata = FXCollections.observableArrayList();
    ErrorWindow errorWindow = new ErrorWindow();
    Manager manager = new Manager();
    public void initialize(URL location, ResourceBundle resources)
    {
        if(tripCount==0)
            deleteButton.setDisable(true);
        tripsListView.setItems(tripsdata);
        for(int i = 0;i<tripCount;i++)
            tripsListView.getItems().add(i,trips[i].route() + "\n" + trips[i].dateAndTime());

        tripsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                tripLabel.setText(newValue);
            }
        });
        tripLabel.setVisible(false);
    }
    public void cancelButtonClicked(ActionEvent event)throws Exception
    {
        Parent customerScene = FXMLLoader.load(getClass().getResource("manager profile.fxml"));
        Scene scene = new Scene(customerScene,600,400);
        currentStage.setTitle("Manager Profile");
        currentStage.setScene(scene);
        currentStage.show();
    }
    public void deleteButtonClicked (ActionEvent event)throws Exception
    {
        int index;
        try{
            index = tripsListView.getItems().indexOf(tripLabel.getText());
            manager.deleteTrip(trips[index]);
            cancelButtonClicked(event);
        }
        catch (Exception e){
            errorWindow.displayErrorWindow("Error!","Please choose a trip to delete.");
        }

    }
}