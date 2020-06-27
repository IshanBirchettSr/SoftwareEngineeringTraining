package customerservice;

import automotive.AutomotiveDept;
import linen.LinenDept;
import electronics.ElectronicsDept;

public class SuperStore {
    boolean status = false;

    public boolean openStore() {
	Greeting greeting = new Greeting();
	greeting.sayGreeting();
	this.setStatus(true);
	return status;

    }

    private boolean setStatus(boolean storeOpen) {
	return status = storeOpen;
    }

    /*
     * Here each department will be instantiated
     */
  	// Add yours here.
    private void openDepartments() {
	AutomotiveDept ad = new AutomotiveDept();	
	LinenDept ld = new LinenDept();
	ElectronicsDept ed = new ElectronicsDept();

    }   

    public static void main(String[] args) {

	// Turn the lights on
	SuperStore store = new SuperStore();

	// Open Each Department
	store.openDepartments();

	// Open Store
	System.out.printf("Is the store ready for business? %b\n", store.openStore());
    }

}
