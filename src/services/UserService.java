package services;

import models.Product;
import models.User;
import utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
    public static final String path = "/Users/nguyenthithanhhoan/Downloads/workspace/CaseStudy2/data/users.csv";
    private static UserService instanceUser;

    public UserService() {
    }

    public static UserService getInstanceUser() {
        if (instanceUser == null) {
            instanceUser = new UserService();
        }
        return instanceUser;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            users.add(User.parseUser(record));
        }
        return users;
    }

    @Override
    public void addUser(User user) {
        List<User> users = findAllUsers();
        users.add(user);
        CSVUtils.write(path, users);
    }

    @Override
    public User loginUser(String username, String password) {
        List<User> users = findAllUsers();
        for (User user : users) {
            if (user.getNameAccountUser().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean exitsByAccount(String nameAccountUser) {
        return findProductByName(nameAccountUser) != null;
    }

    @Override
    public int findIndexProductByNameAccount(String nameAccount) {
        List<User> users = findAllUsers();
        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getNameAccountUser().equals(nameAccount)) {
                index=i;
                return index;
            }
        }
        return index;
    }

    public User  findProductByName(String nameAccount) {
        List<User> users = findAllUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getNameAccountUser().equals(nameAccount)) {
                return users.get(i);
            }
        }
        return null;
    }
    public void setPassWordUser (int index, String password) {
        List<User> users = findAllUsers();
        users.get(index).setPassword(password);
        CSVUtils.write(path, users);
    }

    public void setFullNameUser (int index, String name) {
        List<User> users = findAllUsers();
        users.get(index).setFullName(name);
        CSVUtils.write(path, users);
    }
    public void setPhoneNumberUser (int index, String phoneNumber) {
        List<User> users = findAllUsers();
        users.get(index).setPhoneNumber(phoneNumber);
        CSVUtils.write(path, users);
    }
    public void setEmailUser (int index, String email) {
        List<User> users = findAllUsers();
        users.get(index).setEmail(email);
        CSVUtils.write(path, users);
    }
    public void setAddressUser (int index, String address) {
        List<User> users = findAllUsers();
        users.get(index).setAddress(address);
        CSVUtils.write(path, users);
    }
    public void setAdmins (String userName) {
        List<User> users = findAllUsers();
        for (User user : users) {
            if (user.getNameAccountUser().equals(userName)) {
                user.setRole("Admin");
            }
        }
        CSVUtils.write(path, users);
    }
    public void setUser (String userName) {
        List<User> users = findAllUsers();
        for (User user : users) {
            if (user.getNameAccountUser().equals(userName)) {
                user.setRole("User");
            }
        }
        CSVUtils.write(path, users);
    }

    //    admin xóa account user
    @Override
    public void removeAccountOfUser(String nameAccount) {
        List<User>users = findAllUsers();
        for (int  i =0; i< users.size(); i++) {
            if (users.get(i).getNameAccountUser().equals(nameAccount)) {
                users.remove(i);
                break;
            }
        }
        CSVUtils.write(path, users);
    }
}
