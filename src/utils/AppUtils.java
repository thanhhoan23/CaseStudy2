package utils;

import java.util.Scanner;

public class AppUtils {
    public static Scanner input = new Scanner(System.in);
    public static double retryParseDouble() {
        double result;
        do {
            try {
                result = Double.parseDouble(input.nextLine());
                return result;
            } catch (Exception ex) {
                System.out.println("Nhập sai! vui lòng nhập lại ( Là số )");
                System.out.print("=> ");
            }
        } while (true);
    }
    public static Long retryParseLong() {
        Long result;
        do {
            try {
                result = Long.parseLong(input.nextLine());
                return result;
            } catch (Exception ex) {
                System.out.println("Nhập sai! vui lòng nhập lại (ID là một chuỗi số)");
                System.out.print("=> ");
            }
        } while (true);
    }
}
