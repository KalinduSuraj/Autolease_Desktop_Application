package controller;


import Model.Customer;
import Model.CustomerDAO;
import Model.Vehicle;
import Model.VehiclesDAO;
import com.sun.tools.javac.Main;
import view.HomeScreenView;
import view.PayView;
import view.VehiclesView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class VehiclesController {
    VehiclesView view;
    VehiclesDAO vehiclesDAO;
    Vehicle objSelectedVehicle;
    Customer objSelectedCustomer;
    int selectedRow = -1;

    CustomerDAO customerDAO;


    public VehiclesController(VehiclesView view){
        this.view=view;
        this.vehiclesDAO = new VehiclesDAO();
        this.customerDAO = new CustomerDAO();
        DisplayData();

        view.HomeButtonListener(e -> homeButtonClick());
        view.UploadButtonListener(e -> UploadButtonClick());
        view.PayButtonListener(e -> PayButtonClick());
        view.SearchButtonListener(e -> SearchButtonClick());
        view.ClearButtonListener(e -> ClearButtonClick());
        view.DeleteButtonListener(e -> {
            try {
                deleteButtonClick();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        view.UpdateButtonListener(e -> updateButtonClick());
        view.AddButtonListener(e -> addButtonClick());
        view.RequestButtonListener(e -> requestButtonClick());

        view.TableSelectionListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedRow = view.getTblVehicles().getSelectedRow();
                if (selectedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) view.getTblVehicles().getModel();
                    view.setLblID(model.getValueAt(selectedRow, 0).toString());
                    view.setTxtVIN(model.getValueAt(selectedRow, 1).toString());
                    view.setTxtModel(model.getValueAt(selectedRow, 2).toString());
                    view.setTxtYear(model.getValueAt(selectedRow, 3).toString());
                    view.setTxtPrice(model.getValueAt(selectedRow, 4).toString());

                    view.setImageIcon("src/V_images/"+model.getValueAt(selectedRow,5).toString());
                    view.getImgLabel().setText("");

                    view.setTxtStatus(model.getValueAt(selectedRow,6).toString());
                    view.setTxtNIC(model.getValueAt(selectedRow,7).toString());

                    view.DisableButoon(model.getValueAt(selectedRow,6).toString());
                    view.setEdit(false,model.getValueAt(selectedRow,6).toString());

                    objSelectedVehicle = new Vehicle();
                    objSelectedVehicle.setVIN(view.getTxtVIN());
                    objSelectedVehicle.setModel(view.getTxtModel());
                    objSelectedVehicle.setYear(Integer.parseInt(view.getTxtYear()));
                    objSelectedVehicle.setPrice(Double.parseDouble(view.getTxtPrice()));
                    objSelectedVehicle.setStatus(view.getTxtStatus());
                    objSelectedVehicle.setId(view.getTXTID());

                    objSelectedCustomer = new Customer();
                    objSelectedCustomer.setNIC(view.getTxtNIC());


                }
            }
        });
    }

    private void requestButtonClick() {
        String id = view.getTXTID().trim();
        Vehicle objVehicle = new Vehicle();
        objVehicle.setId(id);

        if(id.isEmpty()){
            showMsg(" Please Select Vehicles ", "Error");
        }else{
            boolean rs = false;
            if (! Objects.equals(view.getTxtStatus(), "Loan Approved")) {

                rs = vehiclesDAO.changeStatus(objVehicle,"Request Leasing");

                if (rs) {
                    showMsg(" Request Leasing Successful", "Success");
                    clear();
                } else {
                    showMsg(" Request Leasing Failed", "Error");
                }
            }
            DisplayData();
            ClearButtonClick();
        }
    }
    private void DisplayData() {
        DefaultTableModel tableModel = vehiclesDAO.Display();
        view.getTblVehicles().setModel(tableModel);
    }
    private void ClearButtonClick() {
        DisplayData();
        selectedRow = -1;
        view.setEdit(true,"");
        clear();
    }
    private void deleteButtonClick() throws IOException {
        Vehicle objVehicle = new Vehicle();
        String id = view.getTXTID();
        Path pathToDelete = Paths.get(view.getImage());

        objVehicle.setId(id);

        boolean result;
        result = vehiclesDAO.DeleteVehicle(objVehicle);
        if(result){

            Files.delete(pathToDelete);
            showMsg("Deleted Successful","Success");
            clear();
        }else {
            showMsg("Delete Failed","Error");
        }

        ClearButtonClick();
        DisplayData();
    }
    private void updateButtonClick() {
        String error ="";

        String id = view.getTXTID();
        String pricetext =view.getTxtPrice();
        String nic = view.getTxtNIC();
        double price=0.00;

        if(pricetext.isEmpty()){
            error = error+"\nEnter Vehicle Price ...";
        }else {
            price = Double.parseDouble(pricetext);
        }
        if(nic.isEmpty()){
            error = error+"\nEnter Vehicle Owner NIC ...";
        }
        if(!error.isEmpty()){
            showMsg(error,"Error");
        }else {
            Vehicle objVehicle = new Vehicle();
            objVehicle.setPrice(price);
            objVehicle.setId(id);

            Customer objCustomer = new Customer();
            objCustomer.setNIC(nic);

            boolean rs;
            rs = vehiclesDAO.updateVehicle(objVehicle,objCustomer);
            if (rs) {
                showMsg(" Updated Successful", "Success");
                clear();
            } else {
                showMsg(" Updated Failed", "Error");
            }
            DisplayData();
            ClearButtonClick();
        }
    }
    private void addButtonClick() {
        String error = "";

        String vin = view.getTxtVIN();
        String model = view.getTxtModel();
        String yearText = view.getTxtYear();
        int year = 0;
        String priceText = view.getTxtPrice();
        double price = 0.00;
        String image = view.getImage();
        String nic = view.getTxtNIC();


        if (vin.isEmpty()) {
            error += "\nEnter VIN ...";
        }
        if (model.isEmpty()) {
            error += "\nEnter Vehicle Model ...";
        }
        if (yearText.isEmpty()) {
            error += "\nEnter Vehicle Year ...";
        } else {
            try {
                year = Integer.parseInt(yearText);
            } catch (NumberFormatException e) {
                error += "\nInvalid Year format ...";
            }
        }
        if (priceText.isEmpty()) {
            error += "\nEnter Vehicle Price ...";
        } else {
            try {
                price = Double.parseDouble(priceText);
            } catch (NumberFormatException e) {
                error += "\nInvalid Price format ...";
            }
        }
        if(nic.isEmpty()){
            error = error+"\nEnter Vehicle Owner NIC ...";
        }
        if (!error.isEmpty()) {
            showMsg(error, "Error");
        } else {
            Vehicle objVehicle = new Vehicle();
            objVehicle.setVIN(vin);
            objVehicle.setModel(model);
            objVehicle.setYear(year);
            objVehicle.setPrice(price);
            objVehicle.setStatus("Available");
            objVehicle.setId(view.getTXTID());

            Customer objCustomer = new Customer();
            objCustomer.setNIC(nic);

            if(customerDAO.checkNIC(objCustomer)){
                if (!vehiclesDAO.checkID(objVehicle) ) {

                    try {

                        Path sourcePath = Paths.get(image);
                        String newFileName = vehiclesDAO.generateVehicleID() + ".jpg";
                        Path targetPath = Paths.get("src/V_images", newFileName);

                        Files.copy(sourcePath, targetPath);System.out.println("hi");
                        objVehicle.setImage(newFileName);

                        boolean rs = vehiclesDAO.addVehicle(objVehicle,objCustomer);
                        if (rs) {
                            showMsg("Added Successfully", "Success");
                            clear();
                        } else {
                            showMsg("Addition Failed", "Error");
                        }
                        DisplayData();
                    } catch (IOException e) {
                        showMsg("File operation error: " + e.getMessage(), "Error");
                    }
                } else {
                    showMsg("Existing Vehicle ID", "Error");
                }
            }else {
                showMsg("Owner Details NotFound","Error");
            }

        }
    }
    private void SearchButtonClick() {
        String txtSearch = view.getTxtSearch();

        DefaultTableModel tableModel = vehiclesDAO.SearchVehicles(txtSearch);
        view.getTblVehicles().setModel(tableModel);
    }
    private void PayButtonClick() {
        if (selectedRow != -1) {
            new PayController(new PayView(), objSelectedVehicle,objSelectedCustomer);
            DisplayData();
        }else{
            showMsg("Please Select Vehicle","Error");
            DisplayData();
        }
        DisplayData();
    }
    private void UploadButtonClick() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", ImageIO.getReaderFileSuffixes()));

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            view.setImageIcon(selectedFile.getPath());
        }
    }
    private void homeButtonClick() {
        new HomeScreenController(new HomeScreenView());
        view.dispose();
    }

    public void showMsg(String msg,String title){
        JOptionPane.showConfirmDialog(
                view,
                msg,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane. INFORMATION_MESSAGE
        );
    }

    public void clear(){
        view.getImgLabel().setText(" Vehicle Image ");
        view.getImgLabel().setIcon(null);
        view.setTxtVIN("");
        view.setTxtModel("");
        view.setTxtYear("");
        view.setTxtPrice("");
        view.setLblID("");
        view.setTxtStatus("");
        view.setTxtNIC("");
    }


}
