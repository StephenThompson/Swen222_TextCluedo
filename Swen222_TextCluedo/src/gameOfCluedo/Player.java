package gameOfCluedo;

import java.util.*;
import gameOfCluedo.cards.*;

public class Player {
	private List<Card> hand = new ArrayList<Card>();
	private Position pos;

	public Card checkGuess(GuessTuple guess){
		for (Card c : hand){
			if (c instanceof CharCard){
				if (c.equals(guess.getCharacter())){
					return guess.getCharacter();
				}
			} else if (c instanceof WeaponCard){
				if (c.equals(guess.getWeapon())){
					return guess.getWeapon();
				}
			} else if (c instanceof RoomCard){
				if (c.equals(guess.getRoom())){
					return guess.getRoom();
				}
			}
		}
		return null;
	}

	public List<Card> gethand(){
		return hand;
	}

	public void addToHand(Card c){
		hand.add(c);
	}
}
