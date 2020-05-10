public class HelloWorld2 {
  // Introducing Data Members - Characteristics
  private String city = "Testing";
  private String state = "Testing2";
  private String country = "Testing3";
  private Integer noOfCitizens = 4;
  
  //Introducing Method's - Behavior
  public void run() {
    
    //Calling a Static method.
    HelloWorld.showName();
    
      //Instantiating the class HelloWorld2
      // Class vs Object
      HelloWorld2 hW = new HelloWorld2();
      HelloWorld2 dave = new HelloWorld2();
      wakeUpCall myClock = new wakeUpCall();
      Produce produceDepartment = new Produce();
      String pList = produceDepartment.getMeAList();
      System.out.printf("Here's what available: %s\n", pList);
      System.out.printf("Time to wake up: %s\n", myClock.getTime());
      System.out.printf("How many Tomatoes: %d\n", produceDepartment.howManyAvailable());
      
      
      System.out.println("Starting Hello World");
      hW.setCity("Detroit");
      String myCity = dave.getCity();
      System.out.printf("This is your city of choice: %s\n", hW.getCity());
      System.out.printf("This is Dave's city: %s\n", myCity);
  }
  
  // This method is a setter used to set data member city
  public void setCity(String myhome) {
    city = myhome;
    System.out.printf("This is your city: %s\n", city);
  }
  public String getCity() {
    //Do somethings
    return city;
  }
}
