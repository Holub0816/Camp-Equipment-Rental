
package testy;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;

@Categories.SuiteClasses({CreateModelsTest.class,PracownikTest.class, FactoryTest.class, DodajKlientaFasadaTest.class})
@RunWith(Categories.class)
@Categories.IncludeCategory(Test_Control.class)
public class Suite1 {

}
