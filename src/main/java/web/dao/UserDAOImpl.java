package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
@Repository
public class UserDAOImpl implements UserDAO{
    @PersistenceContext
    private EntityManager entityManager;

    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        String jpql = "Select u from User u";
        TypedQuery<User> userList = entityManager.createQuery(jpql, User.class);
        return userList.getResultList();
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void delete(long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public void updateUser(long id, User userForUpdate) {
        User user = entityManager.find(User.class, id);
        user.setFirstName(userForUpdate.getFirstName());
        user.setLastName(userForUpdate.getLastName());
        user.setEmail(userForUpdate.getEmail());
        entityManager.persist(user);
    }
}
