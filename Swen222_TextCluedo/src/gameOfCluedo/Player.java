package gameOfCluedo;

import java.util.*;

import gameOfCluedo.cards.Card;

public class Player {
	private List<Card> hand = new ArrayList<Card>();

	public Card checkGuess(GuessTuple guess){
		return null;
	}

	public List<Card> gethand(){
		return hand;
	}

	public void addToHand(Card c){
		hand.add(c);
	}
}
