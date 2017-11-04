package application;

public class Documents {
	String strNumDoc, strTitre, strDate;
	int intPretEtat;
	
	public Documents(String strNumDoc, String strTitre, String strDate, int intPretEtat) {
		// TODO Auto-generated constructor stub
		this.strNumDoc = strNumDoc;
		this.strTitre = strTitre;
		this.intPretEtat = intPretEtat;
		this.strDate = strDate;
	}

	public String getStrNumDoc() {
		return strNumDoc;
	}
	public void setStrNumDoc(String strNumDoc) {
		this.strNumDoc = strNumDoc;
	}

	public String getStrTitre() {
		return strTitre;
	}
	public void setStrTitre(String strTitre) {
		this.strTitre = strTitre;
	}

	public String getStrDate() {
		return strDate;
	}
	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public int getIntPret() {
		return intPretEtat;
	}
	public void setIntPret(int intPretEtat) {
		this.intPretEtat = intPretEtat;
	}
}
