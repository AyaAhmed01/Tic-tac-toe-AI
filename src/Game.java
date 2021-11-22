import java.util.HashMap;

public class Game {
	
	Board board;
	private HashMap<Character, Player> players;
	Character turn;
	
	public Game (){
	    board = new Board();
	    players = new HashMap<>();
	    turn = 'X';
	    Player humPly = new HumanPlayer();
	    Player comPly = new SuperComputerPlayer();
	    players.put('X', humPly);
	    players.put('O', comPly);
	}
	
	Player currentPlayer() {
		return players.get(turn);
	}
	
	boolean placeBoardMark (int [] pos) throws IllegalArgumentException {
	    try{
	        board.setMark(pos, turn);      // throws exception when cell is not empty
	        return true;
	    }
	    catch (IllegalArgumentException e){
	        return false;
	    }
	}
	
	int [] getMove() {
		return currentPlayer().move(this, turn);
	}

	boolean isOver() {
		return board.isOver();
	}
	
	boolean isWon() {
		return board.isWon();
	}
	
	boolean isTied() {
		return board.isTied();
	}
	
	void swap() {
		turn = (turn == 'X')? 'O' : 'X';
	}
	
	Player winner() {
		return players.get(board.winner());
	}
}