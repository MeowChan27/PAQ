package com.example.paq;

import com.example.paq.fr.isep.game7WonderArch.domain.CardDecks;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

import static javafx.scene.paint.Color.DODGERBLUE;

public class  BoardController implements Initializable {

    @FXML
    Pane pane;

    private final int widthWonderImageView = 60;
    private final int heightWonderImageView = 204;
    private final int numImages = Game.getNbrPlayer();

    private final ArrayList<Joueur> lstJoueur = Game.getLstJoueur();

    private final ArrayList<ImageView> lstImageViewPioche = new ArrayList<>();

    public static Image chargeImage(String url) throws Exception{
        return new Image(Objects.requireNonNull(HelloApplication.class.getResource(url)).openStream());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Circle
        Circle circle = new Circle((pane.getPrefWidth() / 2), (pane.getPrefHeight() / 2), 350);
        circle.setFill(DODGERBLUE);
        circle.setOpacity(0.6);
        pane.getChildren().add(circle);

        // définir le nombre d'images et leur distance

        ArrayList<ImageView> imageViews = new ArrayList<>();

        // Placer les images équidistants autour du cercle
        for (int k = 0; k < numImages; k++) {

            // imageview
            ImageView imageView = new ImageView();
            // racine nieme (exp(2*i*pi/n)) avec une rotation de 3pi/2
            double x = circle.getCenterX() + (circle.getRadius() * Math.cos((3 * Math.PI / 2 * numImages) + 2 * Math.PI * k / numImages));
            double y = circle.getCenterY() + (circle.getRadius() * Math.sin((3 * Math.PI / 2 * numImages) + 2 * Math.PI * k / numImages));
            // Emplacement de l'image avec l'angle calculé
            imageViews.add(imageView);
            imageViews.get(k).setX(x - widthWonderImageView / 2);
            imageViews.get(k).setY(y - heightWonderImageView / 2);
            // on place les images
            try {
                pane.getChildren().add(imageView);
                imageView.setImage(chargeImage(lstJoueur.get(k).getWonder().imagePathFront));
                // on met les cartes vers l'ext
                // mageview.setRotate((imageview.getRotate()-90) + 360*i/numImages);
                imageView.setFitWidth(widthWonderImageView);
                imageView.setFitHeight(heightWonderImageView);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // creation pioche
            ArrayList<ArrayList<CardDecks.CardTypeQuantity>> cardDecksAllPlayers = new ArrayList<>();
            cardDecksAllPlayers.add(lstJoueur.get(k).getWonder().lstcardDecks);

            // placer pioche (gauche)
            ImageView imageViewPioche = new ImageView();
            double xsuivant = circle.getCenterX() + (circle.getRadius() * Math.cos((3 * Math.PI / 2 * numImages) + 2 * Math.PI * (k+1) / numImages));
            double ysuivant = circle.getCenterY() + (circle.getRadius() * Math.sin((3 * Math.PI / 2 * numImages) + 2 * Math.PI * (k+1) / numImages));
            double xpioche = (x + xsuivant)/2;
            double ypioche = (y + ysuivant)/2;
            imageViewPioche.setFitWidth((50));
            imageViewPioche.setFitHeight(73);
            imageViewPioche.setX(xpioche-25);
            imageViewPioche.setY(ypioche-36);
            lstImageViewPioche.add(imageViewPioche);
            try {
                pane.getChildren().add(imageViewPioche);
                imageViewPioche.setImage(chargeImage(lstJoueur.get(k).getWonder().imagePathBack));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        //


        // on boucle
        lstImageViewPioche.add(lstImageViewPioche.get(0));
        lstImageViewPioche.get(0).setOnMouseClicked(this::piocherG);
        lstImageViewPioche.get(lstImageViewPioche.toArray().length-2).setOnMouseClicked(this::piocher);
    }

    private int tourDuJoueur = 1;

    public void piocherG(MouseEvent mouseEvent){
        piocher(mouseEvent);
        lstJoueur.get(tourDuJoueur-1).setDeckCardQuantities(lstJoueur.get(tourDuJoueur-1).getDeckCardQuantities());
    }
    public void piocher(MouseEvent mouseEvent){
        ImageView imageView = new ImageView();
        int widthWonderCardView = 200/3;
        int heightWonderCardView = 292/3;
        Circle circle = new Circle((pane.getPrefWidth())/2, (pane.getPrefHeight()/2), 450);
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
            lstImageViewPioche.get(tourDuJoueur-2).setOnMouseClicked(this::piocher);
            lstImageViewPioche.get(tourDuJoueur-1).setOnMouseClicked(this::piocher);
            if (tourDuJoueur == 2){
                lstImageViewPioche.get(lstImageViewPioche.toArray().length-2).setOnMouseClicked(null);
            }
            else {
                lstImageViewPioche.get(tourDuJoueur-3).setOnMouseClicked(null);
            }
        }
    }

