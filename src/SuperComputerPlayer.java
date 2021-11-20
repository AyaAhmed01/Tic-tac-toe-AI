public class SuperComputerPlayer extends ComputerPlayer{
	
	public SuperComputerPlayer(String name) {  // brilliant computer, chooses non-losing move
		super(name);
	} 
	
	public int [] move(Game game, Character mark) {
		GameNode currentNode = new GameNode(game.board, mark);
		GameNode tieNode = null;
		for(GameNode childNode: currentNode.children()) {
			if(childNode.isWinningNode(mark))
				return childNode.prevMovePos;
			if(!childNode.isLosingNode(mark)) {   // the child node gives tie
				tieNode = childNode;
			}
		}
		return tieNode.prevMovePos;
	}
}
