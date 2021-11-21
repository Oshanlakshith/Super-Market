package controller;

import Model.Customer;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class CustomerDeleteFormController {
    public TextField txtCDId;
    public TextField txtCDName;
    public TextField txtDProvince;
    public TextField txtCDAddress;
    public TextField txtCDCity;
    public TextField txtDPostalCode;
    public TextField txtDSalary;
    public TextField txtTimess;
    public Button jbutton;

    public void DeleteCustomerSearch(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId=txtCDId.getText();

        Customer c1=new CustomerController().getCustomer(customerId);
        if(c1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result..").show();
        }else{
            setData(c1);
        }
    }
    void setData(Customer c) {
            txtCDId.setText(c.getCustomerId());
            txtCDName.setText(c.getCustomerName());
            txtCDAddress.setText(c.getCustomerAddress());
            txtCDCity.setText(c.getCustomerCity());
            txtDProvince.setText(c.getCustomerProvince());
            txtDPostalCode.setText(c.getCustomerPostalCode());
            txtDSalary.setText(String.valueOf(c.getCustomerSalary()));
        }
    public void DeleteCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(new CustomerController().deleteCustomer(txtCDId.getText())){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted..").show();
        } else{
            new Alert(Alert.AlertType.WARNING,"Try again").show();
        }
    }


   /* public void DeleteCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       if(new CustomerController().deleteCustomer(txtCDId.getText())){

            new Alert(Alert.AlertType.CONFIRMATION,"Delete...").show();
        }else {
           new Alert(Alert.AlertType.WARNING,"Try Again...").show();
       }
    }*/
}
