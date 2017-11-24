package application;

import java.io.Serializable;

public class DVD extends Documents implements Serializable {
	private static final long serialVersionUID = 1L;
	private String strNomRealisateur;
	private String strNbDisques;
	
	public DVD(String strNumDoc, String strTitre, String strDate, String strPret, String strNbDisques, String strNomRealisateur) {
		super(strNumDoc, strTitre, strDate, strPret);
			
		this.strNomRealisateur = strNomRealisateur;
		this.strNbDisques = strNbDisques;
	}
	
	@Override
	public String toString() {
		return "DVD [strNomRealisateur=" + strNomRealisateur + ", strNbDisques=" + strNbDisques + ", strNumDoc="
				+ strNumDoc + ", strTitre=" + strTitre + ", strDate=" + strDate + ", strPret=" + strPretEtat + "]";
	}
	
	public String getStrNomRealisateur() {
		return strNomRealisateur;
	}
	public void setStrNomRealisateur(String strNomRealisateur) {
		this.strNomRealisateur = strNomRealisateur;
	}

	public String getStrNbDisques() {
		return strNbDisques;
	}
	public void setStrNbDisques(String strNbDisques) {
		this.strNbDisques = strNbDisques;
	}
}
