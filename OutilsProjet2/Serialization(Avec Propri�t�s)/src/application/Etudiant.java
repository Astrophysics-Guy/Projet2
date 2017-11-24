package application;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//https://stackoverflow.com/questions/18791566/notserializableexception-on-simplelistproperty
import java.io.Serializable;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Etudiant implements Serializable{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	transient   private SimpleIntegerProperty numDA;
	transient   private  SimpleStringProperty nom;
	transient 	private  SimpleStringProperty prenom;
	transient   private  SimpleDoubleProperty moyenne;
	private int x;
		
public  Etudiant(int numDA, String nom, String prenom, double moyenne,int x) {
		
		this.numDA = new SimpleIntegerProperty(numDA);
		this.nom = new SimpleStringProperty (nom);
		this.prenom = new SimpleStringProperty(prenom);
		this.moyenne = new SimpleDoubleProperty(moyenne);
		this.x = x;
	}


public final SimpleIntegerProperty numDAProperty() {
	return this.numDA;
}

public final int getNumDA() {
	return this.numDAProperty().get();
}

public final void setNumDA(final int numDA) {
	this.numDAProperty().set(numDA);
}

public final SimpleStringProperty nomProperty() {
	return this.nom;
}



public final java.lang.String getNom() {
	return this.nomProperty().get();
}



public final void setNom(final java.lang.String nom) {
	this.nomProperty().set(nom);
}



public final SimpleStringProperty prenomProperty() {
	return this.prenom;
}



public final java.lang.String getPrenom() {
	return this.prenomProperty().get();
}

public final void setPrenom(final java.lang.String prenom) {
	this.prenomProperty().set(prenom);
}

public final SimpleDoubleProperty moyenneProperty() {
	return this.moyenne;
}

public final double getMoyenne() {
	return this.moyenneProperty().get();
}

public final void setMoyenne(final double moyenne) {
	this.moyenneProperty().set(moyenne);
}

@Override
public String toString() {
	return "Etudiant [numDA=" + numDA.getValue() + ", nom=" + nom.getValueSafe() + ", prenom=" + prenom.getValueSafe() + ", moyenne=" + moyenne.getValue() + ", x=" + x
			+ "]";
}

public int getX() {
	return x;
}

public void setX(int x) {
	this.x = x;
}
public static void main(String[] args) {
	Etudiant etu = new Etudiant(1, "nom", null, 20, 30);
	System.out.println(etu);
}
private  void writeObject(ObjectOutputStream os)  {
	

	// TODO Auto-generated method stub
	try {
		os.defaultWriteObject();
		os.writeInt(numDA.intValue());
		os.writeUTF(nom.getValueSafe());
		os.writeUTF(prenom.getValueSafe());
		os.writeDouble(moyenne.getValue());
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		 
}


private void  readObject(final ObjectInputStream is){
	
	
// initialisation des propriétés
   numDA = new SimpleIntegerProperty();
	nom = new SimpleStringProperty ();
	prenom = new SimpleStringProperty();
	moyenne = new SimpleDoubleProperty();
		try {
			is.defaultReadObject();;
			numDA.set(is.readInt());
			nom.set(is.readUTF());
			prenom.set(is.readUTF());
			moyenne.set(is.readDouble());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}	
}
