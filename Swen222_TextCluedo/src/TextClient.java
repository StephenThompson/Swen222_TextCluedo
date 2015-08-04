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

	private enum PlayerOption{
		MOVE, SUGGEST, ACCUSE
	}

	private PlayerOption playerTurn(Player p){
		//Draw Board
		goc.drawBoard();
		PlayerOption[] inRoomOptions = {PlayerOption.MOVE, PlayerOption.SUGGEST, PlayerOption.ACCUSE};
		PlayerOption[] options = {PlayerOption.MOVE, PlayerOption.ACCUSE};
		if(goc.getPlayerPos().isRoom()){
			options = inRoomOptions;
		}
		System.out.println("\n" + p.getName().name() + "'s turn!\n");
		System.out.println("\n-- Make a choice --");
		for(int i=0; i<options.length; i++){
			System.out.println(i+1 + "\t"+options[i].name());
		}

		int selected = readInt("Choice : ");
		while(selected<1||selected>3){
			System.out.println("Invalid choice");
			selected = readInt("Choice : ");
		}
		return options[selected-1];
	}

	private void getMove(int diceRoll, Player player){
		System.out.println("You Rolled a " + diceRoll);
		Position originalPos = goc.getPlayerPos();
		if(originalPos.isRoom()){
			System.out.println("You are in room " + originalPos.getRoom().getName());
		}else{
			System.out.println("You are at this point " + (char)(originalPos.getX()+65) +","+ (originalPos.getY()+1));
		}
		List<Room> reachable = goc.getReachableRooms(diceRoll);
		if (reachable.size() > 0){
			System.out.println("You can reach these rooms : ");
			for (int i = 0; i < reachable.size(); i++){
				System.out.printf("%2d. %s%n", i + 1, reachable.get(i).toString());
			}
			System.out.println("1. Enter X-Coordinate or name of room");
		} else {
			System.out.println("1. Enter X-Coordinate: ");
		}
		Position newPos = null;
		if(inScanner.hasNext("[a-zA-Z]")){
			int x = (Character.toUpperCase(readChar(""))) - 65;
			int y = readInt("Enter Y-coordinate: ") - 1;
			newPos = new Position(x,y);
		}else if (inScanner.hasNextInt()){
			int roomIndex = readInt("") - 1;
			for(Room r : reachable){
				if (r.getName().equals(reachable.get(roomIndex).toString())){
					newPos = new Position(r);
				}
			}
		}else{
			System.out.println("InvalidMove");
		}
		if(newPos!=null && goc.validMove(newPos, diceRoll)){
			goc.move(newPos);
			if(newPos.isRoom()){
				System.out.println("You can make a suggestion.");
				getSuggest();
			}
		}
	}

	private void getSuggest(){
		String charList[] = {"Miss Scarlett", "Colonel Mustard", "Mrs. White", "The Reverend Green",
				"Mrs. Peacock", "Professor Plum"};

		String weaponList[] = {"Candlestick", "Dagger", "Lead Pipe", "Revolver",
				"Rope", "Spanner"};

		System.out.println("- Guess");
		//Room
		System.out.println("You are accusing in room " + goc.getPlayerPos().getRoom().getName());
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
		RoomCard room = new RoomCard(goc.getPlayerPos().getRoom().getName());

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
			goc.setWinner(goc.getCurrentPlayer());
		} else {
			System.out.println("You lost");
			goc.playerLost(goc.getCurrentPlayer());
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
			if(!goc.isEliminated(currentPlayer)){
				PlayerOption opt = playerTurn(currentPlayer);
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
			}
			goc.endTurn();
		}
		System.out.println("GAME OVER!!!!!!!!!!!");
		System.out.println("The winner was : " + goc.getWinner().getName());
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

	private char readChar(String msg) {
		System.out.println(msg);
		if(inScanner.hasNext("[a-zA-Z]")){
			return inScanner.next("[a-zA-Z]").charAt(0);
		}else{
			inScanner.nextLine();
			return '\0';
		}
	}

	public static void main(String[] args) {
		new TextClient().gameLoop();
	}
}
