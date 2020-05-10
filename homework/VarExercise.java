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
  }
}
    