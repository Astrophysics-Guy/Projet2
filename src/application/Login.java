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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Login extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Pour la liste d'adherent qui peuvent se connecter
             ArrayList<String> arrLstAdherentAjoute = new ArrayList<>();

             VBox vBoxLogin;

             Text textTitre, textEmploye, textMotDePasse, textAdherent;
             TextField textFieldEmploye, textFieldAdherent;
             PasswordField passwordFieldEmploye;
             Label lblMsg;
            
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root,675,450); // 1.5 aspect ratio
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            // vBoxLogin
            vBoxLogin = new VBox();
            vBoxLogin.setPadding(new Insets(15));
            vBoxLogin.setSpacing(20);
            vBoxLogin.setAlignment(Pos.TOP_CENTER);

            VBox vBoxMain = new VBox();
            vBoxMain.setPadding(new Insets(15));
            vBoxMain.setAlignment(Pos.TOP_CENTER);
            vBoxMain.setSpacing(30);

            textTitre = new Text("Veillez vous identifier S.V.P.");
            textTitre.setFont(font(30));
            textTitre.setTextAlignment(TextAlignment.CENTER);

            textEmploye = new Text("No Employe  ");
            textMotDePasse = new Text("Mot de Passe");

            textEmploye.setFont(font(15));
            textMotDePasse.setFont(font(15));

            textFieldEmploye = new TextField();
            passwordFieldEmploye = new PasswordField();

            textFieldEmploye.setPromptText("No Employe");
            passwordFieldEmploye.setPromptText("Mot de passe");

            ToggleGroup toggleGroup = new ToggleGroup();

            RadioButton radioButtonLstAdherent = new RadioButton("Ajouter et/ou enlever des adherents"), radioButtonTableView = new RadioButton("Voir la table");

            radioButtonLstAdherent.setToggleGroup(toggleGroup);
            radioButtonTableView.setToggleGroup(toggleGroup);
            radioButtonLstAdherent.setSelected(true);

            lblMsg = new Label();
            lblMsg.setFont(font(15));
            HBox hBox1 = new HBox(), hBox2 = new HBox(), hBox3 = new HBox();

            hBox1.getChildren().addAll(textEmploye, textFieldEmploye);
            hBox1.setSpacing(10);
            hBox2.getChildren().addAll(textMotDePasse, passwordFieldEmploye);
            hBox2.setSpacing(10);
            hBox3.getChildren().addAll(lblMsg);

            vBoxMain.getChildren().addAll(hBox1, hBox2, hBox3);

            class classConnexionEmploye implements EventHandler<ActionEvent> {

                @Override
                public void handle(ActionEvent event) {
                    if (passwordFieldEmploye.getText().trim().equals("10101997") && textFieldEmploye.getText().trim().equals("Guelleh")) {
                        /*lblMsg.setText("Connecte en tant que prepose " + textFieldEmploye.getText());
                        lblMsg.setTextFill(Color.rgb(21, 117, 84));*/

                        /*textFieldEmploye.setDisable(true);
                        passwordFieldEmploye.setDisable(true);*/

                        //connexion(true);

                        lblMsg.setText(null);

                        //new MenuPrincipal(textFieldEmploye, lblMsg, primaryStage, arrDVD, arrLivres, arrPeriodiques, arrLstAdherentAjoute).show();

                        primaryStage.close();
                    }
                    else {
                        lblMsg.setText("No d'employe ou mot de passe incorrecte");
                        lblMsg.setTextFill(Color.rgb(210, 39, 30));

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

            textFieldEmploye.setOnAction(new classConnexionEmploye());
            passwordFieldEmploye.setOnAction(new classConnexionEmploye());

            vBoxLogin.getChildren().addAll(textTitre, vBoxMain);

            root.setCenter(vBoxLogin);

            //setOnAction(menuItemPrepose, menuItemAdherent, menuItemAide);

            primaryStage.sizeToScene();
            //primaryStage.setResizable(false);
            //this.setMaximized(true);
            //primaryStage.setFullScreen(true); // non pour mtn
            primaryStage.setScene(scene);
            primaryStage.setTitle("Login");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private Font font(int intSize) {
        return Font.font("Serif", FontWeight.BOLD, intSize);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
