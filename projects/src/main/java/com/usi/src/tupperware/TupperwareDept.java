package tupperware;

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

public class TupperwareDept extends Department {
    String deptName = StoreConstants.deptNames.TUPPERWARE.name();
    List<String> tupperwareRecords = null;
    // HashMap<K, V> to hold TupperwareProd objects.
    HashMap<String, TupperwareProd> tupperwareProducts;

    /**
     * Constructor
     */
    public TupperwareDept() {
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.TUPPERWARE_TRUCK);
	tupperwareRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(tupperwareRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// tupperwareRecords.size());

	// Tupperware Product Load
	tupperwareProducts = new HashMap<String, TupperwareProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : tupperwareRecords) {
	    TupperwareProd twp = new TupperwareProd();
	    boolean recordToProductSuccessful = twp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to autoProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(twp);
		tupperwareProducts.put(prodKey, twp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		tupperwareRecords.size(), tupperwareProducts.size());

    }
}
