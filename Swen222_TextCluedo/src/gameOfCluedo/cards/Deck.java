package gameOfCluedo.cards;

public class Deck {
	private CharCard[] characters;
	private WeaponCard[] weapons;
	private RoomCard[] room;

	public Deck(CharCard[] characters, WeaponCard[] weapons, RoomCard[] room) {
		super();
		this.characters = characters;
		this.weapons = weapons;
		this.room = room;
	}
}
