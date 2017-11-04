package application;
	
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Projet2 extends Application {
	// Fichier ressource
	private final String strNomFichier = "Donnees" + System.getProperty("file.separator"); // j utilise ubuntu

	// Pour les documents
	private ArrayList<DVD> arrDVD = new ArrayList<>();
	private ArrayList<Livres> arrLivres = new ArrayList<>();
	private ArrayList<Periodiques> arrPeriodiques = new ArrayList<>();

	private ArrayList<Preposes> arrPrepose = new ArrayList<>();
	private ArrayList<Adherents> arrAdherent = new ArrayList<>();
	
	// Pour la liste d'adherent qui peuvent se connecter
	private ArrayList<String> arrLstAdherent = new ArrayList<>();

	// Pour l'interface
	private MenuBar menuBar;
	private Menu menuDeconnexion;
	private MenuItem menuItemDeconnexion;
	
	private VBox vBox;
	private Button btnDeplacerSource, btnDeplacerDestination;
	private GridPane gridPaneEmploye;
	private Group group;
	
	private Text textTitre, textEmploye, textMotDePasse, textAdherent;
	private TextField textFieldEmploye, textFieldAdherent;
	private PasswordField passwordFieldEmploye;
	private Label lblMsg;
	 
	// Liste Observable
	private ListView<String> lstViewEmployeSource, lstViewEmployeDestination;
	
	@Override
	public void start(Stage primaryStage) { // interface main
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,900,600); // 1.5 aspect ratio
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// Bar
			menuBar = new MenuBar();
			Menu menuPersonne = new Menu("_Personne"), menuAide = new Menu("_Aide");

			menuPersonne.setMnemonicParsing(true);
			menuAide.setMnemonicParsing(true);

			// Items
			MenuItem menuItemPrepose = new MenuItem("P_repose"), menuItemAdherent = new MenuItem("Ad_herent"), menuItemAide = new MenuItem("A_ide");

			// Ajout des menus
			menuPersonne.getItems().addAll(menuItemPrepose, menuItemAdherent);
			menuAide.getItems().add(menuItemAide);
			menuBar.getMenus().addAll(menuPersonne, menuAide);

			// vBox
			vBox = new VBox();
			vBox.setSpacing(20);
			vBox.setAlignment(Pos.TOP_LEFT);
			
			VBox vBoxMain = new VBox();
			vBoxMain.setPadding(new Insets(10));
			vBoxMain.setAlignment(Pos.TOP_CENTER);
			vBoxMain.setSpacing(30);

			textTitre = new Text("Veillez vous identifier S.V.P.");
			textTitre.setFont(font(30));
			textTitre.setTextAlignment(TextAlignment.CENTER);

			vBoxMain.getChildren().addAll(textTitre, vBox);

			root.setTop(menuBar);
			root.setCenter(vBoxMain);

			nouvellePersonne();
			setOnAction(menuItemPrepose, menuItemAdherent, menuItemAide);

			primaryStage.sizeToScene();
			//primaryStage.setResizable(false);
			primaryStage.setMaximized(true);
			//primaryStage.setFullScreen(true);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Projet2");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
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
				arrDVD.add(new DVD(st.nextToken().trim().toString(), st.nextToken().trim().toString(), st.nextToken().trim().toString(), 0, Integer.parseInt(st.nextToken().trim().toString()), st.nextToken().trim().toString()));
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
				arrLivres.add(new Livres(st.nextToken().trim().toString(), st.nextToken().trim().toString(), st.nextToken().trim().toString(), 0, st.nextToken().trim().toString()));
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
				arrPeriodiques.add(new Periodiques(st.nextToken().trim().toString(), st.nextToken().trim().toString(), st.nextToken().trim().toString(), 0, Integer.parseInt(st.nextToken().trim().toString()), Integer.parseInt(st.nextToken().trim().toString())));
			}
			//for (int i = 0; i < arrPeriodiques.size(); i++) System.out.println(arrPeriodiques.get(i).toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Font font(int intSize) {
		return Font.font("Serif", FontWeight.BOLD, intSize);
	}

	private void nouvellePersonne() { // Fait appel a chaque fois que l'utilisateur change de personne
		deconnexion();
		
		arrDVD.clear();
		arrLivres.clear();
		arrPeriodiques.clear();
		
		arrPrepose.clear();
		arrAdherent.clear();
		
		//arrLstAdherent.clear(); // Il ne faut pas l'effacer, pcq on en a besoin pour se connecter dans le compte d'adherent
		
		vBox.getChildren().clear(); // removeAll() doesnt work
		
		lireDVD();
		lireLivres();
		lirePeriodiques();
	}

	private void setOnAction(MenuItem menuItemPrepose, MenuItem menuItemAdherent, MenuItem menuItemAide) { // setOnAction des boutons, itemMenus, ...etc
		menuItemPrepose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				nouvellePersonne();

				if (event.getSource() instanceof MenuItem) textTitre.setText(retourneNomMenuItem(((MenuItem) event.getSource()).getText()));

				textEmploye = new Text("No Employe  ");
				textMotDePasse = new Text("Mot de Passe");
				
				textEmploye.setFont(font(15));
				textMotDePasse.setFont(font(15));

				textFieldEmploye = new TextField();
				textFieldEmploye.setPromptText("No Employe");
				passwordFieldEmploye = new PasswordField();
				passwordFieldEmploye.setPromptText("Mot de passe");
				
				lblMsg = new Label();
				lblMsg.setFont(font(15));
				HBox hBox1 = new HBox(), hBox2 = new HBox(), hBox3 = new HBox();
				
				hBox1.getChildren().addAll(textEmploye, textFieldEmploye);
				hBox1.setSpacing(10);
				hBox2.getChildren().addAll(textMotDePasse, passwordFieldEmploye);
				hBox2.setSpacing(10);
				hBox3.getChildren().addAll(lblMsg);
				
				vBox.getChildren().addAll(hBox1, hBox2, hBox3);
				
				textFieldEmploye.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if (passwordFieldEmploye.getText().trim().equals("10101997") && textFieldEmploye.getText().trim().equals("Guelleh")) {
							lblMsg.setText("Connecte en tant que prepose " + textFieldEmploye.getText());
							lblMsg.setTextFill(Color.rgb(21, 117, 84));
							
							textFieldEmploye.setDisable(true);
							passwordFieldEmploye.setDisable(true);
							
							connexion(true);
						}
						else {
							lblMsg.setText("No d'employe ou mot de passe incorrecte");
							lblMsg.setTextFill(Color.rgb(210, 39, 30));
						}
						
						passwordFieldEmploye.clear();
					}
				});
				passwordFieldEmploye.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						if (passwordFieldEmploye.getText().trim().equals("10101997") && textFieldEmploye.getText().trim().equals("Guelleh")) {
							lblMsg.setText("Connecte en tant que prepose " + textFieldEmploye.getText());
							lblMsg.setTextFill(Color.rgb(21, 117, 84));
							
							textFieldEmploye.setDisable(true);
							passwordFieldEmploye.setDisable(true);
							
							connexion(true);
						}
						else {
							lblMsg.setText("No d'employe ou mot de passe incorrecte");
							lblMsg.setTextFill(Color.rgb(210, 39, 30));
						}
						
						passwordFieldEmploye.clear();
					}
				});
			}
		});
		menuItemAdherent.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				nouvellePersonne();

				if (event.getSource() instanceof MenuItem) textTitre.setText(retourneNomMenuItem(((MenuItem) event.getSource()).getText()));

				textAdherent = new Text("No Adherent");
				
				textAdherent.setFont(font(15));
				
				textFieldAdherent = new TextField();
				textFieldAdherent.setPromptText("No Adherent");

				lblMsg = new Label();
				lblMsg.setFont(font(15));
				HBox hBox1 = new HBox(), hBox2 = new HBox();
				
				hBox1.getChildren().addAll(textAdherent, textFieldAdherent);
				hBox1.setSpacing(10);
				hBox2.getChildren().add(lblMsg);
				
				vBox.getChildren().addAll(hBox1, hBox2);
				
				textFieldAdherent.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						Collections.sort(arrLstAdherent);
						
						boolean blnFor = false;
						for (String strAdherent : arrLstAdherent) {
							if (textFieldAdherent.getText().trim().equals(strAdherent)) {
								lblMsg.setText("Connecte en tant qu'adherent " + textFieldAdherent.getText());
								lblMsg.setTextFill(Color.rgb(21, 117, 84));
								
								textFieldAdherent.setDisable(true);
								
								connexion(false);
								
								blnFor = true;
							}
						}
						
						if (!blnFor) {
							lblMsg.setText("No d'adherent incorrecte");
							lblMsg.setTextFill(Color.rgb(210, 39, 30));
						}
					}
				});
			}
		});

		menuItemAide.setOnAction(new EventHandler<ActionEvent>() { /////////////////////////////////////////////////////////////////////////////
			@Override
			public void handle(ActionEvent event) {

			}
		});
	}

	private String retourneNomMenuItem(String strNomMenuItem) { // Remplace _ du string et retourne par exemple Sport au lieu de _Sport
		return strNomMenuItem.replaceAll("_", "");
	}
	
	private void connexion(boolean blnEmploye) { // Quand la personne se connecte
		menuDeconnexion = new Menu("_Deconnexion");
		menuItemDeconnexion = new MenuItem("De_connexion");
		
		menuDeconnexion.setMnemonicParsing(true);
		
		menuItemDeconnexion.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				deconnexion();
			}
		});
		
		menuDeconnexion.getItems().add(menuItemDeconnexion);
		menuBar.getMenus().add(menuDeconnexion);
		
		if (blnEmploye) { // Quand l'employe se connecte
			methodeLstViewEmploye();
			//methodeTableViewEmploye();
		} 
		else { // Quand l'adherent se connecte
			methodeTableViewAdherent();
		}
	}
	private void deconnexion() { // Quand la personne se deconencte ou change de personne
		if (menuItemDeconnexion != null && menuDeconnexion != null) {
			menuDeconnexion.getItems().remove(menuItemDeconnexion);
			menuBar.getMenus().remove(menuDeconnexion);
		}
		
		if (textFieldEmploye != null && passwordFieldEmploye != null) {
			textFieldEmploye.setDisable(false);
			passwordFieldEmploye.setDisable(false);
		}
		if (textFieldAdherent != null) textFieldAdherent.setDisable(false);
		
		if (lblMsg != null) lblMsg.setText(null);
		if (gridPaneEmploye != null && vBox != null) vBox.getChildren().remove(gridPaneEmploye);
		if (group != null && vBox != null) vBox.getChildren().remove(group);
	}
	
	private void methodeLstViewEmploye() { // Pour permettre au prepose d'ajouter et de supprimer les adherents, methode connexion
		btnDeplacerSource = new Button("Ajouter >>>");
		btnDeplacerDestination = new Button("<<< Retirer");

		if (arrLstAdherent.size() == 0) btnDeplacerDestination.setDisable(true);
		else btnDeplacerDestination.setDisable(false);
		
		VBox vBoxButton = new VBox();
		vBoxButton.setSpacing(10);
		vBoxButton.setAlignment(Pos.CENTER);
		vBoxButton.getChildren().addAll(btnDeplacerDestination, btnDeplacerSource);
		
		gridPaneEmploye = new GridPane();
		gridPaneEmploye.setAlignment(Pos.TOP_RIGHT);
		gridPaneEmploye.setPadding(new Insets(5));
		gridPaneEmploye.setHgap(10);
		gridPaneEmploye.setVgap(10);

		ObservableList<String> listeObservable = FXCollections.observableArrayList(arrLstAdherent);
		Collections.sort(arrLstAdherent);

		lstViewEmployeSource = new ListView<>(FXCollections.observableArrayList("Guelleh", "Guelleh", "cyan", "rouge", "gris", "vert", "rose", "jaune"));
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
        
        btnDeplacerSource.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
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
			}
		});
        btnDeplacerDestination.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
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
			}
		});
        
        listeObservable.addListener(new ListChangeListener<String>() { // Ajoute ou remove les adherents de la arrLstAdherent

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends String> c) {
				// TODO Auto-generated method stub
				while (c.next()) {
					for (String strLstAdherentAdded : c.getAddedSubList()) arrLstAdherent.add(strLstAdherentAdded); // ajoute la valeur que contient c dans arrLstAdherent
					for (String strLstAdherentRemoved : c.getRemoved()) arrLstAdherent.remove(strLstAdherentRemoved);
					
					arrLstAdherent = (ArrayList<String>) arrLstAdherent.stream().distinct().collect(Collectors.toList()); // remove duplicates, ex Guelleh Guelleh devient dans la array Guelleh. C'est pour accelerer le temps de comparaison lorsque l'adherent se connecte et eliminer les duplicates
					Collections.sort(arrLstAdherent); // trie le arraylist
					
					/*System.out.println("arrLstAdherent");
					for (String s : arrLstAdherent) System.out.println(s);
					System.out.println();*/
				}
			}
		});
        
        gridPaneEmploye.add(lblSource, 0, 0);
        gridPaneEmploye.add(lstViewEmployeSource, 0, 1);
        gridPaneEmploye.add(vBoxButton, 1, 1);
        gridPaneEmploye.add(lblDestination, 2, 0);
        gridPaneEmploye.add(lstViewEmployeDestination, 2, 1);
        
        vBox.getChildren().add(gridPaneEmploye);
	}
	private void methodeTableViewEmploye() { // Pour permettre au prepose de voir la table, methode connexion ////////////////////////////////////////////////
		group = new Group();

		Text text = new Text();
		text.setText("Liste de ");
		text.setFont(font(15));

		VBox vBoxGroup = new VBox();
		vBoxGroup.setAlignment(Pos.BOTTOM_LEFT);

		HBox hBoxGroup = new HBox();

		TableView<Documents> tableView = new TableView<>();

		TableColumn<Documents, Integer> colonneNumDoc = new TableColumn<>("No Document"), colonneTitre = new TableColumn<>("Titre"), colonneDate = new TableColumn<>("Date"), colonneEtat = new TableColumn<>("Etat");

		tableView.getColumns().addAll(colonneNumDoc, colonneTitre, colonneDate, colonneEtat);

		colonneNumDoc.setPrefWidth(120);
		colonneTitre.setPrefWidth(120);
		colonneDate.setPrefWidth(120);
		colonneEtat.setPrefWidth(120);

		TextField textFieldNoDocument = new TextField(), textFieldTitre = new TextField(), textFieldDate = new TextField(), textFieldEtat = new TextField();

		textFieldNoDocument.setPromptText("No Document");
		textFieldTitre.setPromptText("Titre");
		textFieldDate.setPromptText("Date");
		textFieldEtat.setPromptText("Etat");

		Button btnAjouter = new Button("Ajouter");
		btnAjouter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

			}
		});

		hBoxGroup.getChildren().addAll(textFieldNoDocument, textFieldTitre, textFieldDate, textFieldEtat, btnAjouter);
		vBoxGroup.getChildren().addAll(text, tableView, hBoxGroup);

		vBoxGroup.getChildren().add(vBoxGroup);
	}
	private void methodeTableViewAdherent() { // Pour permettre a l'adherent de voir la table, les adherents, methode connexion
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
