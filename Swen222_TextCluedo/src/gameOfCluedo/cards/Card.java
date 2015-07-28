package gameOfCluedo.cards;

public abstract class Card {
	private String title;

	public Card(String title) {
		super();
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
}
