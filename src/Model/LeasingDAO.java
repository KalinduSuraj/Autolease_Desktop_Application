package Model;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static controller.UserLoginController.getLogedUserObj;

public class LeasingDAO {
    private DBConnection dbConnection;
    public LeasingDAO() {
        dbConnection = new DBConnection();
    }


    public boolean addLeasing(Leasing objLeasing , Vehicle objVehicle){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            dbConnection.connect();
            connection = dbConnection.getConnection();

            objLeasing.setLeasingID(generateID());

            String sql1 = "INSERT INTO leasing (id, amount, vid, remaining_amount) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql1);

            // Set the parameters
            preparedStatement.setString(1, objLeasing.getLeasingID());
            preparedStatement.setDouble(2, objLeasing.getAmount());
            preparedStatement.setString(3, objVehicle.getId());
            preparedStatement.setDouble(4, objLeasing.getAmount());



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

    public String generateID() {
        Connection connection = null;
        String newID = "L001";
        try {
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String query = "SELECT id FROM leasing ORDER BY id DESC LIMIT 1";
            ResultSet rs = connection.createStatement().executeQuery(query);
            if (rs.next()) {
                String lastID = rs.getString("id");
                int idNum = Integer.parseInt(lastID.substring(1));
                idNum++;
                newID = "L" + String.format("%03d", idNum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newID;
    }

    public Double getLeasingAmount(Vehicle objSelectedVehicle) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Double amount = 0.0;

        try{
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql = "SELECT * FROM leasing WHERE vid = ?";
            preparedStatement=connection.prepareStatement(sql);

            // Set the parameters
            preparedStatement.setString(1, objSelectedVehicle.getId());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                amount = Double.parseDouble(rs.getString("amount"));
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
        return amount;
    }

    public boolean UpdateRemaining(Payment payment,Vehicle objSelectedVehicle) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql1 = "UPDATE leasing SET remaining_amount = ?   WHERE vid = ?";
            preparedStatement = connection.prepareStatement(sql1);
            Double R_Amount= getR_Amount(objSelectedVehicle) - payment.getAmount();

            // Set the parameters
            preparedStatement.setDouble(1,R_Amount );
            preparedStatement.setString(2, objSelectedVehicle.getId());


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

    public double getR_Amount(Vehicle objSelectedVehicle) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Double amount = 0.0;

        try{
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql = "SELECT * FROM leasing WHERE vid = ?";
            preparedStatement=connection.prepareStatement(sql);

            // Set the parameters
            preparedStatement.setString(1, objSelectedVehicle.getId());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                amount = Double.parseDouble(rs.getString("remaining_amount"));
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
        return amount;
    }
}
