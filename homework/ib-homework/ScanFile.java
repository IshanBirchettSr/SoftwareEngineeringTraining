import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class ScanFile {

public static void main(String[] args) {
    // Opening input file
    String filename = "../../Docs/US-States.txt";
    System.out.println("This is the file: " + filename);
    File file = new File(filename);
    
    //Opening Output file.
    Path path = Paths.get("../../Docs/US-States-NoBlanks.txt");

    try {
        BufferedWriter writer = Files.newBufferedWriter(path);
        Scanner sc = new Scanner(file); //.useDelimiter("\\s*-*\\n\\s*");
        while (sc.hasNext()) {
            String stateFirstName = sc.next();
            if(stateFirstName.length() == 0) {
               // Skip
               continue;
            }
 
            String stateLastName = sc.next();
            String stateAbrvName = sc.next();
            System.out.printf("First: %s\n Last: %s\n Abrv: %s\n", stateFirstName, stateLastName, stateAbrvName);
            String stateAndAbrv = stateFirstName + " | " + stateLastName +", " + stateAbrvName;
            System.out.println(stateAndAbrv);
            writer.write(stateAndAbrv, 0, stateAndAbrv.length());
            writer.newLine();
        }
        sc.close();
        writer.close();
    } 
    catch (InvalidPathException e) {
        e.printStackTrace();
    }
    catch(FileNotFoundException e) {
      e.printStackTrace();
    }
    catch(IOException e) {
      e.printStackTrace();
    }
    catch(NoSuchElementException e) {
      e.printStackTrace();
    }
    finally {
      //writer.close();
    }
 }
}