/**
 * 
 */
package haircare;

import java.util.HashMap;
import java.util.List;

import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.StoreConstants;

/**
 * @author Allma M. Johnson, Roxanne L. Earnest
 *
 */
public class HairCareDept extends Department {
    String deptName = StoreConstants.deptNames.HAIR_CARE.name();
    List<String> haircareRecords = null;
    // HashMap<K, V> to hold haircareProd objects.
    HashMap<String, HaircareProd> haircareProducts;

    /**
     * Constructor
     */
    public HairCareDept() {
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.HAIR_CARE_TRUCK);
	haircareRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(haircareRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// haircareRecords.size());

	// haircare Product Load
	haircareProducts = new HashMap<String, HaircareProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : haircareRecords) {
	    HaircareProd hcp = new HaircareProd();
	    boolean recordToProductSuccessful = hcp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to haircareProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(hcp);
		haircareProducts.put(prodKey, hcp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		haircareRecords.size(), haircareProducts.size());

    }
}
