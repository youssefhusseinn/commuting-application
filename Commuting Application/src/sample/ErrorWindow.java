package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ErrorWindow {
    public static void displayErrorWindow(String title , String message){
        Stage window = new Stage();
        Label mylabel = new Label(message);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        Button mybutton = new Button("Back");
        mybutton.setOnAction(e->{
            window.close();
        });
        VBox layout = new VBox(10);
        layout.getChildren().addAll(mylabel,mybutton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }


}
