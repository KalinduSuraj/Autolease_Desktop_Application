package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class CustomerView extends JFrame{
    private JButton homeButton,searchButton,requestButton,clearButton,addButton,updateButton;
    private JTextField txtSearch,txtNIC,txtName,txtContact,txtAddress;
    private JTable tblCustomer;
    private JLabel lblNIC,lblName,lblContact,lblAddress;
    private JScrollPane jScrollPane;
    private JPanel Mpanel,Flowpanel,panel1,Flowpanel1,leftPanel,Tablepanel,panel2,Inputpanel,btnpanel;

    public CustomerView(){
        InitializeComponent();
    }

    private void InitializeComponent() {

        setTitle("Customer");
        setResizable(false);
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/view/img/Autolease4.png"));
        setIconImage(icon);

        Mpanel = new JPanel(new BorderLayout());
        Mpanel.setBackground(new Color(222, 222, 255));
        add(Mpanel);


        Flowpanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        Flowpanel.setBackground(new Color(222, 222, 255));
        Mpanel.add(Flowpanel,BorderLayout.NORTH);

        homeButton = new JButton("Home");
        homeButton.setBackground(new Color(154, 143, 255));
        homeButton.setFocusPainted(false);
        Flowpanel.add(homeButton);

//************************     Left Panel   ************************************************************
        panel1 = new JPanel(new BorderLayout());
        Mpanel.add(panel1,BorderLayout.CENTER);
        panel1.setBackground(new Color(22, 222, 255));

        Flowpanel1 = new JPanel(new BorderLayout());
        Flowpanel1.setBorder(new EmptyBorder(0, 5, 0, 5));
        panel1.add(Flowpanel1,BorderLayout.NORTH);
        Flowpanel1.setBackground(new Color(222, 222, 255));

        leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(222, 222, 255));

        txtSearch = new JTextField(15);
        txtSearch.setSize(getWidth(),30);
        leftPanel.add(txtSearch);

        searchButton = new JButton("Search");
        searchButton.setBackground(new Color(154, 143, 255));
        searchButton.setFocusPainted(false);
        leftPanel.add(searchButton);

        Flowpanel1.add(leftPanel,BorderLayout.WEST);

        Tablepanel = new JPanel(new BorderLayout());
        Tablepanel.setBorder(new EmptyBorder(10, 10, 15, 10));
        panel1.add(Tablepanel,BorderLayout.CENTER);
        Tablepanel.setBackground(new Color(222, 222, 255));

        tblCustomer = new JTable();
        jScrollPane = new JScrollPane(tblCustomer);
        jScrollPane.setBackground(new Color(222, 222, 255));
        jScrollPane.getViewport().setBackground(Color.WHITE);

        Tablepanel.add(jScrollPane);

        panel2 = new JPanel(new BorderLayout());
        panel2.setBackground(new Color(222, 222, 255));
        panel2.setBorder(new EmptyBorder(0, 10, 0, 10));
        Mpanel.add(panel2,BorderLayout.EAST);

        Inputpanel = new JPanel(new GridBagLayout());
        Inputpanel.setBackground(new Color(222, 222, 255));
        panel2.add(Inputpanel,BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lblNIC =new JLabel("NIC :");
        gbc.gridx = 0;
        gbc.gridy = 0;
        Inputpanel.add(lblNIC, gbc);

        txtNIC = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        Inputpanel.add(txtNIC, gbc);

        lblName = new JLabel("Name :");
        gbc.gridx = 0;
        gbc.gridy = 2;
        Inputpanel.add(lblName, gbc);

        txtName = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 3;
        Inputpanel.add(txtName, gbc);

        lblContact = new JLabel("Contact :");
        gbc.gridx = 0;
        gbc.gridy = 4;
        Inputpanel.add(lblContact, gbc);

        txtContact = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 5;
        Inputpanel.add(txtContact, gbc);

        lblAddress = new JLabel("Address :");
        gbc.gridx = 0;
        gbc.gridy = 6;
        Inputpanel.add(lblAddress, gbc);

        txtAddress = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 7;
        Inputpanel.add(txtAddress, gbc);

        btnpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnpanel.setBackground(new Color(222, 222, 255));
        btnpanel.setBorder(new EmptyBorder(0, 5, 80, 5));
        panel2.add(btnpanel,BorderLayout.SOUTH);

        clearButton = new JButton("Clear");
        clearButton.setBackground(new Color(154, 143, 255));
        clearButton.setFocusPainted(false);
        btnpanel.add(clearButton);

        updateButton = new JButton("Update");
        updateButton.setBackground(new Color(154, 143, 255));
        updateButton.setFocusPainted(false);
        btnpanel.add(updateButton);

        addButton = new JButton("Add");
        addButton.setBackground(new Color(154, 143, 255));
        addButton.setFocusPainted(false);
        btnpanel.add(addButton);

        setVisible(true);
    }

    public void HomeButtonListener(ActionListener listener) {
        homeButton.addActionListener(listener);
    }
    public void SearchButtonListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }
    public void ClearButtonListener(ActionListener listener) {
        clearButton.addActionListener(listener);
    }
    public void UpdateButtonListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }
    public void AddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }
    public void TableSelectionListener(MouseAdapter listener) {
        tblCustomer.addMouseListener(listener);
    }

    public JTextField getTextBoxNIC(){
        return txtNIC;
    }
    public JTextField getTextBoxName(){
        return txtName;
    }
    public JTable getTblEmployee() {
        return tblCustomer;
    }
    public String getTxtSearch() {
        return txtSearch.getText().trim();
    }
    public String getTxtNIC() {
        return txtNIC.getText().trim();
    }
    public String getTxtName() {
        return txtName.getText().trim();
    }
    public String getTxtContact() {
        return txtContact.getText().trim();
    }
    public String getTxtAddress() {
        return txtAddress.getText().trim();
    }

    public void setTxtNIC(String txtNIC) {
        this.txtNIC.setText(txtNIC);
    }
    public void setTxtName(String txtName) {
        this.txtName.setText(txtName);
    }
    public void setTxtContact(String txtContact) {
        this.txtContact.setText(txtContact);
    }
    public void setTxtAddress(String txtAddress) {
        this.txtAddress.setText(txtAddress);
    }
}
