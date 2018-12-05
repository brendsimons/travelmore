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

    public List<Trip> searchMin(Trip search) {
        return entityManager.createNamedQuery(Trip.SEARCH_MIN, Trip.class)
                .setParameter("departureLocation", search.getDepartureLocation().getId())
                .setParameter("arrivalLocation", search.getArrivalLocation().getId())
                .setParameter("goDate", search.getGoDate())
                .setParameter("backDate", search.getBackDate())
                .setParameter("places", search.getPlaces())
                .getResultList();
    }

    public List<Trip> searchMinPrice(Trip search) {
        return entityManager.createNamedQuery(Trip.SEARCH_MIN, Trip.class)
                .setParameter("departureLocation", search.getDepartureLocation().getId())
                .setParameter("arrivalLocation", search.getArrivalLocation().getId())
                .setParameter("goDate", search.getGoDate())
                .setParameter("backDate", search.getBackDate())
                .setParameter("places", search.getPlaces())
                .setParameter("price", search.getPrice())
                .getResultList();
    }

    public List<Trip> searchMinTransportType(Trip search) {
        return entityManager.createNamedQuery(Trip.SEARCH_MIN, Trip.class)
                .setParameter("departureLocation", search.getDepartureLocation().getId())
                .setParameter("arrivalLocation", search.getArrivalLocation().getId())
                .setParameter("goDate", search.getGoDate())
                .setParameter("backDate", search.getBackDate())
                .setParameter("places", search.getPlaces())
                .setParameter("transportType", search.getTransportType().getId())
                .getResultList();
    }

    public List<Trip> searchAll(Trip search) {
        return entityManager.createNamedQuery(Trip.SEARCH_ALL, Trip.class)
                .setParameter("departureLocation", search.getDepartureLocation().getId())
                .setParameter("arrivalLocation", search.getArrivalLocation().getId())
                .setParameter("goDate", search.getGoDate())
                .setParameter("backDate", search.getBackDate())
                .setParameter("places", search.getPlaces())
                .setParameter("price", search.getPrice())
                .setParameter("transportType", search.getTransportType().getId())
                .getResultList();
    }
}
