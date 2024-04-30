import java.util.Hashtable;
import java.util.Arrays;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

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
    private static MyHashTable<String, ArrayList> table = new MyHashTable<String, ArrayList>(); 
    

    /**
     * Constructor for objects of class NGram
     */
    public NGram()
    {
        // initialise instance variables
    }

    public static void loadFile(){
        try{
            File guessFile = new File("TestDoc.txt");
            Scanner scannerGuess = new Scanner(guessFile);
            StringBuilder line = new StringBuilder();
            while (scannerGuess.hasNextLine()){ 
                line = line.append(scannerGuess.nextLine() + " ");
                
            }
            String fileString = line.toString();
            String[] wordsFromFile = fileString.split("\s+");
            for(int i = 0; i < wordsFromFile.length; i++){
                if (i + 1 < wordsFromFile.length){
                    if (table.get(wordsFromFile[i]) == null){
                        ArrayList followingWords = new ArrayList();
                        followingWords.add(wordsFromFile[i+1]);
                        table.put(wordsFromFile[i], followingWords);
                    } else {
                        ArrayList followingWords = table.get(wordsFromFile[i]);
                        followingWords.add(wordsFromFile[i+1]);
                    }
                }
            }
            System.out.println(table);
        } catch (FileNotFoundException e){
            
        }
    }
}
