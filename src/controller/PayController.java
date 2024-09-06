package controller;

import Model.*;
import view.LeasingView;
import view.PayView;

import javax.swing.*;
import java.util.Objects;

public class PayController {
    PayView view;
    Vehicle objSelectedVehicle;
    Customer objSelectedCustomer;
    Payment objPayment;
    Leasing objLeasing;
    LeasingDAO leasingDAO;
    PaymentDAO paymentDAO;
    VehiclesDAO vehiclesDAO;
    CustomerDAO customerDAO;

    boolean firstPayemnt;

    public PayController(PayView view, Vehicle objSelectedVehicle, Customer objSelectedCustomer){
        firstPayemnt = true;
        objLeasing = new Leasing();
        leasingDAO = new LeasingDAO();
        objPayment = new Payment();
        paymentDAO = new PaymentDAO();
        vehiclesDAO = new VehiclesDAO();
        customerDAO = new CustomerDAO();
        this.view = view;
        this.objSelectedVehicle = objSelectedVehicle;
        this.objSelectedCustomer = objSelectedCustomer;

        view.setTxtVIN(objSelectedVehicle.getVin());
        view.setTxtModel(objSelectedVehicle.getModel());
        if(Objects.equals(objSelectedVehicle.getStatus(), "Loan Approved")){

            Double price = objSelectedVehicle.getPrice();
            Double leasingAmount = leasingDAO.getLeasingAmount(objSelectedVehicle);
            Double Amount = 0.0;
            Amount = price - leasingAmount;
            view.getTxtBoxPayment().setEditable(false);
            view.getTxtBoxPayment().setText(Double.toString(Amount));
            view.getTxtBoxNIC().setEditable(true);
            view.setLblNIC("Enter New Owner NIC");

        }else if(Objects.equals(objSelectedVehicle.getStatus(), "sold out(Loan)")){
            firstPayemnt = false;
            Double R_Amount = leasingDAO.getR_Amount(objSelectedVehicle);
            view .getlblPayemnt().setText("Payment (Remaining:"+R_Amount+")");
            view.getTxtBoxPayment().setEditable(true);
            view.setLblNIC("Owner NIC");
            view.getTxtBoxNIC().setEditable(false);
            view.getTxtBoxNIC().setText(objSelectedCustomer.getNIC());
        }else{
            view.setLblNIC("Enter New Owner NIC");
            view.getTxtBoxPayment().setText(Double.toString(objSelectedVehicle.getPrice()));
            view.getTxtBoxPayment().setEditable(false);
        }
        view.PayListener(e -> payButtonClick());
    }

    /*
    public String LoanPaymentAmountView(){
        Double Amount = 0.0;

        Double price = objSelectedVehicle.getPrice();
        Double leasingAmount = leasingDAO.getLeasingAmount(objSelectedVehicle);
        if (leasingAmount<=price){
            Amount = price - leasingAmount;

            view.getTxtBoxPayment().setEditable(false);
            view.getTxtBoxNIC().setEditable(true);
            view.setLblNIC("Enter New Owner NIC");
        }else {
            firstPayemnt = false;
            view.setLblNIC("Owner NIC");
            view.getTxtBoxPayment().setEditable(false);
            view.getTxtBoxNIC().setEditable(false);

        }

        return Amount.toString();
    }*/

    private void payButtonClick() {
        Customer owner = new Customer();
        Payment payment = new Payment();
        Vehicle vehicle = new Vehicle();

        String VID = objSelectedVehicle.getId();
        String OwnerNIC = view.getTxtBoxNIC().getText();
        Double Amount =Double.parseDouble(view.getTxtBoxPayment().getText());



        payment.setID(paymentDAO.generatePaymentID());
        payment.setAmount(Amount);
        vehicle.setId(VID);
        owner.setNIC(OwnerNIC);

        if(firstPayemnt){
            if(customerDAO.checkNIC(owner)){
                boolean rs = vehiclesDAO.UpdateOwner(owner,vehicle);
                if(rs){
                    boolean rsPayment = paymentDAO.FullPayment(vehicle,payment);
                    if(rsPayment){
                        if(Objects.equals(objSelectedVehicle.getStatus(), "Loan Approved")){
                            vehiclesDAO.changeStatus(vehicle,"sold out(Loan)");
                            showMsg("Payment Successful .... ","Success");

                        }else {
                            vehiclesDAO.changeStatus(vehicle,"sold out");
                            showMsg("Payment Successful .... ","Success");
                        }

                    }else {
                        showMsg("Payment Failed .... ","Failed");
                    }
                }else {
                    showMsg("Something went Wrong ...... \nCan't Update Owner","Error");
                }
            }else {
                showMsg("Owner Details NotFound","Error");
            }

        }else {
            boolean rsPayment = paymentDAO.FullPayment(vehicle,payment);
            if(rsPayment){
                boolean rsRemainingAmount = leasingDAO.UpdateRemaining(payment,objSelectedVehicle);
                if (rsRemainingAmount){
                    showMsg("Payment Successful .... ","Success");
                }
                else {
                    showMsg("Failed Update Remaining Amount .... ","error");
                }
                double remainingAmount = leasingDAO.getR_Amount(objSelectedVehicle);
                if(remainingAmount==0.0){
                    vehiclesDAO.changeStatus(vehicle,"sold out");
                }
            }else {
                showMsg("Payment Failed .... ","Failed");
            }

        }

    }

    public void showMsg(String msg,String title){
        JOptionPane optionPane = new JOptionPane(
                msg,
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION
        );

        JDialog dialog = optionPane.createDialog(view, title);
        dialog.setModal(true);
        dialog.setVisible(true);
        view.setVisible(false);
    }

}
