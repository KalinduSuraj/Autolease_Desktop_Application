package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class PaymentView extends JFrame {
    private JButton homeButton,searchButton;
    private JTextField txtSearch;
    private JTable tblPayment;
    private JScrollPane jScrollPane;

    public PaymentView(){
        InitializeComponent();
    }

    private void InitializeComponent() {
        setTitle("Payment");
        setResizable(false);
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/view/img/Autolease4.png"));
        setIconImage(icon);

        JPanel Mpanel = new JPanel(new BorderLayout());
        Mpanel.setBackground(new Color(222, 222, 255)); // Background color
        add(Mpanel);

        JPanel Flowpanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        Flowpanel.setBackground(new Color(222, 222, 255)); // Background color
        Mpanel.add(Flowpanel,BorderLayout.NORTH);

        homeButton = new JButton("Home");
        homeButton.setBackground(new Color(154, 143, 255));
        homeButton.setFocusPainted(false);
        Flowpanel.add(homeButton);

//************************     Left Panel   ************************************************************
        JPanel panel1 = new JPanel(new BorderLayout());
        Mpanel.add(panel1,BorderLayout.CENTER);
        panel1.setBackground(new Color(22, 222, 255));

        JPanel Flowpanel1 = new JPanel(new BorderLayout());
        Flowpanel1.setBorder(new EmptyBorder(0, 5, 0, 5));
        panel1.add(Flowpanel1,BorderLayout.NORTH);
        Flowpanel1.setBackground(new Color(222, 222, 255));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(222, 222, 255));

        txtSearch = new JTextField(15);
        txtSearch.setSize(getWidth(),30);
        leftPanel.add(txtSearch);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setBackground(new Color(222, 222, 255));

        searchButton = new JButton("Search");
        searchButton.setBackground(new Color(154, 143, 255));
        searchButton.setFocusPainted(false);
        leftPanel.add(searchButton);

        Flowpanel1.add(leftPanel,BorderLayout.WEST);
        Flowpanel1.add(rightPanel,BorderLayout.EAST);

        JPanel Tablepanel = new JPanel(new BorderLayout());
        Tablepanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel1.add(Tablepanel,BorderLayout.CENTER);
        Tablepanel.setBackground(new Color(222, 222, 255));

        tblPayment = new JTable();
        jScrollPane = new JScrollPane(tblPayment);
        jScrollPane.setBackground(new Color(222, 222, 255));
        jScrollPane.getViewport().setBackground(Color.WHITE);

        Tablepanel.add(jScrollPane);

        setVisible(true);
    }

    public void HomeButtonListener(ActionListener listener) {
        homeButton.addActionListener(listener);
    }
    public void SearchButtonListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    public JTable getTblPayment() {
        return tblPayment;
    }
    public String getTxtSearch() {
        return txtSearch.getText();
    }

}
