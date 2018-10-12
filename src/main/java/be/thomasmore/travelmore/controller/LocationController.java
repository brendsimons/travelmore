package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.domain.Location;
import be.thomasmore.travelmore.service.LocationService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@ViewScoped
public class LocationController {

    private Location newLocation = new Location();

    @Inject
    private LocationService locationService;

    public Location getNewLocation() {
        return newLocation;
    }

    public void setNewLocation(Location newLocation) {
        this.newLocation = newLocation;
    }

    public List<Location> getLocations(){
        return this.locationService.findAllLocations();
    }

    public void submit(){
        this.locationService.insert(newLocation);
    }

}
