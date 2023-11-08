package org.helmo.HolyD.repository.DTO;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "LIEU")
public class LieuDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Lieu")
    @SequenceGenerator(name = "id_Lieu", sequenceName = "ID_LIEU", allocationSize = 1)
    private Long id;

    @Min(value = -180, message = "Wrong longitude size min=-180")
    @Max(value = 180, message = "Wrong longitude size max=180")
    @Column(scale = 6, precision = 9, nullable = false)
    private double longitude;
    @Min(value = -90, message = "Wrong latitude size max=90")
    @Max(value = 90, message = "Wrong latitude size max=90")
    @Column(scale = 6, precision = 8, nullable = false)
    private double latitude;
    @Size(min = 2, max = 70, message = "Wrong street size min=2 max=70")
    @Column(length = 70, nullable = false)
    private String rue;
    @Min(value = 0, message = "Wrong street number size min=0")
    @Max(value = 999999999, message = "Wrong street number size max=999999999")
    @Column(precision = 9, nullable = false)
    private int rueNumero;
    @Min(value = 0, message = "Wrong postal code size min=0")
    @Max(value = 999999999, message = "Wrong postal code size max=999999999")
    @Column(precision = 9, nullable = false)
    private int codePostal;
    @Size(min = 2, max = 50, message = "Wrong city size min=2 max=50")
    @Column(length = 50, nullable = false)
    private String ville;
    @Size(min = 2, max = 50, message = "Wrong country size min=2 max=50")
    @Column(length = 50, nullable = false)
    private String pays;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getRueNumero() {
        return rueNumero;
    }

    public void setRueNumero(int rueNumero) {
        this.rueNumero = rueNumero;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LieuDTO lieu = (LieuDTO) o;
        return id.equals(lieu.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Lieu{" +
                "id=" + id +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", rue='" + rue + '\'' +
                ", rueNumero=" + rueNumero +
                ", codePostal=" + codePostal +
                ", ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                '}';
    }
}
