package pl.wat.db.domain.event.location;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import pl.wat.db.domain.event.location.City;

@Entity
@Table(name = "EVE_Places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVE_PLACE_SEQ")
    @SequenceGenerator(sequenceName = "EVE_PLACE_SEQ", initialValue = 1, allocationSize = 1, name = "EVE_PLACE_SEQ")
    private int idPlace;

    @Column(length = 50)
    @NotNull
    @Size(min = 3, max = 50)
    private String streetName;

    @Column(length = 10)
    @NotNull
    @Size(min = 1, max = 10)
    private String streetNo;

    @NotNull
    @JoinColumn(name = "id_city")
    @ManyToOne
    private City city;

    public Place() {
    }

    public Place(String streetName, String streetNo, City city) {
        this.streetName = streetName;
        this.streetNo = streetNo;
        this.city = city;
    }

    public int getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(int idPlace) {
        this.idPlace = idPlace;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
