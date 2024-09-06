package Model;

public class User {
    private String UserName,Name,Contact,Password;


    public String getUserName() {
        return UserName;
    }
    public String getName() {
        return Name;
    }
    public String getContact() {
        return Contact;
    }
    public String getPassword() {
        return Password;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
    public void setName(String name) {
        Name = name;
    }
    public void setContact(String contact) {
        Contact = contact;
    }
    public void setPassword(String password) {
        Password = password;
    }
}
