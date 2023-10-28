package org.helmo.HolyD.repository.DTO;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import java.util.Collection;
import java.util.Objects;

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
    @Size(min = 2, max = 100, message = "Wrong password size min=2 max=100")
    @Column(length = 100, nullable = false)
    private String passwd;
    @Size(max = 250, message = "Wrong token connection size max=250")
    @Column(length = 250, nullable = true)
    private String tokenConnection;

    @ManyToOne(optional = true)
    private ProviderDTO tokenProvider;

    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private Collection<VacanceDTO> vacances;

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

    public String getTokenConnection() {
        return tokenConnection;
    }

    public void setTokenConnection(String tokenConnection) {
        this.tokenConnection = tokenConnection;
    }

    public ProviderDTO getTokenProvider() {
        return tokenProvider;
    }

    public void setTokenProvider(ProviderDTO tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    public Collection<VacanceDTO> getVacances() {
        return vacances;
    }

    public void setVacances(Collection<VacanceDTO> vacances) {
        this.vacances = vacances;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO user = (UserDTO) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", passwd='" + passwd + '\'' +
                ", tokenConnection='" + tokenConnection + '\'' +
                ", tokenProvider=" + tokenProvider +
                ", vacances=" + vacances +
                '}';
    }
}
