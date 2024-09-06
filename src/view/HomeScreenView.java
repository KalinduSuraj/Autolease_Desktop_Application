package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomeScreenView extends JFrame {
    private JButton logoutButton, vehiclesButton,leasingButton,paymentsButton,customersButton ;

    public HomeScreenView() {
        InitializeComponent();
    }

    public void InitializeComponent() {
        setTitle("Employee Home");
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
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.fill = GridBagConstraints.CENTER;

        logoutButton = new JButton("Log Out");
        logoutButton.setBackground(new Color(154, 143, 255));
        logoutButton.setFocusPainted(false);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        panel.add(logoutButton, gbc);


        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        vehiclesButton = createMenuButton("Vehicles");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(vehiclesButton, gbc);

        leasingButton = createMenuButton("Leasing");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(leasingButton, gbc);

        paymentsButton = createMenuButton("Payments");
        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(paymentsButton, gbc);

        customersButton = createMenuButton("Customers");
        gbc.gridx = 3;
        gbc.gridy = 2;
        panel.add(customersButton, gbc);


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

    public void AddvehiclesButtonListener(ActionListener listener) {
        vehiclesButton.addActionListener(listener);
    }
    public void AddleasingButtonListener(ActionListener listener) {
        leasingButton.addActionListener(listener);
    }
    public void AddpaymentsButtonListener(ActionListener listener) {
        paymentsButton.addActionListener(listener);
    }
    public void AddcustomersButtonListener(ActionListener listener) {
        customersButton.addActionListener(listener);
    }
    public void AddlogoutButtonListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }

}
