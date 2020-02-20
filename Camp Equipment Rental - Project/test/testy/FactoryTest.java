package testy;

import base.Factory;
import base.Klient;
import base.Pracownik;
import base.Rezerwacja;
import base.Sprzet;
import java.time.LocalDate;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;

@Category({Test_Control.class})
public class FactoryTest {

    static Data data;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void setUpClass() {
        data = new Data();
    }

    @Test
    public void stworzSprzet() {
        System.out.println("tworzenie sprzetu");
        Factory factory = new Factory();
        for (int i = 0; i < data.daneSprzetu.length - 1; i++) {
            Sprzet sp = factory.utworzSprzet(data.daneSprzetu[i]);
            assertEquals(data.sprzety[i], sp);
            assertNotSame(data.sprzety[i], sp);
        }
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Wyskoczył wyjątek");
        factory.utworzSprzet(data.daneSprzetu[data.daneSprzetu.length - 1]);
    }

    @Test
    public void stworzKlienta() {
        System.out.println("tworzenie klienta");
        Factory factory = new Factory();
        for (int i = 0; i < data.daneKlientow.length; i++) {
            Klient k = factory.utworzKlienta(data.daneKlientow[i]);
            assertEquals(data.klienci[i], k);
            assertNotSame(data.klienci[i], k);
        }
    }

    @Test
    public void stworzRezerwacje() {
        System.out.println("tworzenie rezerwacji");
        Factory factory = new Factory();
        for (int i = 0; i < data.daneRezerwacji.length; i++) {
            Rezerwacja r = factory.utworzRezerwacje(data.daneRezerwacji[i]);
            assertEquals(data.rezerwacje[i], r);
            assertNotSame(data.rezerwacje[i], r);
        }
    }

    @Test
    public void stworzPracownika() {
        System.out.println("tworzenie pracownika");
        Factory factory = new Factory();
        for (int i = 0; i < data.danePracownikow.length; i++) {
            Pracownik p = factory.utworzPracownika(data.danePracownikow[i]);
            assertEquals(data.pracownicy[i], p);
            assertNotSame(data.pracownicy[i], p);
        }
    }

}
