package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Optional;

//import application.controller.MainController;

public class Login extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) {
		try {
			//MainController.getInstance().init(); //////////////////////////////////////////////////////////////////////////////////////////

			primaryStage.setTitle("Identification");

			BorderPane borderPane = new BorderPane();
			borderPane.setPadding(new Insets(10));

			/*
			 * //Adding HBox HBox hb = new HBox(); hb.setPadding(new
			 * Insets(20,20,20,30)); Label lblMembres = new
			 * Label("Membres du personel"); hb.getChildren().add(lblMembres);
			 * hb.setAlignment(Pos.CENTER);
			 */

			borderPane.setBackground(new Background(new BackgroundImage(new Image("imageLogin.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(0, 0, false, false, false, true /*Recentre l'image et resize*/))));

			ArrayList<Preposes> arrPreposes = DeserialiseObjets.getPreposes(); // static methode
			ArrayList<Adherents> arrAdherents = DeserialiseObjets.getAdherents(); // static methode

			// vBox
			VBox vb = new VBox();
			vb.setAlignment(Pos.CENTER);
			//vb.setSpacing(100);

			// GridPane
			GridPane gridPaneMembres = new GridPane();
			gridPaneMembres.setPadding(new Insets(20));
			gridPaneMembres.setHgap(10);
			gridPaneMembres.setVgap(5);
			
			/*VBox vBoxEmploye = new VBox();
			vBoxEmploye.setAlignment(Pos.CENTER);
			vBoxEmploye.setSpacing(10);
			vBoxEmploye.setBorder(border(Color.WHITE));*/

			GridPane gridAdherent = new GridPane();
			gridAdherent.setPadding(new Insets(20));
			gridAdherent.setHgap(20);
			gridAdherent.setVgap(5);

			// Nodes pour GridPane
			HBox hBoxEmploye = new HBox(), hBoxMDP = new HBox();
			hBoxEmploye.setAlignment(Pos.CENTER);
			hBoxMDP.setAlignment(Pos.CENTER);

			final TextField textFieldEmploye = new TextField();
			textFieldEmploye.setPromptText("No employe");
			//textFieldEmploye.setBackground(Background.EMPTY);
			//textFieldEmploye.setBorder(border(Color.WHITE));
			textFieldEmploye.setFont(font(15));
			//textFieldEmploye.setStyle();
			final PasswordField passwordFieldEmploye = new PasswordField();
			passwordFieldEmploye.setPromptText("Mot de passe");
			passwordFieldEmploye.setFont(font(15));
			Button btnConnexionEmploye = new Button("Connexion", new ImageView(new Image("icons8-enter-80.png", 20, 20, false, false)));
			btnConnexionEmploye.setFont(font(15));

			Button btnCatalogue = new Button("Catalogue", new ImageView(new Image("icons8-descending-sorting-80.png", 20, 20, false, false)));
			btnCatalogue.setFont(font(15));
			Button btnDossiers = new Button("Mon dossier", new ImageView(new Image("icons8-user-folder-80.png", 20, 20, false, false)));
			btnDossiers.setFont(font(15));

			hBoxEmploye.getChildren().add(new ImageView(new Image("icons8-customer-80.png", 25, 28, false, false)));
			hBoxMDP.getChildren().addAll(new ImageView(new Image("icons8-key-80.png", 25, 28, false, false)));

			// Ajout des Nodes au GridPane
			gridPaneMembres.add(hBoxEmploye, 0, 0);
			gridPaneMembres.add(textFieldEmploye, 2, 0);
			gridPaneMembres.add(hBoxMDP, 0, 2);
			gridPaneMembres.add(passwordFieldEmploye, 2, 2);
			gridPaneMembres.add(btnConnexionEmploye, 2, 5);
			gridPaneMembres.setBorder(border(Color.WHITE));
			gridPaneMembres.setPadding(new Insets(10));
			gridPaneMembres.setAlignment(Pos.CENTER);
			
			/*HBox hBoxEmploye = new HBox();
			HBox hBoxMotDePasse = new HBox();
			
			hBoxEmploye.setSpacing(10);
			hBoxMotDePasse.setSpacing(10);
			hBoxEmploye.setAlignment(Pos.CENTER);
			hBoxMotDePasse.setAlignment(Pos.CENTER);
			
			hBoxEmploye.getChildren().addAll(lblEmploye, textFieldEmploye);
			hBoxMotDePasse.getChildren().addAll(lblMDP, passwordFieldEmploye);
			
			vBoxEmploye.getChildren().addAll(hBoxEmploye, hBoxMotDePasse, lblMsg, btnConnexionEmploye);*/

			gridAdherent.add(btnCatalogue, 0, 0);
			gridAdherent.add(btnDossiers, 1, 0);
			gridAdherent.setBorder(border(Color.WHITE));
			gridAdherent.setAlignment(Pos.CENTER);

			//borderPane.setId("borderPane");
			// gridPaneMembres.setId("root");
			//btnConnexionEmploye.setId("btnLogin");

			class classConnexion implements EventHandler<ActionEvent> { ///////////////////////////

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					boolean blnTrouve = false;

					for (Preposes p : arrPreposes) if (textFieldEmploye.getText().toLowerCase().trim().equals(p.getStrNoEmploye().toLowerCase())
							&& passwordFieldEmploye.getText().equals(p.getStrMotDePasse())) {
						blnTrouve = true;

						gridPaneMembres.setBorder(border(Color.WHITE));
						//vBoxEmploye.setBorder(border(Color.WHITE));

						new MenuPrincipal(primaryStage).show();
						primaryStage.close();
					}

					if (!blnTrouve) {
						// shake animation en cas d'erreur
						final int[] x = {0};
						final int[] y = {0};

						Timeline timelineX = new Timeline(new KeyFrame(Duration.seconds(0.1), t -> {
							if (x[0] == 0) {
								primaryStage.setX(primaryStage.getX() + 10);
								x[0] = 1;
								gridPaneMembres.setBorder(border(Color.RED));
							} else {
								primaryStage.setX(primaryStage.getX() - 10);
								x[0] = 0;
								gridPaneMembres.setBorder(border(Color.WHITE));
							}
						}));

						timelineX.setCycleCount(6);
						timelineX.setAutoReverse(false);
						timelineX.play();

						Timeline timelineY = new Timeline(new KeyFrame(Duration.seconds(0.1), t -> {
							if (y[0] == 0) {
								primaryStage.setY(primaryStage.getY() + 10);
								y[0] = 1;
								gridPaneMembres.setBorder(border(Color.RED));
							} else {
								primaryStage.setY(primaryStage.getY() - 10);
								y[0] = 0;
								gridPaneMembres.setBorder(border(Color.WHITE));
							}
						}));

						timelineY.setCycleCount(6);
						timelineY.setAutoReverse(false);
						timelineY.play();
					}

					passwordFieldEmploye.setText("");
				}
			}

			textFieldEmploye.setOnAction(new classConnexion());
			passwordFieldEmploye.setOnAction(new classConnexion());
			btnConnexionEmploye.setOnAction(new classConnexion());

			btnCatalogue.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					//new MenuCatalog(primaryStage).show();
					primaryStage.close();

				}
			});
			vb.getChildren().addAll(gridPaneMembres /*vBoxEmploye*/, gridAdherent);
			vb.setSpacing(10);
			borderPane.setCenter(vb);

			Scene scene = new Scene(borderPane, 420, 280);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);
			//primaryStage.titleProperty().bind(scene.widthProperty().asString().concat(" : ").concat(scene.heightProperty().asString()));
			primaryStage.setResizable(false);
			primaryStage.show();

			primaryStage.setOnCloseRequest(e -> {
				e.consume();
				fermerProgramme(primaryStage);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Border border(Color color) {
		return new Border(new BorderStroke(color,
				new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.BEVEL, StrokeLineCap.BUTT, 10, 0, null),
				CornerRadii.EMPTY, new BorderWidths(2)));
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
}
