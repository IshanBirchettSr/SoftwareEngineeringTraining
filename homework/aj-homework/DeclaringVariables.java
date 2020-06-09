public class DeclaringVariables {
  /* the commuity lived in is E = Englewood.
   * private so no one can change or see the numbers.
   */
  private static byte town = 'E';
  private int howManyHomes = 25000;
  private short ballParkLights = 1300;
  private float costOfSmartWater = 5.00f;
  private double airPolutionIndex = 10.96739673d;
  private long population = 115000000;
  private char firstInitial = 'I';
  private boolean polution = false;
  private String fullName = "Allma Johnson";
  
  public static void setTown(byte inValue) {
    town = inValue;
  }
  public byte getTown() {
    return town;
  }
  public void setOuttings(short ladodgers, float arrowhead) {
    ballParkLights = ladodgers;
    costOfSmartWater = arrowhead;
    System.out.printf("In over %d Ball Parks the price of Arrowhead water is $%1.2f.\n", ballParkLights, costOfSmartWater);
    return;
  }
  public void neighbors(int hMH, long pop) {
    howManyHomes = hMH;
    population = pop;
    System.out.printf("In Engleood there are %d people in the population for every %d homes.\n", population, howManyHomes);
    return;
  }
  public void isBallParkLightsOn(boolean inBallParkLightsOn) {
    if( inBallParkLightsOn == true) {
      System.out.printf("%b BallParkLights are on.\n", inBallParkLightsOn);
    } else {
      System.out.printf("%b BallParkLights are off.\n", inBallParkLightsOn);
    }
    if(inBallParkLightsOn == false) {
      System.out.printf("%b BallParkLights are off.\n", inBallParkLightsOn);
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
      System.out.printf("Thank you, can you taste the difference in Alkaline water? No change: %2.2f, Neither can I!\n", change);
    }
    return;
  }
  public void setFullName(String inFullName) {
    fullName = inFullName;
  }
  public String getFullName() {
    return fullName;
  }    
}
