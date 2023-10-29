package org.helmo.HolyD.models.SaveOldWithSize;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

public class User {

    @NotNull
    private String role;

    @Size(min = 2, max = 30, message = "Wrong lastname size min=2 max=30")
    private String nom;
    @Size(min = 2, max = 30, message = "Wrong firstname size min=2 max=30")
    private String prenom;
    @Email
    @Size(min = 2, max = 100, message = "Wrong email size min=2 max=100")
    private String email;
    @Size(min = 2, max = 100, message = "Wrong password size min=2 max=100")
    private String passwd;
    @Size(max = 150, message = "Wrong token connection size max=150")
    private String tokenConnection;


    private String tokenProvider;

    private Collection<Vacance> vacances;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
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

    public String getTokenProvider() {
        return tokenProvider;
    }

    public void setTokenProvider(String tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    public Collection<Vacance> getVacances() {
        return vacances;
    }

    public void setVacances(Collection<Vacance> vacances) {
        this.vacances = vacances;
    }

    @Override
    public String toString() {
        return "User{" +
                "role=" + role +
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
