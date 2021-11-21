package controller;

import Model.Customer;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class CustomerAddFormController {

    public TextField txtCId;
    public TextField txtCName;
    public TextField txtCProvince;
    public TextField txtCAddress;
    public TextField txtCCity;
    public TextField txtCPostalCode;
    public TextField txtSlary;

    public void CustomerSave(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c1=new Customer(txtCId.getText(),txtCName.getText(),txtCAddress.getText(),txtCCity.getText(),txtCProvince.getText(),txtCPostalCode.getText(),Double.parseDouble(txtSlary.getText()));
        if(saveCustomer(c1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        }else
            new Alert(Alert.AlertType.WARNING,"Try again..").show();

    }
        boolean saveCustomer(Customer c) throws SQLException, ClassNotFoundException {
            return new CustomerController().saveCustomer(c);
        }
    }

