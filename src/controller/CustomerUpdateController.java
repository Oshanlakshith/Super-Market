package controller;

import Model.Customer;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class CustomerUpdateController {
    public TextField txtCUId;
    public TextField txtCUName;
    public TextField txtCUProvince;
    public TextField txtCUAddress;
    public TextField txtCUCity;
    public TextField txtCUPostalCode;
    public TextField txtCSlary;

    public void SearchCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String CustomerId=txtCUId.getText();
        Customer c1=new CustomerController().getCustomer(CustomerId);
        if(c1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Results...").show();
        }else{
            setData(c1);
        }
    }
    void setData(Customer c1){
           txtCUId.setText(c1.getCustomerId());
           txtCUName.setText(c1.getCustomerName());
           txtCUProvince.setText(c1.getCustomerProvince());
           txtCUAddress.setText(c1.getCustomerAddress());
           txtCUCity.setText(c1.getCustomerCity());
           txtCUPostalCode.setText(c1.getCustomerPostalCode());
           txtCSlary.setText(String.valueOf(c1.getCustomerSalary()));
    }

    public void UpdateCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c1 = new Customer(
                txtCUId.getText(), txtCUName.getText(), txtCUAddress.getText(), txtCUCity.getText(), txtCUProvince.getText(), txtCUPostalCode.getText(), Double.parseDouble(txtCSlary.getText())
        );

        if (new CustomerController().updateCustomer(c1))
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again...").show();
    }
}

