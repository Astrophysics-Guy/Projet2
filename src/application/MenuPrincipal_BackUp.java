package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MenuPrincipal_BackUp extends Stage {
	private ArrayList<Livres> arrLivres = new ArrayList<>();
	private final String strNomFichier = "Donnees" + System.getProperty("file.separator"); // j utilise ubuntu
	
	private Stage primaryStage;
	private TextField textField;
	
	private VBox vBox;
	
	public MenuPrincipal_BackUp(TextField textFieldEmploye, Stage primaryStage) {
		// TODO Auto-generated constructor stub
		this.textField = textFieldEmploye;
		this.primaryStage = primaryStage;
		
		//arrLivres = null;
		
		lireLivres();
		
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,900,600); // 1.5 aspect ratio
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		// Bar
		MenuBar menuBar = new MenuBar();
		Menu menuDeconnexion = new Menu("_Deconnexion"), menuAide = new Menu("_Aide");

		menuAide.setMnemonicParsing(true);
		menuDeconnexion.setMnemonicParsing(true);

		// Items
		MenuItem menuItemDeconnexion = new MenuItem("D_econnexion"), menuItemAide = new MenuItem("A_ide");

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

		Text textTitre = new Text("Connecte en tant que prepose " + textField.getText());
		textTitre.setFont(font(30));
		textTitre.setTextAlignment(TextAlignment.CENTER);

		VBox vBoxTest = new VBox();

		TabPane tabPane = new TabPane();
		
		BorderPane borderPane = new BorderPane();
		
		for (int i = 0; i < 4; i++) {
			Tab tab = new Tab();
			HBox hBox = new HBox();
			hBox.getChildren().add(new Label("Test " + i));
			hBox.setAlignment(Pos.CENTER);
			tab.setContent(hBox);
			tabPane.getTabs().add(tab);
		}
		
		borderPane.setCenter(tabPane);
		
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		
		vBoxMain.getChildren().addAll(borderPane /*, vBox*/);
		
		//root.setTop(menuBar);
		root.setLeft(borderPane);
		root.setRight(vBoxTest);

		this.sizeToScene();
		//primaryStage.setResizable(false);
		//this.setMaximized(true);
		//primaryStage.setFullScreen(true); // non pour mtn
		this.setScene(scene);

		this.show();

		menuItemDeconnexion.setOnAction(event -> {
			this.close();
			
			primaryStage.show();
		});
		
		/////////////////////////////////////////////////////////////////////////////
		menuItemAide.setOnAction(event -> {

		});
		
		methodeTableViewEmploye();
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
	
	private void methodeTableViewEmploye() { // Pour permettre au prepose de voir la table, methode connexion ////////////////////////////////////////////////
		ObservableList<Documents> observableList = FXCollections.observableArrayList(arrLivres);

		Group group = new Group();

		Text text = new Text();
		text.setText("Liste des livres");
		text.setFont(font(15));
		
		BorderPane borderPane = new BorderPane();
		
		TabPane tabPane = new TabPane();
		
		for (int i = 0; i < 4; i++) {
			Tab tab = new Tab();
			HBox hBox = new HBox();
			hBox.getChildren().add(new Label("Test " + i));
			hBox.setAlignment(Pos.CENTER);
			tab.setContent(hBox);
			tabPane.getTabs().add(tab);
		}
		
		//borderPane.prefHeightProperty().bind(70);
				
		borderPane.setCenter(tabPane);

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
		vBox.getChildren().addAll(borderPane, group);
	}
	
	private Font font(int intSize) {
		return Font.font("Serif", FontWeight.BOLD, intSize);
	}
}
