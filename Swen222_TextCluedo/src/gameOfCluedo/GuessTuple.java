package gameOfCluedo;

import gameOfCluedo.cards.*;

public class GuessTuple {
	private CharCard character;
	private WeaponCard weapon;
	private RoomCard room;

	public GuessTuple(CharCard character, WeaponCard weapon, RoomCard room) {
		super();
		this.character = character;
		this.weapon = weapon;
		this.room = room;
	}
}
