package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ModificationDocument extends Stage {
	private String[] strDocuments = {"DVD", "Livres", "Periodiques"};

	private Text text, textDoc;
	private TextField textFieldNoDoc, textFieldTitre, textFieldDate;

	private VBox vBoxCentre, vBoxDocVariable;

	private Button btnDoc;

	private ArrayList<DVD> arrDVDSerialise;
	private ArrayList<Livres> arrLivreSerialise;
	private ArrayList<Periodiques> arrPeriodiquesSerialise;

	public ModificationDocument(int i) {
		//this.primaryStage = primaryStage;

		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,675,450); // 1.5 aspect ratio
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		// vBoxCentre
		VBox vBoxMain = new VBox();
		vBoxMain.setPadding(new Insets(15));
		vBoxMain.setAlignment(Pos.TOP_CENTER);
		vBoxMain.setSpacing(50);

		text = new Text();
		text.setFont(font(25));

		vBoxCentre = new VBox();
		vBoxCentre.setSpacing(20);
		vBoxCentre.setAlignment(Pos.TOP_LEFT);

		VBox vBoxDocuments = new VBox();
		vBoxDocuments.setSpacing(20);

		vBoxDocVariable = new VBox();
		//vBoxDocVariable.setSpacing(20);

		VBox vBoxDocInitial = new VBox();
		vBoxDocInitial.setSpacing(10);

		textDoc = new Text();
		textDoc.setFont(font(15));
		text.setTextAlignment(TextAlignment.CENTER);

		ComboBox<String> comboBox = new ComboBox<>();
		comboBox.setValue("DVD");
		comboBox.getItems().addAll("DVD", "Livres", "Periodiques");
		comboBox.valueProperty().addListener(new GestionComboBox());

		HBox hBoxComboBox = new HBox();
		hBoxComboBox.setSpacing(10);

		HBox hBoxNoDoc = new HBox(), hBoxTitre = new HBox(), hBoxDate = new HBox();
		hBoxTitre.setSpacing(10);
		hBoxDate.setSpacing(10);

		Text textNoDoc, textTitre, textDate;

		textNoDoc = new Text("No Document");
		textTitre = new Text("Titre\t\t\t\t\t\t\t");
		textDate = new Text("Date\t\t\t\t\t\t\t\t");

		textTitre.setFont(font(15));
		textDate.setFont(font(15));

		textFieldNoDoc = new TextField();
		textFieldTitre = new TextField();
		textFieldDate = new TextField();

		textFieldNoDoc.setText(""); //////////////////////////////////////////
		textFieldTitre.setPromptText("Titre");
		textFieldDate.setPromptText("Date");

		hBoxNoDoc.getChildren().addAll(textNoDoc, textFieldNoDoc);
		hBoxTitre.getChildren().addAll(textNoDoc, textTitre, textFieldTitre);
		hBoxDate.getChildren().addAll(textDate, textFieldDate);

		btnDoc = new Button();
		btnDoc.setFont(font(15));

		if (i == 0) ajouterDoc();
		else if (i == 1) modifierDoc();
		else if (i == 2) supprimerDoc();

		docDVD();

		hBoxComboBox.getChildren().addAll(textDoc, comboBox);

		vBoxDocInitial.getChildren().addAll(hBoxTitre, hBoxDate);

		vBoxDocuments.getChildren().addAll(vBoxDocInitial, vBoxDocVariable, btnDoc);

		vBoxCentre.getChildren().addAll(hBoxComboBox, vBoxDocuments);

		vBoxMain.getChildren().addAll(text, vBoxCentre);

		root.setCenter(vBoxMain);

		this.sizeToScene();
		this.setResizable(false);
		//primaryStage.setMaximized(true);
		//primaryStage.setFullScreen(true); // non pour mtn
		this.setTitle("Modification d'un document");
		this.setScene(scene);
		this.show();
	}

	private void ajouterDoc() {
		text.setText("Ajouter un document");
		textDoc.setText("Choisisez le type de document a ajouter");
		btnDoc.setText("Ajouter le document");
		btnDoc.setId("0");
	}
	private void modifierDoc() {
		text.setText("Modifier un document");
		textDoc.setText("Choisisez le type de document a modifier");
		btnDoc.setText("Modifier le document");
		btnDoc.setId("1");
	}
	private void supprimerDoc() {
		text.setText("Supprimer un document");
		textDoc.setText("Choisisez le type de document a supprimer");
		btnDoc.setText("Supprimer le document");
		btnDoc.setId("2");
	}

	private Font font(int intSize) {
		return Font.font("Serif", FontWeight.BOLD, intSize);
	}
	private class GestionComboBox implements ChangeListener<String> {
		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			// TODO Auto-generated method stub
			if (vBoxDocVariable != null) {
				vBoxCentre.getChildren().remove(vBoxDocVariable);
				vBoxDocVariable.getChildren().clear();
			}
			if (btnDoc != null) vBoxCentre.getChildren().remove(btnDoc);

			if (newValue.equals(strDocuments[0])) {
				docDVD();
			}
			else if (newValue.equals(strDocuments[1])) {
				docLivre();
			}
			else if (newValue.equals(strDocuments[2])) {
				docPeriodique();
			}

			if (vBoxDocVariable != null) vBoxCentre.getChildren().addAll(vBoxDocVariable);
			if (btnDoc != null) vBoxCentre.getChildren().add(btnDoc);
		}
	}
	private Border border(Color color) {
		return new Border(new BorderStroke(color, new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.BEVEL, StrokeLineCap.BUTT, 10, 0, null), CornerRadii.EMPTY,  new BorderWidths(2)));
	}
	private Background background(Color color) {
		return new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY));
	}

	private void docDVD() {
		VBox vBox = new VBox();
		vBox.setSpacing(10);

		HBox hBoxNomRealisateur = new HBox(), hBoxNbDisque = new HBox();
		hBoxNomRealisateur.setSpacing(10);
		hBoxNbDisque.setSpacing(10);

		Text textNomRealisateur = new Text("Nom du realisateur\t\t\t\t"), textNbDisque = new Text("Nombre de disque\t\t\t\t\t");
		textNomRealisateur.setFont(font(15));
		textNbDisque.setFont(font(15));

		TextField textFieldNomRealisateur = new TextField(), textFieldNbDisque = new TextField();
		textFieldNomRealisateur.setPromptText("Nom du realisateur");
		textFieldNbDisque.setPromptText("Nombre de disque");

		hBoxNomRealisateur.getChildren().addAll(textNomRealisateur, textFieldNomRealisateur);
		hBoxNbDisque.getChildren().addAll(textNbDisque, textFieldNbDisque);

		vBox.getChildren().addAll(hBoxNomRealisateur, hBoxNbDisque);

		vBoxDocVariable.getChildren().addAll(vBox);

		btnDoc.setOnAction(t -> {
			boolean blnCorrect = true;

			if (textFieldTitre.getText().trim().equals("")) {
				blnCorrect = false;
				textFieldTitre.setBorder(border(Color.RED));
			}
			else textFieldTitre.setBorder(Border.EMPTY);

			if (textFieldDate.getText().trim().equals("")) {
				blnCorrect = false;
				textFieldDate.setBorder(border(Color.RED));
			}
			else textFieldDate.setBorder(Border.EMPTY);

			if (textFieldNomRealisateur.getText().trim().equals("")) {
				blnCorrect = false;
				textFieldNomRealisateur.setBorder(border(Color.RED));
			}
			else textFieldNomRealisateur.setBorder(Border.EMPTY);

			if (textFieldNbDisque.getText().trim().equals("")) {
				blnCorrect = false;
				textFieldNbDisque.setBorder(border(Color.RED));
			}
			else textFieldNbDisque.setBorder(Border.EMPTY);

			if (blnCorrect) {
				if (btnDoc.getId().equals("0")) new SerialiseObjets(new DVD("DVD", textFieldTitre.getText(), textFieldDate.getText(), "0", textFieldNomRealisateur.getText(), textFieldNbDisque.getText())); // ajoute les champs dans un fichier serialise DVD
				DeserialiseObjets.getDVD();
			}
		});
	}
	private void docLivre() {
		VBox vBox = new VBox();
		vBox.setSpacing(10);

		HBox hBoxNomAuteur = new HBox();
		hBoxNomAuteur.setSpacing(10);

		Text textNomAuteur = new Text("Nom de l'auteur\t\t\t\t\t");
		textNomAuteur.setFont(font(15));

		TextField textFieldNomAuteur = new TextField();
		textFieldNomAuteur.setPromptText("Nom de l'auteur");

		hBoxNomAuteur.getChildren().addAll(textNomAuteur, textFieldNomAuteur);

		vBox.getChildren().addAll(hBoxNomAuteur);

		vBoxDocVariable.getChildren().addAll(vBox);
	}
	private void docPeriodique() {
		VBox vBox = new VBox();
		vBox.setSpacing(10);

		HBox hBoxVol = new HBox(), hBoxPer = new HBox();
		hBoxVol.setSpacing(10);
		hBoxPer.setSpacing(10);

		Text textVol = new Text("No du volume\t\t\t\t\t\t"), textPer = new Text("No periodique\t\t\t\t\t");
		textVol.setFont(font(15));
		textPer.setFont(font(15));

		TextField textFieldVol = new TextField(), textFieldPer = new TextField();
		textFieldVol.setPromptText("No du volume");
		textFieldPer.setPromptText("No periodique");

		hBoxVol.getChildren().addAll(textVol, textFieldVol);
		hBoxPer.getChildren().addAll(textPer, textFieldPer);

		vBox.getChildren().addAll(hBoxVol, hBoxPer);

		vBoxDocVariable.getChildren().addAll(vBox);
	}

	private void serialse() {

	}
}
