package web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDAO;
import web.model.User;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserDAO userDAO;
    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @Transactional
    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }
    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
    @Transactional
    @Override
    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }
    @Transactional
    @Override
    public void delete(long id) {
        userDAO.delete(id);
    }
    @Transactional
    @Override
    public void updateUser(long id, User userForUpdate) {
        userDAO.updateUser(id, userForUpdate);

    }
}
