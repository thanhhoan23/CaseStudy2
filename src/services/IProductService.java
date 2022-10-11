package services;

import models.Product;

import java.util.List;

public interface IProductService {
List<Product> findAllProducts();
//Thêm sản phẩm Product

void addProduct(Product newProduct);

}
