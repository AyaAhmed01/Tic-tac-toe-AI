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
		ArrayList<GameNode> boardChildren = new ArrayList<GameNode>();
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++) {
				int [] pos = {i, j};
				if(!board.isEmpty(pos))
					continue;
				Board childBoard = board.deepClone();
				childBoard.setMark(pos, nextMoverMark);
				GameNode childNode = new GameNode(childBoard, (nextMoverMark == 'X')? 'O':'X', pos);
				boardChildren.add(childNode);
			}
		}
		return boardChildren;
	}
	
	boolean isLosingNode(Character evaluator) {
		Character opponentEvaluator = (evaluator == 'X')? 'O' : 'X';
		if(board.isOver()) {  // a tie or won
			if(board.winner() == opponentEvaluator)
				return true;
			return false;   // tie is (!loss) 
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
		if(board.isOver()) {                     // a tie or won
			return board.winner() == evaluator; 
		}
		
		ArrayList<GameNode> children = children();
		boolean isWinning;
		if(nextMoverMark == evaluator) {    // player's turn
			isWinning = false;
			for(GameNode child: children) 
				isWinning |= child.isWinningNode(evaluator);
		}
		else {                            // opponent turn
			isWinning = true;
			for(GameNode child: children)
				isWinning &= child.isWinningNode(evaluator);
		}
		return isWinning;
	}
}
