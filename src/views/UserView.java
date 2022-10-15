package views;

import shoeshop.Menu;

import java.util.Scanner;

public class UserView {
    static Scanner input = new Scanner(System.in);

//    tạo tài khỏan
    public static void signIn(){

    }

    //    Phần đăng nhập
    public static void logIn() {
//        boolean check= false;
        do {
            try {
                System.out.println("⟹⟹⟹⟹⟹Đăng nhập⟸⟸⟸⟸⟸");
                System.out.println("Vui lòng nhập mã số tài khoản: ");
                System.out.print("=>");
                String userName = input.nextLine();
                System.out.println("Vui lòng nhập mật khẩu: ");
                System.out.print("=>");
                String passWord = input.nextLine();
                checkLogIn(userName, passWord);
            }catch (Exception e) {
                System.out.println("Tài khoản sai. Vui lòng nhập lại.");
            }
        } while (true);
    }

    public static void checkLogIn(String userName, String passWord) {
        if (userName.equals("admin") && passWord.equals("admin")) {
            System.out.println("Bạn đã đăng nhập thành công");
            Menu.menuAdmin();
        }
        else  {
            System.out.println("Tài khoản sai. Vui lòng nhập lại.");
        }
    }
}
