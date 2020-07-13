package luggage;
import java.util.HashMap;
import java.util.List;
import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.StoreConstants;

public class LuggageDept extends Department {
	String deptName = StoreConstants.deptNames.LUGGAGE. name();
	List<String> luggageRecords = null;
// HashMap<K, V> to hold LuggageProd objects.
	HashMap<String, LuggageProd> LuggageProducts;
	/**
	 * Constructor
	 */
	public void LugggageDept() {
// Record Load
		DataCsvLoad unLoadTrucks = new DataCsvLoad();
		unLoadTrucks.loadData(StoreConstants.LUGGAGE_TRUCK);
		luggageRecords = unLoadTrucks.getRecords();
		this.setLoadedRecords(luggageRecords);
// System.out.printf("%s Department open with %d records\n", deptName,
// autoRecords.size());

// Automotive Product Load
		LuggageProducts = new HashMap<String, LuggageProd>();
		loadProducts();
	}

    @Override
    protected void loadProducts() {
	// TODO Auto-generated method stub
    	// Load products
    			for (String record : luggageRecords) {
    				LuggageProd lp = new LuggageProd();
    				boolean recordToProductSuccessful = lp.recordToProduct(record);

    				// If it fails to convert any field, don't add that object to luggageProducts
    				if (recordToProductSuccessful == true) {
    					String prodKey = ProdKeyGen.genKey(lp);
    					LuggageProducts.put(prodKey, lp);
    				}
    			}
    			System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
    					luggageRecords.size(), LuggageProducts.size());

    		}
    	}

