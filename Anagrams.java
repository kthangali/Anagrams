import java.io.*;
import java.util.*;
import java.math.*;
import javax.sound.sampled.*;


public class Anagrams
{
    /**
     * list to hold the random generated 6 letters
     */
    private ArrayList<String> randomLetters;

    /**
     * creates a FiveLetterWords object
     */
    private FiveLetterWords fiveLetterWords;

    /**
     * creates a FourLetterWords object
     */
    private FourLetterWords fourLetterWords;

    /**
     * creates a ThreeLetterWords object
     */

    private ThreeLetterWords threeLetterWords;

    /**
     * creates a SixLetterWords object
     */
    private SixLetterWords sixLetterWords;

    /**
     * HashSet to check if the user has entered a word more than once
     */
    private HashSet<String> enteredWords;

    /**
     * creates the user
     */
    private Player player1;


    /**
     * Constructor to initialize lists and generate random letters.
     * 
     * @param player1
     *            Current player
     * @throws FileNotFoundException
     *             in case file is not found
     * @throws IOException
     *             General input/output error
     */
    public Anagrams( Player player1 ) throws FileNotFoundException, IOException
    {
        this.player1 = player1;

        sixLetterWords = new SixLetterWords();
        sixLetterWords.addLetters( new File( "SixLetterWords" ) );

        fiveLetterWords = new FiveLetterWords();
        fiveLetterWords.addLetters( new File( "FiveLetterWords" ) );
        fourLetterWords = new FourLetterWords();
        fourLetterWords.addLetters( new File( "FourLetterWords" ) );
        threeLetterWords = new ThreeLetterWords();
        threeLetterWords.addLetters( new File( "ThreeLetterWords" ) );
        randomLetters = generateRandomLetters();
        enteredWords = new HashSet<String>();
    }


    /**
     * 
     * method to generate random letters
     * 
     * @return arrayList with randomly generated letters
     */
    public static ArrayList<String> generateRandomLetters()
    {
        String vowels = "AAAEEIIIOU";
        String consonants = "BBBCDDFGHLMNNPRSSSSSSTTT";

        ArrayList<String> randomVowelLetters = new ArrayList<>();
        while ( randomVowelLetters.size() < 2 )
        {
            int randomIndex = (int)( Math.random() * vowels.length() );
            String v = vowels.substring( randomIndex, randomIndex + 1 );
            if ( v.equals( "I" ) && randomVowelLetters.contains( "I" )
                || v.equals( "U" ) && randomVowelLetters.contains( "U" ) )
            {
                continue;
            }
            randomVowelLetters.add( v );

        }
        HashSet<String> randomConsonantLetters = new HashSet<>();
        while ( randomConsonantLetters.size() < 4 )
        {
            int randomIndex = (int)( Math.random() * consonants.length() );
            String c = consonants.substring( randomIndex, randomIndex + 1 );
            randomConsonantLetters.add( c );

        }
        ArrayList<String> allLetters = new ArrayList<>();
        for ( String s : randomVowelLetters )
        {
            allLetters.add( s );
        }
        for ( String s : randomConsonantLetters )
        {
            allLetters.add( s );
        }
        return allLetters;
    }


    /**
     * 
     * method to handle processing entered word. This method keeps track of
     * already entered words and also checks to make sure the entered word is
     * valid.
     * 
     * @param word
     *            word to be checked
     * @return message regarding the status of the word
     * @throws FileNotFoundException
     *             in case file is not found
     * @throws IOException
     *             General input/output error
     * @throws LineUnavailableException
     */
    public String processInput( String word )
        throws FileNotFoundException,
        IOException,
        LineUnavailableException
    {

        String out = "";
        ArrayList<String> copy = new ArrayList<String>();
        word = word.toUpperCase();
        for ( int j = 0; j < randomLetters.size(); j++ )
        {
            copy.add( randomLetters.get( j ) );
        }
        // int index = 0;

        int len = word.length();

        if ( len < 3 )
        {
            return "Word not long enough";
        }
        else if ( len == 3 )
        {

            if ( threeLetterWords.containsWord( word ) )
            {

                int i = 0;
                for ( i = 0; i < len; i++ )
                {
                    if ( copy.contains( word.charAt( i ) + "" ) )
                    {
                        copy.remove( word.charAt( i ) + "" );
                        // System.out.println( copy );
                    }

                }

                if ( copy.size() == randomLetters.size() - len )
                {
                    if ( enteredWords.add( word ) == true )
                    {
                        player1.incrementPoints( 300 );
                        out = "Valid Word +300";
                        playDingMusic();

                    }
                    else if ( enteredWords.add( word ) == false )
                    {
                        out = "Error - already entered -20";
                    }
                }
                else
                {
                    player1.decrementPoints( 20 );
                    return "Invalid Letters -20";
                }
            }
            else
            {
                player1.decrementPoints( 20 );
                return "Not a valid word -20";
            }

        }

        else if ( len == 4 )
        {
            if ( fourLetterWords.containsWord( word ) )
            {

                int i = 0;
                for ( i = 0; i < len; i++ )
                {
                    if ( copy.contains( word.charAt( i ) + "" ) )
                    {
                        copy.remove( word.charAt( i ) + "" );
                    }

                }
                if ( copy.size() == randomLetters.size() - len )
                {
                    if ( enteredWords.add( word ) == false )
                    {
                        out = "Error - already entered -20";
                    }
                    else
                    {
                        player1.incrementPoints( 400 );
                        out = "Valid Word +400";
                        playDingMusic();

                    }

                }
                else
                {
                    player1.decrementPoints( 20 );
                    out = "Invalid Letters -20";
                }
            }
            else
            {
                player1.decrementPoints( 20 );
                out = "Not a valid word -20";
            }

        }
        else if ( len == 5 )
        {
            if ( fiveLetterWords.containsWord( word ) )
            {

                int i = 0;
                for ( i = 0; i < len; i++ )
                {
                    if ( copy.contains( word.charAt( i ) + "" ) )
                    {
                        copy.remove( word.charAt( i ) + "" );
                    }

                }
                if ( copy.size() == randomLetters.size() - len )
                {
                    if ( enteredWords.add( word ) == false )
                    {
                        player1.decrementPoints( 20 );
                        out = "Error - already entered -20";
                    }
                    else
                    {
                        player1.incrementPoints( 500 );
                        out = "Valid Word +500";
                        playDingMusic();

                    }
                }
                else
                {
                    player1.decrementPoints( 20 );
                    out = "Invalid Letters -20";
                }
            }
            else
            {
                player1.decrementPoints( 20 );
                out = "Not a valid word -20";
            }

        }
        else if ( len == 6 )
        {
            if ( sixLetterWords.containsWord( word ) )
            {

                int i = 0;
                for ( i = 0; i < len; i++ )
                {
                    if ( copy.contains( word.charAt( i ) + "" ) )
                    {
                        copy.remove( word.charAt( i ) + "" );
                    }

                }
                if ( copy.size() == randomLetters.size() - len )
                {

                    if ( enteredWords.add( word ) == false )
                    {
                        player1.decrementPoints( 20 );
                        out = "Error - already entered -20";
                    }
                    else
                    {
                        player1.incrementPoints( 600 );

                        out = "Valid Word +600";
                        playDingMusic();

                    }
                }
                else
                {
                    player1.decrementPoints( 20 );
                    out = "Invalid Letters -20";
                }
            }
        }

        else if ( len > 6 )
        {
            out = "WORD TOO LONG";
        }

        return out;
    }


    /**
     * Plays the "ding" sound when the user enters a valid word
     * 
     * @throws IOException
     *             General input/output error
     * @throws LineUnavailableException
     *             error in case the music doesn't play
     */
    public static void playDingMusic() throws IOException, LineUnavailableException
    {

        AudioInputStream audioIn;
        try
        {
            audioIn = AudioSystem
                .getAudioInputStream( AnagramsGUI.class.getResource( "ding.wav" ) );
            Clip clip = AudioSystem.getClip();
            clip.open( audioIn );
            clip.start();

        }
        catch ( UnsupportedAudioFileException e )
        {
            e.printStackTrace();
        }
    }


    /**
     * 
     * Testing method to get FiveLetterWords object
     * 
     * @return FiveLetterWords object
     */
    public FiveLetterWords getFiveLetterWords()
    {
        return fiveLetterWords;
    }


    /**
     * 
     * Testing method to get FourLetterWords Object
     * 
     * @return FourLetterWords object
     */
    public FourLetterWords getFourLetterWords()
    {
        return fourLetterWords;
    }


    /**
     * 
     * Testing method to get ThreeLetterWords Object
     * 
     * @return ThreeLetterWords object
     */
    public ThreeLetterWords getThreeLetterWords()
    {
        return threeLetterWords;
    }


    /**
     * 
     * Testing method to get randomLetter list
     * 
     * @return random letter list
     */
    public ArrayList<String> getLetters()
    {
        return randomLetters;
    }


    /**
     * 
     * Testing method to return enteredWords
     * 
     * @return enteredWords list
     */
    public HashSet<String> getEntered()
    {
        return enteredWords;
    }


    /**
     * 
     * Testing method to return player
     * 
     * @return Player object
     */
    public Player getPlayer()
    {
        return player1;
    }


    /**
     * 
     * Testing method to generate custom letter list
     * 
     * @param letters
     *            letters to be placed into randomLetters
     */
    public void generateCustom( ArrayList<String> letters )

    {
        randomLetters = letters;
    }

}