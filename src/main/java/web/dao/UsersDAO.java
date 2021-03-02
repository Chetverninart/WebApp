package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;

import javax.persistence.*;
import java.util.List;

@Repository
public class UsersDAO {

    @PersistenceContext
    private EntityManager em;

    public UsersDAO() {
    }

    public User getUser(int id) {
        return em.find(User.class, id);
    }

    @Transactional
    public List<User> getAllUsers() {
        return em.createQuery("select u from User u").getResultList();
    }

    @Transactional
    public void addUser(User user) {
        em.persist(user);
    }

    @Transactional
    public void remove(int id) {
        Query query = em.createQuery("delete from User u where u.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    public void update(User user) {
        em.merge(user);
    }

}
