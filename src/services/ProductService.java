package services;

import models.Product;
import utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    public static final String path = "/Users/nguyenthithanhhoan/Downloads/workspace/CaseStudy2/data/products.csv";

    @Override
    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            products.add(Product.parseProduct(record));
        }
        return products;
    }

//    Thêm sản phẩm mới
    @Override
    public void addProduct(Product newProduct) {
        List<Product> products = findAllProducts();
        products.add(newProduct);
        CSVUtils.write(path, products);
    }

//    Sửa thông tin sản phẩm

//   1. Sửa tên sản phẩm theo id
    public  void editNameProductById (long id, String name) {
        List <Product> products = findAllProducts();
        for (int i=0; i<products.size(); i++) {
            if (products.get(i).getIdProduct() == id) {
                products.get(i).setNameProduct(name);
            }
        }
    }


}

