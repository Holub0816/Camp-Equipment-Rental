package base;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class Pracownik {
    
    private String imie;
    private String nazwisko;
    private int poziomUprawnien;
    private ArrayList<Klient> listaKlientow;
    
    private Locale locale = new Locale("pl");
    private ResourceBundle resource = ResourceBundle.getBundle("Bundle", locale);
    

    public Pracownik(String imie, String nazwisko, int poziomUprawnien) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.poziomUprawnien = poziomUprawnien;
        this.listaKlientow = new ArrayList<>();
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setPoziomUprawnien(int poziomUprawnien) {
        this.poziomUprawnien = poziomUprawnien;
    }

    public void setListaKlientow(ArrayList<Klient> listaKlientow) {
        this.listaKlientow = listaKlientow;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public int getPoziomUprawnien() {
        return poziomUprawnien;
    }

    public ArrayList<Klient> getListaKlientow() {
        return listaKlientow;
    }
    
    public void dodajKlienta(Klient klient){ // wywolany w: fasada metoda usun klienta
        klient.setPracownik(this);
        listaKlientow.add(klient);
    }
    
    public void usunKlienta(Klient klient){ // wywolany w: klient metoda usun pracownika
        listaKlientow.remove(klient);
    }
    
    @Override
    public String toString(){
        return resource.getString("pracownik.toString1")+imie
                +resource.getString("pracownik.toString2")+nazwisko
                +resource.getString("pracownik.toString3")+poziomUprawnien
                +resource.getString("pracownik.toString4")+listaKlientow.size();
    }
    
    @Override
    public boolean equals(Object o){
        Pracownik pr = (Pracownik) o;
        return pr.imie.equalsIgnoreCase(this.imie) && pr.nazwisko.equalsIgnoreCase(this.nazwisko);
    }
    
    
}
