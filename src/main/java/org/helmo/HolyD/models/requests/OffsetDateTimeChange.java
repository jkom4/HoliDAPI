package org.helmo.HolyD.models.requests;

import java.time.OffsetDateTime;

public class OffsetDateTimeChange {

    private OffsetDateTime dateDebut;
    private OffsetDateTime dateFin;

    public OffsetDateTimeChange(OffsetDateTime dateDebut, OffsetDateTime dateFin) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
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
