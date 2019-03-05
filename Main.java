import java.util.*;

public class Main {
	public static void main(String[] args) {
		int rows = 3;
		int cols = 3;
		Board board = new Board(rows, cols);

		Scanner in = new Scanner(System.in);

		while (!board.gameOver()) {
			try {
				System.out.print("Enter a square: ");
				int square = in.nextInt() - 1;
				int row = square / cols;
				int col = square % cols;
				board.play(row, col);
				System.out.println(board);
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		Character winner = board.getWinner();
		System.out.printf("%s wins the game!\n", winner != null ? winner.toString() : "The cat");
	}
}
