package base;

import java.time.LocalDate;
import java.util.ArrayList;

public class Klient {

    private String imie;
    private String nazwisko;
    private String pesel;
    private String miejscowosc;
    private String adres;
    private String numerDowodu;
    private ArrayList<Rezerwacja> listaRezerwacji;
    private static int numerRezerwacji;
    private Pracownik pracownik;
    private ArrayList<Wypozyczenie> listaWypozyczen;

    public Klient(String imie, String nazwisko, String pesel, String miejscowosc, String adres, String numerDowodu) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.miejscowosc = miejscowosc;
        this.adres = adres;
        this.numerDowodu = numerDowodu;
        this.listaRezerwacji = new ArrayList<>();
        this.listaWypozyczen = new ArrayList<>();
    }
    
    public Klient(String pesel, String imie){
        this.pesel = pesel;
        this.imie = imie;
    }

    public Klient() {
       
    }

   

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getNumerDowodu() {
        return numerDowodu;
    }

    public void setNumerDowodu(String numerDowodu) {
        this.numerDowodu = numerDowodu;
    }

    public ArrayList<Rezerwacja> getListaRezerwacji() {
        return listaRezerwacji;
    }

    public void setListaRezerwacji(ArrayList<Rezerwacja> listaRezerwacji) {
        this.listaRezerwacji = listaRezerwacji;
    }

    public String dodajRezerwacje(Egzemplarz egz, LocalDate date) {
        Factory factory = new Factory();
        Rezerwacja rez = factory.utworzRezerwacje(numerRezerwacji++, date);
        rez.setKlient(this);
        rez.setEgzemplarz(egz);
        egz.dodajRezerwacje(rez);
        listaRezerwacji.add(rez);
        return rez.toString();
    }

    public Rezerwacja wyszukajRezerwacje(Rezerwacja rezerwacja) {
        int index = 0;
        if ((index = listaRezerwacji.indexOf(rezerwacja)) != -1) {
            return listaRezerwacji.get(index);
        } else {
            return null;
        }
    }

    public int getNumerRezerwacji() {
        return numerRezerwacji;
    }

    public ArrayList<Wypozyczenie> getListaWypozyczen() {
        return listaWypozyczen;
    }

    public void setListaWypozyczen(ArrayList<Wypozyczenie> listaWypozyczen) {
        this.listaWypozyczen = listaWypozyczen;
    }

    public void usunPracownika() {
        pracownik.usunKlienta(this);
    }

    public String wypozycz(int nrRez, LocalDate date, String stanSprzetu) {
        Factory factory = new Factory();
        Rezerwacja rez = factory.utworzRezerwacje(nrRez, date);
        rez = wyszukajRezerwacje(rez);
        if (rez != null) {
            if (rez.zgodnyTermin(date)) {
                if (!rez.zrealizowano()) {
                    Wypozyczenie w = factory.utworzWypozyczenie(stanSprzetu);
                    w.setDane(this, date, rez);
                    rez.setWypozyczenie(w);
                    listaWypozyczen.add(w);
                    return "Oznaczono jako wypozyczony";
                }else{
                    return "Rezerwacja w trakcie realizacji, sprzęt niedostępny";
                }
            }else{
                return "Podany termin i termin rezerwacji nie zgadzają się";
            }
        } else {
            return "Nie ma takiej rezerwacji";
        }
    }
    
    public String zwrocSprzet(int nrRez){
        Factory factory = new Factory();
        Rezerwacja rez = factory.utworzRezerwacje(nrRez);
        rez = wyszukajRezerwacje(rez);
        if (rez != null) {
            return rez.zwrocSprzet();
        }else{
            return "Nie było takiej rezerwacji";
        }
    }

    public double kosztWyp() {
        double suma = 0;
        for (Wypozyczenie wyp : listaWypozyczen) {
            suma += wyp.getKoszt();
        }
        suma *= 100;
        suma = Math.round(suma);
        suma /= 100;
        return suma;
    }

    public ArrayList<Integer> usunNieaktualneRezerwacje(LocalDate date) {
        ArrayList<Integer> lista = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < listaRezerwacji.size(); i++) {
            if (listaRezerwacji.get(i).nieaktualna(date)) {
                lista.add(listaRezerwacji.get(i).getNrRezerwacji());
                listaRezerwacji.remove(i);
                i--;
            }
        }
        return lista;
    }

    public boolean usunRezerwacje(Rezerwacja rez) {
        return listaRezerwacji.remove(rez);
    }

    public ArrayList<String> usunWszystkieRezerwacje() {
        ArrayList<String> zostaly = new ArrayList();
        int indx = -1;
        for (Rezerwacja rez : listaRezerwacji) {
            indx++;
            if (rez.usunRezerwacje()) {
                listaRezerwacji.remove(indx);
                zostaly.add(rez.toString());
                indx--;
            }
        }
        return zostaly;
    }
//     return "Rezerwacja pomyslnie usunieta";
//        }else{
//            return "Nie udalo sie usunac rezerwacji";

    @Override
    public String toString() {
        return "Klient: Imie: " + imie + ", nazwisko: " + nazwisko
                + ", pesel: " + pesel + ", miejscowosc: " + miejscowosc
                + ", adres: " + adres + ", numer dowodu: " + numerDowodu
                + ", liczba rezerwacji: " + listaRezerwacji.size()
                + ", liczba wypozyczen: " + listaWypozyczen.size()
                + ", sumaryczny koszt: " + kosztWyp() + "zł";
    }

    @Override
    public boolean equals(Object o) {
        Klient k = (Klient) o;
        return k.pesel.equals(this.pesel);
    }

}
