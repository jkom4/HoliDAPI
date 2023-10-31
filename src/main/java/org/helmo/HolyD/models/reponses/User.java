package org.helmo.HolyD.models.reponses;

import java.util.Collection;

public class User {

    private String role;
    private String nom;
    private String prenom;
    private String email;
    private String tokenConnectionAPI; // diff de token provider
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

    public String getTokenConnectionAPI() {
        return tokenConnectionAPI;
    }

    public void setTokenConnectionAPI(String tokenConnectionAPI) {
        this.tokenConnectionAPI = tokenConnectionAPI;
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
                "role='" + role + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", tokenConnectionAPI='" + tokenConnectionAPI + '\'' +
                ", vacances=" + vacances +
                '}';
    }
}
