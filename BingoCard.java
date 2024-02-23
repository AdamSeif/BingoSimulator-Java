
public class BingoCard {
	private int[][] card = { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 420, 0, 0 }, { 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0 } };
	private boolean[][] taken = { { false, false, false, false, false }, { false, false, false, false, false },
			{ false, false, true, false, false }, { false, false, false, false, false },
			{ false, false, false, false, false } };
	public static final int[] MY_WINNER = { 7, 20, 32, 52, 73, 1, 23, 38, 53, 68, 2, 16, 54, 61, 3, 29, 33, 55, 62, 4,
			17, 34, 56, 63 };

	public BingoCard(int[] data) {

		/*
		 * This method cycles through the data array and checks the value of each entry.
		 * It then places the entry in the appropriate column of the card and changes
		 * its value to 100 (to prevent it from being entered into the card again). It
		 * does the same for each subsequent entry in the data array. It also
		 * initializes the taken array by setting all its indexes to false, then setting
		 * the center to true.
		 */

		for (int j = 0; j < 5; j++) {
			for (int i = 0; i < data.length; i++) {
				if (data[i] < 16) {
					card[j][0] = data[i];
					data[i] = 100;
					break;
				}
			}
		}
		for (int j = 0; j < 5; j++) {
			for (int i = 0; i < data.length; i++) {
				if (data[i] < 31) {
					card[j][1] = data[i];
					data[i] = 100;
					break;
				}
			}
		}
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < data.length; i++) {
				if (data[i] < 46) {
					card[j][2] = data[i];
					data[i] = 100;
					break;
				}
			}
		}
		for (int j = 3; j < 5; j++) {
			for (int i = 0; i < data.length; i++) {
				if (data[i] < 46) {
					card[j][2] = data[i];
					data[i] = 100;
					break;
				}
			}
		}
		for (int j = 0; j < 5; j++) {
			for (int i = 0; i < data.length; i++) {
				if (data[i] > 45 && data[i] < 61) {
					card[j][3] = data[i];
					data[i] = 100;
					break;
				}
			}
		}
		for (int j = 0; j < 5; j++) {
			for (int i = 0; i < data.length; i++) {
				if (data[i] > 45 && data[i] < 76) {
					card[j][4] = data[i];
					data[i] = 100;
					break;
				}
			}
		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				taken[i][j] = false;
			}
		}
		taken[2][2] = true;
	}

	private int[] cardToList(int[][] data) {

		/*
		 * This method takes a 2d integer array & turns it into a 1d array. Initially
		 * this was meant to be used as a helper method for the isValid() method, but
		 * the is valid method isn't working.
		 */

		int[] list = new int[25];
		int index = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				list[index] = data[i][j];
				index++;
			}
		}
		return list;
	}

	private boolean checkDuplicates(int[][] data) {

		/*
		 * This method cycles through a 1d array of all the numbers on a given card and
		 * checks that no two indexes refer to the same integer value. This method does
		 * not seem to be working.
		 */

		boolean noDuplicates = true;
		int[] list = cardToList(data);
		for (int i = 0, j = 1; i < list.length - 1; i++, j++) {
			if (list[i] == list[j]) {
				noDuplicates = false;
			}
		}
		return noDuplicates;
	}

	public boolean isValid() {

		/*
		 * This method uses the previous method to check for duplicates, and then a loop
		 * to check that all the values in each column are not too small; it does this
		 * for all five columns concurrently. This method does not seem to be working
		 * either.
		 */

		boolean noDuplicates = checkDuplicates(card);
		boolean position = false;
		for (int i = 0; i < 5; i++) {
			if (card[0][i] < 15) {
				position = true;
			} else if (card[1][i] > 16) {
				position = true;
			} else if (card[2][i] > 31) {
				position = true;
			} else if (card[3][i] > 46) {
				position = true;
			} else if (card[4][i] > 61) {
				position = true;
			}

		}

		if (noDuplicates == true && position == true)
			return true;
		else
			return false;
	}

	public void drawn(int number) {

		/*
		 * This method takes an integer value, then cycles through all the values of the
		 * card. If it finds a value in the card that matches the integer it was given,
		 * it sets the index of that value as true in the taken array.
		 */

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (card[i][j] == number)
					taken[i][j] = true;
			}
		}
	}

	public void drawn(int[] numbers) {

		/*
		 * This method functions exactly like the one above, except with an extra loop
		 * that enables it to cross reference several integers instead of one and "draw"
		 * them.
		 */

		for (int n = 0; n < numbers.length; n++) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (card[i][j] == numbers[n])
						taken[i][j] = true;
				}
			}
		}
	}

	public void reset() {

		/*
		 * This method cycles through the taken array setting all its values to 'false',
		 * then setting the center value to 'true'.
		 */

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				taken[i][j] = false;
			}
		}
		taken[2][2] = true;
	}

	private boolean horizontalWinner(boolean[][] data) {

		/*
		 * This method just contains a conditional that checks if all the variables in a
		 * row of a given card are true. It does this for all five rows and if any row
		 * is all true, the method returns 'true'.
		 */

		boolean winner = false;
		if ((data[0][0] == true && data[0][1] == true && data[0][2] == true && data[0][3] == true && data[0][4] == true)
				|| (data[1][0] == true && data[1][1] == true && data[1][2] == true && data[1][3] == true
						&& data[1][4] == true)
				|| (data[2][0] == true && data[2][1] == true && data[2][2] == true && data[2][3] == true
						&& data[2][4] == true)
				|| (data[3][0] == true && data[3][1] == true && data[3][2] == true && data[3][3] == true
						&& data[3][4] == true)
				|| (data[4][0] == true && data[4][1] == true && data[4][2] == true && data[4][3] == true
						&& data[4][4] == true)) {
			winner = true;
		}
		return winner;
	}

	private boolean verticalWinner(boolean[][] data) {

		/*
		 * This method just contains a conditional that checks if all the variables in a
		 * column of a given card are true. It does this for all five column and if any
		 * column is all true, the method returns 'true'.
		 */

		boolean winner = false;
		if ((data[0][0] == true && data[1][0] == true && data[2][0] == true && data[3][0] == true && data[4][0] == true)
				|| (data[0][1] == true && data[1][1] == true && data[2][1] == true && data[3][1] == true
						&& data[4][1] == true)
				|| (data[0][2] == true && data[1][2] == true && data[2][2] == true && data[3][2] == true
						&& data[4][2] == true)
				|| (data[0][3] == true && data[1][3] == true && data[2][3] == true && data[3][3] == true
						&& data[4][3] == true)
				|| (data[0][4] == true && data[1][4] == true && data[2][4] == true && data[3][4] == true
						&& data[4][4] == true)) {
			winner = true;
		}
		return winner;
	}

	private boolean diagonalWinner(boolean[][] data) {

		/*
		 * This method just contains a conditional that checks if all the diagonal
		 * variables in a given card are true. It does this for both diagonals and if
		 * either diagonal is all true, the method returns 'true'.
		 */

		boolean winner = false;
		if ((data[0][0] == true && data[1][1] == true && data[2][2] == true && data[3][3] == true && data[4][4] == true)
				|| (data[0][4] == true && data[1][3] == true && data[2][2] == true && data[3][1] == true
						&& data[4][0] == true)) {
			winner = true;
		}
		return winner;
	}

	public boolean isAWinner() {

		/*
		 * This method checks the return values of verticalWinner(), horizontalWinner(),
		 * and diagonalWinner() when the taken array is passed as a parameter. If any of
		 * those values is 'true', the method returns 'true'.
		 */

		boolean winner = false;
		if (verticalWinner(taken) == true || horizontalWinner(taken) == true || diagonalWinner(taken) == true) {
			winner = true;
		}
		return winner;
	}

	public int minToWin() {

		/*
		 * This method counts the number of false squares in each row, column, and along
		 * both diagonals of the taken array. It gives each row, column, and diagonal
		 * its own integer value equal to the number of false's in it, and the method
		 * returns the smallest of these integer values.
		 */

		int row1 = 0;
		int row2 = 0;
		int row3 = 0;
		int row4 = 0;
		int row5 = 0;

		int column1 = 0;
		int column2 = 0;
		int column3 = 0;
		int column4 = 0;
		int column5 = 0;

		int diagonal1 = 0;
		int diagonal2 = 0;

		for (int i = 0; i < 5; i++) {
			if (taken[0][i] == false) {
				row1++;
			}
		}
		for (int i = 0; i < 5; i++) {

			if (taken[1][i] == false) {
				row2++;
			}
		}
		for (int i = 0; i < 5; i++) {

			if (taken[2][i] == false) {
				row3++;
			}
		}

		for (int i = 0; i < 5; i++) {
			if (taken[3][i] == false) {
				row4++;
			}
		}

		for (int i = 0; i < 5; i++) {
			if (taken[4][i] == false) {
				row5++;
			}
		}

		for (int i = 0; i < 5; i++) {
			if (taken[i][0] == false) {
				column1++;
			}
		}

		for (int i = 0; i < 5; i++) {
			if (taken[i][1] == false) {
				column2++;
			}
		}

		for (int i = 0; i < 5; i++) {
			if (taken[i][2] == false) {
				column3++;
			}
		}

		for (int i = 0; i < 5; i++) {
			if (taken[i][3] == false) {
				column4++;
			}
		}

		for (int i = 0; i < 5; i++) {
			if (taken[i][4] == false) {
				column5++;
			}
		}

		for (int i = 0; i < 5; i++) {
			if (taken[i][i] == false) {
				diagonal1++;
			}
		}

		for (int i = 0; i < 5; i++) {
			if (taken[i][4 - i] == false) {
				diagonal2++;
			}
		}

		return Math.min(Math.min(Math.min(Math.min(row1, row2), Math.min(row3, row4)), Math.min(row5, diagonal1)), Math
				.min(Math.min(Math.min(column1, column2), Math.min(column3, column4)), Math.min(column5, diagonal2)));

	}

	public String toString() {

		/*
		 * This method creates an initial string containing the word "BINGO" then cycles
		 * through the card printing the numbers in there appropriate positions. It uses
		 * conditionals to mark whether or not each number has been drawn.
		 */

		String cardText = " B    I    N    G    O   \n";
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (card[i][j] == 420) {
					cardText += "[FR] ";
				} else if (card[i][j] < 10) {
					if (taken[i][j] == true) {
						cardText += "[ " + card[i][j] + "] ";
					} else if (taken[i][j] == false) {
						cardText += "  " + card[i][j] + "  ";
					}
				} else if (card[i][j] > 9) {
					if (taken[i][j] == true) {
						cardText += "[" + card[i][j] + "] ";
					} else if (taken[i][j] == false) {
						cardText += " " + card[i][j] + "  ";
					}
				}
			}

			cardText += "\n";
		}
		return cardText;
	}
}
