package controller;

import Model.Customer;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.CustomerTM;
import view.tm.OrderDetailTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AllCustomerController {

    public TableView tblAllCustomer;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colCity;
    public TableColumn colProvince;
    public TableColumn colPostalCode;
    public TableColumn colSalary;
    public TableView<CustomerTM>tblCustomer;


    public void initialize() {
        try {
            colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
            colCity.setCellValueFactory(new PropertyValueFactory<>("City"));
            colProvince.setCellValueFactory(new PropertyValueFactory<>("Province"));
            colPostalCode.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
            colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));


            setCustomerToTable(new CustomerController().getAllCustomers());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCustomerToTable(ArrayList<Customer>customers) {
        ObservableList<CustomerTM>oblist=FXCollections.observableArrayList();
        customers.forEach(e->{
            oblist.add(
                    new CustomerTM(e.getCustomerId(),e.getCustomerName(),e.getCustomerAddress(),e.getCustomerCity(),e.getCustomerPostalCode(),e.getCustomerProvince(),e.getCustomerSalary()));
        });
            tblCustomer.setItems(oblist);
    }
}
