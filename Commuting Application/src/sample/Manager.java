package sample;

import static sample.Main.*;

public class Manager extends Employees {
    public Manager()
    {
    }
    public Manager(String line)
    {
        String arr[] = new String[5];
        int i = 0;
        for(String str : line.split(","))
        {
            arr[i] = str;
            i++;
        }
        firstName = arr[0];
        lastName = arr[1];
        userName = arr[2];
        password = arr[3];
        email = arr[4];
    }
    public void createTrip(String line)
    {
        trips[tripCount]=new Trip(line);
        tripCount++;
    }
    public void deleteTrip(Trip trip)
    {
        int index=0;
        int i;
        boolean flag= false;
        for(i =0;i<tripCount;i++)
        {
            if(trips[i].getTripID()==trip.getTripID())
            {
                index = i;
                flag = true;
                break;
            }
        }
        tripCount--;
        if(flag) {
            for(i=0;i<customerCount;i++)
            {
                if(customers[i].isBooked(trip))
                    customers[i].cancelTrip(trip,customers[i].numberOfTicketsOfATrip(trip));
            }
            for (i = index; i < tripCount; i++)
                trips[i] = trips[i + 1];
        }
    }
}
