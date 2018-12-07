package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.domain.Booking;
import be.thomasmore.travelmore.domain.Trip;
import be.thomasmore.travelmore.domain.User;
import be.thomasmore.travelmore.service.BookingService;
import be.thomasmore.travelmore.service.MailService;
import be.thomasmore.travelmore.service.TripService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
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
    public String errorMessage = null;

    @Inject
    private TripService tripService;
    @Inject
    private BookingService bookingService;

    public Trip getSearchTrip(){ return searchTrip; }

    public String getErrorMessage() {return errorMessage;}

    public void setErrorMessage(String errorMessage) {this.errorMessage = errorMessage;}

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

        if(searchTrip.getPlaces() < 0) {
            this.errorMessage = "Vul bij vrije plaatsen een waarde in groter dan 0";
            return "index";
        } else {
            searchTrip.setBackDate(addDays(searchTrip.getBackDate(), 1));
            searchedTrips = tripService.searchMin(searchTrip);

            List<Trip> loop = new ArrayList<>(searchedTrips);

            for(Trip t : loop){
                if(getPlacesEmpty(t) < searchTrip.getPlaces()){
                    searchedTrips.remove(t);
                }
            }

            this.errorMessage = null;

            return "tripList";
        }
    }

    public String submitdetailed(){

        if(searchTrip.getPlaces() <= 0 || searchTrip.getPrice() <=0) {
            this.errorMessage = "";
            if(searchTrip.getPlaces() <= 0) {
                this.errorMessage += "vul bij vrije plaatsen een waarde in groter dan 0";
            }
            if(searchTrip.getPlaces() <= 0 && searchTrip.getPrice() <=0) {
                this.errorMessage += " en ";
            }
            if(searchTrip.getPrice() <= 0) {
                this.errorMessage += "vul bij maximum prijs een waarde in groter dan 0";
            }
            return "uitgebreidZoeken";
        } else {
            searchTrip.setBackDate(addDays(searchTrip.getBackDate(), 1));
            searchedTrips = tripService.searchAll(searchTrip);

            List<Trip> loop = new ArrayList<>(searchedTrips);

            for(Trip t : loop){
                if(getPlacesEmpty(t) < searchTrip.getPlaces()){
                    searchedTrips.remove(t);
                }
            }

            this.errorMessage = null;

            return "tripList";
        }


    }

    public int getPlacesEmpty(Trip trip){
        List<Booking> bookings = bookingService.getBookingByTrip(trip.getId());
        int places = trip.getPlaces();

        for(Booking b : bookings){
            places -= b.getAmountOfPeople();
        }

        return places;
    }

    public String searchMore() {
        this.errorMessage = null;
        return "uitgebreidZoeken";
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

        return "book";
    }
}
