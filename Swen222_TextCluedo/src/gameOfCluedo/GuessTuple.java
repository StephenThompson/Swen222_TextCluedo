package gameOfCluedo;

import gameOfCluedo.cards.*;

/**
 * This class is used for suggestions, accusations and the envelope in the Game of Cluedo.
 * @author thompsstep2
 *
 */
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

	/**
	 * Return the guessed character
	 * @return CharCard
	 */
	public CharCard getCharacter() {
		return character;
	}

	/**
	 * Return the guessed weapon
	 * @return WeaponCard
	 */
	public WeaponCard getWeapon() {
		return weapon;
	}

	/**
	 * Return the guessed room
	 * @return RoomCard
	 */
	public RoomCard getRoom() {
		return room;
	}
}
