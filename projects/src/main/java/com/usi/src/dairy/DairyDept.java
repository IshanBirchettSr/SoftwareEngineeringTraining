package dairy;

import java.util.HashMap;
import java.util.List;

import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.StoreConstants;

/**
 * @author malac
 *
 */
public class DairyDept extends Department {
    String deptName = StoreConstants.deptNames.DAIRY.name();
    List<String> dairyRecords = null;
    // HashMap<K, V> to hold DairyProd objects.
    HashMap<String, DairyProd> dairyProducts;

    /**
     * Constructor
     */
    public DairyDept() {
// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.DAIRY_TRUCK);
	dairyRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(dairyRecords);
// System.out.printf("%s Department open with %d records\n", deptName,
// dairyRecords.size());  }

	// Automotive Product Load
	dairyProducts = new HashMap<String, DairyProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : dairyRecords) {
	    DairyProd dp = new DairyProd();
	    boolean recordToProductSuccessful = dp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to dairyProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(dp);
		dairyProducts.put(prodKey, dp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		dairyRecords.size(), dairyProducts.size());

    }
}
