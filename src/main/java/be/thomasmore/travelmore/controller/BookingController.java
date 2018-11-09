package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.domain.Booking;
import be.thomasmore.travelmore.domain.Trip;
import be.thomasmore.travelmore.domain.User;
import be.thomasmore.travelmore.service.BookingService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@SessionScoped
public class BookingController {

    private Booking newBooking;

    @Inject
    private BookingService bookingService;

    public Booking getNewBooking() {
        return newBooking;
    }
    public void setNewBooking(Booking newBooking){ this.newBooking = newBooking; }

    public String book(Trip trip, Trip searchTrip){
        return "book";
    }

    public List<Booking> getBookings(){
        return this.bookingService.findAllBookings();
    }

    public List<Booking> getBookingByUser(Integer userId) {
        return this.bookingService.getBookingByUser(userId);
    }

    public String pay(Booking booking, String paymentMethod){
        return "";
    }

    public String paySuccess(){
        this.bookingService.insert(newBooking);
        return "bedankt";
    }
}
