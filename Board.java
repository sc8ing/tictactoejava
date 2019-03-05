import java.util.*;

public class Board {
	// 'X' is before 'O', so it goes first (can be changed)
	private static Character[] players = {'X', 'O'};
	private Character[][] board;
	private int turnsPlayed;

	public Board(int rows, int cols) {
		this.board = new Character[rows][cols];
		this.turnsPlayed = 0;
	}

	public void play(int row, int col) throws Exception {
		if (row >= board.length || col >= board[0].length || row < 0 || col < 0)
			throw new Exception("Row or column out of bounds");

		if (board[row][col] != null)
			throw new Exception("Someone's already played in this square");

		Character currentPlayer = players[turnsPlayed % Board.players.length];
		board[row][col] = currentPlayer;
		turnsPlayed++;
	}

	public boolean gameOver() {
		if (turnsPlayed >= board.length * board[0].length)
			return true;

		if (horizontalWin() != null
				|| verticalWin() != null
				|| diagonalWin() != null)
			return true;

		return false;
	}

	public Character getWinner() {
		if (!gameOver()) return null;

		Character winner = horizontalWin();
		if (winner != null) return winner;

		winner = verticalWin();
		if (winner != null) return winner;

		winner = diagonalWin();
		if (winner != null) return winner;

		return null;
	}

	private Character horizontalWin() {
		for (int row=0; row<board.length; row++) {
			Character first = board[row][0];
			for (int col=0; col<board[0].length; col++) {
				if (board[row][col] != first)
					break;
				if (col == board[row].length - 1)
					return board[row][col];
			}
		}
		return null;
	}

	private Character verticalWin() {
		for (int col=0; col<board[0].length; col++) {
			Character top = board[0][col];
			for (int row=0; row<board.length; row++) {
				if (board[row][col] != top)
					break;
				if (row == board.length - 1)
					return board[row][col];
			}
		}
		return null;
	}
	private Character diagonalWin() {
		// diagonal wins not allowed in these rules when board's not square
		if (board.length != board[0].length)
			return null; 

		Character topLeft = board[0][0];
		Character topRight = board[0][board.length-1];

		for (int d=0; d<board.length; d++) {
			if (topLeft != board[d][d])
				topLeft = null;
			if (topRight != board[d][board.length-1-d])
				topRight = null;
			if (topLeft == null && topRight == null)
				return null;
		}
		return topLeft == null ? topLeft : topRight;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int row=0; row<board.length; row++) {
			for (int col=0; col<board[0].length; col++) {
				sb.append(board[row][col] != null ? board[row][col] : ' ');
				if (col != board[0].length - 1)
					sb.append(",");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
