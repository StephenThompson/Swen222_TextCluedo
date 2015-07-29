import java.util.Scanner;

import gameOfCluedo.*;

public class TextClient {
	private GameOfCluedo goc;

	private enum playerOption{
		Move, Accuse, Suggest
	}

	private void playerTurn(Player player){

	}

	private void getMove(int diceRoll){

	}

	private GuessTuple getSuggest(){
		return null;
	}

	private GuessTuple getAccuse(){
		return null;
	}

	private void gameLoop(){
		System.out.println("Welcome to Game of Cluedo");
		System.out.println("Callum Gill and Stephen Thompson, 2015");

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
		goc.startGame();

		//Loop through players until game has ended
		while (!goc.checkGameOver()){
		//	Ask player option
			//playerOption = playerTurn(goc.getNextPlayer());
		//	Respond to player's choice
			/*switch (){

			}*/
		//
		}
		//End game
	}

	private int readInt(String msg) {
		System.out.println(msg);
		Scanner reader = new Scanner(System.in);

		while (true){
			if (reader.hasNextInt()) {
				int value = reader.nextInt();
				reader.close();
				return value;
			} else {
				System.out.println("Invalid input, must be a number!");
				while (!reader.hasNextInt() && reader.hasNext()){
					reader.next();
				}
			}
		}
	}

	public static void main(String[] args) {
		new TextClient().gameLoop();
	}
}
