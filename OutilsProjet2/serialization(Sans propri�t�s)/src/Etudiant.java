import java.io.*;
public class Etudiant implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 9131634211976043092L;
private int numDa;
private String nom;
private String prenom;
private double moyenne;

public Etudiant(int numDa, String nom, String prenom, double moyenne) {
this.numDa = numDa;
this.nom= nom;
this.prenom= prenom;
this.moyenne = moyenne;

}
public int getNumDA() {
	return numDa;

}
public String getNom() {
	return nom;

}
public String getPreNom() {
	return prenom;

}
public double getMoyenne() {
	return moyenne;

}
public void setNumDa(int numDa){
	this.numDa= numDa;
}
public void setNom(String  nom){
	this.nom = nom;
}
public void setPreNom(String  prenom){
	this.prenom = prenom;
}
public void setMoyenne(double  moyenne){
	this.moyenne= moyenne;
}

public String toString() {
	
	return ("numDa: " + numDa+ "\nnom: "+ nom + "\nPrenom: " + prenom+ "\nMoyenne: "+ moyenne) ;
	
}

}


