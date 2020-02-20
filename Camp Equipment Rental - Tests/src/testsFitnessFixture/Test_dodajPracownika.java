package testsFitnessFixture;

import fit.ColumnFixture;

public class Test_dodajPracownika extends ColumnFixture {

    String[] datainprac;
    String data;
    String result;
    int number;

    public boolean dodajPracownika_() {
        result = null;
        result = SetUp.facade.dodajPracownika(datainprac);
        data = SetUp.data.dataPracownik[number];
        return data.equals(result);
    }
}
