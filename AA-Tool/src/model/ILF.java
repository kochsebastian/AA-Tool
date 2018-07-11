/**
 * 
 */
package model;

/**
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
	
	public ILF(int ret, int det) {
		super(ret, det);
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
		ILF.countlow = 0;
		ILF.countaverage = 0;
		ILF.counthigh = 0;
	}




	

}
