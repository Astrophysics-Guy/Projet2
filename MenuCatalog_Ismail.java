package application;

import application.view.DocumentTableView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MenuCatalog extends Stage {
    // Fichier ressource

    String strUtilisateur = null;
    private DocumentTableView documentTable = new DocumentTableView("all", strUtilisateur);
    private DocumentTableView dvdTable = new DocumentTableView("DVD", strUtilisateur);
    private DocumentTableView livreTable = new DocumentTableView("LIV", strUtilisateur);
    private DocumentTableView priodiqueTable = new DocumentTableView("PER", strUtilisateur);

// utilise
    // ubuntu
    // Pour l'interface
    private MenuBar menuBar;

    private VBox vBox;

    private Text textTitre;
    // le stage du login
    private Stage primaryStage;
    private Scene scene; ///////////////////////////////////////////////////////////

    public MenuCatalog(Stage primaryStage) { // qd le
        // prepose
        // se
        // connecte
        // et veut
        // afficher
        // la table
        // view
        this.primaryStage = primaryStage;

        textTitre = new Text("CATALOG");

        MenuCatalog();

    }

    private Font font(int intSize) {
        return Font.font("Serif", FontWeight.BOLD, intSize);
    }

    public void MenuCatalog() {

        BorderPane root = new BorderPane();
        scene = new Scene(root, 1200, 900); // 1.5 aspect ratio
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        // Bar
        menuBar = new MenuBar();
        Menu menuAide = new Menu("_Aide");

        menuAide.setMnemonicParsing(true);

        // Items
        MenuItem menuItemAide = new MenuItem("A_ide");

        // Ajout des menus
        menuAide.getItems().add(menuItemAide);
        menuBar.getMenus().addAll(menuAide);

        // vBox
        vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.TOP_LEFT);

        VBox vBoxMain = new VBox();
        vBoxMain.setPadding(new Insets(10));
        vBoxMain.setAlignment(Pos.TOP_CENTER);
        vBoxMain.setSpacing(50);

        textTitre.setFont(font(30));
        textTitre.setTextAlignment(TextAlignment.CENTER);

        TabPane tabPane = new TabPane();

        BorderPane borderPane = new BorderPane();

        Tab tabCollection = new Tab("Collection");
        final VBox collVbox = new VBox();
        collVbox.setSpacing(5);
        collVbox.setPadding(new Insets(10, 0, 0, 10));
        documentTable.chargerDonnees();
        collVbox.getChildren().addAll(documentTable);
        tabCollection.setContent(collVbox);

        Tab tabLivres = new Tab("Livres");
        final VBox livVbox = new VBox();
        livVbox.setSpacing(5);
        livVbox.setPadding(new Insets(10, 0, 0, 10));
        livreTable.chargerDonnees();
        livVbox.getChildren().addAll(livreTable);
        tabLivres.setContent(livVbox);

        Tab tabDVD = new Tab("DVDs");
        final VBox dvdVbox = new VBox();
        dvdVbox.setSpacing(5);
        dvdVbox.setPadding(new Insets(10, 0, 0, 10));
        dvdTable.chargerDonnees();
        dvdVbox.getChildren().addAll(dvdTable);
        tabDVD.setContent(dvdVbox);

        Tab tabPeriodique = new Tab("Periodiques");
        final VBox perVbox = new VBox();
        perVbox.setSpacing(5);
        perVbox.setPadding(new Insets(10, 0, 0, 10));
        priodiqueTable.chargerDonnees();
        perVbox.getChildren().addAll(priodiqueTable);
        tabPeriodique.setContent(perVbox);

        tabPane.getTabs().addAll(tabCollection, tabLivres, tabPeriodique, tabDVD);

        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        // bind to take available space
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());

        borderPane.setCenter(tabPane);
        root.getChildren().add(borderPane);

        vBox.getChildren().addAll(borderPane);

        vBoxMain.getChildren().addAll(vBox);

        root.setTop(menuBar);
        root.setCenter(vBoxMain);

        this.sizeToScene();
        this.setResizable(false);
        this.setScene(scene);
        this.setTitle("Mediatheque - © 2017 Ismail El Khattab & Mohammed Guelleh");
        this.show();

        menuItemAide.setOnAction(event -> {

        });
    }

}
