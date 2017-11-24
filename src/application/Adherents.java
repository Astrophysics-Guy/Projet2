package application;

import java.io.Serializable;

public class Adherents extends Personnes implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String strNoAdherent;

	public Adherents(String strNom, String strPrenom, String strAdresse, String strTel, String strNoAdherent) {
		super(strNom, strPrenom, strAdresse, strTel);
		
		this.strNoAdherent = strNoAdherent;
	}

	public String getStrNoAdherent() {
		return strNoAdherent;
	}
	public void setStrNoAdherent(String strNoAdherent) {
		this.strNoAdherent = strNoAdherent;
	}

	@Override
	public String toString() {
		return "Adherents [strNoAdherent=" + strNoAdherent + "]";
	} 
}
