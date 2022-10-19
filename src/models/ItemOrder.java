package models;

public class ItemOrder {
    private Long idItemOrder;
    private double price;
    private double quantity;
    private Long idOrder;
    private Long idProduct;
    private String nameProduct;
    private double totalItem;
    private double grandTotal;

    public ItemOrder(){};

    public ItemOrder(Long idItemOrder, double price, double quantity, Long idOrder, Long idProduct, String nameProduct, double totalItem) {
        this.idItemOrder = idItemOrder;
        this.price = price;
        this.quantity = quantity;
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.totalItem = totalItem;
//        this.grandTotal = grandPrice;
    }

    public static ItemOrder parseItemOrder(String rawItemOrder) {
        ItemOrder itemOrder = new ItemOrder();
        String[] array = rawItemOrder.split(",");
        itemOrder.idItemOrder = Long.parseLong(array[0]);
        itemOrder.price = Double.parseDouble(array[1]);
        itemOrder.quantity = Double.parseDouble(array[2]);
        itemOrder.idOrder = Long.parseLong(array[3]);
        itemOrder.idProduct = Long.parseLong(array[4]);
        itemOrder.nameProduct = array[5];
        itemOrder.totalItem = Double.parseDouble(array[6]);
//        itemOrder.grandTotal = Double.parseDouble(array[7]);
        return itemOrder;
    }

    public Long getIdItemOrder() {
        return idItemOrder;
    }

    public void setIdItemOrder(Long idItemOrder) {
        this.idItemOrder = idItemOrder;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(double totalItem) {
        this.totalItem = totalItem;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s",
                idItemOrder,
                price,
                quantity,
                idOrder,
                idProduct,
                nameProduct,
                totalItem
                );
    }
}
