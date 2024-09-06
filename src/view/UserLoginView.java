package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;


public class UserLoginView extends JFrame {

    private JPanel panel;
    private JTextField userText;
    private JPasswordField passwordText;
    private JLabel imageLabel, loginLabel, userLabel, passwordLabel;
    private JButton loginButton;

    public UserLoginView(){
        InitializeComponent();
    }

    public void InitializeComponent() {
        setTitle("Login");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(222, 222, 255));
        add(panel);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/view/img/Autolease4.png"));
        setIconImage(icon);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/view/img/Picture1.png"));
        imageLabel = new JLabel(imageIcon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 15;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(imageLabel, gbc);

        loginLabel = new JLabel("LogIn");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginLabel, gbc);

        userLabel = new JLabel("User Name");
        userLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridy = 1;
        panel.add(userLabel, gbc);

        userText = new JTextField(20);
        userText.setSize(getWidth(),20);
        userText.setFont(new Font("Arial", Font.PLAIN, 15));
        gbc.gridy = 2;
        panel.add(userText, gbc);

        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridy = 3;
        panel.add(passwordLabel, gbc);

        passwordText = new JPasswordField(20);
        gbc.gridy = 4;
        panel.add(passwordText, gbc);

        loginButton = new JButton("Log In");
        loginButton.setBackground(new Color(154, 143, 255));
        loginButton.setFocusPainted(false);
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        setVisible(true);
    }

    public String getUsername() {
        return userText.getText();
    }
    public String getPassword() {
        return new String(passwordText.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }
    public void addKeyListenerToComponents(KeyListener keyListener) {
        userText.addKeyListener(keyListener);
        passwordText.addKeyListener(keyListener);
        loginButton.addKeyListener(keyListener);
    }

}
