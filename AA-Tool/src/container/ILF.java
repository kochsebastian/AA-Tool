/**
 * 
 */
package container;

/**
 * Container für ILF
 * @author SebastianKoch
 *
 */
public class ILF extends AELFILF {
	private static int countlow=0;
	private static int countaverage=0;
	private static int counthigh=0;
	private static int gewichtunglow=7;
	private static int gewichtungaverage=10;
	private static int gewichtunghigh=15;
	
	/**
	 * zaehlt Anzahl an Low, Average und High
	 * @param ret
	 * @param det
	 */
	public ILF(int ret, int det) {
		super(ret, det);
		if(getKomplexitaet().equals("low"))
			countlow++;
		else if(getKomplexitaet().equals("average"))
			countaverage++;
		else
			counthigh ++;
	}
	

	/**
	 * getter
	 * @return gewichtunglow
	 */
	public static int getGewichtunglow() {
		return gewichtunglow;
	}
	
	/**
	 * getter
	 * @return gewichtungaverage
	 */
	public static int getGewichtungAverage() {
		return gewichtungaverage;
	}
	
	/**
	 * getter
	 * @return gewichtunghigh
	 */
	public static int getGewichtungHigh() {
		return gewichtunghigh;
	}
	
	/**
	 * getter
	 * @return the countlow
	 */
	public static int getCountlow() {
		return countlow;
	}

	/**
	 * getter
	 * @return the countaverage
	 */
	public static int getCountaverage() {
		return countaverage;
	}

	/**
	 * getter
	 * @return the counthigh
	 */
	public static int getCounthigh() {
		return counthigh;
	}
	
	/**
	 * setzt Count auf 0 zurueck
	 */
	public static void resetCount() {
		ILF.countlow = 0;
		ILF.countaverage = 0;
		ILF.counthigh = 0;
	}




	

}
