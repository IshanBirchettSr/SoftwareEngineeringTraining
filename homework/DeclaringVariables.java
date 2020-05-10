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
    fullName = myName;
    town = inValue;
  }
  public byte getTown() {
    String myName = "Ishan";
    return town;
  }
  
}