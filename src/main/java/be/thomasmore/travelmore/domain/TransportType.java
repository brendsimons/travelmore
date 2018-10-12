package be.thomasmore.travelmore.domain;


import javax.persistence.*;

@Entity
@Table(name = "transportType")
@NamedQueries(
        {
                @NamedQuery(
                        name = TransportType.FIND_ALL,
                        query = "SELECT tt FROM TransportType tt"
                )
        }
)
public class TransportType {
    public static final String FIND_ALL = "TransportType.findAll";

    @Id
    private int id;
    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
