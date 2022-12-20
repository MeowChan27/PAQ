package com.example.paq.fr.isep.game7WonderArch.domain;

import com.example.paq.BoardController;
import com.example.paq.DescribePlayerController;
import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game {

    // attributs
    private static int nbrPlayer;

    private static ArrayList <Joueur> lstJoueur = new ArrayList<>();

    private static ArrayList <Wonder> lstWondersPossibles = new ArrayList<>();

    private static ArrayList<String> lstPlayerName = new ArrayList<>();
    private static ArrayList<LocalDate> lstPlayerDate = new ArrayList<>();

    // private static HashMap<Wonder, List<CardDecks.CardTypeQuantity>> hashMapWonderLstCardTypeQuantity;

    private static ArrayList<CardType> lstCardType = new ArrayList<>();

    private static ArrayList<ProgressToken> lstProgressToken = new ArrayList<>();


    // encapulsation

    public static ArrayList <Joueur> getLstJoueur(){
        return lstJoueur;
    }

    public static int getNbrPlayer(){
        return nbrPlayer;
    }

    public static void setNbrPlayer(int nbrPlayer){
        Game.nbrPlayer = nbrPlayer;
    }

    public static ArrayList<LocalDate> getLstPlayerDate() {return lstPlayerDate;}

    public static void setLstPlayerDate(ArrayList<LocalDate> lstPlayerDate){Game.lstPlayerDate = lstPlayerDate;}

    public static ArrayList<String> getLstPlayerName(){return lstPlayerName;}

    public static void setLstPlayerName(ArrayList<String> lstPlayerName){Game.lstPlayerName = lstPlayerName;}

    public static void fillLstWondersPossibles(){
        lstWondersPossibles.add(Wonder.Alexandrie);
        lstWondersPossibles.add(Wonder.Halicarnasse);
        lstWondersPossibles.add(Wonder.Ephese);
        lstWondersPossibles.add(Wonder.Olympie);
        lstWondersPossibles.add(Wonder.Babylone);
        lstWondersPossibles.add(Wonder.Rhodes);
        lstWondersPossibles.add(Wonder.Gizeh);
    }

    // pick wonder for each player
    public static void chooseRandomWonders(){
        fillLstWondersPossibles();
        for (int i=0; i<nbrPlayer;i++){
            // entier aléatoire entre 0 inclus et (lstWondersPossibles.length-1) inclus
            int nbrRandom = (int)(Math.random()*lstWondersPossibles.toArray().length);
            Wonder wonderPlayer = lstWondersPossibles.get(nbrRandom);
            // New Joueur
            initPlayer(lstPlayerName.get(i),wonderPlayer);
            // On retire la merveille choisie
            lstWondersPossibles.remove(nbrRandom);
        }
    }

    // init Player
    public static void initPlayer(String name, Wonder wonder){
        lstJoueur.add(new Joueur(name,wonder, 0,0,0, false, wonder.lstcardDecks, 0, lstCardType,lstProgressToken));
    }

    // HashMap entre les wonders et les LstCardType associées à ces wonders
    /*
    public static void hashMapWonderLstCardTypeQuantity(){
        hashMapWonderLstCardTypeQuantity.put(Wonder.Alexandrie,CardDecks.deckCardQuantities_Alexandrie);
        hashMapWonderLstCardTypeQuantity.put(Wonder.Gizeh,CardDecks.deckCardQuantities_Gizeh);
        hashMapWonderLstCardTypeQuantity.put(Wonder.Ephese,CardDecks.deckCardQuantities_Ephese);
        hashMapWonderLstCardTypeQuantity.put(Wonder.Olympie,CardDecks.deckCardQuantities_Olympie);
        hashMapWonderLstCardTypeQuantity.put(Wonder.Babylone,CardDecks.deckCardQuantities_Babylon);
        hashMapWonderLstCardTypeQuantity.put(Wonder.Rhodes,CardDecks.deckCardQuantities_Rhodes);
        hashMapWonderLstCardTypeQuantity.put(Wonder.Halicarnasse,CardDecks.deckCardQuantities_Halicarnasse);
    }
    */

    public static void  chooseCardLeftPacket(){
        //
    }

}
