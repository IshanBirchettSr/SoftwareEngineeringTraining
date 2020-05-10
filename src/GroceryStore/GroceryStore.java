package GroceryStore;

import java.util.*;

public class GroceryStore {
  
  public void currentTime() {
    Date dt = new Date();
    System.out.println("This is the current time: " + dt.toString());
  }
  public static void main(String[] agrs) {
    GroceryStore EatToLive = new GroceryStore();
    EatToLive.currentTime();
  }
}