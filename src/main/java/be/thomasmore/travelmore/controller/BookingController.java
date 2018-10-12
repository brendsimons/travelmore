package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.domain.Booking;
import be.thomasmore.travelmore.service.BookingService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@ViewScoped
public class BookingController {

    private Booking newBooking = new Booking();

    @Inject
    private BookingService bookingService;

    public Booking getNewBooking() {
        return newBooking;
    }

    public void setNewBooking(Booking newLocation) {
        this.newBooking = newLocation;
    }

    public List<Booking> getBookings(){
        return this.bookingService.findAllBookings();
    }

    public void submit(){
        this.bookingService.insert(newBooking);
    }

}
