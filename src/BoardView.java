import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class BoardView implements ActionListener {
	
	JFrame frame = new JFrame();
	JPanel titlePanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JLabel textField = new JLabel();
	JButton[][] buttons = new JButton[3][3];
	Game game;
	
	public BoardView (Game game) {
		this.game = game;
		initializeFrame();
		initializeTextField();
		initializePanels();
		initializeButtons();
		titlePanel.add(textField);
		frame.add(titlePanel,BorderLayout.NORTH);
		frame.add(buttonPanel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < 3; i++) 
			for(int j = 0; j < 3; j++) 
				if(e.getSource() == buttons[i][j]) {
					int [] pos = new int [] {i, j};
					if(game.placeBoardMark(pos, game.turn)) {
						placeButtonMark(pos, game.turn);
						game.swapTurns();
					}
				}
    }
	
	void placeButtonMark(int [] pos, Character mark) {
		int row = pos[0], col = pos[1];
		JButton btn = buttons[row][col];
		btn.setForeground(new Color(255,0,0));
		btn.setText(mark + "");
	}
	
	private void initializePanels() {
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBounds(0,0,800,100);
		
		buttonPanel.setLayout(new GridLayout(3,3));
		buttonPanel.setBackground(new Color(150,150,150));
	}
	
	private void initializeTextField() {
		textField.setBackground(new Color(25,25,25));
		textField.setForeground(new Color(25,255,0));
		textField.setFont(new Font("Ink Free",Font.BOLD,75));
		textField.setHorizontalAlignment(JLabel.CENTER);
		textField.setText("Welcome to Tic Tac Toe AI!");
		textField.setOpaque(true);
	}
	
	private void initializeFrame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
	}
	
	private void initializeButtons() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				buttons[i][j] = new JButton();
				buttonPanel.add(buttons[i][j]);
				buttons[i][j].setFont(new Font("MV Boli",Font.BOLD,120));
				buttons[i][j].setFocusable(false);
				buttons[i][j].addActionListener(this);
			}
		}
	}
	
	public void viewText(String msg) {
		textField.setText(msg);
	}
}
