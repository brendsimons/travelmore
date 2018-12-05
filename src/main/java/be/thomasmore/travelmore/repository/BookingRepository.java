package be.thomasmore.travelmore.repository;

import be.thomasmore.travelmore.domain.Booking;
import be.thomasmore.travelmore.domain.Location;
import be.thomasmore.travelmore.domain.User;

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
        return entityManager.createNamedQuery(Booking.FIND_ALL, Booking.class).getResultList();
    }

    public void insert(Booking booking) {
        entityManager.persist(booking);
    }

    public List<Booking> getBookingByUser(int user) {
        return entityManager.createNamedQuery(Booking.SEARCH_USER, Booking.class)
                .setParameter("user", (user))
                .getResultList();
    }

    public List<Booking> getBookingByTrip(int trip) {
        return entityManager.createNamedQuery(Booking.SEARCH_TRIP, Booking.class)
                .setParameter("trip", (trip))
                .getResultList();
    }

}
