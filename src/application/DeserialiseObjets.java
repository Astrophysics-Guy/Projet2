package application;

import java.io.*;
import java.util.ArrayList;

public class DeserialiseObjets {
	private static final String strNomFichier = "Donnees" + System.getProperty("file.separator"); // j utilise ubuntu

	private static ArrayList<Preposes> arrPreposes = new ArrayList<>();
	private static ArrayList<Adherents> arrAdherents = new ArrayList<>();

	private static ArrayList<DVD> arrDVD = new ArrayList<>();
	private static ArrayList<Livres> arrLivres = new ArrayList<>();
	private static ArrayList<Periodiques> arrPeriodiques = new ArrayList<>();

	private final static String strFichierPreposes = strNomFichier + "preposes.ser";
	private final static String strFichierAdherents = strNomFichier + "adherents.ser";

	private final static String strFichierDVD = strNomFichier + "dvd.ser";
	private final static String strFichierLivre = strNomFichier + "livre.ser";
	private final static String strFichierPeriodique = strNomFichier + "periodique.ser";

	public static ArrayList<Preposes> getPreposes() {
		if (new File(strFichierPreposes).exists()) {
			FileInputStream fichier = null;
			try {
				fichier = new FileInputStream(strFichierPreposes);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ObjectInputStream is = null;
			try {
				is = new ObjectInputStream(fichier);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Preposes preposes;
			try {
				while((preposes = (Preposes) is.readObject()) != null) {
					arrPreposes.add(preposes);
				}
			}
			catch (IOException e) {
				// fin de fichier ou fichier introuvable.

			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			SerialiseObjets.serialisePersonnes();
			DeserialiseObjets.getPreposes();
		}
		
		//for (Preposes p : arrPreposes) System.out.println(p);
		
		return arrPreposes;
	}
	public static ArrayList<Adherents> getAdherents() {
		if (new File(strFichierAdherents).exists()) {
			FileInputStream fichier = null;
			try {
				fichier = new FileInputStream(strFichierAdherents);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ObjectInputStream is = null;
			try {
				is = new ObjectInputStream(fichier);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Adherents adherents;
			try {
				while((adherents = (Adherents) is.readObject()) != null) {
					arrAdherents.add(adherents);
				}
			}
			catch (IOException e) {
				// fin de fichier ou fichier introuvable.

			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			SerialiseObjets.serialisePersonnes();
			DeserialiseObjets.getAdherents();
		}
		//for (Adherents a : arrAdherents) System.out.println(a);
		
		return arrAdherents;
	}

	public static ArrayList<DVD> getDVD() {
		if (new File(strFichierDVD).exists()) {
			FileInputStream fichier = null;
			try {
				fichier = new FileInputStream(strFichierDVD);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ObjectInputStream is = null;
			try {
				is = new ObjectInputStream(fichier);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			DVD dvd;
			try {
				while((dvd = (DVD) is.readObject()) != null) {
					arrDVD.add(dvd);
				}
			}
			catch (IOException e) {
				// fin de fichier ou fichier introuvable.

			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*else {
			SerialiseObjets.serialiseDVD();
			DeserialiseObjets.getAdherents();
		}*/
		for (DVD a : arrDVD) System.out.println(a);

		return arrDVD;
	}
}
