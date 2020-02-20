package testy;

import base.Egzemplarz;
import base.Klient;
import base.Pracownik;
import base.Rezerwacja;
import base.Sprzet;
import java.time.LocalDate;
import rodzajSprzetu.KijkiTrekkingowe;
import rodzajSprzetu.Lodowka;
import rodzajSprzetu.Namiot;
import rodzajSprzetu.Plecak;
import rodzajSprzetu.Spiwor;

public class Data {

    public Data() {
    }

    public String[][] daneSprzetu = {
        {"0", "kijki1", "1", "39.99", "8", "80.00", "Wlokno weglowe"},
        {"0", "kijki2", "1", "35.00", "8", "120", "stal"},
        {"1", "lodowka1", "2", "80.00", "3.5", "100", "48", "70", "70", "50"},
        {"1", "lodowka2", "2", "70.00", "7", "70", "27", "50", "50", "30"},
        {"2", "Namiot1", "3", "230.00", "8.28", "450", "kewlar"},
        {"2", "Namiot2", "3", "250.00", "5", "550", "poliester"},
        {"3", "plecak1", "4", "110.00", "1", "120", "poliester"},
        {"3", "plecak2", "4", "123.00", "0", "80", "włókno bambusowe"},
        {"3", "plecak3", "5", "98.00", "23", "60", "poliester"},
        {"4", "spiwor1", "5", "46.23", "23", "M", "1.8"},
        {"4", "spiwor2", "6", "44", "18", "L", "3"},
        {"4", "spiwor3", "5", "21", "15", "L", "2.5"},
        {"9", "spiwor3", "5", "21", "15", "L", "2.5"}
    };

    public Sprzet[] sprzety = {
        new KijkiTrekkingowe(80.00, "Wlokno weglowe", 39.99, 8, "kijki1", 1),
        new KijkiTrekkingowe(120.00, "stal", 35, 8, "kijki2", 1),
        new Lodowka(100, 48, 70, 70, 50, 80, 3.5, "lodowka1", 2),
        new Lodowka(70, 27, 50, 50, 30, 70, 7, "lodowka2", 2),
        new Namiot(450, "kewlar", 230, 8.28, "namiot1", 3),
        new Namiot(550, "poliester", 250, 5, "namiot2", 3),
        new Plecak(120, "poliester", 110, 1, "plecak1", 4),
        new Plecak(80, "włókno bambusowe", 123, 0, "plecak2", 4),
        new Plecak(60, "poliester", 98, 23, "plecak3", 5),
        new Spiwor("M", 1.8, 46.23, 23, "spiwor1", 5),
        new Spiwor("L", 3, 44, 18, "spiwor2", 6),
        new Spiwor("L", 2.5, 21, 15, "spiwor3", 5)
    };

    public String[][] danePracownikow = {
        {"Marian", "Kownacki", "1"},
        {"Jan", "Kowalski", "2"}
    };
    public Pracownik[] pracownicy = {
        new Pracownik("Marian", "Kownacki", 1),
        new Pracownik("Jan", "Kowalski", 2)
    };
    
    public String[] pracownicyFasada = {
        "Udało się dodac pracownika",
        
    };

    public String[][] daneKlientow = {
        {"Jan", "Kowalski", "97121208993", "Wroclaw", "Hallera 8/112", "AYW893983"},
        {"Adam", "Nowak", "56128874655", "Ciechocinek", "Nijaka 5/1", "DDL934442"}
    };
    public Klient[] klienci = {
        new Klient("Jan", "Kowalski", "97121208993", "Wroclaw", "Hallera 8/112", "AYW893983"),
        new Klient("Adam", "Nowak", "56128874655", "Ciechocinek", "Nijaka 5/1", "DDL934442")
    };
    public String[] klienciFasada = {
      "Dodano klienta"
    };

    public String[] daneEgzemplarzy = {
        "0", "1", "2", "3"};
    
    public Egzemplarz[] egzemplarze ={
        new Egzemplarz(0),
        new Egzemplarz(1),
        new Egzemplarz(2),
        new Egzemplarz(3)
    };

    public String[][] daneRezerwacji = {
        {"1", LocalDate.now().toString()},
        {"2", LocalDate.MAX.toString()},
        {"0", LocalDate.MIN.toString()},
        {"0", LocalDate.now().toString()},
        {"1", LocalDate.of(2022, 12, 25).toString()}
    };
    public Rezerwacja[] rezerwacje = {
        new Rezerwacja(1, LocalDate.now()),
        new Rezerwacja(2, LocalDate.of(2023, 07, 19)),
        new Rezerwacja(0, LocalDate.MIN),
        new Rezerwacja(0, LocalDate.now()),
        new Rezerwacja(1, LocalDate.of(2022, 12, 25))
    };
    public LocalDate[] daty = {
        LocalDate.now(),
        LocalDate.MAX,
        LocalDate.MIN,
        LocalDate.now(),
        LocalDate.of(2022, 12, 25)  
    };
    

}
