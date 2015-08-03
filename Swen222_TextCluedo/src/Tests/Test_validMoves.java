package Tests;


import static org.junit.Assert.*;

import org.junit.Test;

import gameOfCluedo.Board;
import gameOfCluedo.Position;

public class Test_validMoves {

	@Test
	public void valid_move_1(){
		Board board = new Board("");
		Position pos = new Position(9, 0);
		Position target = new Position(7,3);
		int roll = 5;
		assertTrue(checkMove(board, pos, target, roll));
	}

	@Test
	public void valid_move_2(){
		Board board = new Board("");
		Position pos = new Position(7,3);
		Position target = new Position(board.rooms.get(1));
		int roll = 3;
		assertTrue(checkMove(board, pos, target, roll));
	}

	@Test
	public void valid_move_3(){
		Board board = new Board("");
		Position pos = new Position(board.rooms.get(1));
		Position target = new Position(7,3);
		int roll = 5;
		assertTrue(checkMove(board, pos, target, roll));
	}

	@Test
	public void invalid_move_1(){
		Board board = new Board("");
		Position pos = new Position(9, 0);
		Position target = new Position(7,3);
		int roll = 3;
		assertFalse(checkMove(board, pos, target, roll));
	}

	@Test
	public void valid_move_5(){
		Board board = new Board("");
		Position pos = new Position(board.rooms.get(1));
		Position target = new Position(board.rooms.get(0));
		int roll = 8;
		assertTrue(checkMove(board, pos, target, roll));
	}


	public boolean checkMove(Board b, Position p, Position target, int roll){
		return b.validMove(p, target, roll);
	}
}