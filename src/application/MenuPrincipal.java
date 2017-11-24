package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Optional;
import java.util.StringTokenizer;

public class MenuPrincipal extends Stage {
	// Fichier ressource
	private final String strNomFichier = "Donnees" + System.getProperty("file.separator"); // j
	// utilise
	// ubuntu

	// Pour les documents
	private ArrayList<DVD> arrDVD = new ArrayList<>();
	private ArrayList<Livres> arrLivres = new ArrayList<>();
	private ArrayList<Periodiques> arrPeriodiques = new ArrayList<>();

	// pour le nombre max d'enregistrement deja present
	private int intDVDNoDocMax, intLivresNoDocMax, intPeriodiquesNoDocMax;

	// le stage du login
	private Stage primaryStage;

	public MenuPrincipal(Stage primaryStage) { // qd le
		// prepose
		// se
		// connecte
		// et veut
		// afficher
		// la table
		// view
		this.primaryStage = primaryStage;

		lireDVD();
		lireLivres();
		lirePeriodiques();

		menuPrincipal();
	}

	public void menuPrincipal() {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 1200, 900); // 1.5 aspect ratio
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		// vBox
		VBox vBox = new VBox();
		vBox.setSpacing(10);
		vBox.setAlignment(Pos.TOP_LEFT);

		HBox hBoxMain = new HBox();
		hBoxMain.setPadding(new Insets(10));
		hBoxMain.setAlignment(Pos.TOP_CENTER);
		hBoxMain.setSpacing(20);

		TabPane tabPane = new TabPane();

		BorderPane borderPane = new BorderPane();

		Tab tabCollection = new Tab("Collection");
		Tab tabLivres = new Tab("Livres");
		Tab tabDVD = new Tab("DVDs");
		Tab tabPeriodique = new Tab("Periodiques");
		tabPane.getTabs().addAll(tabCollection, tabLivres, tabPeriodique, tabDVD);

		tabLivres.setContent(new Text("Test"));

		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		// bind to take available space
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());

		borderPane.setCenter(tabPane);
		root.getChildren().add(borderPane);

		vBox.getChildren().addAll(borderPane);

		hBoxMain.getChildren().addAll(vBox);

		//droite(hBoxMain);

		menu(root);
		root.setCenter(hBoxMain);

		this.sizeToScene();
		this.setResizable(false);
		//this.setTitle("Menu Prepose");
		this.setTitle("Mediatheque - © 2017 Ismail El Khattab & Mohamed H. Guelleh");
		this.setScene(scene);
		this.show();

		this.setOnCloseRequest(e -> {
			e.consume();
			fermerProgramme(this);
		});
	}

	private void menu(BorderPane root) {
		// Bar
		MenuBar menuBar = new MenuBar();

		// menu
		Menu menuDocument = new Menu("D_ocuments", new ImageView(new Image("icons8-binder-80.png", 25, 25, false, false)));
		Menu menuAdherents = new Menu("Ad_herents", new ImageView(new Image("icons8-edit-profile-80.png", 25, 25, false, false)));
		Menu menuAutresActions = new Menu("A_utres actions", new ImageView(new Image("icons8-settings-80.png", 25, 25, false, false)));
		Menu menuDeconnexion = new Menu("_Deconnexion", new ImageView(new Image("icons8-exit-80.png", 25, 25, false, false)));

		menuDocument.setMnemonicParsing(true);
		menuAdherents.setMnemonicParsing(true);
		menuAutresActions.setMnemonicParsing(true);
		menuDeconnexion.setMnemonicParsing(true);

		// Items
		MenuItem menuItemAjouterDoc = new MenuItem("_Ajouter", new ImageView(new Image("icons8-documents-80.png", 20, 20, false, false)));
		MenuItem menuItemModifierDoc = new MenuItem("_Modifier", new ImageView(new Image("icons8-edit-file-80.png", 20, 20, false, false)));
		MenuItem menuItemSupprimerDoc = new MenuItem("_Supprimer", new ImageView(new Image("icons8-delete-file-80.png", 20, 20, false, false)));
		MenuItem menuItemAdherent = new MenuItem("_Gerer", new ImageView(new Image("icons8-edit-profile-80.png", 20, 20, false, false)));
		MenuItem menuItemPret = new MenuItem("_Pret", new ImageView(new Image("icons8-borrow-book-80.png", 20, 20, false, false)));
		MenuItem menuItemRetour = new MenuItem("_Retour", new ImageView(new Image("icons8-redo-80.png", 20, 20, false, false)));
		MenuItem menuItemDeconnexion = new MenuItem("D_econnexion", new ImageView(new Image("icons8-exit-80.png", 20, 20, false, false)));

		// setOnAction des menuItems
		menuItemAjouterDoc.setOnAction(t -> {
			t.consume();
			new ModificationDocument(0, intDVDNoDocMax, intLivresNoDocMax, intPeriodiquesNoDocMax).show();
		});
		menuItemModifierDoc.setOnAction(t -> {
			t.consume();
			new ModificationDocument(1, intDVDNoDocMax, intLivresNoDocMax, intPeriodiquesNoDocMax).show();
		});
		menuItemSupprimerDoc.setOnAction(t -> {
			t.consume();
			new ModificationDocument(2, intDVDNoDocMax, intLivresNoDocMax, intPeriodiquesNoDocMax).show();
		});

		menuItemAdherent.setOnAction(t -> { ////////////////////////////////////////////////////
			t.consume();
		});

		menuItemPret.setOnAction(t -> { ////////////////////////////////////////////////////
			t.consume();
		});
		menuItemRetour.setOnAction(t -> { ////////////////////////////////////////////////////
			t.consume();
		});

		menuItemDeconnexion.setOnAction(t -> {
			t.consume();
			this.close();
			primaryStage.show();
		});

		// Ajout des menus
		menuDocument.getItems().addAll(menuItemAjouterDoc, menuItemModifierDoc, menuItemSupprimerDoc);
		menuAdherents.getItems().add(menuItemAdherent);
		menuAutresActions.getItems().addAll(menuItemPret, menuItemRetour);
		menuDeconnexion.getItems().add(menuItemDeconnexion);
		menuBar.getMenus().addAll(menuDocument, menuAdherents, menuAutresActions, menuDeconnexion);

		root.setTop(menuBar);
	}

	private Border border(Color color) {
		return new Border(new BorderStroke(color,
				new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.BEVEL, StrokeLineCap.BUTT, 10, 0, null),
				CornerRadii.EMPTY, new BorderWidths(1)));
	}
	private Font font(int intSize) {
		return Font.font("Serif", FontWeight.BOLD, intSize);
	}

	private void fermerProgramme(Stage stage) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setHeaderText("Etes-vous sur de vouloir quitter ?");
		//alert.setContentText("Are you ok with this?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){ //////////////////////////////////////////////////// Enregistrer tout
			// ... user chose OK
			stage.close();
		} else {
			// ... user chose CANCEL or closed the dialog
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
				arrDVD.add(new DVD(st.nextToken().trim(), st.nextToken().trim(), st.nextToken().trim(), "0", st.nextToken().trim(), st.nextToken().trim()));
			}
			//for (int i = 0; i < arrDVD.size(); i++) System.out.println(arrDVD.get(i));
			String strNumDoc = arrDVD.get(arrDVD.size()-1).getStrNumDoc();
			intDVDNoDocMax = Integer.parseInt(strNumDoc.substring(3, strNumDoc.length())) + 1;
		}
		catch (Exception e) {
			//System.out.println(e);
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
				arrLivres.add(new Livres(st.nextToken().trim(), st.nextToken().trim(), st.nextToken().trim(), "0", st.nextToken().trim()));
			}
			//for (int i = 0; i < arrLivres.size(); i++) System.out.println(arrLivres.get(i));
			String strNumDoc = arrLivres.get(arrLivres.size()-1).getStrNumDoc();
			intLivresNoDocMax = Integer.parseInt(strNumDoc.substring(3, strNumDoc.length())) + 1;
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
				arrPeriodiques.add(new Periodiques(st.nextToken().trim(), st.nextToken().trim(), st.nextToken().trim(), "0", st.nextToken().trim(), st.nextToken().trim()));
			}
			//for (int i = 0; i < arrPeriodiques.size(); i++) System.out.println(arrPeriodiques.get(i));
			String strNumDoc = arrPeriodiques.get(arrPeriodiques.size()-1).getStrNumDoc();
			intPeriodiquesNoDocMax = Integer.parseInt(strNumDoc.substring(3, strNumDoc.length())) + 1;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
