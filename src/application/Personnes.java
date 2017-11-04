package application;

public class Personnes {
	String strNom, strPrenom, strAdresse, strTel;

	public Personnes(String strNom, String strPrenom, String strAdresse, String strTel) {
		this.strNom = strNom;
		this.strPrenom = strPrenom;
		this.strAdresse = strAdresse;
		this.strTel = strTel;
	}

	public String getNom() {
		return strNom;
	}
	public void setNom(String strNom) {
		this.strNom = strNom;
	}

	public String getPrenom() {
		return strPrenom;
	}
	public void setPrenom(String strPrenom) {
		this.strPrenom = strPrenom;
	}

	public String getAdresse() {
		return strAdresse;
	}
	public void setAdresse(String strAdresse) {
		this.strAdresse = strAdresse;
	}
	
	public String getTel() {
		return strTel;
	}
	public void setTel(String strTel) {
		this.strTel = strTel;
	} 
}
