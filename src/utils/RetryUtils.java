package utils;

import shoeshop.Menu;
import views.ChooseStatus;
import views.ProductView;
import views.UserView;

import java.util.Scanner;

public class RetryUtils {
    public static final Scanner input = new Scanner(System.in);

    public static boolean isRetryProduct(ChooseStatus inputOption) {
        do {
            switch (inputOption) {
                case ADD:
                    System.out.println("Nhấn 'c' để thêm tiếp \t|\t 'b' để quay lại \t|\t 'e' để thoát chương trình");
                    break;
                case EDIT:
                    System.out.println("Nhấn 'c' để sửa tiếp \t|\t 'b' để quay lại \t|\t 'e' để thoát chương trình");
                    break;
                case FIND:
                    System.out.println("Nhấn 'c' để tìm tiếp \t|\t 'b' để quay lại\t|\t 'e' để thoát chương trình");
                    break;
                case REMOVE:
                    System.out.println("Nhấn 'c' để xóa tiếp \t|\t 'b' để quay lại\t|\t 'e' để thoát chương trình");
                    break;
                case DISPLAY:
                    System.out.println("Nhấn 'b' để quay lại\t|\t 'e' để thoát chương trình");
                    break;
                case DISORDER:
                    System.out.println("Nhấn 'b' để quay lại\t|\t 'e' để thoát chương trình");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + inputOption);
            }
            System.out.print("⟹");
            String option = input.nextLine();
            do {
                switch (option) {
                    case "c":
                        switch (inputOption) {
                            case ADD:
                                ProductView.addProduct();
                                break;
                            case EDIT:
                                ProductView.editProduct();
                                break;
                            case REMOVE:
                                ProductView.removeProduct();
                                break;
                            case FIND:
                                ProductView.findProduct();
                                break;
                        }
                        break;
                    case "b":
                        switch (inputOption) {
                            case ADD:
                            case EDIT:
                            case REMOVE:
                            case FIND:
                            case DISPLAY:
                                Menu.menuManagerProduct();
                                break;
                            case DISORDER:
                                Menu.menuManagerOrder();
                                break;

                        }
                    case "e":
                        exit();
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        break;
                }
            } while (true && option== null);
        } while (true);
    }
    public static boolean isRetryUser(ChooseStatus inputOption) {
        do {
            switch (inputOption) {
                case ADD:
                    System.out.println("Nhấn 'b' để quay lại \t|\t 'e' để thoát chương trình");
                    break;
                case EDIT:
                    System.out.println("Nhấn 'c' để sửa tiếp \t|\t 'b' để quay lại \t|\t 'e' để thoát chương trình");
                    break;
                case REMOVE:
                    System.out.println("Nhấn 'c' để xóa tiếp \t|\t 'b' để quay lại\t|\t 'e' để thoát chương trình");
                    break;
                case BACKMENUUSER:
                    System.out.println("Nhấn 'c' để tiếp tục \t|\t 'b' để quay lại\t|\t 'e' để thoát chương trình");
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + inputOption);
            }
            System.out.print("⟹");
            String option = input.nextLine();
            do {
                switch (option) {
                    case "c":
                        switch (inputOption) {
                            case ADD:
                                break;
                            case EDIT:
                                UserView.editInformation();
                                break;
                            case REMOVE:
//                                admin xóa
                                UserView.removeUserByNameAccount();
                                break;
                            case BACKMENUUSER:
//                                admin cài đặt phân quyền
                                UserView.setRoleUser();
                                break;
                        }
                        break;
                    case "b":
                        switch (inputOption) {
                            case REMOVE:
                            case BACKMENUUSER:
                                Menu.menuManagerUsers();
                                break;
                            case ADD:
//                                add của user
                                Menu.menuUser();
                                break;
                        }
                        break;
                    case "e":
                        exit();
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        break;
                }
            } while (true && option==null);
        } while (true);
    }
    public static void exit() {
        System.out.println("\tTạm biệt. Hẹn gặp lại lần sau!");
        System.exit(5);
    }
}
