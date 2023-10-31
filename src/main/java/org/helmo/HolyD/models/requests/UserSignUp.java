package org.helmo.HolyD.models.requests;

import org.helmo.HolyD.repository.DTO.enums.RoleType;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class UserSignUp {

    @Size(min = 2, max = 30, message = "Wrong lastname size min=2 max=30")
    private String nom;
    @Size(min = 2, max = 30, message = "Wrong firstname size min=2 max=30")
    private String prenom;
    @Email
    @Size(min = 2, max = 100, message = "Wrong email size min=2 max=100")
    private String email;
    @Size(min = 2, max = 100, message = "Wrong password size min=2 max=100")
    private String passwd;

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

    public RoleType getDefaultRoleType(){
        return RoleType.USER;
    }

    @Override
    public String toString() {
        return "UserSignUp{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
