package base;
import java.util.ArrayList;

public class Pracownik {
    
    private String imie;
    private String nazwisko;
    private int poziomUprawnien;
    private ArrayList<Klient> listaKlientow;

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
        return "Pracownik: Imie: "+imie+", nazwisko: "+nazwisko+", poziom uprawnien: "
                +poziomUprawnien+", liczba klientow: "+listaKlientow.size();
    }
    
    @Override
    public boolean equals(Object o){
        Pracownik pr = (Pracownik) o;
        return pr.imie.equalsIgnoreCase(this.imie) && pr.nazwisko.equalsIgnoreCase(this.nazwisko);
    }
    
    
}
