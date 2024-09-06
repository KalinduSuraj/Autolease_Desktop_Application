package view;

import Model.Vehicle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class PayView extends JDialog{

    private JPanel panel1,panel2,panel3;
    private JLabel lblVIC,lblModel, lblNIC, lblPayment;
    private JTextField txtVIN,txtModel, txtNIC, txtPayment;
    private JButton btnPay;

    public PayView(){
        InitializeComponent();
    }

    private void InitializeComponent() {

        setTitle("Pay");
        setModal(false);
        setResizable(false);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/view/img/Autolease4.png"));
        setIconImage(icon);

        panel1 = new JPanel(new GridBagLayout());
        panel1.setBackground(new Color(222, 222, 255));
        panel1.setBorder(new EmptyBorder(0, 10, 0, 10));
        add(panel1, BorderLayout.WEST);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lblVIC = new JLabel("VIN");
        lblVIC.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel1.add(lblVIC, gbc);

        txtVIN = new JTextField(13);
        txtVIN.setFont(new Font("Arial", Font.PLAIN, 15));
        txtVIN.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel1.add(txtVIN, gbc);

        lblPayment = new JLabel("Payment");
        lblPayment.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel1.add(lblPayment, gbc);

        txtPayment = new JTextField(13);
        txtPayment.setFont(new Font("Arial", Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel1.add(txtPayment, gbc);

        panel2 = new JPanel(new GridBagLayout());
        panel2.setBackground(new Color(222, 222, 255));
        panel2.setBorder(new EmptyBorder(0, 10, 0, 10));
        add(panel2, BorderLayout.EAST);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(5, 0, 5, 0);
        gbc2.fill = GridBagConstraints.HORIZONTAL;

        lblModel = new JLabel("Model");
        lblModel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.gridwidth = 1;
        panel2.add(lblModel, gbc2);

        txtModel = new JTextField(13);
        txtModel.setFont(new Font("Arial", Font.PLAIN, 15));
        txtModel.setEditable(false);
        gbc2.gridx = 0;
        gbc2.gridy = 2;
        gbc2.gridwidth = 1;
        panel2.add(txtModel, gbc2);

        lblNIC = new JLabel("NIC");
        lblNIC.setFont(new Font("Arial", Font.BOLD, 12));
        gbc2.gridx = 0;
        gbc2.gridy = 3;
        gbc2.gridwidth = 1;
        panel2.add(lblNIC, gbc2);

        txtNIC = new JTextField(13);
        txtNIC.setFont(new Font("Arial", Font.PLAIN, 15));
        gbc2.gridx = 0;
        gbc2.gridy = 4;
        gbc2.gridwidth = 1;
        panel2.add(txtNIC, gbc2);

        panel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel3.setBorder(new EmptyBorder(0, 10, 0, 5));
        panel3.setBackground(new Color(222, 222, 255));
        add(panel3,BorderLayout.SOUTH);

        btnPay = new JButton("Pay");
        btnPay.setBackground(new Color(154, 143, 255));
        btnPay.setForeground(Color.WHITE);
        btnPay.setFocusPainted(false);
        panel3.add(btnPay);

        setVisible(true);
    }

    public void setTxtVIN(String  vin){
        txtVIN.setText(vin);
    }
    public void setTxtModel(String  model){
        txtModel.setText(model);
    }
    public void setLblNIC(String  txt) {
        this.lblNIC.setText(txt);
    }

    public  JTextField getTxtBoxPayment(){
        return txtPayment;
    }
    public  JTextField getTxtBoxNIC(){
        return txtNIC;
    }
    public JLabel getlblPayemnt() {
        return  lblPayment;
    }

    public void PayListener(ActionListener listener) {
        btnPay.addActionListener(listener);
    }

}
