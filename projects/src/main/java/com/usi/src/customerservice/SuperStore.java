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
    static List<Department> dList = null;

    /**
     * @return the dList
     */
    protected static List<Department> getdList() {
	return dList;
    }

    AutomotiveDept ad = null;
    BabyEssentialsDept bed = null;
    BakeryDept bd = null;
    BeddingDept bedd = null;
    BeveragesDept bevd = null;
    BikesDept bkd = null;
    BookDept bod = null;
    CannedGoodsDept cgd = null;
    CleaningSuppliesDept csd = null;
    ClothesDept cd = null;
    DairyDept dp = null;
    DeliDept dep = null;
    ElectronicsDept ed = null;
    FragranceDept fdd = null;
    FrozenFoodsDept ffd = null;
    FurnitureDept fd = null;
    GardenDept gd = null;
    HairCareDept hcd = null;
    HealthAndBeautyDept hba = null;
    HousewaresDept hwd = null;
    LinenDept ln = null;
    MeatDept md = null;
    PantryDept ptd = null;
    PetcareDept pcd = null;
    PlantBaseDept pbd = null;
    PharmacyDept pd = null;
    PrescriptionEyewareDept pw = null;
    ProduceDept pdd = null;
    SeafoodDept sd = null;
    ShoeDept shd = null;
    StationaryDept syd = null;
    ToiletryDept tyd = null;
    ToysDept td = null;
    TupperwareDept twd = null;
    StoreCheckOut checkoutLane01 = null;
    StoreCheckOut checkoutLane02 = null;
    StoreCheckOut checkoutLane03 = null;
    StoreCheckOut checkoutLane04 = null;
    StoreCheckOut checkoutLane05 = null;

    public void openStore(String[] args) {
	this.setStatus(true);
	do {
	    greeting = new Greeting();
	    // greeting.setDepartment(dList);
	    patron = greeting.sayGreeting(args);
	    patron.startShopping();
	    checkoutLane01.checkoutCustomer(patron);
	} while (true);
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
	bed = new BabyEssentialsDept();
	dList.add(bed);
	bd = new BakeryDept();
	dList.add(bd);
	bedd = new BeddingDept();
	dList.add(bedd);
	bevd = new BeveragesDept();
	dList.add(bevd);
	bkd = new BikesDept();
	dList.add(bkd);
	bod = new BookDept();
	dList.add(bod);
	cgd = new CannedGoodsDept();
	dList.add(cgd);
	csd = new CleaningSuppliesDept();
	dList.add(csd);
	cd = new ClothesDept();
	dList.add(cd);
	dp = new DairyDept();
	dList.add(dp);
	dep = new DeliDept();
	dList.add(dep);
	ed = new ElectronicsDept();
	dList.add(ed);
	fdd = new FragranceDept();
	dList.add(fdd);
	ffd = new FrozenFoodsDept();
	dList.add(ffd);
	fd = new FurnitureDept();
	dList.add(fd);
	gd = new GardenDept();
	dList.add(gd);
	hcd = new HairCareDept();
	dList.add(hcd);
	hba = new HealthAndBeautyDept();
	dList.add(hba);
	hwd = new HousewaresDept();
	dList.add(hwd);
	ln = new LinenDept();
	dList.add(ln);
	md = new MeatDept();
	dList.add(md);
	ptd = new PantryDept();
	dList.add(ptd);
	pcd = new PetcareDept();
	dList.add(pcd);
	pbd = new PlantBaseDept();
	dList.add(pbd);
	pd = new PharmacyDept();
	dList.add(pd);
	pw = new PrescriptionEyewareDept();
	dList.add(pw);
	pdd = new ProduceDept();
	dList.add(pdd);
	sd = new SeafoodDept();
	dList.add(sd);
	shd = new ShoeDept();
	dList.add(shd);
	syd = new StationaryDept();
	dList.add(syd);
	tyd = new ToiletryDept();
	dList.add(tyd);
	td = new ToysDept();
	dList.add(td);
	twd = new TupperwareDept();
	dList.add(twd);
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
