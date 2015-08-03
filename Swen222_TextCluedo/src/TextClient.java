import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import gameOfCluedo.*;
import gameOfCluedo.cards.*;

public class TextClient {
	private GameOfCluedo goc;
	private Scanner inScanner = new Scanner(System.in);

	private enum playerOption{
		MOVE, SUGGEST, ACCUSE
	}

	private playerOption playerTurn(Player p){
		System.out.println("\n" + p.getName().name() + "'s turn!\n");
		goc.drawBoard();
		System.out.println("\n-- Make a choice --");
		System.out.println("1\tMove");
		System.out.println("2\tSuggest");
		System.out.println("3\tAccuse");

		int selected = readInt("Choice : ");
		while(selected<1||selected>3){
			System.out.println("Invalid choice");
			selected = readInt("Choice : ");
		}
		return playerOption.values()[selected-1];
	}

	private void getMove(int diceRoll, Player player){
		System.out.println("You Rolled a : " + diceRoll);
		Position originalPos = goc.getPlayerPos();
		if(originalPos.inRoom()){
			System.out.println("You are in room " + originalPos.getRoom().getName());
		}else{
			System.out.println("You are at this point " + originalPos.getX() +","+ originalPos.getY());
		}
		System.out.println("1. Enter X-Coordinate or name of room");
		List<Room> reachable = goc.getReachableRooms(diceRoll);
		System.out.println("You can reach these rooms : " + reachable.toString());
		Position newPos = null;
		if(inScanner.hasNextInt()){
			int x = readInt("");
			int y = readInt("Enter Y-coordinate");
			newPos = new Position(x,y);
		}else{
			String input = inScanner.nextLine();
			for(Room r : reachable){
				r.getName().equals(input);
				newPos = new Position(r);
			}
		}
		if(newPos!=null && goc.validMove(newPos)){
			goc.move(newPos);
		}
	}

	private void getSuggest(){
		String charList[] = {"Miss Scarlett", "Colonel Mustard", "Mrs. White", "The Reverend Green",
				"Mrs. Peacock", "Professor Plum"};

		String weaponList[] = {"Candlestick", "Dagger", "Lead Pipe", "Revolver",
				"Rope", "Spanner"};

		System.out.println("- Guess");
		// Character
		System.out.println("Choose your character");
		for (int i = 0; i < charList.length; i++){
		System.out.println((i+1) + ". " + charList[i]);
		}
		int charChoice = readInt(": ") - 1;

		// Weapon
		System.out.println("Choose your weapon");
		for (int i = 0; i < weaponList.length; i++){
		System.out.println((i+1) + ". " + weaponList[i]);
		}
		int weaponChoice = readInt(": ") - 1;

		CharCard character = new CharCard(charList[charChoice]);
		WeaponCard weapon = new WeaponCard(weaponList[weaponChoice]);
		RoomCard room = new RoomCard("Kitchen");

		GuessTuple guess = new GuessTuple(character, weapon, room);
		Card back = goc.guess(guess);
		if (back != null){
			System.out.println("The next player card is \"" + back.getTitle() + "\"");
		} else {
			System.out.println("The next player cannot disprove your guess");
		}
	}

	private void getAccuse(){
		String charList[] = {"Miss Scarlett", "Colonel Mustard", "Mrs. White", "The Reverend Green",
				"Mrs. Peacock", "Professor Plum"};

		String weaponList[] = {"Candlestick", "Dagger", "Lead Pipe", "Revolver",
				"Rope", "Spanner"};

		String roomList[] = {"Kitchen", "Ball Room", "Conservatory", "Dining Room",
				"Billard Room", "Library",  "Lounge",  "Hall",  "Study"};

		System.out.println("- Accuse");

		// Character
		System.out.println("Choose your character");
		for (int i = 0; i < charList.length; i++){
		System.out.println((i+1) + ". " + charList[i]);
		}
		int charChoice = readInt(": ") - 1;

		// Weapon
		System.out.println("Choose your weapon");
		for (int i = 0; i < weaponList.length; i++){
		System.out.println((i+1) + ". " + weaponList[i]);
		}
		int weaponChoice = readInt(": ") - 1;

		// Room
		System.out.println("Choose your room");
		for (int i = 0; i < roomList.length; i++){
		System.out.println((i+1) + ". " + roomList[i]);
		}
		int roomChoice = readInt(": ") - 1;

		CharCard character = new CharCard(charList[charChoice]);
		WeaponCard weapon = new WeaponCard(weaponList[weaponChoice]);
		RoomCard room = new RoomCard(roomList[roomChoice]);

		GuessTuple guess = new GuessTuple(character, weapon, room);
		if (goc.accuse(guess)){
			System.out.println("You win");
		} else {
			System.out.println("You lost");
		}
	}

	private void gameLoop(){
		System.out.println("Welcome to Game of Cluedo");
		System.out.println("Callum Gill and Stephen Thompson\nversion 1.0\n2015");

		//Get player number
		int numPlayers = 0;
		while (numPlayers < 3 || numPlayers > 6){
			numPlayers = readInt("\nPlease input the number of players : ");
			if (numPlayers < 3 || numPlayers > 6) {
				System.out.println("There must be between 3 - 6 players!");
			}
		}

		goc = new GameOfCluedo();

		//Start Game
		goc.startGame(numPlayers);

		//Loop through players until game has ended
		while (!goc.checkGameOver()){
		//	Ask player option
			Player currentPlayer = goc.getCurrentPlayer();
			playerOption opt = playerTurn(currentPlayer);
		//	Respond to player's choice
			switch (opt){
				case MOVE:
					getMove(Dice.roll(), currentPlayer);
					break;
				case SUGGEST:
					getSuggest();
					break;
				case ACCUSE:
					getAccuse();
					break;
			}
			goc.nextPlayer();
		}
		//End game
	}

	private int readInt(String msg) {
		System.out.println(msg);
		if(inScanner.hasNextInt()){
			return inScanner.nextInt();
		}else{
			inScanner.nextLine();
			return -1;
		}
	}

	public static void main(String[] args) {
		new TextClient().gameLoop();
	}
}
