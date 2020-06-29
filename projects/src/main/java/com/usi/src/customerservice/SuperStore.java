package customerservice;

import automotive.AutomotiveDept;
import electronics.ElectronicsDept;
import garden.GardenDept;
import linen.LinenDept;
import toys.ToysDept;

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
    private void openDepartments() {
	AutomotiveDept ad = new AutomotiveDept();
	ElectronicsDept ed = new ElectronicsDept();
	LinenDept ln = new LinenDept();
	ToysDept td = new ToysDept();
	GardenDept gd = new GardenDept();
	// Add yours here.

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
