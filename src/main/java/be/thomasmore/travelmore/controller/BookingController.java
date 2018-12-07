package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.domain.Booking;
import be.thomasmore.travelmore.domain.Trip;
import be.thomasmore.travelmore.domain.User;
import be.thomasmore.travelmore.service.BookingService;
import be.thomasmore.travelmore.service.MailService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.text.DecimalFormat;
import java.util.List;

@ManagedBean
@SessionScoped
public class BookingController {

    private Booking newBooking;

    @Inject
    private BookingService bookingService;
    @Inject
    private MailService emailService;

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

    public List<Booking> getBookingByUser(int userId) {
        return this.bookingService.getBookingByUser(userId);
    }

    public List<Booking> getBookingByTrip(int tripId) {
        return this.bookingService.getBookingByTrip(tripId);
    }

    private static DecimalFormat df2 = new DecimalFormat(".##");

    public String totalPrice(){
        double totalPric = this.newBooking.getTrip().getPrice()*this.newBooking.getAmountOfPeople();
        String totalPrice = df2.format(totalPric);
        return totalPrice;
    }

    public String pay(Booking booking, String paymentMethod){
        this.newBooking = booking;
        this.newBooking.setPaymentMethod(paymentMethod);
        this.totalPrice();

        if (this.newBooking.getPaymentMethod().equals("Bancontact")){
            return "betalenBancontact";
        }else if (this.newBooking.getPaymentMethod().equals("Creditcard")){
            return "betalenCreditcard";
        }else{
            return "betalenPaypal";
        }

    }


    public String paySuccess(){
        this.newBooking.setPaid(true);
        this.bookingService.insert(newBooking);

        emailService.send(this.newBooking.getUser().getEmail(), "Bedankt voor uw booking", "" +
                "Bedankt voor uw booking bij travelmore!\n" +
                "\n" +
                "Overzicht van de booking:\n" +
                "Van: " + newBooking.getTrip().getDepartureLocation().getName() + "\n" +
                "Naar: " + newBooking.getTrip().getArrivalLocation().getName() + "\n" +
                "Aantal personen: " + newBooking.getAmountOfPeople() + "\n" +
                "Vervoer: " + newBooking.getTrip().getTransportType().getName()
        );

        return "bedankt";
    }
}
