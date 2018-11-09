package be.thomasmore.travelmore.domain;

import javax.persistence.*;

@Entity
@Table(name = "booking")
@NamedQueries(
        {
                @NamedQuery(
                        name = Booking.FIND_ALL,
                        query = "SELECT b FROM Booking b"
                ),
                @NamedQuery(
                        name = Booking.SEARCH,
                        query = "SELECT b from Booking b where b.user.id = :user"
                )
        }
)
public class Booking {
    public static final String FIND_ALL = "Booking.findAll";
    public static final String SEARCH = "Booking.search";

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
    @Column(name = "paymentMethod")
    private String paymentMethod;

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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
