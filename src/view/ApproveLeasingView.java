package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ApproveLeasingView extends JDialog {
    private JPanel panel1,panel3;
    private JLabel lblAmount;
    private JTextField txtAmount;
    private JButton btnApprove;

    public ApproveLeasingView(){
        InitializeComponent();
    }

    private void InitializeComponent() {

        setTitle("Approve Leasing");
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
        add(panel1, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lblAmount = new JLabel("Enter Leasing Amount");
        lblAmount.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel1.add(lblAmount, gbc);

        txtAmount = new JTextField(13);
        txtAmount.setFont(new Font("Arial", Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel1.add(txtAmount, gbc);

        panel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel3.setBorder(new EmptyBorder(0, 10, 0, 5));
        panel3.setBackground(new Color(222, 222, 255));
        add(panel3,BorderLayout.SOUTH);

        btnApprove = new JButton("Approve Leasing");
        btnApprove.setBackground(new Color(154, 143, 255));
        btnApprove.setForeground(Color.WHITE);
        btnApprove.setFocusPainted(false);
        panel3.add(btnApprove);

        setVisible(true);
    }

    public String getTxtAmount(){
        return txtAmount.getText();
    }

    public void btnApproveListener(ActionListener listener) {
        btnApprove.addActionListener(listener);
    }

}
