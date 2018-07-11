/**
 * 
 */
package model;

/**
 * @author SebastianKoch
 *
 */
public class EI extends AEx{
	private static int countlow=0;
	private static int countaverage=0;
	private static int counthigh=0;
	private static int gewichtunglow=3;
	private static int gewichtungaverage=4;
	private static int gewichtunghigh=6;
	
	/**
	 * 
	 */
	public EI(int ftr, int det) {
		super(ftr,det);
		if(getKomplexitaet().equals("low"))
			countlow++;
		else if(getKomplexitaet().equals("average"))
			countaverage++;
		else
			counthigh ++;
		
	}
	public static int getGewichtunglow() {
		return gewichtunglow;
	}
	public static int getGewichtungAverage() {
		return gewichtungaverage;
	}
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
	
	
	public static void resetCount() {
		EI.countlow = 0;
		EI.countaverage = 0;
		EI.counthigh = 0;
	}
	
	
	
	
}
