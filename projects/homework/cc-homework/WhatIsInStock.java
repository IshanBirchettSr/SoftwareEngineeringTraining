import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.NoSuchElementException;


public class WhatIsInStock {
 
  public static void main(String[] args)  {

    GroceryProgram gp = new GroceryProgram();
  
       gp.inStock();
    
  String inputFileName =("Docs/CannedGoodsInventory.txt");  
 try {
  File file = new File(inputFileName);
  
  Scanner scan = new Scanner(file);
 
     
  Path path = Paths.get("Docs/newCannedGoodsInventory.txt");

    //BufferedWriter writer = new BufferedWriter(path);
    
  
   while (scan.hasNextLine()) {
    String line = scan.nextLine();
    System.out.println(line);
  
    
  }
  }catch (InvalidPathException e) {
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
  