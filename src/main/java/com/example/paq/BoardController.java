package com.example.paq;

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

public class BoardController implements Initializable {

    @FXML
    Pane pane;

    public static Image chargeImage(String url) throws Exception{
        return new Image(Objects.requireNonNull(HelloApplication.class.getResource(url)).openStream());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image image;
        try {
            image = chargeImage("cards/card-material-brick-women.png");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Circle circle = new Circle((pane.getPrefWidth()-image.getWidth())/2, (pane.getPrefHeight()-image.getHeight())/2, 400);
        // définir le nombre d'images et leur distance
        int numImages = 7;
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
                imageview.setRotate((imageview.getRotate()-90) + 360*i/numImages);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
