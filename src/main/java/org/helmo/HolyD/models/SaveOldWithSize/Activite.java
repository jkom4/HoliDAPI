package org.helmo.HolyD.models.SaveOldWithSize;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.Collection;

public class Activite {

    @Size(min = 2, max = 50, message = "Wrong name  size min=2 max=50")
    private String nom;
    @Size(min = 2, max = 50, message = "Wrong description size min=2 max=50")
    private String description;

    @NotNull
    private OffsetDateTime dateDebut;
    @NotNull
    private OffsetDateTime dateFin;

    private Collection<User> participants;

    @NotNull
    private Lieu lieu;


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

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public Collection<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Collection<User> participants) {
        this.participants = participants;
    }


    @Override
    public String toString() {
        return "Activite{" +
                "nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", participants=" + participants +
                ", lieu=" + lieu +
                '}';
    }
}