package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminHomeView extends JFrame {
    private JButton logoutButton, loanButton,leasingButton,employeeButton;
    private JLabel imageLabel;

    public AdminHomeView(){
        InitializeComponent();
    }

    public void InitializeComponent() {

        setTitle("Admin Home");
        setResizable(false);
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(222, 222, 255));
        add(panel);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/view/img/Autolease4.png"));
        setIconImage(icon);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 0, 5);
        gbc.fill = GridBagConstraints.CENTER;


        logoutButton = new JButton("Log Out");
        logoutButton.setBackground(new Color(154, 143, 255));
        logoutButton.setFocusPainted(false);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        panel.add(logoutButton, gbc);


        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;

        leasingButton = createMenuButton("Leasing Request");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(leasingButton, gbc);

        loanButton = createMenuButton("Loan Request");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(loanButton, gbc);

        employeeButton = createMenuButton("Employee");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(employeeButton, gbc);

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/view/img/Picture2.png"));
        imageLabel = new JLabel(imageIcon);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 20, 0, 20);
        panel.add(imageLabel, gbc);

        setVisible(true);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(130, 100));
        button.setForeground(Color.BLACK);
        button.setBackground(new Color(154, 143, 255));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return button;
    }

    public void leasingButtonListener(ActionListener listener) {
        leasingButton.addActionListener(listener);
    }
    public void loanButtonListener(ActionListener listener) {
        loanButton.addActionListener(listener);
    }
    public void employeeButtonListener(ActionListener listener) {
        employeeButton.addActionListener(listener);
    }
    public void logoutButtonListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }
}
