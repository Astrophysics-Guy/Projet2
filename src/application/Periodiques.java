package application;

public class Periodiques extends Documents {
	int intVol, intPer;
	
	public Periodiques(String strNumDoc, String strTitre, String strDate, int intPret, int intVol, int intPer) {
		super(strNumDoc, strTitre, strDate,  intPret);
		
		this.intVol = intVol;
		this.intPer = intPer;
	}

	@Override
	public String toString() {
		return "Periodiques [intVol=" + intVol + ", intPer=" + intPer + ", strNumDoc=" + strNumDoc + ", strTitre="
				+ strTitre + ", strDate=" + strDate + ", intPret=" + intPretEtat + "]";
	}

	public int getIntVol() {
		return intVol;
	}
	public void setIntVol(int intVol) {
		this.intVol = intVol;
	}

	public int getIntPer() {
		return intPer;
	}
	public void setIntPer(int intPer) {
		this.intPer = intPer;
	}
}
