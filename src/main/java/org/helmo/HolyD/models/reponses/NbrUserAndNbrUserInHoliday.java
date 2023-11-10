package org.helmo.HolyD.models.reponses;

public class NbrUserAndNbrUserInHoliday {

    private int nbrUserTotal;
    private int nbrUserInHoliday;

    public NbrUserAndNbrUserInHoliday(int nbrUserTotal, int nbrUserInHoliday) {
        this.nbrUserTotal = nbrUserTotal;
        this.nbrUserInHoliday = nbrUserInHoliday;
    }

    public int getNbrUserTotal() {
        return nbrUserTotal;
    }

    public void setNbrUserTotal(int nbrUserTotal) {
        this.nbrUserTotal = nbrUserTotal;
    }

    public int getNbrUserInHoliday() {
        return nbrUserInHoliday;
    }

    public void setNbrUserInHoliday(int nbrUserInHoliday) {
        this.nbrUserInHoliday = nbrUserInHoliday;
    }

    @Override
    public String toString() {
        return "NbrUserAndNbrUserInHoliday{" +
                "nbrUserTotal=" + nbrUserTotal +
                ", nbrUserInHoliday=" + nbrUserInHoliday +
                '}';
    }
}
