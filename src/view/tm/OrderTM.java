package view.tm;

public class OrderTM {
    String orderId;
    String CustomerId;
    String OrderDate;
    String Time;
    double cost;

    public OrderTM() {
    }

    public OrderTM(String orderId, String customerId, String orderDate, String time, double cost) {
        this.orderId = orderId;
        CustomerId = customerId;
        OrderDate = orderDate;
        Time = time;
        this.cost = cost;
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

    @Override
    public String toString() {
        return "OrdreTM{" +
                "orderId='" + orderId + '\'' +
                ", CustomerId='" + CustomerId + '\'' +
                ", OrderDate='" + OrderDate + '\'' +
                ", Time='" + Time + '\'' +
                ", cost=" + cost +
                '}';
    }
}
