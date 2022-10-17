package services;

import models.Product;
import utils.CSVUtils;

import java.time.Instant;
import java.util.List;

public interface IProductService {
    List<Product> findAllProducts();
    //Thêm sản phẩm Product
    void addProduct(Product newProduct);

    void removeProduct(long productId);
     void removeProductByCategory(String category);
    Product  findProductById(long id);
    boolean exitsById(Long idProduct);
    boolean exitsByCategory(String category);
    int findIndexProductById(long id);

    List<Product> findProductByName(String nameProduct);
    List<Product> findProductByCategory (String nameProduct);
    void setNameProduct(int index, String name);
    void setPriceProduct(int index, double price) ;
    void setQuantityProduct(int index, int quantity);


//    Sau khi order sp thì cập nhật lại
    void updateQuantity(Long idProduct, Double quantity);

}
