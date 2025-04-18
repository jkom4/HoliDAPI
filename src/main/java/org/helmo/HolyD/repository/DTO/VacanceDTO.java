package org.helmo.HolyD.repository.DTO;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
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

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserDTO owner;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserDTO> participants = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "vacance", cascade={CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval=true)
    private Set<ActiviteDTO> activites = new HashSet<>();

    @ManyToOne(optional = false, cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private LieuDTO lieu;

    @OneToMany(mappedBy="vacance", cascade={CascadeType.ALL}, orphanRemoval=true) // Bug si non All (veut inserer null dans content alors que content n'est pas vide)
    private Set<MessageDTO> messages = new HashSet<>();

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

    public Set<UserDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<UserDTO> participants) {
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
    public Set<ActiviteDTO> getActivites() {
        return activites;
    }

    public void setActivites(Set<ActiviteDTO> activites) {
        this.activites = activites;
    }

    public LieuDTO getLieu() {
        return lieu;
    }

    public void setLieu(LieuDTO lieu) {
        this.lieu = lieu;
    }

    public Set<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(Set<MessageDTO> messages) {
        this.messages = messages;
    }
    public void addMessage(MessageDTO messageDTO, UserDTO userDTO){
        messageDTO.setSendingDate(OffsetDateTime.now());
        messageDTO.setSender(userDTO);
        messageDTO.setVacance(this);
        this.messages.add(messageDTO);
    }
    public boolean intervalIsInside(OffsetDateTime dateDebut, OffsetDateTime dateFin){
        return ((dateDebut.isAfter(this.dateDebut) || dateDebut.isEqual(this.dateDebut)) &&
                (dateFin.isBefore(this.dateFin) || dateFin.isEqual(this.dateFin)));
    }
    public boolean userHasAlreadyAtciviteForDateTimeInterval(UserDTO userDTO, OffsetDateTime dateDebut, OffsetDateTime dateFin){
        for (ActiviteDTO acti : this.activites) {
            if(acti.intervalIsCrossed(dateDebut, dateFin) && acti.userIsInside(userDTO))
                return true;
        }
        return false;
    }
    public boolean userHasAlreadyAtciviteForDateTimeIntervalWithOutOneActi(UserDTO userDTO, OffsetDateTime dateDebut, OffsetDateTime dateFin, Long idActivite){
        for (ActiviteDTO acti : this.activites) {
            if(Objects.equals(acti.getId(), idActivite) && acti.intervalIsCrossed(dateDebut, dateFin) && acti.userIsInside(userDTO))
                return true;
        }
        return false;
    }
    public ActiviteDTO editDateOfActivite(Long id, OffsetDateTime dateDebut, OffsetDateTime dateFin) {
        for (ActiviteDTO acti : this.activites) {
            if(Objects.equals(acti.getId(), id)){
                acti.setDateDebut(dateDebut);
                acti.setDateFin(dateFin);
                return acti;
            }

        }
        return null;
    }
    public boolean isOwnerOfActivite(Long idActivite, Long userId) {
        for (ActiviteDTO acti : this.activites) {
            if (acti.getId().equals(idActivite) && acti.getOwner().getId().equals(userId)) {
                return true;
            }
        }
        return false;
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
