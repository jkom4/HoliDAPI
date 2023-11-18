package org.helmo.HolyD.models.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class UserSignInWithProvider {

    @Email
    @Size(min = 2, max = 100, message = "Wrong email size min=2 max=100")
    private String email;
    @Size(min = 2, max = 30, message = "Wrong lastname size min=2 max=30")
    private String nom;
    @Size(min = 2, max = 30, message = "Wrong firstname size min=2 max=30")
    private String prenom;

    public UserSignInWithProvider(String email, String nom, String prenom) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
