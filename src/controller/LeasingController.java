package controller;

import Model.Vehicle;
import Model.VehiclesDAO;
import view.HomeScreenView;
import view.LeasingView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LeasingController {
    LeasingView view;
    VehiclesDAO vehiclesDAO;
    String ID = "";

    public LeasingController(LeasingView view){
        this.view = view;
        this.vehiclesDAO = new VehiclesDAO();
        DisplayData();

        view.HomeButtonListener(e -> homeButtonClick());
        view.SearchButtonListener(e -> SearchButtonClick());
        view.RequestButtonListener(e -> RequestButtonClick());

        view.TableSelectionListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = view.getTblLeasingVehicle().getSelectedRow();
                if (selectedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) view.getTblLeasingVehicle().getModel();
                    ID = model.getValueAt(selectedRow, 0).toString();
                }
            }
        });

    }

    private void RequestButtonClick() {
        Vehicle objVehicle = new Vehicle();
        objVehicle.setId(ID);
        if(ID.isEmpty()){
            showMsg(" Please Select Vehicles ", "Error");
        }else{
            String status = "Request Loan";
            boolean rs;
            rs = vehiclesDAO.changeStatus(objVehicle,status);
            if (rs) {
                showMsg(" Request Loan Successful", "Success");

            } else {
                showMsg(" Request Loan Failed", "Error");
            }
            DisplayData();
        }
    }
    private void SearchButtonClick() {
        String txtSearch = view.getTxtSearch().getText();
        DefaultTableModel tableModel = vehiclesDAO.EmployeeLeasingSearch(txtSearch);
        view.getTblLeasingVehicle().setModel(tableModel);

    }
    private void DisplayData() {
        DefaultTableModel tableModel = vehiclesDAO.EmployeeLeasingDisplay();
        view.getTblLeasingVehicle().setModel(tableModel);
    }
    private void homeButtonClick() {
        new HomeScreenController(new HomeScreenView());
        view.dispose();
    }

    public void showMsg(String msg,String title){
        JOptionPane.showConfirmDialog(
                view,
                msg,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane. INFORMATION_MESSAGE
        );
    }

}
