package views;

import models.User;
import services.UserService;
import shoeshop.Menu;
import utils.AppUtils;
import utils.InstantUtils;
import utils.RetryUtils;
import utils.ValidateUtils;

import java.time.Instant;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private static final String ACCOUNT_ROLE_ADMIN = "admin";
    private static final String ACCOUNT_PASSWORD_ROLE_ADMIN = "admin";
    private static final String CODE_ROLE_ADMIN = "admin";
    public List<User> products;
    private static UserService userService = new UserService();

    static Scanner input = new Scanner(System.in);


//    Cho người dùng
    //    tạo tài khỏan
    public static void signIn() {
        boolean flagChoose = false;
        do {
            System.out.println();
            System.out.println("Tạo tài khoản");
            Long userId = System.currentTimeMillis() / 1000;
            String userName = inputNameAccountUser();
            String password = inputPassword(ChooseStatus.ADD);
            String fullName = inputFullName(ChooseStatus.ADD);
            String phoneNumber = inputPhoneNumber(ChooseStatus.ADD);
            String email = inputEmail(ChooseStatus.ADD);
            String address = inputAddress(ChooseStatus.ADD);
            String roleUser = inputRoleUser();
            User user = new User(userId, userName, password, fullName, phoneNumber, email, address, roleUser, Instant.now());
            System.out.println("Bạn đã đăng kí thành công. Hãy vui lòng quay lại màn hình đăng nhập.");
            userService.addUser(user);
            int choice;
            do {
                System.out.println("Nhấn 0 để quay về màn hình đăng nhập.");
                System.out.print("=> ");
                choice = AppUtils.retryParseInt();
            } while (choice != 0);
            Menu.shoeShop();
        } while (!flagChoose);

    }

    public static String inputNameAccountUser() {
        boolean check = true;
        String nameAccount = null;
        System.out.println("⟹⟹⟹ Nhập tên tài khoản ⟸⟸⟸ ");
        do {
            System.out.print("⟹");
            nameAccount = input.nextLine();

            if (!ValidateUtils.isNameAccountInvalid(nameAccount)) {
                System.out.println("Tên " + nameAccount + " không đúng định dạng." + " Vui lòng nhập lại!" + "Tên tài khoản có thể là viết thường hoặc viết chữ hoa");
                check = false;
            } else if (!(userService.exitsByAccount(nameAccount))) {
                System.out.println("Nhập tên thành công");
                check=true;
            } else {
                System.out.println("Tên tài khoản bị trùng vui lòng nhập lại.");
                check=false;
            }

        } while (check==false);
        return nameAccount;
    }
    public static String inputPassword(ChooseStatus status) {
        String passwordAccountUser;
        switch (status) {
            case ADD:
                System.out.println("Nhập mật khẩu của bạn (8 <= Password <= 16): ");
                break;
            case EDIT:
                System.out.println("Nhập mật khẩu tài khoản bạn muốn thay đổi: ");
                break;
        }
        System.out.print("=> ");
        while (!ValidateUtils.isNamePasswordInValid(passwordAccountUser = input.nextLine())) {
            System.out.println("Tên " + passwordAccountUser + " không đúng định dạng." + " Vui lòng nhập lại!" + " (Mật khẩu là chữ số)");
            System.out.print("=> ");
        }
        return passwordAccountUser;
    }

    public static String inputFullName(ChooseStatus status) {
        String fullName;
        switch (status) {
            case ADD:
                System.out.println("Nhập tên của bạn (VD: Thanh Hoan): ");
                break;
            case EDIT:
                System.out.println("Nhập tên bạn muốn thay đổi: ");
                break;
        }
        System.out.print("=> ");
        while (!ValidateUtils.isNameInValid(fullName = input.nextLine())) {
            System.out.println("Tên " + fullName + " không đúng định dạng." + " Vui lòng nhập lại!" + " (Tên phải viết hoa chữ cái đầu và không dấu)");
            System.out.print("=> ");
        }
        return fullName;
    }

    public static String inputPhoneNumber(ChooseStatus status) {
        String phoneNumber;
        switch (status) {
            case ADD:
                System.out.println("Nhập số điện thoại của bạn: ");
                break;
            case EDIT:
                System.out.println("Nhập số điện thoại mới của bạn: ");
                break;
        }
        System.out.print("=> ");
        boolean flagInputTitle = true;
        do {
            phoneNumber = input.nextLine();
            if (!ValidateUtils.isPhoneNumberInValid(phoneNumber)) {
                System.out.println("Số " + phoneNumber + " của bạn không đúng. Vui lòng nhập lại. (Số điện thoại bao gồm 10 số và bắt đầu là số 0)");
                System.out.println("Nhập số điện thoại (VD: 0392657898)");
                System.out.print("=> ");
                continue;
            }
            break;
        } while (flagInputTitle);
        return phoneNumber;
    }

    public static String inputEmail(ChooseStatus status) {
        String email;
        switch (status) {
            case ADD:
                System.out.println("Nhập Email của bạn: ");
                break;
            case EDIT:
                System.out.println("Nhập Email mới của bạn: ");
                break;
        }
        System.out.print("=> ");
        boolean flagInputTitle = true;
        do {
            if (!ValidateUtils.isEmailInValid(email = input.nextLine())) {
                System.out.println("Email " + email + "của bạn không đúng định dạng! Vui lòng kiểm tra và nhập lại ");
                System.out.println("Nhập email (VD: ngthanhhoan3006@gmail.com)");
                System.out.print("=> ");
                continue;
            }
            break;
        } while (flagInputTitle);
        return email;
    }

    public static String inputAddress(ChooseStatus status) {
        String address;
        switch (status) {
            case ADD:
                System.out.println("Nhập địa chỉ của bạn: ");
                break;
            case EDIT:
                System.out.println("Nhập địa chỉ mới của bạn: ");
                break;
        }
        System.out.print("=> ");
        boolean flagInputTitle = true;
        do {
            address = input.nextLine();
            boolean exits = (!address.isEmpty());
            switch (status) {
                case ADD:
                case EDIT:
                    if (!exits) {
                        System.out.println("Địa chỉ không được để trống, vui lòng nhập lại:");
                        System.out.print("=> ");
                    }
                    flagInputTitle = !exits;
                    break;
            }
        } while (flagInputTitle);
        return address;
    }

    public static String inputRoleUser() {
        System.out.println("Nhập mã Admin của bạn, nếu nhập sai hoặc không nhập bạn là User.");
        System.out.print("=> ");
        String setRole = input.nextLine();
        String role;
        if (setRole.equals(CODE_ROLE_ADMIN)) {
            role = "Admin";
        } else {
            role = "User";
        }
        return role;
    }

    //    USER sửa thông tin
    public static void editInformation() {
        boolean check = false;
        String choose;
        do {
            try {
                System.out.println("⟹⟹⟹ Nhập tên tài khoản ⟸⟸⟸ ");
                System.out.print("⟹");
                String nameAccount = input.nextLine();

                if (userService.exitsByAccount(nameAccount)) {
                    System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸");
                    System.out.println("⟹⟹⟹           Sửa thông tin                    ⟸⟸⟸");
                    System.out.println("⟹⟹⟹   1. Nhập mật khẩu bạn muốn sửa            ⟸⟸⟸");
                    System.out.println("⟹⟹⟹   2. Nhập họ và tên bạn muốn sửa           ⟸⟸⟸");
                    System.out.println("⟹⟹⟹   3. Nhập số đện thoại                     ⟸⟸⟸");
                    System.out.println("⟹⟹⟹   3. Nhập địa chỉ email   ⟸⟸⟸");
                    System.out.println("⟹⟹⟹   3. Nhập địa chỉ   ⟸⟸⟸");
                    System.out.println("⟹⟹⟹ 0. Nhập 0 để quay lại màn hình ⟸⟸⟸");
                    System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸");
                    System.out.println("Vui lòng chọn chức năng");
                    System.out.print("⟹");

                    boolean flag = true;
                    do {
                        choose = input.nextLine();
                        switch (choose) {
                            case "1":
                                userService.setPassWordUser(userService.findIndexProductByNameAccount(nameAccount), inputPassword(ChooseStatus.EDIT));
                                break;
                            case "2":
                                userService.setFullNameUser(userService.findIndexProductByNameAccount(nameAccount), inputFullName(ChooseStatus.EDIT));
                                break;
                            case "3":
                                userService.setPhoneNumberUser(userService.findIndexProductByNameAccount(nameAccount), inputPhoneNumber(ChooseStatus.EDIT));
                                break;
                            case "4":
                                userService.setEmailUser(userService.findIndexProductByNameAccount(nameAccount), inputEmail(ChooseStatus.EDIT));
                                break;
                            case "5":
                                userService.setAddressUser(userService.findIndexProductByNameAccount(nameAccount), inputAddress(ChooseStatus.EDIT));
                                break;
                            case "0":
                                Menu.menuAdmin();
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại");
                                System.out.println("⟹");
                        }
                    }while (flag);
                } else {
                    System.out.println("Không tìm thấy tên tài khoản của bạn.");
                    RetryUtils.isRetryUser(ChooseStatus.EDIT);
                }
            } catch (Exception e) {
                System.out.println("Vui lòng nhập lại");
            }
        } while (!check);
    }


//    Phần admin
//    xóa người dùng
public static void removeUserByNameAccount() {
    boolean check = false;
    String choose;
    showUserList();
    do {
        try {
            System.out.println("Nhập tên tài khoản người dùng mà bạn muốn xóa ");
            System.out.print("⟹");
            String nameAccount = input.nextLine();
            if (userService.exitsByAccount(nameAccount)) {
                System.out.println("⇅⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⇅");
                System.out.println("⇅         ► Xóa tài khoản ◄              ⇅");
                System.out.println("⇅         1.     Đồng ý                  ⇅");
                System.out.println("⇅         2.     Quay lại                ⇅");
                System.out.println("⇅⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⇅");

                System.out.println("Vui lòng chọn chức năng");
                System.out.print("⟹");

                boolean flag = true;
                do {
                    choose = input.nextLine();
                    switch (choose) {
                        case "1":
                            userService.removeAccountOfUser(nameAccount);
                            System.out.println("Sản phẩm đã được xóa");
                            showUserList();
                            break;
                        case "2":
                            Menu.menuManagerUsers();
                            break;
                        default:
                            System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại");
                            System.out.println("⟹");
                    }
                }while (flag);
            } else {
                System.out.println("Không tìm thấy thể loại sản phầm.");
                RetryUtils.isRetryUser(ChooseStatus.REMOVE);
            }
        } catch (Exception e) {
            System.out.println("Vui lòng nhập lại");
        }
    } while (check);

}

//pHÂN QUYỀN
    public static void setRoleUser () {
        try {
            showUserList();
            System.out.println("Nhập tên tài khoản bạn muốn đổi");
            System.out.print("⟹");
            String nameAccount = input.nextLine();
            if (userService.exitsByAccount(nameAccount)) {
                boolean flagUpdate = true;
                System.out.println("Phân quyền người dùng");
                System.out.println("1. Cài đặt quyền Admin");
                System.out.println("2. Cài đặt quyền User");
                System.out.println("0. Quay lại màn hình quản lý user ");
                System.out.println("Vui lòng chọn chức năng");
                System.out.print("⟹");
                do {
                    String choose = input.nextLine();
                    switch (choose) {
                        case "1":
                            userService.setAdmins(nameAccount);
                            System.out.println("Bạn đã cài đặt quyền Admin thành công!");
                            RetryUtils.isRetryUser(ChooseStatus.BACKMENUUSER);
                            break;
                        case "2":
                            userService.setUser(nameAccount);
                            System.out.println("Bạn đã cài đặt quyền User thành công!");
                            RetryUtils.isRetryUser(ChooseStatus.BACKMENUUSER);
                            break;
                        case "0":
                            Menu.menuManagerUsers();
                            break;
                        default:
                            System.out.println("Lựa chọn không hợp lệ vui lòng nhập lại.");
                            System.out.print("=> ");
                            flagUpdate = false;
                    }
                } while (!flagUpdate);
            } else {
                System.out.println("Không tìm thấy tài khoản User.");
                setRoleUser();
            }
        } catch (Exception ex) {
            System.out.println("Server lỗi! Bạn đã được đưa về trang");
            setRoleUser();
        }
    }

    //    Phần đăng nhập
    public static void logIn() {
        User users;
//        boolean check= false;
        do {
            try {
                System.out.println("⟹⟹⟹⟹⟹Đăng nhập⟸⟸⟸⟸⟸");
                System.out.println("Vui lòng nhập tên tài khoản: ");
                System.out.print("=>");
                String userName = input.nextLine();
                System.out.println("Vui lòng nhập mật khẩu: ");
                System.out.print("=>");
                String passWord = input.nextLine();
                users = userService.loginUser(userName, passWord);
                if (userName.equals(ACCOUNT_ROLE_ADMIN) && passWord.equals(ACCOUNT_PASSWORD_ROLE_ADMIN)) {
                    System.out.println("Đăng nhập thành công");
                    Menu.menuAdmin();
                } else if (users.getNameAccountUser().equals(userName) && users.getPassword().equals(passWord)) {
                    if (users.getRole().equals("User")) {
                        System.out.println("Đăng nhập đã thành công");
                        Menu.menuUser();
                    } else if (users.getRole().equals("Admin")) {
                        System.out.println("Đăng nhập thành công");
                        Menu.menuAdmin();
                    }
                    break;
                }
            } catch (Exception e) {
                System.out.println("Tài khoản sai. Vui lòng nhập lại.");
                continueOrExit();
            }
        } while (true);
    }

    public static void continueOrExit() {
        boolean flag = true;
        do {
            System.out.println("Nhấn 1 để tiếp tục or nhấn 2 để thoát");
            System.out.print("=> ");
            String choice = input.nextLine();
            switch (choice) {
                case "1":
                    logIn();
                    break;
                case "2":
                    Menu.shoeShop();
                    break;
                default:
                    System.out.println("Lựa chọn không đúng vui lòng nhập lại");
                    flag = false;
                    break;
            }
        } while (!flag);
    }

    public static void continueOrExitUser() {
        boolean flag = true;
        do {
            System.out.println("Nhấn 0 để quay lại");
            System.out.print("=> ");
            String choice = input.nextLine();
            switch (choice) {
                case "0":
                    Menu.menuUser();
                    break;
                default:
                    System.out.println("Lựa chọn không đúng vui lòng nhập lại");
                    flag = false;
                    break;
            }
        } while (!flag);
    }
//Của admin

    public static void continueOrExitAdmin() {
        boolean flag = true;
        do {
            System.out.println("Nhấn 0 để quay lại");
            System.out.print("=> ");
            String choice = input.nextLine();
            switch (choice) {
                case "0":
                    Menu.menuManagerUsers();
                    break;
                default:
                    System.out.println("Lựa chọn không đúng vui lòng nhập lại");
                    flag = false;
                    break;
            }
        } while (!flag);
    }

    public static void showUserList() {
        System.out.println();
        System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹ Danh sách Users ⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸");
        System.out.printf("%-15s %-15s %-25s %-25s %-15s %-25s %-25s %-10s %-25s\n", "ID", "Username", "Password", "FullName", "Phone Number", "Email", "Address", "Role", "Time Creat");
        System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸");

        for (User user : userService.findAllUsers()) {
            System.out.printf("%-15s %-15s %-25s %-25s %-15s %-25s %-25s %-10s %-25s\n",
                    user.getIdUser(),
                    user.getNameAccountUser(),
                    user.getPassword(),
                    user.getFullName(),
                    user.getEmail(),
                    user.getPhoneNumber(),
                    user.getAddress(),
                    user.getRole(),
                    user.getTimeCreatUser());
//                    InstantUtils.instantConvertString(user.getTimeCreatUser()));
        }
        System.out.println("⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟹⟸⟸⟸⟸⟸⟹⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸⟸");

    }
}
