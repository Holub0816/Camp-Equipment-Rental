/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testy;

import base.Klient;
import base.Pracownik;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;
import org.junit.runners.Parameterized;

/**
 *
 * @author Maciek
 */
@Category({Test_Control.class})
public class PracownikTest {

    static Data data;
    static Pracownik pracownik;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void setUpClass() {
        data = new Data();
    }

    @Before
    public void setUp() {
        pracownik = data.pracownicy[0];
    }

    @Test
    public void testEquals() {
        System.out.println("equals");
        for (int j = 0; j < 2; j++) {
            for (int i = j; i < 2; i++) {
                if (i == j) {
                    assertTrue(data.pracownicy[i].equals(data.pracownicy[j]));
                } else {
                    assertFalse(data.pracownicy[i].equals(data.pracownicy[j]));
                }
            }
        }
    }
    @Test
    public void dodajKlienta() {
        System.out.println("dodaj klienta");
        for (int i = 0; i < data.klienci.length; i++) {
            pracownik.dodajKlienta(data.klienci[i]);
            assertEquals(data.klienci[i], pracownik.getListaKlientow().get(i));
        }

    }

    @Test
    public void usunKlienta() {
        System.out.println("usuń klienta");
        for (int i = 0; i < data.klienci.length; i++) {
            pracownik.dodajKlienta(data.klienci[i]);
        }
        for (int i = data.klienci.length - 1; i > 0; i--) {
            pracownik.usunKlienta(data.klienci[i]);
            assertNotEquals(data.klienci[i], pracownik.getListaKlientow().get(i));
        }

    }

}
