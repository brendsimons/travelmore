package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.domain.Booking;
import be.thomasmore.travelmore.domain.Trip;
import be.thomasmore.travelmore.domain.User;
import be.thomasmore.travelmore.service.MailService;
import be.thomasmore.travelmore.service.TripService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ManagedBean
@SessionScoped
public class SearchController {

    private Trip searchTrip = new Trip();
    private LocalDateTime minDate;

    private List<Trip> searchedTrips;

    private Booking newBooking;

    @Inject
    private TripService tripService;
    @Inject
    private MailService emailService;

    public Trip getSearchTrip(){ return searchTrip; }

    public LocalDateTime getMinDate() {
        return minDate;
    }

    public void setMinDate(LocalDateTime minDate) {
        this.minDate = minDate;
    }

    public void updateMinDate() {
        minDate = LocalDateTime.ofInstant(searchTrip.getGoDate().toInstant(), ZoneId.systemDefault());
    }

    public Booking getNewBooking() {
        return newBooking;
    }

    public List<Trip> getSearchedTrips(){ return searchedTrips; }

    public String submit(){

        searchTrip.setBackDate(addDays(searchTrip.getBackDate(), 1));
        searchedTrips = tripService.searchMin(searchTrip);

        return "tripList";
    }

    private Date addDays(Date date, int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public String book(Trip trip){
        FacesContext context = FacesContext.getCurrentInstance();
        boolean loggedIn = context.getExternalContext().getSessionMap().containsKey("user");

        if(!loggedIn){
            return "login";
        }

        User user = (User) context.getExternalContext().getSessionMap().get("user");

        newBooking = new Booking();

        newBooking.setUser(user);
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
