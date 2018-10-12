package be.thomasmore.travelmore.repository;

import be.thomasmore.travelmore.domain.Location;
import be.thomasmore.travelmore.domain.TransportType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TransportTypeRepository {
    @PersistenceContext(unitName = "travelMorePU")
    private EntityManager entityManager;

    public TransportType findById(int id) {
        return entityManager.find(TransportType.class, id);
    }

    public List<TransportType> findAll() {
        return entityManager.createNamedQuery(Location.FIND_ALL, TransportType.class).getResultList();
    }

    public void insert(TransportType transportType) {
        entityManager.persist(transportType);
    }

}
