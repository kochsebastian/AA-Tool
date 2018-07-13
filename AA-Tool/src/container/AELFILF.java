/**
 * 
 */
package container;

/**
 * Container für die ELF/ILF der Produktdaten
 * @author SebastianKoch
 *
 */
public abstract class AELFILF {


	private int ret;
	private int det;
	private String komplexitaet;
	
	/**
	 * setzt ret, det und berechnet daraus gleich die komplexitaet
	 * @param ret
	 * @param det
	 */
	public AELFILF(int ret, int det) {
		this.ret = ret;
		this.det = det;
		if((ret <= 1 && det <= 50) || (ret <= 5 && det <= 19)) 
			komplexitaet = "low";
		else if((ret <= 5 && det > 5) || (ret <= 5 && det <= 50) 
				|| (ret <= 1 && det > 50))
			komplexitaet = "average";
		else
			komplexitaet = "high";
	}
	
	/**
	 * @return komplexitaet
	 */
	public String getKomplexitaet() {
		return komplexitaet;
	}
	
	
	/**
	 * @return the ret
	 */
	public int getRet() {
		return ret;
	}
	/**
	 * @param ret the ret to set
	 */
	public void setRet(int ret) {
		this.ret = ret;
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
