package pl.wat.db.domain.event.location;

import com.querydsl.core.annotations.QueryInit;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "EVE_Cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVE_CITY_SEQ")
    @SequenceGenerator(sequenceName = "EVE_CITY_SEQ", initialValue = 1, allocationSize = 1, name = "EVE_CITY_SEQ")
    private int idCity;

    @Column(length = 50)
    @NotNull
    @Size(min = 3, max = 50)
    private String cityName;

    @NotNull
    @JoinColumn(name = "id_region")
    @ManyToOne
    private Region region;

    public City() {
    }

    public City(String cityName, Region region) {
        this.cityName = cityName;
        this.region = region;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public static class CityBuilder {
        private String cityName;
        private Region region;

        public CityBuilder cityName(String cityName) {
            this.cityName = cityName;
            return this;
        }

        public CityBuilder region(Region region) {
            this.region = region;
            return this;
        }

        public City createCity() {
            return new City(cityName, region);
        }
    }
}
