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

public class Login_BackUp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Pour la liste d'adherent qui peuvent se connecter
             ArrayList<String> arrLstAdherentAjoute = new ArrayList<>(), arrLstAdherentDisponible = new ArrayList<String>(Arrays.asList("Guelleh", "Guelleh", "cyan", "rouge", "gris", "vert", "rose", "jaune"));

             VBox vBoxLogin;

             Text textTitre, textEmploye, textMotDePasse, textAdherent;
             TextField textFieldEmploye, textFieldAdherent;
             PasswordField passwordFieldEmploye;
             Label lblMsgPrepose, lblMsgAdherent;
            
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root,675,450); // 1.5 aspect ratio
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            // vBoxLogin
            vBoxLogin = new VBox();
            vBoxLogin.setPadding(new Insets(20));
            vBoxLogin.setSpacing(75);
            vBoxLogin.setAlignment(Pos.TOP_CENTER);
            
            // hBoxLogin
            HBox hBoxLogin = new HBox();
            hBoxLogin.setSpacing(50);
            hBoxLogin.setAlignment(Pos.TOP_CENTER);

            textTitre = new Text("Veillez vous identifier S.V.P.");
            textTitre.setFont(font(30));
            textTitre.setTextAlignment(TextAlignment.CENTER);
            
            ////////////////////////////////////////////////////////////////////////////////////////// vBox pour menuPrepose
            VBox vBoxPrepose = new VBox();
            vBoxPrepose.setPadding(new Insets(10));
            vBoxPrepose.setAlignment(Pos.TOP_RIGHT);
            vBoxPrepose.setSpacing(20);
            vBoxPrepose.setBorder(border(color(true)));

            textEmploye = new Text("No Prepose   ");
            textMotDePasse = new Text("Mot de Passe");

            textEmploye.setFont(font(15));
            textMotDePasse.setFont(font(15));

            textFieldEmploye = new TextField();
            passwordFieldEmploye = new PasswordField();

            textFieldEmploye.setPromptText("No Prepose");
            passwordFieldEmploye.setPromptText("Mot de passe");

            // radioButton
            ToggleGroup toggleGroup = new ToggleGroup();

            RadioButton radioButtonLstAdherent = new RadioButton("Ajouter et/ou enlever des adherents"), radioButtonTableView = new RadioButton("Voir la table");
            radioButtonLstAdherent.setFont(font(15));
            radioButtonTableView.setFont(font(15));

            radioButtonLstAdherent.setToggleGroup(toggleGroup);
            radioButtonTableView.setToggleGroup(toggleGroup);
            radioButtonLstAdherent.setSelected(true);

            lblMsgPrepose = new Label();
            lblMsgPrepose.setFont(font(15));
            HBox hBox1Prepose = new HBox(), hBox2Prepose = new HBox(), hBox3Prepose = new HBox();
            VBox vBoxRadioButton = new VBox();

            hBox1Prepose.getChildren().addAll(textEmploye, textFieldEmploye);
            hBox1Prepose.setSpacing(10);
            hBox2Prepose.getChildren().addAll(textMotDePasse, passwordFieldEmploye);
            hBox2Prepose.setSpacing(10);
            hBox3Prepose.getChildren().addAll(lblMsgPrepose);
            vBoxRadioButton.getChildren().addAll(radioButtonLstAdherent, radioButtonTableView);
            vBoxRadioButton.setSpacing(10);

            // ajout des elements dans le vBoxPrepose
            vBoxPrepose.getChildren().addAll(hBox1Prepose, hBox2Prepose, vBoxRadioButton, hBox3Prepose);
            ////////////////////////////////////////////////////////////////////////////////////////// fin du vBox pour menuPrepose
            
            ////////////////////////////////////////////////////////////////////////////////////////// vBox pour menuAdherent
            VBox vBoxAdherent = new VBox();
            vBoxAdherent.setPadding(new Insets(10));
            vBoxAdherent.setAlignment(Pos.TOP_LEFT);
            vBoxAdherent.setSpacing(20);
            vBoxAdherent.setBorder(border(color(true)));

            textAdherent = new Text("No Adherent");

            textAdherent.setFont(font(15));

            textFieldAdherent = new TextField();

            textFieldAdherent.setPromptText("No Adherent");

            lblMsgAdherent = new Label();
            lblMsgAdherent.setFont(font(15));
            HBox hBox1 = new HBox(), hBox2 = new HBox();

            hBox1.getChildren().addAll(textAdherent, textFieldAdherent);
            hBox1.setSpacing(10);
            hBox2.getChildren().add(lblMsgAdherent);

            vBoxAdherent.getChildren().addAll(hBox1, hBox2);
            ////////////////////////////////////////////////////////////////////////////////////////// fin du vBox pour menuAdherent
            
            class classConnexionEmploye implements EventHandler<ActionEvent> { // lorsque l'utilisateur est un employe et veut se connecter

                @Override
                public void handle(ActionEvent event) {
                    if (passwordFieldEmploye.getText().trim().equals("10101997") && textFieldEmploye.getText().trim().equals("Guelleh")) {
                    	lblMsgPrepose.setText(null);
                        
                        vBoxPrepose.setBorder(border(color(true)));

                        primaryStage.close();
                        
                        System.out.println("arrLstAdherentAjoute");
                        for (String s : arrLstAdherentAjoute) System.out.println(s);
                        System.out.println();
                        
                        //if (radioButtonLstAdherent.isSelected()) new MenuPrincipal_OLD(textFieldEmploye, primaryStage, arrLstAdherentAjoute, arrLstAdherentDisponible).show();
                        //else  new MenuPrincipal_OLD(textFieldEmploye, primaryStage).show();
                    }
                    else {
                    	lblMsgPrepose.setText("No d'employe ou mot de passe incorrecte");
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

        			boolean blnFor = false;
        			for (String strAdherent : arrLstAdherentAjoute) {
        				if (textFieldAdherent.getText().trim().equals(strAdherent)) {
        					blnFor = true;
        					
        					lblMsgAdherent.setText(null);
        					
        					vBoxAdherent.setBorder(border(color(true)));

        					primaryStage.close();
        					
        					new MenuPrincipal_OLD(primaryStage, textFieldAdherent).show();
        				}
        			}

        			if (!blnFor) { // il n'a trouve aucun adherent dans la liste qui correspond
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
            
            textFieldAdherent.setOnAction(new classConnexionAdherent());

            hBoxLogin.getChildren().addAll(vBoxAdherent, vBoxPrepose);
            vBoxLogin.getChildren().addAll(textTitre, hBoxLogin);

            root.setCenter(vBoxLogin);

            primaryStage.sizeToScene();
            primaryStage.setResizable(false);
            //this.setMaximized(true);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Bienvenu a la mediatheque BackUp");
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
