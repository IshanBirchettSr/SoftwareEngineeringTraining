/**
 * 
 */
package housewares;

import java.util.HashMap;
import java.util.List;

import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.StoreConstants;

/**
 * @author Allma M. Johnson
 *
 */

public class HousewaresDept extends Department {
    String deptName = StoreConstants.deptNames.HOUSEWARES.name();
    List<String> housewaresRecords = null;
    // HashMap<K, V> to hold HousewaresProd objects.
    HashMap<String, HousewaresProd> housewaresProducts;

    /**
     * Constructor
     */
    public HousewaresDept() {
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.HOUSEWARES_TRUCK);
	housewaresRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(housewaresRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// autoRecords.size());

	// Housewares Product Load
	housewaresProducts = new HashMap<String, HousewaresProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : housewaresRecords) {
	    HousewaresProd hwp = new HousewaresProd();
	    boolean recordToProductSuccessful = hwp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to housewaresProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(hwp);
		housewaresProducts.put(prodKey, hwp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		housewaresRecords.size(), housewaresProducts.size());

    }
}
