package be.thomasmore.travelmore.domain;

import javax.persistence.*;

@Entity
@Table(name = "booking")
@NamedQueries(
        {
                @NamedQuery(
                        name = Booking.FIND_ALL,
                        query = "SELECT b FROM Booking b"
                )
        }
)
public class Booking {
    public static final String FIND_ALL = "Booking.findAll";

    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name="trip")
    private Trip trip;
    @ManyToOne
    @JoinColumn(name="user")
    private User user;
    @Column(name = "amountOfPeople")
    private int amountOfPeople;
    @Column(name = "paid")
    private boolean paid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Trip getTrip(){ return trip; }

    public void setTrip(Trip trip){ this.trip = trip; }

    public User getUser(){ return user; }

    public void setUser(User user){ this.user = user; }

    public int getAmountOfPeople() {
        return amountOfPeople;
    }

    public void setAmountOfPeople(int amountOfPeople) {
        this.amountOfPeople = amountOfPeople;
    }

    public boolean isPaid(){ return paid; }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
