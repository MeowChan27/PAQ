package com.example.paq.fr.isep.game7WonderArch.domain;

public enum CardBack {

	// Enum ??

	CentralDeck(null), //
	
	Alexandrie(Wonder.Alexandrie), //
	Halicarnasse(Wonder.Halicarnasse),
	Ephese(Wonder.Ephese), //
	Olympie(Wonder.Olympie), //,
	Babylone(Wonder.Babylone), //
	Rhodes(Wonder.Rhodes), //
	Gizeh(Wonder.Gizeh); //
	
	public final boolean centralDeck;
	// set only when not centralDeck
	public final Wonder wonderDeck;
	
	private CardBack(Wonder wonderDeck) {
		this.centralDeck = (wonderDeck == null);
		// true only if wonderDeck is null
		this.wonderDeck = wonderDeck;
	}
	
}
