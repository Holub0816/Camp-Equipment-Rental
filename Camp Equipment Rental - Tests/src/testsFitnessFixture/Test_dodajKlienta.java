package testsFitnessFixture;

import fit.ColumnFixture;

public class Test_dodajKlienta extends ColumnFixture {

    String[] datainklient;
    String[] datainprac;
    String result;
    String data;
    int number;

    public boolean dodajKlienta_() {
        result = null;
        result = SetUp.facade.dodajKlienta(datainklient, datainprac);
        data = SetUp.data.dataKlient[number];
        return data.equals(result);
    }
}
