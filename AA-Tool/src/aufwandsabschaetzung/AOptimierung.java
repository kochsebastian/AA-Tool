package aufwandsabschaetzung;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JTable;

public abstract class AOptimierung {

protected float i;
    
    // Optimierungsparameter als Datei neben das Executable schreiben
    public static void setOpimierungsDatei(JTable tableEinflussfaktoren) {
        File optimierungsDatei = new File("optimierung.txt");

        try{
            optimierungsDatei.createNewFile();
            FileWriter writer = new FileWriter(optimierungsDatei);

            for (int i = 0; i < tableEinflussfaktoren.getRowCount(); i++) {
            	if(i == 3) {
            		writer.write("0 ");
            		continue;
            	}
                writer.write(tableEinflussfaktoren.getValueAt(i, 1)+" ");
            }

            writer.close();
        }
        catch (IOException e)
        {

        }
    }
    
    // gibt die Parameter der Optimierungsdatei neben dem Executable zurück
    public static String[] getOpimierungsDatei() {
        int anzahlEInflussfaktoren = 0;
        String[] einflussfaktor;
        
        File optimierungsDatei = new File("optimierung.txt");
       
        try { 
            if (! optimierungsDatei.exists())  {
                optimierungsDatei.createNewFile();
            }

            Scanner scanner = new Scanner(optimierungsDatei);

            while (scanner.hasNext()) {
                scanner.next();
                anzahlEInflussfaktoren++;
            }
            einflussfaktor = new String[anzahlEInflussfaktoren];

            scanner.close();
            scanner = new Scanner(optimierungsDatei);
            for(int i = 0; i < anzahlEInflussfaktoren; i++) {
            	einflussfaktor[i] = scanner.next();
            }

            scanner.close();
            return einflussfaktor;
        }
        catch(IOException e) {
            return null;
        }
    }  
}
