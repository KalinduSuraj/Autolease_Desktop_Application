package controller;

import view.*;

import javax.swing.*;

public class AdminHomeController {
    AdminHomeView view;

    public AdminHomeController(AdminHomeView view) {
        this.view= view;
        view.leasingButtonListener(e -> leasingButtonClick());
        view.loanButtonListener(e -> loanButtonClick());
        view.employeeButtonListener(e -> employeeButtonClick());
        view.logoutButtonListener(e -> logoutButtonClick());

    }

    private void employeeButtonClick() {
        new AdminEmployeeController(new AdminEmployeeView());
        view.dispose();
    }
    private void leasingButtonClick() {
        new AdminLeasingController(new AdminLeasingView());
        view.dispose();
    }
    private void loanButtonClick() {
        new AdminLoanController(new AdminLoanView());
        view.dispose();
    }
    private void logoutButtonClick() {

        int response = JOptionPane.showConfirmDialog(
                view, "Are you sure you want to log out?",
                "Confirm Logout",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE
        );

        if (response == JOptionPane.OK_OPTION) {
            new UserLoginController(new UserLoginView());
            view.dispose();
        }
    }

}
