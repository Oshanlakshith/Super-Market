package controller;

import Model.Item;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.ItemTM;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDetailTableController {

    public TableView<ItemTM>tblItemDetail;
    public TableColumn colICode;
    public TableColumn colIName;
    public TableColumn colDescription;
    public TableColumn colQtyOnHand;
    public TableColumn colUnitPrice;

    ItemTM selectedItem=null;

    public void initialize() {
        colICode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colIName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        try {
            setItemDetail(new ItemController().getAllItems());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        tblItemDetail.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if(newValue!=null){
            selectedItem=newValue;
             }
         });
    }

    private void setItemDetail(ArrayList<Item> items) {
        ObservableList<ItemTM>oblist = FXCollections.observableArrayList();
        items.forEach(e -> {
            oblist.add(
                    new ItemTM(e.getItemCode(), e.getItemName(), e.getDescription(), e.getQtyOnHand(), e.getUnitPrice())
            );
        });
        tblItemDetail.setItems(oblist);

    }
    public void DeleteItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            if(selectedItem!=null) {
                if (deleteItem(selectedItem.getCode())) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
                    setItemDetail(new ItemController().getAllItems());
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try again..").show();
                }
            }
    }
    boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Item WHERE Itemcode=?");
        stm.setString(1,id);
        return stm.executeUpdate()>0;
    }
}

