package org.helmo.HolyD.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Ticket")
    @SequenceGenerator(name = "id_Ticket", sequenceName = "ID_TICKET", allocationSize = 1)
    private Long id;

    @Size(min = 2, max = 150, message = "Wrong topic size min=2 max=150")
    @Column(length = 150, nullable = false)
    private String sujet;
    @Size(min = 2, max = 700, message = "Wrong description size min=2 max=700")
    @Column(length = 700, nullable = false)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id.equals(ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", sujet='" + sujet + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
