package application;

public class DVD extends Documents {
	String strNomRealisateur;
	int intNbDisques;
	
	public DVD(String strNumDoc, String strTitre, String strDate, int intPret, int intNbDisques, String strNomRealisateur) {
		super(strNumDoc, strTitre, strDate, intPret);
			
		this.strNomRealisateur = strNomRealisateur;
		this.intNbDisques = intNbDisques;
	}
	
	@Override
	public String toString() {
		return "DVD [strNomRealisateur=" + strNomRealisateur + ", intNbDisques=" + intNbDisques + ", strNumDoc="
				+ strNumDoc + ", strTitre=" + strTitre + ", strDate=" + strDate + ", intPret=" + intPretEtat + "]";
	}
	
	public String getStrNomRealisateur() {
		return strNomRealisateur;
	}
	public void setStrNomRealisateur(String strNomRealisateur) {
		this.strNomRealisateur = strNomRealisateur;
	}

	public int getIntNbDisques() {
		return intNbDisques;
	}
	public void setIntNbDisques(int intNbDisques) {
		this.intNbDisques = intNbDisques;
	}
}
