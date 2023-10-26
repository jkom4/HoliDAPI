package org.helmo.HolyD.models;

import javax.persistence.*;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Activite {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Activite")
    @SequenceGenerator(name = "id_Activite", sequenceName = "ID_ACTIVITE", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private OffsetDateTime dateDebut;
    @Column(nullable = false)
    private OffsetDateTime dateFin;

    @ManyToMany
    private Collection<User> participants;

    @ManyToOne(optional = false)
    private Lieu lieu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activite activite = (Activite) o;
        return id.equals(activite.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Activite{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", participants=" + participants +
                ", lieu=" + lieu +
                '}';
    }
}