import java.util.Scanner;
import java.util.HashMap;


public class Player
{
    /**
     * fields to hold player name, points
     */
    private String name;
    private int points;
    
    /**
     * Constructor for player
     * @param str name of player
     */
    public Player(String str)
    {
        
        name = str;
        points = 0;
    }
    /**
     * 
     * method to get name of player
     * @return name of player
     */
    public String getName()
    {
        return name;
    }
    /**
     * 
     * method to get points of player
     * @return number of points player has
     */
    public int getPoints()
    {
        return points;
    }
    /**
     * 
     * method to increment points of player
     * @param pointAdd number of points to add
     */
    public void incrementPoints( int pointAdd )
    {
        points += pointAdd;
    }
    /**
     * 
     * method to decrement points of player
     * @param pointSub number of points to subtract
     */
    public void decrementPoints(int pointSub)
    {
        points -= pointSub;
    }
    
   
    
    
}