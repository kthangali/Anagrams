import java.util.*;
import java.io.*;
import java.math.*;


/**
 * 
 * This is a class to create list of four-letter words. This class scans through
 * the 4 letter word text file and puts it in a HashSet.
 *
 * @author kruthithangali, lauryntuo, bellachen
 * @version May 25, 2019
 * @author Period: 3
 * @author Assignment: Anagrams
 *
 * @author Sources: NA
 */
public class FourLetterWords
{
    /**
     * field for fourLetterWords
     */

    private HashSet<String> fourLetterWordsHS;

    /**
     * constructor to initialize fourLetterWords HashSet
     */
    public FourLetterWords()
    {
        fourLetterWordsHS = new HashSet<>();

    }

    /**
     * 
     * method to add words into hashSet
     * @param fileName text file to be scanned
     * @throws FileNotFoundException in case text file is not found
     * @throws IOException General input/output error
     */
    public void addLetters( File fileName ) throws FileNotFoundException, IOException
    {
        Scanner sc = new Scanner( fileName );
        while ( sc.hasNext() )
        {
            String str = sc.next();
            str = str.toUpperCase();
            fourLetterWordsHS.add( str );
        }
    }

    /**
     * 
     * method to check if a given word is in the list of words
     * @param word word to be checked
     * @return whether or not the word exists in the list
     */
    public boolean containsWord( String word )
    {
        return fourLetterWordsHS.contains( word );
    }
    //FOR TESTING PURPOSES
    /**
     * 
     * Testing method to get FourLetterWords set
     * @return FourLetterWordsHS
     */
    public HashSet<String> getWords()
    {
        return fourLetterWordsHS;
    }

}
