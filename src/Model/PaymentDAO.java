package Model;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDAO {
    DBConnection dbConnection;

    public PaymentDAO (){
        this.dbConnection = new DBConnection();
    }

    public String generatePaymentID() {
        Connection connection = null;
        String newID = "P001";
        try {
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String query = "SELECT id FROM payment ORDER BY id DESC LIMIT 1";
            ResultSet rs = connection.createStatement().executeQuery(query);
            if (rs.next()) {
                String lastID = rs.getString("id");
                int idNum = Integer.parseInt(lastID.substring(1));
                idNum++;
                newID = "P" + String.format("%03d", idNum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newID;
    }

    public boolean FullPayment(Vehicle vehicle , Payment payment){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql1 = "INSERT INTO payment (id, Amount, vid) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql1);

            // Set the parameters
            preparedStatement.setString(1, payment.getID());
            preparedStatement.setDouble(2, payment.getAmount());
            preparedStatement.setString(3, vehicle.getId());

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

    public DefaultTableModel Display() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        DefaultTableModel table = new DefaultTableModel();
        table.setColumnIdentifiers(new String[]{"Payment ID","Amount","Date & Time","Vehicle ID"});

        try{
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql = "SELECT * FROM payment;";
            preparedStatement=connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Object[] rowData = {
                        rs.getString("id"),
                        rs.getString("amount"),
                        rs.getString("date_time"),
                        rs.getString("vid")
                };
                table.addRow(rowData);
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

    public DefaultTableModel SearchPayment(String txtSearch) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        DefaultTableModel table = new DefaultTableModel();
        table.setColumnIdentifiers(new String[]{"Payment ID","Amount","Date & Time","Vehicle ID"});

        try{
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql = "SELECT * FROM payment WHERE vid LIKE ? ";
            preparedStatement=connection.prepareStatement(sql);

            // Set the parameters
            preparedStatement.setString(1, "%" + txtSearch + "%");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Object[] rowData = {
                        rs.getString("id"),
                        rs.getString("amount"),
                        rs.getString("date_time"),
                        rs.getString("vid")
                };
                table.addRow(rowData);
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
