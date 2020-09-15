import java.io.*;
import java.util.*;
import java.math.*;


public class FiveLetterWords
{
    /**
     * Field containing 5 letter words
     */
    private HashSet<String> fiveLetterWordsHS;
    /**
     * Constructor to build FiveLetterWords object
     */
    public FiveLetterWords()
    {
        fiveLetterWordsHS = new HashSet<>();

    }
    /**
     * 
     * method to add words to fiveLetterWordsHS HashSet
     * @param fileName file to scan
     * @throws FileNotFoundException in case textfile is not found
     * @throws IOException General input/output error
     */
    public void addLetters(File fileName) throws FileNotFoundException, IOException
    {
        Scanner sc = new Scanner( fileName);
        while ( sc.hasNext() )
        {
            String str = sc.next();
            str = str.toUpperCase();
            fiveLetterWordsHS.add( str );
        }
    }
    /**
     * 
     * Method to check if a given word is in the list of 5 letter words.
     * @param word word to be checked
     * @return whether or not the word exists in the list.
     */
    public boolean containsWord( String word )
    {
        return fiveLetterWordsHS.contains( word );
    }
    //FOR TESTING PURPOSES
    /**
     * 
     * Testing method to get fiveLetterWords set
     * @return fiveLetterWordsHS
     */
    public HashSet<String> getWords()
    {
        return fiveLetterWordsHS;
    }

}

