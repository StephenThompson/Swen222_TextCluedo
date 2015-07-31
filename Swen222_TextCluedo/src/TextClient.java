import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import gameOfCluedo.*;

public class TextClient {
	private GameOfCluedo goc;
	private Scanner inScanner = new Scanner(System.in);

	private enum playerOption{
		MOVE, SUGGEST, ACCUSE
	}

	private playerOption playerTurn(Player player){
		//System.out.println();
		System.out.println("-- Make a choice --");
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

	private void getMove(int diceRoll){
		System.out.println(diceRoll);
		//Room[] reachableRooms = goc.reachableRooms()

	}

	private GuessTuple getSuggest(){
		return null;
	}

	private GuessTuple getAccuse(){
		return null;
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
		System.out.println(playerTurn(goc.nextPlayer()));

		//Loop through players until game has ended
		while (!goc.checkGameOver()){
		//	Ask player option
			playerOption opt = playerTurn(goc.getCurrentPlayer());
		//	Respond to player's choice
			switch (opt){
				case MOVE:
					getMove(Dice.roll());
					break;
				case SUGGEST:
					getSuggest();
					break;
				case ACCUSE:
					getAccuse();
					break;
			}
		//
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
