package be.thomasmore.travelmore.repository;

import be.thomasmore.travelmore.domain.Location;
import be.thomasmore.travelmore.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class UserRepository {
    @PersistenceContext(unitName = "travelMorePU")
    private EntityManager entityManager;

    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    public List<User> findAll() {
        return entityManager.createNamedQuery(Location.FIND_ALL, User.class).getResultList();
    }

    public void insert(User user) {
        entityManager.persist(user);
    }

    public User compareLogin(User compareLogin){
        return entityManager.createNamedQuery(User.COMPARE_LOGIN, User.class)
                .setParameter("email", compareLogin.getEmail())
                .setParameter("password", compareLogin.getPassword())
                .getSingleResult();
    }

}
