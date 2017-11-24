import java.io.*;
public class DeserializationEtudiant {


	public static void main(String[] args) throws IOException {
		 boolean fin = false; 
		 
		 
		 FileInputStream fichier = new FileInputStream("etudiant.ser"); 
		    
			ObjectInputStream is = new ObjectInputStream(fichier); 
			Etudiant etu;
		
		try {
			  
		while(	(etu = (Etudiant) is.readObject())!=null){
			System.out.println(etu);
		 
		}
		}
			catch (IOException e) { 
				
			   // fin de fichier ou fichier introuvable.  
			    
			   
			} 
	   
			catch (ClassNotFoundException e) { 
		     
			e.printStackTrace(); 
			   
			} 
		}
	
	}


