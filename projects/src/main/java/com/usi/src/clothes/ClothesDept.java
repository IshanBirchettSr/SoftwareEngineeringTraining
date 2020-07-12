package clothes;

import java.util.HashMap;
import java.util.List;

import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.StoreConstants;

public class ClothesDept extends Department {
    String deptName = StoreConstants.deptNames.CLOTHES.name();
    List<String> clothesRecords = null;
    // HashMap<K, V> to hold ClothesProd objects.
    HashMap<String, ClothesProd> clothesProducts;

    /**
     * Constructor
     */
    public ClothesDept() {
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.CLOTHES_TRUCK);
	clothesRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(clothesRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// clothesRecords.size());

	// Clothes Product Load
	clothesProducts = new HashMap<String, ClothesProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : clothesRecords) {
	    ClothesProd cp = new ClothesProd();
	    boolean recordToProductSuccessful = cp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to clothesProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(cp);
		clothesProducts.put(prodKey, cp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		clothesRecords.size(), clothesProducts.size());

    }
}
