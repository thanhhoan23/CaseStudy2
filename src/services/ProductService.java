package services;

import models.Product;
import utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    public static final String path = "/Users/nguyenthithanhhoan/Downloads/workspace/Case/Case/data/products.csv";

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

}

