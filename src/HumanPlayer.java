import java.util.*;

public class HumanPlayer extends Player{
	
	public HumanPlayer(String name) {
		super(name);
	}
	
	public HumanPlayer() {
		super("aya");
	}
	
	// Below methods for CLI version of app
	public int [] move(Game game, Character mark) {
		try(Scanner sc = new Scanner(System.in)){
			while(true) {
				try {
					System.out.println(name + ", please select your space \"row,col\" i.e 1,1");
					String inputSpace = sc.nextLine();
					validInputFormat(inputSpace);               
					
					int row = inputSpace.charAt(0) - '0' - 1;  // input is 1 indexed
					int col = inputSpace.charAt(2) - '0' - 1; 
					int [] pos = new int[] {row, col};
					
					validCoordinates(pos);                 
					return pos;
				}
				catch (IllegalArgumentException e){
					System.out.println(e.getMessage());
				}
			}
		}
	}
	
	private void validCoordinates(int [] pos) throws IllegalArgumentException {
		int row = pos[0], col = pos[1];
		boolean validRow = (row < 3 && row >= 0);
		boolean validCol = (col < 3 && col >= 0);
		if (!validRow || !validCol )
			throw new IllegalArgumentException("Invalid coordinate! select numbers from 1 to 3");
	}
	
	private void validInputFormat(String input) throws IllegalArgumentException{
		if (input.length() != 3 || input.charAt(1) != ',')
			throw new IllegalArgumentException("Invalid input format!");
	}
}

