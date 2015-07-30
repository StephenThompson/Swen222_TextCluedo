import java.util.Scanner;

import gameOfCluedo.*;

public class TextClient {
	private GameOfCluedo goc;

	private enum playerOption{
		MOVE, SUGGEST, ACCUSE
	}

	private playerOption playerTurn(Player player){
		System.out.println("-- Make a choice --");
		System.out.println("1\tMove");
		System.out.println("2\tSuggest");
		System.out.println("3\tAccuse");


		return playerOption.values()[readInt("Choice : ")-1];
	}

	private void getMove(int diceRoll){
		System.out.println(diceRoll);
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
		goc.startGame(3);
		System.out.println(playerTurn(goc.nextPlayer()));

		//Loop through players until game has ended
		/*while (!goc.checkGameOver()){
		//	Ask player option
			playerOption opt = playerTurn(null);
		//	Respond to player's choice
			switch (opt.ordinal()){
				case 0:
					getMove(Dice.roll());
					break;
				case 1:
					getSuggest();
					break;
				case 2:
					getAccuse();
					break;
			}
		//
		}*/
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
