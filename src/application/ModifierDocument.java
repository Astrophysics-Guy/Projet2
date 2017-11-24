package application;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ModifierDocument extends Application {
private String[] strDocuments = {"DVD", "Livres", "Periodiques"};
	
	private Text textTitre, textDate/*, textPret*/;
	private TextField textFieldTitre, textFieldDate/*, textFieldPret*/;
	
	private VBox vBoxCentre, vBoxDocVariable;
	
	private Button btnAjouterDoc;
	
	private ComboBox<String> comboBox;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,675,450); // 1.5 aspect ratio
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		// vBoxCentre
		VBox vBoxMain = new VBox();
		vBoxMain.setPadding(new Insets(15));
		vBoxMain.setAlignment(Pos.TOP_CENTER);
		vBoxMain.setSpacing(50);
		
		Text text = new Text("Modifier un document");
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
		
		Text textDoc = new Text("Choisisez le type de document a modifier");
		textDoc.setFont(font(15));
		
		comboBox = new ComboBox<>();
		comboBox.setValue("DVD");
		comboBox.getItems().addAll("DVD", "Livres", "Periodiques");
		comboBox.valueProperty().addListener(new GestionComboBox());
		
		HBox hBoxComboBox = new HBox();
		hBoxComboBox.setSpacing(10);
		
		HBox hBoxTitre = new HBox(), hBoxDate = new HBox()/*, hBoxPret = new HBox()*/;
		hBoxTitre.setSpacing(10);
		hBoxDate.setSpacing(10);
		//hBoxPret.setSpacing(10);
		
		textTitre = new Text("Titre\t\t\t\t\t\t\t");
		textDate = new Text("Date\t\t\t\t\t\t\t\t");
		//textPret = new Text("Pret\t\t\t\t\t\t");
		
		textTitre.setFont(font(15));
		textDate.setFont(font(15));
		//textPret.setFont(font(15));
		
		textFieldTitre = new TextField();
		textFieldDate = new TextField();
		//textFieldPret = new TextField();
		
		textFieldTitre.setPromptText("Titre");
		textFieldDate.setPromptText("Date");
		//textFieldPret.setPromptText("Pret");
		
		hBoxTitre.getChildren().addAll(textTitre, textFieldTitre);
		hBoxDate.getChildren().addAll(textDate, textFieldDate);
		//hBoxPret.getChildren().addAll(textPret, textFieldPret);
		
		btnAjouterDoc = new Button("Modifier le document");
		btnAjouterDoc.setFont(font(15));
		
		docDVD();
		
		hBoxComboBox.getChildren().addAll(textDoc, comboBox);
		
		vBoxDocInitial.getChildren().addAll(hBoxTitre, hBoxDate/*, hBoxPret*/);
		
		vBoxDocuments.getChildren().addAll(vBoxDocInitial, vBoxDocVariable, btnAjouterDoc);
		
		vBoxCentre.getChildren().addAll(hBoxComboBox, vBoxDocuments);
		
		vBoxMain.getChildren().addAll(text, vBoxCentre);

		root.setCenter(vBoxMain);

		primaryStage.sizeToScene();
		//primaryStage.setResizable(false);
		//primaryStage.setMaximized(true);
		//primaryStage.setFullScreen(true); // non pour mtn
		primaryStage.setTitle("Modifier un document");
		primaryStage.setScene(scene);

		primaryStage.show();
	}
	
	public static void main(String[] args) {
        launch(args);
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
			if (btnAjouterDoc != null) vBoxCentre.getChildren().remove(btnAjouterDoc);
			
			if (newValue.equals(strDocuments[0])) {
				docDVD();
				
				btnAjouterDoc.setOnAction(t -> {
					
				});
			}
			else if (newValue.equals(strDocuments[1])) {
				docLivre();
				
				btnAjouterDoc.setOnAction(t -> {
					
				});
			}
			else if (newValue.equals(strDocuments[2])) {
				docPeriodique();
				
				btnAjouterDoc.setOnAction(t -> {
					
				});
			}
			
			if (vBoxDocVariable != null) vBoxCentre.getChildren().addAll(vBoxDocVariable);
			if (btnAjouterDoc != null) vBoxCentre.getChildren().add(btnAjouterDoc);
		}
	}
	private Border border(Color color) {
    	return new Border(new BorderStroke(color, new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.BEVEL, StrokeLineCap.BUTT, 10, 0, null), CornerRadii.EMPTY,  new BorderWidths(6)));
    }
	
	private void docDVD() {
		btnAjouterDoc.setOnAction(t -> {
			textFieldDate.setText(textFieldDate.getText() + " " + comboBox.getValue());
		});
		
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
}
