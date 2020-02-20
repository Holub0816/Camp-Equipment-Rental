package testsFitnessFixture;

import fit.ColumnFixture;

public class Test_dodajEgzemplarz extends ColumnFixture {

    String[] datainspr;
    String[] datainegz;
    String data;
    String result;
    int number;

    public boolean dodajEgzemplarz_() {
        result = null;
        result = SetUp.facade.dodajEgzemplarz(datainspr, datainegz);
        data = SetUp.data.dataEgzemplarz[number];
        return data.equals(result);
    }
}
