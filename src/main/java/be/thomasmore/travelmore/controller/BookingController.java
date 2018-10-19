package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.domain.Booking;
import be.thomasmore.travelmore.domain.Trip;
import be.thomasmore.travelmore.service.BookingService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@ViewScoped
public class BookingController {

    private Booking newBooking;

    @Inject
    private BookingService bookingService;

    public Booking getNewBooking() { return newBooking; }

    public String book(Trip trip, int people){
        newBooking = new Booking();

        newBooking.setUser(null);
        newBooking.setTrip(trip);
        newBooking.setAmountOfPeople(people);
        newBooking.setPaid(false);

        return "book";
    }
}
