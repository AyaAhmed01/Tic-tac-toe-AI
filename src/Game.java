import java.util.HashMap;
import java.util.Scanner;

public class Game {
	
	Board board;
	HashMap<Character, Player> players;
	Player currentPlayer;
	Character turn;
	
	public Game (){
	    board = new Board();
	    players = new HashMap<>();
	    initializePlayers();
	    turn = 'X';
	}
	
	private void initializePlayers() {
		System.out.println("Welcome to the Amazing Tic Tac Toe!\n");
		Character [] symbols = new Character[] {'X', 'O'};
		for(Character symbol : symbols) {
			int type = getPlayerType(symbol);
			String name = getPlayerName(symbol);
			Player player;
			switch(type) {
			case 1:
				player = new HumanPlayer(name);
				break;
			case 2:
				player = new ComputerPlayer(name);
				break;
			default:
				player = new SuperComputerPlayer(name);
			}
			players.put(symbol, player);
		}		
	}
	
	private int getPlayerType(Character symbol) {
		System.out.println("Please select type of "+ symbol +" player:");
		System.out.println("1 for human player\n2 for computer player\n3 for super computer player");
		Scanner sc = new Scanner(System.in);
		int type = sc.nextInt();
		return type;
	}
	
	private String getPlayerName(Character symbol) {
		System.out.println("What is " + symbol + " player name");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		return name;
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
		System.out.println("\n" + currentPlayer.name + "'s turn..\n");
	    for( Character [] row : board.rows){
	        for(Character mark : row){
	            System.out.print(mark + " ");
	        }
	        System.out.println();
	    }
	}
	
	private boolean placeMark (int [] pos, Character mark) throws IllegalArgumentException {
	    try{
	        board.setMark(pos, mark);      // throws exception when cell is not empty
	        return true;
	    }
	    catch (IllegalArgumentException e){
	    	System.out.println(e.getMessage());
	        return false;
	    }
	}

	private void playTurn(){
	    while(true){
	        currentPlayer = players.get(turn);
	        show();
	        int [] pos = currentPlayer.move(this, turn);
	        if(placeMark(pos, turn))
	        	break;
	    }

	    //swap turn
	    turn = (turn == 'X')? 'O' : 'X';
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.run();
	}

}
