package controller;

import Model.Customer;
import Model.CustomerDAO;
import Model.User;
import Model.UserDAO;
import view.AdminHomeView;
import view.CustomerView;
import view.HomeScreenView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerController {
    CustomerView view;
    CustomerDAO customerDAO;

    public CustomerController(CustomerView view){
        this.view = view;
        this.customerDAO = new CustomerDAO();
        DisplayData();
        view.HomeButtonListener(e -> homeButtonClick());
        view.SearchButtonListener(e -> searchButtonClick());
        view.ClearButtonListener(e -> ClearButtonClick());
        view.UpdateButtonListener(e -> updateButtonClick());
        view.AddButtonListener(e -> addButtonClick());

        view.TableSelectionListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = view.getTblEmployee().getSelectedRow();
                if (selectedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) view.getTblEmployee().getModel();
                    view.setTxtNIC(model.getValueAt(selectedRow, 0).toString());
                    view.setTxtName(model.getValueAt(selectedRow, 1).toString());
                    view.setTxtContact(model.getValueAt(selectedRow, 2).toString());
                    view.setTxtAddress(model.getValueAt(selectedRow, 3).toString());

                    view.getTextBoxNIC().setEditable(false);
                    view.getTextBoxName().setEditable(false);

                }
            }
        });
    }

    private void ClearButtonClick() {
        clear();
        view.getTextBoxNIC().setEditable(true);
        view.getTextBoxName().setEditable(true);
    }
    private void DisplayData() {
        DefaultTableModel tableModel = customerDAO.DisplayCustomer();
        view.getTblEmployee().setModel(tableModel);
    }
    private void addButtonClick() {
        Customer customer = new Customer();
        String error ="";
        String NIC = view.getTxtNIC();
        String Name = view.getTxtName();
        String Contact = view.getTxtContact();
        String Address = view.getTxtAddress();

        if(NIC.isEmpty()){
            error = error+"\nEnter NIC ...";
        }
        if(Name.isEmpty()){
            error = error+"\nEnter Name ...";
        }
        if(Contact.isEmpty()){
            error = error+"\nEnter Contact ...";
        }else {
            try {
                int num = Integer.parseInt(Contact);
                if (Contact.length() != 10) {
                    error += "\nContact number must be exactly 10 digits.";
                }
            }catch (NumberFormatException ex){
                error += "\nContact number must be numeric.";
            }
        }
        if(Address.isEmpty()){
            error = error+"\nEnter Address ...";
        }
        if(!error.isEmpty()){
            showMsg(error,"Error");
        }else {
            customer.setNIC(NIC);
            customer.setName(Name);
            customer.setContact(Contact);
            customer.setAddress(Address);

            if(!customerDAO.checkNIC(customer)){
                boolean result;
                result = customerDAO.AddCustomer(customer);
                if(result){
                    showMsg("Customer Added Successful","Success");
                    clear();
                }else {
                    showMsg("Customer Added Failed","Error");
                }
            }else {
                showMsg("Existing Customer","Error");
            }
        }
        DisplayData();
    }
    private void updateButtonClick() {
        Customer customer = new Customer();
        String error ="";
        String NIC = view.getTxtNIC();
        String Contact = view.getTxtContact();
        String Address = view.getTxtAddress();

        if(Contact.isEmpty()){
            error = error+"\nEnter Contact ...";
        }
        if(Address.isEmpty()){
            error = error+"\nEnter Password ...";
        }
        if(!error.isEmpty()){
            showMsg(error,"Error");
        }else {
            customer.setNIC(NIC);
            customer.setContact(Contact);
            customer.setAddress(Address);

            boolean result;
            result = customerDAO.UpdateCustomer(customer);
            if(result){
                showMsg("Updated Successful","Success");
                clear();
            }else {
                showMsg("Updated Failed","Error");
            }
        }
        ClearButtonClick();
        DisplayData();
    }
    private void searchButtonClick() {
        String txtSearch = view.getTxtSearch();

        DefaultTableModel tableModel = customerDAO.SearchCustomer(txtSearch);
        view.getTblEmployee().setModel(tableModel);
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

    private void clear(){
        view.setTxtNIC("");
        view.setTxtName("");
        view.setTxtContact("");
        view.setTxtAddress("");
    }

}
