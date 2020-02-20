package base;


import base.Sprzet;
import java.time.LocalDate;
import java.util.ArrayList;

public class Egzemplarz {
    
    private Sprzet sprzet;
    private int nrEgzemplarza;
    private ArrayList<Rezerwacja> listaRezerwacji;

    public Egzemplarz(int numer){
        this.nrEgzemplarza=numer;
        listaRezerwacji = new ArrayList<>();
    }
    
    public Egzemplarz(Sprzet sprzet, int nrEgzemplarza) {
        this.sprzet = sprzet;
        this.nrEgzemplarza = nrEgzemplarza;
        this.listaRezerwacji = new ArrayList<>();
    }

    public Sprzet getSprzet() {
        return sprzet;
    }

    public void setSprzet(Sprzet sprzet) {
        this.sprzet = sprzet;
    }

    public int getNrEgzemplarza() {
        return nrEgzemplarza;
    }

    public void setNrEgzemplarza(int nrEgzemplarza) {
        this.nrEgzemplarza = nrEgzemplarza;
    }

    public ArrayList<Rezerwacja> getListaRezerwacji() {
        return listaRezerwacji;
    }

    public void setListaRezerwacji(ArrayList<Rezerwacja> listaRezerwacji) {
        this.listaRezerwacji = listaRezerwacji;
    }
    
    public boolean isFree(LocalDate date){
        for(int i=0;i<listaRezerwacji.size();i++){
            
            if(!listaRezerwacji.get(i).isFree(date)){
                return false;
            }
        }
        return true;
    }
    
    public void dodajRezerwacje(Rezerwacja rez){
        rez.setEgzemplarz(this);
        listaRezerwacji.add(rez);
    }
    
    public boolean usunRezerwacje(Rezerwacja rez){
        return listaRezerwacji.remove(rez);
    }
    
    public Rezerwacja wyszukajRezerwacje(Rezerwacja rezerwacja){
        int index =0;
       if((index = listaRezerwacji.indexOf(rezerwacja)) != -1){
           return listaRezerwacji.get(index);
       }else{
           return null;
       }
    }
    
    public double obliczKoszt(){
        return sprzet.getCena() *(1+sprzet.getPodatek()/100);
    }
    
    @Override
    public String toString(){
        return "Sprzet: "+sprzet.getNazwa()+", nr egzemplarza: "+nrEgzemplarza+", liczba rezerwacji: "+listaRezerwacji.size();
    }
    
    @Override
    public boolean equals(Object o){
        Egzemplarz egz = (Egzemplarz)o;
        return egz.nrEgzemplarza == this.nrEgzemplarza;
    }
    
}
