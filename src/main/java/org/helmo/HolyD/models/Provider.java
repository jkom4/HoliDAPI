package org.helmo.HolyD.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Provider")
    @SequenceGenerator(name = "id_Provider", sequenceName = "ID_PROVIDER", allocationSize = 1)
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
        Provider provider = (Provider) o;
        return id.equals(provider.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
