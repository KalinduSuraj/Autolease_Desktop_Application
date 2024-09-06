package Model;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    private DBConnection dbConnection;

    public CustomerDAO() {
        dbConnection = new DBConnection();
    }

    public boolean checkNIC(Customer objCustomer){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql = "SELECT * FROM customer where nic = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Set the parameters
            preparedStatement.setString(1, objCustomer.getNIC());

            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean AddCustomer(Customer objCustomer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql1 = "INSERT INTO customer (nic, name, contact, address) VALUES (?, ?, ?,?)";
            preparedStatement = connection.prepareStatement(sql1);

            // Set the parameters
            preparedStatement.setString(1, objCustomer.getNIC());
            preparedStatement.setString(2, objCustomer.getName());
            preparedStatement.setString(3, objCustomer.getContact());
            preparedStatement.setString(4, objCustomer.getAddress());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean UpdateCustomer(Customer objCustomer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql1 = "UPDATE user SET contact = ? , address = ?  WHERE nic = ?";
            preparedStatement = connection.prepareStatement(sql1);

            // Set the parameters
            preparedStatement.setString(1, objCustomer.getContact());
            preparedStatement.setString(2, objCustomer.getAddress());
            preparedStatement.setString(3, objCustomer.getNIC());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public DefaultTableModel DisplayCustomer(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        DefaultTableModel table = new DefaultTableModel();
        table.setColumnIdentifiers(new String[]{"NIC","Name","Contact","Address"});

        try{
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql = "SELECT * FROM customer";
            preparedStatement=connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Object[] rowdata ={
                        rs.getString("nic"),
                        rs.getString("name"),
                        rs.getString("contact"),
                        rs.getString("address")
                };
                table.addRow(rowdata);
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return table;
    }

    public DefaultTableModel SearchCustomer(String txtSearch) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        DefaultTableModel table = new DefaultTableModel();
        table.setColumnIdentifiers(new String[]{"NIC","Name","Contact","Address"});

        try{
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql = "SELECT * FROM customer WHERE nic LIKE ? OR name LIKE ?";
            preparedStatement=connection.prepareStatement(sql);

            // Set the parameters
            preparedStatement.setString(1, "%" + txtSearch + "%");
            preparedStatement.setString(2, "%" + txtSearch + "%");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Object[] rowdata ={
                        rs.getString("nic"),
                        rs.getString("name"),
                        rs.getString("contact"),
                        rs.getString("address")
                };
                table.addRow(rowdata);
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return table;
    }
}
