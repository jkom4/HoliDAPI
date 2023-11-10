package org.helmo.HolyD.repository.DTO;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "VACANCE")
public class VacanceDTO {

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

    @OneToOne
    @JoinColumn(nullable = false)
    private UserDTO owner;

    @ManyToMany(mappedBy = "vacances")
    private Collection<UserDTO> participants = new ArrayList<>();

    @OneToMany(mappedBy = "vacance", cascade={CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval=true)
    private Collection<ActiviteDTO> activites = new ArrayList<>();

    @ManyToOne(optional = false, cascade={CascadeType.PERSIST})
    private LieuDTO lieu;

    @OneToMany(mappedBy="vacance", cascade={CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval=true)
    private Collection<MessageDTO> messages = new ArrayList<>();

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

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

    public Collection<UserDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(Collection<UserDTO> participants) {
        this.participants = participants;
    }
    public boolean addParticipant(UserDTO userDTO){
        if(!this.participants.contains(userDTO)) {
            this.participants.add(userDTO);
            return true;
        } else {
            return false;
        }
    }
    public Collection<ActiviteDTO> getActivites() {
        return activites;
    }

    public void setActivites(Collection<ActiviteDTO> activites) {
        this.activites = activites;
    }

    public LieuDTO getLieu() {
        return lieu;
    }

    public void setLieu(LieuDTO lieu) {
        this.lieu = lieu;
    }

    public Collection<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(Collection<MessageDTO> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacanceDTO that = (VacanceDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "VacanceDTO{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", owner=" + owner +
                ", participants=" + participants +
                ", activites=" + activites +
                ", lieu=" + lieu +
                ", messages=" + messages +
                '}';
    }
}
