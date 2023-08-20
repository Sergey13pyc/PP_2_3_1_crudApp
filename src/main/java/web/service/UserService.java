package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user); //запрос update
    List<User> getAllUsers(); // запрос select
    User getUserById (long id);

    void delete(long id); //запрос delete
    void updateUser (long id, User userForUpdate);
}
