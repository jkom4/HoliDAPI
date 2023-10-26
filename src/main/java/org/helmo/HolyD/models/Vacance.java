package org.helmo.HolyD.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Vacance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Vacance")
    @SequenceGenerator(name = "id_Vacance", sequenceName = "ID_VACANCE", allocationSize = 1)
    private Long id;

    @Size(min = 2, max = 50, message = "Wrong name size min=2 max=50")
    @Column(length = 50, nullable = false)
    private String nom;
    @Size(min = 2, max = 250, message = "Wrong description size min=2 max=250")
    @Column(length = 250, nullable = false)
    private String description;

    @Column(nullable = false)
    private OffsetDateTime dateDebut;
    @Column(nullable = false)
    private OffsetDateTime dateFin;

    @OneToMany(mappedBy="vacance", cascade={CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval=true)
    private Collection<Activite> activites;

    @ManyToOne(optional = false, cascade={CascadeType.PERSIST})
    private Lieu lieu;

    @OneToMany(mappedBy="vacance", cascade={CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval=true)
    private Collection<Message> messages;

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

    public Collection<Activite> getActivites() {
        return activites;
    }

    public void setActivites(Collection<Activite> activites) {
        this.activites = activites;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public Collection<Message> getMessages() {
        return messages;
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacance vacance = (Vacance) o;
        return id.equals(vacance.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Vacance{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", activites=" + activites +
                ", lieu=" + lieu +
                ", messages=" + messages +
                '}';
    }
}
