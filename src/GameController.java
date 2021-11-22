import javax.swing.JButton;

public class GameController {
	
	private Game game;
	private GameView view;
	
	public GameController() {
		this.game = new Game();
		this.view = new GameView();
		initView();
		initController();
	}
	
	private void initController() {
		JButton [][] buttons = view.getButtons();
		for(int i = 0; i < 3; i++) 
			for(int j = 0; j < 3; j++) {
				int [] btnPos = new int [] {i, j};
				buttons[i][j].addActionListener(e -> placeMark(btnPos)); 
			}
		run();
    }
	
	private void placeMark(int [] btnPos) {
		if(game.placeBoardMark(btnPos)) {
			view.placeButtonMark(btnPos, game.turn);
			swapTurns();
		}
	}
	
	private void displayTurn() {
		view.viewText(game.turn + "'s turn");
	}
	
	private void run() {
		while(!game.isOver()) {
			displayTurn();
			if(!(game.currentPlayer() instanceof HumanPlayer)) 
				playComputerTurn();
		}
		
		if(game.isWon()){
	        Player winningPlayer = game.winner();
	        view.viewText(winningPlayer.name +" won the game!");
	    }
	    else {
	        view.viewText("No one wins!");
	    }
		view.toggleButtons();
	}
	
	void swapTurns() {
	    game.swap();
	}

	private void playComputerTurn(){  
		// disable humanPlayer from clicking buttons while its computer's turn
		view.toggleButtons();
		try {                                    
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		    return; 
		    }
        int [] pos = game.getMove(); 
        if(game.placeBoardMark(pos))         
        	view.placeButtonMark(pos, game.turn);  
        view.toggleButtons();
        swapTurns();                        
	}
	
	private void initView() {
		view.startFrame();
		view.viewText("Amazing AI Tic Tac Toe!");
		try {                                    
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		    return; 
		    }
	}	
}
