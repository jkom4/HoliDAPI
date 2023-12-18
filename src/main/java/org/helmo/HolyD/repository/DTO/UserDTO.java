package org.helmo.HolyD.repository.DTO;

import org.helmo.HolyD.repository.DTO.enums.RoleType;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import java.time.OffsetDateTime;
import java.util.*;

@Entity
@Table(name = "UTILISATEUR")
public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_User")
    @SequenceGenerator(name = "id_User", sequenceName = "ID_USER", allocationSize = 1)
    private Long id;

    @ManyToOne(optional = false)
    private RoleDTO role;

    @Size(min = 2, max = 30, message = "Wrong lastname size min=2 max=30")
    @Column(length = 30, nullable = false)
    private String nom;
    @Size(min = 2, max = 30, message = "Wrong firstname size min=2 max=30")
    @Column(length = 30, nullable = false)
    private String prenom;
    @Email
    @Size(min = 2, max = 100, message = "Wrong email size min=2 max=100")
    @Column(length = 100, unique = true, nullable = false)
    private String email;
    @Size(max = 100, message = "Wrong password size max=100")
    @Column(length = 100, nullable = false)
    private String passwd;

    @OneToMany(mappedBy = "owner", cascade={CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval=true)
    private Set<VacanceDTO> ownedVacances = new HashSet<>();

    @OneToMany(mappedBy = "owner", cascade={CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval=true)
    private Set<ActiviteDTO> ownedActivites = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "participants")
    private Set<VacanceDTO> vacances = new HashSet<>();

    @OneToMany(mappedBy = "sender", cascade={CascadeType.REMOVE}, orphanRemoval=true)
    private Set<MessageDTO> sendedMessages = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(Long id, RoleDTO role, String nom, String prenom, String email, String passwd, Set<VacanceDTO> ownedVacances, Set<ActiviteDTO> ownedActivites, Set<VacanceDTO> vacances, Set<MessageDTO> sendedMessages) {
        this.id = id;
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.passwd = passwd;
        this.ownedVacances = ownedVacances;
        this.ownedActivites = ownedActivites;
        this.vacances = vacances;
        this.sendedMessages = sendedMessages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }
    public void setRoleWithRoleType(RoleType roleType) {
        this.role = new RoleDTO(roleType);
    }
    public String getRoleName() {
        return role.getNom();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Set<VacanceDTO> getOwnedVacances() {
        return ownedVacances;
    }

    public void setOwnedVacances(Set<VacanceDTO> ownedVacances) {
        this.ownedVacances = ownedVacances;
    }

    public boolean addOwnedVacanceAndParticipation(VacanceDTO vacanceDTO){
        if(!this.vacances.contains(vacanceDTO) && !this.ownedVacances.contains(vacanceDTO)) {
            this.vacances.add(vacanceDTO);
            this.ownedVacances.add(vacanceDTO);
            return true;
        } else {
            return false;
        }
    }

    public Set<ActiviteDTO> getOwnedActivites() {
        return ownedActivites;
    }

    public void setOwnedActivites(Set<ActiviteDTO> ownedActivites) {
        this.ownedActivites = ownedActivites;
    }

    public Set<VacanceDTO> getVacances() {
        return vacances;
    }

    public void setVacances(Set<VacanceDTO> vacances) {
        this.vacances = vacances;
    }
    public boolean addParticipantionVacance(VacanceDTO vacanceDTO){
        if(!this.vacances.contains(vacanceDTO)) {
            this.vacances.add(vacanceDTO);
            return true;
        } else {
            return false;
        }
    }

    public Set<MessageDTO> getSendedMessages() {
        return sendedMessages;
    }

    public void setSendedMessages(Set<MessageDTO> sendedMessages) {
        this.sendedMessages = sendedMessages;
    }

    public boolean userIsAlreadyInHoliday(OffsetDateTime dateDebut, OffsetDateTime dateFin){
        return vacances.stream()
                .anyMatch((holiD) ->
                        ((holiD.getDateDebut().isBefore(dateFin) || holiD.getDateDebut().isEqual(dateFin)) &&
                                (holiD.getDateFin().isAfter(dateDebut) || holiD.getDateFin().isEqual(dateDebut))));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", role=" + role +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", passwd='" + passwd + '\'' +
                ", ownedVacances=" + ownedVacances +
                ", ownedActivites=" + ownedActivites +
                ", vacances=" + vacances +
                ", sendedMessages=" + sendedMessages +
                '}';
    }
}
