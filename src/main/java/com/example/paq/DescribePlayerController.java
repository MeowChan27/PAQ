package com.example.paq;

import com.example.paq.fr.isep.game7WonderArch.domain.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class DescribePlayerController implements Initializable {

    @FXML
    protected Pane pane;

    private ArrayList<String> lstNamePlayer = new ArrayList<>();
    private ArrayList<TextField> lstTextField = new ArrayList<>();

    private ArrayList<LocalDate> lstDatePlayer = new ArrayList<>();
    private ArrayList<DatePicker> lstDatePicker = new ArrayList<>();

    private ArrayList<ImageView> lstImageWondersView = new ArrayList<>();

    public static Image chargeImage(String url) throws Exception{
        return new Image(Objects.requireNonNull(HelloApplication.class.getResource(url)).openStream());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // {Name1, Date1, Name2, Date2, ..., Name_nbrJoueur, Date_nbrJoueur}
        int nbrJoueur = Game.getNbrPlayer();
        // valeur arbitraire pour limiter les séparateurs verticalement
        double heightSeparator = 0.8;
        for (int i = 1; i<=nbrJoueur; i++){
            // Creation des afficheurs JavaFX
            Separator separator = new Separator(Orientation.VERTICAL);
            Label labelPlayer = new Label();
            Label labelNamePlayer = new Label();
            Label labelBirth = new Label();
            TextField textField = new TextField();
            DatePicker datePicker = new DatePicker();
            Button validerBtn = new Button("Valider");
            ImageView wondersImageView = new ImageView();
            // On superpose au pane les afficheurs
            pane.getChildren().add(labelBirth);
            pane.getChildren().add(separator);
            pane.getChildren().add(labelPlayer);
            pane.getChildren().add(labelNamePlayer);
            pane.getChildren().add(textField);
            lstTextField.add(textField);
            pane.getChildren().add(validerBtn);
            validerBtn.setOnAction(this::validerBtn);
            pane.getChildren().add(datePicker);
            lstDatePicker.add(datePicker);
            pane.getChildren().add(wondersImageView);
            lstImageWondersView.add(wondersImageView);
            // ---
            // separator
            separator.setLayoutX(i*pane.getPrefWidth()/nbrJoueur);
            separator.setLayoutY(0);
            separator.setPrefWidth(0);
            separator.setPrefHeight(heightSeparator*pane.getPrefHeight());
            // label Player
            labelPlayer.setPrefWidth(60);
            labelPlayer.setLayoutX((i-1)*pane.getPrefWidth()/nbrJoueur+0.3*(pane.getPrefWidth()/nbrJoueur)-labelPlayer.getPrefWidth());
            labelPlayer.setText("Joueur " + i);
            // label NamePlayer
            labelNamePlayer.setPrefWidth(60);
            labelNamePlayer.setLayoutX((i-1)*pane.getPrefWidth()/nbrJoueur+0.3*(pane.getPrefWidth()/nbrJoueur)-labelNamePlayer.getPrefWidth());
            labelNamePlayer.setLayoutY(heightSeparator*pane.getPrefHeight()/2);
            labelNamePlayer.setText("Nom : ");
            // label DateOfBirth
            labelBirth.setPrefWidth(60);
            labelBirth.setLayoutX((i-1)*pane.getPrefWidth()/nbrJoueur+0.3*(pane.getPrefWidth()/nbrJoueur)-labelBirth.getPrefWidth());
            labelBirth.setLayoutY(pane.getPrefHeight()/2);
            labelBirth.setText("Date : ");
            // TextField
            textField.setPrefWidth(120);
            textField.setLayoutX((i-1)*pane.getPrefWidth()/nbrJoueur+0.3*(pane.getPrefWidth()/nbrJoueur));
            textField.setLayoutY(heightSeparator*pane.getPrefHeight()/2);
            // Btn Valider (on aura pu le définir sur scène builder également)
            validerBtn.setPrefWidth(100);
            validerBtn.setLayoutX(pane.getPrefWidth()/2 - validerBtn.getPrefWidth());
            validerBtn.setLayoutY(heightSeparator*pane.getPrefHeight()+(pane.getPrefHeight()-heightSeparator*pane.getPrefHeight())/2 - validerBtn.getPrefHeight());
            // Date Picker
            datePicker.setPrefWidth(120);
            datePicker.setLayoutX((i-1)*pane.getPrefWidth()/nbrJoueur+0.3*(pane.getPrefWidth()/nbrJoueur));
            datePicker.setLayoutY(pane.getPrefHeight()/2);
            // ImageView
            wondersImageView.setX((i-1)*pane.getPrefWidth()/nbrJoueur+0.2*(pane.getPrefWidth()/nbrJoueur));
            wondersImageView.setY(50);
        }
    }

    public void validerBtn(ActionEvent event){
        HashMap<LocalDate,String> hashMapDatePlayerName = new HashMap<>();
        for (int i = 0; i<lstTextField.toArray().length;i++){
            hashMapDatePlayerName.put(lstDatePicker.get(i).getValue(), lstTextField.get(i).getText());
            lstDatePlayer.add(lstDatePicker.get(i).getValue());
        }
        // on trie les dates
        lstDatePlayer.sort(Comparator.reverseOrder());
        // on sauvegarde les données dans Game
        Game.setLstPlayerDate(lstDatePlayer);
        // on trie les name selon les dates
        for (int i = 0; i<lstDatePlayer.toArray().length;i++){
            lstNamePlayer.add(hashMapDatePlayerName.get(lstDatePlayer.get(i)));
        }
        // on sauvegarde les données dans Game
        Game.setLstPlayerName(lstNamePlayer);
        Game.chooseRandomWonders();
    }

    public void changeView(ActionEvent event){
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/paq/board-view.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}