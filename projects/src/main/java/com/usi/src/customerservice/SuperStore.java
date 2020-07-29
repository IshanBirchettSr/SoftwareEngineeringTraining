package customerservice;

import automotive.AutomotiveDept;
import babyessentials.BabyEssentialsDept;
import bakery.BakeryDept;
import bedding.BeddingDept;
import beverages.BeveragesDept;
import bikes.BikesDept;
import books.BookDept;
import cannedgoods.CannedGoodsDept;
import cleaningsupplies.CleaningSuppliesDept;
import clothes.ClothesDept;
import dairy.DairyDept;
import deli.DeliDept;
import electronics.ElectronicsDept;
import fragrance.FragranceDept;
import frozenfoods.FrozenFoodsDept;
import furniture.FurnitureDept;
import garden.GardenDept;
import haircare.HairCareDept;
import healthandbeauty.HealthAndBeautyDept;
import housewares.HousewaresDept;
import linen.LinenDept;
import meat.MeatDept;
import pantry.PantryDept;
import petcare.PetcareDept;
import pharmacy.PharmacyDept;
import plantbase.PlantBaseDept;
import prescriptioneyeware.PrescriptionEyewareDept;
import produce.ProduceDept;
import seafood.SeafoodDept;
import shoe.ShoeDept;
import smartcart.CheckOut;
import smartcart.Receipt;
import stationary.StationaryDept;
import toiletries.ToiletryDept;
import toys.ToysDept;
import tupperware.TupperwareDept;

public class SuperStore {
    // Data member section
    boolean status = false;
    Greeting greeting = null;
   //Customer patron = null;
    CheckOut checkoutlane01 = null; 
    CheckOut checkoutlane02 = null;
    CheckOut checkoutlane03 = null;
    CheckOut checkoutlane04 = null;
    CheckOut checkoutlane05 = null;
    
    
    public boolean openStore() {
	greeting = new Greeting();
	//patron = greeting.sayGreeting();
	//patron.startShopping();
	
	
	this.setStatus(true);
	return status;

    }

    private boolean setStatus(boolean storeOpen) {
	return status = storeOpen;
    }

    /*
     * Please keep Open Department list in alphabetical order Here each department
     * will be instantiated
     */
    private void openDepartments() {
	AutomotiveDept ad = new AutomotiveDept();
	BabyEssentialsDept bed = new BabyEssentialsDept();
	BakeryDept bd = new BakeryDept();
	BeddingDept bedd = new BeddingDept();
	BeveragesDept bevd = new BeveragesDept();
	BikesDept bkd = new BikesDept();
	BookDept bod = new BookDept();
	CannedGoodsDept cgd = new CannedGoodsDept();
	CleaningSuppliesDept csd = new CleaningSuppliesDept();
	ClothesDept cd = new ClothesDept();
	DairyDept dp = new DairyDept();
	DeliDept dep = new DeliDept();
	ElectronicsDept ed = new ElectronicsDept();
	FragranceDept fdd = new FragranceDept();
	FrozenFoodsDept ffd = new FrozenFoodsDept();
	FurnitureDept fd = new FurnitureDept();
	GardenDept gd = new GardenDept();
	HairCareDept hcd = new HairCareDept();
	HealthAndBeautyDept hba = new HealthAndBeautyDept();
	HousewaresDept hwd = new HousewaresDept();
	LinenDept ln = new LinenDept();
	MeatDept md = new MeatDept();
	PantryDept ptd = new PantryDept();
	PetcareDept pcd = new PetcareDept();
	PlantBaseDept pbd = new PlantBaseDept();
	PharmacyDept pd = new PharmacyDept();
	PrescriptionEyewareDept pw = new PrescriptionEyewareDept();
	ProduceDept pdd = new ProduceDept();
	SeafoodDept sd = new SeafoodDept();
	ShoeDept shd = new ShoeDept();
	StationaryDept syd = new StationaryDept();
	ToiletryDept tyd = new ToiletryDept();
	ToysDept td = new ToysDept();
	TupperwareDept twd = new TupperwareDept();
    }
    
    public SuperStore() {
    	checkoutlane01 = new CheckOut();
    	checkoutlane02 = new CheckOut();
    	checkoutlane03 = new CheckOut();
    	checkoutlane04 = new CheckOut();
    	checkoutlane05 = new CheckOut();
    }

    public static void main(String[] args) {

	// Turn the lights on
	SuperStore store = new SuperStore();

	// Open Each Department
	store.openDepartments();

	// Open Store
    store.openStore();
    }

}
