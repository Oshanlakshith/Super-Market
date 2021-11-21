package controller;

import Model.Customer;
import db.DbConnection;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController implements CustomerService{
    public List<String> getCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT*FROM Customer").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString(1)
            );
        }
        return  ids;
    }
    @Override
    public boolean saveCustomer(Customer c) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query="INSERT INTO Customer VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stm=con.prepareStatement(query);

        stm.setObject(1,c.getCustomerId());
        stm.setObject(2,c.getCustomerName());
        stm.setObject(3,c.getCustomerAddress());
        stm.setObject(4,c.getCustomerCity());
        stm.setObject(5,c.getCustomerProvince());
        stm.setObject(6,c.getCustomerPostalCode());
        stm.setObject(7,c.getCustomerSalary());


        return stm.executeUpdate()>0;
    }

    @Override
    public boolean updateCustomer(Customer c) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Customer SET Custname=?,CustAddress=?,City=?,Province=?,PostalCode=?,CustSalary=? WHERE CustId=? ");
        stm.setObject(1,c.getCustomerName());
        stm.setObject(2,c.getCustomerAddress());
        stm.setObject(3,c.getCustomerCity());
        stm.setObject(4,c.getCustomerProvince());
        stm.setObject(5,c.getCustomerPostalCode());
        stm.setObject(6,c.getCustomerSalary());
        stm.setObject(7,c.getCustomerId());
        return stm.executeUpdate()>0;

    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
            if(DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE CustId='"+id+"'").executeUpdate()>0){
                return  true;
            }else {
                return false;
            }

    }

    @Override
    public Customer getCustomer(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT*FROM Customer WHERE CustId=?");
        stm.setObject(1,id);

        ResultSet rst= stm.executeQuery();
        if(rst.next()){
            Customer c1=new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getDouble(7)

                    );
            return c1;
        }else{
            new Alert(Alert.AlertType.WARNING,"Empty Result..").show();
        }
        return null;
    }


    @Override
    public ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT*FROM Customer");
        ResultSet rst=stm.executeQuery();
        ArrayList<Customer>customers=new ArrayList<>();
        while (rst.next()){
            customers.add(new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getDouble(7)
                    ));
        }
        return customers;
    }
}
