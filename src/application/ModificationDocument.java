package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
import javafx.util.Duration;

import java.util.ArrayList;

public class ModificationDocument extends Stage {
	// pour le nombre max d'enregistrement deja present
	private int intDVDNoDocMax, intLivresNoDocMax, intPeriodiquesNoDocMax;
	private final String[] strDocuments = {"DVD", "Livres", "Periodiques"};

	private Text text, textDoc;
	private TextField textFieldNoDoc, textFieldTitre, textFieldDate;

	private VBox vBoxDocInitial, vBoxCentre, vBoxDocVariable;

	private Label labelError;

	private Button btnDoc;

	private ComboBox<String> comboBoxDocumentsNoDoc;

	private ArrayList<DVD> arrDVDSerialise;
	private ArrayList<Livres> arrLivreSerialise;
	private ArrayList<Periodiques> arrPeriodiquesSerialise;

	private Stage stageThis;

	public ModificationDocument(int intChoixDoc, int intDVDNoDocMax, int intLivresNoDocMax, int intPeriodiquesNoDocMax) {
		this.intDVDNoDocMax = intDVDNoDocMax;
		this.intLivresNoDocMax = intLivresNoDocMax;
		this.intPeriodiquesNoDocMax = intPeriodiquesNoDocMax;

		arrDVDSerialise = DeserialiseObjets.getArrDVD();
		arrLivreSerialise = DeserialiseObjets.getArrLivres();
		arrPeriodiquesSerialise = DeserialiseObjets.getArrPeriodiques();

		stageThis = this;

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

		vBoxDocInitial = new VBox();
		vBoxDocInitial.setSpacing(10);

		textDoc = new Text();
		textDoc.setFont(font(15));
		text.setTextAlignment(TextAlignment.CENTER);

		ComboBox<String> comboBoxDocuments = new ComboBox<>();
		comboBoxDocuments.setValue("DVD");
		comboBoxDocuments.getItems().addAll("DVD", "Livres", "Periodiques");
		comboBoxDocuments.valueProperty().addListener(new GestionComboBoxDocuments());

		HBox hBoxComboBoxDocuments = new HBox();
		hBoxComboBoxDocuments.setSpacing(10);

		HBox hBoxTitre = new HBox(), hBoxDate = new HBox();
		hBoxTitre.setSpacing(10);
		hBoxDate.setSpacing(10);

		Text textTitre, textDate;

		textTitre = new Text("Titre\t\t\t\t\t\t\t");
		textDate = new Text("Date\t\t\t\t\t\t\t\t");

		textTitre.setFont(font(15));
		textDate.setFont(font(15));

		textFieldTitre = new TextField();
		textFieldDate = new TextField();

		textFieldTitre.setPromptText("Titre");
		textFieldDate.setPromptText("Date");

		hBoxTitre.getChildren().addAll(textTitre, textFieldTitre);
		hBoxDate.getChildren().addAll(textDate, textFieldDate);

		HBox hBoxButton = new HBox();
		hBoxButton.setSpacing(20);

		btnDoc = new Button();
		btnDoc.setFont(font(15));

		labelError = new Label("Test");
		labelError.setFont(font(15));
		labelError.setTextFill(Color.RED);

		hBoxButton.getChildren().addAll(btnDoc, labelError);

		if (intChoixDoc == 0) ajouterDoc();
		else if (intChoixDoc == 1) modifierDoc();
		else if (intChoixDoc == 2) supprimerDoc();

		docDVD();

		hBoxComboBoxDocuments.getChildren().addAll(textDoc, comboBoxDocuments);

		vBoxDocInitial.getChildren().addAll(hBoxTitre, hBoxDate);

		vBoxDocuments.getChildren().addAll(vBoxDocInitial, vBoxDocVariable, hBoxButton);

		vBoxCentre.getChildren().addAll(hBoxComboBoxDocuments, vBoxDocuments);

		vBoxMain.getChildren().addAll(text, vBoxCentre);

		root.setCenter(vBoxMain);

		this.sizeToScene();
		this.setResizable(false);
		this.setTitle("Modification d'un document");
		this.setScene(scene);
		this.show();
	}

	private void ajouterDoc() {
		text.setText("Ajouter un document");
		textDoc.setText("Choisisez le type de document a ajouter");

		HBox hBoxNoDoc = new HBox();
		Text textNoDoc = new Text("No Document\t\t\t\t\t\t");
		textNoDoc.setFont(font(15));
		textFieldNoDoc = new TextField();
		textFieldNoDoc.setText("DVD" + intDVDNoDocMax);
		textFieldNoDoc.setEditable(false);
		hBoxNoDoc.getChildren().addAll(textNoDoc, textFieldNoDoc);
		vBoxDocInitial.getChildren().addAll(hBoxNoDoc);

		btnDoc.setText("Ajouter le document");
		btnDoc.setId("0");
	}
	private void modifierDoc() {
		text.setText("Modifier un document");
		textDoc.setText("Choisisez le type de document a modifier");

		HBox hBoxNoDoc = new HBox();
		Text textNoDoc = new Text("No Document\t\t\t\t\t\t");
		textNoDoc.setFont(font(15));
		comboBoxDocumentsNoDoc = new ComboBox<>();
		if (arrDVDSerialise.size() != 0) {
			comboBoxDocumentsNoDoc.setDisable(false);
			comboBoxDocumentsNoDoc.getItems().clear();
			for (DVD dvd : arrDVDSerialise) comboBoxDocumentsNoDoc.getItems().add(dvd.getStrNumDoc());
		}
		else {
			comboBoxDocumentsNoDoc.setDisable(true);
		}
		hBoxNoDoc.getChildren().addAll(textNoDoc, comboBoxDocumentsNoDoc);
		vBoxDocInitial.getChildren().addAll(hBoxNoDoc);

		btnDoc.setText("Modifier le document");
		btnDoc.setId("1");
	}
	private void supprimerDoc() {
		text.setText("Supprimer un document");
		textDoc.setText("Choisisez le type de document a supprimer");

		HBox hBoxNoDoc = new HBox();
		Text textNoDoc = new Text("No Document\t\t\t\t\t\t");
		textNoDoc.setFont(font(15));
		comboBoxDocumentsNoDoc = new ComboBox<>();
		if (arrDVDSerialise.size() != 0) {
			comboBoxDocumentsNoDoc.setDisable(false);
			comboBoxDocumentsNoDoc.getItems().clear();
			for (DVD dvd : arrDVDSerialise) comboBoxDocumentsNoDoc.getItems().add(dvd.getStrNumDoc());
		}
		else {
			comboBoxDocumentsNoDoc.setDisable(true);
		}
		hBoxNoDoc.getChildren().addAll(textNoDoc, comboBoxDocumentsNoDoc);
		vBoxDocInitial.getChildren().addAll(hBoxNoDoc);

		btnDoc.setText("Supprimer le document");
		btnDoc.setId("2");
	}

	private Font font(int intSize) {
        return Font.font("Serif", FontWeight.BOLD, intSize);
    }
	private class GestionComboBoxDocuments implements ChangeListener<String> {
		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			// TODO Auto-generated method stub
			if (vBoxDocVariable != null) {
				vBoxDocVariable.getChildren().clear();
				vBoxCentre.getChildren().remove(vBoxDocVariable);
			}
			if (btnDoc != null) vBoxCentre.getChildren().remove(btnDoc);
			if (labelError != null) vBoxCentre.getChildren().remove(labelError);

			HBox hBoxButton = new HBox();
			hBoxButton.setSpacing(10);

			if (newValue.equals(strDocuments[0])) {
				docDVD();
			}
			else if (newValue.equals(strDocuments[1])) {
				docLivre();
			}
			else if (newValue.equals(strDocuments[2])) {
				docPeriodique();
			}

			if (vBoxDocVariable != null) vBoxCentre.getChildren().add(vBoxDocVariable);

			if (btnDoc != null) hBoxButton.getChildren().add(btnDoc);
			if (labelError != null) hBoxButton.getChildren().add(labelError);

			vBoxCentre.getChildren().add(hBoxButton);
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

		if (btnDoc.getId().equals("0")) textFieldNoDoc.setText((arrDVDSerialise.size() == 0) ? "DVD" + intDVDNoDocMax : "DVD" + (Integer.parseInt(arrDVDSerialise.get(arrDVDSerialise.size()-1).getStrNumDoc().substring(3, arrDVDSerialise.get(arrDVDSerialise.size()-1).getStrNumDoc().length()))+1)); // si c'est pour ajouter, il affiche le dernier numero augemnte de 1 du no document du fichier text ou serialise DVD
		else { // si c'est pour modififer ou supprimer, il affiche soit le texte soit le premier numero du fichier serialise DVD
			if (arrDVDSerialise.size() == 0) {
				comboBoxDocumentsNoDoc.setValue("Aucun nouveau DVD");

				labelError.setText("Aucun nouveau DVD");

				textFieldTitre.setEditable(false);
				textFieldDate.setEditable(false);
				textFieldNomRealisateur.setEditable(false);
				textFieldNbDisque.setEditable(false);

				textFieldTitre.setText(null);
				textFieldDate.setText(null);
				textFieldNomRealisateur.setText(null);
				textFieldNbDisque.setText(null);
			}
			else {
				DVD d = arrDVDSerialise.get(0);

				labelError.setText(null);

				comboBoxDocumentsNoDoc.setValue(d.getStrNumDoc());

				textFieldTitre.setText(d.getStrTitre());
				textFieldDate.setText(d.getStrDate());
				textFieldNomRealisateur.setText(d.getStrNomRealisateur());
				textFieldNbDisque.setText(d.getStrNbDisques());

				textFieldTitre.setEditable(true);
				textFieldDate.setEditable(true);
				textFieldNomRealisateur.setEditable(true);
				textFieldNbDisque.setEditable(true);

				if (btnDoc.getId().equals("2")) {
					textFieldTitre.setEditable(false);
					textFieldDate.setEditable(false);
					textFieldNomRealisateur.setEditable(false);
					textFieldNbDisque.setEditable(false);
				}

				textFieldNomRealisateur.setPromptText("Nom du realisateur");
				textFieldNbDisque.setPromptText("Nombre de disque");
			}
		}

		hBoxNomRealisateur.getChildren().addAll(textNomRealisateur, textFieldNomRealisateur);
		hBoxNbDisque.getChildren().addAll(textNbDisque, textFieldNbDisque);

		vBox.getChildren().addAll(hBoxNomRealisateur, hBoxNbDisque);

		vBoxDocVariable.getChildren().addAll(vBox);

		btnDoc.setOnAction(t -> {
			t.consume();

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
				if (btnDoc.getId().equals("0")) new SerialiseObjets(new DVD((arrDVDSerialise.size() == 0) ? "DVD" + intDVDNoDocMax : "DVD" + (Integer.parseInt(arrDVDSerialise.get(arrDVDSerialise.size()-1).getStrNumDoc().substring(3, arrDVDSerialise.get(arrDVDSerialise.size()-1).getStrNumDoc().length()))+1), textFieldTitre.getText(), textFieldDate.getText(), "0", textFieldNomRealisateur.getText(), textFieldNbDisque.getText())); // envoi le dernier nombre augmente de 1 du no document du fichier text ou serialise DVD et ajoute les champs dans un fichier serialise DVD
				else if (btnDoc.getId().equals("1")) { // remplace les valeurs anciennes avec les nouvelles
					boolean blnTrouve= false;
					for (DVD dvd : arrDVDSerialise) if (comboBoxDocumentsNoDoc.getValue().equals(dvd.getStrNumDoc())) blnTrouve = true;
					if (!blnTrouve) {

					}
				}
				else if (btnDoc.getId().equals("2")) { // supprime le document

				}

				stageThis.close();
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

		if (btnDoc.getId().equals("0")) textFieldNoDoc.setText((arrLivreSerialise.size() == 0) ? "Liv" + intLivresNoDocMax : "Liv" + (Integer.parseInt(arrLivreSerialise.get(arrLivreSerialise.size()-1).getStrNumDoc().substring(3, arrLivreSerialise.get(arrLivreSerialise.size()-1).getStrNumDoc().length()))+1)); // si c'est pour ajouter, il affiche le dernier numero augemnte de 1 du no document du fichier text ou serialise Livres
		else { // si c'est pour modififer ou supprimer, il affiche soit l'erreur soit le premier numero du fichier serialise Livres
			if (arrLivreSerialise.size() == 0) {
				comboBoxDocumentsNoDoc.setValue("Aucun nouveau Livre");

				labelError.setText("Aucun nouveau Livre");

				textFieldTitre.setEditable(false);
				textFieldDate.setEditable(false);
				textFieldNomAuteur.setEditable(false);

				textFieldTitre.setText(null);
				textFieldDate.setText(null);
				textFieldNomAuteur.setText(null);
			}
			else {
				Livres l = arrLivreSerialise.get(0);

				labelError.setText(null);

				comboBoxDocumentsNoDoc.setValue(l.getStrNumDoc());

				textFieldTitre.setText(l.getStrTitre());
				textFieldDate.setText(l.getStrDate());
				textFieldNomAuteur.setText(l.getStrNomAuteur());

				textFieldTitre.setEditable(true);
				textFieldDate.setEditable(true);
				textFieldNomAuteur.setEditable(true);

				if (btnDoc.getId().equals("2")) {
					textFieldTitre.setEditable(false);
					textFieldDate.setEditable(false);
					textFieldNomAuteur.setEditable(false);
				}

				textFieldNomAuteur.setPromptText("Nom de l'auteur");
			}
		}

		hBoxNomAuteur.getChildren().addAll(textNomAuteur, textFieldNomAuteur);

		vBox.getChildren().addAll(hBoxNomAuteur);

		vBoxDocVariable.getChildren().addAll(vBox);

		btnDoc.setOnAction(t -> {
			t.consume();

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

			if (textFieldNomAuteur.getText().trim().equals("")) {
				blnCorrect = false;
				textFieldNomAuteur.setBorder(border(Color.RED));
			}
			else textFieldNomAuteur.setBorder(Border.EMPTY);

			if (blnCorrect) {
				if (btnDoc.getId().equals("0")) new SerialiseObjets(new Livres((arrLivreSerialise.size() == 0) ? "Liv" + intLivresNoDocMax : "Liv" + (Integer.parseInt(arrLivreSerialise.get(arrLivreSerialise.size()-1).getStrNumDoc().substring(3, arrLivreSerialise.get(arrLivreSerialise.size()-1).getStrNumDoc().length()))+1), textFieldTitre.getText(), textFieldDate.getText(), "0", textFieldNomAuteur.getText())); // envoi le dernier nombre augmente de 1 du no document du fichier text ou serialise Livre et ajoute les champs dans un fichier serialise Livre

				stageThis.close();
			}
		});
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

		if (btnDoc.getId().equals("0")) textFieldNoDoc.setText((arrPeriodiquesSerialise.size() == 0) ? "Per" + intPeriodiquesNoDocMax : "Per" + (Integer.parseInt(arrPeriodiquesSerialise.get(arrPeriodiquesSerialise.size()-1).getStrNumDoc().substring(3, arrPeriodiquesSerialise.get(arrPeriodiquesSerialise.size()-1).getStrNumDoc().length()))+1)); // si c'est pour ajouter, il affiche le dernier numero augemnte de 1 du no document du fichier text ou serialise Periodique
		else { // si c'est pour modififer ou supprimer, il affiche soit le texte soit le premier numero du fichier serialise Periodique
			if (arrPeriodiquesSerialise.size() == 0) {
				comboBoxDocumentsNoDoc.setValue("Aucun nouveau Periodique");

				labelError.setText("Aucun nouveau Periodique");

				textFieldTitre.setEditable(false);
				textFieldDate.setEditable(false);
				textFieldVol.setEditable(false);
				textFieldPer.setEditable(false);

				textFieldTitre.setText(null);
				textFieldDate.setText(null);
				textFieldVol.setText(null);
				textFieldPer.setText(null);
			}
			else {
				Periodiques p = arrPeriodiquesSerialise.get(0);

				labelError.setText(null);

				comboBoxDocumentsNoDoc.setValue(p.getStrNumDoc());

				textFieldTitre.setText(p.getStrTitre());
				textFieldDate.setText(p.getStrDate());
				textFieldVol.setText(p.getStrVol());
				textFieldPer.setText(p.getStrPer());

				textFieldTitre.setEditable(true);
				textFieldDate.setEditable(true);
				textFieldVol.setEditable(true);
				textFieldPer.setEditable(true);

				if (btnDoc.getId().equals("2")) {
					textFieldTitre.setEditable(false);
					textFieldDate.setEditable(false);
					textFieldVol.setEditable(false);
					textFieldPer.setEditable(false);
				}

				textFieldVol.setPromptText("No du volume");
				textFieldPer.setPromptText("No periodique");
			}
		}

		hBoxVol.getChildren().addAll(textVol, textFieldVol);
		hBoxPer.getChildren().addAll(textPer, textFieldPer);

		vBox.getChildren().addAll(hBoxVol, hBoxPer);

		vBoxDocVariable.getChildren().addAll(vBox);

		btnDoc.setOnAction(t -> {
			t.consume();

			boolean blnCorrect = true;

			if (textFieldTitre.getText().trim().equals("") || textFieldTitre.getText() == null) { ////////////////////////////// CRASH
				blnCorrect = false;
				textFieldTitre.setBorder(border(Color.RED));
			}
			else textFieldTitre.setBorder(Border.EMPTY);

			if (textFieldDate.getText().trim().equals("") || textFieldDate.getText() == null) {
				blnCorrect = false;
				textFieldDate.setBorder(border(Color.RED));
			}
			else textFieldDate.setBorder(Border.EMPTY);

			if (textFieldVol.getText().trim().equals("") || textFieldVol.getText() == null) {
				blnCorrect = false;
				textFieldVol.setBorder(border(Color.RED));
			}
			else textFieldVol.setBorder(Border.EMPTY);

			if (textFieldPer.getText().trim().equals("") || textFieldPer.getText() == null) {
				blnCorrect = false;
				textFieldPer.setBorder(border(Color.RED));
			}
			else textFieldPer.setBorder(Border.EMPTY);

			if (blnCorrect) {
				if (btnDoc.getId().equals("0")) new SerialiseObjets(new Periodiques((arrPeriodiquesSerialise.size() == 0) ? "Per" + intPeriodiquesNoDocMax : "Per" + (Integer.parseInt(arrPeriodiquesSerialise.get(arrPeriodiquesSerialise.size()-1).getStrNumDoc().substring(3, arrPeriodiquesSerialise.get(arrPeriodiquesSerialise.size()-1).getStrNumDoc().length()))+1), textFieldTitre.getText(), textFieldDate.getText(), "0", textFieldVol.getText(), textFieldPer.getText())); // envoi le dernier nombre augmente de 1 du no document du fichier text ou serialise Livre et ajoute les champs dans un fichier serialise Livre

				stageThis.close();
			}
		});
	}
}
