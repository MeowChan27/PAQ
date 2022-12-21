package com.example.paq;

import com.example.paq.fr.isep.game7WonderArch.domain.Game;
import com.example.paq.fr.isep.game7WonderArch.domain.Joueur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.DODGERBLUE;

public class  BoardController implements Initializable {

    @FXML
    Pane pane;

    private Button btnPiocher = new Button("Piocher");

    private final int widthWonderImageView = 200/2;
    private final int heightWonderImageView = 292/2;
    private final int numImages = Game.getNbrPlayer();

    HashMap<Integer, Joueur> hashMapTourDeJeuPlayer = new HashMap<>();

    public static Image chargeImage(String url) throws Exception{
        return new Image(Objects.requireNonNull(HelloApplication.class.getResource(url)).openStream());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Circle
        Circle circle = new Circle((pane.getPrefWidth()/2), (pane.getPrefHeight()/2), 350);
        circle.setFill(DODGERBLUE);
        circle.setOpacity(0.6);
        pane.getChildren().add(circle);

        // Button
        pane.getChildren().add(btnPiocher);
        btnPiocher.setPrefWidth(150);
        btnPiocher.setPrefHeight(5);
        btnPiocher.setLayoutX((pane.getPrefWidth()-btnPiocher.getPrefWidth())/2);
        btnPiocher.setLayoutY((pane.getPrefHeight()-btnPiocher.getPrefHeight())/2);
        btnPiocher.setOnAction(this::piocher);

        // définir le nombre d'images et leur distance

        ArrayList <ImageView> imageViews = new ArrayList<>();

    // Placer les images équidistants autour du cercle
        for (int k = 0; k < numImages; k++) {
            ImageView imageView = new ImageView();
            // racine nieme (exp(2*i*pi/n)) avec une rotation de 3pi/2
            double x = circle.getCenterX() + (circle.getRadius() * Math.cos((3*Math.PI/2*numImages)+2*Math.PI*k/numImages));
            double y = circle.getCenterY() + (circle.getRadius() * Math.sin((3*Math.PI/2*numImages)+2*Math.PI*k/numImages));
            // Placer l'image à l'angle calculé
            imageViews.add(imageView);
            imageViews.get(k).setX(x-widthWonderImageView/2);
            imageViews.get(k).setY(y-heightWonderImageView/2);
        }
        for (int i = 0; i<imageViews.toArray().length;i++){
            try {
                ImageView imageview = imageViews.get(i);
                pane.getChildren().add(imageview);
                imageview.setImage(chargeImage("cards/card-material-brick-women.png"));
                // on met les cartes vers l'ext
                // mageview.setRotate((imageview.getRotate()-90) + 360*i/numImages);
                imageview.setFitWidth(widthWonderImageView);
                imageview.setFitHeight(heightWonderImageView);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    private int tourDuJoueur = 1;
    public void piocher(ActionEvent event){
        ImageView imageView = new ImageView();
        int widthWonderCardView = 200/3;
        int heightWonderCardView = 292/3;
        Circle circle = new Circle((pane.getPrefWidth())/2, (pane.getPrefHeight()/2), 350+heightWonderImageView/2);
            double x = circle.getCenterX() + (circle.getRadius() * Math.cos((3*Math.PI/2*numImages)+2*Math.PI*(tourDuJoueur-1)/numImages));
            double y = circle.getCenterY() + (circle.getRadius() * Math.sin((3*Math.PI/2*numImages)+2*Math.PI*(tourDuJoueur-1)/numImages));
            imageView.setX(x);
            imageView.setY(y);
            // imageView.setRotate((imageView.getRotate()-90) + 360*tourDuJoueur/numImages);
            imageView.setFitWidth(widthWonderCardView);
            imageView.setFitHeight(heightWonderCardView);
            pane.getChildren().add(imageView);
            try {
                imageView.setImage(chargeImage("cards/card-progress-architect.png"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            tourDuJoueur += 1;
        }
    }

