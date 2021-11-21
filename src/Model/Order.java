package Model;

import java.util.ArrayList;
import java.util.Objects;

public class Order {
    String orderId;
    String CustomerId;
    String OrderDate;
    String Time;
    double cost;
    ArrayList<ItemDeails> items;

    public Order() {
    }

    public Order(String orderId, String customerId, String orderDate, String time, double cost, ArrayList<ItemDeails> items) {
        this.orderId = orderId;
        CustomerId = customerId;
        OrderDate = orderDate;
        Time = time;
        this.cost = cost;
        this.items = items;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ArrayList<ItemDeails> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemDeails> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", CustomerId='" + CustomerId + '\'' +
                ", OrderDate='" + OrderDate + '\'' +
                ", Time='" + Time + '\'' +
                ", cost=" + cost +
                ", items=" + items +
                '}';
    }
}