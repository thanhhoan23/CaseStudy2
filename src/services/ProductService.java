package services;

import models.Product;
import utils.AppUtils;
import utils.CSVUtils;
import views.ProductView;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    public static final String path = "/Users/nguyenthithanhhoan/Downloads/workspace/CaseStudy2/data/products.csv";

    private static ProductService instanceProduct;

    public static ProductService getInstanceProduct() {
        if (instanceProduct == null) {
            instanceProduct = new ProductService();
        }
        return instanceProduct;
    }

    public ProductService() {
    }

    @Override
    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            products.add(Product.parseProduct(record));
        }
        return products;
    }

    //   1. Thêm sản phẩm mới
    @Override
    public void addProduct(Product newProduct) {
        newProduct.setCreatedAt(Instant.now().toString());
        newProduct.setUpdatedAt("-");
        List<Product> products = findAllProducts();
        products.add(newProduct);
        CSVUtils.write(path, products);
    }

    //2. Xóa sản phẩm theo id
    @Override
    public void removeProduct(long productId) {
        List<Product> products = findAllProducts();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getIdProduct() == productId) {
                products.remove(i);
                break;
            }
        }
        CSVUtils.write(path, products);
    }

//    3. xóa sản phẩm theo thể loại
    @Override
    public void removeProductByCategory(String category) {
        List<Product> products = findAllProducts();
        products.removeIf(product1 -> product1.getCategory().contains(category));
        CSVUtils.write(path, products);

//        List<Product> products = findAllProducts();
//        for (int  i =0; i< products.size(); i++) {
//            if (products.get(i).getCategory().contains(category)) {
//                products.remove(i);
//                CSVUtils.write(path, products);
//            }
//        }

    }
//   Tìm sản phẩm bằng id
    @Override
    public Product findProductById(long id) {
        List<Product> products = findAllProducts();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getIdProduct()== id) {
                return products.get(i);
            }
        }
        return null;
    }

    //    tìm sản phẩm bằng tên
    @Override
    public List<Product> findProductByName(String nameProduct) {
        List<Product> products = findAllProducts();
        List<Product> listFind = new ArrayList<>();
        if (nameProduct != null) {
            for (Product oldProduct : products) {
                if (oldProduct.getNameProduct().toLowerCase().contains(nameProduct.toLowerCase())) {
                    listFind.add(oldProduct);
                }
            }
        }
        return listFind;
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        List<Product> products = findAllProducts();
        List<Product> listFind = new ArrayList<>();
        if (category != null) {
            for (Product oldProduct : products) {
                if (oldProduct.getCategory().toLowerCase().contains(category.toLowerCase())) {
                    listFind.add(oldProduct);
                }
            }
        }
        return listFind;
    }
    @Override
    public boolean exitsById(Long idProduct) {
            return findProductById(idProduct) != null;
    }

    @Override
    public boolean exitsByCategory(String category) {
        return findProductByCategory(category) != null;
    }
    @Override
    public int findIndexProductById(long id) {
        List<Product> products = findAllProducts();
        int index = -1;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getIdProduct() == id) {
                index = i;
                return index;
            }
        }
        return index;
    }

    // Sửa tên sản phẩm
    @Override
    public void setNameProduct(int index, String name) {
        List<Product> products = findAllProducts();
        products.get(index).setNameProduct(name);
        products.get(index).setUpdatedAt(Instant.now().toString());
        CSVUtils.write(path, products);
    }

    //    Sửa giá sản phẩm
   @Override
    public void setPriceProduct(int index, double price) {
        List<Product> products = findAllProducts();
        products.get(index).setPrice(price);
        products.get(index).setUpdatedAt(Instant.now().toString());
        CSVUtils.write(path, products);
    }

    //    Sửa khối lượng sản phẩm
    @Override
    public void setQuantityProduct(int index, int quantity) {
        List<Product> products = findAllProducts();
        products.get(index).setQuantity(quantity);
        products.get(index).setUpdatedAt(Instant.now().toString());
        CSVUtils.write(path, products);
    }

//    Cập nhật khối lượng ở phần order sản phẩm
    @Override
    public void updateQuantity(Long idProduct, Double quantity) {
        List<Product> products = findAllProducts();
        for (Product oldProduct : products) {
            if (oldProduct.getIdProduct() == (idProduct)) {
                Double oldQuantity = oldProduct.getQuantity();
                if (oldQuantity >= quantity) {
                    oldProduct.setQuantity(oldQuantity - quantity);
                    CSVUtils.write(path, products);
                    break;
                }
            }
        }

    }
}

