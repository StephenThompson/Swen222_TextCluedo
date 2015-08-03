package gameOfCluedo;

import java.util.List;

public class Room {
	private String name;
	private Room passage;
	private List<Position> entrances;

	public Room(String name, Room passage, List<Position> entrances) {
		super();
		this.name = name;
		this.passage = passage;
		this.entrances = entrances;
	}

	public String getName() {
		return name;
	}

	public Room getPassage() {
		return passage;
	}

	public void addEntrance(Position entrance){
		entrances.add(entrance);
	}

	public List<Position> getEntrances() {
		return entrances;
	}

	/**
	 * Sets passage if it is still null
	 * @param passage
	 * @return
	 */
	public boolean setPassage(Room passage) {
		if(this.passage==null){
			this.passage = passage;
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Room){
			Room other = (Room)obj;
			if(name.equals(other.getName())){
				return true;
			}
		}
		return false;
	}


}
