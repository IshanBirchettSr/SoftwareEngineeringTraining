package cleaningsupplies;

import java.util.HashMap;
import java.util.List;

import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.StoreConstants;

public class CleaningSuppliesDept extends Department {
    String deptName = StoreConstants.deptNames.CLEANING_SUPPLIES.name();
    List<String> cleaningsuppliesRecords = null;
    // HashMap<K, V> to hold CleaningSuppliesProd objects.
    HashMap<String, CleaningSuppliesProd> cleaningsuppliesProducts;

    /**
     * Constructor
     */
    public CleaningSuppliesDept() {
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.CLEANING_SUPPLIES_TRUCK);
	cleaningsuppliesRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(cleaningsuppliesRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// autoRecords.size());

	// Automotive Product Load
	cleaningsuppliesProducts = new HashMap<String, CleaningSuppliesProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : cleaningsuppliesRecords) {
	    CleaningSuppliesProd csp = new CleaningSuppliesProd();
	    boolean recordToProductSuccessful = csp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to autoProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(csp);
		cleaningsuppliesProducts.put(prodKey, csp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		cleaningsuppliesRecords.size(), cleaningsuppliesProducts.size());

    }
}
