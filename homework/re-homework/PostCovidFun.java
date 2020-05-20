public class PostCovidFun {
 /*
  * Primative Javatypes
  * string,int,short,boolean,byte,
  * 
  * town is the town I will visit
  * C = Cape
  */
  private String fullName = "Roxanne Earnest";
  private int howManyPlacesWillYouVisit = 5;
  private short beaches = 1;
  private boolean iWillTravel = true;
  private byte whatTownWillYouVisit = 'C';
  private String whoWillJoinYou = "Ishan Birchett, Sr.";
  
  
  /*
   * methods section
   */
  
  public void setTown(byte inValue) {
   town = inValue;
  }
  public byte getTown() {
    return town;
  }
  public void iWillTravel(boolean iWillTravel) {
  
    // If <ture> Then <false> then Else
  if(iWillTravel == true) {
     System.out.printf("%b Yes,iWillTravel.\n", iWillTravel);
    } else {
     System.out.printf("%b No,iWillNotTravel\n",iWillTravel );
    }
    return;
  }
}

  
  