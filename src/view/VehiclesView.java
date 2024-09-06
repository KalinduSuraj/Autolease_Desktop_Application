package view;

import Model.VehiclesDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Objects;

public class VehiclesView extends JFrame {
    private JButton homeButton, searchButton, requestButton, uploadButton, payButton, clearButton, deleteButton, updateButton, addButton;
    private JTextField txtSearch, txtVIN, txtModel, txtYear, txtPrice, txtStatus,txtNIC;
    private JTable tblVehicles;
    private JScrollPane jScrollPane;
    private JLabel lblID,lblVehicleImage,lblStatus,lblVIN,lblModel,lblYear,lblPrice,lblNIC;
    private JPanel Inputpanel,ImagePanel,btnpanel,Mpanel,Flowpanel,panel1,panel2,Flowpanel1,leftPanel,rightPanel,Tablepanel,BFlowpanel1,BleftPanel,BrightPanel;
    private String imageFilePath;

    VehiclesDAO vehiclesDAO;

    public VehiclesView() {
        InitializeComponent();
        this.vehiclesDAO = new VehiclesDAO();
    }

    private void InitializeComponent() {

        setTitle("Vehicles");
        setResizable(false);
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set application icon
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/view/img/Autolease4.png"));
        setIconImage(icon);

        // Create main panel
        Mpanel = new JPanel(new BorderLayout());
        Mpanel.setBackground(new Color(222, 222, 255));
        add(Mpanel);

        //Flow Panel
        Flowpanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        Flowpanel.setBackground(new Color(222, 222, 255));
        Mpanel.add(Flowpanel,BorderLayout.NORTH);

            // Home button
            homeButton = new JButton("Home");
            homeButton.setBackground(new Color(154, 143, 255));
            homeButton.setFocusPainted(false);
            Flowpanel.add(homeButton);

//************************     Left Panel   ************************************************************
        //Left Panel
        panel1 = new JPanel(new BorderLayout());
        Mpanel.add(panel1,BorderLayout.CENTER);
        panel1.setBackground(new Color(22, 222, 255));

            //left panel flow
            Flowpanel1 = new JPanel(new BorderLayout());
            Flowpanel1.setBorder(new EmptyBorder(0, 5, 0, 5));
            panel1.add(Flowpanel1,BorderLayout.NORTH);
            Flowpanel1.setBackground(new Color(222, 222, 255));

                // Left panel for text field and button2
                leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                leftPanel.setBackground(new Color(222, 222, 255));


                // Search textbox and button
                txtSearch = new JTextField(15);
                txtSearch.setSize(getWidth(),30);
                leftPanel.add(txtSearch);

                searchButton = new JButton("Search");
                searchButton.setBackground(new Color(154, 143, 255));
                searchButton.setFocusPainted(false);
                leftPanel.add(searchButton);

                // Right panel for Request button
                rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                rightPanel.setBackground(new Color(222, 222, 255));
                // Request button
                requestButton = new JButton("Request");
                requestButton.setBackground(new Color(154, 143, 255));
                requestButton.setFocusPainted(false);
                rightPanel.add(requestButton);

                // Add  panels to  main panel
                Flowpanel1.add(leftPanel,BorderLayout.WEST);
                Flowpanel1.add(rightPanel,BorderLayout.EAST);


            //Table panel
            Tablepanel = new JPanel(new BorderLayout());
            Tablepanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            panel1.add(Tablepanel,BorderLayout.CENTER);
            Tablepanel.setBackground(new Color(222, 222, 255));

                // Table for vehicles
                tblVehicles = new JTable();
                jScrollPane = new JScrollPane(tblVehicles);
                jScrollPane.setBackground(new Color(222, 222, 255));
                jScrollPane.getViewport().setBackground(Color.WHITE);

                Tablepanel.add(jScrollPane);

            //left panel Bottom
            BFlowpanel1 = new JPanel(new BorderLayout());
            panel1.add(BFlowpanel1,BorderLayout.SOUTH);
            BFlowpanel1.setBorder(new EmptyBorder(0, 5, 0, 5));
            BFlowpanel1.setBackground(new Color(222, 222, 255));

                // Left panel
                BleftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                BleftPanel.setBackground(new Color(222, 222, 255));


                    // Status field
                    lblStatus=new JLabel("Status:");
                    BleftPanel.add(lblStatus);

                    txtStatus = new JTextField(10);
                    txtStatus.setEditable(false);
                    BleftPanel.add(txtStatus);


                // Right panel
                BrightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                BrightPanel.setBackground(new Color(222, 222, 255));
                    //Buy Button
                    payButton = new JButton("Pay");
                    payButton.setBackground(new Color(154, 143, 255));
                    payButton.setFocusPainted(false);
                    BrightPanel.add(payButton);

                // Add panels to main panel
                BFlowpanel1.add(BleftPanel,BorderLayout.WEST);
                BFlowpanel1.add(BrightPanel,BorderLayout.EAST);


//************************     Right Panel   ************************************************************
        panel2 = new JPanel(new BorderLayout());
        panel2.setBackground(new Color(222, 222, 255));
        panel2.setBorder(new EmptyBorder(0, 10, 0, 10));
        Mpanel.add(panel2,BorderLayout.EAST);


            ImagePanel = new JPanel(new BorderLayout());
            ImagePanel.setBackground(new Color(222, 222, 255));
            panel2.add(ImagePanel,BorderLayout.NORTH);

                lblID = new JLabel();
                ImagePanel.add(lblID,BorderLayout.NORTH);
                // Vehicle image label
                lblVehicleImage = new JLabel("Vehicle Image", SwingConstants.CENTER);
                lblVehicleImage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                lblVehicleImage.setPreferredSize(new Dimension(150, 100));
                ImagePanel.add(lblVehicleImage,BorderLayout.CENTER);

                uploadButton = new JButton("Upload");
                uploadButton.setBackground(new Color(154, 143, 255));
                uploadButton.setFocusPainted(false);
                uploadButton.setPreferredSize(new Dimension(100, 30));
                ImagePanel.add(uploadButton,BorderLayout.SOUTH );

            Inputpanel = new JPanel(new GridBagLayout());
            Inputpanel.setBackground(new Color(222, 222, 255));
            panel2.add(Inputpanel,BorderLayout.CENTER);


                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 0, 5);
                gbc.fill = GridBagConstraints.HORIZONTAL;


                    // VIN
                    lblVIN =new JLabel("VIN:");
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    Inputpanel.add(lblVIN, gbc);

                    txtVIN = new JTextField(20);
                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    Inputpanel.add(txtVIN, gbc);

                    //Model
                    lblModel = new JLabel("Model:");
                    gbc.gridx = 0;
                    gbc.gridy = 2;
                    Inputpanel.add(lblModel, gbc);

                    txtModel = new JTextField(25);
                    gbc.gridx = 0;
                    gbc.gridy = 3;
                    Inputpanel.add(txtModel, gbc);

                    // Year
                    lblYear = new JLabel("Year:");
                    gbc.gridx = 0;
                    gbc.gridy = 4;
                    Inputpanel.add(lblYear, gbc);

                    txtYear = new JTextField(20);
                    gbc.gridx = 0;
                    gbc.gridy = 5;
                    Inputpanel.add(txtYear, gbc);

                    //Price
                    lblPrice = new JLabel("Price:");
                    gbc.gridx = 0;
                    gbc.gridy = 6;
                    Inputpanel.add(lblPrice, gbc);

                    txtPrice = new JTextField(20);
                    gbc.gridx = 0;
                    gbc.gridy = 7;
                    Inputpanel.add(txtPrice, gbc);

                    //Price
                    lblNIC = new JLabel("Owner NIC:");
                    gbc.gridx = 0;
                    gbc.gridy = 8;
                    Inputpanel.add(lblNIC, gbc);

                    txtNIC = new JTextField(20);
                    gbc.gridx = 0;
                    gbc.gridy = 9;
                    Inputpanel.add(txtNIC, gbc);



            btnpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            btnpanel.setBackground(new Color(222, 222, 255));
            panel2.add(btnpanel,BorderLayout.SOUTH);

                clearButton = new JButton("Clear");
                clearButton.setBackground(new Color(154, 143, 255));
                clearButton.setFocusPainted(false);
                btnpanel.add(clearButton);

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
    public void RequestButtonListener(ActionListener listener) {
        requestButton.addActionListener(listener);
    }
    public void UploadButtonListener(ActionListener listener) {
        uploadButton.addActionListener(listener);
    }
    public void PayButtonListener(ActionListener listener) {
        payButton.addActionListener(listener);
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
        tblVehicles.addMouseListener(listener);
    }

    public JTable getTblVehicles() {
        return tblVehicles;
    }
    public JLabel getImgLabel(){
        return lblVehicleImage;
    }

    public void setTxtSearch(String txtUsername) {
        this.txtSearch.setText(txtUsername);
    }
    public void setTxtVIN(String vin) {
        this.txtVIN.setText(vin);
    }
    public void setTxtModel(String model) {
        this.txtModel.setText(model);
    }
    public void setTxtPrice(String price) {
        this.txtPrice.setText(price);
    }
    public void setTxtYear(String year) {
        this.txtYear.setText(year);
    }
    public void setTxtNIC(String nic) {
        this.txtNIC.setText(nic);
    }
    public void setTxtStatus(String status) {
        this.txtStatus.setText(status);
    }
    public void setLblID(String id){
        this.lblID.setText(id);
    }

    public String getTxtSearch() {
        return txtSearch.getText().trim();
    }
    public String getTxtVIN() {
        return txtVIN.getText().trim();
    }
    public String getTxtModel() {
        return txtModel.getText().trim();
    }
    public String getTxtPrice() {
        return txtPrice.getText().trim();
    }
    public String getTxtYear() {
        return txtYear.getText().trim();
    }
    public String getTxtNIC() {
        return txtNIC.getText().trim();
    }
    public String getTxtStatus() {
        return txtStatus.getText().trim();
    }
    public String getTXTID() {
        return lblID.getText().trim();
    }

    public void setImageIcon(String filePath) {
        ImageIcon icon = new ImageIcon(filePath);
        lblVehicleImage.setIcon(icon);
        this.imageFilePath = filePath;
    }
    public String getImage() {
        return this.imageFilePath;
    }

    public void setEdit(boolean r ,String status){
        txtYear.setEditable(r);
        txtVIN.setEditable(r);
        txtModel.setEditable(r);
        if(!r){
            ImagePanel.remove(uploadButton);
        }else {
            ImagePanel.add(uploadButton,BorderLayout.SOUTH );
        }

        if(Objects.equals(status, "sold out(Loan)")){
            requestButton.setEnabled(false);
        } else if (Objects.equals(status, "sold out")) {
            requestButton.setEnabled(false);
            payButton.setEnabled(false);
            txtPrice.setEditable(false);
            txtNIC.setEditable(false);
            addButton.setEnabled(false);
            deleteButton.setEnabled(false);
            updateButton.setEnabled(false);
        }else {
            requestButton.setEnabled(true);
            payButton.setEnabled(true);
            txtPrice.setEditable(true);
            txtNIC.setEditable(true);
            addButton.setEnabled(true);
            deleteButton.setEnabled(true);
            updateButton.setEnabled(true);

        }

    }

    public void DisableButoon(String type){
        if(Objects.equals(type, "Loan Approved")){
            deleteButton.setEnabled(false);
            updateButton.setEnabled(false);
            addButton.setEnabled(false);
            requestButton.setEnabled(false);
        }else {
            deleteButton.setEnabled(true);
            updateButton.setEnabled(true);
            addButton.setEnabled(true);
            requestButton.setEnabled(true);
        }
    }







}
