package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.domain.TransportType;
import be.thomasmore.travelmore.service.TransportTypeService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@ViewScoped
public class TransportTypeController {

    private TransportType newTransportType = new TransportType();

    @Inject
    private TransportTypeService transportTypeService;

    public TransportType getTransportType() {
        return newTransportType;
    }

    public void setTransportType(TransportType newTransportType) {
        this.newTransportType = newTransportType;
    }

    public List<TransportType> getTransportTypes(){ return this.transportTypeService.findAllTransportTypes(); }

    public void submit(){
        this.transportTypeService.insert(newTransportType);
    }

}
