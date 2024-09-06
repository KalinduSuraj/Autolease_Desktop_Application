package controller;

import Model.Vehicle;
import Model.VehiclesDAO;
import view.AdminHomeView;
import view.AdminLeasingView;
import view.ApproveLeasingView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminLeasingController {
    AdminLeasingView view;
    VehiclesDAO vehiclesDAO;
    String ID = "";

    public AdminLeasingController(AdminLeasingView view){
        this.view = view;
        vehiclesDAO = new VehiclesDAO();
        DisplayData();

        view.HomeButtonListener(e -> homeButtonClick());
        view.SearchButtonListener(e -> SearchButtonClick());
        view.approveButtonListener(e -> ApproveButtonClick());

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

    private void ApproveButtonClick() {

        if(ID.isEmpty()){
            showMsg(" Please Select Vehicles ", "Error");
        }else{
            new ApproveLeasingController(new ApproveLeasingView(), ID);
        }
        DisplayData();


    }
    private void SearchButtonClick() {
        String txtSearch = view.getTxtSearch().getText();
        DefaultTableModel tableModel = vehiclesDAO.LeasingSearch(txtSearch);
        view.getTblLeasingVehicle().setModel(tableModel);
    }
    private void homeButtonClick() {
        new AdminHomeController(new AdminHomeView());
        view.dispose();
    }

    private void DisplayData() {
        DefaultTableModel tableModel = vehiclesDAO.LeasingDisplay();
        view.getTblLeasingVehicle().setModel(tableModel);
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
