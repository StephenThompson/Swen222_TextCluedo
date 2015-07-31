package gameOfCluedo;

import gameOfCluedo.cards.*;
import java.util.ArrayList;
import java.util.List;

public class GameOfCluedo {
	private Board board;
	private List<Room> rooms;
	private List<Player> players;
	private List<Player> eliminated;
	private Player currentPlayer;
	private Deck deck;
	private GuessTuple envelope;

	public GameOfCluedo() {
		super();
		this.board = new Board("");
	}

	/**
	 * Sets up a new game of Cluedo
	 * @param numPlayers
	 */
	public void startGame(int numPlayers){
		// Create lists
		List<CharCard> charCards = new ArrayList<CharCard>();
		List<WeaponCard> weaponCards = new ArrayList<WeaponCard>();
		List<RoomCard> roomCards = new ArrayList<RoomCard>();

		// Characters
		charCards.add(new CharCard("Miss Scarlett"));
		charCards.add(new CharCard("Colonel Mustard"));
		charCards.add(new CharCard("Mrs. White"));
		charCards.add(new CharCard("The Reverend Green"));
		charCards.add(new CharCard("Mrs. Peacock"));
		charCards.add(new CharCard("Professor Plum"));

		// Weapons
		weaponCards.add(new WeaponCard("Candlestick"));
		weaponCards.add(new WeaponCard("Dagger"));
		weaponCards.add(new WeaponCard("Lead Pipe"));
		weaponCards.add(new WeaponCard("Revolver"));
		weaponCards.add(new WeaponCard("Rope"));
		weaponCards.add(new WeaponCard("Spanner"));

		// Rooms
		roomCards.add(new RoomCard("Kitchen"));
		roomCards.add(new RoomCard("Ball Room"));
		roomCards.add(new RoomCard("Conservatory"));
		roomCards.add(new RoomCard("Dining Room"));
		roomCards.add(new RoomCard("Billard Room"));
		roomCards.add(new RoomCard("Library"));
		roomCards.add(new RoomCard("Lounge"));
		roomCards.add(new RoomCard("Hall"));
		roomCards.add(new RoomCard("Study"));

		// Setup game
		players = new ArrayList<Player>();
		for(int i=0; i<numPlayers; i++){
			if(i>=Player.Character.values().length){
				System.out.println("What we looping for too many players : " + i);
			}else{
				System.out.println("Character : " + i);
				players.add(new Player(Player.Character.values()[i]));
			}
		}

		deck = new Deck(charCards, weaponCards, roomCards);
		currentPlayer = players.get(0);
		envelope = deck.deal(players);



		System.out.println("Character : " + envelope.getCharacter().getTitle());
		System.out.println("Weapon    : " + envelope.getWeapon().getTitle());
		System.out.println("Room      : " + envelope.getRoom().getTitle());

	}

	/**
	 * Attempts to move current player to new x,y.
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean move(int x, int y){
		return false;
	}

	/**
	 * Checks the current player's guess with the next player. If the player has a card that disproves the guess, the player returns it.
	 * @param guess
	 * @return Card
	 */
	public Card guess(GuessTuple guess){
		return nextPlayer().checkGuess(guess);
	}

	/**
	 * Returns whether the player has guessed the cards in the envelope correctly or not
	 * @param guess
	 * @return boolean
	 */
	public boolean accuse(GuessTuple guess){
		return guess.equals(envelope);
	}



	/**
	 * Checks whether there is one player remaining
	 * @return boolean
	 */
	public boolean checkGameOver(){
		return players.size() == 1;
	}

	/**
	 * Returns reachable rooms for currentPlayer with the given dice roll
	 * @param diceRoll
	 * @return
	 */
	/*public Room[] getReachableRooms(int diceRoll){
		board.
	}*/

	/**
	 * Return currentPlayer
	 * @return
	 */
	public Player getCurrentPlayer(){
		return currentPlayer;
	}


	/**
	 * Sets current player to the next player and return said player
	 * @return
	 */
	public Player nextPlayer(){
		if(players.indexOf(currentPlayer)<players.size()-1){
			currentPlayer = players.get(players.indexOf(currentPlayer)+1);
		}else{
			currentPlayer = players.get(0);
		}
		return currentPlayer;
	}
}
