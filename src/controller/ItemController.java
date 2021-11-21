package controller;

import Model.Item;
import com.mysql.cj.jdbc.CallableStatement;
import db.DbConnection;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemController {
    public List<String> getItemIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT*FROM Item ").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString(1)
            );
        }
        return  ids;
    }
    public Item getItem(String id ) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT*FROM Item WHERE Itemcode='" + id + "'").executeQuery();
        if(rst.next()){
        return new Item(
        rst.getString(1),
        rst.getString(2),
        rst.getString(3),
        rst.getInt(4),
        rst.getDouble(5)
        );
        }else{
            return null;
        }
    }
    public boolean AddItem(Item i) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO item VALUES(?,?,?,?,?)");
        stm.setObject(1,i.getItemCode());
        stm.setObject(2,i.getItemName());
        stm.setObject(3,i.getDescription());
        stm.setObject(4,i.getQtyOnHand());
        stm.setObject(5,i.getUnitPrice());

        return stm.executeUpdate()>0;

    }
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        if(DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Item WHERE ItemCode='"+code+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }
   /* public Item getItem(String code) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT*FROM Item WHERE Itemcode=?");
        stm.setObject(1,code);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            Item i1=new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getDouble(5)

                    );
            return i1;
        }else{
            new Alert(Alert.AlertType.WARNING,"Empty Results....").show();
        }
        return null;
    }*/
    //table
    public ArrayList<Item>getAllItems() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT*FROM Item");
        ResultSet rst=stm.executeQuery();
        ArrayList<Item>items=new ArrayList<>();
        while (rst.next()){
            items.add(new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getDouble(5)

                    ));
        }
            return items;
    }

  public boolean updateItem(Item c) throws SQLException, ClassNotFoundException {
      PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Item SET ItemName=?,Description=?,QtyOnHand=?,unitPrice=? WHERE Itemcode=? ");
      stm.setObject(1,c.getItemName());
      stm.setObject(2,c.getDescription());
      stm.setObject(3,c.getQtyOnHand());
      stm.setObject(4,c.getUnitPrice());
      stm.setObject(5,c.getItemCode());
      return stm.executeUpdate()>0;
  }
}
