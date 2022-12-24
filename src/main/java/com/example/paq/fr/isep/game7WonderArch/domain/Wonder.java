package com.example.paq.fr.isep.game7WonderArch.domain;

import com.example.paq.DescribePlayerController;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public enum Wonder {

	// Wonder are 'les merveilles'

	Alexandrie("Alexandrie", "Alexandrie", //
			"Prenez la première carte d'une pioche au choix, n'importe où sur la table, et posez-la devant vous","decks/deck-alexandrie.png","cards/card-back/card-back-alexandrie.png", CardDecks.deckCardQuantities_Alexandrie),
	
	Halicarnasse("Halicarnasse", "Halicarnasse", //
			"Prenez les 5 premières cartes de la pioche à votre gauche ou à votre droite, choisissez-en 1 et posez-la devant vous" //
			+ "Mélangez-les cartes restantes dans leur pioche","decks/deck-halicarnasse.png", "cards/card-back/card-back-halicarnasse.png",CardDecks.deckCardQuantities_Halicarnasse),

	Ephese("Ephese", "Ephèse", //
			"Prenez la première carte de la pioche centrale et posez-la devant vous","decks/deck-ephese.png", "cards/card-back/card-back-ephese.png",CardDecks.deckCardQuantities_Ephese),
	
	Olympie("Olympie", "Olympie", //
			"Prenez la première carte de la pioche à votre gauche et de celle à votre droite, et posez-les devant vous","decks/deck-olympie.png", "cards/card-back/card-back-olympie.png",CardDecks.deckCardQuantities_Olympie),
	
	Babylone("Babylone", "Babylone", //
			"Choisissez 1 jeton Progrès parmi les 4 disponibles, et posez-le devant vous","decks/deck-babylon.png", "cards/card-back/card-back-babylon.png",CardDecks.deckCardQuantities_Babylon),
	
	Rhodes("Rhodes", "Rhodes", //
			"Ajoutez 1 Bouclier à votre total de Boucliers","decks/deck-rhodes.png", "cards/card-back/card-back-rhodes.png",CardDecks.deckCardQuantities_Rhodes),

	Gizeh("Gizeh", "Gizeh", //
			"Cette merveille n'a pas d'effet particulier, mais rapporte plus de points de victoire que les autres Merveilles","decks/deck-cizeh.png", "cards/card-back/card-back-gizeh.png",CardDecks.deckCardQuantities_Gizeh);
	
	// ------------------------------------------------------------------------
	
	public final String displayName;
	
	public final String frenchName;
	
	public final String effectDescription;

	public final String imagePathBack;

	public final ArrayList<CardDecks.CardTypeQuantity> lstcardDecks;

	public String imagePathFront;
	
	// ------------------------------------------------------------------------
	
	Wonder(String displayName, String frenchName, String effectDescription, String imagePathFront, String imagePathBack, ArrayList<CardDecks.CardTypeQuantity> lstcardDecks) {
		this.displayName = displayName;
		this.frenchName = frenchName;
		this.effectDescription = effectDescription;
		this.imagePathFront = imagePathFront;
		this.imagePathBack = imagePathBack;
		this.lstcardDecks = lstcardDecks;
	}
}
