/**
 * 
 */
package linen;

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
public class LinenDept extends Department {
    String deptName = StoreConstants.deptNames.LINEN.name();
    List<String> linenRecords = null;
    // HashMap<K, V> to hold AutomotiveProd objects.
    HashMap<String, LinenProd> linenProducts;

    /**
     * 
     */
    public LinenDept() {
	DataCsvLoad unLoadTrucks = new DataCsvLoad();

	unLoadTrucks.loadData(StoreConstants.LINEN_TRUCK);
	List<String> loadedRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(loadedRecords);
	System.out.printf("%s Department open with %d products\n", deptName, loadedRecords.size());

	linenProducts = new HashMap<String, LinenProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	for (String record : linenRecords) {
	    LinenProd ap = new LinenProd();
	    boolean recordToProductSuccessful = ap.recordToProduct(record);

	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(ap);
		linenProducts.put(prodKey, ap);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		linenRecords.size(), linenProducts.size());

	// TODO Auto-generated method stub
    }

}
