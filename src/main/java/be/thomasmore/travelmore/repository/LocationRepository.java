package be.thomasmore.travelmore.repository;

import be.thomasmore.travelmore.domain.Location;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class LocationRepository {
    @PersistenceContext(unitName = "travelMorePU")
    private EntityManager entityManager;

    public Location findById(int id) {
        return entityManager.find(Location.class, id);
    }

    public List<Location> findAll() {
        return entityManager.createNamedQuery(Location.FIND_ALL, Location.class).getResultList();
    }

    public Location findByCode(String code) {
        return null;/*entityManager.createNamedQuery(Location.FIND_BY_CODE, Location.class).setParameter("code", code).getSingleResult();*/
    }

    public void insert(Location location) {
        entityManager.persist(location);
    }

    public void remove(Location location) {
        entityManager.remove(entityManager.merge(location));
    }

}
