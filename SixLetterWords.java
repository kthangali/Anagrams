
import java.util.*;
import java.io.*;
import java.math.*;

/**
 * 
 * This is a class to represent six letter words.
 *  This class scans a text file of 6 letter words and puts them into a HashSet
 *  @author  kruthithangali, lauryntuo, bellachen
 *  @version May 25, 2019
 *  @author  Period: 3
 *  @author  Assignment: Anagrams
 *
 *  @author  Sources:
 */
public class SixLetterWords
{
    /**
     * field to represent sixLetterWord HashSet
     */
    private HashSet<String> sixLetterWordsHS;

    /**
     * constructor to initialize HashSet
     */
    public SixLetterWords()
    {
        sixLetterWordsHS = new HashSet<>();

    }

    /**
     * 
     * method to add words into hashset
     * @param fileName textFile to be scanned
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
            sixLetterWordsHS.add( str );
        }
    }

    /**
     * 
     * checks if a given word is in the list
     * @param word word to be checked
     * @return whether or not the word exists
     */
    public boolean containsWord( String word )
    {
        return sixLetterWordsHS.contains( word );
    }
    //FOR TESTING PURPOSES
    /**
     * 
     * Testing method to get SixLetterWords set
     * @return SixLetterWordsHS
     */
    public HashSet<String> getWords()
    {
        return sixLetterWordsHS;
    }

}
