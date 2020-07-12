package customerservice;

import automotive.AutomotiveDept;
import bakery.BakeryDept;
import cleaningsupplies.CleaningSuppliesDept;
import clothes.ClothesDept;
import electronics.ElectronicsDept;
import furniture.FurnitureDept;
import garden.GardenDept;
import haircare.HairCareDept;
import linen.LinenDept;
import meat.MeatDept;
import pharmacy.PharmacyDept;
import plantbase.PlantBaseDept;
import prescriptioneyeware.PrescriptionEyewareDept;
import seafood.SeafoodDept;
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
	GardenDept gd = new GardenDept();
	HairCareDept hd = new HairCareDept();
	LinenDept ln = new LinenDept();
	PrescriptionEyewareDept pw = new PrescriptionEyewareDept();
	ToysDept td = new ToysDept();
	CleaningSuppliesDept csd = new CleaningSuppliesDept();
	BakeryDept bd = new BakeryDept();
	FurnitureDept fd = new FurnitureDept();
	ClothesDept cd = new ClothesDept();
	MeatDept md = new MeatDept();
	PlantBaseDept pbd = new PlantBaseDept();
	PharmacyDept pd = new PharmacyDept();
	SeafoodDept sd = new SeafoodDept();
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
