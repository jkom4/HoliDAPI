package org.helmo.HolyD.models.reponses;

public class Lieu {

    private Long id;
    private double longitude;
    private double latitude;
    private String rue;
    private int rueNumero;
    private int codePostal;
    private String ville;
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
