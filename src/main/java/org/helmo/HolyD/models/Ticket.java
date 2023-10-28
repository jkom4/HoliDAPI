package org.helmo.HolyD.models;

import javax.validation.constraints.Size;

public class Ticket {

    @Size(min = 2, max = 150, message = "Wrong topic size min=2 max=150")
    private String sujet;
    @Size(min = 2, max = 700, message = "Wrong description size min=2 max=700")
    private String description;

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
    public String toString() {
        return "Ticket{" +
                "sujet='" + sujet + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
