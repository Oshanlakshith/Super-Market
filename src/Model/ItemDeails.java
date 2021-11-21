package Model;

public class ItemDeails {
    String itemCode;
    double unitPrice;
    int QtyForSell;

    public ItemDeails() {
    }

    public ItemDeails(String itemCode, double unitPrice, int qtyForSell) {
        this.itemCode = itemCode;
        this.unitPrice = unitPrice;
        QtyForSell = qtyForSell;

    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyForSell() {
        return QtyForSell;
    }

    public void setQtyForSell(int qtyForSell) {
        QtyForSell = qtyForSell;
    }

    @Override
    public String toString() {
        return "ItemDeails{" +
                "itemCode='" + itemCode + '\'' +
                ", unitPrice=" + unitPrice +
                ", QtyForSell=" + QtyForSell +
                '}';
    }
}