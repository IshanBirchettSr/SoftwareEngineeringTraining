package cannedgoods;

import java.util.HashMap;
import java.util.List;

import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

/**
 * @author chich
 *
 */

public class CannedGoodsDept extends Department {
    String deptName = StoreConstants.deptNames.CANNED_GOODS.name();
    List<String> cannedGoodsRecords = null;
    // HashMap<K, V> to hold AutomotiveProd objects.
    HashMap<String, CannedGoodsProd> cannedGoodsProducts;

    /**
     * Constructor
     */
    public CannedGoodsDept() {
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.CANNED_GOODS_TRUCKS);
	cannedGoodsRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(cannedGoodsRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// autoRecords.size());

	// Automotive Product Load
	cannedGoodsProducts = new HashMap<String, CannedGoodsProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : cannedGoodsRecords) {
	    CannedGoodsProd cp = new CannedGoodsProd();
	    boolean recordToProductSuccessful = cp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to autoProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(cp);
		cannedGoodsProducts.put(prodKey, cp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		cannedGoodsRecords.size(), cannedGoodsProducts.size());

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
