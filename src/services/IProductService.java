package services;

import models.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAllProducts();
//Thêm sản phẩm Product

    void addProduct(Product newProduct);

    void removeProduct(long productId);
     void removeProductByCategory(String category);

    List<Product> findProductByName(String nameProduct);
    List<Product> findProductByCategory (String nameProduct);
}
