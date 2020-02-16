package sample;

import java.io.File;
import java.io.IOException;

import static sample.Main.*;

public class Algorithms {
    public static Customer searchCustomers(String userName , String pass)
    {
        for (int i=0;i<customerCount;i++)
            if(customers[i].getUserName().equals(userName) && customers[i].getPassword().equals(pass))
                return customers[i];
        return null;
    }
    public static Drivers searchDrivers(String userName , String pass)
    {
        for (int i=0;i<driverCount;i++)
            if(drivers[i].getUserName().equals(userName) && drivers[i].getPassword().equals(pass))
                return drivers[i];
        return null;
    }
    public static Manager searchManagers(String userName , String pass)
    {
        for (int i=0;i<managerCount;i++)
            if(managers[i].getUserName().equals(userName) && managers[i].getPassword().equals(pass))
                return managers[i];
        return null;
    }
    public static Trip searchTrips(int ID)
    {
        for(int i = 0;i<tripCount;i++)
            if(trips[i].getTripID()==ID)
                return trips[i];
        return null;
    }
    public static int getCustomerIndex(Customer customer)
    {
        for(int i=0;i<customerCount;i++)
        {
            if(customer.getFirstName().equals(customers[i].getFirstName()) && customer.getLastName().equals(customers[i].getLastName()))
                return i;
        }
        return -1;
    }
    public static int getTripIndex(Trip trip)
    {
        for(int i=0;i<tripCount;i++)
        {
            if(trip.route().equals(trips[i].route()) && trip.dateAndTime().equals(trips[i].dateAndTime()))
                return i;
        }
        return -1;
    }
    public static void save()
    {
        Save save = new Save();
        try
        {
            File customersFile = new File("customers");
            File tripsFile = new File("trips");
            save.makeTripsStrings(tripsFile);
            save.makeCustomersStrings(customersFile);
            if(customersFile==null)
                System.out.println("file null");
        }
        catch(IOException e)
        {
            ErrorWindow errorWindow = new ErrorWindow();
            errorWindow.displayErrorWindow("Error!","File not found!");
        }
    }
}
