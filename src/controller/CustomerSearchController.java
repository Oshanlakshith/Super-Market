package controller;

import Model.Customer;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class CustomerSearchController {
    public TextField txtCSId;
    public TextField txtCSName;
    public TextField txtCSProvince;
    public TextField txtCSAddress;
    public TextField txtCSCity;
    public TextField txtCSPostalCode;
    public TextField txtCSSalary;

    public void CustomerSearch(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId=txtCSId.getText();
        Customer c1=new CustomerController().getCustomer(customerId);
            if(c1==null){
                new Alert(Alert.AlertType.WARNING,"Empty results...").show();
            }else {
                setData(c1);
            }
        }
    void setData(Customer c){
        txtCSId.setText(c.getCustomerId());
        txtCSName.setText(c.getCustomerName());
        txtCSAddress.setText(c.getCustomerAddress());
        txtCSCity.setText(c.getCustomerCity());
        txtCSProvince.setText(c.getCustomerProvince());
        txtCSPostalCode.setText(c.getCustomerPostalCode());
        txtCSSalary.setText(String.valueOf(c.getCustomerSalary()));
    }

    }

