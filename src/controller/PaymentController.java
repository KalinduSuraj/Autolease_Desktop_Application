package controller;

import Model.PaymentDAO;
import view.HomeScreenView;
import view.PaymentView;

import javax.swing.table.DefaultTableModel;

public class PaymentController {
    PaymentDAO paymentDAO;
    PaymentView view;

    public PaymentController(PaymentView view){
        this.view = view;
        this.paymentDAO = new PaymentDAO();
        DisplayData();
        view.HomeButtonListener(e -> homeButtonClick());
        view.SearchButtonListener(e -> SearchButtonClick());
    }

    private void DisplayData() {
        DefaultTableModel tableModel = paymentDAO.Display();
        view.getTblPayment().setModel(tableModel);
    }

    private void SearchButtonClick() {
        String txtSearch = view.getTxtSearch();

        DefaultTableModel tableModel = paymentDAO.SearchPayment(txtSearch);
        view.getTblPayment().setModel(tableModel);
    }

    private void homeButtonClick() {
        new HomeScreenController(new HomeScreenView());
        view.dispose();
    }
}
