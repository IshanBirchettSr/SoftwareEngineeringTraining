package fragrance;
import java.util.HashMap;
import java.util.List;
import util.DataCsvLoad;
import util.Department;
import util.StoreConstants;

public class FragranceDept extends Department {
	String deptName = StoreConstants.deptNames.FRAGRANCE.name();
    List<String> fragranceRecords = null;
    // HashMap<K, V> to hold FragranceProd objects.
    HashMap<String, FragranceProd> autoProducts;
	private HashMap<String, FragranceProd> FragranceProducts;
    /**
     * Constructor
     */
    public FragranceDept() {
    	// Record Load
    	DataCsvLoad unLoadTrucks = new DataCsvLoad();
    	unLoadTrucks.loadData(StoreConstants.FRAGRANCE_TRUCK);
    	List<String> fragranceRecords = unLoadTrucks.getRecords();
    	this.setLoadedRecords(fragranceRecords);
    	// System.out.printf("%s Department open with %d records\n", deptName,
    	// autoRecords.size());

    	// Fragrance Product Load
    	FragranceProducts = new HashMap<String, FragranceProd>();
    	loadProducts();
        }
    @Override
    protected void loadProducts() {
	// TODO Auto-generated method stub
    }

}
