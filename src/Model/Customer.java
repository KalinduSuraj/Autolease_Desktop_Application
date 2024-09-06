package Model;

public class Customer {
    private String NIC;
    private String Name;
    private String Contact;
    private String Address;

    public String getNIC() {
        return NIC;
    }
    public String getName() {
        return Name;
    }
    public String getContact() {
        return Contact;
    }
    public String getAddress() {
        return Address;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }
    public void setName(String name) {
        Name = name;
    }
    public void setContact(String contact) {
        Contact = contact;
    }
    public void setAddress(String address) {
        Address = address;
    }
}
