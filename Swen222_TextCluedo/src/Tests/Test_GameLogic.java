package Tests;

import static org.junit.Assert.*;
import gameOfCluedo.GameOfCluedo;

import org.junit.Test;

public class Test_GameLogic {

	@Test
	public void gameover(){
		GameOfCluedo game = new GameOfCluedo();
		game.startGame(6);
		game.setWinner(game.getCurrentPlayer());
		assertTrue(game.checkGameOver());
		assertTrue(game.getWinner().equals(game.getCurrentPlayer()));
	}
}
