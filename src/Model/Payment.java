package Model;

import java.sql.Date;

public class Payment {
    private String ID ;
    private double Amount;

    public String getID() {
        return ID;
    }
    public double getAmount() {
        return Amount;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public void setAmount(double amount) {
        Amount = amount;
    }

}
