package base;

import base.Egzemplarz;
import java.time.LocalDate;

public class Rezerwacja {

    private Egzemplarz egzemplarz;
    private int nrRezerwacji;
    private Klient klient;
    private LocalDate date;
    private Wypozyczenie wypozyczenie;

    public Rezerwacja() {
    }

    public Rezerwacja(int numer) {
        this.nrRezerwacji = numer;
    }

    public Rezerwacja(Egzemplarz egzemplarz, int nrRezerwacji, Klient klient, LocalDate date) {
        this.egzemplarz = egzemplarz;
        this.nrRezerwacji = nrRezerwacji;
        this.klient = klient;
        this.date = date;
    }
    public Rezerwacja(int nrRezerwacji, LocalDate date){
        this.nrRezerwacji = nrRezerwacji;
        this.date = date;
    }

    public Egzemplarz getEgzemplarz() {
        return egzemplarz;
    }

    public void setEgzemplarz(Egzemplarz egzemplarz) {
        this.egzemplarz = egzemplarz;
    }

    public int getNrRezerwacji() {
        return nrRezerwacji;
    }

    public void setNrRezerwacji(int nrRezerwacji) {
        this.nrRezerwacji = nrRezerwacji;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Wypozyczenie getWypozyczenie() {
        return wypozyczenie;
    }

    public void setWypozyczenie(Wypozyczenie wypozyczenie) {
        this.wypozyczenie = wypozyczenie;
    }

    public boolean isFree(LocalDate date) {
        if (!date.equals(this.date)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean zrealizowano() {
        if (wypozyczenie != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean nieaktualna(LocalDate date) {
        if (this.date.isBefore(date) && !zrealizowano()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean zgodnyTermin(LocalDate date) {
        return this.date.equals(date);
    }

    public double obliczKoszt() {
        return egzemplarz.obliczKoszt();
    }

    public boolean usunRezerwacje() {
        if (wypozyczenie != null) {
            return false;
        } else {
            klient.usunRezerwacje(this);
            egzemplarz.usunRezerwacje(this);
            return true;
        }
    }

    public String zwrocSprzet() {
        if (zrealizowano()) {
            egzemplarz.usunRezerwacje(this);
            wypozyczenie.usunRezerwacje();
            klient.usunRezerwacje(this);
            return "Sprzet zwrócony";
        } else {
            return "Rezerwacja nie została podjęta";
        }
    }

    public String toString() {
        return "Numer rezerwacji: " + nrRezerwacji
                + ", klient: " + klient.toString()
                + ", egzemplarz: " + egzemplarz.toString();
    }

    @Override
    public boolean equals(Object o) {
        Rezerwacja r = (Rezerwacja) o;
        return r.nrRezerwacji == this.nrRezerwacji;
    }

}
