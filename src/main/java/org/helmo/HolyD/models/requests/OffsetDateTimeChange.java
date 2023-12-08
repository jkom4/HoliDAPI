package org.helmo.HolyD.models.requests;

import java.time.OffsetDateTime;

public class OffsetDateTimeChange {

    private Long id;
    private OffsetDateTime dateDebut;
    private OffsetDateTime dateFin;

    public OffsetDateTimeChange(Long id, OffsetDateTime dateDebut, OffsetDateTime dateFin) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(OffsetDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public OffsetDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(OffsetDateTime dateFin) {
        this.dateFin = dateFin;
    }
}
