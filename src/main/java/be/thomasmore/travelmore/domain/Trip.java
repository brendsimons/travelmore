package be.thomasmore.travelmore.domain;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "trip")
@NamedQueries(
        {
                @NamedQuery(
                        name = Trip.FIND_ALL,
                        query = "SELECT t FROM Trip t"
                ),
                @NamedQuery(
                        name = Trip.SEARCH,
                        query = "SELECT t FROM Trip t WHERE t.departureLocation.id = :departureLocation AND t.arrivalLocation.id = :arrivalLocation AND t.goDate >= :goDate AND t.backDate <= :backDate AND t.places >= :places"
                )
        }
)
public class Trip {
    public static final String FIND_ALL = "Trip.findAll";
    public static final String SEARCH = "Trip.search";

    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name="departureLocation")
    private Location departureLocation;
    @ManyToOne
    @JoinColumn(name="arrivalLocation")
    private Location arrivalLocation;
    @Column(name = "goDate")
    @NotNull(message = "Kies een datum.")
    private Date goDate;
    @Column(name = "backDate")
    private Date backDate;
    @Column(name = "places")
    private int places;
    @Column(name = "price")
    private double price;
    @ManyToOne
    @JoinColumn(name="transportType")
    private TransportType transportType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Location getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(Location departureLocation) {
        this.departureLocation = departureLocation;
    }

    public Location getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(Location arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public Date getGoDate() {return goDate; }

    public void setGoDate(Date goDate) {
        this.goDate = goDate;
    }

    public Date getBackDate() {return backDate; }

    public void setBackDate(Date backDate) {
        this.backDate = backDate;
    }

    public int getPlaces() { return places; }

    public void setPlaces(int places) {
        this.places = places;
    }

    public double getPrice() { return price; }

    public void setPrice(double price) {
        this.price = price;
    }

    public TransportType getTransportType() { return transportType; }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

}
