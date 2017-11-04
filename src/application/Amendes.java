package application;

public class Amendes {
	String strNo, strEtat;
	int intMontant;
	public Amendes(String strNo, String strEtat, int intMontant) {
		super();
		this.strNo = strNo;
		this.strEtat = strEtat;
		this.intMontant = intMontant;
	}
	
	public String getStrNo() {
		return strNo;
	}
	public void setStrNo(String strNo) {
		this.strNo = strNo;
	}
	
	public String getStrEtat() {
		return strEtat;
	}
	public void setStrEtat(String strEtat) {
		this.strEtat = strEtat;
	}
	
	public int getIntMontant() {
		return intMontant;
	}
	public void setIntMontant(int intMontant) {
		this.intMontant = intMontant;
	} 
}
