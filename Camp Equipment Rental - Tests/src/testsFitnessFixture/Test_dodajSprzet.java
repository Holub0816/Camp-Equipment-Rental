package testsFitnessFixture;

import fit.ColumnFixture;

public class Test_dodajSprzet extends ColumnFixture {

    String[] datainspr;
    String data;
    String result;
    int number;

    public boolean dodajSprzet_() {
        try {
            result = null;
            result = SetUp.facade.dodajSprzet(datainspr);
            data = SetUp.data.dataSprzet[number];
            return data.equals(result);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}