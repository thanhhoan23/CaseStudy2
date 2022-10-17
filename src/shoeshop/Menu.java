package shoeshop;

import models.Order;
import utils.RetryUtils;
import views.*;

import java.util.Scanner;

public class Menu {
    static Scanner input = new Scanner(System.in);

    public static void shoeShop() {
        try {
            boolean check = true;
            String choose;

            System.out.println("⇅⟹⟹⟹⟹⟹  Shoe  Shop  ⟸⟸⟸⟸⇅");
            System.out.println("⇅      1. Đăng nhập            ⇅");
            System.out.println("⇅      2. Tạo tài khoản        ⇅");
            System.out.println("⇅   0. Thoát chương trình      ⇅");
            System.out.println("⇅⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⇅");
            System.out.println("Vui lòng chọn chức năng:");
            System.out.print("⟹");
            do {
                choose = input.nextLine();
                switch (choose) {
                    case "1":
                        UserView.logIn();
                        break;
                    case "2":
                        UserView.signIn();
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ vui lòng nhập lại");
                        System.out.print("=>");
                }

            } while (check);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void menuAdmin() {
        try {
            boolean check = true;
            String choose;

            System.out.println("⇅⟹⟹⟹⟹⟹⟹⟹⟹  Menu ⟸⟸⟸⟸⟸⟸⟸⟸⟸⇅");
            System.out.println("⇅    1. Quản lý sản phẩm              ⇅");
            System.out.println("⇅    2. Quản lý đơn hàng              ⇅");
            System.out.println("⇅    3. Quản lý người dùng            ⇅");
            System.out.println("⇅    4. Quay lại màn hình đăng nhập   ⇅");
            System.out.println("⇅    0. Thoát chương trình            ⇅");
            System.out.println("⇅⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⇅");
            System.out.println("Vui lòng chọn chức năng:");
            System.out.print("⟹");
            do {
                choose = input.nextLine();
                switch (choose) {
                    case "1":
                        menuManagerProduct();
                        break;
                    case "2":
                        menuManagerOrder();
                        break;
                    case "3":
                        menuManagerUsers();
                        break;
                    case "4":
                        shoeShop();
                        break;
                    case "0":
                        exit();
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                        System.out.print("⟹");
//                    check = false;
                }
            } while (check);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void menuManagerProduct() {
        boolean check = true;
        String choose;
        System.out.println("⇅⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹ Menu ⟸⟸⟸⟸⟸⟸⟸⟸⇅");
        System.out.println("⇅    1. Thêm sản phẩm                   ⇅");
        System.out.println("⇅    2. Sửa thông tin sản phẩm          ⇅");
        System.out.println("⇅    3. Xóa sản phẩm                    ⇅");
        System.out.println("⇅    4. Hiển thị danh sách sản phẩm     ⇅");
        System.out.println("⇅    5. Tìm kiếm sản phẩm               ⇅");
        System.out.println("⇅    6. Sắp xếp sản phẩm                ⇅");
        System.out.println("⇅    0. Quay lại menu Admin             ⇅");
        System.out.println("⇅⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⇅");
        System.out.println("Vui lòng chọn chức năng:");
        System.out.print("⟹");
        do {
            choose = input.nextLine();

            switch (choose) {
                case "1":
                    ProductView.addProduct();
                    break;
                case "2":
                    ProductView.editProduct();
                    break;
                case "3":
                    ProductView.removeProduct();
                    break;
                case "4":
                    ProductView.showProductList();
                    RetryUtils.isRetryProduct(ChooseStatus.DISPLAY);
                    break;
                case "5":
                    ProductView.findProduct();
                    break;
                case "6":
                    SortView.menuSort();
                    break;
                case "0":
                    menuAdmin();
                    break;
                default:
                    System.out.println("Lựa chọn không đúng vui lòng nhập lại");
                    System.out.print("⟹");
            }

        } while (check);
    }

    public static void menuManagerOrder() {
        boolean check = true;
        String choose;
        System.out.println("⇅⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹ Menu ⟸⟸⟸⟸⟸⟸⟸⟸⇅");
        System.out.println("⇅   1. Tạo đặt hàng                    ⇅");
        System.out.println("⇅   2. Xem danh sách đặt hàng          ⇅");
        System.out.println("⇅   0. Quay lại menu Admin             ⇅");
        System.out.println("⇅⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⇅");
        System.out.println("Vui lòng chọn chức năng:");
        System.out.print("⟹");
        do {
            choose = input.nextLine();
            switch (choose) {
                case "1":
                    OrderView.addOrder();
                    RetryUtils.isRetryProduct(ChooseStatus.DISORDER);
                    break;
                case "2":
                    OrderView.showListOrder();
                    RetryUtils.isRetryProduct(ChooseStatus.DISORDER);
                    break;
                case "0":
                    menuAdmin();
                    break;
                default:
                    System.out.println("Lựa chọn không đúng vui lòng nhập lại");
                    System.out.print("⟹");
            }
        } while (check);

    }

    public static void menuManagerUsers() {
        boolean check = true;
        String choose;
        System.out.println("⇅⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹ Menu ⟸⟸⟸⟸⟸⟸⟸⟸⟸⇅");
        System.out.println("⇅     1. Xem thông tin người dùng         ⇅");
        System.out.println("⇅     2. Xóa User                         ⇅");
        System.out.println("⇅     3. Cài đặt phân quyền               ⇅");
        System.out.println("⇅     0. Quay lại menu Admin              ⇅");
        System.out.println("⇅ ⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⇅");
        System.out.println("Vui lòng chọn chức năng:");
        System.out.print("⟹");
        do {
            choose = input.nextLine();

            switch (choose) {
                case "1":
                    UserView.showUserList();
                    UserView.continueOrExitAdmin();
                    break;
                case "2":
                    UserView.removeUserByNameAccount();
                    break;
                case "3":
                    UserView.setRoleUser();
                    break;
                case "0":
                    menuAdmin();
                    break;
                default:
                    System.out.println("Lựa chọn không đúng vui lòng nhập lại");
                    System.out.print("⟹");
            }
        } while (check);
    }

    public static void menuUser() {
        boolean check = true;
        String choose;

        System.out.println("⇅⟹⟹⟹⟹⟹⟹⟹⟹⟹ Menu  ⟸⟸⟸⟸⟸⟸⟸⟸⇅");
        System.out.println("⇅      1. Xem toàn bộ sản phẩm        ⇅");
        System.out.println("⇅      2. Đặt hàng                    ⇅");
//        System.out.println("⇅      3. Sửa thông tin               ⇅");
        System.out.println("⇅      0. Quay về màn hình chính      ⇅");
        System.out.println("⇅⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⇅");
        System.out.println("Vui lòng chọn chức năng:");
        System.out.print("⟹");
        do {
            choose = input.nextLine();
            switch (choose) {
                case "1":
                    ProductView.showProductListForUser();
                    UserView.continueOrExitUser();
                    break;
                case "2":
                    OrderView.addOrder();
                    break;
//                case "3":
//                    UserView.editInformation();
//                    break;
                case "0":
                    shoeShop();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                    System.out.print("⟹");
            }
        } while (check);
    }

    public static void exit() {
        System.out.println("\tTạm biệt. Hẹn gặp lại lần sau!");
        System.exit(5);
    }


}
