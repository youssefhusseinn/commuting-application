package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.RandomAccessFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main extends Application {
   // public static Complaints[] complaints = new Complaints[20];
    public static Customer customers[] = new Customer[20];
    public static Drivers drivers[] = new Drivers[5];
    public static Manager managers[] = new Manager[5];
    public static Trip trips[] = new Trip[50];
    public static int managerCount = 0, driverCount = 0, customerCount = 0,tripCount = 0,generalTripID=0;
    public static Stage currentStage = new Stage();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main menu.fxml"));
        currentStage.setTitle("Bus Station");
        currentStage.setScene(new Scene(root, 600, 400));
        currentStage.show();
    }


    public static void main(String[] args) throws FileNotFoundException{
        File managersFile = new File("managers");
        File customersFile = new File("customers");
        File driversFile = new File("drivers");
        File tripsFile = new File("trips");

        Scanner tripsScan = new Scanner(tripsFile);
        Scanner managerScan = new Scanner(managersFile);
        Scanner customerScan = new Scanner(customersFile);
        Scanner driverScan = new Scanner(driversFile);
        int i=0;

        while(tripsScan.hasNextLine())
        {
            trips[i] = new Trip(tripsScan.nextLine());
            tripCount++;
            i++;
        }
        i = 0;
        while(managerScan.hasNextLine()) {
            managers[i]= new Manager(managerScan.nextLine());
            managerCount++;
            i++;
        }
        i=0;
        while(customerScan.hasNextLine())
        {
            customers[i]= new Customer(customerScan.nextLine());
            customerCount++;
            i++;
        }
        i=0;
        while(driverScan.hasNextLine()) {
            drivers[i] = new Drivers(driverScan.nextLine());
            driverCount++;
            i++;
        }
         launch(args);
}
}
