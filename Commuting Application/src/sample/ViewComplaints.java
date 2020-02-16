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
import javafx.scene.control.ListView;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Main.currentStage;

public class ViewComplaints implements Initializable {

@FXML
ListView listView;
@FXML
Button backButton;


    ObservableList<String> data = FXCollections.observableArrayList();
    ErrorWindow errorWindow = new ErrorWindow();
    public void initialize(URL location, ResourceBundle resources)
    {
        Complaints[] complaints = new Complaints[20];
        int complaintsCount=0;
        try {
        File complaintsFile = new File("complaints");
        FileInputStream fis = new FileInputStream(complaintsFile);
        byte[] data = new byte[(int) complaintsFile.length()];

        fis.read(data);
        fis.close();

            String line = new String(data, "UTF-8");
        int i=0;
        for ( String str : line.split("====================")) {        System.out.println("here"+i);
            System.out.println(str);
            complaints[i]= new Complaints(str);
            complaintsCount++;
            i++;
        }
    }catch(Exception e)
    {
        errorWindow.displayErrorWindow("Error","File not found!");
    }

        listView.setItems(data);
        for(int i = 0;i<complaintsCount;i++)
            listView.getItems().add(i,complaints[i].printComplaint());

    }


    public void backButtonClicked(ActionEvent event)throws Exception
    {
        Parent driverScene = FXMLLoader.load(getClass().getResource("manager profile.fxml"));
        Scene scene = new Scene(driverScene,600,400);
        currentStage.setTitle("Manager Profile");
        currentStage.setScene(scene);
        currentStage.show();
    }

}
