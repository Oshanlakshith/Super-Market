package view.tm;

import java.util.Objects;

public class CartTm {
    String ItemCode;
    String ItemName;
    String Description;
    int Quantity;
    double UnitPrice;
    double total;

    public CartTm() {
    }

    public CartTm(String itemCode, String itemName, String description, int quantity, double unitPrice, double total) {
        ItemCode = itemCode;
        ItemName = itemName;
        Description = description;
        Quantity = quantity;
        UnitPrice = unitPrice;
        this.total = total;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CartTm{" +
                "ItemCode='" + ItemCode + '\'' +
                ", ItemName='" + ItemName + '\'' +
                ", Description='" + Description + '\'' +
                ", Quantity=" + Quantity +
                ", UnitPrice=" + UnitPrice +
                ", total=" + total +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartTm cartTm = (CartTm) o;
        return Objects.equals(ItemCode, cartTm.ItemCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ItemCode);
    }
}