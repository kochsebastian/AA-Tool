package xmlFramework;

import java.io.File;
import java.util.ArrayList;

/**
 * @author SebastianKoch
 */
public class IOConnector {

    private static ArrayList<String> LFBuffer = new ArrayList<String>();
    private static ArrayList<String> LDBuffer = new ArrayList<String>();
    private static ArrayList<Integer> FunctionpointsBuffer = new ArrayList<Integer>();


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

    /**
     * 
     * @return LFBuffer
     */
    public static ArrayList<String> getsLFBuffer() {
        return LFBuffer;
    }

    /**
     * 
     * @param e
     */
    public static void addLFBuffer(String e) {
        LFBuffer.add(e);
    }

    /**
     * 
     */
    public static void resetLFBuffer() {
        LFBuffer.clear();
    }

    /**
     * 
     * @return
     */
    public static ArrayList<String> getsLDBuffer() {
        return LDBuffer;
    }

    /**
     * 
     * @param e
     */
    public static void addLDBuffer(String e) {
        LDBuffer.add(e);
    }

    /**
     * 
     */
    public static void resetLDBuffer() {
        LDBuffer.clear();
    }
    
    public static ArrayList<Integer> getsFunctionpointsBuffer() {
    	return FunctionpointsBuffer;
    }
}
