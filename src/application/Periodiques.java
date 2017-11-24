package application;

import java.io.Serializable;

public class Periodiques extends Documents implements Serializable {
	private static final long serialVersionUID = 1L;
	private String intVol, intPer;
	
	public Periodiques(String strNumDoc, String strTitre, String strDate, String strPretEtat, String intVol, String intPer) {
		super(strNumDoc, strTitre, strDate,  strPretEtat);
		
		this.intVol = intVol;
		this.intPer = intPer;
	}

	@Override
	public String toString() {
		return "Periodiques [intVol=" + intVol + ", intPer=" + intPer + ", strNumDoc=" + strNumDoc + ", strTitre="
				+ strTitre + ", strDate=" + strDate + ", strPretEtat=" + strPretEtat + "]";
	}

	public String getIntVol() {
		return intVol;
	}
	public void setIntVol(String intVol) {
		this.intVol = intVol;
	}

	public String getIntPer() {
		return intPer;
	}
	public void setIntPer(String intPer) {
		this.intPer = intPer;
	}
}
