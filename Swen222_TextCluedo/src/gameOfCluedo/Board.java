package gameOfCluedo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import gameOfCluedo.squares.*;

public class Board {
	public final int xSize = 25;
	public final int ySize = 25;
	private Square[][] board = new Square[xSize][ySize];
	public final List<Room> rooms = new ArrayList<Room>();
	private final String[] roomTitles = {"Kitchen","Ball Room", "Conservatory", "Dining Room",
										"Billiard Room", "Library", "Lounge", "Hall", "Study"};

	public Board(String file){
		//Creates rooms
		for(int i = 0; i<roomTitles.length; i++){
			rooms.add(new Room(roomTitles[i], null));
		}

		//TODO Create passages

		//Scan File to create board
		try{
			Scanner s = new Scanner(new File("src/CluedoBoard.txt"));
			int xPos = 0;
			int yPos = 0;
			while(s.hasNext()){
				//Checks if int(hallway or room)
				if(s.hasNextInt()){
					int i = s.nextInt();
					if(i==0){
						board[xPos][yPos] = new BlankSquare();
					}else if(i==1){
						board[xPos][yPos] = new RoomSquare();
					}else{
						System.out.println("Error loading file (invalid int - " + i + ", xPos = " + xPos + ", yPox = " + yPos + ")");
					}
					//Else square is a door
				}else{
					String room = s.next();
					//Checks string was 1 char
					if(room.length()!=1){
						throw new IOException("Invalid string");
					}
					//gets char
					char c = room.charAt(0);
					//creates door to room (63 is A, rooms should be in orde of ABC in list)
					board[xPos][yPos] = new DoorSquare(rooms.get(((int)c)-65));
				}
				//Updates x and y
				xPos++;
				if(xPos>=25){
					xPos=0;
					yPos++;
				}
			}
			s.close();
		}catch(IOException e){
			System.out.println("Error loading board - " + e.getMessage());
		}
	}


	public void draw(){
		for (int y=0; y < board[0].length; y++){
			for(int x =0; x<board.length; x++){
				if(board[x][y] instanceof BlankSquare){
					System.out.print("0 ");
				}else if(board[x][y] instanceof RoomSquare){
					System.out.print("1 ");
				}else if (board[x][y] instanceof DoorSquare){
					System.out.println("E ");
				}
			}
			System.out.println("\n");
		}
	}


}
