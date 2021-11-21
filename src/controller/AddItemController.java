package controller;

import Model.Customer;
import Model.Item;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sun.font.FontScalerException;

import java.sql.SQLException;

public class AddItemController {

    public TextField txtItemCode;
    public TextField txtQtyOnHand;
    public TextField txtItemName;
    public TextField txtDescription;
    public TextField txtUnitPrice;

    public void AddItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item i1=new Item(
                txtItemCode.getText(),txtItemName.getText(),txtDescription.getText(),Integer.parseInt(txtQtyOnHand.getText()),Double.parseDouble(txtUnitPrice.getText()));

        if(AddItem(i1)){
            new Alert(Alert.AlertType.CONFIRMATION,"Add Item").show();
        }else{
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        }
    }
    boolean AddItem(Item i) throws SQLException, ClassNotFoundException {
        return new ItemController().AddItem(i);

    }

  public void UpdateItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item i1=new Item(
                txtItemCode.getText(),txtItemName.getText(),txtDescription.getText(),Integer.parseInt(txtQtyOnHand.getText()),Double.parseDouble(txtUnitPrice.getText()));
                if(new ItemController().updateItem(i1)){
                    new Alert(Alert.AlertType.CONFIRMATION,"Update").show();
                }else{
                    new Alert(Alert.AlertType.WARNING,"Try Again..").show();
                }
    }

    public void ItemSearch(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String Itemcode=txtItemCode.getText();
        Item c1=new ItemController().getItem(Itemcode);
        if(c1==null){
            new Alert(Alert.AlertType.WARNING,"Empty results...").show();
        }else {
            setData(c1);
        }
    }
    void setData(Item c){
        txtItemCode.setText(c.getItemCode());
        txtItemName.setText(c.getItemName());
        txtDescription.setText(c.getDescription());
        txtQtyOnHand.setText(String.valueOf(c.getQtyOnHand()));
        txtUnitPrice.setText(String.valueOf(c.getUnitPrice()));

    }

    public void DeleteItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(new ItemController().deleteItem(txtItemCode.getText())){
            new Alert(Alert.AlertType.CONFIRMATION,"Delete..").show();
        }else{
            new Alert(Alert.AlertType.WARNING,"Try Again...").show();
        }
    }
}
