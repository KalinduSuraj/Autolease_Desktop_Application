package controller;

import Model.Leasing;
import Model.LeasingDAO;
import Model.Vehicle;
import Model.VehiclesDAO;
import view.ApproveLeasingView;

import javax.swing.*;

public class ApproveLeasingController {
    ApproveLeasingView view;
    LeasingDAO leasingDAO;
    VehiclesDAO vehiclesDAO;
    Vehicle objvehicle;
    Leasing objleasing;
    String VID;

    public ApproveLeasingController(ApproveLeasingView view, String ID){
        this.view = view;
        this.VID = ID;
        leasingDAO = new LeasingDAO();
        vehiclesDAO = new VehiclesDAO();
        objleasing = new Leasing();
        view.btnApproveListener(e -> approveButtonClick());
    }

    private void approveButtonClick() {
        String Amount = view.getTxtAmount();
        objvehicle = new Vehicle();
        objvehicle.setId(VID);

        objleasing.setLeasingID(leasingDAO.generateID());
        objleasing.setAmount(Double.parseDouble(Amount));


        boolean rs1 = false;
        rs1 = vehiclesDAO.changeStatus(objvehicle,"Leasing Approved");
        if(rs1){
            boolean rs = false;
            rs = leasingDAO.addLeasing(objleasing,objvehicle);
            showmsg("Leasing Approve Successful","Success");
        }else {
            rs1 = vehiclesDAO.changeStatus(objvehicle,"Request Leasing");
            showmsg("Leasing Approve Failed","Error");
        }

    }

    private void showmsg(String msg,String title){
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
