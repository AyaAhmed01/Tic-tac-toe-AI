import java.util.*;

public class GameNode {
	
	Board board;
	Character nextMoverMark;
	int [] prevMovePos;
	
	public GameNode(Board board, Character nextMoverMark, int [] prevMovePos) {
		this.board = board;
		this.nextMoverMark = nextMoverMark;
		this.prevMovePos = prevMovePos;
	}
	
	GameNode(Board board, Character nextMoverMark) {
		this(board, nextMoverMark, new int [] {-1, -1});
	}
	
	// generate child nodes with boards for all possible moves after the current move.
	ArrayList<GameNode> children() {
		
	}
	
	boolean isLosingNode(Character evaluator) {
		 
		}
		
		ArrayList<GameNode> children = children();
		boolean isLosing;
		if(nextMoverMark == evaluator) {    // player's turn
			isLosing = true;
			for(GameNode child: children) 
				isLosing &= child.isLosingNode(evaluator);
		}
		else {                           // opponent turn
			isLosing = false;
			for(GameNode child: children)
				isLosing |= child.isLosingNode(evaluator);
		}
		return isLosing;
	}
	
	boolean isWinningNode(Character evaluator) {
		
	}
}
