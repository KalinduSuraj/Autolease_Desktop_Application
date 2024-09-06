package controller;

import view.*;

import javax.swing.*;

public class HomeScreenController {
    HomeScreenView view;

    public HomeScreenController(HomeScreenView view){
        this.view=view;

        view.AddvehiclesButtonListener(e -> vehiclesButtonClick());
        view.AddleasingButtonListener(e -> leasingButtonClick());
        view.AddpaymentsButtonListener(e -> paymentsButtonClick());
        view.AddcustomersButtonListener(e -> customersButtonClick());
        view.AddlogoutButtonListener(e -> logoutButtonClick());

    }

    private void vehiclesButtonClick() {
        new VehiclesController(new VehiclesView());
        view.dispose();
    }
    private void leasingButtonClick() {
        new LeasingController(new LeasingView());
        view.dispose();
    }
    private void paymentsButtonClick() {
        new PaymentController(new PaymentView());
        view.dispose();
    }
    private void customersButtonClick() {
        new CustomerController(new CustomerView());
        view.dispose();
    }
    private void logoutButtonClick() {
        // Show confirmation dialog
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
