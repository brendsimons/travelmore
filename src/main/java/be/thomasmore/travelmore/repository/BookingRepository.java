package be.thomasmore.travelmore.repository;

import be.thomasmore.travelmore.domain.Booking;
import be.thomasmore.travelmore.domain.Location;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class BookingRepository {
    @PersistenceContext(unitName = "travelMorePU")
    private EntityManager entityManager;

    public Booking findById(int id) {
        return entityManager.find(Booking.class, id);
    }

    public List<Booking> findAll() {
        return entityManager.createNamedQuery(Location.FIND_ALL, Booking.class).getResultList();
    }

    public void insert(Booking booking) {
        entityManager.persist(booking);
    }

}
