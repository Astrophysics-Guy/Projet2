package application;

import java.io.Serializable;

public class Periodiques extends Documents implements Serializable {
	private static final long serialVersionUID = 1L;
	private String strVol, strPer;
	
	public Periodiques(String strNumDoc, String strTitre, String strDate, String strPretEtat, String strVol, String strPer) {
		super(strNumDoc, strTitre, strDate,  strPretEtat);
		
		this.strVol = strVol;
		this.strPer = strPer;
	}

	@Override
	public String toString() {
		return "Periodiques [strVol=" + strVol + ", strPer=" + strPer + ", strNumDoc=" + strNumDoc + ", strTitre="
				+ strTitre + ", strDate=" + strDate + ", strPretEtat=" + strPretEtat + "]";
	}

	public String getStrVol() {
		return strVol;
	}
	public void setStrVol(String strVol) {
		this.strVol = strVol;
	}

	public String getStrPer() {
		return strPer;
	}
	public void setStrPer(String strPer) {
		this.strPer = strPer;
	}
}
