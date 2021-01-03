package jwt.service;


import jwt.Entity.User;

import java.util.List;

public interface UserService {
    User getUserByEmail(String userName);

    User saveUser(User user);

    List<User> getAllUsers();
}
