package org.helmo.HolyD.models.requests;

import java.time.OffsetDateTime;

public class NbrUserAndNbrUserInHolidayRequest {

    private OffsetDateTime dateDebut;
    private OffsetDateTime dateFin;

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

    @Override
    public String toString() {
        return "NbrUserAndNbrUserInHolidayRequest{" +
                "dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                '}';
    }
}
