package controller;

import Model.ItemDeails;
import Model.Order;
import Model.OrderDetail;
import Model.Orderr;
import db.DbConnection;
import view.tm.OrderTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderController {
    //labe
    public String getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT*FROM `Order` ORDER BY orderId DESC LIMIT 1").executeQuery();
        if(rst.next()){
            int tempId =Integer.parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if(tempId<9){
                return "0-00"+tempId;
            }else if(tempId<99){
                return "0-0"+tempId;
            }else {
                return "0-"+tempId;
            }
        }else {
            return "0-001";
        }

    }

    public boolean placeOrder(Order order){
        Connection con=null;
        try {
            con=DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stm = con.
                    prepareStatement("INSERT INTO `Order`VALUES(?,?,?,?,?)");
                    stm.setObject(1,order.getOrderId());
                    stm.setObject(2,order.getCustomerId());
                    stm.setObject(3,order.getOrderDate());
                    stm.setObject(4,order.getTime());
                    stm.setObject(5,order.getCost());


            if(stm.executeUpdate()>0){
                if(saveOrderDetail(order.getOrderId(),order.getItems())){
                    con.commit();
                    return true;
                }else {
                    con.rollback();
                    return false;
                }

            }else{
                con.rollback();
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
         }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }
   private boolean saveOrderDetail(String orderId, ArrayList<ItemDeails> items) throws SQLException, ClassNotFoundException {
        for (ItemDeails temp:items) {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO `order detail` VALUES(?,?,?,?)");
                stm.setObject(1,temp.getItemCode());
                stm.setObject(2,orderId);
                stm.setObject(3,temp.getQtyForSell());
                stm.setObject(4,temp.getUnitPrice());

                if(stm.executeUpdate()>0){
                    if(updateQty(temp.getItemCode(),temp.getQtyForSell())){

                    }else{
                        return false;
                    }

                }else{
                    return false;
                }
        }
        return true;
    }
    private boolean updateQty(String itemcode,int qty) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE ITEM SET qtyOnHand=(qtyOnHand-" + qty + ")WHERE ItemCode='" + itemcode + "'");
    return stm.executeUpdate()>0;

    }
    public ArrayList<Orderr> getAllOrders() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT*FROM `order`");
           ResultSet rst=stm.executeQuery();
           ArrayList<Orderr>orderrs=new ArrayList<>();
           while(rst.next()){
            orderrs.add(new Orderr(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5)
                    ));
           }
           return orderrs;
    }
    //tblOrderDetail
   public ArrayList<OrderDetail>getAllOrderDetail() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT*FROM `Order Detail`");
        ResultSet rst=stm.executeQuery();
        ArrayList<OrderDetail>orders=new ArrayList<>();
        while(rst.next()){
            orders.add(
                    new OrderDetail(
                             rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getString(4)
                    ));
        }
        return orders;
    }
}
