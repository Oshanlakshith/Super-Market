package controller;

import Model.Order;
import Model.Orderr;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.OrderTM;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderTableController {

    public TableView<OrderTM> tblOrdes;
    public TableColumn colOId;
    public TableColumn colCId;
    public TableColumn colOrderDate;
    public TableColumn colTime;
    public TableColumn colCost;

    OrderTM selectOrder = null;

    public void initialize() {
        colOId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCId.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("Time"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        try {
            setOrderDetail(new OrderController().getAllOrders());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        tblOrdes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectOrder = newValue;
            }
        });
    }

    private void setOrderDetail(ArrayList<Orderr> orders) {
        ObservableList<OrderTM> oblist = FXCollections.observableArrayList();
        orders.forEach(e -> {
            oblist.add(
                    new OrderTM(e.getOrderId(), e.getCustomerId(), e.getOrderDate(), e.getTime(), e.getCost())
            );
        });
        tblOrdes.setItems(oblist);
    }

    public void DeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(selectOrder!=null) {
           if(deleteItem(selectOrder.getOrderId())){
               new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
               setOrderDetail(new OrderController().getAllOrders());
           }else{
               new Alert(Alert.AlertType.WARNING, "Try again..").show();

           }
        }
    }

    boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM `Order` WHERE OrderId=?");
        stm.setString(1, id);
        return stm.executeUpdate() > 0;
    }
}
