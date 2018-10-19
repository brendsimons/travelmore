package be.thomasmore.travelmore.service;

import be.thomasmore.travelmore.domain.Booking;
import be.thomasmore.travelmore.domain.User;
import be.thomasmore.travelmore.repository.BookingRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class BookingService {
    @Inject
    private BookingRepository bookingRepository;

    public Booking findBookingById(int id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }

    public void insert(Booking booking) {
        bookingRepository.insert(booking);
    }

    public List<Booking> getBookingByUser(User user) {
        return bookingRepository.getBookingByUser(user);
    }
}
