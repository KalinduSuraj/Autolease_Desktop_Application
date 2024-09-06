package Model;

public class Leasing {
    private String LeasingID;
    private Double Amount;

    public String getLeasingID() {
        return LeasingID;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setLeasingID(String leasingID) {
        LeasingID = leasingID;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }
}

