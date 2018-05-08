package levelGenerator;

/*
 * File: Pillar.java
 * Author: Richard Kneale
 * Student ID: 200790336
 * Date created: 6th July 2017
 * Description: Contains the information relating to a pillar of crates
 */

public class Pillar
{
    // The height of a pillar of crates
	private int height;

    // Used to construct a pillar with given height
    public Pillar(int pillarHeight)
    {
        height = pillarHeight;
    }

    // Returns the height of a pillar
    public int getHeight()
    {
        return height;
    }
}