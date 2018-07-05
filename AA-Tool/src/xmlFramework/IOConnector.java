package xmlFramework;

import java.io.File;
import java.util.ArrayList;

/**
 * @author SebastianKoch
 */
public class IOConnector {

    private static ArrayList<String> LFBuffer = new ArrayList<String>();
    private static ArrayList<String> LDBuffer = new ArrayList<String>();


    /**
     * @param zieldatei
     */
    public static void speichereXML(File zieldatei) {
        XMLGenerator.generiereXML(zieldatei);
    }

    /**
     * @param zieldatei
     */
    public static void leseXML(File zieldatei) {
        XMLParser.parseXML(zieldatei);
    }


    public static ArrayList<String> getsLFBuffer() {
        return LFBuffer;
    }

    public static void addLFBuffer(String e) {
        LFBuffer.add(e);
    }

    public static void resetLFBuffer() {
        LFBuffer.clear();
    }

    public static ArrayList<String> getsLDBuffer() {
        return LDBuffer;
    }

    public static void addLDBuffer(String e) {
        LDBuffer.add(e);
    }

    public static void resetLDBuffer() {
        LDBuffer.clear();
    }
}
