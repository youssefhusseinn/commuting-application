package sample;

public class Drivers extends Employees {
    private String driverID;
    private String phoneNumber;
    private String vehicleType;
    public Drivers(String line)
    {
        String arr[] = new String[20];
        int i = 0;
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
        driverID = arr[5];
        phoneNumber = arr[6];
        vehicleType = arr[7];

    }


    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getVehicleType() {
        return vehicleType;
    }
}
