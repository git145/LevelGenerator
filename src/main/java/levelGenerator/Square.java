package levelGenerator;

/*
 * File: Square.java
 * Author: Richard Kneale
 * Student ID: 200790336
 * Date created: 6th July 2017
 * Description: Contain the information relating to a square on the board
 */

public class Square
{
    // Defines whether the square is empty or not
    private Boolean isOccupied = false;

    // A square that is up can be tipped over if all conditions are met
    // A square that is not up (down) can be stood up if all conditions are met
    private Boolean isUp = false;

    // Is this square in a part of the board that the player can access
    private Boolean isPlayerHere = false;

    // Used to determine if the square has been checked in search algorithms
    private Boolean isChecked = false;

    // Defines the position of the square on the board
    private int iD = -1;

    // The height of a pillar of crates in this square
    private int height = 0;
    private String heightString = "";

    // Used to construct a square with given ID
    public Square(int squareID)
    {
        iD = squareID;
    }

    // Used to construct a square given an ID, height, whether it is occupied and whether it is up
    public Square(int squareID, int squareHeight, Boolean squareOccupied, Boolean squareUp)
    {
        iD = squareID;
        setHeight(squareHeight);
        isOccupied = squareOccupied;
        isUp = squareUp;
    }

    // Return an integer representation of the ID of the square
    public int getID()
    {
        return iD;
    }

    // Return a string representation of the ID of the square
    public String toString()
    {
        return String.valueOf(iD);
    }

    // Set the height of a square and whether it is occupied
    public void setAttributes(int pillarHeight, boolean occupied)
    {
        setHeight(pillarHeight);
        isOccupied = occupied;
    }

    // Set the height of a square, whether it is occupied and whether it is up
    public void setAttributes(int pillarHeight, boolean occupied, boolean up)
    {
        setHeight(pillarHeight);
        isOccupied = occupied;
        isUp = up;
    }

    // Set the height of a square, whether it is occupied, whether it is up and whether the player can access it
    public void setAttributes(int pillarHeight, boolean occupied, boolean up, boolean playerHere)
    {
        setHeight(pillarHeight);
        isOccupied = occupied;
        isUp = up;
        isPlayerHere = playerHere;
    }

    // Returns whether the player can access the square
    public Boolean getPlayerHere()
    {
        return isPlayerHere;
    }

    // Tell the square that the player can access it
    public void setPlayerHere(Boolean playerHere)
    {
        isPlayerHere = playerHere;
    }

    // Returns whether the square has been used in a traversal algorithm
    public Boolean getChecked() 
    {
        return isChecked;
    }

    // Define whether the square has been used in a traversal algorithm
    public void setChecked(Boolean checked) 
    {
        isChecked = checked;
    }

    // Returns whether the square is occupied (not empty) or not occupied (empty)
    public Boolean getOccupied() 
    {
        return isOccupied;
    }

    // Returns the height of the square
    public int getHeight()
    {
        return height;
    }

    // Returns a string representation of the height of the square
    public String getHeightString() 
    {
        return heightString;
    }

    // Sets the integer and string representation of the height
    public void setHeight(int pillarHeight)
    {
        height = pillarHeight;

        if(height > 0)
        {
            heightString = String.valueOf(height);
        }
        else
        {
            heightString = "";
        }
    }

    // Returns whether the pillar of crates is up
    public Boolean getUp() 
    {
        return isUp;
    }

    // Define whether the pillar of crates is up
    public void setUp(Boolean up) 
    {
        isUp = up;
    }
}