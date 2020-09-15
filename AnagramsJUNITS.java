import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.*;

import org.junit.Test;


public class AnagramsJUNITS
{
    
    ThreeLetterWords words = new ThreeLetterWords();
    FourLetterWords fourWords = new FourLetterWords();
    FiveLetterWords fiveWords = new FiveLetterWords();
    SixLetterWords sixWords = new SixLetterWords();
    Player p = new Player("bob");
    Leaderboard b = new Leaderboard();
    @Test
    public void sixLetterWordsConstructor()
    {
        assertTrue(sixWords != null);
    }
    @Test
    public void sixAddLettersTest()
    {
        assertTrue(sixWords.getWords() != null);
    }
    @Test
    public void sixContainsWordTest() throws FileNotFoundException, IOException
    {
        sixWords.addLetters(new File("SixLetterWords"));
        String str = "JAZZED";
        String str2 = "ABCDEF";
        assertTrue(sixWords.containsWord( str ) && !sixWords.containsWord( str2 ));
    }
    @Test
    public void fiveLetterWordsConstructor()
    {
        assertTrue(fiveWords != null);
    }
    @Test
    public void fiveAddLettersTest()
    {
        assertTrue(fiveWords.getWords() != null);
    }
    @Test
    public void fiveContainsWordTest() throws FileNotFoundException, IOException
    {
        fiveWords.addLetters(new File("FiveLetterWords"));
        String str = "PARCH";
        String str2 = "ABCDE";
        assertTrue(fiveWords.containsWord(str) && !fiveWords.containsWord(str2));
    }
    @Test
    public void fourLetterWordsConstructor()
    {
        assertTrue(fourWords != null);
    }
    @Test
    public void addLettersTest()
    {
        assertTrue(fourWords.getWords() != null);

    }
    @Test
    public void containsWordTest() throws FileNotFoundException, IOException
    {
        fourWords.addLetters( new File( "FourLetterWords" ) );
        String str = "NOON";
        String str2 = "BEIL";
        assertTrue(fourWords.containsWord(str) && !fourWords.containsWord(str2));
    }   
    @Test
    public void playerConstructor()
    {
        assertTrue(p != null);
    }
    @Test
    public void getNameTest()
    {
        assertEquals("bob", p.getName());
    }
    @Test
    public void getPointsTest()
    {
        assertEquals(0, p.getPoints());
    }
    @Test
    public void incrementPointsTest()
    {
        p.incrementPoints(400);
        assertTrue(p.getPoints() == 400);
    }
    
    @Test
    public void leaderBoardConstructor()
    {
        assertTrue(b != null);
    }
    @Test
    public void leaderBoardAddScore()
    {
        Player p = new Player("bob");
        p.incrementPoints( 200 );
        b.addScore( p );
        assertTrue(!b.getBoard().isEmpty());
    }
    @Test
    public void leaderBoardClearBoard()
    {
        b.clearBoard();
        assertTrue(b.getBoard().isEmpty());
    }
    @Test
    public void leaderBoardPrintBoard()
    {
        Leaderboard b = new Leaderboard();
        Player p = new Player("one");
        Player p2 = new Player("two");
        Player p3 = new Player("three");
        p.incrementPoints( 350 );
        p2.incrementPoints( 100 );
        p3.incrementPoints( 250 );
        b.addScore( p );
        b.addScore( p2 );
        b.addScore( p3 );
        assertEquals("\n" + "\tLeaderboard " + "\n" + "\tone: 350" + "\n" 
        + "\tthree: 250" + "\n" + "\ttwo: 100" + "\n", b.printBoard());
    }


    @Test
    public void ThreeLetterWordsConstructor()
    {
        assertTrue( words != null );
    }


    @Test
    public void ThreeLetterWordsAddLetters()
    {
        assertTrue( words.getWords() != null );

    }


    @Test
    public void ThreeLetterWordsContains() throws FileNotFoundException, IOException
    {
        String correct = "BAT";
        String incorrect = "XYZ";
        words.addLetters( new File( "ThreeLetterWords" ) );
        assertTrue( words.containsWord( correct ) && !words.containsWord( incorrect ) );
    }

    // ANAGRAMS JUNITS
    Player player = new Player( "name" );


    @Test
    public void anagramsConstructor() throws FileNotFoundException, IOException
    {
        Anagrams a = new Anagrams( player );
        assertTrue( a.getFiveLetterWords() != null && a.getFourLetterWords() != null
            && a.getThreeLetterWords() != null );
        assertTrue( a.getLetters() != null );
        assertTrue( a.getEntered() != null && a.getEntered().isEmpty() );

    }


    @Test
    public void generateLettersTest() throws FileNotFoundException, IOException
    {
        // Anagrams a = new Anagrams(player);
        assertTrue( Anagrams.generateRandomLetters() != null );

    }
    @Test
    public void getPlayerTest() throws FileNotFoundException, IOException
    {
        Anagrams a = new Anagrams( p );
        assertEquals(a.getPlayer(), p);
    }


    @Test
    public void processInputTest() throws FileNotFoundException, IOException, LineUnavailableException
    {
        Anagrams a = new Anagrams( p );
        ArrayList<String> letters = new ArrayList<String>();
        letters.add( "S" );
        letters.add( "H" );
        letters.add( "A" );
        letters.add( "L" );
        letters.add( "E" );
        letters.add( "S" );
        a.generateCustom( letters );
        assertEquals(a.processInput( "SA" ), "Word not long enough");
        assertEquals(a.processInput( "HAS" ), "Valid Word +300");
        assertEquals(a.processInput( "HAS" ), "Error - already entered -20");
        assertEquals(a.processInput( "HEAL" ), "Valid Word +400");
        assertEquals(a.processInput( "SHALE" ), "Valid Word +500");
        assertEquals(a.processInput( "SHALES" ), "Valid Word +600");
        assertEquals(a.processInput( "SHALES" ), "Error - already entered -20");
        assertEquals(a.processInput( "SHAME" ), "Invalid Letters -20");
        assertEquals(a.processInput( "HAD" ), "Invalid Letters -20");
        assertEquals(a.processInput( "SHACKS" ), "Invalid Letters -20");
        assertEquals(a.processInput( "LEND" ), "Invalid Letters -20");
        assertEquals(a.processInput( "HASE" ), "Not a valid word -20");
        assertEquals(a.processInput( "SHL" ), "Not a valid word -20");
        assertEquals(a.processInput( "LESAS" ), "Not a valid word -20");
        assertEquals(a.processInput( "HEAL" ), "Error - already entered -20");
        assertEquals(a.processInput( "SHALE" ), "Error - already entered -20");
        assertEquals(a.processInput( "SHALESS" ), "WORD TOO LONG");


    }

    // assertTrue(player.getPoints() == 1200);
    // a.processInput( "ABBBD" );
    // assertTrue(player.getPoints() == 1200);

}
