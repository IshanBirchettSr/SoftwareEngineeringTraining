import java.lang.Thread;

public class DeclaringVariables {
  /*
   * Primative Java Types
   * byte, int, short, long, char, boolean, String, float, double
   * 
   */
  
  /*
   * town is the town live in.
   * B = Boston
   * D = Detroit
   * C = Cleaveland
   */
  private byte town = 'B';
  private int  howManyHomes = 32000;
  private short ballParks = 1200;
  private float costOfSmartWater = 1.25f;
  private double airPolutionIndex = 12.123123156d;
  private long population = 128000000;
  private char firstInitial = 'I';
  private boolean married = false;
  private String fullName = "Ishan Birchett, Sr.";
  
  public void setTown(byte inValue) {
   town = inValue;
  }
  public byte getTown() {
    return town;
  }
  public void setOuttings(short yankees, float essentia) {
    ballParks = yankees;
    costOfSmartWater = essentia;
    System.out.printf("In over %d Ball Parks the price of Essentia water is $%1.2f.\n", ballParks, costOfSmartWater);
    return;
  }
  
  public void neighbors(int hMH, long pop) {
   howManyHomes = hMH;
   population = pop;
   System.out.printf("In Chicago there are %d people in the population for every %d homes.\n",population, howManyHomes);
   return;
 }
  
  /*
   * This method checks to see if the Street Lights are on.
   * param inStreetLightsOn - value of truefalse
   * return void
   */
  public void isStreetLightsOn(boolean inStreetLightsOn) { 
  
    // If <ture> Then <false> then Else
    if( inStreetLightsOn == true) {
      System.out.printf("%b Streetlights are on.\n", inStreetLightsOn);
    } else {
      System.out.printf("%b Streetlights are off.\n", inStreetLightsOn);
    }
    
    if(inStreetLightsOn == false) {
      System.out.printf("%b Streetlights are off.\n", inStreetLightsOn);
    }
    return;
  }
  public void isThereChange(float cost, float money) {
    
    float change = 0.0f;
    change = money - cost;
    
    if(change > 0) {
      System.out.printf("This is the change: %4.2f\n", change);
    } else if (change < 0) {
      System.out.printf("You still owe: %2.2f\n", change);
    } else {
      System.out.printf("Thank you for purchase. No change: %2.2f, Go home!\n", change);
    }
    return;
  }
  public void setFullName(String inFullName) {
    
    fullName = inFullName;
    
  }
  public String getFullName() {
    return fullName;
  }
  
  /**
   * This section will examine while & for loops.
   */
    
  public void testTheLoop(int inValue) {
    // While (something is true) do something.
    int i = inValue;
    
    while ( i < 5 ) {
      System.out.printf("The value of i is %d\n", i);
      i++;
    } 
  }
  public void familyMembers() {
    
  }
}