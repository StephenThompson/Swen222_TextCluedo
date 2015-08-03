package gameOfCluedo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import gameOfCluedo.squares.*;

public class Board {
	public final int xSize = 25;
	public final int ySize = 25;
	private Square[][] board = new Square[xSize][ySize];
	public final List<Room> rooms = new ArrayList<Room>();
	private final String[] roomTitles = {"Kitchen","Ball Room", "Conservatory", "Dining Room",
										"Billiard Room", "Library", "Lounge", "Hall", "Study"};
	private Map<Player, Position> playerPos = new HashMap<Player, Position>();
	private final int[][] startingPositions = {{9,0}, {14,0}, {23,6}, {23,19}, {7, 24}, {0, 17}};

	public Board(String file){
		createRooms();
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
		setupEntrances();
	}
	/**
	 * Adds player to map at their starting location
	 * @param player
	 */
	public void addPlayers(Player player){
		Position pos = new Position(startingPositions[player.getName().ordinal()][0], startingPositions[player.getName().ordinal()][1]);
		playerPos.put(player, pos);
	}

	/**
	 * Returns player position on the board. (null if player not found)
	 * @param p
	 * @return
	 */
	public Position getPlayerPosition(Player p){
		if(playerPos.get(p)!=null){
			return playerPos.get(p);
		}else{
			return null;
		}
	}

	public void setPlayerPosition(Player player, Position pos){
		if(!playerPos.containsKey(player)){return;}
		playerPos.put(player, pos);
		if(!pos.inRoom()){
			System.out.println(player.getName() + "is now at" + pos.getX() + "," + pos.getY());
		}else{
			System.out.println(player.getName() + " is now in " + pos.getRoom());
		}
	}

	/**
	 * Finds reachable rooms from a position
	 * @param diceRoll
	 * @param startingPos
	 * @return
	 */
	public List<Room> reachableRooms(int diceRoll, Position startingPos){
		return rooms;
		/*List<Room> reachable = new ArrayList<Room>();
		if(startingPos.inRoom()){
			if(startingPos.getRoom().getPassage()!=null){
				reachable.add(startingPos.getRoom().getPassage());
			}
			for(Position p: startingPos.getRoom().getEntrances()){

			}
		}
		return null;*/
	}

	/**
	 * Finds reachable rooms from an x,y coordinate
	 * @param diceRoll
	 * @param x
	 * @param y
	 * @return
	 */
	public List<Room> reachableRooms(int diceRoll, int x, int y){
		return null;
	}

	/**
	 * Recursive flood algorithm to find valid move or not.
	 * @param p
	 * @param target
	 * @param diceRoll
	 * @return
	 */
	public boolean validMove(Player p, Position target, int diceRoll){
		Position orgPos = getPlayerPosition(p);
		return getValidMoves(orgPos, diceRoll).contains(target);
	}

	public boolean validMove(Position pos, Position target, int diceRoll){
		return getValidMoves(pos, diceRoll).contains(target);
	}

	/**
	 * Returns set of valid moves from position with dice roll
	 * @param pos
	 * @param movesLeft
	 * @param visited
	 * @return
	 */
	public Set<Position> getValidMoves(Position pos,  int diceRoll){
		Set<Position> validMoves = new HashSet<Position>();
		Queue<PosInfo> nextPos = new ArrayDeque<PosInfo>();
		nextPos.add(new PosInfo(pos, diceRoll));
		while(!nextPos.isEmpty()){
			PosInfo posInfo = nextPos.poll();
			if(!validMoves.contains(posInfo.pos)){
				validMoves.add(posInfo.pos);
				for(Position neighbour : getSurroundingPositions(posInfo.pos)){
					nextPos.add(new PosInfo(neighbour, diceRoll-1));
				}
			}
		}
		return validMoves;
	}

	/**
	 * Usd for iterative flood on positions of board
	 * @author gillcall1
	 *
	 */
	private class PosInfo{
		int movesLeft;
		Position pos;

		public PosInfo(Position pos, int movesLeft){
			this.pos=pos;
			this.movesLeft = movesLeft;
		}
	}

	/**
	 * Gets surrounding positions including rooms
	 * @param pos
	 * @return
	 */
	private List<Position> getSurroundingPositions(Position pos){
		if(pos.inRoom()){
			return pos.getRoom().getEntrances();
		}
		List<Position> adjPos = new ArrayList<Position>();
		int x = pos.getX();
		int y = pos.getY();
		int[] xCoords = new int[]{x+1,x,x-1,x};
		int[] yCoords = new int[]{y,y+1,y,y-1};
		//Checks adjTiles
		for(int i = 0; i<xCoords.length; i++){
			if(!(xCoords[i]<0||xCoords[i]>=board.length||yCoords[i]<0||yCoords[i]>=board[0].length)){
				if(!(board[xCoords[i]][yCoords[i]] instanceof RoomSquare)){
					adjPos.add(new Position(xCoords[i],yCoords[i]));
				}
			}
		}
		if(board[x][y] instanceof DoorSquare){
			adjPos.add(new Position(((DoorSquare)board[x][y]).to()));
		}
		return adjPos;
	}

	/**
	 * Creates the rooms and passages between them
	 */
	private void createRooms(){
		//Creates rooms
		for(int i = 0; i<roomTitles.length; i++){
			rooms.add(new Room(roomTitles[i], null, new ArrayList<Position>()));
		}
		//Create passages
		//Kitchen->Study
		rooms.get(0).setPassage(rooms.get(8));
		//Study->Kitchen
		rooms.get(8).setPassage(rooms.get(0));
		//Conservatory->Lounge
		rooms.get(2).setPassage(rooms.get(6));
		//Lounge->Conservatory
		rooms.get(6).setPassage(rooms.get(2));
	}

	private void setupEntrances(){
		//Setup entrances
		for(int x = 0; x<board.length; x++){
			for(int y = 0; y<board[0].length; y++){
				if(board[x][y] instanceof DoorSquare){
					((DoorSquare)board[x][y]).to().addEntrance(new Position(x,y));;
				}
			}
		}
	}

	public void draw(){
		System.out.print("   ");
		for(int x =0; x<board.length; x++){
			System.out.print(" " + (char)(x+65));
		}
		System.out.println();

		String boardASCII[] = {
				   "┌──────────┬─────┘ ░│      │ ░└───┐ ┌────────────┐",
				   "│▒         │ ░ ░ ░ ░│      │ ░ ░ ░└─┤           ▒│",
				   "│          │ ░ ░┌───┘      └───┐ ░ ░│Conservatory│",
				   "│  Kitchen │ ░ ░│              │ ░ ░│            │",
				   "│          │ ░ ░│   Ball Room  │ ░ ░└↑┐       ┌──┘",
				   "│          │ ░ ░→              ← ░ ░ ░└───────┴──┐",
				   "└───────↑↑─┘ ░ ░│              │ ░ ░ ░ ░ ░ ░ ░ ░ │",
				   " ░ ░ ░ ░ ░ ░ ░ ░└─↑↑────────↑↑─┘ ░ ░ ░ ░ ░ ░ ░┌──┘",
				   "─┐ ░ ░ ░ ░ ░ ░ ░ ░ ░ ░ ░ ░ ░ ░ ░ ░ ░┌─────────┴──┐",
				   "┌┴───────┐ ░ ░ ░ ░ ░ ░ ░ ░ ░ ░ ░ ░ ░→  Billard   │",
				   "│        └─────┐ ░ ░┌────────┐ ░ ░ ░│    Room    │",
				   "│              │ ░ ░│        │ ░ ░ ░│            │",
				   "│ Dining Room  ← ░ ░│        │ ░ ░ ░└───────↑↑┬──┘",
				   "│              │ ░ ░│ Cluedo │ ░ ░ ░ ░ ░ ░ ░ ░│   ",
				   "│              │ ░ ░│        │ ░ ░ ░┌───↓↓────┴──┐",
				   "└┬──────────↑↑─┘ ░ ░│        │ ░ ░┌─┘            │",
				   "─┘ ░ ░ ░ ░ ░ ░ ░ ░ ░└────────┘ ░ ░→    Library   │",
				   " ░ ░ ░ ░ ░ ░ ░ ░ ░ ░ ░ ░ ░ ░ ░ ░ ░└─┐            │",
				   "─┐ ░ ░ ░ ░ ░ ░ ░ ░┌───↓↓↓↓───┐ ░ ░ ░└────────────┤",
				   "┌┴──────────↓┐ ░ ░│          │ ░ ░ ░ ░ ░ ░ ░ ░ ░ │",
				   "│            │ ░ ░│          ← ░ ░ ░ ░ ░ ░ ░ ░┌──┘",
				   "│   Lounge   │ ░ ░│   Hall   │ ░ ░┌↓──────────┴──┐",
				   "│            │ ░ ░│          │ ░ ░│    Study     │",
				   "│▒           │ ░ ░│          │ ░ ░│             ▒│",
				   "└────────────┘ ░┌─┴──────────┴─┐ ░└──────────────┘"};

		for (int y=0; y < board[0].length; y++){

			System.out.printf("%2d ", y);
			for(int x =0; x<board.length; x++){
				if (playerPos.containsValue(new Position(x,y))){
					System.out.print(" P");
				} else {
					System.out.print(boardASCII[y].substring(x*2, x*2+2));
				}
			}
			System.out.println();
		}
	}

/*
 *
					/*if(board[x][y] instanceof BlankSquare){
						//System.out.print("▗▚");
						System.out.print(" ░");
					}else if(board[x][y] instanceof RoomSquare){
						System.out.print("▐█");
					}else if (board[x][y] instanceof DoorSquare){
						//System.out.print("▕░");
						if (board[x][y+1] instanceof RoomSquare)
							System.out.print("↓↓");
						else if (board[x-1][y] instanceof RoomSquare)
							System.out.print(" ←");
						else if (board[x][y-1] instanceof RoomSquare)
							System.out.print("↑↑");
						else if (board[x+1][y] instanceof RoomSquare)
							System.out.print(" →");
					}*/

}
