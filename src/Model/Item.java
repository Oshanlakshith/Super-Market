package Model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

public class Item  {
    String ItemCode;
    String ItemName;
    String Description;
    int QtyOnHand;
    double UnitPrice;

    public Item() {
    }

    public Item(String itemCode, String itemName, String description, int qtyOnHand, double unitPrice) {
        ItemCode = itemCode;
        ItemName = itemName;
        Description = description;
        QtyOnHand = qtyOnHand;
        UnitPrice = unitPrice;
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

    public int getQtyOnHand() {
        return QtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        QtyOnHand = qtyOnHand;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Item{" +
                "ItemCode='" + ItemCode + '\'' +
                ", ItemName='" + ItemName + '\'' +
                ", Description='" + Description + '\'' +
                ", QtyOnHand=" + QtyOnHand +
                ", UnitPrice=" + UnitPrice +
                '}';
    }
}