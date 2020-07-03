package automotive;

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
public class AutomotiveDept extends Department {
    String deptName = StoreConstants.deptNames.AUTOMOTIVE.name();
    List<String> autoRecords = null;
    // HashMap<K, V> to hold AutomotiveProd objects.
    HashMap<String, AutomotiveProd> autoProducts;

    /**
     * Constructor
     */
    public AutomotiveDept() {
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.AUTOMOTIVE_TRUCK);
	autoRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(autoRecords);
	System.out.printf("%s Department open with %d records\n", deptName, autoRecords.size());

	// Product Load
	autoProducts = new HashMap<String, AutomotiveProd>();
	loadProducts();
	System.out.printf("%s Department open with %d types of products\n", deptName, autoProducts.size());
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : autoRecords) {
	    AutomotiveProd ap = new AutomotiveProd();
	    ap.recordToProduct(record);
	    String prodKey = ProdKeyGen.genKey(ap);
	    autoProducts.put(prodKey, ap);
	}
    }
}
