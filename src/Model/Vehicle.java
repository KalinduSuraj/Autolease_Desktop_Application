package Model;

public class Vehicle {
    private  String id;
    private String vin;
    private String model;
    private int year;
    private double price;
    private String image;
    private String status;

    public String getId(){
        return id;
    }
    public String getVin() {
        return vin;
    }
    public String getModel() {
        return model;
    }
    public int getYear() {
        return year;
    }
    public double getPrice() {
        return price;
    }
    public String getImage() {
        return image;
    }
    public String getStatus(){
        return status;
    }

    public void setId(String id){
        this.id= id;
    }
    public void setVIN(String vin) {
        this.vin = vin;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setStatus(String status){
        this.status = status;
    }
}
