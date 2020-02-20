package base;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class Facade {

    private ArrayList<Sprzet> sprzety;
    private ArrayList<Klient> klienci;
    private ArrayList<Pracownik> pracownicy;
    private Locale locale = new Locale("pl");
    private ResourceBundle resource = ResourceBundle.getBundle("Bundle", locale);

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

    public <T> T wyszukaj(T t, ArrayList<T> list) {
        int index = 0;
        if ((index = list.indexOf(t)) != -1) {
            return list.get(index);
        } else {
            return null;
        }
    }

    public <T> boolean dodaj(T t, ArrayList<T> list) {
        if (wyszukaj(t, list) == null) {
            list.add(t);
            return true;
        } else {
            return false;
        }
    }

    public String dodajKlienta(String[] daneKlienta, String[] pracownik) {
        Factory factory = new Factory();
        Pracownik prac = factory.utworzPracownika(pracownik);
        prac = wyszukaj(prac, pracownicy);
        if (prac != null) {
            Klient klient = factory.utworzKlienta(daneKlienta);
            if (dodaj(klient, klienci)) {
                prac.dodajKlienta(klient);
                return resource.getString("fasada.dodajKlienta1");
            } else {
                return resource.getString("fasada.dodajKlienta2");
            }
        } else {
            return resource.getString("fasada.dodajKlienta3");
        }
    }

    public String usunPracownika(String[] danePracownika) {
        Factory factory = new Factory();
        Pracownik pracownik = factory.utworzPracownika(danePracownika);
        int index = pracownicy.indexOf(pracownik);
        if (index != -1) {
            Pracownik pr = pracownicy.get(index);
            pracownicy.remove(index);
            przepiszKlientow(pr);
            return resource.getString("fasada.usunPracownika1");
        } else {
            return resource.getString("fasada.usunPracownika2");
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
        return resource.getString("fasada.przepiszKlientow");
    }

    public String usunKlienta(String[] daneKlienta) {
        Factory factory = new Factory();
        Klient klient = factory.utworzKlienta(daneKlienta);
        int index = klienci.indexOf(klient);
        if (index != -1) {
            klient = klienci.get(index);
            klient.usunWszystkieRezerwacje();
            klient.usunPracownika();
            klienci.remove(index);
            return resource.getString("fasada.usunKlienta1");
        } else {
            return resource.getString("fasada.usunKlienta2");
        }
    }

    public String usunSprzet(String[] daneSprzetu) {
        Factory factory = new Factory();
        Sprzet sprzet = factory.utworzSprzet(daneSprzetu);
        if (sprzety.remove(sprzet)) {
            return resource.getString("fasada.usunSprzet1");
        } else {
            return resource.getString("fasada.usunSprzet2");
        }
    }

    public String dodajPracownika(String[] danePracownika) {
        Factory factory = new Factory();
        Pracownik pracownik = factory.utworzPracownika(danePracownika);
        if (dodaj(pracownik, pracownicy)) {
            return resource.getString("fasada.dodajPracownika1");
        } else {

            return resource.getString("fasada.dodajPracownika2");
        }
    }

    public String dodajSprzet(String[] daneSprzetu) {
        Factory factory = new Factory();
        Sprzet sprzet = factory.utworzSprzet(daneSprzetu);
        if (dodaj(sprzet, sprzety)) {
            return resource.getString("fasada.dodajSprzet1");
        } else {
            return resource.getString("fasada.dodajSprzet2");
        }
    }

    public String dodajEgzemplarz(String[] dane1, String[] dane2) {
        String info;
        Factory factory = new Factory();
        Sprzet sp = factory.utworzSprzet(dane1);
        sp = wyszukaj(sp, sprzety);
        if (sp != null) {
            if (sp.dodajDoListyEgzemplarzy(dane2)) {
                info = resource.getString("fasada.dodajEgzemplarz1");
            } else {
                info = resource.getString("fasada.dodajEgzemplarz2");
            }
        } else {
            info = resource.getString("fasada.dodajEgzemplarz3");
        }
        return info;
    }

    public String dodajRezerwacje(String[] dane1, String[] dane2, LocalDate date) {
        String info;
        Factory factory = new Factory();
        Sprzet sprzet = factory.utworzSprzet(dane1);
        sprzet = wyszukaj(sprzet, sprzety);
        if (sprzet != null) {
            Egzemplarz egz;
            if ((egz = sprzet.wyszukajWolnegoEgzemplarza(date)) != null) {
                Klient klient = factory.utworzKlienta(dane2);
                klient = wyszukaj(klient, klienci);
                if (klient != null) {
                    klient.dodajRezerwacje(egz, date);
                    info = resource.getString("fasada.dodajRezerwacje1");
                } else {
                    info = resource.getString("fasada.dodajRezerwacje2");
                }

            } else {
                info = resource.getString("fasada.dodajRezerwacje3");
            }
        } else {
            info = resource.getString("fasada.dodajRezerwacje4");
        }
        return info;
    }

    public String usunRezerwacje(int nr) {
        Factory factory = new Factory();
        Rezerwacja rez = factory.utworzRezerwacje(nr);
        rez = wyszukajRezerwacje(rez);
        if (rez != null) {
            if (rez.usunRezerwacje()) {
                return resource.getString("fasada.usunRezerwacje1");
            } else {
                return resource.getString("fasada.usunRezerwacje2");
            }
        } else {
            return resource.getString("fasada.usunRezerwacje3");
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
        k = wyszukaj(k, klienci);
        if (k != null) {
            return k.wypozycz(nrRez, date, stanSprzetu);
        } else {
            return resource.getString("fasada.oznaczJakoWypozyczony");
        }
    }

    public String usunNieaktualneRezerwacje(LocalDate date) {
        StringBuilder str = new StringBuilder();
        for (Klient k : klienci) {
            str.append(k.usunNieaktualneRezerwacje(date).toString());
        }
        return resource.getString("fasada.usunNieaktualneRezerwacje") + str.toString();
    }

    public String zwrocWypozyczonySprzet(String[] klient, int nrRez) {
        Factory factory = new Factory();
        Klient k = factory.utworzKlienta(klient);
        k = wyszukaj(k, klienci);
        if (k != null) {
            return k.zwrocSprzet(nrRez);
        } else {
            return resource.getString("fasada.zwrocWypozyczonySprzet");
        }
    }

    public ArrayList<String> stworzModelPracownikow() {
        return stworzModel(pracownicy);
    }

    public ArrayList<String> stworzModelKlientow() {
        return stworzModel(klienci);
    }

    public ArrayList<String> stworzModelSprzetow() {
        return stworzModel(sprzety);
    }
    
    public <T> ArrayList<String> stworzModel(ArrayList<T> list){
        ArrayList<String> stringlist = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            stringlist.add(list.get(i).toString());
        }
        return stringlist;
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
        String[] klient = {"Jan", "Kowalski", "97121208993", "Wroclaw", "Hallera 8/112", "AYW8938983"};
        System.out.println(facade.dodajKlienta(klient, pracownik));
        System.out.println(facade.dodajKlienta(klient, pracownik));

        System.out.println("\ndodawanie egzemplarza: \n");
        System.out.println(facade.dodajEgzemplarz(sprzet1, new String[]{"0"}));
        System.out.println(facade.dodajEgzemplarz(sprzet2, new String[]{"0"}));
        System.out.println(facade.dodajEgzemplarz(sprzet2, new String[]{"1"}));
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
