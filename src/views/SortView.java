package views;

import models.Product;
import services.ProductService;
import services.sort.*;
import shoeshop.Menu;
import utils.CSVUtils;
import utils.InstantUtils;
import utils.RetryUtils;

import java.util.List;
import java.util.Scanner;

import static views.ProductView.showProductList;

public class SortView {
    private static final Scanner input = new Scanner(System.in);
    private static ProductService productService = new ProductService();
    public static void menuSort () {
        try {
            boolean check = true;
            String choose;

            System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹Sắp xếp sản phẩm⟸⟸⟸⟸⟸⟸⟸⟸");
            System.out.println("⟹       1. Sắp xếp theo ID               ⟸");
            System.out.println("⟹   2. Sắp xếp theo tên sản phẩm         ⟸");
            System.out.println("⟹   3. Sắp xếp theo giá sản phẩm         ⟸");
            System.out.println("⟹ 4. Sắp xếp theo thể loại sản phẩm      ⟸");
            System.out.println("⟹  0. quay lại màn hình quản lý sản phẩm ⟸");
            System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸");
            System.out.println("Vui lòng chọn chức năng:");
            System.out.print("=>");
            do {
                choose = input.nextLine().trim();
                switch (choose) {
                    case "1":
                        sortProductByID();
                        break;

                    case "2":
                        sortProductByName();
                        break;

                    case "3":
                        sortProductByPrice();
                        break;
                    case "4":
                        sortProductByCategory();
                        break;
                    case "0":
                        Menu.menuManagerProduct();

                    default:
                        System.out.println("Lựa chọn không hợp lệ vui lòng nhập lại");
                        System.out.print("=>");
                        check = false;
                }

            } while (!check);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sortProductByID () {
        try {
            boolean check = true;
            String choose;

            System.out.println("⟹⟹⟹⟹⟹Sắp xếp id sản phẩm⟸⟸⟸⟸⟸");
            System.out.println("⟹          1. ID tăng dần         ⟸");
            System.out.println("⟹          2. ID giảm dần         ⟸");
            System.out.println("⟹     0. quay lại                 ⟸");
            System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸  ");
            System.out.println("Vui lòng chọn chức năng:");
            System.out.print("=>");
            do {
                choose = input.nextLine().trim();
                switch (choose) {
                    case "1":
                        sortIdASC();
                        break;
                    case "2":
                        sortIDDESC();
                        break;
                    case "0":
                        menuSort();
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ vui lòng nhập lại");
                        System.out.print("=>");
                        check = false;
                }

            } while (!check);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    Sắp xếp tăng dần ASC: accending

    public static void sortIdASC() {
        List<Product> products = productService.findAllProducts();
        SortIDByASC sortIDByASC = new SortIDByASC();
        products.sort(sortIDByASC);
        showSortList(products);
        sortProductByID();
    }
//    Sắp xếp giảm dần SortByIdDESC
    public static void sortIDDESC() {
        List<Product> products = productService.findAllProducts();
        SortIDByDESC sortIDByDESC = new SortIDByDESC();
        products.sort(sortIDByDESC);
        showSortList(products);
        sortProductByID();
    }

    public static void sortProductByName() {
        try {
            boolean check = true;
            String choose;

            System.out.println("⟹⟹⟹⟹⟹Sắp xếp theo tên sản phẩm⟸⟸⟸⟸⟸");
            System.out.println("⟹          1.  tên theo A-z         ⟸");
            System.out.println("⟹          2. Tên theo z-a         ⟸");
            System.out.println("⟹     0. quay lại                 ⟸");
            System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸  ");
            System.out.println("Vui lòng chọn chức năng:");
            System.out.print("=>");
            do {
                choose = input.nextLine().trim();
                switch (choose) {
                    case "1":
                        sortNameASC();
                        break;
                    case "2":
                        sortNameDESC();
                        break;
                    case "0":
                        menuSort();
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ vui lòng nhập lại");
                        System.out.print("=>");
                        check = false;
                }

            } while (!check);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sortNameASC() {
        List<Product> products = productService.findAllProducts();
        SortNameByASC sortNameByASC = new SortNameByASC();
        products.sort(sortNameByASC);
        showSortList(products);
        sortProductByName();
    }
    private static void sortNameDESC() {
        List<Product> products = productService.findAllProducts();
        SortNameByDESC sortNameByDESC = new SortNameByDESC();
        products.sort(sortNameByDESC);
        showSortList(products);
        sortProductByName();
    }

//Sắp xếp theo giá
public static void sortProductByPrice() {
    try {
        boolean check = true;
        String choose;

        System.out.println("⟹⟹⟹⟹⟹Sắp xếp theo tên sản phẩm⟸⟸⟸⟸⟸");
        System.out.println("⟹          1.  Giá tăng dần        ⟸");
        System.out.println("⟹          2. Giá giảm dần         ⟸");
        System.out.println("⟹     0. quay lại                 ⟸");
        System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸  ");
        System.out.println("Vui lòng chọn chức năng:");
        System.out.print("=>");
        do {
            choose = input.nextLine().trim();
            switch (choose) {
                case "1":
                    sortPriceASC();
                    break;
                case "2":
                    sortPriceDESC();
                    break;
                case "0":
                    menuSort();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ vui lòng nhập lại");
                    System.out.print("=>");
                    check = false;
            }

        } while (!check);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    private static void sortPriceASC() {
        List<Product> products = productService.findAllProducts();
        SortPriceByASC sortPriceByASC = new SortPriceByASC();
        products.sort(sortPriceByASC);
        showSortList(products);
        sortProductByPrice();
    }
    private static void sortPriceDESC() {
        List<Product> products = productService.findAllProducts();
        SortPriceByDESC sortPriceByASC = new SortPriceByDESC();
        products.sort(sortPriceByASC);
        showSortList(products);
        sortProductByPrice();
    }
//Sắp xếp theo thể loại sản phẩm
    private static void sortProductByCategory() {

        try {
            boolean check = true;
            String choose;

            System.out.println("⟹⟹⟹⟹⟹Sắp xếp thể loại sản phẩm⟸⟸⟸⟸⟸");
            System.out.println("⟹         1. Theo thể loại từ a-z       ⟸");
            System.out.println("⟹         2. Theo thể loại từ z-a       ⟸");
            System.out.println("⟹        0. quay lại                    ⟸");
            System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸");
            System.out.println("Vui lòng chọn chức năng:");
            System.out.print("=>");
            do {
                choose = input.nextLine().trim();
                switch (choose) {
                    case "1":
                        sortCategoryASC();
                        break;
                    case "2":
                        sortCategoryDESC();
                        break;
                    case "0":
                        menuSort();
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ vui lòng nhập lại");
                        System.out.print("=>");
                        check = false;
                }

            } while (!check);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    Theo từ a-z
    private static void sortCategoryASC() {
        List<Product> products = productService.findAllProducts();
        SortCategoryByASC sortCategoryByASC = new SortCategoryByASC();
        products.sort(sortCategoryByASC);
        showSortList(products);
        sortProductByCategory();
    }
//Theo từ z-a
    private static void sortCategoryDESC() {
        List<Product> products = productService.findAllProducts();
        SortCategoryByDESC sortCategoryByDESC = new SortCategoryByDESC();
        products.sort(sortCategoryByDESC);
        showSortList(products);
        sortProductByCategory();
    }
    public static void showSortList(List<Product> products) {
        System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸");
        System.out.printf("%-15s %-30s %-20s %-10s %-25s %-25s %-25s\n", "ID", "Tên Sản Phẩm", "Giá", "Số lượng", "Thể loại", "Ngày tạo", "Ngày cập nhật");
        for (Product product : products) {
            System.out.printf("%-15s %-30s %-20s %-10s %-25s %-25s %-25s\n",
                    product.getIdProduct(),
                    product.getNameProduct(),
                    InstantUtils.convertVND(product.getPrice()),
                    product.getQuantity(),
                    product.getCategory(),
                    product.getCreatedAt(),
                    product.getUpdatedAt());
        }
        System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸");
        RetryUtils.isRetry(ChooseStatus.FIND);
    }

}
