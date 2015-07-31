package gameOfCluedo;

import java.awt.Point;

public class Position {
	private Room currentRoom;
	private int x, y;

	public Position(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((currentRoom == null) ? 0 : currentRoom.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (currentRoom == null) {
			if (other.currentRoom != null)
				return false;
		} else if (!currentRoom.equals(other.currentRoom))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
