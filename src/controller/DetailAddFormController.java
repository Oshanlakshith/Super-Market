package controller;

import Model.Customer;
import Model.Item;
import Model.ItemDeails;
import Model.Order;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.CartTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailAddFormController {

    public AnchorPane DetailForm;
    public TextField txtCustomerName;
    public TextField txtCustomerAddress;
    public TextField txtCustomerSalary;
    public Label lblTime;
    public Label lblDate;
    public ComboBox<String> cmbItemCode;
    public ComboBox<String> cmbCustomerId;
    public TextField txtQuantity;
    public TextField txtQOnhand;
    public TextField txtCostOfItem;
    public TextField txtIName;
    public TextField txtDescription;
    public TableView <CartTm>tblCart;
    public TableColumn colCode;
    public TableColumn colName;
    public TableColumn colTotal;
    public TableColumn colDescription;
    public TableColumn colCostOfHand;
    public TableColumn colQuantity;
    public Label lblTotal;
    public Label lblOrderId;
    public Label lblDiscount;
    public TextArea context;

    int cartSelectedRowForRemove=-1;
    public void initialize(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        colCostOfHand.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        //OrderId
        setOrderId();

//==============================

        Time();
        Date();
        try {
            loadCustomerIds();
            loadItemIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setCustomerData(newValue);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        tblCart.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove= (int) newValue;
        });
    }
//orderId
    private void setOrderId() {
        try {
            lblOrderId.setText(new OrderController().getOrderId());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setItemData(String itemcode) throws SQLException, ClassNotFoundException {
        Item i1=new ItemController().getItem(itemcode);
        if(i1==null){
            new Alert(Alert.AlertType.WARNING, "Empty Result set..");
        }else{
            txtDescription.setText(i1.getDescription());
            txtIName.setText(i1.getItemName());
            txtQOnhand.setText(String.valueOf(i1.getQtyOnHand()));
            txtCostOfItem.setText(String.valueOf(i1.getUnitPrice()));
        }
    }

    private void setCustomerData(String cutomerId) throws SQLException, ClassNotFoundException {
        Customer c1=new CustomerController().getCustomer(cutomerId);
        if(c1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result");
        }else{
            txtCustomerName.setText(c1.getCustomerName());
            txtCustomerAddress.setText(c1.getCustomerAddress());
            txtCustomerSalary.setText(String.valueOf(c1.getCustomerSalary()));
        }
    }

    private void loadItemIds() throws SQLException, ClassNotFoundException {
        List<String>itemIds=new ItemController().getItemIds();
        cmbItemCode.getItems().addAll(itemIds);
    }


    public void CustomerAdd(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/CustomerAddForm.fxml"));
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public void UpdateCustomer(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/CustomerUpdate.fxml"));
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void CustomerDelete(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/CustomerDeleteForm.fxml"));
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }
    public void CustomerSearch(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/CustomerSearch.fxml"));
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void AllCustomer(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/AllCustomer.fxml"));
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }
    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        List<String>customerIds=new CustomerController().getCustomerIds();
        cmbCustomerId.getItems().addAll(customerIds);
    }

    private void Time(){
        Thread thread=new Thread(()->{
            SimpleDateFormat sdf=new SimpleDateFormat("hh:mm:ss a");

            boolean stop = false;
            while (!stop){
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    System.out.println(e);
                }
                final String timenow=sdf.format(new Date());
                Platform.runLater(()->{
                    lblTime.setText(timenow);
                });
            }
        });
        thread.start();

    }
    private void Date(){
        SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
        String datenow=sdf.format(new Date());
        lblDate.setText(datenow);

    }


    ObservableList<CartTm>oblist= FXCollections.observableArrayList();

    public void AddToCardOnAction(ActionEvent actionEvent) {
        String ItemName = txtIName.getText();
        String description = txtDescription.getText();
        int quantityOnHand = Integer.parseInt(txtQOnhand.getText());
        double unitprice = Double.parseDouble(txtCostOfItem.getText());
        int Quantity = Integer.parseInt(txtQuantity.getText());
        double total = Quantity * unitprice;


        if (quantityOnHand < Quantity) {
            new Alert(Alert.AlertType.WARNING, "Invalid Qty..").show();
            return;
        }
        CartTm tm = new CartTm(
                cmbItemCode.getValue(),
                ItemName,
                description,
                Quantity,
                unitprice,
                total
        );
        int rowNumber = isExists(tm);
        if (isExists(tm) == -1) {
            //update
            oblist.add(tm);
        } else {
            CartTm temp = oblist.get(rowNumber);
            CartTm newTm = new CartTm(

                    temp.getItemCode(),
                    temp.getItemName(),
                    temp.getDescription(),
                    temp.getQuantity() + Quantity,
                    unitprice,
                    total + temp.getTotal()
            );

            oblist.remove(rowNumber);
            // System.out.println(rowNumber+"remove");
            oblist.add(newTm);
        }

        tblCart.setItems(oblist);
        calculateCost();
    }

    private int isExists(CartTm tm){
        for (int i = 0; i < oblist.size(); i++) {
            if(tm.getItemCode().equals(oblist.get(i).getItemCode())){
                return  i;

            }
        }
        return -1;
    }
    void calculateCost(){
        double total=0;
        for (CartTm tm:oblist
             ) {
            total+=tm.getTotal();
        }
        lblTotal.setText(total+"/=");
        if(total>=2500){
            lblDiscount.setText(250+"/=");
        }else {

        }
    }

    public void ClearOnAction(ActionEvent actionEvent) {
        if(cartSelectedRowForRemove==-1){
            new Alert(Alert.AlertType.WARNING,"Please select a row").show();
        }else{
            oblist.remove(cartSelectedRowForRemove);
            calculateCost();
            tblCart.refresh();
        }
    }
    public void SaveButtonOnAction(ActionEvent actionEvent) {
      ArrayList<ItemDeails>items=new ArrayList<>();
      double total=0;
        for (CartTm tempTm:oblist
             ) {
            total+=tempTm.getTotal();

            items.add(new ItemDeails(
                    tempTm.getItemCode(),
                    tempTm.getUnitPrice(),
                    tempTm.getQuantity()
            ));
        }
        Order order=new Order(
                lblOrderId.getText(),
                cmbCustomerId.getValue(),
                lblDate.getText(),
                lblTime.getText(),
                total,
                items
        );
        if(new OrderController().placeOrder(order)){
            new Alert(Alert.AlertType.CONFIRMATION,"Success").show();
            setOrderId();
        }else{
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        }
    }

    public void menuOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/MenuPassword.fxml"));
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void refreshOnAction(ActionEvent actionEvent) throws IOException {
      URL resource=getClass().getResource("../view/DetailAddForm.fxml");
      Parent load=FXMLLoader.load(resource);
      DetailForm.getChildren().add(load);
    }
}