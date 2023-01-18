package com.example.paq;

import com.example.paq.fr.isep.game7WonderArch.domain.CardDecks;
import com.example.paq.fr.isep.game7WonderArch.domain.Game;
import com.example.paq.fr.isep.game7WonderArch.domain.Joueur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
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
            double xsuivant = circle.getCenterX() + (circle.getRadius() * Math.cos((3 * Math.PI / 2 * numImages) + 2 * Math.PI * (k + 1) / numImages));
            double ysuivant = circle.getCenterY() + (circle.getRadius() * Math.sin((3 * Math.PI / 2 * numImages) + 2 * Math.PI * (k + 1) / numImages));
            double xpioche = (x + xsuivant)/2;
            double ypioche = (y + ysuivant)/2;
            imageViewPioche.setFitWidth((50));
            imageViewPioche.setFitHeight(73);
            imageViewPioche.setX(xpioche - 25);
            imageViewPioche.setY(ypioche - 36);
            lstImageViewPioche.add(imageViewPioche);
            try {
                pane.getChildren().add(imageViewPioche);
                imageViewPioche.setImage(chargeImage(lstJoueur.get(k).getWonder().imagePathBack));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // Partie de gauche
                Separator separator = new Separator(Orientation.HORIZONTAL);
                pane.getChildren().add(separator);
                separator.setPrefWidth(480);
                separator.setPrefHeight(0);
                separator.setLayoutX(0);
                separator.setLayoutY(k*pane.getPrefHeight()/numImages);

                Label labelPlayerName = new Label();
                pane.getChildren().add(labelPlayerName);
                labelPlayerName.setPrefWidth(80);
                labelPlayerName.setLayoutX(20);
                labelPlayerName.setLayoutY((k*pane.getPrefHeight()+20)/numImages);
                labelPlayerName.setText("Nom du joueur : " + lstJoueur.get(k).getName());

        }
        // Start
        imageViews.get(0).setScaleX(2);
        System.out.println(lstJoueur.get(0).getDeckCardQuantities().toArray().length);

        // on boucle
        lstImageViewPioche.add(lstImageViewPioche.get(0));
        lstImageViewPioche.get(0).setOnMouseClicked(this::piocherG);
        lstImageViewPioche.get(lstImageViewPioche.toArray().length-2).setOnMouseClicked(this::piocherD);
    }

    private int tourDuJoueur = 1;

    public void piocherG(MouseEvent mouseEvent){
        CardDecks.CardTypeQuantity cardPiocher = Game.playCardDraw(lstJoueur.get(tourDuJoueur-1));
        piocher(mouseEvent,cardPiocher);
    }

    public void piocherD(MouseEvent mouseEvent){
        int i = 0;
        if (tourDuJoueur == 1){
            i = 1;
        }
        else{
            i = 2;
        }
        CardDecks.CardTypeQuantity cardPiocher = Game.playCardDraw(lstJoueur.get(tourDuJoueur-i));
        piocher(mouseEvent,cardPiocher);
    }

    public void piocher(MouseEvent mouseEvent, CardDecks.CardTypeQuantity cardPiocher){
        ImageView imageView = new ImageView();
        int widthWonderCardView = 200/3;
        int heightWonderCardView = 292/3;
        Circle circle = new Circle((pane.getPrefWidth())/2, (pane.getPrefHeight()/2), 450);
            System.out.println(lstJoueur.get(tourDuJoueur-1).getLstPlayerCard());
            double x = 50*(lstJoueur.get(tourDuJoueur-1).getLstPlayerCard().toArray().length);
            double y = ((tourDuJoueur-1)*pane.getPrefHeight()+40)/numImages;
            // double x = circle.getCenterX() + (circle.getRadius() * Math.cos((3*Math.PI/2*numImages)+2*Math.PI*(tourDuJoueur-1)/numImages));
            // double y = circle.getCenterY() + (circle.getRadius() * Math.sin((3*Math.PI/2*numImages)+2*Math.PI*(tourDuJoueur-1)/numImages));
            imageView.setX(x);
            imageView.setY(y);
            // imageView.setRotate((imageView.getRotate()-90) + 360*tourDuJoueur/numImages);
            imageView.setFitWidth(widthWonderCardView);
            imageView.setFitHeight(heightWonderCardView);
            pane.getChildren().add(imageView);

            try {
                String pathImage = cardPiocher.cardType.imageResource;
                imageView.setImage(chargeImage(pathImage));
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
            tourDuJoueur += 1;
            lstImageViewPioche.get(tourDuJoueur-2).setScaleX(2);
            lstImageViewPioche.get(tourDuJoueur-2).setOnMouseClicked(this::piocherD);
            lstImageViewPioche.get(tourDuJoueur-1).setScaleY(2);
            lstImageViewPioche.get(tourDuJoueur-1).setOnMouseClicked(this::piocherG);
            if (tourDuJoueur == 2){
                lstImageViewPioche.get(lstImageViewPioche.toArray().length-2).setOnMouseClicked(null);
            }
            else {
                lstImageViewPioche.get(tourDuJoueur-3).setOnMouseClicked(null);
            }
        System.out.println(lstJoueur.get(0).getLstPlayerCard());
        System.out.println(lstJoueur.get(1).getLstPlayerCard());
        System.out.println(lstJoueur.get(2).getLstPlayerCard());

    }
    }

