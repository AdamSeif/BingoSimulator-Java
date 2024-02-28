# Bingo Simulation

This repository contains Java classes for simulating a Bingo game. The `BingoCard` class represents a single Bingo card, and the `BingoSim` class simulates multiple Bingo cards.

## BingoCard

The `BingoCard` class represents a Bingo card. It has the following methods:

- `BingoCard(int[] data)`: Constructor for initializing a Bingo card with given data.
- `boolean isValid()`: Checks if the Bingo card is valid.
- `void drawn(int number)`: Marks a number as drawn on the Bingo card.
- `void drawn(int[] numbers)`: Marks multiple numbers as drawn on the Bingo card.
- `void reset()`: Resets the Bingo card.
- `boolean isAWinner()`: Checks if the Bingo card is a winner.
- `int minToWin()`: Calculates the minimum numbers needed to win.
- `String toString()`: Returns a string representation of the Bingo card.

## BingoSim

The `BingoSim` class simulates multiple Bingo cards. It has the following methods:

- `BingoSim(int max)`: Constructor for initializing the simulation with a maximum number of cards.
- `void addCard(BingoCard b)`: Adds a Bingo card to the simulation.
- `String simulate(int[] sequence)`: Simulates the drawing of numbers in a sequence and returns the simulation results.
- `String showCardsWithMin(int min)`: Shows cards with a minimum number needed to win.
- `int numDrawn()`: Counts the number of drawn numbers.
- `String toString()`: Returns a string representation of the simulation status.

## Usage

1. Instantiate a `BingoCard` object with data.
2. Optionally, check if the card is valid using `isValid()`.
3. Simulate multiple Bingo cards by adding them to a `BingoSim` object using `addCard()`.
4. Simulate drawing numbers using `simulate(sequence)`.
5. Check simulation status and winning cards using `toString()` and `showCardsWithMin(min)` respectively.

## Example

```java
public static void main(String[] args) {
    // Example usage
    int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    BingoCard card = new BingoCard(data);
    System.out.println(card.toString());

    BingoSim sim = new BingoSim(10);
    sim.addCard(card);
    int[] sequence = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    System.out.println(sim.simulate(sequence));
}
