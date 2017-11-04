package application;

public class Adherents extends Personnes {
	String strNoAdherent;

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
}
