package aufwandsabschaetzung;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class AOptimierung {

protected float i;
    
    // Optimierungsparameter als Datei neben das Executable schreiben
    protected void setOpimierungsDatei(String[] paramater) {
        File optimierungsDatei = new File("optimierung.txt");

        try{
            optimierungsDatei.createNewFile();
            FileWriter writer = new FileWriter(optimierungsDatei);

            for (int i = 0; i < paramater.length; i++) {
                writer.write(paramater[i]+" ");
            }

            writer.close();
        }
        catch (IOException e)
        {

        }
    }
    
    // gibt die Parameter der Optimierungsdatei neben dem Executable zurück
    protected String[] getOpimierungsDatei() {
        int anzahlParamater = 0;
        String[] paramater;
        
        File optimierungsDatei = new File("optimierung.txt");
       
        try { 
            if (! optimierungsDatei.exists())  {
                optimierungsDatei.createNewFile();
            }

            Scanner scanner = new Scanner(optimierungsDatei);

            while (scanner.hasNext()) {
                scanner.next();
                anzahlParamater++;
            }
            paramater = new String[anzahlParamater];

            scanner.close();
            scanner = new Scanner(optimierungsDatei);
            for(int i = 0; i < anzahlParamater; i++) {
                  paramater[i] = scanner.next();
            }

            scanner.close();
            return paramater;
        }
        catch(IOException e) {
            return null;
        }
    }  
    
    // casted eine Float Zhl zu einer Integer Zahl und rundet dabei 
    protected int rundenKonvertierenInt(float p_zahl) {
        return (int)((p_zahl)+0.5);
    }

}
