
package com.tamboro.question.twoandthree;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *  This class contains the tic tac toe game implementation using swing  
 *  for n*n grid with p players.
 *  User can play with friend or computer.  
 */
public class TicTacToe extends JFrame implements ItemListener, ActionListener {

	Checkbox computerCheck;
	Checkbox friendCheck;
	JLabel l1; 
	JLabel l2;
	JButton b[][];
	JButton reset;
	private List<String> playerList = new ArrayList();
	int totalNoOfGrids;
	int noOfGrid;
	boolean type;
	int playerIndex;
	JLabel label;
	JButton exit;
	static final  String ERROR = "Error";
	/*
	 * This method implements logic for place selection and there validations.
	 */
	@Override
	public void actionPerformed(ActionEvent action) {

		if (action.getSource() == exit) { // exit the game
			System.exit(0);
		} else if (action.getSource() == reset) { // reset the board

			resetBoard();
			return;
		}

		String validSelection = checkValidSelection(action);
		
		if (validSelection != "") {

			JOptionPane.showMessageDialog(this, "This field already selected, Please choose anothor field.", ERROR,
					JOptionPane.ERROR_MESSAGE);

		} else {
			
			if (playerIndex == playerList.size() || !type) {
				playerIndex = 0;
			}

			String currentPalyer = playerList.get(playerIndex);

			for (int i = 0; i < noOfGrid; i++) {
				for (int j = 0; j < noOfGrid; j++) {
					if (b[i][j] == action.getSource()) {
						b[i][j].setText(currentPalyer);
						break;
					}
				}
			}

			playerIndex++;

			String nextPlayer;
			if (playerIndex < playerList.size() && type) {
				nextPlayer = playerList.get(playerIndex);
			} else {
				nextPlayer = playerList.get(0);
			}

			boolean isWon = checkForWin(currentPalyer); // after place select check for win

			if (isWon) {
				JOptionPane.showMessageDialog(this, currentPalyer + " You won the match !!!");
				resetBoard();
				label.setText("Player " + nextPlayer + " now your turn.");
				return;
			}

			if (!type) {// computer turn for next move

				while (true) {

					Random computer = new Random();
					int x = computer.nextInt(noOfGrid);
					int y = computer.nextInt(noOfGrid);

					if (b[x][y].getText() == "") {
						b[x][y].setText("c");
						break;
					}
				}

				isWon = checkForWin("c");

				if (isWon) {
					JOptionPane.showMessageDialog(this, "Computer won the match !!!");
					resetBoard();
					playerIndex = 0;
					return;
				}
				playerIndex = 0;
				nextPlayer = playerList.get(playerIndex);
			}

			label.setText("Player " + nextPlayer + " now your turn.");
			
			boolean isBoardFull = checkIsBoardFull();
			
			if(isBoardFull) {
				JOptionPane.showMessageDialog(this, "Board is full,please reset or exit.");
			}
			
		}

	}
	
	private String checkValidSelection(ActionEvent action) {
		String validSelection = null;
		for (int i = 0; i < noOfGrid; i++) { // check whether the selected place is already allocated or not.
			for (int j = 0; j < noOfGrid; j++) {
				if (b[i][j] == action.getSource()) {
					validSelection = b[i][j].getText();
					break;
				}
			}
		}
		return validSelection;
	}

	private void resetBoard() {
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b.length; j++) {
				b[i][j].setText("");
			}
		}
	}

	/*check the board is full or not*/
	private boolean checkIsBoardFull() {
		boolean isBoardFull = false;
		for (int i = 0; i < noOfGrid; i++) {
			for (int j = 0; j < noOfGrid; j++) {
				if(b[i][j].getText() != "") {
					isBoardFull = true;
				}else {
					isBoardFull = false;
					break;
				}
			}
			if(!isBoardFull) {
				break;
			}
		}
		
		return isBoardFull;
	}
	
	

	private boolean checkForWin(String currentPalyer) {

		boolean isWon = checkForRow(currentPalyer);

		if (isWon) {
			return isWon;
		}

		isWon = checkForColumn(currentPalyer);

		if (isWon) {
			return isWon;
		}

		isWon = checkForDiagonal(currentPalyer);

		return isWon;
	}

	/*check the rows of the board for win*/
	private boolean checkForRow(String currentPalyer) {
		boolean isWon = false;
		for (int i = 0; i < noOfGrid; i++) {
			for (int j = 0; j < noOfGrid; j++) {

				if (currentPalyer == b[i][j].getText()) {
					isWon = true;
				} else {
					isWon = false;
					break;
				}
			}
			if (isWon) {
				return isWon;
			}
		}
		return isWon;
	}
	/*check the column of the board for win*/
	private boolean checkForColumn(String currentPalyer) {
		boolean isWon = false;
		for (int i = 0; i < noOfGrid; i++) {
			for (int j = 0; j < noOfGrid; j++) {

				if (currentPalyer == b[j][i].getText()) {
					isWon = true;
				} else {
					isWon = false;
					break;
				}
			}
			if (isWon) {
				return isWon;
			}
		}
		return isWon;
	}

	/*check the board diagonally for win*/
	private boolean checkForDiagonal(String currentPalyer) {

		boolean isWon = false;

		for (int i = 0; i < noOfGrid; i++) {
			if (currentPalyer == b[i][i].getText()) {
				isWon = true;
			} else {
				isWon = false;
				break;
			}
		}

		if (isWon) {
			return isWon;
		}

		int j = noOfGrid - 1;
		for (int i = 0; i < noOfGrid; i++, j--) {
			if (currentPalyer == b[i][j].getText()) {
				isWon = true;
			} else {
				isWon = false;
				break;
			}

		}

		return isWon;
	}

	
	@Override
	public void itemStateChanged(ItemEvent e) {

		noOfGrid = Integer.parseInt(getGridSize());
		totalNoOfGrids = noOfGrid * noOfGrid;
		b = new JButton[noOfGrid][noOfGrid];

		if (computerCheck.getState()) {// playing with computer.
			type = false;

			String playerName = getNameOfThePlayer();
			playerList.add(playerName);
			playerList.add("c");

		}

		else if (friendCheck.getState()) {
			type = true;

			String noOfPlayers = getNoOfPlayers();

			int totalPlayers = Integer.parseInt(noOfPlayers);

			for (int i = 0; i < totalPlayers; i++) {
				String playerName = getNameOfThePlayer();
				playerList.add(playerName);
			}

		}

		remove(computerCheck);
		remove(friendCheck);

		setSize(noOfGrid * 100, noOfGrid * 125);

		repaint(0, 0, 330, 450);

		showButton(noOfGrid);

	}

	/*this method gives the no of players by doing proper validations.*/
	private String getNoOfPlayers() {

		String inputValue = JOptionPane.showInputDialog(this, "Enter no of Players .");

		if (inputValue == null || inputValue.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter valid inputs", ERROR, JOptionPane.ERROR_MESSAGE);
			inputValue = getNoOfPlayers();
		} else if (Integer.parseInt(inputValue) <= 1) {
			JOptionPane.showMessageDialog(this, "Players should be greater than 1", ERROR, JOptionPane.ERROR_MESSAGE);
			inputValue = getNoOfPlayers();
		}

		return inputValue;
	}

	/*this method gives  the name of players by doing proper validations.*/
	private String getNameOfThePlayer() {

		String inputValue = JOptionPane.showInputDialog(this, "Enter the player Name .");

		if (inputValue == null || inputValue.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter valid inputs", "Input Box", JOptionPane.ERROR_MESSAGE);
			inputValue = getNameOfThePlayer();
		} else if (playerList.contains(inputValue)) {
			JOptionPane.showMessageDialog(this, "This name already exist.", ERROR, JOptionPane.ERROR_MESSAGE);
			inputValue = getNameOfThePlayer();
		}

		return inputValue;
	}

	/*this method gives the no grids .*/
	private String getGridSize() {

		String inputValue = JOptionPane.showInputDialog(this, "Enter no of grids .");

		if (inputValue == null) {
			System.exit(1);
		} else if (inputValue.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter valid inputs", "Input Box", JOptionPane.ERROR_MESSAGE);
			inputValue = getGridSize();
		} else if (Integer.parseInt(inputValue) <= 2) {
			JOptionPane.showMessageDialog(this, "Grid size should be greater than 2", ERROR,
					JOptionPane.ERROR_MESSAGE);
			inputValue = getGridSize();
		}

		return inputValue;
	}

	
	
	/*this method shows the board buttons on jframe */
	void showButton(int noOfGrids) {
		int x = 100;
		int y = 100;
		int j = 0;
		for (int i = 0; i < (noOfGrids); i++) {
			for (int k = 0; k < noOfGrids; k++, j++, x += 50) {
				b[i][k] = new JButton();
				if (j == noOfGrids) {
					j = 0;
					y += 50;
					x = 100;
				}

				b[i][k].setBounds(x, y, 50, 50);
				add(b[i][k]);
				b[i][k].addActionListener(this);
			}

		} // eof for

		int height = 100;

		if (noOfGrids > 4) {
			height = 80;
		}

		label = new JLabel("Player " + playerList.get(0) + " now your turn.");
		label.setBounds(100, (noOfGrids * height) - 50, 200, 50);
		add(label);

		reset = new JButton("RESET");
		reset.setBounds(100, noOfGrids * height, 90, 50);
		add(reset);
		reset.addActionListener(this);

		exit = new JButton("EXIT");
		exit.setBounds(200, noOfGrids * height, 90, 50);
		exit.setForeground(Color.RED);
		add(exit);
		exit.addActionListener(this);

	}

	TicTacToe() {

		CheckboxGroup cbg = new CheckboxGroup();
		computerCheck = new Checkbox("vs computer", cbg, false);
		friendCheck = new Checkbox("vs friend", cbg, false);
		computerCheck.setBounds(120, 80, 100, 40);
		friendCheck.setBounds(120, 150, 100, 40);
		add(computerCheck);
		add(friendCheck);
		friendCheck.addItemListener(this);
		computerCheck.addItemListener(this);

		type = true;

		JLabel title = new JLabel("Please choose the option.");

		title.setBounds(100, 5, 200, 100);
		add(title);

		setTitle("TicTacToe");

		setLayout(null);
		setSize(350, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}// eof constructor

	public static void main(String[] args) { 

		new TicTacToe();
	}
}
