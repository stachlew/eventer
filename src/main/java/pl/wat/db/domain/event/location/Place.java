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

    @Column(length = 20)
    @NotNull
    @Size(min = 1, max = 20)
    private String streetNo;

    @Column(length = 20)
    @Size(max = 20)
    private String geoLength;

    @Column(length = 10)
    @Size(max = 10)
    private String geoWidth;

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

    public Place(String streetName, String streetNo, String geoLength, String geoWidth, City city) {
        this.streetName = streetName;
        this.streetNo = streetNo;
        this.geoLength = geoLength;
        this.geoWidth = geoWidth;
        this.city = city;
    }

    public String getGeoLength() {
        return geoLength;
    }

    public void setGeoLength(String geoLength) {
        this.geoLength = geoLength;
    }

    public String getGeoWidth() {
        return geoWidth;
    }

    public void setGeoWidth(String geoWidth) {
        this.geoWidth = geoWidth;
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
