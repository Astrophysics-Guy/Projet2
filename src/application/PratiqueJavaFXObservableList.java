package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class PratiqueJavaFXObservableList {
	public static void main(String[] args) {
		ArrayList<String> lisDoc = new ArrayList<>();
		ObservableList<String> listeObservable = FXCollections.observableList(lisDoc);
		
		listeObservable.addListener(new ListChangeListener<String>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends String> c) {
				// TODO Auto-generated method stub
				System.out.println("Modification detectee: " + c);
				while(c.next()) {
					System.out.println("Ajout? " + c.wasAdded());
					System.out.println("Suppression? " + c.wasRemoved());
					//for (String i : c.getAddedSubList()) System.out.println(i);
					for (String j : c.getRemoved()) System.out.println(j);
					System.out.println();
				}
			}
		});
		
		listeObservable.add("Chaine1");
		lisDoc.add("Chaine2");
		listeObservable.add("Chaine3");
		
		listeObservable.remove("Chaine1");
		
		System.out.println("lisDoc");
		for (String s : lisDoc) System.out.println(s);
		System.out.println();
		System.out.println("listeObservable");
		for (String s : listeObservable) System.out.println(s);
	}
}
