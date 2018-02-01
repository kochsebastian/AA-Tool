
package xmlFramework;

import java.io.File;

/**
 * 
 * @author SebastianKoch
 *
 */
public class IOConnector {
	
	/**
	 * 
	 * @param zieldatei
	 */
    public static void speichereXML(File zieldatei) {
        XMLGenerator.generiereXML(zieldatei);
    }
    
    /**
     * 
     * @param zieldatei
     */
    public static void leseXML(File zieldatei) {
        XMLParser.parseXML(zieldatei);
    }
}
