package views;

import models.Product;
import services.ProductService;
import shoeshop.Menu;
import utils.AppUtils;
import utils.ConvertUtils;
import utils.InstantUtils;
import utils.RetryUtils;

import java.util.List;
import java.util.Scanner;

public class ProductView {
    public List<Product> list;

    static ProductService productService = new ProductService();
    static Scanner input = new Scanner(System.in);

    public static void addProduct() {
        boolean check = false;
        do {
            try {
                System.out.println("⟹⟹⟹⟹ Thêm sản phẩm ⟸⟸⟸⟸");
                long idProduct = System.currentTimeMillis() / 1000;
                String name = inputNameProduct(ChooseStatus.ADD);
                double price = inputPriceProduct(ChooseStatus.ADD);
                int quantity = inputQuantityProduct(ChooseStatus.ADD);
                String category = inputCategoryProduct(ChooseStatus.ADD);
                Product product = new Product(idProduct, name, price, quantity, category);
                productService.addProduct(product);
                System.out.println("Đã thêm thành công");
                showProductList();
                RetryUtils.isRetryProduct(ChooseStatus.ADD);
//                Menu.menuManagerProduct();

            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập lại.");
                System.out.print("⟹ ");
            }
        } while (true);
    }

    //    Nhập tên và sửa tên sản phẩm bằng sửa dụng enum

    private static String inputNameProduct(ChooseStatus status) {
        switch (status) {
            case ADD:
                System.out.println("Nhập tên sản phẩm:");
                break;
            case EDIT:
                System.out.println("Nhập tên sản phẩm bạn muốn sửa:");
                break;
        }
        System.out.print("⟹");
        String name;
        boolean flag = true;

        do {
            name = input.nextLine();
            if (name.equals("") || name.equals(null)) {
                System.out.println("Vui lòng nhập lại tên sản phẩm");
                System.out.print("⟹");
                flag = false;
            } else if (!(name.length() < 30)) {
                System.out.println("Tên sản phẩm không được dài quá 30 ký tự");
                System.out.print("⟹");
                flag = false;
            } else {
                System.out.println("Đã nhập đúng");
                flag = true;
            }
        } while (flag == false);

        return name;
    }

    //    Nhập giá và sửa giá sản phẩm bằng sửa dụng enum

    private static double inputPriceProduct(ChooseStatus status) {
        switch (status) {
            case ADD:
                System.out.println("Nhập giá sản phẩm");
                break;
            case EDIT:
                System.out.println("Nhập giá sản phẩm bạn muốn sửa");
        }
        System.out.print("⟹");
        double price = 0;
        boolean check = false;

        do {
            try {
                price = Double.parseDouble(input.nextLine());
                if (!(price > 1000)) {
                    System.out.println("Giá tiền không được nhỏ hơn 1000");
                    System.out.print("⟹ ");
                } else if (price > 10000000) {
                    System.out.println("Giá tiền không được lớn hơn 10,000,000");
                    System.out.print("⟹ ");
                } else {
                    System.out.println("Đã nhập đúng");
                    check = true;
                }
            } catch (Exception e) {
                System.out.println("Vui lòng nhập lại giá");
                System.out.print("⟹ ");
            }
        } while (check == false);

        return price;
    }

    //    Nhập số lượng và sửa số lượng sản phẩm bằng sửa dụng enum

    private static int inputQuantityProduct(ChooseStatus status) {

        switch (status) {
            case ADD:
                System.out.println("Nhập số lượng sản phẩm:");
                break;
            case EDIT:
                System.out.println("Nhập số lượng sản phẩm cần sửa");
                break;
        }
        System.out.print("⟹");
        int quantity = 0;
        boolean check = false;
        do {
            try {
                quantity = Integer.parseInt(input.nextLine());
                if (quantity <= 0) {
                    System.out.println("Số lượng không được nhỏ hơn 0.");
                    System.out.print("⟹ ");
                    check = false;
                } else if (quantity > 500) {
                    System.out.println("Số lượng không được lớn hơn 500.");
                    System.out.print("⟹ ");
                    check = false;
                } else {
                    System.out.println("Đã nhập thành công");
                    check = true;
                }
            } catch (Exception e) {
                System.out.println("Vui lòng nhập lại số lượng");
                System.out.print("⟹ ");
            }

        } while (check == false);

        return quantity;
    }
//    Nhập và sửa theo thể loại sản phẩm
    private static String inputCategoryProduct(ChooseStatus status) {
        switch (status) {
            case ADD:
                System.out.println("Nhập thể loại sản phẩm:");
                break;
            case EDIT:
                System.out.println("Nhập thể loại sản phẩm bạn muốn sửa:");
                break;
        }
        System.out.print("⟹");
        String nameCategory;
        boolean flag = true;

        do {
                nameCategory = input.nextLine();
                if (nameCategory.equals("") || nameCategory.equals(null)) {
                    System.out.println("Vui lòng nhập lại thể lọai sản phẩm");
                    System.out.print("⟹");
                    flag = false;
                } else if (!(nameCategory.length() < 30)) {
                    System.out.println("Thể loại sản phẩm không được dài quá 30 ký tự");
                    System.out.print("⟹");
                    flag = false;
                } else {
                    System.out.println("Đã nhập đúng");
                    flag = true;
                }
        } while (flag == false);

        return nameCategory;
    }
    // Sửa sản phẩm
    public static void editProduct() {
        boolean check = false;
        String choose;
        showProductList();
        do {
            try {
                System.out.println("Nhập id mà bạn đang muốn sửa ");
                System.out.print("⟹");
                long idProduct = Long.parseLong(input.nextLine());
                if (productService.exitsById(idProduct)) {
                    System.out.println("Đã tìm thấy ID sản phẩm!");
                    System.out.println("⇅⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⇅");
                    System.out.println("⇅             ► Nhập thông tin bạn muốn sửa ◄            ⇅");
                    System.out.println("⇅      1. Nhập tên sản phẩm bạn muốn sửa                 ⇅");
                    System.out.println("⇅      2. Nhập giá sản phẩm bạn muốn sửa                 ⇅");
                    System.out.println("⇅      3. Nhập số lượng sản phẩm bạn muốn sửa            ⇅");
                    System.out.println("⇅      0. Nhập 0 để quay lại màn hình quản lý sản phẩm   ⇅");
                    System.out.println("⇅⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⇅");
                    System.out.println("Vui lòng chọn chức năng");
                    System.out.print("⟹");

                    boolean flag = true;
                    do {
                        choose = input.nextLine();

                        switch (choose) {
                            case "1":
                                productService.setNameProduct(productService.findIndexProductById(idProduct), inputNameProduct(ChooseStatus.EDIT));
                                showProductList();
                                break;
                            case "2":
                                productService.setPriceProduct(productService.findIndexProductById(idProduct), inputPriceProduct(ChooseStatus.EDIT));
                                showProductList();
                                break;
                            case "3":
                                productService.setQuantityProduct(productService.findIndexProductById(idProduct), inputQuantityProduct(ChooseStatus.EDIT));
                                showProductList();
                                break;
                            case "0":
                                Menu.menuManagerProduct();
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại");
                                System.out.println("⟹");
                        }
                    }while (flag);
                } else {
                    System.out.println("Không tìm thấy ID sản phầm.");
                    RetryUtils.isRetryProduct(ChooseStatus.EDIT);
                }
            } catch (Exception e) {
                System.out.println("Không nhận được thông tin");
                RetryUtils.isRetryProduct(ChooseStatus.EDIT);

//                System.out.println("Vui lòng nhập lại!");
            }
        } while (!check);
    }

    public static void removeProduct() {
        boolean check = false;
        String choose;
        do {
            try {
                System.out.println("⇅⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⇅");
                System.out.println("⇅            ► Xóa sản phẩm ◄            ⇅");
                System.out.println("⇅       1. Xóa sản phẩm theo ID          ⇅");
                System.out.println("⇅     2. Xóa sản phẩm theo thể loại      ⇅");
                System.out.println("⇅ 0.Quay lại màn hình quản lý sản phẩm   ⇅");
                System.out.println("⇅⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⇅");
                System.out.println("Vui lòng chọn chức năng");
                System.out.print("⟹");
                boolean flag = true;
                do {
                    choose = input.nextLine();

                    switch (choose) {
                        case "1":
                            removeProductByID();
                            break;
                        case "2":
                            removeProductByCategory();
                            break;
                        case "0":
                            Menu.menuManagerProduct();
                            break;
                        default:
                            System.out.println("Lựa chọn không hợp lệ");
                            System.out.print("⟹");
                    }
                }while (flag);
            } catch (Exception e) {
                System.out.println("Vui lòng nhập lại");
            }
        } while (check);
    }


    // xóa sản phẩm
    public static void removeProductByID() {

        boolean check = false;
        String choose;
        showProductList();
        do {
            try {
                System.out.println(" Nhập id Sản phẩm mà bạn muốn xóa ");
                System.out.print("⟹");
                long idProduct = Long.parseLong(input.nextLine());

                if (productService.exitsById(idProduct)) {
                    System.out.println("⇅⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⇅");
                    System.out.println("⇅          ► Xóa Sản Phẩm ◄           ⇅");
                    System.out.println("⇅         1.     Đồng ý               ⇅");
                    System.out.println("⇅         2.     Quay lại             ⇅");
                    System.out.println("⇅⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⇅");

                    System.out.println("Vui lòng chọn chức năng");
                    System.out.print("⟹");

                    choose = input.nextLine();

                    switch (choose) {
                        case "1":
                            productService.removeProduct(idProduct);
                            System.out.println("Sản phẩm đã được xóa");
                            showProductList();
                            break;
                        case "2":
                            Menu.menuManagerProduct();
                            break;
                        default:
                            System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại");
                            System.out.println("⟹");
                    }
                    check = RetryUtils.isRetryProduct(ChooseStatus.REMOVE);
                } else {
                    System.out.println("Không tìm thấy ID sản phầm.");
                    RetryUtils.isRetryProduct(ChooseStatus.REMOVE);
                }
            } catch (Exception e) {
                System.out.println("Vui lòng nhập lại");
            }
        } while (check);
    }

    //Tìm kiếm sản phẩm theo tên
    public static void findProduct() {
        boolean check = false;
        String choose;
        do {
            try {
                System.out.println("⇅⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⇅");
                System.out.println("⇅          ► Tìm kiếm sản phẩm ◄             ⇅");
                System.out.println("⇅     1. Tìm kiếm theo tên sản phẩm          ⇅");
                System.out.println("⇅     2. Tìm kiếm theo id sản phẩm           ⇅");
                System.out.println("⇅     3. Tìm kiếm theo thể loại sản phẩm     ⇅");
                System.out.println("⇅     0.Quay lại màn hình quản lý sản phẩm   ⇅");
                System.out.println("⇅⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸");
                System.out.println("Vui lòng chọn chức năng");
                System.out.print("⟹");

                choose = input.nextLine();

                switch (choose) {
                    case "1":
                        findProductByName();
                        break;
                    case "2":
                        findProductById();
                        break;
                    case "3":
                        findProductByCategory();
                        break;
                    case "0":
                        Menu.menuManagerProduct();
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ");
                }
                check = RetryUtils.isRetryProduct(ChooseStatus.FIND);
            } catch (Exception e) {
                System.out.println("Vui lòng nhập lại");
            }
        } while (check);
    }

    public static void findProductByName() {
        System.out.println("Nhập tên sản phẩm muốn tìm");
        System.out.print("=> ");
        String name = input.nextLine().trim();
        name.toLowerCase();
        List<Product> products = productService.findProductByName(name);
        if (products.size() != 0) {
            System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹Sản phẩm bạn cần tìm là ⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸");
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
            System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸");
            RetryUtils.isRetryProduct(ChooseStatus.FIND);
        } else {
            System.out.println("Tên " + name + " không có trong danh sách");
            RetryUtils.isRetryProduct(ChooseStatus.FIND);
        }
    }
    public static void findProductByCategory() {
        System.out.println("Nhập thể loại sản phẩm muốn tìm");
        System.out.print("=> ");
        String name = input.nextLine();
        name.toLowerCase();
        List<Product> products = productService.findProductByCategory(name);
        if (products.size() != 0) {
            System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹Sản phẩm bạn cần tìm là ⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸");
            System.out.printf("%-15s %-30s %-20s %-10s %-25s %-25s %-25s\n", "ID", "Tên Sản Phẩm", "Giá", "Số lượng", "Thể loại", "Ngày tạo", "Ngày cập nhật" );
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
            System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸");
            RetryUtils.isRetryProduct(ChooseStatus.FIND);
        } else {
            System.out.println("Tên " + name + " không có trong danh sách");
            RetryUtils.isRetryProduct(ChooseStatus.FIND);
        }
    }

    public static void findProductById() {
        System.out.println("Nhập ID sản phẩm muốn tìm");
        System.out.print("=> ");
        long idProductFind = AppUtils.retryParseLong();
        Product products = productService.findProductById(idProductFind);
        if (products != null) {
            System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹Sản phẩm bạn cần tìm là ⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸");
            System.out.printf("%-15s %-30s %-20s %-10s %-25s %-25s %-25s\n", "ID", "Tên Sản Phẩm", "Giá", "Số lượng", "Thể loại", "Ngày tạo", "Ngày cập nhật");
            System.out.printf("%-15s %-30s %-20s %-10s %-25s %-25s %-25s\n",
                    products.getIdProduct(),
                    products.getNameProduct(),
                    InstantUtils.convertVND(products.getPrice()),
                    products.getQuantity(),
                    products.getCategory(),
                    products.getCreatedAt(),
                    products.getUpdatedAt());
            System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸");
            RetryUtils.isRetryProduct(ChooseStatus.FIND);
        } else {
            System.out.println("ID " + idProductFind + " không có trong danh sách");
            RetryUtils.isRetryProduct(ChooseStatus.FIND);
        }
    }

//    public static void continueOrExitFind () {
//        RetryUtils.isRetryProduct(ChooseStatus.FIND);
//        findProductById();
//    }
//Xóa sản phẩm bằng thể loại
    public static void removeProductByCategory() {
        boolean check = false;
        String choose;
        showProductList();
        do {
            try {
                System.out.println(" Nhập thể loại Sản phẩm mà bạn muốn xóa ");
                System.out.print("⟹");
                String category = input.nextLine();
                if (productService.exitsByCategory(category)) {
                    System.out.println("⇅⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⇅");
                    System.out.println("⇅          ► Xóa Sản Phẩm ◄           ⇅");
                    System.out.println("⇅         1.     Đồng ý               ⇅");
                    System.out.println("⇅         2.     Quay lại             ⇅");
                    System.out.println("⇅⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⇅");

                    System.out.println("Vui lòng chọn chức năng");
                    System.out.print("⟹");

                    choose = input.nextLine();

                    switch (choose) {
                        case "1":
                            productService.removeProductByCategory(category);
                            System.out.println("Sản phẩm đã được xóa");
                            showProductList();
                            break;
                        case "2":
                            Menu.menuManagerProduct();
                            break;
                        default:
                            System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại");
                            System.out.println("⟹");
                    }
                    check = RetryUtils.isRetryProduct(ChooseStatus.REMOVE);
                } else {
                    System.out.println("Không tìm thấy thể loại sản phầm.");
                    RetryUtils.isRetryProduct(ChooseStatus.REMOVE);
                }
            } catch (Exception e) {
                System.out.println("Vui lòng nhập lại");
            }
        } while (check);
    }
    public static void showProductListShow() {
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
        System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹ Danh sách sản phẩm ⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸");
        System.out.printf("%-15s %-30s %-20s %-10s %-25s %-35s %-35s\n", "ID", "Tên Sản Phẩm", "Giá", "Số lượng","Thể loại", "Ngày tạo", "Ngày cập nhật");
        for (Product product : productService.findAllProducts()) {
            System.out.printf("%-15s %-30s %-20s %-10s %-25s %-35s %-35s\n",
                    product.getIdProduct(),
                    product.getNameProduct(),
                    InstantUtils.convertVND(product.getPrice()),
                    product.getQuantity(),
                    product.getCategory(),
                    product.getCreatedAt(),
                    product.getUpdatedAt());
        }
        System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸");
    }

    public static void showProductListForUser() {
        System.out.println("\t⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹ Danh sách sản phẩm ⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸");
        System.out.printf("\t%-15s %-30s %-20s %-10s %-25s\n", "ID", "Tên Sản Phẩm", "Giá", "Số lượng","Thể loại");
        for (Product product : productService.findAllProducts()) {
            System.out.printf("\t%-15s %-30s %-20s %-10s %-25s\n",
                    product.getIdProduct(),
                    product.getNameProduct(),
                    InstantUtils.convertVND(product.getPrice()),
                    product.getQuantity(),
                    product.getCategory());
        }
        System.out.println("\t⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸");
    }

}
