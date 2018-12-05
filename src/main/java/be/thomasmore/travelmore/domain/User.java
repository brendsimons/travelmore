package be.thomasmore.travelmore.domain;


import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "user")
@NamedQueries(
        {
                @NamedQuery(
                        name = User.FIND_ALL,
                        query = "SELECT u FROM User u"
                ),
                @NamedQuery(
                        name = User.COMPARE_LOGIN,
                        query = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password"
                ),
                @NamedQuery(
                        name = User.COMPARE_REGISTER,
                        query = "SELECT count(u) FROM User u WHERE u.email = :email"
                )
        }
)
public class User {
    public static final String FIND_ALL = "User.findAll";
    public static final String COMPARE_LOGIN = "User.compareLogin";
    public static final String COMPARE_REGISTER = "User.compareRegister";

    @Id
    private int id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "email")
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+", message = "Email format is invalid.")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "village")
    private String village;
    @Column(name = "street")
    private String street;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword(){ return password; }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVillage(){ return village; }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getStreet(){ return street; }

    public void setStreet(String street) {
        this.street = street;
    }
}
