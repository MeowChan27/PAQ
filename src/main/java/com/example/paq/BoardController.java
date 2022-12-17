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
    // Créer un cercle

        Image image = null;
        try {
            image = chargeImage("cards/card-material-brick-women.png");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Circle circle = new Circle((pane.getPrefWidth()-image.getWidth())/2, (pane.getPrefHeight()-image.getHeight())/2, 600);
        // définir le nombre d'images et leur distance
        int numImages = 7;
        int distance = 360 / numImages;
        ArrayList <ImageView> imageViews = new ArrayList<>();

    // Placer les images équidistants autour du cercle
        for (int i = 0; i < numImages; i++) {
            ImageView imageView = new ImageView();
            double angle = i * distance;
            double x = circle.getCenterX() + (circle.getRadius() * Math.cos(angle));
            double y = circle.getCenterY() + (circle.getRadius() * Math.sin(angle));
            // Placer l'image à l'angle calculé
            imageViews.add(imageView);
            imageViews.get(i).setX(x);
            imageViews.get(i).setY(y);
            System.out.println("x = " + x);
            System.out.println("y = " + y);
        }
        for (ImageView imageview: imageViews){
            try {
                pane.getChildren().add(imageview);
                imageview.setImage(chargeImage("cards/card-material-brick-women.png"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
