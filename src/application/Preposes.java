package application;

import java.io.Serializable;

public class Preposes extends Personnes implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String strNoEmploye, strMotDePasse;

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

	//@Override
	public String toString() {
		return "Preposes [strNoEmploye=" + strNoEmploye + ", strMotDePasse=" + strMotDePasse + "]";
	} 
}
