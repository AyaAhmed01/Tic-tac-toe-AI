public class SuperComputerPlayer extends ComputerPlayer{
	
	public SuperComputerPlayer(String name) {  // brilliant computer, chooses non-losing move
		super(name);
	} 
	
	public SuperComputerPlayer() {  
		super("Brilliant computer");
	} 
	
	public int [] move (Game game, Character mark) throws RuntimeException {
		GameNode currentNode = new GameNode(game.board, mark);
		boolean moveToTie = false;
		GameNode tieNode = null;
		for(GameNode childNode: currentNode.children()) {
			if(childNode.isWinningNode(mark))
				return childNode.prevMovePos;
			if(!childNode.isLosingNode(mark)) {   // the child node gives tie
				tieNode = childNode;
				moveToTie = true;
			}
		}
		try {
			if(!moveToTie)
				throw new RuntimeException("Game is Not perfect! you can not win or make a draw");	
		}
		catch (RuntimeException e){
			System.out.println(e.getMessage());
            System.exit(0); // Terminate JVM
		}
		return tieNode.prevMovePos;
	}
}
