package gameOfCluedo;

import gameOfCluedo.cards.Card;
import gameOfCluedo.cards.Deck;

import java.util.List;

public class GameOfCluedo {
	private List<Room> rooms;
	private List<Player> players;
	private List<Player> eliminated;
	private Player currentPlayer;
	private Deck deck;
	private GuessTuple envelope;

	public void startGame(){

	}

	public boolean move(int diceRoll ){
		return false;
	}

	public Card guess(GuessTuple guess){
		return null;
	}

	public boolean accuse(GuessTuple guess){
		return false;
	}

	public boolean checkGameOver(){
		return false;
	}

	public Player getNextPlayer(){
		return null;
	}

	public Room[] getReachableRooms(int diceRoll){
		return null;
	}
}
