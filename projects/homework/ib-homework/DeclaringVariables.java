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
  public int  howManyHomes = 32000;
  public short ballParks = 1200;
  public float costOfSmartWater = 1.25f;
  public double airPolutionIndex = 12.123123156d;
  private long population = 128000000;
  public char firstInitial = 'I';
  public boolean married = false;
  public String fullName = "Ishan Birchett, Sr.";
  
  public void setTown(byte inValue ) {
    town = inValue;
    return;
  }
  public byte getTown() {
    String myName = "Ishan";
    return town;
  }
  public void setBallParksAndHowManyHomes(short inBP, int inHMH) {
    ballParks = inBP;
    howManyHomes = inHMH;
    
    System.out.printf("We have %d of Ball Parks and %d Number of Homes.\n",
                      ballParks, howManyHomes); 
    return;
  }
  
}