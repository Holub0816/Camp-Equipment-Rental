
package testsFitnessFixture;

import base.Facade;
import fit.Fixture;
import testdata.Data;


public class SetUp extends Fixture {
    static Facade facade;
    static Data data;
    public SetUp(){
        facade = new Facade();
        data = new Data();
    }
}
