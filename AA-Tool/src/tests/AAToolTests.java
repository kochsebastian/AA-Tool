package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestImportExport.class,
				TestAufwandsabschaetzung.class,
				TestSelbstoptimierung.class})
public class AAToolTests {

}
