package application;

import java.io.Serializable;

public class Livres extends Documents implements Serializable {
	private static final long serialVersionUID = 1L;
	private String strNomAuteur;
	
	public Livres(String strNumDoc, String strTitre, String strDate, String strPretEtat, String strNomAuteur) {
		super(strNumDoc, strTitre, strDate, strPretEtat);
		
		this.strNomAuteur = strNomAuteur;
	}

	@Override
	public String toString() {
		return "Livres [strNomAuteur=" + strNomAuteur + ", strNumDoc=" + strNumDoc + ", strTitre=" + strTitre
				+ ", strDate=" + strDate + ", strPretEtat=" + strPretEtat + "]";
	}

	public String getStrNomAuteur() {
		return strNomAuteur;
	}
	public void setStrNomAuteur(String strNomAuteur) {
		this.strNomAuteur = strNomAuteur;
	}
}
