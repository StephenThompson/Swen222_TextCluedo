package gameOfCluedo;

import java.util.*;
import gameOfCluedo.cards.*;

public class Player {
	static public enum Character{
		Miss_Scarlett, Colonel_Mustard, Mrs_White
		,The_Reverend_Green ,Mrs_Peacock ,Professor_Plum
	}

	private List<Card> hand = new ArrayList<Card>();
	private Character name;

	public Player(Character name) {
		super();
		this.name = name;
	}

	public Character getName(){
		return name;
	}

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
