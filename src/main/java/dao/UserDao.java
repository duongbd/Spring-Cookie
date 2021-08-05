package dao;

import model.User;

import java.util.List;
import java.util.ArrayList;

public class UserDao {
    private static final List<User> users;
    static {
        users = new ArrayList<User>();
        User u1 = new User();
        u1.setAge(10);
        u1.setName("John");
        u1.setAccount("john");
        u1.setEmail("john@codegym.vn");
        u1.setPassword("123456");
        users.add(u1);

        User u2 = new User();
        u2.setAge(15);
        u2.setName("Bill");
        u2.setAccount("bill");
        u2.setEmail("bill@codegym.vn");
        u2.setPassword("123456");
        users.add(u2);

        User u3 = new User();
        u3.setAge(16);
        u3.setName("Mike");
        u3.setAccount("mike");
        u3.setEmail("mike@codegym.vn");
        u3.setPassword("123456");
        users.add(u3);
    }

    public static boolean checkLogin(User user){
        for (User u: users) {
            if(u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }
}