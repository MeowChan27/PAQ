package com.example.paq;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import com.example.paq.fr.isep.game7WonderArch.domain.Game;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ChooseNbrPlayerController implements Initializable {

    @FXML
    Slider slider;

    private Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setShowTickLabels(true);
        slider.setSnapToTicks(true);
    }

    public void chooseNbrPlayerBtn(ActionEvent event){
        int nbrPlayer = (int) slider.getValue();
        Game.setNbrPlayer(nbrPlayer);
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/paq/describePlayer.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
