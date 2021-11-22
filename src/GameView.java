import java.awt.*;
import javax.swing.*;

public class GameView {

	private JFrame mainFrame = new JFrame(); 
	private JPanel titlePanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JLabel textField = new JLabel();
	private JButton[][] buttons = new JButton[3][3];
	
	
	public GameView() {
		initFrame();
		initTextField();
		initPanels();
		initButtons();
		titlePanel.add(textField);
		mainFrame.add(titlePanel, BorderLayout.NORTH);
		mainFrame.add(buttonPanel);
	}

	void placeButtonMark(int[] pos, Character mark) {
		int row = pos[0], col = pos[1];
		JButton btn = buttons[row][col];
		btn.setForeground(new Color(255, 0, 0));
		btn.setText(mark + "");
	}

	public JButton[][] getButtons() {
		return buttons;
	}

	public void viewText(String msg) {
		textField.setText(msg);
	}

	private void initButtons() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				buttons[i][j] = new JButton();
				buttonPanel.add(buttons[i][j]);
				buttons[i][j].setFont(new Font("MV Boli", Font.BOLD, 120));
				buttons[i][j].setFocusable(false);
			}
		}
	}

	private void initPanels() {
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBounds(0, 0, 800, 100);

		buttonPanel.setLayout(new GridLayout(3, 3));
		buttonPanel.setBackground(new Color(150, 150, 150));
	}

	private void initTextField() {
		textField.setBackground(new Color(25, 25, 25));
		textField.setForeground(new Color(25, 255, 0));
		textField.setFont(new Font("Ink Free", Font.BOLD, 50));
		textField.setHorizontalAlignment(JLabel.CENTER);
		textField.setText("Welcome to Tic Tac Toe AI!");
		textField.setOpaque(true);
	}

	private void initFrame() {
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(800, 800);
		mainFrame.getContentPane().setBackground(new Color(50, 50, 50));
		mainFrame.setLayout(new BorderLayout());
	}

	public void startFrame() {
		mainFrame.setVisible(true);
	}
}
