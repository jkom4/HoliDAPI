package org.helmo.HolyD.models;

import javax.persistence.*;

import java.util.Objects;

@Entity
public class Lieu {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Lieu")
    @SequenceGenerator(name = "id_Lieu", sequenceName = "ID_LIEU", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private float longitude;
    @Column(nullable = false)
    private float latitude;
    @Column(nullable = false)
    private String rue;
    @Column(nullable = false)
    private int rueNumero;
    @Column(nullable = false)
    private int codePostal;
    @Column(nullable = false)
    private String ville;
    @Column(nullable = false)
    private String pays;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
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
        Lieu lieu = (Lieu) o;
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
