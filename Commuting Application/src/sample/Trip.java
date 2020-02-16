package sample;


import static sample.Main.generalTripID;

public class Trip {
    private String from;
    private String destination;
    private int price;
    private String time;
    private String driverName;
    private String date;
    private String type;
    private String vehicle;
    private int vacantSeats;
    private int tripID;
    private int stops;
    private String route;
    public Trip(String line)
    {
        String arr[] = new String[20];
        int i = 0;
        for (String str: line.split(","))
        {
            arr[i] = str;
            i++;
        }
        from = arr[0];
        destination = arr[1];
        price = Integer.valueOf(arr[2]);
        time = arr[3];
        date = arr[4];
        vehicle = arr[5];
        stops = Integer.valueOf(arr[6]);
        type = arr[7];
        route = arr[8];
        vacantSeats = Integer.valueOf(arr[9]);
        driverName = arr[10];
        tripID = Integer.valueOf(arr[11]);
        generalTripID++;
    }
    public String route() {
        String line ="From: " + from +"  "+ " To: " + destination +"  "+ " Stops: " + stops + " Type: " + route;
        return line;
    }
    public String dateAndTime() {
        String line ="Time: " + time +"  "+ " Date: " + date + "  "+" Vacant Seats: " + vacantSeats +"  "+ " Price: "+price;
        return line;
    }

    public void buyTickets(int numberOfTickets)
    {
        if(vacantSeats>=numberOfTickets)
            vacantSeats-= numberOfTickets;
    }
    public void returnTickets(int numberOfTickets)
    {
        vacantSeats+=numberOfTickets;
    }

    public int getPrice() {
        return price;
    }
    public int getVacantSeats() {
        return vacantSeats;
    }
    public String getTime() {
        return time;
    }
    public String getFrom() {
        return from;
    }
    public String getDestination() {
        return destination;
    }
    public String getDriverName() {
        return driverName;
    }
    public String getDate() {
        return date;
    }
    public String getType() {
        return type;
    }
    public String getVehicle() {
        return vehicle;
    }
    public int getTripID() {
        return tripID;
    }
    public int getStops() {
        return stops;
    }

    public String getRoute() {
        return route;
    }
}
