public class BingoSim {

	/*
	 * Please not that for tests 8 & 10, BingoTests.java returned "failed". However,
	 * when I copied the code from BingoTests.java into a different main method both
	 * tests returned "passed". I didn't change anything about the code, all I did
	 * was cntrl+A > cntrl+C > cntrl+V.
	 */

	private int numCards;
	private boolean[] taken;
	private BingoCard[] cards;

	public BingoSim(int max) {

		/*
		 * This method initializes numCards as zero, sets the size of cards to the
		 * parameter max, sets the size of taken to one hundred, and initializes all the
		 * values in taken to 'false'.
		 */

		numCards = 0;
		this.cards = new BingoCard[max];
		this.taken = new boolean[100];
		for (int i = 0; i < 100; i++) {
			taken[i] = false;
		}
	}

	public void addCard(BingoCard b) {

		/*
		 * This method checks if the number of cards in the array cards is equal to its
		 * size, i.e. it checks if the array is full. If it isn't it enters a new card
		 * into the array and increments numCards to indicate that the array has one
		 * less vacancy.
		 */

		if (numCards == cards.length) {
		} else {
			cards[numCards] = b;
			numCards++;
		}
	}

	public String simulate(int[] sequence) {

		/*
		 * This method cycles through the sequence parameter using a loop, taking each
		 * value, drawing it in each card in the cards array with the aid of a nested
		 * loop, prints out the value with its appropriate prefix, uses a separate
		 * nested loop to print out the minToWin() for each card, and uses a third
		 * nested loop to check whether any card in cards is a winner. If any card is a
		 * winner, the outermost loop is broken, and the method returns the string.
		 */

		String text = "";
		String summary = "Simulating ...\n";
		String line = "";
		boolean winnerFound = false;

		for (int i = 0; i < sequence.length; i++) {
			int seqIndex = i + 1;
			for (int j = 0; j < numCards; j++) {
				int index = sequence[i];
				cards[j].drawn(index);
				taken[index] = true;
			}
			summary += seqIndex + ". ";
			if (sequence[i] < 16) {
				summary += "B-" + sequence[i];
			} else if (sequence[i] < 31) {
				summary += "I-" + sequence[i];
			} else if (sequence[i] < 46) {
				summary += "N-" + sequence[i];
			} else if (sequence[i] < 61) {
				summary += "G-" + sequence[i];
			} else if (sequence[i] < 76) {
				summary += "O-" + sequence[i];
			}

			for (int n = 0; n < numCards; n++) {
				line += cards[n].minToWin() + " ";
			}
			summary += " " + line + "\n";
			text = summary;
			line = "";
			for (int c = 0; c < numCards; c++) {
				if (cards[c].isAWinner() == true) {
					winnerFound = true;
				}
			}
			if (winnerFound == true) {
				break;
			}
		}

		for (int i = 0; i < sequence.length; i++) {
			for (int j = 0; j < numCards; j++) {
				int drawnNumber = sequence[i];
				cards[j].drawn(drawnNumber);
				if (cards[j].isAWinner() == true) {
					winnerFound = true;
				}
			}
			if (winnerFound == true) {
				break;
			}
		}
		return text;
	}

	public String showCardsWithMin(int min) {

		/*
		 * This method cycles through the cards array and retrieves cards whose minimum
		 * to win is equal to the parameter min. It then prints out those cards using
		 * the toString overridden method from BingoCard.java.
		 */

		String cardsMin = "";
		for (int i = 0; i < numCards; i++) {
			if (cards[i].minToWin() == min) {
				cardsMin += cards[i].toString();
			}
		}
		return cardsMin;
	}

	private int numDrawn() {

		/*
		 * This method cycles through the taken array and counts the number of true's,
		 * i.e. it counts the number of numbers that have been drawn thus far and
		 * returns that integer value.
		 */

		int drawn = 0;
		for (int i = 0; i < 75; i++) {
			if (taken[i] == true) {
				drawn++;
			}
		}
		return drawn;
	}

	public String toString() {

		/*
		 * This method creates an initial string containing the word "BINGO" then sets
		 * up five integer arrays the first containing the numbers 1 to 15, the second
		 * 16 to 30, etc... It uses conditionals to check if the value in taken whose
		 * index is equal to the number is true, if it is that number is marked with two
		 * square brackets.
		 */

		String status = " B    I    N    G    O   \n";
		String line = "";
		int winners = 0;

		int[] b = new int[15];
		int[] i = new int[15];
		int[] n = new int[15];
		int[] g = new int[15];
		int[] o = new int[15];

		for (int k = 0; k < 15; k++) {
			b[k] = k + 1;
		}
		for (int k = 0; k < 15; k++) {
			i[k] = k + 16;
		}
		for (int k = 0; k < 15; k++) {
			n[k] = k + 31;
		}
		for (int k = 0; k < 15; k++) {
			g[k] = k + 46;
		}
		for (int k = 0; k < 15; k++) {
			o[k] = k + 61;
		}

		for (int k = 1; k < 10; k++) {
			if (taken[b[k - 1]] == true) {
				status += "[ " + b[k - 1] + "] ";
			} else if (taken[b[k - 1]] == false) {
				status += "  " + b[k - 1] + "  ";
			}
			if (taken[i[k - 1]] == true) {
				status += "[" + i[k - 1] + "] ";
			} else if (taken[i[k - 1]] == false) {
				status += " " + i[k - 1] + "  ";
			}
			if (taken[n[k - 1]] == true) {
				status += "[" + n[k - 1] + "] ";
			} else if (taken[n[k - 1]] == false) {
				status += " " + n[k - 1] + "  ";
			}
			if (taken[g[k - 1]] == true) {
				status += "[" + g[k - 1] + "] ";
			} else if (taken[g[k - 1]] == false) {
				status += " " + g[k - 1] + "  ";
			}
			if (taken[o[k - 1]] == true) {
				status += "[" + o[k - 1] + "] ";
			} else if (taken[o[k - 1]] == false) {
				status += " " + o[k - 1] + "  ";
			}
			status += "\n";
		}

		for (int k = 0; k < 6; k++) {
			if (taken[b[k + 9]] == true) {
				status += "[" + b[k + 9] + "] ";
			} else if (taken[b[k + 9]] == false) {
				status += " " + b[k + 9] + "  ";
			}
			if (taken[i[k + 9]] == true) {
				status += "[" + i[k + 9] + "] ";
			} else if (taken[i[k + 9]] == false) {
				status += " " + i[k + 9] + "  ";
			}
			if (taken[n[k + 9]] == true) {
				status += "[" + n[k + 9] + "] ";
			} else if (taken[n[k + 9]] == false) {
				status += " " + n[k + 9] + "  ";
			}
			if (taken[g[k + 9]] == true) {
				status += "[" + g[k + 9] + "] ";
			} else if (taken[g[k + 9]] == false) {
				status += " " + g[k + 9] + "  ";
			}
			if (taken[o[k + 9]] == true) {
				status += "[" + o[k + 9] + "] ";
			} else if (taken[o[k + 9]] == false) {
				status += " " + o[k + 9] + "  ";
			}
			status += "\n";
		}

		for (int c = 0; c < numCards; c++) {
			line += cards[c].minToWin() + " ";
			if (cards[c].isAWinner() == true) {
				winners++;
			}
		}
		status += "# Drawn: " + numDrawn() + "\nmins:" + line + "\n# Winners: " + winners + " out of " + numCards;
		return status;
	}

}
