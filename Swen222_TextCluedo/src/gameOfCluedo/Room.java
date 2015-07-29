package gameOfCluedo;

public class Room {
	private String name;
	private Room passage;

	public Room(String name, Room passage) {
		super();
		this.name = name;
		this.passage = passage;
	}

	public String getName() {
		return name;
	}

	public Room getPassage() {
		return passage;
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
}
