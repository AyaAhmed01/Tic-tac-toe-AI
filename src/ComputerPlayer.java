import java.util.Random;

public class ComputerPlayer extends Player{  // dummy computer, looks if Only next move will make it wins, otherwise chooses random move
	
	public ComputerPlayer(String name){
		super(name);
	}
	
	public int [] move(Game game, Character mark) {
		int [] nextMove = winnerMove(game, mark);
		if(nextMove[0] != -1)
			return nextMove;
		else
			return randomMove(game);
	}
	
	
	private int [] winnerMove(Game game, Character mark) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				int pos[] = {i, j};
				Board currentBoard = game.board.deepClone();
				Board nextMoveBoard = new Board(currentBoard.rows);
				if(!nextMoveBoard.isEmpty(pos))
					continue;
				nextMoveBoard.setMark(pos , mark);
				if(nextMoveBoard.winner() == mark) 
					return pos;	
			}
		}
		// No next winning move
		return new int [] {-1, -1};
	}
	
	private int [] randomMove(Game game) {
		Board board = game.board;
		while(true) {
			Random rand = new Random();
			int row = rand.nextInt(3), col = rand.nextInt(3);
			int [] pos = {row, col};
			if(board.isEmpty(pos))
				return pos;
		}
	}
}
