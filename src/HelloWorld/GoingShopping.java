public class GoingShopping {
  static String favoriteStore = "TJ Maxx";
  
  public static void main(String[] args) {
    MyShoppingExperience roxanneShoppingExperience = new MyShoppingExperience();
    
    roxanneShoppingExperience.setStoreName(favoriteStore);
    
    String newStoreName = roxanneShoppingExperience.getStoreName();
    
    System.out.printf("This is the new storename %s.\n", newStoreName);
    
    roxanneShoppingExperience.setCustomerInfo("Sabrina", "Varteresan", 142398);
    
    String cFName = roxanneShoppingExperience.getCFName();
    System.out.printf("Customer First Name: %s\n", cFName);
    
    String cLName = roxanneShoppingExperience.getCLName();
    System.out.printf("Customer Last Name: %s\n", cLName);
    
    int cardNumber = roxanneShoppingExperience.getCardNumber();
    System.out.printf("Customer Card Number: %d\n", cardNumber);
    
  }
}