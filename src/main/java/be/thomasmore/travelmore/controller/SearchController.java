package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.domain.Booking;
import be.thomasmore.travelmore.domain.Trip;
import be.thomasmore.travelmore.service.MailService;
import be.thomasmore.travelmore.service.TripService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ManagedBean
@SessionScoped
public class SearchController {

    private Trip searchTrip = new Trip();
    private List<Trip> searchedTrips;

    private Booking newBooking;

    @Inject
    private TripService tripService;
    @Inject
    private MailService emailService;

    public Trip getSearchTrip(){ return searchTrip; }
    public Booking getNewBooking() {
        return newBooking;
    }

    public List<Trip> getSearchedTrips(){ return searchedTrips; }
    public List<Trip> getAllTrips(){ return tripService.findAllTrips(); }

    public void submit(){
        searchTrip.setBackDate(addDays(searchTrip.getBackDate(), 1));
        searchedTrips = tripService.search(searchTrip);
    }

    private Date addDays(Date date, int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public String book(Trip trip){
        newBooking = new Booking();

        newBooking.setUser(null);
        newBooking.setTrip(trip);
        newBooking.setAmountOfPeople(searchTrip.getPlaces());
        newBooking.setPaid(false);

        emailService.send("brendsimons@gmail.com", "Hier is uw booking", "" +
                "Ziet er een leuke booking uit!\n" +
                "\n" +
                "Van: " + newBooking.getTrip().getDepartureLocation().getName() + "\n" +
                "Naar: " + newBooking.getTrip().getArrivalLocation().getName() + "\n" +
                "Aantal personen: " + newBooking.getAmountOfPeople() + "\n" +
                "Vervoer: " + newBooking.getTrip().getTransportType().getName() + "\n" +
                "\n" +
                "Bye"
        );

        return "book";
    }
}
