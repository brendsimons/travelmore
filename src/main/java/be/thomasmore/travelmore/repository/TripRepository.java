package be.thomasmore.travelmore.repository;

import be.thomasmore.travelmore.domain.Location;
import be.thomasmore.travelmore.domain.Trip;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TripRepository {
    @PersistenceContext(unitName = "travelMorePU")
    private EntityManager entityManager;

    public Trip findById(int id) {
        return entityManager.find(Trip.class, id);
    }

    public List<Trip> findAll() {
        return entityManager.createNamedQuery(Trip.FIND_ALL, Trip.class).getResultList();
    }

    public void insert(Trip trip) {
        entityManager.persist(trip);
    }

    public List<Trip> search(Trip search) {
        return entityManager.createNamedQuery(Trip.FIND_ALL, Trip.class)
                .setParameter("departureLocation", search.getDepartureLocation())
                .setParameter("arrivalLocation", search.getArrivalLocation())
                .setParameter("goDate", search.getGoDate())
                .setParameter("backDate", search.getBackDate())
                .setParameter("places", search.getPlaces())
                .getResultList();
    }
}
