package be.thomasmore.travelmore.service;

import be.thomasmore.travelmore.domain.Trip;
import be.thomasmore.travelmore.repository.TripRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TripService {
    @Inject
    private TripRepository tripRepository;

    public Trip findTripById(int id) {
        return tripRepository.findById(id);
    }

    public List<Trip> findAllTrips() {
        return tripRepository.findAll();
    }

    public void insert(Trip trip) {
        tripRepository.insert(trip);
    }

    public List<Trip> searchMin(Trip search){
        return tripRepository.searchMin(search);
    }

    public List<Trip> searchMinPrice(Trip search){
        return tripRepository.searchMinPrice(search);
    }

    public List<Trip> searchMinTransportType(Trip search){
        return tripRepository.searchMinTransportType(search);
    }

    public List<Trip> searchAll(Trip search){
        return tripRepository.searchAll(search);
    }

}
