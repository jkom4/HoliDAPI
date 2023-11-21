package org.helmo.HolyD.models.requests;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class LieuAdd {

    @Min(value = -180, message = "Wrong longitude size min=-180")
    @Max(value = 180, message = "Wrong longitude size max=180")
    private double longitude;
    @Min(value = -90, message = "Wrong latitude size max=90")
    @Max(value = 90, message = "Wrong latitude size max=90")
    private double latitude;
    @Size(max = 70, message = "Wrong street size max=70")
    private String rue;

    @Size(max = 15, message = "Wrong street number size max=15")
    private String rueNumero;

    @Size(max = 15, message = "Wrong postal code size max=15")
    private String codePostal;
    @Size(min = 2, max = 50, message = "Wrong city size min=2 max=50")
    private String ville;
    @Size(min = 2, max = 50, message = "Wrong country size min=2 max=50")
    private String pays;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getRueNumero() {
        return rueNumero;
    }

    public void setRueNumero(String rueNumero) {
        this.rueNumero = rueNumero;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
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
    public String toString() {
        return "LieuAdd{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", rue='" + rue + '\'' +
                ", rueNumero=" + rueNumero +
                ", codePostal=" + codePostal +
                ", ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                '}';
    }
}
