package application;

public class Pret {
	String strPret, strDateDuJour, strDateRetourPrevue, strDateEffectiveRetour;

	public Pret(String strPret, String strDateDuJour, String strDateRetourPrevue, String strDateEffectiveRetour) {
		this.strPret = strPret;
		this.strDateDuJour = strDateDuJour;
		this.strDateRetourPrevue = strDateRetourPrevue;
		this.strDateEffectiveRetour = strDateEffectiveRetour;
	}

	public String getStrPret() {
		return strPret;
	}
	public void setStrPret(String strPret) {
		this.strPret = strPret;
	}

	public String getStrDateDuJour() {
		return strDateDuJour;
	}
	public void setStrDateDuJour(String strDateDuJour) {
		this.strDateDuJour = strDateDuJour;
	}

	public String getStrDateRetourPrevue() {
		return strDateRetourPrevue;
	}
	public void setStrDateRetourPrevue(String strDateRetourPrevue) {
		this.strDateRetourPrevue = strDateRetourPrevue;
	}

	public String getStrDateEffectiveRetour() {
		return strDateEffectiveRetour;
	}
	public void setStrDateEffectiveRetour(String strDateEffectiveRetour) {
		this.strDateEffectiveRetour = strDateEffectiveRetour;
	} 
}
