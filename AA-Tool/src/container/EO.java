/**
 * 
 */
package container;

/**
 * Container für Ausgabe (EO)
 * @author SebastianKoch
 *
 */
public class EO extends AEx{
	private static int countlow=0;
	private static int countaverage=0;
	private static int counthigh=0;
	private static int gewichtunglow=4;
	private static int gewichtungaverage=5;
	private static int gewichtunghigh=7;
	
	/**
	 * ruft Superklasse auf und zaehlt die ausgaben nach komplexitaet
	 * @param ftr
	 * @param det
	 */
	public EO(int ftr, int det) {
		super(ftr,det);if(getKomplexitaet().equals("low"))
			countlow++;
		else if(getKomplexitaet().equals("average"))
			countaverage++;
		else
			counthigh ++;
		
	}
	
	/**
	 * 
	 * @return gewichtunglow
	 */
	public static int getGewichtunglow() {
		return gewichtunglow;
	}
	/**
	 * 
	 * @return gewichtungaverage
	 */
	public static int getGewichtungAverage() {
		return gewichtungaverage;
	}
	/**
	 * 
	 * @return gewichtunghigh
	 */
	public static int getGewichtungHigh() {
		return gewichtunghigh;
	}
	
	/**
	 * @return the countlow
	 */
	public static int getCountlow() {
		return countlow;
	}

	/**
	 * @return the countaverage
	 */
	public static int getCountaverage() {
		return countaverage;
	}

	/**
	 * @return the counthigh
	 */
	public static int getCounthigh() {
		return counthigh;
	}
	
	/**
	 * resetet alle zaehler
	 */
	public static void resetCount() {
		EO.countlow = 0;
		EO.countaverage = 0;
		EO.counthigh = 0;
	}
}
