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

    private ArrayList<CardDecks.CardTypeQuantity> lstNextCard = new ArrayList<>();

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
            // carte deck
            try {
                pane.getChildren().add(imageViewPioche);
                CardDecks.CardTypeQuantity nextCard = CardDecks.CardTypeQuantity.nextCard(lstJoueur.get(k).getDeckCardQuantities());
                lstNextCard.add(nextCard);
                imageViewPioche.setImage(chargeImage(nextCard.cardType.imageResource));
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
                labelPlayerName.setPrefWidth(200);
                labelPlayerName.setLayoutX(30);
                labelPlayerName.setLayoutY((k*pane.getPrefHeight()+20)/numImages);
                labelPlayerName.setText("Nom du joueur : " + lstJoueur.get(k).getName());

        }
        // Start
        imageViews.get(0).setScaleX(2);
        System.out.println(lstJoueur.get(0).getDeckCardQuantities().toArray().length);

        // on boucle
        lstImageViewPioche.add(lstImageViewPioche.get(0));
        lstImageViewPioche.get(0).setOnMouseClicked(mouseEvent -> {
            try {
                piocherG(mouseEvent);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        lstImageViewPioche.get(lstImageViewPioche.toArray().length-2).setOnMouseClicked(mouseEvent -> {
            try {
                piocherD(mouseEvent);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // Animation sur la pioche init
        lstImageViewPioche.get(0).setOnMouseEntered(event -> scaleUp(event, lstImageViewPioche.get(0)));
        lstImageViewPioche.get(0).setOnMouseExited(event -> scaleDown(event, lstImageViewPioche.get(0)));
        lstImageViewPioche.get(numImages-1).setOnMouseEntered(event -> scaleUp(event,lstImageViewPioche.get(numImages-1)));
        lstImageViewPioche.get(numImages-1).setOnMouseExited(event -> scaleDown(event,lstImageViewPioche.get(numImages-1)));

    }

    private int tourDuJoueur = 1;

    public void piocherG(MouseEvent mouseEvent) throws Exception {
        // joueur pioche = joueur deck
        CardDecks.CardTypeQuantity cardPiocher = Game.playCardDraw(lstJoueur.get(tourDuJoueur-1),lstJoueur.get(tourDuJoueur-1),lstNextCard.get(tourDuJoueur-1));
        piocher(mouseEvent,cardPiocher);
        if (tourDuJoueur == 1){
            CardDecks.CardTypeQuantity nextCard = CardDecks.CardTypeQuantity.nextCard(lstJoueur.get(numImages-1).getDeckCardQuantities());
            lstNextCard.set(numImages-1, nextCard);
            lstImageViewPioche.get(numImages-1).setImage(chargeImage(nextCard.cardType.imageResource));
        }
        else {
            CardDecks.CardTypeQuantity nextCard = CardDecks.CardTypeQuantity.nextCard(lstJoueur.get(tourDuJoueur - 2).getDeckCardQuantities());
            lstNextCard.set(tourDuJoueur - 2, nextCard);
            lstImageViewPioche.get(tourDuJoueur - 2).setImage(chargeImage(nextCard.cardType.imageResource));
        }
    }

    public void piocherD(MouseEvent mouseEvent) throws Exception {
        if (tourDuJoueur == 1){
            // joueur pioche = joueur deck - 1
            CardDecks.CardTypeQuantity cardPiocher = Game.playCardDraw(lstJoueur.get(tourDuJoueur-1),lstJoueur.get(lstJoueur.toArray().length-1),lstNextCard.get(tourDuJoueur-1));
            piocher(mouseEvent,cardPiocher);
        }
        else{
            CardDecks.CardTypeQuantity cardPiocher = Game.playCardDraw(lstJoueur.get(tourDuJoueur-1),lstJoueur.get(tourDuJoueur-2), lstNextCard.get(tourDuJoueur-2));
            piocher(mouseEvent,cardPiocher);
        }
        // la valeur de tourDuJoueur a changé
        // reset nextcard
        if (tourDuJoueur == 1){
            CardDecks.CardTypeQuantity nextCard = CardDecks.CardTypeQuantity.nextCard(lstJoueur.get(numImages-2).getDeckCardQuantities());
            lstNextCard.set(numImages-2, nextCard);
            lstImageViewPioche.get(numImages-2).setImage(chargeImage(nextCard.cardType.imageResource));
        }
        else if (tourDuJoueur == 2){
            CardDecks.CardTypeQuantity nextCard = CardDecks.CardTypeQuantity.nextCard(lstJoueur.get(numImages-1).getDeckCardQuantities());
            lstNextCard.set(numImages-1, nextCard);
            lstImageViewPioche.get(numImages-1).setImage(chargeImage(nextCard.cardType.imageResource));
        }
        else {
            CardDecks.CardTypeQuantity nextCard = CardDecks.CardTypeQuantity.nextCard(lstJoueur.get(tourDuJoueur-3).getDeckCardQuantities());
            lstNextCard.set(tourDuJoueur-3, nextCard);
            lstImageViewPioche.get(tourDuJoueur-3).setImage(chargeImage(nextCard.cardType.imageResource));
        }
    }

    public void piocher(MouseEvent mouseEvent, CardDecks.CardTypeQuantity cardPiocher){
        // désactiver animation init
        lstImageViewPioche.get(0).setOnMouseEntered(null);
        lstImageViewPioche.get(numImages-1).setOnMouseEntered(null);
        // affichage test
        System.out.println(tourDuJoueur);
        ImageView imageView = new ImageView();
        int widthWonderCardView = 200/3;
        int heightWonderCardView = 292/3;
        Circle circle = new Circle((pane.getPrefWidth())/2, (pane.getPrefHeight()/2), 450);
            double x = 50*(lstJoueur.get(tourDuJoueur-1).getLstPlayerCard().toArray().length);
            double y = ((tourDuJoueur-1)*pane.getPrefHeight()+80)/numImages;
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
            if (tourDuJoueur == numImages+1){
                lstImageViewPioche.get(numImages+1-2).setOnMouseEntered(event -> scaleUp(event,lstImageViewPioche.get(numImages+1-2)));
                lstImageViewPioche.get(numImages+1-2).setOnMouseExited(event -> scaleDown(event,lstImageViewPioche.get(numImages+1-2)));
            }
            else{
                lstImageViewPioche.get(tourDuJoueur-2).setOnMouseEntered(event -> scaleUp(event,lstImageViewPioche.get(tourDuJoueur-2)));
                lstImageViewPioche.get(tourDuJoueur-2).setOnMouseExited(event -> scaleDown(event,lstImageViewPioche.get(tourDuJoueur-2)));
            }
            lstImageViewPioche.get(tourDuJoueur-1).setOnMouseEntered(event -> scaleUp(event,lstImageViewPioche.get(tourDuJoueur-1)));
            lstImageViewPioche.get(tourDuJoueur-1).setOnMouseExited(event -> scaleDown(event,lstImageViewPioche.get(tourDuJoueur-1)));
            lstImageViewPioche.get(tourDuJoueur-1).setOnMouseClicked(mouseEvent1 -> {
                try {
                    piocherG(mouseEvent1);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            lstImageViewPioche.get(tourDuJoueur-2).setOnMouseClicked(mouseEvent1 -> {
                try {
                    piocherD(mouseEvent1);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            if (tourDuJoueur == 2){
                lstImageViewPioche.get(lstImageViewPioche.toArray().length-2).setScaleX(1);
                lstImageViewPioche.get(lstImageViewPioche.toArray().length-2).setScaleY(1);
                lstImageViewPioche.get(lstImageViewPioche.toArray().length-2).setOnMouseEntered(null);
                lstImageViewPioche.get(lstImageViewPioche.toArray().length-2).setOnMouseExited(null);
                lstImageViewPioche.get(lstImageViewPioche.toArray().length-2).setOnMouseClicked(null);
            }
            else {
                lstImageViewPioche.get(tourDuJoueur-3).setScaleX(1);
                lstImageViewPioche.get(tourDuJoueur-3).setScaleY(1);
                lstImageViewPioche.get(tourDuJoueur-3).setOnMouseClicked(null);
                lstImageViewPioche.get(tourDuJoueur-3).setOnMouseEntered(null);
                lstImageViewPioche.get(tourDuJoueur-3).setOnMouseExited(null);
            }

            // Etat du jeu
            etatDuJeu();


        if (tourDuJoueur == numImages+1){
            tourDuJoueur = 1;
        }
    }

    public void scaleUp(MouseEvent mouseEvent, ImageView imageView){
        imageView.setScaleX(2);
        imageView.setScaleY(2);
    }

    public void scaleDown(MouseEvent mouseEvent, ImageView imageView){
        imageView.setScaleX(1);
        imageView.setScaleY(1);
    }

    public void etatDuJeu(){
        System.out.println("JOUEURS \n");
        for(int i=0;i<numImages;i++){
            Joueur joueur = lstJoueur.get(i);
            System.out.println(joueur.getName() + " : " + joueur.getLstPlayerCard());
        }
    }

    }

