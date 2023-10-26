package org.helmo.HolyD.models;


import javax.persistence.*;

import java.util.Objects;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Role")
    @SequenceGenerator(name = "id_Role", sequenceName = "ID_ROLE", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String nom;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id.equals(role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
