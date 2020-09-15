import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.*;
import javax.swing.*;


public class AnagramsGUI extends JFrame implements ActionListener
{
    /**
     * creates a button
     */
    private JButton button;

    /**
     * creates a field for the user to type in the word
     */
    private JTextField wordEntryField;

    /**
     * the username the user enters into the game
     */
    private String userName;

    /**
     * label to display the user's score
     */
    private JLabel scoreLabel;

    /**
     * label to display if the word is invalid/valid
     */
    private JLabel statusLabel;

    /**
     * label to display the random 6 letters generated
     */
    private JLabel randomLettersLabel;

    /**
     * button to replay the game
     */
    private JButton redoButton;

    /**
     * creates Anagrams object
     */
    private Anagrams anagrams;

    /**
     * panel that holds multiple screens of the game
     */
    private JPanel childPanel;

    /**
     * panel for the game
     */
    private JPanel gamePanel;

    /**
     * panel where the user starts the game
     */
    private JPanel startButtonPanel;

    /**
     * creates a Leaderboard object
     */
    private Leaderboard board;

    /**
     * creates the opening screen
     */
    private JPanel titlePanel;

    /**
     * label to display the anagrams gif
     */

    private JLabel anagramsGIFLabel;

    /**
     * label to display the anagram title
     */
    private JLabel title;

    /**
     * allows us to add GIF into the GUI
     */
    private ImageIcon anagramsGIF;

    /**
     * JTextArea to display the leaderboard scores
     */
    private JTextArea leaderboard;


    /**
     * Constructor to initialize GUI Elements
     * 
     * @throws FontFormatException
     *             In case the font does not work
     * @throws IOException
     *             General input/output error
     */
    public AnagramsGUI() throws FontFormatException, IOException
    {

        board = new Leaderboard();
        JFrame.setDefaultLookAndFeelDecorated( true );
        JFrame frame = new JFrame( "Anagrams" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setExtendedState( JFrame.MAXIMIZED_BOTH );
        JPanel parentPanel = createPanel();
        BoxLayout boxLayout = new BoxLayout( parentPanel, BoxLayout.Y_AXIS );
        parentPanel.setLayout( boxLayout );
        titlePanel = createTitlePanel();
        childPanel = createPanel();
        gamePanel = createGamePanel();
        JPanel enterNamePanel = createEnterUserNamePanel( gamePanel );
        startButtonPanel = createStartButtonPanel( enterNamePanel );

        anagramsGIF = new ImageIcon( "anagramstitlefont.gif" );
        anagramsGIFLabel = new JLabel( anagramsGIF, SwingConstants.CENTER );
        anagramsGIFLabel.setSize( 10, 10 );
        titlePanel.add( anagramsGIFLabel );

        frame.setSize( 600, 700 );
        parentPanel.add( titlePanel );
        parentPanel.add( childPanel );
        childPanel.add( startButtonPanel );
        childPanel.add( enterNamePanel );
        childPanel.add( gamePanel );

        frame.add( parentPanel );
        frame.setVisible( true );

    }


    /**
     * 
     * general method to create panels
     * 
     * @return Panel created
     */
    private JPanel createPanel()
    {
        JPanel panel = new JPanel();
        panel.setBackground( new Color( 153, 153, 255 ) );
        return panel;
    }


    /**
     * 
     * method to create panel used in game screen
     * 
     * @return panel created
     * @throws FontFormatException
     *             in case font does not work
     * @throws IOException
     *             General input/output error
     */
    public JPanel createGamePanel() throws FontFormatException, IOException
    {
        Font font = Font.createFont( Font.TRUETYPE_FONT,
            getClass().getResourceAsStream( "Pintgram-Regular.ttf" ) );
        Font sized = font.deriveFont( Font.BOLD, 48f );
        title.setFont( sized );
        title.setForeground( Color.WHITE );
        title.setVisible( true );
        title.setAlignmentX( JPanel.CENTER_ALIGNMENT );
        titlePanel.add( title );
        scoreLabel = new JLabel();
        statusLabel = new JLabel();
        scoreLabel.setFont( new Font( "Serif", Font.BOLD, 20 ) );
        statusLabel.setFont( new Font( "Serif", Font.BOLD, 20 ) );
        statusLabel.setPreferredSize( new Dimension( 100, 100 ) );
        scoreLabel.setForeground( Color.WHITE );
        statusLabel.setForeground( Color.WHITE );

        leaderboard = new JTextArea();
        leaderboard.setSize( 600, 600 );
        leaderboard.setFont( new Font( "Serif", Font.BOLD, 15 ) );
        leaderboard.setForeground( Color.WHITE );
        leaderboard.setAlignmentX( JPanel.CENTER_ALIGNMENT );
        leaderboard.setBackground( new Color( 153, 153, 255 ) );
        randomLettersLabel = new JLabel();
        randomLettersLabel.setFont( new Font( "Serif", Font.BOLD, 30 ) );
        randomLettersLabel.setForeground( Color.WHITE );
        JPanel panel = createPanel();

        BoxLayout boxLayout = new BoxLayout( panel, BoxLayout.Y_AXIS );
        panel.setLayout( boxLayout );

        JTextField wordEntry = new JTextField( "Enter a word here:" );
        wordEntry.setAlignmentX( JPanel.CENTER_ALIGNMENT );
        wordEntry.setPreferredSize( new Dimension( 200, 100 ) );
        panel.add( wordEntry );
        wordEntry.addFocusListener( new FocusListener()
        {

            @Override
            public void focusGained( FocusEvent e )
            {
                wordEntry.setText( "" );

            }


            @Override
            public void focusLost( FocusEvent e )
            {

            }
        } );
        wordEntry.addActionListener( new ActionListener()
        {

            @Override
            public void actionPerformed( ActionEvent e )
            {
                if ( anagrams != null )
                {
                    try
                    {
                        statusLabel.setText( anagrams.processInput( wordEntry.getText() ) );
                        scoreLabel
                            .setText( "Your current score: " + anagrams.getPlayer().getPoints() );
                        wordEntry.setText( "" );
                    }
                    catch ( IOException | LineUnavailableException e1 )
                    {

                        e1.printStackTrace();
                    }
                }
            }

        } );

        randomLettersLabel.setAlignmentX( JPanel.CENTER_ALIGNMENT );
        randomLettersLabel.setText( "Your current score: " );
        randomLettersLabel.setPreferredSize( new Dimension( 200, 100 ) );
        panel.add( randomLettersLabel );

        scoreLabel.setAlignmentX( JPanel.CENTER_ALIGNMENT );
        scoreLabel.setText( "Your current score: " );
        panel.add( scoreLabel );

        statusLabel.setAlignmentX( JPanel.CENTER_ALIGNMENT );
        statusLabel.setText( "" );
        statusLabel.setPreferredSize( new Dimension( 300, 300 ) );

        leaderboard.setText( "leaderboard" );

        leaderboard.setVisible( true );
        leaderboard.setAlignmentX( JPanel.CENTER_ALIGNMENT );
        leaderboard.setPreferredSize( new Dimension( 200, 100 ) );
        panel.add( leaderboard );
        leaderboard.setVisible( false );
        panel.add( statusLabel );
        redoButton = new JButton( "Press to play again" );
        redoButton.setAlignmentX( JPanel.CENTER_ALIGNMENT );
        redoButton.setPreferredSize( new Dimension( 30, 30 ) );
        redoButton.setVisible( false );

        redoButton.addActionListener( new ActionListener()
        {

            @Override
            public void actionPerformed( ActionEvent e )
            {
                gamePanel.setVisible( false );
                startButtonPanel.setVisible( true );

            }

        } );
        panel.add( redoButton );
        panel.setVisible( false );
        return panel;

    }


    /**
     * 
     * method to create panel used in username screen
     * 
     * @param gamePanel
     *            current gamePanel
     * @return panel created
     */
    public JPanel createEnterUserNamePanel( JPanel gamePanel )
    {

        JPanel enterUserNamePanel = createPanel();
        enterUserNamePanel.setBackground( Color.WHITE );

        JTextField textField = new JTextField( "Please enter a user name:" );
        textField.setPreferredSize( new Dimension( 200, 100 ) );
        textField.setHorizontalAlignment( JTextField.CENTER );
        textField.addFocusListener( new FocusListener()
        {

            @Override
            public void focusGained( FocusEvent e )
            {
                textField.setText( "" );

            }


            @Override
            public void focusLost( FocusEvent e )
            {

            }

        } );
        textField.addActionListener( new ActionListener()
        {

            @Override
            public void actionPerformed( ActionEvent e )
            {
                userName = textField.getText();
                textField.setText( "Please enter a user name:" );
                enterUserNamePanel.setVisible( false );

                gamePanel.setVisible( true );
                try
                {
                    redoButton.setVisible( false );
                    leaderboard.setVisible( false );
                    startGame( userName );
                }
                catch ( Exception ex )
                {

                }

            }

        } );
        enterUserNamePanel.add( textField );
        enterUserNamePanel.setVisible( false );

        return enterUserNamePanel;

    }


    /**
     * 
     * method to start game once username is entered
     * 
     * @param username
     *            entered username
     * @throws FileNotFoundException
     *             exception in case file is not found
     * @throws IOException
     *             General input/output error
     * @throws LineUnavailableException
     */
    public void startGame( String username )
        throws FileNotFoundException,
        IOException,
        LineUnavailableException
    {

        if ( userName == null || userName.isEmpty() )
        {
            username = "user";
        }
        statusLabel.setText( "" );
        scoreLabel.setText( "" );
        Player p = new Player( username );

        anagrams = new Anagrams( p );
        randomLettersLabel.setText( anagrams.getLetters().toString() );

        long startTime = System.currentTimeMillis();
        Timer timer = new Timer();
        timer.schedule( new TimerTask()
        {
            public void run()
            {
                if ( ( System.currentTimeMillis() - startTime ) / 1000 > 30 )
                {
                    statusLabel.setText( "TIMES UP" );

                    scoreLabel.setText(
                        "Player " + p.getName() + " received " + p.getPoints() + " points" );

                    redoButton.setVisible( true );
                    board.addScore( p );
                    leaderboard.setText( board.printBoard() );

                    anagrams = null;
                    leaderboard.setVisible( true );
                    leaderboard.setEditable( false );
                    timer.cancel();
                }
            }
        }, 1000, 1000 );

    }


    /**
     * 
     * method to create panel used in titleScreen
     * 
     * @return panel created
     * @throws FontFormatException
     *             in case font does not work
     * @throws IOException
     *             General input/output error
     */
    public JPanel createTitlePanel() throws FontFormatException, IOException
    {

        JPanel titlePanel = createPanel();
        title = new JLabel();
        title.setHorizontalAlignment( JLabel.CENTER );
        title.setVisible( false );
        Font font = Font.createFont( Font.TRUETYPE_FONT,
            getClass().getResourceAsStream( "Pintgram-Regular.ttf" ) );
        Font sized = font.deriveFont( Font.BOLD, 48f );
        title.setFont( sized );
        title.setForeground( Color.WHITE );
        return titlePanel;
    }


    /**
     * 
     * Method to create startButton panel
     * 
     * @param enterNamePanel
     *            panel button should be placed on
     * @return panel with startButton
     */
    public JPanel createStartButtonPanel( JPanel enterNamePanel )
    {
        JPanel buttonPanel = createPanel();
        JButton button = new JButton();
        button.setHorizontalAlignment( JButton.CENTER );
        button.setText( "Click to start" );
        buttonPanel.add( button );
        button.addActionListener( new ActionListener()
        {

            @Override
            public void actionPerformed( ActionEvent e )
            {
                buttonPanel.setVisible( false );
                anagramsGIFLabel.setVisible( false );
                title.setText( "ANAGRAMS" );
                enterNamePanel.setVisible( true );

            }

        } );
        return buttonPanel;
    }


    /**
     * Determine what to do if the button is pressed
     * 
     * @param e
     *            the event that happens when the user presses the button
     */
    public void actionPerformed( ActionEvent e )
    {
        if ( e.getSource() == button )
        {
            wordEntryField.setVisible( true );
            button.setVisible( false );

        }

    }


    /**
     * 
     * Main method to run program
     * 
     * @param args
     * @throws FontFormatException
     *             in case font does not work
     * @throws IOException
     *             General input/output error
     * @throws LineUnavailableException
     *             in case music file does not work
     */
    public static void main( String[] args )
        throws FontFormatException,
        IOException,
        LineUnavailableException
    {
        AnagramsGUI ag = new AnagramsGUI();
        AudioInputStream audioIn;
        try
        {
            audioIn = AudioSystem
                .getAudioInputStream( AnagramsGUI.class.getResource( "kahoot.wav" ) );
            Clip clip = AudioSystem.getClip();
            clip.open( audioIn );
            clip.start();
            clip.loop( clip.LOOP_CONTINUOUSLY );
        }
        catch ( UnsupportedAudioFileException e )
        {
            e.printStackTrace();
        }

    }

}