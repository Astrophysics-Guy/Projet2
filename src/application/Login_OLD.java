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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Login_OLD extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Pour la liste d'adherent qui peuvent se connecter
             ArrayList<String> arrLstAdherentAjoute = new ArrayList<>(), arrLstAdherentDisponible = new ArrayList<String>(Arrays.asList("Guelleh", "Guelleh", "cyan", "rouge", "gris", "vert", "rose", "jaune"));

             VBox vBoxLogin;
             
             ArrayList<Preposes> arrPreposes = DeserialiseObjets.getPreposes(); // static methode
             ArrayList<Adherents> arrAdherents = DeserialiseObjets.getAdherents(); // static methode
             
             TextField textFieldEmploye, textFieldAdherent;
             PasswordField passwordFieldEmploye;
             Label lblMsgPrepose, lblMsgAdherent;
            
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root,675,450); // 1.5 aspect ratio
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            
            Image imageLogin = new Image("imageLogin.jpg");
            
            root.setBackground(new Background(new BackgroundImage(imageLogin, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));            
            // vBoxLogin
            vBoxLogin = new VBox();
            vBoxLogin.setPadding(new Insets(20));
            vBoxLogin.setSpacing(75);
            vBoxLogin.setAlignment(Pos.TOP_CENTER);
            
            // hBoxLogin
            HBox hBoxLogin = new HBox();
            hBoxLogin.setSpacing(50);
            hBoxLogin.setAlignment(Pos.TOP_CENTER);

            Text textTitre = new Text("Veillez vous identifier S.V.P.");
            textTitre.setFont(font(30));
            textTitre.setTextAlignment(TextAlignment.CENTER);
            textTitre.setFill(Color.WHITE);
            
            ////////////////////////////////////////////////////////////////////////////////////////// vBox pour menuPrepose
            VBox vBoxPrepose = new VBox();
            vBoxPrepose.setPadding(new Insets(10));
            vBoxPrepose.setAlignment(Pos.TOP_RIGHT);
            vBoxPrepose.setSpacing(20);
            vBoxPrepose.setBorder(border(color(true)));

            Text textEmploye = new Text("No Prepose\t"), textMotDePasse = new Text("Mot de Passe\t");
            textEmploye.setFill(Color.WHITE);
            textMotDePasse.setFill(Color.WHITE);
            
            textEmploye.setFont(font(15));
            textMotDePasse.setFont(font(15));

            textFieldEmploye = new TextField();
            passwordFieldEmploye = new PasswordField();

            textFieldEmploye.setPromptText("No Prepose");
            passwordFieldEmploye.setPromptText("Mot de passe");

            // radioButton
            /*ToggleGroup toggleGroup = new ToggleGroup();

            RadioButton radioButtonLstAdherent = new RadioButton("Ajouter et/ou enlever des adherents"), radioButtonTableView = new RadioButton("Voir la table");
            radioButtonLstAdherent.setFont(font(15));
            radioButtonTableView.setFont(font(15));

            radioButtonLstAdherent.setToggleGroup(toggleGroup);
            radioButtonTableView.setToggleGroup(toggleGroup);
            radioButtonLstAdherent.setSelected(true);*/

            lblMsgPrepose = new Label();
            lblMsgPrepose.setFont(font(15));
            HBox hBox1Prepose = new HBox(), hBox2Prepose = new HBox(), hBox3Prepose = new HBox();
            VBox vBoxRadioButton = new VBox();
            
            Button btnConnexionPrepose = new Button("Connexion");
            btnConnexionPrepose.setFont(font(15));

            hBox1Prepose.getChildren().addAll(textEmploye, textFieldEmploye);
            hBox1Prepose.setSpacing(10);
            hBox2Prepose.getChildren().addAll(textMotDePasse, passwordFieldEmploye);
            hBox2Prepose.setSpacing(10);
            hBox3Prepose.getChildren().addAll(lblMsgPrepose);
            vBoxRadioButton.setSpacing(10);
            vBoxPrepose.setAlignment(Pos.CENTER);

            // ajout des elements dans le vBoxPrepose
            vBoxPrepose.getChildren().addAll(hBox1Prepose, hBox2Prepose, hBox3Prepose, btnConnexionPrepose);
            ////////////////////////////////////////////////////////////////////////////////////////// fin du vBox pour menuPrepose
            
            ////////////////////////////////////////////////////////////////////////////////////////// vBox pour menuAdherent
            VBox vBoxAdherent = new VBox();
            vBoxAdherent.setPadding(new Insets(10));
            vBoxAdherent.setAlignment(Pos.TOP_LEFT);
            vBoxAdherent.setSpacing(20);
            vBoxAdherent.setBorder(border(color(true)));
            vBoxAdherent.setAlignment(Pos.CENTER);
            
            Text textAdherent = new Text("No Adherent"), textNoTel = new Text("No de telephone\t"), textNom = new Text("Nom\t\t\t"), textPrenom = new Text("Prenom\t\t\t"), textOU = new Text("OU");
            textNoTel.setFill(Color.WHITE);
            textNom.setFill(Color.WHITE);
            textPrenom.setFill(Color.WHITE);
            textOU.setFill(Color.WHITE);
            
            textAdherent.setFont(font(15));
            textNoTel.setFont(font(15));
            textNom.setFont(font(15));
            textPrenom.setFont(font(15));
            textOU.setFont(font(15));

            textFieldAdherent = new TextField();
            TextField textFieldNoTel = new TextField(), textFieldNom = new TextField(), textFieldPrenom = new TextField();

            textFieldAdherent.setPromptText("No Adherent");
            textFieldNoTel.setPromptText("No de telephone");
            textFieldNom.setPromptText("Nom");
            textFieldPrenom.setPromptText("Prenom");

            lblMsgAdherent = new Label();
            lblMsgAdherent.setFont(font(15));
            HBox hBox1 = new HBox(), hBox2 = new HBox(), hBox3 = new HBox(), hBox4 = new HBox();
            
            hBox1.setSpacing(10);
            hBox2.setSpacing(10);
            hBox3.setSpacing(10);
            hBox4.setSpacing(10);
            
            hBox1.setAlignment(Pos.CENTER);
            //hBox2.setAlignment(Pos.TOP_LEFT);
            //hBox3.setAlignment(Pos.CENTER);
            hBox4.setAlignment(Pos.CENTER);

            hBox1.getChildren().addAll(textNoTel, textFieldNoTel);
            hBox2.getChildren().addAll(textPrenom, textFieldPrenom);
            hBox3.getChildren().addAll(textNom, textFieldNom);
            
            Button btnConnexionAdherent = new Button("Mon Dossier"), btnConnexionCatalogue = new Button("Catalogue");
            
            btnConnexionAdherent.setFont(font(15));
            btnConnexionCatalogue.setFont(font(15));
            
            hBox4.getChildren().addAll(btnConnexionAdherent, btnConnexionCatalogue);
            
            vBoxAdherent.getChildren().addAll(hBox1, textOU, hBox2, hBox3, lblMsgAdherent, hBox4);
            ////////////////////////////////////////////////////////////////////////////////////////// fin du vBox pour menuAdherent
            
            class classConnexionEmploye implements EventHandler<ActionEvent> { // lorsque l'utilisateur est un employe et veut se connecter

                @Override
                public void handle(ActionEvent event) {
                	boolean blnTrouver = false;
                    for (Preposes preposes : arrPreposes) if (passwordFieldEmploye.getText().trim().equals(preposes.getStrMotDePasse().trim()) && textFieldEmploye.getText().trim().equals(preposes.getStrNoEmploye().trim())) {
                    	blnTrouver = true;
                    	
                    	lblMsgPrepose.setText(null);
                        
                        vBoxPrepose.setBorder(border(color(true)));

                        primaryStage.close();
                        
                        /*System.out.println("arrLstAdherentAjoute");
                        for (String s : arrLstAdherentAjoute) System.out.println(s);
                        System.out.println();*/
                        
                        new MenuPrincipal_BackUp(textFieldEmploye, primaryStage).show();
                    }
                    
                    if (!blnTrouver) {
                    	lblMsgPrepose.setText("No prepose ou mot de passe incorrecte");
                    	lblMsgPrepose.setTextFill(Color.rgb(210, 39, 30));
                        
                        vBoxPrepose.setBorder(border(color(false)));

                        // shake animation en cas d'erreur
                        final int[] x = {0};
                        final int[] y = {0};

                        Timeline timelineX = new Timeline(new KeyFrame(Duration.seconds(0.1), t -> {
                            if (x[0] == 0) {
                                primaryStage.setX(primaryStage.getX() + 10);
                                x[0] = 1;
                            } else {
                                primaryStage.setX(primaryStage.getX() - 10);
                                x[0] = 0;
                            }
                        }));

                        timelineX.setCycleCount(6);
                        timelineX.setAutoReverse(false);
                        timelineX.play();


                        Timeline timelineY = new Timeline(new KeyFrame(Duration.seconds(0.1), t -> {
                            if (y[0] == 0) {
                                primaryStage.setY(primaryStage.getY() + 10);
                                y[0] = 1;
                            } else {
                                primaryStage.setY(primaryStage.getY() - 10);
                                y[0] = 0;
                            }
                        }));

                        timelineY.setCycleCount(6);
                        timelineY.setAutoReverse(false);
                        timelineY.play();
                    }

                    passwordFieldEmploye.clear();
                }
            }
            class classConnexionAdherent implements EventHandler<ActionEvent> { // lorsque l'utilisateur est un adherent et veut se connecter

        		@Override
        		public void handle(ActionEvent event) {
        			Collections.sort(arrLstAdherentAjoute);

        			boolean blnTrouver = false;
        			for (Adherents adherents : arrAdherents) if (textFieldNoTel.getText().trim().equals(adherents.getTel().trim()) || (textFieldNom.getText().trim().equals(adherents.getNom().trim()) && textFieldPrenom.getText().trim().equals(adherents.getPrenom().trim()))) {
    					blnTrouver = true;
    					
    					lblMsgAdherent.setText(null);
    					
    					vBoxAdherent.setBorder(border(color(true)));

    					primaryStage.close();
    					
    					 new MenuPrincipal_BackUp(textFieldEmploye, primaryStage).show();
    				}
        			
        			if (!blnTrouver) { // il n'a trouve aucun adherent dans la liste qui correspond
        				lblMsgAdherent.setText("No d'adherent incorrecte");
        				lblMsgAdherent.setTextFill(Color.rgb(210, 39, 30));
        				
        				vBoxAdherent.setBorder(border(color(false)));
        				
        				// shake animation en cas d'erreur
                        final int[] x = {0};
                        final int[] y = {0};

                        Timeline timelineX = new Timeline(new KeyFrame(Duration.seconds(0.1), t -> {
                            if (x[0] == 0) {
                                primaryStage.setX(primaryStage.getX() + 10);
                                x[0] = 1;
                            } else {
                                primaryStage.setX(primaryStage.getX() - 10);
                                x[0] = 0;
                            }
                        }));

                        timelineX.setCycleCount(6);
                        timelineX.setAutoReverse(false);
                        timelineX.play();


                        Timeline timelineY = new Timeline(new KeyFrame(Duration.seconds(0.1), t -> {
                            if (y[0] == 0) {
                                primaryStage.setY(primaryStage.getY() + 10);
                                y[0] = 1;
                            } else {
                                primaryStage.setY(primaryStage.getY() - 10);
                                y[0] = 0;
                            }
                        }));

                        timelineY.setCycleCount(6);
                        timelineY.setAutoReverse(false);
                        timelineY.play();
        			}
        		}
        	}

            textFieldEmploye.setOnAction(new classConnexionEmploye());
            passwordFieldEmploye.setOnAction(new classConnexionEmploye());
            
            //textFieldAdherent.setOnAction(new classConnexionAdherent());
            textFieldNoTel.setOnAction(new classConnexionAdherent());
            textFieldNom.setOnAction(new classConnexionAdherent());
            textFieldPrenom.setOnAction(new classConnexionAdherent());
            
            btnConnexionPrepose.setOnAction(new classConnexionEmploye());
            btnConnexionAdherent.setOnAction(new classConnexionAdherent());
            
            hBoxLogin.getChildren().addAll(vBoxAdherent, vBoxPrepose);
            vBoxLogin.getChildren().addAll(textTitre, hBoxLogin);

            root.setCenter(vBoxLogin);

            primaryStage.sizeToScene();
            primaryStage.setResizable(false);
            //this.setMaximized(true);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Bienvenu a la mediatheque");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private Font font(int intSize) {
        return Font.font("Serif", FontWeight.BOLD, intSize);
    }
    private Color color(boolean blnGreen) {
    	return ((blnGreen) ? Color.rgb(21, 117, 84) : Color.rgb(210, 39, 30));
    }
    private Border border(Color color) {
    	return new Border(new BorderStroke(color, new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.BEVEL, StrokeLineCap.BUTT, 10, 0, null), CornerRadii.EMPTY,  new BorderWidths(6)));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
