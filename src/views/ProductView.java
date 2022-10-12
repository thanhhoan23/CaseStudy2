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

        boolean check = false;
        do{
            try{
                System.out.println("⟹⟹⟹⟹ Thêm sản phẩm ⟸⟸⟸⟸");
                long idProduct = System.currentTimeMillis()/1000;
                System.out.println("Nhập tên sản phẩm:");
                System.out.print("⟹");
                String name;
                
                do {
                    name = input.nextLine().trim();
                    if (name.equals("") ||name.equals(null)) {
                        System.out.println("Vui lòng nhập lại tên sản phẩm");
                        System.out.print("⟹");
                    }
                } while (name.equals("") ||name.equals(null));

                System.out.println("Nhập giá sản phẩm:");
                System.out.print("⟹");
                double price;

                do {
                    price = Double.parseDouble(input.nextLine());
                    if (!(price > 1000)) {
                        System.out.println("Giá tiền không được nhỏ hơn 1000");
                        System.out.print("⟹ ");
                    }
                } while (!(price > 1000));

                System.out.println("Nhập số lượng sản phẩm:");
                System.out.print("⟹");
                int quantity;
                do {
                    quantity = Integer.parseInt(input.nextLine());
                    if (quantity<0) {
                        System.out.println("Khối lượng không được nhỏ hơn 0.");
                        System.out.print("⟹ ");
                    }
                } while (quantity<0);
                Product product = new Product(idProduct, name, price, quantity);
                productService.addProduct(product);
                System.out.println("Đã thêm thành công");
                showProductList();
                Menu.menuManagerProduct();

            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập lại.");
            }

        } while (true);

    }
    public static void showProductListShow(){
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


//    Sửa thông tin sản phẩm
//    1 sửa tên sản phẩm qua id
//    public static void editNameProductById() {
//
//    }
}
