package xmlFramework;

import model.ATab;
import model.Inhalt;
import model.Model;
import model.Tab;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

/**
 * erstellt xml code und file
 * @author SebastianKoch
 */
public class XMLGenerator {

    /**
     *  erstellt XML:
     *	erstellt Datei
     *	iteriert durch model und schreibt XML
     * @param zieldatei
     */
    protected static void generiereXML(File zieldatei) {

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //Wurzelement
            Document doc = docBuilder.newDocument();

            org.w3c.dom.Element rootElement = doc.createElement("AA-Tool");
            doc.appendChild(rootElement);

            ATab newTab = Model.getInstanz().getRoot();

            Tab naechsterTab = null;

            while (newTab != null) {

                org.w3c.dom.Element aktuellerTab = doc.createElement(newTab.getName());
                rootElement.appendChild(aktuellerTab);

                @SuppressWarnings("rawtypes")
                ArrayList next = newTab.getNext();

                for (int i = 0; i < next.size(); i++) { //gehe durch die einzelnen header
                    if (next.get(i) instanceof Inhalt) {
                        Inhalt tmpNext = (Inhalt) next.get(i);

                        String[][] inhalt = tmpNext.getInhalt();

                        for (int j = 0; j < inhalt.length; j++) { //first array dimension

                            for (int k = 0; k < inhalt[j].length; k++) { //second array Dimension

                                if (inhalt[j][k] != null) {   //set contents
                                    org.w3c.dom.Element Element = doc.createElement("Element_" + j + "_" + k);
                                    aktuellerTab.appendChild(Element);

                                    Attr attribut = doc.createAttribute("Inhalt");
                                    attribut.setValue(inhalt[j][k]);
                                    Element.setAttributeNode(attribut);

                                    Attr spalte = doc.createAttribute("Spalte");
                                    spalte.setValue("" + j);
                                    Element.setAttributeNode(spalte);

                                    Attr zeile = doc.createAttribute("Zeile");
                                    zeile.setValue("" + k);
                                    Element.setAttributeNode(zeile);
                                }
                            }
                        }
                    } else if (next.get(i) instanceof Tab | next.get(i) instanceof Inhalt) {
                        naechsterTab = (Tab) next.get(i);
                    }
                }
                newTab = naechsterTab;
                naechsterTab = null;
            }

            //schreibe gesamte Datei als XML ins Zielverzeichnis
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(zieldatei);
            transformer.transform(source, result);

        } 
        catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } 
        catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

}
