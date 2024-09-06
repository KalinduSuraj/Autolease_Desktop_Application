package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class AdminLeasingView extends JFrame {
    private JButton homeButton,searchButton,approveButton;
    private JTextField txtSearch;
    private JTable tblLeasing;
    private JScrollPane jScrollPane;

    public AdminLeasingView(){
        InitializeComponent();
    }

    private void InitializeComponent() {

        setTitle("Leasing");
        setResizable(false);
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/view/img/Autolease4.png"));
        setIconImage(icon);


        JPanel Mpanel = new JPanel(new BorderLayout());
        Mpanel.setBackground(new Color(222, 222, 255));
        add(Mpanel);


        JPanel Flowpanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        Flowpanel.setBackground(new Color(222, 222, 255));
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

        searchButton = new JButton("Search");
        searchButton.setBackground(new Color(154, 143, 255));
        searchButton.setFocusPainted(false);
        leftPanel.add(searchButton);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setBackground(new Color(222, 222, 255));

        approveButton = new JButton("Approve");
        approveButton.setBackground(new Color(154, 143, 255));
        approveButton.setFocusPainted(false);
        rightPanel.add(approveButton);

        Flowpanel1.add(leftPanel,BorderLayout.WEST);
        Flowpanel1.add(rightPanel,BorderLayout.EAST);


        JPanel Tablepanel = new JPanel(new BorderLayout());
        Tablepanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel1.add(Tablepanel,BorderLayout.CENTER);
        Tablepanel.setBackground(new Color(222, 222, 255));

        tblLeasing = new JTable();
        jScrollPane = new JScrollPane(tblLeasing);
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
    public void approveButtonListener(ActionListener listener) {
        approveButton.addActionListener(listener);
    }
    public void TableSelectionListener(MouseAdapter listener) {
        tblLeasing.addMouseListener(listener);
    }

    public JTable getTblLeasingVehicle() {
        return tblLeasing;
    }
    public JTextField getTxtSearch() {
        return txtSearch;
    }


}
