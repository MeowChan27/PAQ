package com.example.paq.fr.isep.game7WonderArch.domain;
import java.time.LocalDate;
import java.util.ArrayList;

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

    private static ArrayList<ArrayList<CardDecks.CardTypeQuantity>> deckCardQuantitiesAll = new ArrayList<>();


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

    public static ArrayList<ArrayList<CardDecks.CardTypeQuantity>> getDeckCardQuantitiesAll(){
        return deckCardQuantitiesAll;
    }

    public static void setDeckCardQuantities(int indice, CardType cardType){
        //deckCardQuantitiesAll.get(indice).remove();
    }

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
        lstJoueur.add(new Joueur(name,wonder, 0,0,0, false, wonder.lstcardDecks, 0, new ArrayList<CardType>(),new ArrayList<ProgressToken>()));
    }

    public static CardDecks.CardTypeQuantity playCardDraw(Joueur joueurQuiPioche, Joueur joueurDeck, CardDecks.CardTypeQuantity cardPiocher){
        // CardDecks.CardTypeQuantity cardPiocher = CardDecks.CardTypeQuantity.drawCard(joueurDeck.getWonder().lstcardDecks);
        CardDecks.CardTypeQuantity.drawCard(joueurDeck.getDeckCardQuantities(),cardPiocher);
        CardType card = cardPiocher.cardType;
        ArrayList <CardType> playerCards = joueurQuiPioche.getLstPlayerCard();
        playerCards.add(card);
        joueurQuiPioche.setLstPlayerCard(playerCards);
        return cardPiocher;
    }

    public static CardDecks.CardTypeQuantity playCardDrawCentrale(Joueur joueurQuiPioche,ArrayList <CardDecks.CardTypeQuantity> lstPiocheCentrale, CardDecks.CardTypeQuantity cardPiocher){
        CardDecks.CardTypeQuantity.drawCard(lstPiocheCentrale,cardPiocher);
        CardType card = cardPiocher.cardType;
        ArrayList <CardType> playerCards = joueurQuiPioche.getLstPlayerCard();
        playerCards.add(card);
        joueurQuiPioche.setLstPlayerCard(playerCards);
        return cardPiocher;
    }

    public static boolean checkWonderUpgrade(Joueur joueur){
        Wonder wonder = joueur.getWonder();
        int pieceWonderBuild = joueur.getPieceWonderBuild();
        return false;
    }
}