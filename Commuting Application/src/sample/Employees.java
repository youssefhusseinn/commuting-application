package sample;

public abstract class Employees implements IUsers {

    String userName= null;
    String firstName=null;
    String lastName=null;
    String password=null;
    String email=null;

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }
}
