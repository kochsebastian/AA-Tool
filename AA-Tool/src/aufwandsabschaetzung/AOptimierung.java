package aufwandsabschaetzung;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JTable;

/**
 * Zum abspeichern der Optimierungsparameter der Einflussfaktoren
 * @author chrisboger, SebastianKoch, AnneBlomeier
 *
 */

public abstract class AOptimierung {

protected float i;
    
    /**
     *  Optimierungsparameter als Datei neben das Executable schreiben
     * @param tableEinflussfaktoren
     */
    public static void setOpimierungsDatei(JTable tableEinflussfaktoren) {
        File optimierungsDatei = new File("optimierung.txt"); //

        try{
            optimierungsDatei.createNewFile();
            FileWriter writer = new FileWriter(optimierungsDatei);

            for (int i = 0; i < tableEinflussfaktoren.getRowCount(); i++) {
            	if(i == 3) { // die eine zeile die leer ist
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
    
    /**
     *  gibt die Parameter der Optimierungsdatei neben dem Executable zurück
     * @return einflussfaktoren
     */
    public static int[] getOpimierungsDatei() {
        int anzahlEInflussfaktoren = 0;
        int[] einflussfaktor;
        
        File optimierungsDatei = new File("optimierung.txt");
       
        try { 
            if (! optimierungsDatei.exists())  {
                optimierungsDatei.createNewFile();
            }

            Scanner scanner = new Scanner(optimierungsDatei);

            while (scanner.hasNextInt()) {
                scanner.nextInt();
                anzahlEInflussfaktoren++;
            }
            einflussfaktor = new int[anzahlEInflussfaktoren];

            scanner.close();
            scanner = new Scanner(optimierungsDatei);
            for(int i = 0; i < anzahlEInflussfaktoren; i++) {
            	einflussfaktor[i] = scanner.nextInt();
            }

            scanner.close();
            return einflussfaktor;
        }
        catch(IOException e) {
            return null;
        }
    }  
}
