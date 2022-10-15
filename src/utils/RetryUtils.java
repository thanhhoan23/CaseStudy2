package utils;

import shoeshop.Menu;
import views.ChooseStatus;

import java.util.Scanner;

public class RetryUtils {
    public static final Scanner input = new Scanner(System.in);

    public static boolean isRetry(ChooseStatus inputOption) {
        do {
            switch (inputOption) {
                case ADD:
                    System.out.println("Nhấn 'c' để thêm tiếp \t|\t 'b' để quay lại \t|\t 'e' để thoát chương trình");
                    break;
                case EDIT:
                    System.out.println("Nhấn 'c' để sửa tiếp \t|\t 'b' để quay lại\t|\t 'e' để thoát chương trình");
                    break;
                case FIND:
                    System.out.println("Nhấn 'c' để tìm tiếp \t|\t 'b' để quay lại\t|\t 'e' để thoát chương trình");
                    break;
                case REMOVE:
                    System.out.println("Nhấn 'c' để xóa tiếp \t|\t 'b' để quay lại\t|\t 'e' để thoát chương trình");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + inputOption);
            }

            System.out.print(" ⭆ ");
            String option = input.nextLine();
            switch (option) {
                case "c":
                    return true;
                case "b":
                    Menu.menuManagerProduct();
                    return false;
                case "e":
                    exit();
                    break;
                default:
                    System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                    break;
            }
        } while (true);
    }

    public static void exit() {
        System.out.println("\tTạm biệt. Hẹn gặp lại lần sau!");
        System.exit(5);
    }
}
