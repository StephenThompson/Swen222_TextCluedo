package gameOfCluedo.squares;

import gameOfCluedo.Room;

public class DoorSquare implements Square {

	private Room to;

	public DoorSquare(Room to){
		this.to = to;
	}

	public Room to(){
		return to;
	}
}
