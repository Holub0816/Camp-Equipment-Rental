package testdata;

public class Data {

    public String[] dataSprzet = {"dodano sprzet: Sprzet: Kijki treningowe firmy Decathlon,"
        + " cena: 39.99, podatek: 8.0%, id sprzetu: 1234, Liczba egzemplarzy: 0,"
        + " dlugosc: 80.0, material: Wlokno weglowe",
        "dodano sprzet: Sprzet: Namiot Campus, cena: 100.0, podatek: 8.0%, id sprzetu: 333,"
        + " Liczba egzemplarzy: 0, pojemnosc: 350, material: kaszmir"
    };

    public String[] dataPracownik = {"Pracownik: Imie: Marian, nazwisko: Kownacki,"
        + " poziom uprawnien: 1, liczba klientow: 0"
    };

    public String[] dataKlient = {"Dodano klienta: Klient: Imie: Jan, nazwisko: Kowalski,"
        + " pesel: 1234, miejscowosc: Wroclaw, adres: Hallera 8, numer dowodu: 1234,"
        + " liczba rezerwacji: 0, liczba wypozyczen: 0, sumaryczny koszt: 0.0zł",
        "Dodano klienta: Klient: Imie: Anna, nazwisko: Nowak,"
        + " pesel: 12345, miejscowosc: Wroclaw, adres: Hallera 8, numer dowodu: 12345,"
        + " liczba rezerwacji: 0, liczba wypozyczen: 0, sumaryczny koszt: 0.0zł"
    };

    public String[] dataEgzemplarz = {"dodano egzemplarz",
        "dodano egzemplarz",
        "dodano egzemplarz",
        "egzemplarz juz istnieje",
        "nie ma takiego sprzetu"
    };

    public String[] dataRezerwacja = {"Numer rezerwacji: 0, klient: Klient: Imie: Jan, nazwisko: Kowalski,"
        + " pesel: 1234, miejscowosc: Wroclaw, adres: Hallera 8, numer dowodu: 1234,"
        + " liczba rezerwacji: 1, liczba wypozyczen: 0, sumaryczny koszt: 0.0zł,"
        + " egzemplarz: Sprzet: Kijki treningowe firmy Decathlon, nr egzemplarza: 0, liczba rezerwacji: 1",
        
        "Brak wolnego egzemplarza, spróbuj kiedy indziej",
        
        "Numer rezerwacji: 1, klient: Klient: Imie: Jan, nazwisko: Kowalski, pesel: 1234,"
        + " miejscowosc: Wroclaw, adres: Hallera 8, numer dowodu: 1234,"
        + " liczba rezerwacji: 2, liczba wypozyczen: 0, sumaryczny koszt: 0.0zł,"
        + " egzemplarz: Sprzet: Namiot Campus, nr egzemplarza: 1, liczba rezerwacji: 1",
        
        "Numer rezerwacji: 2, klient: Klient: Imie: Jan, nazwisko: Kowalski, pesel: 1234,"
        + " miejscowosc: Wroclaw, adres: Hallera 8, numer dowodu: 1234,"
        + " liczba rezerwacji: 3, liczba wypozyczen: 0, sumaryczny koszt: 0.0zł,"
        + " egzemplarz: Sprzet: Namiot Campus, nr egzemplarza: 2, liczba rezerwacji: 1",
        
        "Numer rezerwacji: 3, klient: Klient: Imie: Jan, nazwisko: Kowalski, pesel: 1234,"
        + " miejscowosc: Wroclaw, adres: Hallera 8, numer dowodu: 1234,"
        + " liczba rezerwacji: 4, liczba wypozyczen: 0, sumaryczny koszt: 0.0zł,"
        + " egzemplarz: Sprzet: Namiot Campus, nr egzemplarza: 1, liczba rezerwacji: 2"
    };
    
}
