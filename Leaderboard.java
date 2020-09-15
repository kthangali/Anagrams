import java.util.Collections;
import java.util.TreeMap;


public class Leaderboard
{
    TreeMap<Integer, String> map;
    /**
     * constructs a leaderboard
     */
    public Leaderboard()
    {
        map = new TreeMap<Integer, String>(Collections.reverseOrder());
    }

    /**
     * 
     * adds the player and their score to the leaderboard
     * @param p player
     */
    public void addScore(Player p)
    {
        map.put( p.getPoints(), p.getName() );
    }
    
    /**
     * 
     * clears the leaderboard
     */
    public void clearBoard()
    {
        map.clear();
    }
    
    /**
     * 
     * returns the leaderboard
     * @return the leaderboard string
     */
    public String printBoard()
    { 
        String result = "\n" + "\tLeaderboard " + "\n";
        for (Integer points: map.keySet())
        {
            result +=  "\t" + map.get(points) + ": " + points + "\n";
        }
        return result;
    }
    /**
     * 
     * Testing method to get Leaderboard map
     * @return leaderboard map
     */
    public TreeMap<Integer, String> getBoard()
    {
        return map;
    }

}