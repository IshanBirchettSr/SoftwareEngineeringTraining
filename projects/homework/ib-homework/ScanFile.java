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
    String inputFilename = "../../Docs/DaysOfWeek.txt";
    System.out.println("This is the file: " + inputFilename);
    
    File file = new File(inputFilename);
    
    //Opening Output file.
    Path outputPath = Paths.get("../../Docs/newDaysOfWeek.txt");

    try {
        BufferedWriter writer = Files.newBufferedWriter(outputPath);
        Scanner sc = new Scanner(file).useDelimiter("\\s*,\\s*");
        String headerLine = sc.next();
        while (sc.hasNext()) {
            String firstDay = sc.next();
            if(firstDay.length() == 0) {
               // Skip
               System.out.println("Blank line encountered");
               continue;
            }

            String secondDay = sc.next();
            String thirdDay = sc.next();
            String fourthDay = sc.next();
            String fifthDay = sc.next();
            String sixDay = sc.next();
            String sevenDay = sc.next();
            
            System.out.printf(" First: %s\n Second: %s\n Third: %s\n Four: %s\n Five: %s\n Six: %s\n Seven: %s\n",
                             firstDay, secondDay, thirdDay, fourthDay, fifthDay, sixDay, sevenDay);   
            String theWeek = firstDay + " " + secondDay + " " + thirdDay + " " + fourthDay + " " +
              fifthDay + " " + sixDay + " " + sevenDay;
            writer.write(theWeek, 0, theWeek.length());
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