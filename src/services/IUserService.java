package services;

import models.Product;
import models.User;
import utils.CSVUtils;

import java.util.List;

public interface IUserService {
    List<User> findAllUsers();

    //    Thêm người dùng
    void addUser(User user);

    User loginUser(String username, String password);

    boolean exitsByAccount(String nameAccountUser);

    int findIndexProductByNameAccount(String nameAccount);

    void setFullNameUser(int index, String name);

    //     Xóa tài khoản ngươì dùng admin
    void removeAccountOfUser(String nameAccount);

    void setAdmins(String userName);

    void setUser(String userName);
}
