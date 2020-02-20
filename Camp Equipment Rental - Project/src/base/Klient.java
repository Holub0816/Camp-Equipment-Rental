package base;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

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
    
    private Locale locale = new Locale("pl");
    private ResourceBundle resource = ResourceBundle.getBundle("Bundle", locale);
    

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

    public Rezerwacja dodajRezerwacje(Egzemplarz egz, LocalDate date) {
        Factory factory = new Factory();
        Rezerwacja rez = factory.utworzRezerwacje(numerRezerwacji++, date);
        rez.setKlient(this);
        rez.setEgzemplarz(egz);
        egz.dodajRezerwacje(rez);
        listaRezerwacji.add(rez);
        return rez;
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
            return rez.wypozycz(this, date, stanSprzetu);
        } else {
            return resource.getString("klient.wypozycz");
        }
    }
    
    void dodajWypozyczenie(Wypozyczenie w){
        this.listaWypozyczen.add(w);
    }
    
    public String zwrocSprzet(int nrRez){
        Factory factory = new Factory();
        Rezerwacja rez = factory.utworzRezerwacje(nrRez);
        rez = wyszukajRezerwacje(rez);
        if (rez != null) {
            return rez.zwrocSprzet();
        }else{
            return resource.getString("klient.zwrocSprzet");
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
        return resource.getString("klient.toString1") + imie + resource.getString("klient.toString2") + nazwisko
                + resource.getString("klient.toString3") + pesel + resource.getString("klient.toString4") + miejscowosc
                + resource.getString("klient.toString5") + adres + resource.getString("klient.toString6") + numerDowodu
                + resource.getString("klient.toString7") + listaRezerwacji.size()
                + resource.getString("klient.toString8") + listaWypozyczen.size()
                + resource.getString("klient.toString9") + kosztWyp() + resource.getString("klient.toString10");
    }

    @Override
    public boolean equals(Object o) {
        Klient k = (Klient) o;
        return k.pesel.equals(this.pesel);
    }

}
