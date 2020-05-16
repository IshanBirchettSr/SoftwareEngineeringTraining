public class MyShoppingExperience {
  // data memembers which are call
  // varaiables (retain something)
  private String storeName;
  private String cFirstName;
  private String cLastName;
  private int cTjMaxxCardNumber; 
  
  /*
   * Methods which are also know functions (Behavior).
   * To set the value of the data member storeName.
   */
    public void setStoreName(String inStoreName) {
     storeName = inStoreName;
     }
    public String getStoreName() {
      return storeName;
    }
    public void setCustomerInfo(String inFName, String inLName, int inCardNumber) {
      
      cFirstName = inFName;
      cLastName = inLName;
      cTjMaxxCardNumber = inCardNumber;
    }
    public String getCFName() {
      return cFirstName;
    }
    public String getCLName() {
      return cLastName;
    }
    public int getCardNumber() {
      return cTjMaxxCardNumber;
    }
    
  }