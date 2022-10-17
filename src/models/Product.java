package models;

import java.time.Instant;

public class Product {
    private long getIdProduct;
    private long idProduct;
    private String nameProduct;
    private double price;
    private double quantity;
    private String category;
    private String createdAt;

    private String updatedAt;

    public Product() {
    }

    public Product(long idProduct, String nameProduct, double price, double quantity,String category) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public Product(long idProduct, String nameProduct, double price, double quantity, String category, String createdAt, String updatedAt) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    //    line là những dòng để ghi file
    public static Product parseProduct(String line) {
        String[] array = line.split(",");

        Product product = new Product();
        product.setIdProduct(Long.parseLong(array[0]));
        product.setNameProduct(array[1]);

        product.setPrice(Double.parseDouble(array[2]));
        product.setQuantity(Double.parseDouble(array[3]));
        product.setCategory(array[4]);
        product.setCreatedAt(array[5]);
        product.setUpdatedAt(array[6]);

        return product;

//        String[] array = line.split(",");
//        String sIdProduct = array[0];
//        String nameProduct = array[1];
//        String sPrice = array[2];
//        String sQuantity = array[3];
//
//        long idProduct = Long.parseLong(sIdProduct);
//        double price = Double.parseDouble(sPrice);
//        int quantity = Integer.parseInt(sQuantity);
//        return new Product(idProduct, nameProduct, price, quantity);

    }


    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
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

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getGetIdProduct() {
        return getIdProduct;
    }

    public void setGetIdProduct(long getIdProduct) {
        this.getIdProduct = getIdProduct;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s",
                idProduct,
                nameProduct,
                price,
                quantity,
                category,
                createdAt,
                updatedAt);
    }
}
