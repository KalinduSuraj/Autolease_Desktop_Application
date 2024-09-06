package controller;

import Model.User;
import Model.UserDAO;
import view.AdminEmployeeView;
import view.AdminHomeView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminEmployeeController {
    AdminEmployeeView view;
    UserDAO userDAO;

    public AdminEmployeeController(AdminEmployeeView view){
        this.view=view;
        this.userDAO = new UserDAO();
        DisplayData();
        view.HomeButtonListener(e -> homeButtonClick());
        view.SearchButtonListener(e -> searchButtonClick());
        view.ClearButtonListener(e -> ClearButtonClick());
        view.DeleteButtonListener(e -> deleteButtonClick());
        view.UpdateButtonListener(e -> updateButtonClick());
        view.AddButtonListener(e -> addButtonClick());

        view.TableSelectionListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = view.getTblEmployee().getSelectedRow();
                if (selectedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) view.getTblEmployee().getModel();
                    view.setTxtUsername(model.getValueAt(selectedRow, 0).toString());
                    view.setTxtName(model.getValueAt(selectedRow, 1).toString());
                    view.setTxtContact(model.getValueAt(selectedRow, 2).toString());
                    view.setTxtPassword(model.getValueAt(selectedRow, 3).toString());

                    view.getTextBoxUserName().setEditable(false);
                    view.getTextBoxName().setEditable(false);
                    view.btnDisable(model.getValueAt(selectedRow, 0).toString());

                }
            }
        });
    }

    private void ClearButtonClick() {
        clear();
        view.getTextBoxUserName().setEditable(true);
        view.getTextBoxName().setEditable(true);
    }
    private void addButtonClick() {
        User user = new User();
        String error ="";
        String UserName = view.getTxtUsername();
        String Name = view.getTxtName();
        String Contact = view.getTxtContact();
        String Password = view.getTxtPassword();

        if(UserName.isEmpty()){
            error = error+"\nEnter User Name ...";
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
        if(Password.isEmpty()){
            error = error+"\nEnter Password ...";
        }
        if(!error.isEmpty()){
            showMsg(error,"Error");
        }else {
            user.setUserName(UserName);
            user.setName(Name);
            user.setContact(Contact);
            user.setPassword(Password);

            if(!userDAO.checkUserName(user)){
                boolean result;
                result = userDAO.AddUser(user);
                if(result){
                    showMsg("User Added Successful","Success");
                    clear();
                }else {
                    showMsg("User Added Failed","Error");
                }
            }else {
                showMsg("Existing User Name","Error");
            }
        }
        DisplayData();
    }
    private void updateButtonClick() {
        User user = new User();
        String error ="";
        String UserName = view.getTxtUsername();
        String Contact = view.getTxtContact();
        String Password = view.getTxtPassword();

        if(Contact.isEmpty()){
            error = error+"\nEnter Contact ...";
        }
        if(Password.isEmpty()){
            error = error+"\nEnter Password ...";
        }
        if(!error.isEmpty()){
            showMsg(error,"Error");
        }else {
            user.setUserName(UserName);
            user.setContact(Contact);
            user.setPassword(Password);

            boolean result;
            result = userDAO.UpdateUser(user);
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
    private void deleteButtonClick() {
        User user = new User();
        String UserName = view.getTxtUsername();

        user.setUserName(UserName);

        boolean result;
        result = userDAO.DeleteUser(user);
        if(result){
            showMsg("Deleted Successful","Success");
            clear();
        }else {
            showMsg("Delete Failed","Error");
        }

        ClearButtonClick();
        DisplayData();
    }
    private void searchButtonClick() {
        String txtSearch = view.getTxtSearch();

        DefaultTableModel tableModel = userDAO.SearchUser(txtSearch);
        view.getTblEmployee().setModel(tableModel);
    }
    private void homeButtonClick() {
        new AdminHomeController(new AdminHomeView());
        view.dispose();
    }

    private void DisplayData() {
        DefaultTableModel tableModel = userDAO.DisplayUser();
        view.getTblEmployee().setModel(tableModel);
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
        view.setTxtUsername("");
        view.setTxtName("");
        view.setTxtContact("");
        view.setTxtPassword("");
        view.btnEnable();
    }
}
