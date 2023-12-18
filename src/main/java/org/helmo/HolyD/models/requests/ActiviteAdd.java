package org.helmo.HolyD.models.requests;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

public class ActiviteAdd {

    @Size(min = 2, max = 50, message = "Wrong name size min=2 max=50")
    private String nom;
    @Size(min = 2, max = 250, message = "Wrong description size min=2 max=250")
    private String description;
    private OffsetDateTime dateDebut;
    private OffsetDateTime dateFin;
    private LieuAdd lieu;


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OffsetDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(OffsetDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public OffsetDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(OffsetDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public LieuAdd getLieu() {
        return lieu;
    }

    public void setLieu(LieuAdd lieu) {
        this.lieu = lieu;
    }

    @Override
    public String toString() {
        return "ActiviteAdd{" +
                "nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", lieu=" + lieu +
                '}';
    }
}
