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

	}

	public static void main(String[] args) {
		System.out.println("Welcome to Game of Cluedo");
		System.out.println("Callum Gill and Stephen Thompson, 2015");
		for (int i = 2000; i < 2500; i++)
			System.out.println(i + " : " + (char)(i));
		//Get player number
		//Start Game
		//Loop through players until game has ended
		//	Ask player option
		//	Respond to player's choice
		//
		//End game
	}
}
