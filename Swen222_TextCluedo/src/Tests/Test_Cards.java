package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import gameOfCluedo.cards.*;

public class Test_Cards {

	@Test
	public void pass_Equals_1(){
		Card c1 = new CharCard("Test");
		Card c2 = new CharCard("Test");
		assertTrue(c1.equals(c2));
	}

	@Test
	public void pass_Equals_2(){
		CharCard c1 = new CharCard("Test");
		CharCard c2 = new CharCard("Test");
		assertTrue(c1.equals(c2));
	}

	@Test
	public void pass_Equals_3(){
		Card c1 = new WeaponCard("Test");
		Card c2 = new WeaponCard("Test");
		assertTrue(c1.equals(c2));
	}

	@Test
	public void pass_Equals_4(){
		Card c1 = new RoomCard("Test");
		Card c2 = new RoomCard("Test");
		assertTrue(c1.equals(c2));
	}

	@Test
	public void fail_Equals_1(){
		Card c1 = new WeaponCard("Test");
		Card c2 = new RoomCard("Test");
		assertFalse(c1.equals(c2));
	}

	@Test
	public void fail_Equals_2(){
		Card c1 = new CharCard("Test");
		Card c2 = null;
		assertFalse(c1.equals(c2));
	}

	@Test
	public void fail_Equals_3(){
		Card c1 = new WeaponCard("Test");
		RoomCard c2 = new RoomCard("Test");
		assertFalse(c1.equals(c2));
	}
	@Test
	public void GetTitle(){
		String title = "Test";
		Card c1 = new CharCard(title);
		assertTrue(c1.getTitle().equals(title));
	}

}
