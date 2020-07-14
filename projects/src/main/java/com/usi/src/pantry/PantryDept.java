package pantry;

import java.util.HashMap;
import java.util.List;
import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.StoreConstants;

/**
 * @author chich
 *
 */

public class PantryDept extends Department {

	    String deptName = StoreConstants.deptNames.PANTRY.name();
	    List<String> pantryRecords = null;
	    // HashMap<K, V> to hold PantryProd objects.
	    HashMap<String, PantryProd> pantryProducts;

	    /**
	     * Constructor
	     */
	    public PantryDept() {
		// Record Load
		DataCsvLoad unLoadTrucks = new DataCsvLoad();
		unLoadTrucks.loadData(StoreConstants.PANTRY_TRUCK);
		pantryRecords = unLoadTrucks.getRecords();
		this.setLoadedRecords(pantryRecords);
		// System.out.printf("%s Department open with %d records\n", deptName,
		// pantryRecords.size());

		// Pantry Product Load
		pantryProducts = new HashMap<String, PantryProd>();
		loadProducts();
	    }

	    @Override
	    protected void loadProducts() {
		// Load products
		for (String record : pantryRecords) {
		    PantryProd pp = new PantryProd();
		    boolean recordToProductSuccessful = pp.recordToProduct(record);

		    // If it fails to convert any field, don't add that object to pantryProducts
		    if (recordToProductSuccessful == true) {
			String prodKey = ProdKeyGen.genKey(pp);
			pantryProducts.put(prodKey, pp);
		    }
		}
		System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
			pantryRecords.size(), pantryProducts.size());

	    }
	}
