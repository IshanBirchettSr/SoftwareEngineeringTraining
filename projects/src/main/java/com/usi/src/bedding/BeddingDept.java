/**
 * 
 */
package bedding;

import java.util.HashMap;
import java.util.List;

import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

/**
 * @author Allma M. Johnson and Chich
 *
 */
public class BeddingDept extends Department {

    String deptName = StoreConstants.deptNames.BEDDING.name();
    List<String> beddingRecords = null;
// HashMap<K, V> to hold ElectronicsProd objects.
    HashMap<String, BeddingProd> BeddingProducts;

    /**
     * Constructor
     */
    public BeddingDept() {
// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.BEDDING_TRUCK);
	beddingRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(beddingRecords);
// System.out.printf("%s Department open with %d records\n", deptName,
// autoRecords.size());

// Automotive Product Load
	BeddingProducts = new HashMap<String, BeddingProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
// Load products
	for (String record : beddingRecords) {
	    BeddingProd bp = new BeddingProd();
	    boolean recordToProductSuccessful = bp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to autoProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(bp);
		BeddingProducts.put(prodKey, bp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		beddingRecords.size(), BeddingProducts.size());

    }

    @Override
    public void listProducts() {
	// TODO Auto-generated method stub

    }

    @Override
    public List<Product> getProducts() {
	// TODO Auto-generated method stub
	return null;
    }
}
