package com.example.paq.fr.isep.game7WonderArch.domain;

import com.example.paq.BoardController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    // attributs
    private static int nbrPlayer;

    private ArrayList <Joueur> lstJoueur;

    private ArrayList <Wonder> lstWondersPossibles = new ArrayList<>();

    private static ArrayList<String> lstPlayerName;
    private static ArrayList<LocalDate> lstPlayerDate;

    // encapulsation

    public ArrayList <Joueur> getLstJoueur(){
        return lstJoueur;
    }

    public void chooseCardLeftPacket(){
        //
    }

    public static int getNbrPlayer(){
        return nbrPlayer;
    }

    public static void setNbrPlayer(int nbrPlayer){
        Game.nbrPlayer = nbrPlayer;
    }

    // pick wonder for each player
    public void chooseRandomWonders(){
        for (int i=0; i<nbrPlayer;i++){
            fillLstWondersPossibles();
            // entier aléatoire entre 0 inclus et (lstWondersPossibles.length-1) inclus
            int nbrRandom = (int)(Math.random()*lstWondersPossibles.toArray().length);
            Wonder wonderPlayer = lstWondersPossibles.get(nbrRandom);
            // New Joueur
            // On retire la merveille choisie
            lstWondersPossibles.remove(nbrRandom);
        }
    }
    public void fillLstWondersPossibles(){
        lstWondersPossibles.add(Wonder.Alexandrie);
        lstWondersPossibles.add(Wonder.Halicarnasse);
        lstWondersPossibles.add(Wonder.Ephese);
        lstWondersPossibles.add(Wonder.Olympie);
        lstWondersPossibles.add(Wonder.Babylone);
        lstWondersPossibles.add(Wonder.Rhodes);
        lstWondersPossibles.add(Wonder.Gizeh);
    }

    public static ArrayList<LocalDate> getLstPlayerDate() {return lstPlayerDate;}

    public static void setLstPlayerDate(ArrayList<LocalDate> lstPlayerDate){Game.lstPlayerDate = lstPlayerDate;}

    public static ArrayList<String> getLstPlayerName(){return lstPlayerName;}

    public static void setLstPlayerName(ArrayList<String> lstPlayerName){Game.lstPlayerName = lstPlayerName;}

}
