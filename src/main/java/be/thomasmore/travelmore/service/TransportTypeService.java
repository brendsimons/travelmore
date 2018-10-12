package be.thomasmore.travelmore.service;

import be.thomasmore.travelmore.domain.TransportType;
import be.thomasmore.travelmore.repository.TransportTypeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TransportTypeService {
    @Inject
    private TransportTypeRepository transportTypeRepository;

    public TransportType findTransportTypeById(int id) {
        return transportTypeRepository.findById(id);
    }

    public List<TransportType> findAllTransportTypes() {
        return transportTypeRepository.findAll();
    }

    public void insert(TransportType transportType) {
        transportTypeRepository.insert(transportType);
    }

}
