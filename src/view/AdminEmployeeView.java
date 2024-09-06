package view;

import Model.VehiclesDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Objects;

public class AdminEmployeeView extends JFrame {
    private JButton homeButton, searchButton, deleteButton, updateButton, addButton,clearButton;
    private JTextField txtSearch, txtUsername, txtName, txtContact, txtPassword;
    private JTable tblEmployee;
    private JScrollPane jScrollPane;
    private JLabel lblUsername,lblName,lblContact,lblPassword;

    VehiclesDAO vehiclesDAO;

    public AdminEmployeeView() {
        InitializeComponent();
    }

    private void InitializeComponent() {

        setTitle("Employee");
        setResizable(false);
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/view/img/Autolease4.png"));
        setIconImage(icon);

        JPanel Mpanel = new JPanel(new BorderLayout());
        Mpanel.setBackground(new Color(222, 222, 255));
        add(Mpanel);

        //Flow Panel
        JPanel Flowpanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        Flowpanel.setBackground(new Color(222, 222, 255));
        Mpanel.add(Flowpanel,BorderLayout.NORTH);

        // Home button
        homeButton = new JButton("Home");
        homeButton.setBackground(new Color(154, 143, 255));
        homeButton.setFocusPainted(false);
        Flowpanel.add(homeButton);

//************************     Left Panel   ************************************************************
        //Left Panel
        JPanel panel1 = new JPanel(new BorderLayout());
        Mpanel.add(panel1,BorderLayout.CENTER);
        panel1.setBackground(new Color(22, 222, 255));

        //left panel flow
        JPanel Flowpanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        Flowpanel1.setBorder(new EmptyBorder(0, 5, 0, 5));
        panel1.add(Flowpanel1,BorderLayout.NORTH);
        Flowpanel1.setBackground(new Color(222, 222, 255));

        // Search textbox and button
        txtSearch = new JTextField(15);
        txtSearch.setSize(getWidth(),30);
        Flowpanel1.add(txtSearch);

        searchButton = new JButton("Search");
        searchButton.setBackground(new Color(154, 143, 255));
        searchButton.setFocusPainted(false);
        Flowpanel1.add(searchButton);


        //Table panel
        JPanel Tablepanel = new JPanel(new BorderLayout());
        Tablepanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel1.add(Tablepanel,BorderLayout.CENTER);
        Tablepanel.setBackground(new Color(222, 222, 255));

        tblEmployee = new JTable();
        jScrollPane = new JScrollPane(tblEmployee);
        jScrollPane.setBackground(new Color(222, 222, 255));
        jScrollPane.getViewport().setBackground(Color.WHITE);

        Tablepanel.add(jScrollPane);




//************************     Right Panel   ************************************************************
        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setBackground(new Color(222, 222, 255));
        panel2.setBorder(new EmptyBorder(0, 10, 0, 10));
        Mpanel.add(panel2,BorderLayout.EAST);

        JPanel Inputpanel = new JPanel(new GridBagLayout());
        Inputpanel.setBackground(new Color(222, 222, 255));
        panel2.add(Inputpanel,BorderLayout.CENTER);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        // User Name
        lblUsername =new JLabel("User Name :");
        gbc.gridx = 0;
        gbc.gridy = 0;
        Inputpanel.add(lblUsername, gbc);

        txtUsername = new JTextField(24);
        gbc.gridx = 0;
        gbc.gridy = 1;
        Inputpanel.add(txtUsername, gbc);

        //Name
        lblName = new JLabel("Name :");
        gbc.gridx = 0;
        gbc.gridy = 2;
        Inputpanel.add(lblName, gbc);

        txtName = new JTextField(24);
        gbc.gridx = 0;
        gbc.gridy = 3;
        Inputpanel.add(txtName, gbc);

        // Contact
        lblContact = new JLabel("Contact:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        Inputpanel.add(lblContact, gbc);

        txtContact = new JTextField(24);
        gbc.gridx = 0;
        gbc.gridy = 5;
        Inputpanel.add(txtContact, gbc);

        //Password
        lblPassword = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        Inputpanel.add(lblPassword, gbc);

        txtPassword = new JTextField(24);
        gbc.gridx = 0;
        gbc.gridy = 7;
        Inputpanel.add(txtPassword, gbc);



        JPanel btnpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnpanel.setBackground(new Color(222, 222, 255));
        btnpanel.setBorder(new EmptyBorder(0, 5, 80, 5));
        panel2.add(btnpanel,BorderLayout.SOUTH);

        clearButton = new JButton("Clear");
        clearButton.setBackground(new Color(154, 143, 255));
        clearButton.setFocusPainted(false);
        btnpanel.add(clearButton);

        // Action buttons (Delete, Update, Add)
        deleteButton = new JButton("Delete");
        deleteButton.setBackground(new Color(154, 143, 255));
        deleteButton.setFocusPainted(false);
        btnpanel.add(deleteButton);

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
    public void DeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }
    public void UpdateButtonListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }
    public void AddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }
    public void TableSelectionListener(MouseAdapter listener) {
        tblEmployee.addMouseListener(listener);
    }

    public JTextField getTextBoxUserName(){
        return txtUsername;
    }
    public JTextField getTextBoxName(){
        return txtName;
    }
    public JTable getTblEmployee() {
        return tblEmployee;
    }

    public String getTxtSearch() {
        return txtSearch.getText().trim();
    }
    public String getTxtUsername() {
        return txtUsername.getText().trim();
    }
    public String getTxtName() {
        return txtName.getText().trim();
    }
    public String getTxtContact() {
        return txtContact.getText().trim();
    }
    public String getTxtPassword() {
        return txtPassword.getText().trim();
    }

    public void setTxtUsername(String txtUsername) {
        this.txtUsername.setText(txtUsername);
    }
    public void setTxtName(String txtName) {
        this.txtName.setText(txtName);
    }
    public void setTxtContact(String txtContact) {
        this.txtContact.setText(txtContact);
    }
    public void setTxtPassword(String txtPassword) {
        this.txtPassword.setText(txtPassword);
    }

    public void btnDisable(String username) {
        if(Objects.equals(username, "admin")){
            deleteButton.setEnabled(false);
            addButton.setEnabled(false);
        }else {
            addButton.setEnabled(false);
            deleteButton.setEnabled(true);
        }
    }

    public void btnEnable(){
        deleteButton.setEnabled(true);
        addButton.setEnabled(true);
        updateButton.setEnabled(true);
    }

}
