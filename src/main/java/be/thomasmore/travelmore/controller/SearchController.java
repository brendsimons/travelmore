package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.domain.Location;
import be.thomasmore.travelmore.domain.Trip;
import be.thomasmore.travelmore.service.TripService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class SearchController {

    private Trip searchTrip = new Trip();
    private List<Trip> searchedTrips;

    @Inject
    private TripService tripService;

    public Trip getSearchTrip(){ return searchTrip; }

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
}
