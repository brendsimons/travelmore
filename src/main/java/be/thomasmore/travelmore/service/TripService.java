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

    public List<Trip> search(Trip search){
        return tripRepository.search(search); }

}
