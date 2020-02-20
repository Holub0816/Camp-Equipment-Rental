package base;

import base.Egzemplarz;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

public class Rezerwacja {

    private Egzemplarz egzemplarz;
    private int nrRezerwacji;
    private Klient klient;
    private LocalDate date;
    private Wypozyczenie wypozyczenie;
    private Locale locale = new Locale("pl");
    private ResourceBundle resource = ResourceBundle.getBundle("Bundle", locale);

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

    public Rezerwacja(int nrRezerwacji, LocalDate date) {
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

    public String wypozycz(Klient klient, LocalDate date, String stanSprzetu) {
        Wypozyczenie w = null;
        if (zgodnyTermin(date)) {
            if (!zrealizowano()) {
                Factory factory = new Factory();
                w = factory.utworzWypozyczenie(stanSprzetu);
                w.setDane(klient, date, this);
                this.wypozyczenie = w;
                klient.dodajWypozyczenie(w);
                return resource.getString("rezerwacja.wypozycz1");
            }else{
                return resource.getString("rezerwacja.wypozycz2");
            }
        }else{
            return resource.getString("rezerwacja.wypozycz3");
        }
    }

    public String zwrocSprzet() {
        if (zrealizowano()) {
            egzemplarz.usunRezerwacje(this);
            wypozyczenie.usunRezerwacje();
            klient.usunRezerwacje(this);
            return resource.getString("rezerwacja.zwrocSprzet1");
        } else {
            return resource.getString("rezerwacja.zwrocSprzet2");
        }
    }

    public String toString() {
        return resource.getString("rezerwacja.toString1") + nrRezerwacji
                + resource.getString("rezerwacja.toString2") + klient.toString()
                + resource.getString("rezerwacja.toString3") + egzemplarz.toString();
    }

    @Override
    public boolean equals(Object o) {
        Rezerwacja r = (Rezerwacja) o;
        return r.nrRezerwacji == this.nrRezerwacji;
    }

}
