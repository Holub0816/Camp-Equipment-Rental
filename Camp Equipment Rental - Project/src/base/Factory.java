
package base;

import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;
import rodzajSprzetu.*;

public class Factory {
    
    private Locale locale = new Locale("pl");
    private ResourceBundle resource = ResourceBundle.getBundle("Bundle", locale);
    
    public Factory(){
        
    }
    
    public Sprzet utworzSprzet(String[] data) throws IllegalArgumentException{
        Sprzet sprzet = null;
        switch(Integer.parseInt(data[0])){
            case 0:{
                sprzet = new KijkiTrekkingowe(Double.parseDouble(data[5]), data[6],
                        Double.parseDouble(data[3]), Double.parseDouble(data[4]),
                        data[1], Integer.parseInt(data[2]));
            }break;
            case 1:{
                sprzet = new Lodowka(Integer.parseInt(data[5]), Double.parseDouble(data[6]), Double.parseDouble(data[7]),
                        Double.parseDouble(data[8]), Double.parseDouble(data[9]),
                        Double.parseDouble(data[3]), Double.parseDouble(data[4]),
                        data[1], Integer.parseInt(data[2]));
            }break;
            case 2:{
                sprzet = new Namiot(Integer.parseInt(data[5]), data[6],Double.parseDouble(data[3]), Double.parseDouble(data[4]),
                        data[1], Integer.parseInt(data[2]));
            }break;
            case 3:{
                sprzet = new Plecak(Integer.parseInt(data[5]), data[6],Double.parseDouble(data[3]), Double.parseDouble(data[4]),
                        data[1], Integer.parseInt(data[2]));
            }break;
            case 4:{
                sprzet = new Spiwor(data[5], Double.parseDouble(data[6]), Double.parseDouble(data[3]),
                        Double.parseDouble(data[4]), data[1], Integer.parseInt(data[2]));
            }break;
            default: throw new IllegalArgumentException(resource.getString("factory.wyjatek")); ///// dodano wyjatek
        }
        return sprzet;
    }
    
   
    
    public Pracownik utworzPracownika(String[] data){
        return new Pracownik(data[0], data[1], Integer.parseInt(data[2]));
    }
    
    public Egzemplarz utworzEgzemplarz(String[] data){
       return new Egzemplarz(Integer.parseInt(data[0])); 
    }
    
    public Rezerwacja utworzRezerwacje(int numer, LocalDate date){
        Rezerwacja rezerwacja = new Rezerwacja();
        rezerwacja.setDate(date);
        rezerwacja.setNrRezerwacji(numer);
        return rezerwacja;
    }
    public Rezerwacja utworzRezerwacje(String[] data){
        return new Rezerwacja(Integer.parseInt(data[0]), LocalDate.parse(data[1]));
    }
    
    public Rezerwacja utworzRezerwacje(int numer){
        return new Rezerwacja(numer);
    }

    public Klient utworzKlienta(String[] daneKlienta) {
        return new Klient(daneKlienta[0], daneKlienta[1], daneKlienta[2], daneKlienta[3], daneKlienta[4], daneKlienta[5]);
    }
    
    public Wypozyczenie utworzWypozyczenie(String stan){
        return new Wypozyczenie(stan);
    }

   
    
}
