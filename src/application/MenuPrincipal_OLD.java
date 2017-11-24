package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class MenuPrincipal_OLD extends Stage {
	// Fichier ressource
	private final String strNomFichier = "Donnees" + System.getProperty("file.separator"); // j utilise ubuntu

	// Pour les documents
	private ArrayList<DVD> arrDVD = new ArrayList<>();
	private ArrayList<Livres> arrLivres = new ArrayList<>();
	private ArrayList<Periodiques> arrPeriodiques = new ArrayList<>();
	
	// Pour la liste d'adherents qui peuvent se connecter
	private ArrayList<String> arrLstAdherentAjoute, arrLstAdherentDisponible; // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Pas utiliser

	// Pour l'interface
	private MenuBar menuBar;
	private Menu menuDeconnexion, menuAide;
	private MenuItem menuItemDeconnexion, menuItemAide;
	
	private VBox vBox;
	private Button btnDeplacerSource, btnDeplacerDestination;
	private GridPane gridPaneEmploye;
	private Group group;
	
	private Text textTitre, textEmploye, textMotDePasse, textAdherent;
	private TextField textFieldEmploye, textFieldAdherent;
	private PasswordField passwordFieldEmploye;
	 
	// Liste Observable
	private ListView<String> lstViewEmployeSource, lstViewEmployeDestination;

	// le stage du login
	private Stage primaryStage;
	private Scene scene; ///////////////////////////////////////////////////////////

	/*MenuPrincipal_OLD(TextField textFieldEmploye, Stage primaryStage) { // qd l'adherent se connecte et veut ajouter/enlever des adherents
		this.textFieldEmploye = textFieldEmploye;
		this.primaryStage = primaryStage;

		this.arrLstAdherentAjoute = arrLstAdherentAjoute;
		this.arrLstAdherentDisponible = arrLstAdherentDisponible;
		
		nouvellePersonne(false);
		
		menuPrepose(true);
	}*/
	MenuPrincipal_OLD(TextField textFieldEmploye, Stage primaryStage) { // qd le prepose se connecte et veut afficher la table view
		this.textFieldEmploye = textFieldEmploye;
		this.primaryStage = primaryStage;
		
		nouvellePersonne(true);

		menuPrepose(false);		
	}
	MenuPrincipal_OLD(Stage primaryStage, TextField textFieldAdherent) { // qd l'adherent se connecte
		this.textAdherent = textAdherent;
		this.primaryStage = primaryStage;
		
		nouvellePersonne(true);
		
		menuAdherent();
	}



	private Font font(int intSize) {
		return Font.font("Serif", FontWeight.BOLD, intSize);
	}

	private void nouvellePersonne(boolean blnLire) { // Fait appel a chaque fois que l'utilisateur se connecte
		if (blnLire) {
			arrDVD.clear();
			arrLivres.clear();
			arrPeriodiques.clear();
			
			lireDVD();
			lireLivres();
			lirePeriodiques();
		}
		else {
			lstViewEmployeSource = null;
			lstViewEmployeDestination = null;
		}
		
		//arrLstAdherentAjoute.clear(); // Il ne faut pas l'effacer, pcq on en a besoin pour se connecter dans le compte d'adherent
		
		//vBox.getChildren().clear(); // removeAll() doesnt work
	}

	private void menuPrincipal() {
		BorderPane root = new BorderPane();
		scene = new Scene(root,900,600); // 1.5 aspect ratio
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		// Bar
		menuBar = new MenuBar();
		menuDeconnexion = new Menu("_Deconnexion");
		menuAide = new Menu("_Aide");

		menuAide.setMnemonicParsing(true);
		menuDeconnexion.setMnemonicParsing(true);

		// Items
		menuItemDeconnexion = new MenuItem("D_econnexion");
		menuItemAide = new MenuItem("A_ide");

		// Ajout des menus
		menuDeconnexion.getItems().add(menuItemDeconnexion);
		menuAide.getItems().add(menuItemAide);
		menuBar.getMenus().addAll(menuDeconnexion, menuAide);

		// vBox
		vBox = new VBox();
		vBox.setSpacing(20);
		vBox.setAlignment(Pos.TOP_LEFT);

		VBox vBoxMain = new VBox();
		vBoxMain.setPadding(new Insets(10));
		vBoxMain.setAlignment(Pos.TOP_CENTER);
		vBoxMain.setSpacing(50);

		textTitre.setFont(font(30));
		textTitre.setTextAlignment(TextAlignment.CENTER);

		vBoxMain.getChildren().addAll(textTitre, vBox);

		root.setTop(menuBar);
		root.setCenter(vBoxMain);

		this.sizeToScene();
		//primaryStage.setResizable(false);
		//this.setMaximized(true);
		//primaryStage.setFullScreen(true); // non pour mtn
		this.setScene(scene);

		this.show();

		/////////////////////////////////////////////////////////////////////////////
		menuItemAide.setOnAction(event -> {

		});
	}
	private void menuPrepose(boolean blnLstAdherent) {
		textTitre = new Text("Connecte en tant que prepose " + textFieldEmploye.getText());
		
		menuPrincipal();
		
		menuItemDeconnexion.setOnAction(event -> { // ne pas oublier de passer arrLstAdherentAjoute au Login_OLD
			deconnexion(true); // deconnexion prepose
		});

		if (blnLstAdherent) methodeLstViewEmploye();
		else methodeTableViewEmploye();

		if (blnLstAdherent) this.setTitle("Menu Prepose --> Ajouter et/ou enlever des comptes adherents");
		else this.setTitle("Menu Prepose --> Consulter la liste");
	}
	private void menuAdherent() {
		textTitre = new Text("Connecte en tant que prepose " + textAdherent.getText());
		
		menuPrincipal();
		
		menuItemDeconnexion.setOnAction(event -> { // ne pas oublier de passer arrLstAdherentAjoute au Login_OLD
			deconnexion(false); // deconnexion adherent
		});

		//methodeTableViewAdherent(); // A completer
		methodeTableViewEmploye();

		this.setTitle("Menu Adherent --> Consulter la liste");
	}
	
	private String retourneNomMenuItem(String strNomMenuItem) { // Remplace _ du string et retourne par exemple Sport au lieu de _Sport
		return strNomMenuItem.replaceAll("_", "");
	}
	
	/*private void connexion(boolean blnPrepose) { // Quand la personne se connecte
		/*menuDeconnexion = new Menu("_Deconnexion");
		menuItemDeconnexion = new MenuItem("De_connexion");
		
		menuDeconnexion.setMnemonicParsing(true);
		
		menuItemDeconnexion.setOnAction(event -> {
            // TODO Auto-generated method stub
            deconnexion();
        });
		
		menuDeconnexion.getItems().add(menuItemDeconnexion);
		menuBar.getMenus().add(menuDeconnexion);
		
		if (blnPrepose) { // Quand l'employe se connecte
			methodeLstViewEmploye();
			methodeTableViewEmploye();
		} 
		else { // Quand l'adherent se connecte
			methodeTableViewAdherent();
		}
	}*/
	
	private void deconnexion(boolean blnPrepose) { // Quand la personne se deconnecte ou change de personne
		if (blnPrepose) { // animation de deconnexion pour prepose
			this.close();

			primaryStage.show();
		}
		else { // animation de deconnexion pour adherent
			this.close();

			primaryStage.show();
		}
	}
	
	private void methodeLstViewEmploye() { // Pour permettre au prepose d'ajouter et de supprimer les adherents, methode connexion
		btnDeplacerSource = new Button("Ajouter >>>");
		btnDeplacerDestination = new Button("<<< Retirer");

		if (arrLstAdherentAjoute.size() == 0) btnDeplacerDestination.setDisable(true);
		else btnDeplacerDestination.setDisable(false);
		
		VBox vBoxButton = new VBox();
		vBoxButton.setSpacing(10);
		vBoxButton.setAlignment(Pos.TOP_LEFT);
		vBoxButton.getChildren().addAll(btnDeplacerDestination, btnDeplacerSource);
		
		gridPaneEmploye = new GridPane();
		gridPaneEmploye.setAlignment(Pos.BOTTOM_RIGHT);
		gridPaneEmploye.setPadding(new Insets(5));
		gridPaneEmploye.setHgap(10);
		gridPaneEmploye.setVgap(10);

		ObservableList<String> listeObservable = FXCollections.observableArrayList(arrLstAdherentAjoute);
		Collections.sort(arrLstAdherentAjoute);

		lstViewEmployeSource = new ListView<>(FXCollections.observableArrayList(arrLstAdherentDisponible)); 
		lstViewEmployeDestination = new ListView<>(listeObservable);

		lstViewEmployeSource.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		lstViewEmployeDestination.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		Label lblSource = new Label("Adherent disponible"), lblDestination = new Label("Adherent ajoute");

		lblSource.setAlignment(Pos.CENTER); // Ca ne centre pas
		lblSource.setTextAlignment(TextAlignment.CENTER); // Ca ne centre pas
		lblSource.setFont(font(15));

		lblDestination.setAlignment(Pos.CENTER); // Ca ne centre pas
		lblDestination.setTextAlignment(TextAlignment.CENTER); // Ca ne centre pas
		lblDestination.setFont(font(15));

		ColumnConstraints colonneSource = new ColumnConstraints(lblSource.getMinWidth(), lblSource.getPrefWidth(), Double.MAX_VALUE), colonneButton = new ColumnConstraints(110), colonneDestination = new ColumnConstraints(lblDestination.getMinWidth(), lblDestination.getPrefWidth(), Double.MAX_VALUE);

        gridPaneEmploye.getColumnConstraints().addAll(colonneSource, colonneButton, colonneDestination);
        
        //colonneSource.setHgrow(Priority.ALWAYS);
        //colonneDestination.setHgrow(Priority.ALWAYS);
        
        btnDeplacerSource.setOnAction(event -> {
            // TODO Auto-generated method stub
            ObservableList<String> listeSelectionnee = lstViewEmployeSource.getSelectionModel().getSelectedItems();

            if (listeSelectionnee != null) {
				lstViewEmployeDestination.getItems().addAll(listeSelectionnee);
				lstViewEmployeSource.getItems().removeAll(listeSelectionnee);

				lstViewEmployeSource.getSelectionModel().clearSelection();

				if (lstViewEmployeSource.getItems().size() == 0) btnDeplacerSource.setDisable(true);
				else btnDeplacerSource.setDisable(false);

				if (lstViewEmployeDestination.getItems().size() != 0) btnDeplacerDestination.setDisable(false);
			}
        });
        btnDeplacerDestination.setOnAction(event -> {
            // TODO Auto-generated method stub
            ObservableList<String> listeSelectionnee = lstViewEmployeDestination.getSelectionModel().getSelectedItems();

            if (listeSelectionnee != null) {
				lstViewEmployeSource.getItems().addAll(listeSelectionnee);
				lstViewEmployeDestination.getItems().removeAll(listeSelectionnee);

				lstViewEmployeDestination.getSelectionModel().clearSelection();

				if (lstViewEmployeDestination.getItems().size() == 0) btnDeplacerDestination.setDisable(true);
				else btnDeplacerDestination.setDisable(false);

				if (lstViewEmployeSource.getItems().size() != 0) btnDeplacerSource.setDisable(false);
			}
        });

		// Ajoute ou remove les adherents de la arrLstAdherentAjoute
		listeObservable.addListener((ListChangeListener<String>) c -> {
            // TODO Auto-generated method stub
            while (c.next()) {
                for (String strLstAdherentAdded : c.getAddedSubList()) arrLstAdherentAjoute.add(strLstAdherentAdded); // ajoute la valeur que contient c dans arrLstAdherentAjoute
                for (String strLstAdherentRemoved : c.getRemoved()) arrLstAdherentAjoute.remove(strLstAdherentRemoved);

                arrLstAdherentAjoute = (ArrayList<String>) arrLstAdherentAjoute.stream().distinct().collect(Collectors.toList()); // remove duplicates, ex Guelleh Guelleh devient dans la array Guelleh. C'est pour accelerer le temps de comparaison lorsque l'adherent se connecte et eliminer les duplicates
                Collections.sort(arrLstAdherentAjoute); // trie le arraylist

                /*System.out.println("arrLstAdherentAjoute");
                for (String s : arrLstAdherentAjoute) System.out.println(s);
                System.out.println();*/
            }
        });
        
        gridPaneEmploye.add(lblSource, 0, 0);
        gridPaneEmploye.add(lstViewEmployeSource, 0, 1);
        gridPaneEmploye.add(vBoxButton, 1, 1);
        gridPaneEmploye.add(lblDestination, 2, 0);
        gridPaneEmploye.add(lstViewEmployeDestination, 2, 1);
        
        gridPaneEmploye.setAlignment(Pos.CENTER);
        
        vBox.getChildren().add(gridPaneEmploye);
	}
	private void methodeTableViewEmploye() { // Pour permettre au prepose de voir la table, methode connexion ////////////////////////////////////////////////
		ObservableList<Documents> observableList = FXCollections.observableArrayList(arrLivres);

		group = new Group();

		Text text = new Text();
		text.setText("Liste des livres");
		text.setFont(font(15));
		//borderPane.prefHeightProperty().bind(70);
				
		VBox vBoxGroup = new VBox();
		vBoxGroup.setAlignment(Pos.BOTTOM_LEFT);

		HBox hBoxGroup = new HBox();

		TableView<Documents> tableView = new TableView<>();

		TableColumn<Documents, Integer> colonneNumDoc = new TableColumn<>("No Document"), colonneTitre = new TableColumn<>("Titre"), colonneDate = new TableColumn<>("Date"), colonneEtat = new TableColumn<>("Etat"), colonneAuteur = new TableColumn<>("Nom Auteur");

		tableView.getColumns().addAll(colonneNumDoc, colonneTitre, colonneDate, colonneEtat, colonneAuteur);

		colonneNumDoc.setPrefWidth(200);
		colonneTitre.setPrefWidth(200);
		colonneDate.setPrefWidth(200);
		colonneEtat.setPrefWidth(200);
		colonneAuteur.setPrefWidth(200);
		//colonneAuteur.setMaxWidth(colonneAuteur.getPrefWidth());

		colonneNumDoc.setCellValueFactory(new PropertyValueFactory<>("strNumDoc"));
		colonneTitre.setCellValueFactory(new PropertyValueFactory<>("strTitre"));
		colonneDate.setCellValueFactory(new PropertyValueFactory<>("strDate"));
		colonneEtat.setCellValueFactory(new PropertyValueFactory<>("intPretEtat"));
		colonneAuteur.setCellValueFactory(new PropertyValueFactory<>("strNomAuteur"));


		TextField textFieldNoDocument = new TextField(), textFieldTitre = new TextField(), textFieldDate = new TextField(), textFieldEtat = new TextField(), textFieldAuteur = new TextField();

		textFieldNoDocument.setPromptText("No Document");
		textFieldTitre.setPromptText("Titre");
		textFieldDate.setPromptText("Date");
		textFieldEtat.setPromptText("Etat");
		textFieldAuteur.setPromptText("Nom Auteur");
		tableView.setItems(observableList);

		Button btnAjouter = new Button("Ajouter");
		btnAjouter.setOnAction(event -> {

        });

		hBoxGroup.getChildren().addAll(textFieldNoDocument, textFieldTitre, textFieldDate, textFieldEtat, textFieldAuteur, btnAjouter);
		vBoxGroup.getChildren().addAll(text, tableView, hBoxGroup);

		group.getChildren().add(vBoxGroup);
		vBox.getChildren().addAll(group);
	}
	private void methodeTableViewAdherent() { // Pour permettre a l'adherent de voir la table, les adherents, methode connexion
		
	}

	private void lireDVD() { // lit le fichier dvd
		BufferedReader brFichier = null;

		try {
			brFichier = new BufferedReader(new FileReader(strNomFichier + "DVD.txt"));
		}
		catch (FileNotFoundException e){

			e.printStackTrace();
		}

		try {
			String strLigne;

			while ((strLigne = brFichier.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(strLigne, ",");
				arrDVD.add(new DVD(st.nextToken().trim().toString(), st.nextToken().trim().toString(), st.nextToken().trim().toString(), "0", st.nextToken().trim().toString(), st.nextToken().trim().toString()));
			}
			//for (int i = 0; i < arrDVD.size(); i++) System.out.println(arrDVD.get(i).toString());
		}
		catch (Exception e) {
			//System.out.println(e.toString());
			e.printStackTrace();
		}
	}

	private void lireLivres() { // lit le fichier livre
		BufferedReader brFichier = null;

		try {
			brFichier = new BufferedReader(new FileReader(strNomFichier + "Livres.txt"));
		}
		catch (FileNotFoundException e){

			e.printStackTrace();
		}

		try {
			String strLigne;

			while ((strLigne = brFichier.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(strLigne, ",");
				arrLivres.add(new Livres(st.nextToken().trim().toString(), st.nextToken().trim().toString(), st.nextToken().trim().toString(), "0", st.nextToken().trim().toString()));
			}
			//for (int i = 0; i < arrLivres.size(); i++) System.out.println(arrLivres.get(i).toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void lirePeriodiques() { // lit le fichier periodique
		BufferedReader brFichier = null;

		try {
			brFichier = new BufferedReader(new FileReader(strNomFichier + "Periodiques.txt"));
		}
		catch (FileNotFoundException e){

			e.printStackTrace();
		}

		try {
			String strLigne;

			while ((strLigne = brFichier.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(strLigne, ",");
				arrPeriodiques.add(new Periodiques(st.nextToken().trim().toString(), st.nextToken().trim().toString(), st.nextToken().trim().toString(), "0", st.nextToken().trim().toString(), st.nextToken().trim().toString()));
			}
			//for (int i = 0; i < arrPeriodiques.size(); i++) System.out.println(arrPeriodiques.get(i).toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
