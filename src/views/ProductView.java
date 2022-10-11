package views;

import models.Product;
import services.ProductService;
import shoeshop.Menu;
import utils.ConvertUtils;

import java.util.List;
import java.util.Scanner;

public class ProductView {
    public List<Product> list;
    static ProductService productService = new ProductService();
    static Scanner input = new Scanner(System.in);

    public static void addProduct() {

        boolean checkProduct = false;
        do{
            System.out.println("⟹⟹⟹⟹ Thêm sản phẩm ⟸⟸⟸⟸");
            long idProduct = System.currentTimeMillis()/1000;
            System.out.println("Nhập tên sản phẩm:");
            String name = input.nextLine();
            System.out.println("Nhập giá sản phẩm:");
            double price = input.nextDouble();
            System.out.println("Nhập số lượng sản phẩm:");
            int quantity = input.nextInt();

            Product product = new Product(idProduct, name, price, quantity);
            productService.addProduct(product);
            System.out.println("Đã thê thành công");
            showProductList();
            Menu.menuManagerProduct();

        } while (true);

    }
    public void showProductListShow(){
        showProductList();
        int choose;
        do {
            System.out.println("Nhấn 0 để quay lại màn hình chính");
            System.out.print("⟹");
            choose = ConvertUtils.convertParseInt();
        } while (choose != 0);
    }

//    Hiển thị sản phẩm:
    public static void showProductList() {
        System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹ Danh sách sản phẩm ⟸⟸⟸⟸⟸⟸⟸⟸");
        System.out.printf("%-25s %-20s %-20s %-20s\n", "ID", "Tên Sản Phẩm", "Giá", "Số lượng sản phẩm");
        for (Product product : productService.findAllProducts()) {
            System.out.printf("%-25s %-20s %-20s %-20s\n",
                    product.getIdProduct(),
                    product.getNameProduct(),
                    product.getPrice(),
                    product.getPrice());
        }
    }

}
