package com.example.paq;

import com.example.paq.fr.isep.game7WonderArch.domain.Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class  BoardController implements Initializable {

    @FXML
    Pane pane;
    private int numImages = Game.getNbrPlayer();


    public static Image chargeImage(String url) throws Exception{
        return new Image(Objects.requireNonNull(HelloApplication.class.getResource(url)).openStream());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int widthWonderImageView = 200/2;
        int heightWonderImageView = 292/2;
        Circle circle = new Circle((pane.getPrefWidth()/2)-widthWonderImageView, (pane.getPrefHeight()/2)-heightWonderImageView, 350);
        System.out.println((pane.getPrefWidth())/2-widthWonderImageView);
        System.out.println((pane.getPrefHeight()/2)-heightWonderImageView);
        // définir le nombre d'images et leur distance

        ArrayList <ImageView> imageViews = new ArrayList<>();

    // Placer les images équidistants autour du cercle
        for (int k = 0; k < numImages; k++) {
            ImageView imageView = new ImageView();

            // racine nieme (exp(2*i*pi/n))

            double x = circle.getCenterX() + (circle.getRadius() * Math.cos(2*Math.PI*k/numImages));
            double y = circle.getCenterY() + (circle.getRadius() * Math.sin(2*Math.PI*k/numImages));
            // Placer l'image à l'angle calculé
            imageViews.add(imageView);
            imageViews.get(k).setX(x);
            imageViews.get(k).setY(y);
        }
        for (int i = 0; i<imageViews.toArray().length;i++){
            try {
                ImageView imageview = imageViews.get(i);
                pane.getChildren().add(imageview);
                imageview.setImage(chargeImage("cards/card-material-brick-women.png"));
                // on met les cartes vers l'ext
                imageview.setRotate((imageview.getRotate()-90) + 360*i/numImages);
                imageview.setFitWidth(widthWonderImageView);
                imageview.setFitHeight(heightWonderImageView);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    private int tourDuJoueur = 1;
    public void piocher(){
        ImageView imageView = new ImageView();
        int widthWonderImageView = 200/3;
        int heightWonderImageView = 292/3;
        Circle circle = new Circle((pane.getPrefWidth())/2-widthWonderImageView, (pane.getPrefHeight()/2)-heightWonderImageView, 440);
        if (tourDuJoueur == 1){
            double x = circle.getCenterX() + (circle.getRadius() * Math.cos(2*Math.PI*tourDuJoueur/numImages));
            double y = circle.getCenterY() + (circle.getRadius() * Math.sin(2*Math.PI*tourDuJoueur/numImages));
            imageView.setX(x);
            imageView.setY(y);
            imageView.setRotate((imageView.getRotate()-90) + 360*tourDuJoueur/numImages);
            imageView.setFitWidth(widthWonderImageView);
            imageView.setFitHeight(heightWonderImageView);
            pane.getChildren().add(imageView);
            try {
                imageView.setImage(chargeImage("cards/card-progress-architect.png"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
