public class VarExercise {
  public static void main(String[] args) {
    byte aTown = 'C';
    /*
     * Create a DeclaringVariable instances
     * 
    */
    DeclaringVariables dv = new DeclaringVariables();
    dv.setTown(aTown);
    byte myTown = dv.getTown();
    System.out.printf("This is my town: %c\n", myTown);
    
      short ballParks = 1000;
      float costOfSmartWater = 3.75f;
      dv.setOuttings(ballParks, costOfSmartWater);
      int howManyHomes = 20000;
      long population = 34000;
      dv.neighbors(howManyHomes, population);
      boolean lightsOn = true;
      dv.isStreetLightsOn(lightsOn);
      lightsOn = false;
      dv.isStreetLightsOn(lightsOn);
      dv.isThereChange(5.45f, 5.45f);
  }   
}
    