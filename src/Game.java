import java.util.HashMap;
import java.util.Scanner;

public class Game {
	
	Board board;
	HashMap<Character, Player> players;
	Player currentPlayer;
	Character turn;
	BoardView boardView;
	
	public Game (Player p1, Player p2){
	    board = new Board();
	    players = new HashMap<>();
	    turn = 'X';
	    boardView = new BoardView(this);
//	    initializePlayers();               // -----------edit---------------
		players.put('X', p1);
		players.put('O', p2);
	    newTurn();
	}
	
	private int getPlayerType(Character symbol) {
		System.out.println("Please select type of "+ symbol +" player:");
		System.out.println("1 for human player\n2 for computer player\n3 for super computer player");
		try(Scanner sc = new Scanner(System.in)){
			int type = sc.nextInt();
			return type;
		}
	}
	
	private String getPlayerName(Character symbol) {
		System.out.println("What is " + symbol + " player name");
		try(Scanner sc = new Scanner(System.in)){
			String name = sc.nextLine();
			return name;
		}
	}

	void newTurn(){
	    if(board.isWon()){
	        Player winningPlayer = players.get(board.winner());
	        boardView.viewText("Congratulations " + winningPlayer.name +", you won the game!");
	    }
	    else if(board.isTied()){
	    	boardView.viewText("No one wins!");
	    }
	    else {
	    	boardView.viewText(turn + "'s turn");
	    	currentPlayer = players.get(turn);
	    	if(!(currentPlayer instanceof HumanPlayer))
	    		playComputerTurn();
	    }
	}
	
	boolean placeBoardMark (int [] pos, Character mark) throws IllegalArgumentException {
	    try{
	        board.setMark(pos, mark);      // throws exception when cell is not empty
	        return true;
	    }
	    catch (IllegalArgumentException e){
	    	boardView.viewText(e.getMessage());
	        return false;
	    }
	}
	
	void swapTurns() {
	    turn = (turn == 'X')? 'O' : 'X';
	    newTurn();
	}

	private void playComputerTurn(){
        currentPlayer = players.get(turn);
        int [] pos = currentPlayer.move(this, turn);
        if(placeBoardMark(pos, turn)) {
        	boardView.placeButtonMark(pos, turn);
        	swapTurns();
        }
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
	
	public static void main(String[] args) {
		Player h = new HumanPlayer("aya"), c = new SuperComputerPlayer("ali");
		Game game = new Game(h, c);
	}
}
