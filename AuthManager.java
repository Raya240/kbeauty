package kbeauty;

import java.util.*;

public class AuthManager {
    private List<User> users = new ArrayList<>();

    public AuthManager() {
        users.add(new User("sofa", "1234"));
        users.add(new User("admin", "admin"));
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.authenticate(username, password)) {
                return user;
            }
        }
        return null;
    }
}