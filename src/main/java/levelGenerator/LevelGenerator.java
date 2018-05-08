package levelGenerator;

/*
 * File: LevelGenerator.java
 * Author: Richard Kneale
 * Student ID: 200790336
 * Date created: 6th July 2017
 * Description: Writes valid levels to text files
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

// Writes valid levels to text files
public class LevelGenerator 
{
	/* FIELDS DEFINED HERE SHOULD BE USED THROUGHOUT THE EXECUTION OF THE PROGRAM OR INITIALISED USING A METHOD FOR PRACTICALITY */
	
	// For writing to a .txt file
	private static String FILENAME = "levels.txt";
	private static final int IS_FIRST_LEVEL_POSITION = 0;
	private static final int IS_MORE_LEVELS_POSITION = 1;
	
	// Defined here as it is initialised using a try/catch statement
	// The BufferedWriter may therefore not be initialised if defined locally
	private static BufferedWriter bufferedWriter; 
	
	// Strings representing difficulty levels
	private static final String BEGINNER_TEXT = "beginner";
	private static final String INTERMEDIATE_TEXT = "intermediate";
	private static final String ADVANCED_TEXT = "advanced";
	private static final String EXPERT_TEXT = "expert";
	
	// The board has thirty-six squares
	private static final int NUM_SQUARES = 36;

    // Define the minimum number of pillars for each difficulty level
	private static final int PILLARS_BEGINNER_MIN = 4;
	private static final int PILLARS_INTERMEDIATE_MIN = 7;
	private static final int PILLARS_ADVANCED_MIN = 9;
	private static final int PILLARS_EXPERT_MIN = 9;

    // Define the maximum number of pillars for each difficulty level
	private static final int PILLARS_BEGINNER_MAX = 8;
	private static final int PILLARS_INTERMEDIATE_MAX = 10;
	private static final int PILLARS_ADVANCED_MAX = 14;
	private static final int PILLARS_EXPERT_MAX = 16;

    // Define the minimum number of moves for each difficulty level
	private static final int MOVES_BEGINNER_MIN = 3;
	private static final int MOVES_INTERMEDIATE_MIN = 5;
	private static final int MOVES_ADVANCED_MIN = 5;
	private static final int MOVES_EXPERT_MIN = 7;

    // Define the maximum number of moves for each difficulty level
	private static final int MOVES_BEGINNER_MAX = 7;
	private static final int MOVES_INTERMEDIATE_MAX = 8;
	private static final int MOVES_ADVANCED_MAX = 10;
	private static final int MOVES_EXPERT_MAX = 10;

    // Allocate integers for the minimum and maximum number of pillars and moves
    private static final int MIN_PILLARS = 0;
    private static final int MAX_PILLARS = 1;
    private static final int MIN_MOVES = 2;
    private static final int MAX_MOVES = 3;

    // Define the maximum number of pillars of each type
    private static final int NUMBER_OF_PILLARS_TWO_HIGH = 10;
    private static final int NUMBER_OF_PILLARS_THREE_HIGH = 4;
    private static final int NUMBER_OF_PILLARS_FOUR_HIGH = 2;
    
    // The height of different types of pillar
    private static final int PILLAR_HEIGHT_ZERO = 0;
    private static final int PILLAR_HEIGHT_ONE = 1;
    private static final int PILLAR_HEIGHT_TWO = 2;
    private static final int PILLAR_HEIGHT_THREE = 3;
    private static final int PILLAR_HEIGHT_FOUR = 4;
    
    // Directions
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
	
	public static void main(String[] args) 
	{
		// Print a description of the program
		System.out.println("\n*TIP THE CRATES LEVEL GENERATOR V1.0.0*\n\nUse this program to write valid levels"
				+ " to level.txt to be imported by the Android application Tip the Crates.\nIt is recommended that"
				+ " you balance the number of levels that can be completed in a certain number of moves evenly for each difficulty.\n"
				+ "The game will have more variety if more levels are created.\nLevels completable in three moves are "
				+ "generated quickly.\nThe time to generate a level increases with the number of moves required.\n");
		
		// Set up the file for writing
		Boolean isExistingFile = setFile(FILENAME);
		
		// Create a scanner
	    Scanner in = new Scanner(System.in); 
	    
	    // Define whether the first level has been generated for correct formatting of the file
	    Boolean isFirstLevel = true;
		
		// Generate valid levels and write them to the file
		while(true)
		{
			// Write the levels and determine whether the user wants to generate more levels
			Boolean[] isFirstMoreLevels = writeLevels(in, isFirstLevel, isExistingFile, bufferedWriter, FILENAME);
			
			// Update Boolean values
			isFirstLevel = isFirstMoreLevels[IS_FIRST_LEVEL_POSITION];
			Boolean isMoreLevels = isFirstMoreLevels[IS_MORE_LEVELS_POSITION];
			
			// Leave the loop when the user does not want to generate more levels
			if (!isMoreLevels)
				break;
		}
		
		// Close the BufferedWriter
		try 
		{
			bufferedWriter.close();
			System.out.print(FILENAME + " closed. ");
		} 
		catch (IOException e) 
		{
			System.out.print(FILENAME + " could not be closed. ");
		}
		
		// Close the Scanner
		in.close(); 
		System.out.print("Input stream closed. ");
		
		// Inform the user that the program has finished
		System.out.println("Goodbye!");
	} // main()
	
	// Connect to a file
	// Returns whether a file with FILENAME existed prior to executing the program for correct formatting of the file
	private static Boolean setFile(String fName)
	{
		// Recognises whether a file with FILENAME existed prior to executing the program for correct formatting of the file
		Boolean existingFile = false;
		
		// Create a reference to a file with the given fileName
		File file = new File(fName);
	        
	    // Connect to the file
	    if (!file.exists()) // If the file does not already exist
	    {
	    	// Create the file
	    	try
	    	{ 	
	    		// Connect the file
	    		bufferedWriter = new BufferedWriter(new FileWriter(file));
	    		
	    		// Inform the user that a new file was created
		    	System.out.println(fName + " did not already exist. " + fName + " created.\n");
			} 
	    	catch (IOException e) 
	    	{
	    		// Inform the user of an error
	    		System.out.println(fName + " did not already exist. Error creating " + fName + ".\n");
			}
	    }
	    else // If the file already exists
	    {
	    	try 
	    	{
	    		// Connect to the existing file to append text
	    		bufferedWriter = new BufferedWriter(new FileWriter(file, true));
	    		existingFile = true;
	    		
	    		// Inform the user that an existing file will be appended to
	    		System.out.println(fName + " already exists. Output will be appended to " + fName + ".\n");
			} 
	    	catch (IOException e) 
	    	{
	    		// Inform the user of an error
	    		System.out.println(fName + " already exists. Error accessing " + fName + ".\n");
			}
	    }
	    
	    return existingFile;
	} // setFile()
	
	// Writes valid levels to a file based on the specification of a user
	private static Boolean[] writeLevels(Scanner scanner, Boolean firstLevel, Boolean existingFile, BufferedWriter bWriter, String fName)
	{		
		// Allow the user to enter a difficulty level
		int[] boardStatistics = enterDifficulty(scanner);
					
		// Allow the user to enter a number of moves
		int numMoves = enterMoves(boardStatistics, scanner);
					
		// Allow the user to enter a number of levels
		int numLevels = enterLevels(numMoves, scanner);
					
		// For each level
		for(int level = 1 ; level <= numLevels ; level++)
		{
			// Generate a board that meets the specification
			Board board = newLevel(numMoves, level, boardStatistics);
			
			// Try to write the level to a file
			try
	    	{	
				// If it is not the first level generated or it is the first level appended to an existing file
				if(!firstLevel || (firstLevel && existingFile))
					bWriter.newLine(); // Move to the next line of the file
				
				// Write the height of the pillars in each space to the buffer
				for(int square = 0 ; square < NUM_SQUARES ; square++)
					bWriter.write(board.getSquare(square).getHeight() + ","); // Add the height of the square to the buffer
				
				// Although derivable
				// To access game statistics quickly and improve performance of the Android game
				bWriter.write(board.getNumPillars() + ","); // Add the number of pillars
				bWriter.write(numMoves + ","); // Add the number of moves
				bWriter.write(board.getPlayerSquare() + ","); // Add the starting position
				bWriter.write(String.valueOf(board.getGoalSquare())); // Add the goal position

	    		// Write the buffered output bytes to the file
				bWriter.flush();

	    		// Inform the user that the file was written successfully
	    		System.out.println("Level " + level + " written to " + fName + ".\n");
	    		
	    		// Recognise that the first level was written for correct formatting of the file
	    		if(firstLevel)
	    			firstLevel = false;
	    	} 
	    	catch (IOException e) 
	    	{
	    		// Inform the user that there was a problem writing the file
	    		System.out.println("There was a problem writing to " + fName + ". Level " + level + " not written");
	    	}
		}
		
		// Represents whether the user want to write more levels
		Boolean moreLevels;
		
		// While the user has not entered a valid value
		while(true)
		{
			// Inform the user of their options and allow them to enter their choice
			System.out.print("Do you want to generate more levels (Y/N)? ");
			
			// Read the input of the user
			String userMoreLevels = scanner.nextLine();
			
			// The user selects to generate more levels
			if(userMoreLevels.equals("Y"))
			{
				moreLevels = true;
				System.out.println("More levels will be generated.\n");
				break;
			}
			 	
			// The user selects to not generate more levels
			else if(userMoreLevels.equals("N"))
			{
				moreLevels = false;
				break;
			}
				
			// The user enters an invalid value
			else
				// Inform the user that the value was invalid
				System.out.println("You entered " + userMoreLevels + ". This value is invalid.\n"); 
		}
		
		// Contains whether the first level will be generated next and whether more levels will be generated
		Boolean[] firstMoreLevels = {firstLevel, moreLevels};
		return firstMoreLevels;
	} // writeLevels()
	
	// Allows the user to enter a difficulty
	// Returns the minimum and maximum number of pillars and moves
	private static int[] enterDifficulty(Scanner input)
	{
		// Array representing the minimum and maximum number of pillars and moves
		int[] boardPreferences =  new int[4];
		
		// Set valid user input values
		String beginnerAbbreviation = String.valueOf(Character.toUpperCase(BEGINNER_TEXT.charAt(0))); // B
		String intermediateAbbreviation = String.valueOf(Character.toUpperCase(INTERMEDIATE_TEXT.charAt(0))); // I
		String advancedAbbreviation = String.valueOf(Character.toUpperCase(ADVANCED_TEXT.charAt(0))); // A
		String expertAbbreviation = String.valueOf(Character.toUpperCase(EXPERT_TEXT.charAt(0))); // E
		
		// While the user has not entered a valid value
		while(true)
		{
			// Inform the user of their options
			System.out.print("Enter a difficulty. " + beginnerAbbreviation + " - Beginner, " + intermediateAbbreviation + " -Intermediate, " + 
					advancedAbbreviation + " - Advanced, " + expertAbbreviation + " - Expert. "); 
			
			// Read the input of the user
			String userDifficulty = input.nextLine();
			
			// The user selects beginner
			if(userDifficulty.equals(beginnerAbbreviation))
			{
				boardPreferences[MIN_PILLARS] = PILLARS_BEGINNER_MIN;
				boardPreferences[MAX_PILLARS] = PILLARS_BEGINNER_MAX;
				boardPreferences[MIN_MOVES] = MOVES_BEGINNER_MIN;
				boardPreferences[MAX_MOVES] = MOVES_BEGINNER_MAX;
			    difficultyChangedConfirmation(boardPreferences, BEGINNER_TEXT);
				break;
			}
			 	
			// The user selects intermediate
			else if(userDifficulty.equals(intermediateAbbreviation))
			{
				boardPreferences[MIN_PILLARS] = PILLARS_INTERMEDIATE_MIN;
				boardPreferences[MAX_PILLARS] = PILLARS_INTERMEDIATE_MAX;
				boardPreferences[MIN_MOVES] = MOVES_INTERMEDIATE_MIN;
				boardPreferences[MAX_MOVES] = MOVES_INTERMEDIATE_MAX;
			    difficultyChangedConfirmation(boardPreferences, INTERMEDIATE_TEXT);
				break;
			}
			 
			// The user selects advanced
			else if(userDifficulty.equals(advancedAbbreviation))
			{
				boardPreferences[MIN_PILLARS] = PILLARS_ADVANCED_MIN;
				boardPreferences[MAX_PILLARS] = PILLARS_ADVANCED_MAX;
				boardPreferences[MIN_MOVES] = MOVES_ADVANCED_MIN;
				boardPreferences[MAX_MOVES] = MOVES_ADVANCED_MAX;
			    difficultyChangedConfirmation(boardPreferences, ADVANCED_TEXT);
				break;
			}
			 
			// The user selects expert
			else if(userDifficulty.equals(expertAbbreviation))
			{
				boardPreferences[MIN_PILLARS] = PILLARS_EXPERT_MIN;
				boardPreferences[MAX_PILLARS] = PILLARS_EXPERT_MAX;
				boardPreferences[MIN_MOVES] = MOVES_EXPERT_MIN;
				boardPreferences[MAX_MOVES] = MOVES_EXPERT_MAX;
			    difficultyChangedConfirmation(boardPreferences, EXPERT_TEXT);
				break;
			}
				
			// The user enters an invalid value
			else
				// Inform the user that the value was invalid
				System.out.println("You entered " + userDifficulty + ". This value is invalid.\n");
		}
		
		return boardPreferences;
	} // enterDifficulty()
	
	// Inform the user of a change in difficulty
	private static void difficultyChangedConfirmation(int[] boardStats, String difficultyText)
	{
		System.out.println("\nDifficulty changed to " + difficultyText + ".\nMinimum number of pillars: " + boardStats[MIN_PILLARS] + ", maximum number of"
				+ " pillars: " + boardStats[MAX_PILLARS] + ", minimum number of moves: " + boardStats[MIN_MOVES] + ", maximum number of"
				+ " moves: " + boardStats[MAX_MOVES] + ".\n");
	}
	
	// Returns a valid number of moves entered by a user
	private static int enterMoves(int[] boardPreferences, Scanner input)
	{
		// Allow the user to enter a number of moves
		int userMoves;
		
		// While the user has not entered a valid value
		while(true)
		{
			// Inform the user of valid inputs
			System.out.print("How many moves will be required to complete the level(s) (minimum " + boardPreferences[MIN_MOVES] + ", maximum " + 
					boardPreferences[MAX_MOVES] + ")? "); 
			
			// If the user enters an integer
			if(input.hasNextInt()) 
			{
				// Read the integer
				userMoves = input.nextInt();
				
				// If the user enters a valid value
				if((userMoves >= boardPreferences[MIN_MOVES]) && (userMoves <= boardPreferences[MAX_MOVES]))
				{
					// Inform the user their value was accepted
					System.out.println("Levels will require " + userMoves + " moves to complete.\n");
					break;
				}
				
				// If the user enters any other value
				else
					// Inform the user that their invalid value was interpreted
					System.out.println("You entered " + userMoves + ". This value is invalid.\n");
			}
			
			// If the user does not enter an integer
			else 
				integerPrompt(input);
		}
		
		// Return the choice of the user
		return userMoves;
	} // enterMoves()
	
	// Returns a valid number of levels to generate entered by a user
	private static int enterLevels(int userMoves, Scanner input)
	{
		// The number of levels to generate
		int userLevels;
		
		// Create a cap on the number of levels that a user can enter based on the number of moves
		// This is because it takes longer to generate levels that require more moves to complete
		int levelsCap;
		if(userMoves <= 3)
			levelsCap = 50;
		else if(userMoves == 4)
			levelsCap = 25;
		else if(userMoves == 5)
			levelsCap = 10;
		else
			levelsCap = 5;
		
		// While the user has not entered a valid value
		while(true)
		{
			// Inform the user of valid inputs
			System.out.print("How many levels would you like to generate (the value is capped based on the number of moves - " +
					userMoves + " moves has a cap of " + levelsCap +")? ");
			
			// If the user enters an integer
			if(input.hasNextInt()) 
			{
				// Read the integer
				userLevels = input.nextInt();
				
				// If the user enters a valid value
				if((userLevels > 0) && (userLevels <= levelsCap))
				{
					// Inform the user that their value was accepted
					System.out.println(userLevels + " level(s) will be generated.\n");
					break;
				}
				
				// If the value of the user exceeds the cap
				else if(userLevels > levelsCap)
					System.out.println("You cannot exceed the cap.\n");
				
				// If the user does not enter at least one
				else if(userLevels <= 0)
					System.out.println("You must generate at least one level.\n");
				
				// If the user enters any other value
				else
					// Inform the user that their invalid value was interpreted
					System.out.println("You entered " + userLevels + ". This value is invalid.\n");
			}
			
			// If the user does not enter an integer
			else
				integerPrompt(input);
		}
		
		// Clear leftover data
		input.nextLine(); 
				
		// Return the choice of the user
		return userLevels;
	} // enterLevels()
	
	// Inform the user that they must enter an integer
	private static void integerPrompt(Scanner inputValue)
	{
		System.out.println("The value must be an integer.\n");
		inputValue.next();
	}
	
	// Returns a valid new level
	private static Board newLevel(int userMoves, int levelNumber, int[] boardPreferences)
    {
    	// Assign data for a board object
    	Board newBoard;
    	
    	// A measure of the boards generated and time to identify a valid board
        int boardsGenerated = 0;
        
        // Measure the time on the system clock
        long startTime = System.currentTimeMillis(); 
    	
        // Identify a valid board and present it to the player
        while(true)
        {
            // Create an empty board and then populate it
        	newBoard = newBoard(boardPreferences[MIN_PILLARS], boardPreferences[MAX_PILLARS], userMoves);
        	
        	// Increment the number of boards generated
        	boardsGenerated++;
        	
        	// Provide feedback to the user
        	System.out.print("Level " + levelNumber + ". Board " + boardsGenerated + ". ");
        	
            // Leave the loop if the board has a solution
            if(getIsValid(newBoard, userMoves))
            	break;
        }
        
        // Calculate the elapsed time
        long endTime = System.currentTimeMillis();
        long elapsedMilliSeconds = endTime - startTime;
        double elapsedSeconds = elapsedMilliSeconds / 1000.0;
        
        // Provide feedback to the user
        System.out.println("Board accepted. Time elapsed: " + elapsedSeconds + "s.\n");
        System.out.println("Average time to generate and analyse a board during generation of level " + 
        		levelNumber + ": " + (elapsedSeconds/boardsGenerated)  + "s.\n");
        
        return newBoard;
    } // newLevel()
    
    // Initialises the data describing the squares on the board
    private static Board newBoard(int minPillars, int maxPillars, int numberOfMoves)
    {
        // Instantiate an empty board
        Board newBoard = new Board();

        // List the empty squares on the board
        LinkedList<Square> emptySquares = new LinkedList<>();
        int numberOfSquares = newBoard.getSquaresLength();
        for(int square = 0 ; square < numberOfSquares ; square++)
            emptySquares.addLast(newBoard.getSquare(square));

        // Add a goal block to a random square and remove the square from the list of empty squares
        int squareRandom; // A variable to represent a random square
        Random random = new Random(); // Used to generate random numbers
        squareRandom = random.nextInt(emptySquares.size()); // Get the ID of an empty square at random
        Square emptySquareReference; // A variable to reference the random empty square
        emptySquareReference = emptySquares.remove(squareRandom); // Access the empty square in the board
        emptySquareReference.setAttributes(PILLAR_HEIGHT_ONE, true, true); // Add the goal crate to the board
        newBoard.setGoalSquare(emptySquareReference.getID()); // Recognise this square as the goal

        // Create a list of pillars that can be added to the level
        LinkedList<Pillar> pillars = new LinkedList<>();
        for(int pillarsTwoHigh = 0 ; pillarsTwoHigh < NUMBER_OF_PILLARS_TWO_HIGH ; pillarsTwoHigh++)
            pillars.addLast(new Pillar(PILLAR_HEIGHT_TWO));
        for(int pillarsThreeHigh = 0 ; pillarsThreeHigh < NUMBER_OF_PILLARS_THREE_HIGH ; pillarsThreeHigh++)
            pillars.addLast(new Pillar(PILLAR_HEIGHT_THREE));
        for(int pillarsFourHigh = 0 ; pillarsFourHigh < NUMBER_OF_PILLARS_FOUR_HIGH ; pillarsFourHigh++)
        	pillars.addLast(new Pillar(PILLAR_HEIGHT_FOUR));

        // Add the first non-goal pillar to a random square
        squareRandom = random.nextInt(emptySquares.size()); // Get the ID of an empty square at random
        emptySquareReference = emptySquares.remove(squareRandom); // Access the empty square in the board
        int pillarRandom; // A variable to represent a random pillar
        pillarRandom = random.nextInt(pillars.size()); // Get the number of a random pillar from the list of pillars
        Pillar pillarReference; // Create an object to reference pillars
        pillarReference = pillars.remove(pillarRandom); // Access the pillar
        emptySquareReference.setAttributes(pillarReference.getHeight(), true, true); // Add the starting pillar to the board
        newBoard.setPlayerSquare(emptySquareReference.getID()); // Recognise this square as the starting position of the player

        // An integer to represent the number of pillars added to the board
        // The initial value is two as the goal crate and starting crate have already been added
        int numberOfPillars = 2;

        // Integers to represent the odds of not having the maximum number of pillars for the difficulty
        int minPillarsForMoves = numberOfMoves + 1; // The goal pillar plus useable pillars is minimum to make required moves
        int notMaxPillarsOdds = (maxPillars - minPillarsForMoves) + 1;

        // Add more pillars
        while(numberOfPillars < maxPillars)
        {
            // Randomly break out of the loop if the board has more than the minimum number of pillars
            // so the level doesn't always have the maximum number of pillars for the difficulty
            if((numberOfPillars >= minPillarsForMoves) && (random.nextInt(notMaxPillarsOdds) == 0))
            	break;

            // Add a pillar to a random square
            squareRandom = random.nextInt(emptySquares.size()); // Get the ID of an empty square at random
            emptySquareReference = emptySquares.remove(squareRandom); // Access the empty square in the board
            pillarRandom = random.nextInt(pillars.size()); // Get the number of a random pillar from the list of pillars
            pillarReference = pillars.remove(pillarRandom); // Access a random pillar
            emptySquareReference.setAttributes(pillarReference.getHeight(), true, true); // Add the pillar to the board
            numberOfPillars++; // Increase the number of pillars
        }
        
        // Add the number of pillars to the board object
        newBoard.setNumPillars(numberOfPillars);
        
        return newBoard;
    } // newBoard()

    // Determine whether the board is valid
    private static Boolean getIsValid(Board initialBoard, int movesToSolution)
    {
        // A list of boards to check
        LinkedList<Board> toInvestigate = new LinkedList<>();

        // Add current to the list of boards to check
        toInvestigate.addLast(initialBoard);

        // Represents whether the game has a state with a solution
        Boolean isValid = false;
        Boolean isEarlySolution = false;

        // A breadth-first search will be used to find the first solution state while not all game states have been checked
        while(toInvestigate.size() > 0)
        {
            // Create a variable to represent the board being investigated
            // Remove the first board from the list to be investigated
            Board current = toInvestigate.remove(0);

            // Label the squares that the player can access
            setPlayerSquares(current);

            // Discard any boards with a solution in a state with fewer than the minimum number of moves
            if(getSolution(current) && (current.getMovesMade() < movesToSolution)) 
            {
                System.out.println("Board rejected. Required moves to solution: " + movesToSolution +
                        ", actual moves to solution: " + current.getMovesMade() + ".");
                isEarlySolution = true;
                break;
            }

            // If a solution with at least the minimum number of moves is found
            else if(getSolution(current))
            {
                isValid = true;
                break;
            }

            // Generate boards that result from each available move in the current board
            for(int squareToCheck = 0 ; squareToCheck < current.getSquaresLength() ; squareToCheck++)
            {
                // Access the square
                Square currentSquare = current.getSquare(squareToCheck);

                // If the square is useable
                // A useable square is accessible by the player and has not been used previously
                if(currentSquare.getUp() && currentSquare.getPlayerHere())
                {
                    // If the pillar is two crates high
                    if(currentSquare.getHeight() == PILLAR_HEIGHT_TWO)
                    {
                        // If there are two empty squares above
                        if(squareToCheck >= 12)
                            if(!(current.getSquare(squareToCheck - 6).getOccupied()) && !(current.getSquare(squareToCheck - 12).getOccupied()))
                                createGameState(current, squareToCheck, PILLAR_HEIGHT_TWO, toInvestigate, UP);

                        // If there are two empty squares to the right
                        if(!((((squareToCheck + 1) % 6) == 0) || (((squareToCheck + 2) % 6) == 0)))
                            if(!(current.getSquare(squareToCheck + 1).getOccupied()) && !(current.getSquare(squareToCheck + 2).getOccupied()))
                                createGameState(current, squareToCheck, PILLAR_HEIGHT_TWO, toInvestigate, RIGHT);

                        // If there are two empty squares below
                        if(squareToCheck < 24)
                            if(!(current.getSquare(squareToCheck + 6).getOccupied()) && !(current.getSquare(squareToCheck + 12).getOccupied()))
                                createGameState(current, squareToCheck, PILLAR_HEIGHT_TWO, toInvestigate, DOWN);

                        // If there are two empty squares to the left
                        if(!(((squareToCheck % 6) == 0) || (((squareToCheck - 1) % 6) == 0)))
                            if(!(current.getSquare(squareToCheck - 1).getOccupied()) && !(current.getSquare(squareToCheck - 2).getOccupied()))
                                createGameState(current, squareToCheck, PILLAR_HEIGHT_TWO, toInvestigate, LEFT);
                    }

                    // If the pillar is three crates high
                    else if(currentSquare.getHeight() == PILLAR_HEIGHT_THREE)
                    {
                        // If there are three empty squares above
                        if(squareToCheck >= 18)
                            if(!(current.getSquare(squareToCheck - 6).getOccupied()) &&
                                    !(current.getSquare(squareToCheck - 12).getOccupied()) && !(current.getSquare(squareToCheck - 18).getOccupied()))
                                createGameState(current, squareToCheck, PILLAR_HEIGHT_THREE, toInvestigate, UP);

                        // If there are three empty squares to the right
                        if(((squareToCheck % 6) == 0) || (((squareToCheck - 1) % 6) == 0) || (((squareToCheck - 2) % 6) == 0))
                            if(!(current.getSquare(squareToCheck + 1).getOccupied()) &&
                                    !(current.getSquare(squareToCheck + 2).getOccupied()) && !(current.getSquare(squareToCheck + 3).getOccupied()))
                                createGameState(current, squareToCheck, PILLAR_HEIGHT_THREE, toInvestigate, RIGHT);

                        // If there are three empty squares below
                        if(squareToCheck < 18)
                            if(!(current.getSquare(squareToCheck + 6).getOccupied()) &&
                                    !(current.getSquare(squareToCheck + 12).getOccupied()) && !(current.getSquare(squareToCheck + 18).getOccupied()))
                                createGameState(current, squareToCheck, PILLAR_HEIGHT_THREE, toInvestigate, DOWN);

                        // If there are three empty squares to the left
                        if((((squareToCheck + 1) % 6) == 0) || (((squareToCheck + 2) % 6) == 0) || (((squareToCheck + 3) % 6) == 0))
                            if(!(current.getSquare(squareToCheck - 1).getOccupied()) &&
                                    !(current.getSquare(squareToCheck - 2).getOccupied()) && !(current.getSquare(squareToCheck - 3).getOccupied()))
                                createGameState(current, squareToCheck, PILLAR_HEIGHT_THREE, toInvestigate, LEFT);
                    }

                    // If the pillar is four crates high
                    else if(currentSquare.getHeight() == PILLAR_HEIGHT_FOUR)
                    {
                        // If there are four empty squares above
                        if(squareToCheck >= 24)
                            if(!(current.getSquare(squareToCheck - 6).getOccupied()) && !(current.getSquare(squareToCheck - 12).getOccupied()) &&
                                    !(current.getSquare(squareToCheck - 18).getOccupied()) && !(current.getSquare(squareToCheck - 24).getOccupied()))
                                createGameState(current, squareToCheck, PILLAR_HEIGHT_FOUR, toInvestigate, UP);

                        // If there are four empty squares to the right
                        if(((squareToCheck % 6) == 0) || (((squareToCheck - 1) % 6) == 0))
                            if(!(current.getSquare(squareToCheck + 1).getOccupied()) && !(current.getSquare(squareToCheck + 2).getOccupied()) &&
                                    !(current.getSquare(squareToCheck + 3).getOccupied()) && !(current.getSquare(squareToCheck + 4).getOccupied()))
                                createGameState(current, squareToCheck, PILLAR_HEIGHT_FOUR, toInvestigate, RIGHT);

                        // If there are four empty squares below
                        if(squareToCheck < 12)
                            if(!(current.getSquare(squareToCheck + 6).getOccupied()) && !(current.getSquare(squareToCheck + 12).getOccupied()) &&
                                    !(current.getSquare(squareToCheck + 18).getOccupied()) && !(current.getSquare(squareToCheck + 24).getOccupied()))
                                createGameState(current, squareToCheck, PILLAR_HEIGHT_FOUR, toInvestigate, DOWN);

                        // If there are four empty squares to the left
                        if((((squareToCheck + 1) % 6) == 0) || (((squareToCheck + 2) % 6) == 0))
                            if(!(current.getSquare(squareToCheck - 1).getOccupied()) && !(current.getSquare(squareToCheck - 2).getOccupied()) &&
                                    !(current.getSquare(squareToCheck - 3).getOccupied()) && !(current.getSquare(squareToCheck - 4).getOccupied()))
                                createGameState(current, squareToCheck, PILLAR_HEIGHT_FOUR, toInvestigate, LEFT);
                    }
                }
            }
        }
        
        // Inform the user that the board does not have a solution
        if(!isValid && !isEarlySolution)
            System.out.println("Board rejected. No solution.");

        return isValid;
    } // getIsValid()

    // Used to create a game state based on another game state and add it to a list
    private static void createGameState(Board boardToCopy, int squareID, int pillarHeight, LinkedList<Board> linkedList, int tipDirection)
    {
        // Create a copy of the board
        Board newBoard = new Board(boardToCopy);

        // Make the starting square empty
        newBoard.getSquare(squareID).setAttributes
                (PILLAR_HEIGHT_ZERO, false, false);

        // Tip the crate
        switch(tipDirection)
        {
            case UP: // Tip up
            	
                // Populate the squares above
                for(int tipSquare = 1 ; tipSquare <= pillarHeight ; tipSquare++)
                    newBoard.getSquare(squareID - (tipSquare * 6)).setAttributes
                            (pillarHeight, true, false);

                newBoard.setPlayerSquare(squareID - (6 * pillarHeight));
                break;

            case RIGHT: // Tip right
            	
                // Populate the squares to the right
                for(int tipSquare = 1 ; tipSquare <= pillarHeight ; tipSquare++)
                    newBoard.getSquare(squareID + tipSquare).setAttributes
                            (pillarHeight, true, false);

                // Set the player's position to be the end of the tipped pillar
                newBoard.setPlayerSquare(squareID + pillarHeight);
                break;

            case DOWN: // Tip down
            	
                // Populate the squares below
                for(int tipSquare = 1 ; tipSquare <= pillarHeight ; tipSquare++)
                    newBoard.getSquare(squareID + (tipSquare * 6)).setAttributes
                            (pillarHeight, true, false);

                // Set the player's position to be the end of the tipped pillar
                newBoard.setPlayerSquare(squareID + (6 * pillarHeight));
                break;

            case LEFT: // Tip left
            	
                // Populate the squares to the left
                for(int tipSquare = 1 ; tipSquare <= pillarHeight ; tipSquare++)
                    newBoard.getSquare(squareID - tipSquare).setAttributes
                            (pillarHeight, true, false);
                
                // Set the player's position to be the end of the tipped pillar
                newBoard.setPlayerSquare(squareID - pillarHeight);
                break;
        }

        // Increase the number of moves made
        newBoard.changeMovesMade(1);

        // Add the new board to the end of the list of boards to investigate
        linkedList.addLast(newBoard);
    } // createGameState()

    // Label the squares that can be reached by the player
    private static void setPlayerSquares(Board boardToCheck)
    {
        // Initially assume that no squares contain the player
        for(int square = 0 ; square < boardToCheck.getSquaresLength() ; square++)
        {
            // Access the square
            Square boardSquare = boardToCheck.getSquare(square);

            // If player could previously access the square
            if(boardSquare.getPlayerHere())
                boardSquare.setPlayerHere(false); // Tell the square that the player can't access it
        }

        // Create a list of unchecked squares and add the square where the player is
        LinkedList<Square> uncheckedSquares = new LinkedList<>();
        uncheckedSquares.addLast(boardToCheck.getSquare(boardToCheck.getPlayerSquare()));

        // Create a list of checked squares
        LinkedList<Square> checkedSquares = new LinkedList<>();

        // While the list of unchecked squares is not empty
        while(uncheckedSquares.size() > 0)
        {
            // Get the head of the list of unchecked squares
            Square checkNext = uncheckedSquares.remove(0);

            // Get the ID of the square being checked
            int checkNextID = checkNext.getID();

            // If the square is not in the top row of the board
            if(checkNextID >= 6)
            {
                // Access the square above
                Square squareReference = boardToCheck.getSquare(checkNextID - 6);

                // Add the square to the unchecked list if it contains a crate and hasn't been checked
                addPlayerSquareToList(squareReference, uncheckedSquares);
            }

            // If the square is not in the right column of the board
            if(((checkNextID + 1) % 6) != 0)
            {
                // Access the square to the right
                Square squareReference = boardToCheck.getSquare(checkNextID + 1);

                // Add the square to the unchecked list if it contains a crate and hasn't been checked
                addPlayerSquareToList(squareReference, uncheckedSquares);
            }

            // If the square is not in the bottom row of the board
            if(!(checkNextID >= 30))
            {
                // Access the square below
                Square squareReference = boardToCheck.getSquare(checkNextID + 6);

                // Add the square to the unchecked list if it contains a crate and hasn't been checked
                addPlayerSquareToList(squareReference, uncheckedSquares);
            }

            // If the square is not in the left column of the board
            if((checkNextID % 6) != 0)
            {
                // Access the square to the left
                Square squareReference = boardToCheck.getSquare(checkNextID - 1);

                // Add the square to the unchecked list if it contains a crate and hasn't been checked
                addPlayerSquareToList(squareReference, uncheckedSquares);
            }

            // Tell the square that it has been checked
            checkNext.setChecked(true);

            // Add the square to the list of checked squares
            checkedSquares.addLast(checkNext);
        }

        // Define that the player can access the squares and change their colour
        for(Square square : checkedSquares)
        {
            // Tell the square that the player can access it
            square.setPlayerHere(true);

            // Tell the square that it hasn't been checked
            square.setChecked(false);
        }
    } // setPlayerSquares()

    // Used to add a square that a player can reach to a list
    private static void addPlayerSquareToList(Square square, LinkedList<Square> linkedList)
    {
        // If the square is occupied and hasn't been checked already
        if (square.getOccupied() && !(square.getChecked()))
        {
            // Add the given square to the end of the given list
            linkedList.addLast(square);
        }
    }

    // Determine whether the board is in a solution state
    // A player can access the goal crate in the solution state
    private static Boolean getSolution(Board boardToCheck)
    {
    	return boardToCheck.getSquare(boardToCheck.getGoalSquare()).getPlayerHere();
    }
}