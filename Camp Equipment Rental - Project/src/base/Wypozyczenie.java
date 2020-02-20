package base;

import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

public class Wypozyczenie {

    private Klient client;
    private LocalDate czasWypozyczenia;
    private String stanSprzetu;
    private double koszt;
    private Rezerwacja rezerwacja;
    
    private Locale locale = new Locale("pl");
    private ResourceBundle resource = ResourceBundle.getBundle("Bundle", locale);
    

    public Wypozyczenie(String stanSprzetu) {
        this.stanSprzetu = stanSprzetu;
    }

    public Wypozyczenie(Klient client, LocalDate czasWypozyczenia, String stanSprzetu, double koszt, Rezerwacja rezerwacja) {
        this.client = client;
        this.czasWypozyczenia = czasWypozyczenia;
        this.stanSprzetu = stanSprzetu;
        this.rezerwacja = rezerwacja;
        this.koszt = koszt;
    }

    public Klient getClient() {
        return client;
    }

    public void setClient(Klient client) {
        this.client = client;
    }

    public LocalDate getCzasWypozyczenia() {
        return czasWypozyczenia;
    }

    public void setCzasWypozyczenia(LocalDate czasWypozyczenia) {
        this.czasWypozyczenia = czasWypozyczenia;
    }

    public String getStanSprzetu() {
        return stanSprzetu;
    }

    public void setStanSprzetu(String stanSprzetu) {
        this.stanSprzetu = stanSprzetu;
    }

    public double getKoszt() {
        return koszt;
    }

    public Rezerwacja getRezerwacja() {
        return rezerwacja;
    }

    public void setKoszt(double koszt) {
        this.koszt = koszt;
    }

    public void setRezerwacja(Rezerwacja rezerwacja) {
        this.rezerwacja = rezerwacja;
    }
    
    public void usunRezerwacje(){
        this.rezerwacja = null;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(resource.getString("wypozyczenie.toString1")).append(client.toString())
                .append(resource.getString("wypozyczenie.toString2")).append(czasWypozyczenia)
                .append(resource.getString("wypozyczenie.toString3")).append(stanSprzetu);
        return str.toString();
    }

    public void setDane(Klient k, LocalDate czas, Rezerwacja rez) {
        this.client = k;
        this.czasWypozyczenia = czas;
        this.koszt = rez.obliczKoszt();
        this.rezerwacja = rez;
        rez.setWypozyczenie(this);
    }

    @Override
    public boolean equals(Object o) {
        Wypozyczenie w = (Wypozyczenie) o;
        return w.rezerwacja.equals(this.rezerwacja);
    }

}
