package base;

import base.Sprzet;
import java.time.LocalDate;
import java.util.ArrayList;

public class Facade {

    private ArrayList<Sprzet> sprzety;
    private ArrayList<Klient> klienci;
    private ArrayList<Pracownik> pracownicy;

    public Facade() {
        sprzety = new ArrayList<>();
        klienci = new ArrayList<>();
        pracownicy = new ArrayList<>();
    }

    public ArrayList<Sprzet> getSprzety() {
        return sprzety;
    }

    public void setSprzety(ArrayList<Sprzet> sprzety) {
        this.sprzety = sprzety;
    }

    public ArrayList<Klient> getKlienci() {
        return klienci;
    }

    public void setKlienci(ArrayList<Klient> klienci) {
        this.klienci = klienci;
    }

    public ArrayList<Pracownik> getPracownicy() {
        return pracownicy;
    }

    public void setPracownicy(ArrayList<Pracownik> pracownicy) {
        this.pracownicy = pracownicy;
    }

    public String dodajKlienta(String[] daneKlienta, String[] pracownik) {
        String inf = "";
        Factory factory = new Factory();
        Pracownik prac = factory.utworzPracownika(pracownik);
        prac = wyszukajPracownika(prac);
        if (prac != null) {
            Klient klient = factory.utworzKlienta(daneKlienta);
            if (wyszukajKlienta(klient) == null) {
                klienci.add(klient);
                prac.dodajKlienta(klient);
                return "Dodano klienta: "+klient.toString();
            } else {
                return "Klient juz istnieje";
            }
        } else {
            return "Pracownik nie istnieje";
        }
    }

    public String usunPracownika(String[] danePracownika) {
        String info;
        Factory factory = new Factory();
        Pracownik pracownik = factory.utworzPracownika(danePracownika);
        int index = pracownicy.indexOf(pracownik);
        if (index != -1) {
            Pracownik pr = pracownicy.get(index);
            pracownicy.remove(index);
            przepiszKlientow(pr);
            return info = "Usunieto wskazanego pracownika z listy pracowników.";
        } else {
            return info = "Pracownik nie został usunięty.";
        }
    }

    private String przepiszKlientow(Pracownik pr) {
        ArrayList<Klient> lista = pr.getListaKlientow();
        int j = 0;
        for (int i = 0; i < lista.size(); i++) {
            j = i;
            if (i >= pracownicy.size()) {
                j = 0;
            }
            pracownicy.get(j).dodajKlienta(lista.get(i));
        }
        return "Przepisano klientow";
    }

    public String usunKlienta(String[] daneKlienta) { ///////////////////
        String info;
        Factory factory = new Factory();
        Klient klient = factory.utworzKlienta(daneKlienta);
        klient = wyszukajKlienta(klient);
        if (klient != null) {
            klient.usunWszystkieRezerwacje();
            klient.usunPracownika();
            klienci.remove(klient);
            //if (klienci.remove(klient)) {    
            klient.usunPracownika();
            return info = "Usunieto wskazanego klienta z listy klientow.";
        } else {
            return info = "klient nie został usunięty.";
        }
    }

    public String usunSprzet(String[] daneSprzetu) {
        String info;
        Factory factory = new Factory();
        Sprzet sprzet = factory.utworzSprzet(daneSprzetu);
        if (sprzety.remove(sprzet)) {
            return info = "Usunieto wskazany sprzęt z listy pracowników.";
        } else {
            return info = "Sprzęt nie został usunięty.";
        }
    }

    public String dodajPracownika(String[] danePracownika) {
        Factory factory = new Factory();
        Pracownik pracownik = factory.utworzPracownika(danePracownika);
        Pracownik prac;
        prac = wyszukajPracownika(pracownik);
        if (prac == null) {
            pracownicy.add(pracownik);
            return pracownik.toString();
        } else {
            
            return "pracownik juz istnieje";
        }
    }

    public Klient wyszukajKlienta(Klient klient) {
        int index = 0;
        if ((index = klienci.indexOf(klient)) != -1) {
            return klienci.get(index);
        } else {
            return null;
        }
    }

    public void rejestracjaKlienta(Klient klient, Pracownik pracownik) {
        klient = wyszukajKlienta(klient);
        if (klient != null) {
            klienci.add(klient);
            pracownik.getListaKlientow().add(klient);
        }
    }

    public Sprzet wyszukajSprzet(Sprzet sprzet) {
        int index = 0;
        if ((index = sprzety.indexOf(sprzet)) != -1) {
            return sprzety.get(index);
        } else {
            return null;
        }
    }

    public Pracownik wyszukajPracownika(Pracownik pracownik) {
        int index = 0;
        if ((index = pracownicy.indexOf(pracownik)) != -1) {
            return pracownicy.get(index);
        } else {
            return null;
        }
    }

    public String dodajSprzet(String[] daneSprzetu) {
        String inf = "";
        Factory factory = new Factory();
        Sprzet sprzet = factory.utworzSprzet(daneSprzetu);
        Sprzet sp;
        sp = wyszukajSprzet(sprzet);
        if (sp == null) {
            sprzety.add(sprzet);
            return "dodano sprzet: "+sprzet.toString();
        } else {
            return "sprzet juz istnieje";
        }
    }

    public String dodajEgzemplarz(String[] dane1, String[] dane2) {
        String info;
        Factory factory = new Factory();
        Sprzet sp = factory.utworzSprzet(dane1);
        sp = wyszukajSprzet(sp);
        if (sp != null) {
            if (sp.dodajDoListyEgzemplarzy(dane2)) {
                info = "dodano egzemplarz";
            } else {
                info = "egzemplarz juz istnieje";
            }
        } else {
            info = "nie ma takiego sprzetu";
        }
        return info;
    }

    public String dodajRezerwacje(String[] dane1, String[] dane2, LocalDate date) {
        String info;
        Factory factory = new Factory();
        Sprzet sprzet = factory.utworzSprzet(dane1);
        sprzet = wyszukajSprzet(sprzet);
        if (sprzet != null) {
            Egzemplarz egz;
            if ((egz = sprzet.wyszukajWolnegoEgzemplarza(date)) != null) {
                Klient klient = factory.utworzKlienta(dane2);
                klient = wyszukajKlienta(klient);
                if (klient != null) {
                    info = klient.dodajRezerwacje(egz, date);
                } else {
                    info = "Klient nie istnieje";
                }

            } else {
                info = "Brak wolnego egzemplarza, spróbuj kiedy indziej";
            }
        } else {
            info = "Brak danego sprzetu";
        }
        return info;
    }

    public String usunRezerwacje(int nr) {
        Factory factory = new Factory();
        Rezerwacja rez = factory.utworzRezerwacje(nr);
        rez = wyszukajRezerwacje(rez);
        if (rez != null) {
            if (rez.usunRezerwacje()) {
                return "rezerwacja usunięta";
            } else {
                return "rezerwacja podjęta, egzemplarz w trakcie wynajmu";
            }
        } else {
            return "nie ma takiej rezerwacji";
        }
    }

    public Rezerwacja wyszukajRezerwacje(Rezerwacja rez) {
        int index = 0;
        Rezerwacja rezerwacja;
        do {
            rezerwacja = klienci.get(index).wyszukajRezerwacje(rez);
            index++;
        } while (rezerwacja == null && index < klienci.size());
        return rezerwacja;
    }

    public String oznaczJakoWypozyczony(String[] klient, int nrRez, LocalDate date, String stanSprzetu) {
        Factory factory = new Factory();
        Klient k = factory.utworzKlienta(klient);
        k = wyszukajKlienta(k);
        if (k != null) {
            return k.wypozycz(nrRez, date, stanSprzetu);
        } else {
            return "Nie ma takiego klienta";
        }
    }

    public String usunNieaktualneRezerwacje(LocalDate date) {
        StringBuilder str = new StringBuilder();
        for (Klient k : klienci) {
            str.append(k.usunNieaktualneRezerwacje(date).toString());
        }
        return "Usuniete rezerwacje: " + str.toString();
    }
    
    public String zwrocWypozyczonySprzet(String[] klient, int nrRez){
        Factory factory = new Factory();
        Klient k = factory.utworzKlienta(klient);
        k = wyszukajKlienta(k);
        if(k != null){
            return k.zwrocSprzet(nrRez);
        }else{
            return "Nie ma takiego klienta";
        }
    }

    public ArrayList<String> stworzModelPracownikow() {
        ArrayList<String> list = new ArrayList<>();
        for (Pracownik prac : pracownicy) {
            list.add(prac.toString());
        }
        return list;
    }

    public ArrayList<String> stworzModelKlientow() {
        ArrayList<String> list = new ArrayList<>();
        for (Klient k : klienci) {
            list.add(k.toString());
        }
        return list;
    }

    public ArrayList<String> stworzModelSprzetow() {
        ArrayList<String> list = new ArrayList<>();
        for (Sprzet s : sprzety) {
            list.add(s.toString());
        }
        return list;
    }

    public static void main(String[] args) {
        Facade facade = new Facade();
        String[] sprzet1 = {"0", "Kijki treningowe firmy Decathlon", "1234", "39.99", "8", "80.00", "Wlokno weglowe"};
        String[] sprzet2 = {"2", "Namiot Campus", "333", "100.00", "8", "350", "kaszmir"};

        System.out.println("\ndodawanie sprzętu: \n");
        System.out.println(facade.dodajSprzet(sprzet1));
        System.out.println(facade.dodajSprzet(sprzet2));
        System.out.println(facade.dodajSprzet(sprzet2));
        
        System.out.println("\ndodawanie pracownika i klienta: \n");
        String[] pracownik = {"Marian", "Kownacki", "1"};
        System.out.println(facade.dodajPracownika(pracownik));
        String[] klient = {"Jan", "Kowalski", "1234", "Wroclaw", "Hallera 8", "AYW8938983"};
        System.out.println(facade.dodajKlienta(klient, pracownik));
        System.out.println(facade.dodajKlienta(klient, pracownik));

        System.out.println("\ndodawanie egzemplarza: \n");
        System.out.println(facade.dodajEgzemplarz(sprzet1, new String[]{"0"}));
        System.out.println(facade.dodajEgzemplarz(sprzet2, new String[]{"0"}));
       System.out.println( facade.dodajEgzemplarz(sprzet2, new String[]{"1"}));
        System.out.println(facade.dodajEgzemplarz(sprzet2, new String[]{"1"}));

        System.out.println("\ndodawanie rezerwacji: \n");
        System.out.println(facade.dodajRezerwacje(sprzet1, klient, LocalDate.now()));
        System.out.println(facade.dodajRezerwacje(sprzet1, klient, LocalDate.now()));
        System.out.println(facade.dodajRezerwacje(sprzet2, klient, LocalDate.now()));
        System.out.println(facade.dodajRezerwacje(sprzet2, klient, LocalDate.now()));
        System.out.println(facade.dodajRezerwacje(sprzet2, klient, LocalDate.now()));

        System.out.println("\nwypożyczanie: \n");
        System.out.println(facade.oznaczJakoWypozyczony(klient, 0, LocalDate.now(), "stan dobry+")); // jak sie dobrac inaczej do numeru rezerwacji?
        System.out.println(facade.oznaczJakoWypozyczony(klient, 1, LocalDate.now(), "stan dobry+"));
        System.out.println(facade.oznaczJakoWypozyczony(klient, 1, LocalDate.now(), "stan dobry+"));

        System.out.println("\nusuwanie rezerwacji: \n");
        System.out.println(facade.usunRezerwacje(0));
        System.out.println(facade.usunRezerwacje(2));

        System.out.println("\ndrukuj listy: \n");
        for (String sp : facade.stworzModelSprzetow()) {
            System.out.println(sp + "\n");
        }
        for (String pr : facade.stworzModelPracownikow()) {
            System.out.println(pr + "\n");
        }
        for (String kl : facade.stworzModelKlientow()) {
            System.out.println(kl + "\n");
        }
        
        System.out.println("\nponownie usuwanie rezerwacji\n");
        System.out.println(facade.usunRezerwacje(0));
        System.out.println(facade.usunRezerwacje(2));

        for (String kl : facade.stworzModelKlientow()) {
            System.out.println(kl + "\n");
        }

        System.out.println("\nPonownie dodaje rezerwacje: \n");
        System.out.println(facade.dodajRezerwacje(sprzet1, klient, LocalDate.now()));
        System.out.println(facade.dodajRezerwacje(sprzet1, klient, LocalDate.of(2025, 12, 12)));
        System.out.println(facade.dodajRezerwacje(sprzet1, klient, LocalDate.of(2024, 12, 12)));
        System.out.println(facade.oznaczJakoWypozyczony(klient, 3, LocalDate.now(), "stan dobry+")); // tu sie nie zgadzaja terminy
        System.out.println(facade.oznaczJakoWypozyczony(klient, 3, LocalDate.of(2025, 12, 12), "stan dobry+")); // tutaj wypozyczy
        
        System.out.println(facade.zwrocWypozyczonySprzet(klient, 0));//zwroci
        System.out.println(facade.zwrocWypozyczonySprzet(klient, 1));//zwroci
        System.out.println(facade.zwrocWypozyczonySprzet(klient, 2));// nie ma rezerwacji o numerze 2, wczesniej usuwalismy
        System.out.println(facade.zwrocWypozyczonySprzet(klient, 3)); // zwroci
        System.out.println(facade.zwrocWypozyczonySprzet(klient, 3));// nie ma rezerwacji o numerze 3, bo juz zwrócono sprzet
        
        facade.dodajRezerwacje(sprzet1, klient, LocalDate.now()); // ponownie mozemy dodac rezerwacje na sprzet
        facade.dodajRezerwacje(sprzet2, klient, LocalDate.now()); // rezerwujemy znowu
        
        
        for (String kl : facade.stworzModelKlientow()) {
            System.out.println(kl + "\n");
        }
    }

}
