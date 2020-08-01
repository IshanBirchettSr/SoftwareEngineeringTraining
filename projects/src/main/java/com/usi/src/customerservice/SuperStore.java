package customerservice;

import java.util.ArrayList;
import java.util.List;

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
import smartcart.StoreCheckOut;
import stationary.StationaryDept;
import toiletries.ToiletryDept;
import toys.ToysDept;
import tupperware.TupperwareDept;
import util.Department;
import util.Product;

public class SuperStore {
    // Data member section
    boolean status = false;
    Greeting greeting = null;
    Customer patron = null;
    Product prod;
    List<Department> dList = null;
    AutomotiveDept ad = null;
    StoreCheckOut checkoutLane01 = null;
    StoreCheckOut checkoutLane02 = null;
    StoreCheckOut checkoutLane03 = null;
    StoreCheckOut checkoutLane04 = null;
    StoreCheckOut checkoutLane05 = null;

    public boolean openStore(String[] args) {
	greeting = new Greeting();
	patron = greeting.sayGreeting(args);
	patron.setDepartment(dList);
	patron.startShopping();

	this.setStatus(true);
	return status;

    }

    private boolean setStatus(boolean storeOpen) {
	return status = storeOpen;
    }

    public boolean departmentClosed() {

	if (false)
	    prod.loadProducts();
	System.out.print("This department is not open for business.");
	this.departmentClosed();
	return status;

    }

    /*
     * Please keep Open Department list in alphabetical order Here each department
     * will be instantiated
     */
    private void openDepartments() {
	ad = new AutomotiveDept();
	dList.add(ad);
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
	checkoutLane01 = new StoreCheckOut();
	checkoutLane02 = new StoreCheckOut();
	checkoutLane03 = new StoreCheckOut();
	checkoutLane04 = new StoreCheckOut();
	checkoutLane05 = new StoreCheckOut();
	dList = new ArrayList<Department>();
    }

    public static void main(String[] args) {
	// Pass on args
	String[] inArgs = args;
	// Turn the lights on
	SuperStore store = new SuperStore();

	// Open Each Department
	store.openDepartments();

	// Open Store
	store.openStore(inArgs);

    }

}
