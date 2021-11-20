import java.util.HashMap;

public class Game {
	
	Board board;
	HashMap<Character, Player> players;
	Character turn;
	
	public Game (Player player1, Player player2){
	    board = new Board();
	    players = new HashMap<>();
	    players.put('X', player1);
	    players.put('O', player2);
	    turn = 'X';
	}

	void run(){
	    while(!board.isOver()){
	        playTurn();
	    }

	    if(board.isWon()){
	        Player winningPlayer = players.get(board.winner());
	        System.out.println("Congratulations " + winningPlayer.name +", you won the game!");
	    }
	    else {
	        System.out.println("No one wins!");
	    }
	}

	void show(){
	    // not very pretty printing..
	    for( Character [] row : board.rows){
	        for(Character mark : row){
	            System.out.print(mark + " ");
	        }
	        System.out.println();
	    }
	}
	
	private boolean placeMark(int [] pos, Character mark){
	    try{
	        board.setMark(pos, mark);      // throws exception when cell is not empty
	        return true;
	    }
	    catch (IllegalArgumentException e){
	    	System.out.println(e);
	        return false;
	    }
	}

	private void playTurn(){
	    while(true){
	        Player currentPlayer = players.get(turn);
	        int [] pos = currentPlayer.move(this, turn);
	        if(placeMark(pos, turn))
	        	break;
	    }

	    //swap turn
	    turn = (turn == 'X')? 'O' : 'X';
	}
	
	public static void main(String[] args) {
		Player scp = new SuperComputerPlayer("aya");
		Player hp = new HumanPlayer("ali");
		Game game = new Game(scp, hp);
		game.run();
	}

}
