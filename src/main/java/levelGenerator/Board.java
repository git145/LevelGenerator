package levelGenerator;

/*
 * File: Board.java
 * Author: Richard Kneale
 * Student ID: 200790336
 * Date created: 6th July 2017
 * Description: Contains the information relating a board
 */

public class Board
{
	// The board has thirty-six squares
    private static final int NUM_SQUARES = 36;
    
    // An array representing the squares on the board
    private Square[] squares;

    // The position of the player on the board
    private int playerSquare = -1;

    // The number of moves made to reach this board
    private int movesMade = 0;
    
    // These values can be derived but will be assigned here to improve speed
    private int goalSquare = -1; // The ID of the square containing the goal crate
    private int numPillars = -1; // The number of pillars

    // Construct a board based on the number of squares
    public Board()
    {
        // Instantiate the array of squares
        squares = new Square[NUM_SQUARES];

        // Instantiate the individual squares
        for(int square = 0 ; square < NUM_SQUARES ; square++)
        {
            squares[square] = new Square(square);
        }
    }

    // Create a board based on another board
    public Board(Board boardToCopy)
    {
        // Instantiate the array of squares
        squares = new Square[NUM_SQUARES];

        // Instantiate the individual squares
        for(int square = 0 ; square < NUM_SQUARES ; square++)
        {
            // Access the square from the given board
            Square copySquare = boardToCopy.getSquare(square);

            // Instantiate the square
            squares[square] = new Square(square, copySquare.getHeight(), copySquare.getOccupied(), copySquare.getUp());  
        }
        
        // Copy other statistics of the board
        movesMade = boardToCopy.getMovesMade();
        goalSquare = boardToCopy.getGoalSquare();
    }

    // Returns a square from the board
    public Square getSquare(int square)
    {
        return squares[square];
    }

    // Returns the length of the array of squares
    public int getSquaresLength()
    {
        return squares.length;
    }

    // Change the position of the player
    public void setPlayerSquare(int playerPosition)
    {
        playerSquare = playerPosition;
    }

    // Returns the position of the player
    public int getPlayerSquare() 
    {
        return playerSquare;
    }

    // Returns the number of moves made to reach this board
    public int getMovesMade() 
    {
        return movesMade;
    }

    // Used to change the number of moves made to reach this board
    public void changeMovesMade(int movesMadeShift) 
    {
        movesMade += movesMadeShift;
    }

    // Returns the position of the goal crate on the board
	public int getGoalSquare() 
	{
		return goalSquare;
	}

	// Sets the position of the goal crate on the board
	public void setGoalSquare(int goal) 
	{
		goalSquare = goal;
	}

	// Returns the number of pillars on the board
	public int getNumPillars() 
	{
		return numPillars;
	}

	// Sets the number of pillars on the board
	public void setNumPillars(int pillars) 
	{
		numPillars = pillars;
	}
}