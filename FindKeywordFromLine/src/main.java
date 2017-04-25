import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by bsnell on 4/25/2017.
 *
 * This is a simple program contained in one main that will reduce
 * sfc log files into something more readable.
 *
 * It takes the file then reads it in as individual lines of strings
 * Then it checks if they have the word "failed" in them
 * If it does it adds it to an array to check later
 * It then removed duplicates by comparing them.
 * It then prints it to a text file called "Errors.txt"
 *
 */
public class main {


    public static void main(String[] args){
        File textInput = new File("C:/Users/bsnell/Desktop/sfclog.txt"); //Location of log
        Scanner sc = null;
        try{
            sc = new Scanner(textInput);
        }catch (FileNotFoundException e){
            System.out.println("File does not exist");
        }

        ArrayList<String> lineArrayList = new ArrayList<>(); //Stores the lines
        ArrayList<String> errors = new ArrayList<>(); //Stores the errors
        int totalErrors = 0;
        int totalLines = 0;

        PrintWriter writer = null;
        try{
            writer = new PrintWriter("Errors.txt"); //Output file name
        }catch (FileNotFoundException e){
            System.out.println("Cannot write file");
        }

        while(sc.hasNextLine()){
            String currentLine = sc.nextLine();
            String tempString = "";
            for(int i=0; i < currentLine.length(); i++){ //Turns the line into an arrayList
                if(currentLine.charAt(i) != ' '){
                    tempString += currentLine.charAt(i); //Adds the current character to a tempString
                }

                else{ //If the character is a space add tempString to lineArrayList and clear tempString
                    lineArrayList.add(tempString);
                    tempString = "";
                }
            }

            if(lineArrayList.contains("Failed")){ //If the arraylist has failed in it then add it to the errors.
                errors.add(currentLine);
                totalErrors++;
            }

            lineArrayList.clear(); //Clear the arrayList
            totalLines++;
        }

        for(int i=0; i < errors.size(); i++){ //Checks if errors has any duplicates and removes the duplicate
            for(int j=i; j < errors.size(); j++){
                if(errors.get(i).equals(errors.get(j))){
                    errors.remove(j);
                }
            }
        }


        //These print out the number of lines, errors, and unique errors.
        writer.println( totalLines + " Lines");
        writer.println(totalErrors + " Errors");
        writer.println(errors.size() + " Unique Errors");
        writer.println("");

        //Prints the errors.
        for(int i=0; i < errors.size(); i++){
            writer.println(errors.get(i));
        }
    }
}
