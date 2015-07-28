package gameOfCluedo;

public class Room {
	private String name;
	private Room passage;

	public Room(String name, Room passage) {
		super();
		this.name = name;
		this.passage = passage;
	}
}
