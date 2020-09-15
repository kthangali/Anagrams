import java.util.*;
import java.io.*;
import java.math.*;


public class ThreeLetterWords
{
    /**
     * Field to store three letter word list
     */
    private HashSet<String> threeLetterWordsHS;

    /**
     * Constructor to build ThreeLetterWords object
     */
    public ThreeLetterWords()
    {
        threeLetterWordsHS = new HashSet<>();
    }
    
   

    /**
     * 
     * method to scan text file and add words to threeLetterWordsHS
     * @param fileName textFile to scan
     * @throws FileNotFoundException in case file is not found
     * @throws IOException General input/output error
     */
    public void addLetters( File fileName ) throws FileNotFoundException, IOException
    {
        Scanner sc = new Scanner( fileName );
        while ( sc.hasNext() )
        {
            String str = sc.next();
            str = str.toUpperCase();
            threeLetterWordsHS.add( str );
        }
    }

    /**
     * 
     * method to check if given word is in SixLetterWords set
     * @param word word to be checked
     * @return whether or not the word exists in the list
     */
    public boolean containsWord( String word )
    {
        return threeLetterWordsHS.contains( word );
    }
    //FOR TESTING PURPOSES
    /**
     * 
     * Testing method to get ThreeLetterWords set
     * @return threeLetterWordsHS
     */
    public HashSet<String> getWords()
    {
        return threeLetterWordsHS;
    }
    
    
}

