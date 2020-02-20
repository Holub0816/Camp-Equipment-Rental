package testy;

import base.Facade;
import base.Klient;
import base.Pracownik;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;
import static testy.FactoryTest.data;

@Category({Test_Control.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DodajKlientaFasadaTest {

    private static Facade facade = new Facade();

    @BeforeClass
    public static void setUpClass() {
        data = new Data();
    }

    @Test
    public void b_dodajPracownika() {
        System.out.println("dodaj pracownika");
        facade.getPracownicy().clear();
        for (int i = 0; i < data.danePracownikow.length; i++) {
            String str = facade.dodajPracownika(data.danePracownikow[i]);
            System.out.println(str);
            assertEquals(data.pracownicyFasada[0], str);
        }
    }

    @Test
    public void a_wyszukajPracownika2() {
        ArrayList<Pracownik> pracownicy = new ArrayList<>(Arrays.asList(data.pracownicy));
        facade.setPracownicy(pracownicy);
        System.out.println("wyszukaj pracownika");
        for (int i = 0; i < data.pracownicy.length; i++) {
            Pracownik prac = facade.wyszukaj(data.pracownicy[i], facade.getPracownicy());
            assertNotNull(prac);
        }
    }

    @Test
    public void c_wyszukajPracownika() {
        System.out.println("wyszukaj pracownika");
        for (int i = 0; i < data.pracownicy.length; i++) {
            Pracownik prac = facade.wyszukaj(data.pracownicy[i], facade.getPracownicy());
            assertSame(facade.getPracownicy().get(i), prac);
        }
    }

    @Test
    public void d_wyszukajKlienta() {
        System.out.println("Wyszukaj klienta");
        for (int i = 0; i < data.klienci.length; i++) {
            Klient kl = facade.wyszukaj(data.klienci[i], facade.getKlienci());
            assertNull(kl);
        }
    }

    @Test
    public void e_dodajKlienta() {
        System.out.println("Dodaj klienta");
        for (int i = 0; i < data.daneKlientow.length; i++) {
            String str = facade.dodajKlienta(data.daneKlientow[i], data.danePracownikow[i]);
            assertEquals(data.klienciFasada[0], str);
        }
    }

    @Test
    public void f_wyszukajKlienta2() {
        System.out.println("Wyszukaj klienta");
        ArrayList<Klient> klienci = new ArrayList<>(Arrays.asList(data.klienci));
        facade.setKlienci(klienci);
        for (int i = 0; i < data.klienci.length; i++) {
            Klient kl = facade.wyszukaj(data.klienci[i], facade.getKlienci());
            assertSame(kl, facade.getKlienci().get(i));
        }
    }

}
