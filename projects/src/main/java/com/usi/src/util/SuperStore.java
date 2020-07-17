package util;

import automotive.AutomotiveDept;
import babyessentials.BabyEssentialsDept;
import bakery.BakeryDept;
import beverages.BeveragesDept;
import books.BookDept;
import cannedgoods.CannedGoodsDept;
import cleaningsupplies.CleaningSuppliesDept;
import clothes.ClothesDept;
import customerservice.Greeting;
import electronics.ElectronicsDept;
import fragrance.FragranceDept;
import furniture.FurnitureDept;
import garden.GardenDept;
import linen.LinenDept;
import meat.MeatDept;
import pantry.PantryDept;
import petcare.PetcareDept;
import pharmacy.PharmacyDept;
import plantbase.PlantBaseDept;
import prescriptioneyeware.PrescriptionEyewareDept;
import produce.ProduceDept;
import seafood.SeafoodDept;
import stationary.StationaryDept;
import toys.ToysDept;
import tupperware.TupperwareDept;

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
	FragranceDept fdd = new FragranceDept();
	GardenDept gd = new GardenDept();
	// HairCareDept hd = new HairCareDept();
	LinenDept ln = new LinenDept();
	PrescriptionEyewareDept pw = new PrescriptionEyewareDept();
	ToysDept td = new ToysDept();
	CleaningSuppliesDept csd = new CleaningSuppliesDept();
	BakeryDept bd = new BakeryDept();
	FurnitureDept fd = new FurnitureDept();
	ClothesDept cd = new ClothesDept();
	MeatDept md = new MeatDept();
	PlantBaseDept pbd = new PlantBaseDept();
	ProduceDept pdd = new ProduceDept();
	PharmacyDept pd = new PharmacyDept();
	SeafoodDept sd = new SeafoodDept();
	BabyEssentialsDept bed = new BabyEssentialsDept();
 	BookDept bod = new BookDept();
	CannedGoodsDept cgd = new CannedGoodsDept();
	PetcareDept pcd = new PetcareDept();
	PantryDept ptd = new PantryDept();
	TupperwareDept twd = new TupperwareDept();
	BeveragesDept bevd = new BeveragesDept();
	StationaryDept syd = new StationaryDept();

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
