/**
 * 
 */
package container;

/**
 * @author SebastianKoch
 *
 */
public class ELF extends AELFILF{
	private static int countlow=0;
	private static int countaverage=0;
	private static int counthigh=0;
	private static int gewichtunglow=5;
	private static int gewichtungaverage=7;
	private static int gewichtunghigh=10;
	
	public ELF(int ret, int det) {
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
		ELF.countlow = 0;
		ELF.countaverage = 0;
		ELF.counthigh = 0;
	}



}
