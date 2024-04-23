import java.util.Hashtable;
import java.util.Arrays;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Write a description of class NGram here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NGram
{
    // instance variables - replace the example below with your own
    private String[] text;
    private Hashtable table; 

    /**
     * Constructor for objects of class NGram
     */
    public NGram()
    {
        // initialise instance variables
    }

    private void loadFile(){
        try{
            File guessFile = new File("speeches.txt");
            Scanner scannerGuess = new Scanner(guessFile);
            StringBuilder line = new StringBuilder();
            while (scannerGuess.hasNextLine()){ 
                line = line.append(scannerGuess.nextLine());
                
            }
            
        } catch (FileNotFoundException e){
            
        }
    }
}
