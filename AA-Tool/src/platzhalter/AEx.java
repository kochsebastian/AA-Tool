/**
 * 
 */
package platzhalter;

/**
 * @author SebastianKoch
 *
 */
public abstract class AEx {

	private int ftr;
	private int det;
	private String komplexitaet;
	
	/**
	 * 
	 */
	public AEx(int ftr, int det) {
		this.ftr = ftr;
		this.det = det;
		if((ftr <= 1 && det <= 15) || (ftr <= 2 && det <= 4)) 
			komplexitaet = "low";
		else if((ftr <= 1 && det <= 15) || (ftr == 2 && det <= 4) 
				|| (ftr <= 1 && det > 15))
			komplexitaet = "average";
		else
			komplexitaet = "high";
		
		
	}
	
	/**
	 * @return the komplexitaet
	 */
	public String getKomplexitaet() {
		return komplexitaet;
	}
	
	
	/**
	 * @return the fet
	 */
	public int getFtr() {
		return ftr;
	}
	/**
	 * @param fet the fet to set
	 */
	public void setFtr(int ftr) {
		this.ftr = ftr;
	}
	/**
	 * @return the det
	 */
	public int getDet() {
		return det;
	}
	/**
	 * @param det the det to set
	 */
	public void setDet(int det) {
		this.det = det;
	}
}
