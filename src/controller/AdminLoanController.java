package controller;

import Model.Vehicle;
import Model.VehiclesDAO;
import view.AdminHomeView;
import view.AdminLoanView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AdminLoanController {
    AdminLoanView view;
    VehiclesDAO vehiclesDAO;
    String ID="";

    public AdminLoanController(AdminLoanView view){
        this.view = view;
        this.vehiclesDAO = new VehiclesDAO();
        DisplayData();

        view.HomeButtonListener(e -> homeButtonClick());
        view.SearchButtonListener(e -> SearchButtonClick());
        view.approveButtonListener(e -> ApproveButtonClick());

        view.TableSelectionListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = view.getTblLoan().getSelectedRow();
                if (selectedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) view.getTblLoan().getModel();
                    ID = model.getValueAt(selectedRow, 0).toString();
                }
            }
        });

    }

    private void ApproveButtonClick() {
        Vehicle objVehicle = new Vehicle();
        objVehicle.setId(ID);
        if(ID.isEmpty()){
            showMsg(" Please Select Vehicles ", "Error");
        }else{
            String status = "Loan Approved";
            boolean rs;
            rs = vehiclesDAO.changeStatus(objVehicle,status);
            if (rs) {
                showMsg(" Loan Approve Successful", "Success");

            } else {
                showMsg(" Loan Failed", "Error");
            }
            DisplayData();
        }
    }
    private void SearchButtonClick() {
        String txtSearch = view.getTxtSearch().getText();
        DefaultTableModel tableModel = vehiclesDAO.LoanSearch(txtSearch);
        view.getTblLoan().setModel(tableModel);
    }
    private void homeButtonClick() {
        new AdminHomeController(new AdminHomeView());
        view.dispose();
    }

    private void DisplayData() {
        DefaultTableModel tableModel = vehiclesDAO.LoanDisplay();
        view.getTblLoan().setModel(tableModel);
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
