package base;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;


public class Sprzet {
    
    private double cena;
    private double podatek;
    private String nazwa;
    private ArrayList<Egzemplarz> listaEgzemplarzy;
    private int idSprzetu;
    
    private Locale locale = new Locale("pl");
    private ResourceBundle resource = ResourceBundle.getBundle("Bundle", locale);
    

    public Sprzet() {
        listaEgzemplarzy = new ArrayList<>();
    }
    
    public Sprzet(int idSprzetu){
        this.idSprzetu = idSprzetu;
        listaEgzemplarzy = new ArrayList<>();
    }
    
    

    public Sprzet(double cena, double podatek, String nazwa, int idSprzetu) {
        this.cena = cena;
        this.podatek = podatek;
        this.nazwa = nazwa;
        this.idSprzetu = idSprzetu;
        listaEgzemplarzy = new ArrayList<>();
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getPodatek() {
        return podatek;
    }

    public void setPodatek(double podatek) {
        this.podatek = podatek;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public ArrayList<Egzemplarz> getListaEgzemplarzy() {
        return listaEgzemplarzy;
    }

    public void setListaEgzemplarzy(ArrayList<Egzemplarz> listaEgzemplarzy) {
        this.listaEgzemplarzy = listaEgzemplarzy;
    }

    public int getIdSprzetu() {
        return idSprzetu;
    }

    public void setIdSprzetu(int idSprzetu) {
        this.idSprzetu = idSprzetu;
    }
    
    
    public boolean dodajDoListyEgzemplarzy(String[] dane1){
        Factory factory = new Factory();
        Egzemplarz egz = factory.utworzEgzemplarz(dane1);
        if(wyszukajEgzemplarza(egz) == null){
            egz.setSprzet(this);
            listaEgzemplarzy.add(egz);
            return true;
        }else{
            return false;
        }
    }
    
    public Egzemplarz wyszukajEgzemplarza(Egzemplarz egz){
       int index =0;
       if((index = listaEgzemplarzy.indexOf(egz)) != -1){
           return listaEgzemplarzy.get(index);
       }else{
           return null;
       }
    }
    
    public Egzemplarz wyszukajWolnegoEgzemplarza(LocalDate date){
        for(int i=0;i<listaEgzemplarzy.size();i++){
            if(listaEgzemplarzy.get(i).isFree(date)){
                return listaEgzemplarzy.get(i);
            }
        }
        return null;
    }
    
    
    @Override
    public String toString(){
        return resource.getString("sprzet.toString1")+nazwa
                +resource.getString("sprzet.toString2")+cena
                +resource.getString("sprzet.toString3")+podatek
                +resource.getString("sprzet.toString4")+idSprzetu
                +resource.getString("sprzet.toString5")+listaEgzemplarzy.size();
    }
    
    @Override
    public boolean equals(Object o){
        Sprzet sp = (Sprzet)o;
        return sp.idSprzetu == this.idSprzetu;
    }
    
    
    
}
