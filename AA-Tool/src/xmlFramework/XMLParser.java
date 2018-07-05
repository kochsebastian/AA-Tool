package xmlFramework;

import model.ATab;
import model.Inhalt;
import model.Model;
import model.Tab;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author SebastianKoch
 */
public class XMLParser {


    /**
     * kommt aus dem Internet
     *
     * @param zieldatei
     */
    protected static void parseXML(File zieldatei) {
        try {
            Boolean LFAuslesen = false, LDAuslesen = false; //////////////////////////////////////////////////

            int i = 0;
            DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder xmlBuilder = xmlFactory.newDocumentBuilder();
            Document dokument = xmlBuilder.parse(zieldatei);

            dokument.getDocumentElement().normalize();

            // einlesen Rootelement Model

            ATab newReiter = Model.getInstanz().getRoot();
            if (dokument.hasChildNodes()) {
                NodeList ebeneEinsNodes = dokument.getChildNodes();
                for (int countEins = 0; countEins < ebeneEinsNodes.getLength(); countEins++) {
                    Node ebeneEinsNode = ebeneEinsNodes.item(countEins);
                    if (ebeneEinsNode.hasChildNodes()) {
                        NodeList ebeneZweiNodes = ebeneEinsNode.getChildNodes();
                        //Tab beginn

                        if (newReiter != null) {

                            for (int countZwei = 0; countZwei < ebeneZweiNodes.getLength(); countZwei++) {
                                @SuppressWarnings("rawtypes")
                                ArrayList next = newReiter.getNext();
                                if (i < next.size()) {
                                    Node ebeneZweiNode = ebeneZweiNodes.item(countZwei);
                                    if (ebeneZweiNode.hasChildNodes()) { //Wenn es inhalt hat
                                        if (next.get(i) instanceof Inhalt) {
                                            Inhalt tmpKind = (Inhalt) next.get(i);

                                            String[][] temp = tmpKind.getInhalt(); // Tab
                                            NodeList ebeneDreiNodes = ebeneZweiNode.getChildNodes();
                                            for (int countDrei = 0; countDrei < ebeneDreiNodes.getLength(); countDrei++) {
                                                Node ebeneDreiNode = ebeneDreiNodes.item(countDrei);

                                                if (ebeneDreiNode.hasAttributes()) {
                                                    NamedNodeMap nodeMap = ebeneDreiNode.getAttributes(); //immer 3 elemente enthalten (inhalt, spalte, zeile)
                                                    Node inhalt = nodeMap.item(0);
                                                    if (LFAuslesen == true) {//////////////////////////////////////////////////
                                                        LFAuslesen = false;//////////////////////////////////////////////////
                                                        IOConnector.addLFBuffer(inhalt.getNodeValue());//////////////////////////////////////////////////
                                                    }//////////////////////////////////////////////////

                                                    if (inhalt.getNodeValue().equals("/LF/")) {//////////////////////////////////////////////////
                                                        // hier fuer aufwandsabschaetzung//////////////////////////////////////////////////
                                                        LFAuslesen = true;//////////////////////////////////////////////////
                                                    }//////////////////////////////////////////////////
                                                    if (LDAuslesen == true) {//////////////////////////////////////////////////
                                                        LDAuslesen = false;//////////////////////////////////////////////////
                                                        IOConnector.addLDBuffer(inhalt.getNodeValue());//////////////////////////////////////////////////
                                                    }//////////////////////////////////////////////////

                                                    if (inhalt.getNodeValue().equals("/LD/")) {//////////////////////////////////////////////////
                                                        // hier fuer aufwandsabschaetzung//////////////////////////////////////////////////
                                                        LDAuslesen = true;//////////////////////////////////////////////////
                                                    }//////////////////////////////////////////////////

                                                    Node spalte = nodeMap.item(1);
                                                    Node zeile = nodeMap.item(2);
                                                    if (temp[Integer.parseInt(spalte.getNodeValue())][Integer.parseInt(zeile.getNodeValue())] != null) {
                                                        temp[Integer.parseInt(spalte.getNodeValue())][Integer.parseInt(zeile.getNodeValue())] = inhalt.getNodeValue();
                                                    }
                                                }
                                            }
                                            i++;
                                        } else if (next.get(i) instanceof Tab | next.get(i) instanceof Inhalt) {
                                            newReiter = (Tab) next.get(i);
                                            i = 0;
                                            countZwei--;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException error) {
            error.printStackTrace();
        }
    }
}