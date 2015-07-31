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

	private playerOption playerTurn(Player p){
		System.out.println(p.getName().name() + "'s turn!");
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

	private void getMove(int diceRoll, Player player){
		System.out.println("You Rolled a : " + diceRoll);
		Position pos = goc.getPlayerPos();
		if(pos.inRoom()){
			System.out.println("You are in room " + pos.getRoom().getName());
		}else{
			System.out.println("You are at this point " + pos.getX() +","+ pos.getY());
		}
		System.out.println("1. Enter Co-ordinate or name of room");


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
