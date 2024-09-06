package Model;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static controller.UserLoginController.getLogedUserObj;

public class VehiclesDAO {
    private DBConnection dbConnection;
    public VehiclesDAO() {
        dbConnection = new DBConnection();
    }

    public boolean checkID(Vehicle objVehicle){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql = "SELECT * FROM vehicles where id = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Set the parameters
            preparedStatement.setString(1, objVehicle.getId());

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

    public String generateVehicleID() {
        Connection connection = null;
        String newID = "V001";
        try {
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String query = "SELECT id FROM vehicles ORDER BY id DESC LIMIT 1";
            ResultSet rs = connection.createStatement().executeQuery(query);
            if (rs.next()) {
                String lastID = rs.getString("id");
                int idNum = Integer.parseInt(lastID.substring(1));
                idNum++;
                newID = "V" + String.format("%03d", idNum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newID;
    }
    public boolean addVehicle(Vehicle objVehicle , Customer objCustomer){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            dbConnection.connect();
            connection = dbConnection.getConnection();

            objVehicle.setId(generateVehicleID());

            String sql1 = "INSERT INTO vehicles (id, vin, model, year, price, image, status, employee,nic) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql1);

            // Set the parameters
            preparedStatement.setString(1, objVehicle.getId());
            preparedStatement.setString(2, objVehicle.getVin());
            preparedStatement.setString(3, objVehicle.getModel());
            preparedStatement.setInt(4, objVehicle.getYear());
            preparedStatement.setDouble(5, objVehicle.getPrice());
            preparedStatement.setString(6, objVehicle.getImage());
            preparedStatement.setString(7,objVehicle.getStatus());
            preparedStatement.setString(8,getLogedUserObj().getUserName());
            preparedStatement.setString(9,objCustomer.getNIC());


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
    public boolean updateVehicle(Vehicle objVehicle ,Customer objCustomer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql1 = "UPDATE vehicles SET price = ? ,nic = ?  WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql1);

            // Set the parameters
            preparedStatement.setDouble(1, objVehicle.getPrice());
            preparedStatement.setString(2, objCustomer.getNIC());
            preparedStatement.setString(3, objVehicle.getId());

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
    public boolean DeleteVehicle(Vehicle objVehicle) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql1 = "DELETE FROM vehicles  WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql1);

            // Set the parameters
            preparedStatement.setString(1, objVehicle.getId());

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
    public boolean UpdateOwner(Customer owner,Vehicle vehicle) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql1 = "UPDATE vehicles SET nic = ?  WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql1);

            // Set the parameters
            preparedStatement.setString(1, owner.getNIC());
            preparedStatement.setString(2, vehicle.getId());

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



    //Emp Vehicle Display
    public DefaultTableModel Display() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        DefaultTableModel table = new DefaultTableModel();
        table.setColumnIdentifiers(new String[]{"ID","VIN","Model","Year","Price","Image","Status","Owner NIC"});

        try{
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql = "SELECT * FROM vehicles WHERE status = 'Available' OR status ='Request Leasing' OR status ='Loan Approved' OR status ='Leasing Approved' OR status ='sold out' OR status ='sold out(Loan)';";
            preparedStatement=connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Object[] rowData = {
                        rs.getString("id"),
                        rs.getString("vin"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getDouble("price"),
                        rs.getString("image"),
                        rs.getString("status"),
                        rs.getString("nic"),
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

    //Emp Vehicle Search
    public DefaultTableModel SearchVehicles(String txtSearch) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        DefaultTableModel table = new DefaultTableModel();
        table.setColumnIdentifiers(new String[]{"ID","VIN","Model","Year","Price","Image","Status","Owner NIC"});

        try{
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql = "SELECT * FROM vehicles WHERE vin LIKE ? OR model LIKE ? AND status = 'Available' OR status ='Request Leasing' OR status ='Loan Approved' OR status ='Leasing Approved' OR status ='sold out' OR status ='sold out(Loan)";
            preparedStatement=connection.prepareStatement(sql);

            // Set the parameters
            preparedStatement.setString(1, "%" + txtSearch + "%");
            preparedStatement.setString(2, "%" + txtSearch + "%");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Object[] rowData = {
                        rs.getString("id"),
                        rs.getString("vin"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getDouble("price"),
                        rs.getString("image"),
                        rs.getString("status"),
                        rs.getString("nic"),

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

    //Emp Leasing Display
    public DefaultTableModel EmployeeLeasingDisplay() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        DefaultTableModel table = new DefaultTableModel();
        table.setColumnIdentifiers(new String[]{"ID","VIN","Model","Year","Price","Leasing Amount"});

        try{
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql = "SELECT v.id , v.vin , v.model ,v.year ,v.price ,l.amount FROM vehicles v JOIN leasing l ON v.id = l.vid  WHERE v.status = 'Leasing Approved'";
            preparedStatement=connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Object[] rowData = {
                        rs.getString("id"),
                        rs.getString("vin"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getDouble("price"),
                        rs.getDouble("amount")
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

    //Emp Leasing Search
    public DefaultTableModel EmployeeLeasingSearch(String txtSearch) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        DefaultTableModel table = new DefaultTableModel();
        table.setColumnIdentifiers(new String[]{"ID","VIN","Model","Year","Price","Leasing Amount"});

        try{
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql = "SELECT v.id , v.vin , v.model ,v.year ,v.price ,l.amount FROM vehicles v JOIN leasing l ON v.id = l.vid  WHERE v.status = 'Leasing Approved' AND v.id= ?";
            preparedStatement=connection.prepareStatement(sql);
            // Set the parameters
            preparedStatement.setString(1, "%" + txtSearch + "%");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Object[] rowData = {
                        rs.getString("id"),
                        rs.getString("vin"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getDouble("price"),
                        rs.getDouble("amount")
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

    //Admin Leasing view
    public DefaultTableModel LeasingDisplay() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        DefaultTableModel table = new DefaultTableModel();
        table.setColumnIdentifiers(new String[]{"ID","VIN","Model","Year","Price","Image"});

        try{
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql = "SELECT * FROM vehicles WHERE status = 'Request Leasing'";

            preparedStatement=connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Object[] rowData = {
                        rs.getString("id"),
                        rs.getString("vin"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getDouble("price"),
                        rs.getString("image"),
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

    //Admin Leasing view search
    public DefaultTableModel LeasingSearch(String txtSearch) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        DefaultTableModel table = new DefaultTableModel();
        table.setColumnIdentifiers(new String[]{"ID","VIN","Model","Year","Price","Image"});

        try{
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql = "SELECT * FROM vehicles WHERE status = 'Request Leasing' AND id= ?";

            preparedStatement=connection.prepareStatement(sql);
            // Set the parameters
            preparedStatement.setString(1, "%" + txtSearch + "%");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Object[] rowData = {
                        rs.getString("id"),
                        rs.getString("vin"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getDouble("price"),
                        rs.getString("image"),
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

    //Admin Loan Display
    public DefaultTableModel LoanDisplay() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        DefaultTableModel table = new DefaultTableModel();
        table.setColumnIdentifiers(new String[]{"ID","VIN","Model","Year","Price"});

        try{
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql = "SELECT * FROM vehicles WHERE status = 'Request Loan'";
            preparedStatement=connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Object[] rowData = {
                        rs.getString("id"),
                        rs.getString("vin"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getDouble("price"),
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

    //Admin Loan Search
    public DefaultTableModel LoanSearch(String txtSearch) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        DefaultTableModel table = new DefaultTableModel();
        table.setColumnIdentifiers(new String[]{"ID","VIN","Model","Year","Price"});

        try{
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql = "SELECT * FROM vehicles WHERE status = 'Request Loan' AND id = ?";
            preparedStatement=connection.prepareStatement(sql);
            // Set the parameters
            preparedStatement.setString(1, "%" + txtSearch + "%");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Object[] rowData = {
                        rs.getString("id"),
                        rs.getString("vin"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getDouble("price"),
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

    public boolean changeStatus(Vehicle objVehicle, String status) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            dbConnection.connect();
            connection = dbConnection.getConnection();

            String sql1 = "UPDATE vehicles SET status = ?   WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql1);

            // Set the parameters
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, objVehicle.getId());


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



}
