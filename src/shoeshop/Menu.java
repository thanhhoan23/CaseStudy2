package shoeshop;

import utils.RetryUtils;
import views.ChooseStatus;
import views.ProductView;
import views.SortView;
import views.UserView;

import java.util.Scanner;

public class Menu {
    static Scanner input = new Scanner(System.in);

    public static void shoeShop() {
        try {
            boolean check = true;
            String choose;

            System.out.println("⟹⟹⟹⟹⟹ Shoe  Shop ⟸⟸⟸⟸⟸⟸");
            System.out.println("⟹      1. Đăng nhập          ⟸");
            System.out.println("⟹      2. Tạo tài khoản      ⟸");
            System.out.println("⟹   0. Thoát chương trình    ⟸");
            System.out.println("⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸");
            System.out.println("Vui lòng chọn chức năng:");
            System.out.print("=>");
            do {

                choose = input.nextLine();

                switch (choose) {
                    case "1":
                        UserView.logIn();
                        break;

                    case "2":
                        break;

                    case "3":
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

    public static void menuAdmin() {
        boolean check = true;
        String choose;

        System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹Menu⟸⟸⟸⟸⟸⟸⟸⟸⟸");
        System.out.println("⟹       1. Quản lý sản phẩm          ⟸");
        System.out.println("⟹       2. Quản lý đơn hàng          ⟸");
        System.out.println("⟹       3. Quản lý người dùng        ⟸");
        System.out.println("⟹   4. Quay lại màn hình đăng nhập   ⟸");
        System.out.println("⟹       0. Thoát chương trình        ⟸");
        System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸");
        System.out.println("Vui lòng chọn chức năng:");
        System.out.print("=>");
        do {
            choose = input.nextLine();
            switch (choose) {
                case "1":
                    menuManagerProduct();
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                    System.out.print("⟹");
//                    check = false;
            }
        } while (!check);
    }

    public static void menuManagerProduct() {
        boolean check = false;
        String choose;
        System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹Menu⟸⟸⟸⟸⟸⟸⟸⟸⟸");
        System.out.println("⟹   1. Thêm sản phẩm                 ⟸");
        System.out.println("⟹   2. Sửa thông tin sản phẩm        ⟸");
        System.out.println("⟹   3. Xóa sản phẩm                  ⟸");
        System.out.println("⟹   4. Hiển thị danh sách sản phẩm   ⟸");
        System.out.println("⟹   5. Tìm kiếm sản phẩm             ⟸");
        System.out.println("⟹   6. Sắp xếp sản phẩm              ⟸");
        System.out.println("⟹   0. Quay lại menu Admin           ⟸");
        System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸");
        System.out.println("Vui lòng chọn chức năng:");
        System.out.print("=>");
        do {
            choose = input.nextLine().trim();

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
                    RetryUtils.isRetry(ChooseStatus.FIND);
                    break;
                case "5":
                    ProductView.findProduct();
                    break;
                case "6":
                    SortView.menuSort();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Lựa chọn không đúng vui lòng nhập lại");
                    System.out.print("⟹");
                    check = false;
            }

        } while (!check);
    }

}
