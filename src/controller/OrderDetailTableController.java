package controller;

import Model.OrderDetail;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.OrderDetailTM;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


public class OrderDetailTableController {

    public TableColumn colItemCode;
    public TableColumn colOrderId;
    public TableColumn colQty;
    public TableColumn colPrice;
    public TableView<OrderDetailTM>tblOrderDtail;

    OrderDetailTM selectOrder = null;
    public void initialize() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        try {

            setOrderDetail(new OrderController().getAllOrderDetail());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        tblOrderDtail.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!= null) {
                selectOrder=newValue;
            }
        });
    }

    private void setOrderDetail(ArrayList<OrderDetail> orderDetails) {
        ObservableList<OrderDetailTM> oblist = FXCollections.observableArrayList();
        orderDetails.forEach(e -> {
            oblist.add(
                    new OrderDetailTM(e.getItemCode(), e.getOrderId(), e.getQty(), e.getPrice())
            );
        });
        tblOrderDtail.setItems(oblist);
    }

    public void DeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (selectOrder != null) {
            if (deleteItem(selectOrder.getOrderId())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
                setOrderDetail(new OrderController().getAllOrderDetail());
            } else {
                new Alert(Alert.AlertType.WARNING, "Try again..").show();
            }
        }
    }
    boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM `Order Detail` WHERE Itemcode=?");
        stm.setString(1, id);
        return stm.executeUpdate() > 0;
    }
}