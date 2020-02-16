package sample;
import java.lang.*;
import java.io.*;
import static sample.Main.*;

public class Save {
    private String strTrip[] = new String[tripCount];
    private String strCustomer[] = new String[customerCount];
    ErrorWindow errorWindow = new ErrorWindow();

    public void makeTripsStrings(File f)throws IOException {
        FileWriter fw = new FileWriter(f);
        for (int i = 0; i < tripCount; i++)
        {
            strTrip[i] = trips[i].getFrom() + "," + trips[i].getDestination() + "," + trips[i].getPrice() + "," + trips[i].getTime() + "," + trips[i].getDate() + "," + trips[i].getVehicle() + "," + trips[i].getStops() + "," + trips[i].getType() + "," + trips[i].getRoute()+ "," + trips[i].getVacantSeats() + "," + trips[i].getDriverName() + "," + trips[i].getTripID();
            fw.write(strTrip[i]);
            if(i!=(tripCount-1))
                fw.write("\n");
        }
        fw.close();
    }
    public void makeCustomersStrings(File f)throws IOException {
        FileWriter fw = new FileWriter(f);
        for (int i = 0; i < customerCount; i++)
        {
            strCustomer[i] = customers[i].getFirstName() + "," + customers[i].getLastName() + "," + customers[i].getUserName() + "," + customers[i].getPassword() + "," + customers[i].getEmail() + "," + customers[i].getCustomerID() + "," + customers[i].getPhoneNumber() + "," + customers[i].getBookedTripsInfoForFile();
            System.out.println(strCustomer[i]);
            fw.write(strCustomer[i]);
            if(i!=(customerCount-1))
                fw.write("\n");
        }
        fw.close();
    }
}