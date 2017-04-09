package pl.wat.db.domain.event.location;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "EVE_Regions")
public class Region {

    @Id
    private int idRegion;

    @Column(length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String regionName;

    public Region() {
    }

    public Region(int idRegion, String regionName) {
        this.idRegion = idRegion;
        this.regionName = regionName;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
