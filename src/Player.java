public abstract class Player {
	
	public String name;
	
	public Player(String name) {
		this.name = name;
	}
	
	public abstract int [] move(Game game, Character mark);
}
