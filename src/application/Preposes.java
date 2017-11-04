package application;

public class Preposes extends Personnes {
	String strNoEmploye, strMotDePasse;

	public Preposes(String strNom, String strPrenom, String strAdresse, String strTel, String strNoEmploye, String strMotDePasse) {
		super(strNom, strPrenom, strAdresse, strTel);
		
		this.strNoEmploye = strNoEmploye;
		this.strMotDePasse = strMotDePasse;
	}

	public String getStrNoEmploye() {
		return strNoEmploye;
	}
	public void setStrNoEmploye(String strNoEmploye) {
		this.strNoEmploye = strNoEmploye;
	}

	public String getStrMotDePasse() {
		return strMotDePasse;
	}
	public void setStrMotDePasse(String strMotDePasse) {
		this.strMotDePasse = strMotDePasse;
	} 
}
