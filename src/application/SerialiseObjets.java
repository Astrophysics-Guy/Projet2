package application;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerialiseObjets {
	private static final String strNomFichier = "Donnees" + System.getProperty("file.separator"); // j utilise ubuntu

	public static void serialisePersonnes() {
		Preposes preposeGuelleh = new Preposes("Guelleh", "Mohamed H.", "44552 rue Basswood", "5145546458", "Guelleh", "10101997"), preposeIsmail = new Preposes("Ismail", "El-Katthab", "4326554 rue j«ndhfda", "1511354879", "Ismail", "123");
		
		Adherents adherents = new Adherents("Guelleh", "Mohamed", "10515 hsdfghv ", "5176184597", "GUEM97");
		
		try { 
			ObjectOutputStream osPreposes = new ObjectOutputStream(new FileOutputStream(strNomFichier + "preposes.ser")), osAdherents = new ObjectOutputStream(new FileOutputStream(strNomFichier + "adherents.ser"));
			
			osPreposes.writeObject(preposeGuelleh);
			osPreposes.writeObject(preposeIsmail);
			osPreposes.close();
			
			osAdherents.writeObject(adherents);
			osAdherents.close();
		} 
		catch (IOException e) { 
			e.printStackTrace(); 
		} 
	}

	public SerialiseObjets(DVD dvd) {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(strNomFichier + "dvd.ser"));

			objectOutputStream.writeObject(dvd);
			objectOutputStream.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public SerialiseObjets(Livres livres) {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(strNomFichier + "livre.ser"));

			objectOutputStream.writeObject(livres);
			objectOutputStream.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public SerialiseObjets(Periodiques periodiques) {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(strNomFichier + "periodique.ser"));

			objectOutputStream.writeObject(periodiques);
			objectOutputStream.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*public static void serialiseDVD() {

	}*/
}
