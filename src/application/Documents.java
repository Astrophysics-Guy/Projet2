package application;

import java.io.Serializable;

public class Documents implements Serializable {
	private static final long serialVersionUID = 1L;
	String strNumDoc, strTitre, strDate; //////////////// private  
	String strPretEtat; /////////////// private 
	
	public Documents(String strNumDoc, String strTitre, String strDate, String strPretEtat) {
		// TODO Auto-generated constructor stub
		this.strNumDoc = strNumDoc;
		this.strTitre = strTitre;
		this.strPretEtat = strPretEtat;
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

	public String getStrPret() {
		return strPretEtat;
	}
	public void setStrPret(String strPretEtat) {
		this.strPretEtat = strPretEtat;
	}
}
