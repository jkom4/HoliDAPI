package org.helmo.HolyD.repository.DTO;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ACTIVITE")
public class ActiviteDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Activite")
    @SequenceGenerator(name = "id_Activite", sequenceName = "ID_ACTIVITE", allocationSize = 1)
    private Long id;

    @Size(min = 2, max = 50, message = "Wrong name  size min=2 max=50")
    @Column(length = 50, nullable = false)
    private String nom;
    @Size(min = 2, max = 50, message = "Wrong description size min=2 max=50")
    @Column(length = 50, nullable = false)
    private String description;
    @Column(nullable = false)
    private OffsetDateTime dateDebut;
    @Column(nullable = false)
    private OffsetDateTime dateFin;

    @OneToOne
    private UserDTO owner;

    @ManyToMany
    private Collection<UserDTO> participants;

    @ManyToOne(optional = false, cascade={CascadeType.PERSIST})
    private LieuDTO lieu;

    @ManyToOne(optional = false)
    private VacanceDTO vacance;

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

    public LieuDTO getLieu() {
        return lieu;
    }

    public void setLieu(LieuDTO lieu) {
        this.lieu = lieu;
    }

    public Collection<UserDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(Collection<UserDTO> participants) {
        this.participants = participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActiviteDTO activite = (ActiviteDTO) o;
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