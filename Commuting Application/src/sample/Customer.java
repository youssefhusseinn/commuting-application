package sample;

import static sample.Main.*;

public class Customer implements IUsers {

    String userName= null;
    String firstName=null;
    String lastName=null;
    String password=null;
    String email=null;
    private int customerID;
    private String phoneNumber;
    int numberOfBookedTrips;
    public Trip[] bookedTrips = new Trip[10];
    public int[] tickets = new int[10];

    public String getUserName(){return userName; }
    public String getPassword(){return password; }
    public String getEmail(){return email;}
    public String getFirstName(){return firstName;}
    public String getLastName(){
        return lastName;
    }

    public int getNumberOfBookedTrips() {
        return numberOfBookedTrips;
    }

    public int getCustomerID() {
        return customerID;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }


    public Customer()
    {

    }
    public Customer (String line)
    {
        String arr[] = new String[50];
        int i = 0;
        int j = 0;
        for (String str: line.split(","))
        {
            arr[i] = str;
            i++;
        }
        firstName = arr[0];
        lastName = arr[1];
        userName = arr[2];
        password = arr[3];
        email = arr[4];
        customerID = Integer.valueOf(arr[5]);
        phoneNumber = arr[6];
        numberOfBookedTrips = Integer.valueOf(arr[7]);
        if(numberOfBookedTrips>0)
             for(j=0;j<numberOfBookedTrips;j++) {
                  bookedTrips[j] = Algorithms.searchTrips(Integer.valueOf(arr[8+(j*2)]));
                  tickets[j] = Integer.valueOf(arr[9+(j*2)]);
               }
        //bookedTrip = Algorithms.searchTrips(Integer.valueOf(arr[7]));
    }

    //TODO: methods: review trips, book trips.
    public void bookTrip(Trip trip, int numberOfTickets)
    {
        //TODO: book many trips
        int i ;
        int found = -1;
        for(i=0;i<numberOfBookedTrips;i++) {
            if (trip.getTripID() == bookedTrips[i].getTripID()) {
                found = i;
                break;
            }
        }
        int index = Algorithms.getTripIndex(trip);
        if (index != -1)
            trips[index].buyTickets(numberOfTickets);
        if(found!=-1)
            tickets[found]= tickets[found]+numberOfTickets;
        else {
            bookedTrips[numberOfBookedTrips] = trip;
            tickets[numberOfBookedTrips] = numberOfTickets;
            numberOfBookedTrips++;
        }

    }
    public String getBookedTripsInfoForFile()
    {
        String line = String.valueOf(numberOfBookedTrips);
        int i;
        for(i=0;i<numberOfBookedTrips;i++) {
            // line.concat(" "+String.valueOf(bookedTrips[i].getTripID()) + " " + String.valueOf(tickets[i]));
            line = line + "," + bookedTrips[i].getTripID() + "," + tickets[i];
            System.out.println(bookedTrips[i].getTripID()+ "," + tickets[i]+"       "+i+"      "+numberOfBookedTrips);
        }
        //System.out.println(bookedTrips[1].getTripID()+ "," + tickets[1]);
        //System.out.println(numberOfBookedTrips + " "+ i);

        // System.out.println(line);

        return line;
    }

    public void cancelTrip(Trip trip, int numberOfTickets)
    {
        int i ;
        int found = -1;
        for(i=0;i<numberOfBookedTrips;i++) {
            if (trip.getTripID() == bookedTrips[i].getTripID()) {
                found = i;
                break;
            }
        }

        int index = Algorithms.getTripIndex(bookedTrips[found]);
        //TODO: cancel trip in trips class
        trips[index].returnTickets(numberOfTickets);
    if(numberOfTickets == tickets[found])
        deleteBookedTrip(found);
    else
        tickets[found]-=numberOfTickets;
    }

    private void deleteBookedTrip(int index)
    {
        for(int i=index;i<numberOfBookedTrips-1;i++)
        {
            bookedTrips[i]= bookedTrips[i+1];
            tickets[i] = tickets[i+1];
        }
        numberOfBookedTrips--;
    }
    boolean isBooked(Trip trip)
    {
        for(int i=0;i<numberOfBookedTrips;i++)
        {
            if(bookedTrips[i]==trip)
                return true;
        }
        return false;
    }
    public int numberOfTicketsOfATrip(Trip trip)
    {
        for(int i =0;i<numberOfBookedTrips;i++)
            if(bookedTrips[i]==trip)
                return tickets[i];
            return 0;
    }
    public void changeInfo(String firstName,String lastName,String email,String phoneNumber,String password, String userName)
    {
        if(!phoneNumber.equals(null))
            this.phoneNumber =phoneNumber;
        if(!firstName.equals(null))
            this.firstName = firstName;
        if(!lastName.equals(null))
            this.lastName = lastName;
        if(!email.equals(null))
            this.email = email;
        if(!password.equals(null))
            this.password = password;
        if(!userName.equals(null))
            this.userName = userName;

    }
}
