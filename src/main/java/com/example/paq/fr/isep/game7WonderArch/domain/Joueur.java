package com.example.paq.fr.isep.game7WonderArch.domain;

import java.util.ArrayList;
import java.util.List;

public class Joueur {

    /* Un nom
     Un joueur a une merveille
    Il a entier correspond aux nombres de morceaux de merveilles qu'il a construit
    Il a un certain nombre de jeton militaire
    Il a un nombre de points
    Il a un chat ou pas
    Il a une liste de carte de sa merveille qu'il peut piocher à sa gauche
    Il a un nombre de points de bouclier
    Il a des cartes placées devant lui
    Date de naissance {JJ,MM,AA}
    * */

    private final String name;
    private Wonder wonder;
    private int pieceWonderBuild;
    private int points;
    private int nbrJetonMilitaire;
    private boolean cat;
    private List<CardDecks.CardTypeQuantity> deckCardQuantities;
    private int totalShield;
    private ArrayList<CardType> lstPlayerCard;
    private ArrayList<ProgressToken> lstPlayerProgressToken;

    // Constructeur
    Joueur(String name,
           Wonder wonder,
           int pieceWonderBuild,
           int points,
           int nbrJetonMilitaire,
           boolean cat,
           List<CardDecks.CardTypeQuantity> deckCardQuantities,
           int totalShield,
           ArrayList<CardType> lstPlayerCard,
           ArrayList<ProgressToken> lstPlayerProgressToken){

        this.name = name;
        this.wonder = wonder;
        this.pieceWonderBuild = pieceWonderBuild;
        this.points = points;
        this.nbrJetonMilitaire = nbrJetonMilitaire;
        this.cat = cat;
        this.deckCardQuantities = deckCardQuantities;
        this.totalShield = totalShield;
        this.lstPlayerCard = lstPlayerCard;
        this.lstPlayerProgressToken = lstPlayerProgressToken;

    }

    // Encapsulation

    public String getName(){
        return name;
    }

    public Wonder getWonder(){
        return wonder;
    }

    public int getPoints(){
        return points;
    }

    public int getPieceWonderBuild() {
        return pieceWonderBuild;
    }

    public void setPieceWonderBuild(int pieceWonderBuild){
        this.pieceWonderBuild += pieceWonderBuild;
    }

    public void setPoint(int points){
        this.points += points;
    }

    public int getNbrJetonMilitaire(){
        return nbrJetonMilitaire;
    }

    public void setNbrJetonMilitaire(int nbrJetonMilitaire){
        this.nbrJetonMilitaire += nbrJetonMilitaire;
    }

    public boolean getCat(){
        return cat;
    }

    public void setCat(boolean bool){
        cat = bool;
    }

    public List<CardDecks.CardTypeQuantity> getDeckCardQuantities(){
        return deckCardQuantities;
    }

    public void setDeckCardQuantities(List<CardDecks.CardTypeQuantity> deckCardQuantities){
        this.deckCardQuantities = deckCardQuantities;
    }

    public void removeDeckCardQuantities(){
        deckCardQuantities.remove(deckCardQuantities.toArray().length-1);
    }

    public int getTotalShield(){
        return totalShield;
    }

    public void setTotalShield(int totalShield){
        this.totalShield += totalShield;
    }

    public ArrayList<CardType> getLstPlayerCard(){
        return lstPlayerCard;
    }

    public void setLstPlayerCard(ArrayList<CardType> lstPlayerCard){
        this.lstPlayerCard = lstPlayerCard;
    }

    public void addLstPlayerCard(CardType cardType){
        lstPlayerCard.add(cardType);
    }

    public ArrayList<ProgressToken> getLstPlayerProgressToken(){
        return lstPlayerProgressToken;
    }

    public void setLstPlayerProgressToken(ProgressToken progressToken){
        lstPlayerProgressToken.add(progressToken);
    }

    // methodes

}
