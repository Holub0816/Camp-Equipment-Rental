package testsFitnessFixture;

import fit.ColumnFixture;
import java.time.LocalDate;

public class Test_dodajRezerwacje extends ColumnFixture {
    
    String[] datainspr;
    String[] datainklient;
    int[] date;
    String data;
    String result;
    int number;

    public boolean dodajRezerwacje_() {
        result = null;
        result = SetUp.facade.dodajRezerwacje(datainspr, datainklient, LocalDate.of(date[0], date[1], date[2]));
        data = SetUp.data.dataRezerwacja[number];
        return data.equals(result);
    }
    
}
