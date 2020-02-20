package testy;


import base.Facade;
import base.Klient;
import base.Pracownik;
import base.Sprzet;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

@Category({Test_Control.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateModelsTest {

    private static Facade facade = new Facade();

    static Data data;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void setUpClass() {
        data = new Data();
    }

    @Test
    public void a_testWyszukajSprzet() {
        System.out.println("wyszukaj sprzet");
        ArrayList<Sprzet> sprzety = new ArrayList<>(Arrays.asList(data.sprzety));
        facade.setSprzety(sprzety);
        for (int i = 0; i < data.sprzety.length; i++) {
            Sprzet sprzet = facade.wyszukaj(data.sprzety[i], facade.getSprzety());
            assertNotNull(sprzet);
        }
    }

    @Test
    public void b_testStworzModelPracownikow() {
        System.out.println("stwórz model pracowników");
        Pracownik[] newPracownik = new Pracownik[data.pracownicy.length];
        for (int i = 0; i < data.pracownicy.length; i++) {
            newPracownik[i] = data.pracownicy[i];
            ArrayList<String> prac = new ArrayList<>(Arrays.asList(Arrays.toString(newPracownik)));
            ArrayList<String> prac2 = facade.stworzModelPracownikow();
            assertNotSame(prac, prac2);
        }
    }

    @Test
    public void c_testStworzModelKlientow() {
        System.out.println("stwórz model klientów");
        Klient[] newKlient = new Klient[data.klienci.length];
        for (int i = 0; i < data.klienci.length; i++) {
            newKlient[i] = data.klienci[i];
            ArrayList<String> klienci = new ArrayList<>(Arrays.asList(Arrays.toString(newKlient)));
            ArrayList<String> klienci2 = facade.stworzModelKlientow();
            assertNotSame(klienci, klienci2);
        }
    }

    @Test
    public void d_testStworzModelSprzetow() {
        System.out.println("stwórz model sprzetow");
        Sprzet[] newSprzet = new Sprzet[data.sprzety.length];
        for (int i = 0; i < data.sprzety.length; i++) {
            newSprzet[i] = data.sprzety[i];
            ArrayList<String> sprzety = new ArrayList<>(Arrays.asList(Arrays.toString(newSprzet)));
            ArrayList<String> sprzety2 = facade.stworzModelSprzetow();
            assertNotNull(sprzety2);
            assertNotEquals(sprzety2.size(), 0);
        }
    }

    @Test
    public void e_testStworzModelSprzetow2() {
        System.out.println("stwórz model sprzetow2");
        for (int i = 0; i < data.daneSprzetu.length; i++) {
            String s = facade.dodajSprzet(data.daneSprzetu[0]);
            ArrayList<String> sprzety2 = facade.stworzModelSprzetow();
            assertNotNull(sprzety2);
            assertEquals(sprzety2.get(0), facade.getSprzety().get(0).toString());
        }
    }

}
