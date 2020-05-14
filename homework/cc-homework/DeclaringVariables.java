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
  
  public void setTown(byte inValue) {
   town = inValue;
  }
  public byte getTown() {
  return town;
  }
  public void setOuttings(short yankees, float essentia) {
    ballParks = yankees;
    costOfSmartWater = essentia;
    System.out.printf("In over %d Ball Parks the price of Essentia water is %f.\n", ballParks, costOfSmartWater);
    return;
  }
  
  public void neighbors(int hMH, long pop) {
   howManyHomes = hMH;
   population = pop;
   System.out.printf("In Chicago there are %d people in the population for every %d homes.\n",population, howManyHomes);
  }
  
}