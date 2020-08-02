package stationary;

import java.util.HashMap;
import java.util.List;

import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

public class StationaryDept extends Department {
    String deptName = StoreConstants.deptNames.STATIONARY.name();
    List<String> autoRecords = null;
    // HashMap<K, V> to hold StationaryProd objects.
    HashMap<String, StationaryProd> stationaryProducts;
    private List<String> stationaryRecords;

    /**
     * Constructor
     */
    public StationaryDept() {
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.STATIONARY_TRUCK);
	stationaryRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(stationaryRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// stationaryRecords.size());

	// Stationary Product Load
	stationaryProducts = new HashMap<String, StationaryProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : stationaryRecords) {
	    StationaryProd sp = new StationaryProd();
	    boolean recordToProductSuccessful = sp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to stationaryProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(sp);
		stationaryProducts.put(prodKey, sp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		stationaryRecords.size(), stationaryProducts.size());

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

    @Override
    public List<Product> getProds(int index, int quantity) {
	// TODO Auto-generated method stub
	return null;
    }
}
