package application;

public class Livres extends Documents {
	String strNomAuteur;
	
	public Livres(String strNumDoc, String strTitre, String strDate, int intPret, String strNomAuteur) {
		super(strNumDoc, strTitre, strDate, intPret);
		
		this.strNomAuteur = strNomAuteur;
	}

	@Override
	public String toString() {
		return "Livres [strNomAuteur=" + strNomAuteur + ", strNumDoc=" + strNumDoc + ", strTitre=" + strTitre
				+ ", strDate=" + strDate + ", intPret=" + intPretEtat + "]";
	}

	public String getStrNomAuteur() {
		return strNomAuteur;
	}
	public void setStrNomAuteur(String strNomAuteur) {
		this.strNomAuteur = strNomAuteur;
	}
}
