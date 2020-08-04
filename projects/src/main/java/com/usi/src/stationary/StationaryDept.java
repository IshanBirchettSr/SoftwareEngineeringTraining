package stationary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

public class StationaryDept extends Department {
    String deptName = StoreConstants.deptNames.STATIONARY.name();
    List<String> autoRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold StationaryProd objects.
    HashMap<String, StationaryProd> stationaryProducts;
    private List<String> stationaryRecords;

    /**
     * Constructor
     */
    public StationaryDept() {
	super.setDeptName(deptName);
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
	String aKey = null;
	Set<String> stationaryProductKeys = stationaryProducts.keySet();

	int totalProducts = stationaryProductKeys.size();
	int i = 1;
	for (String pKey : stationaryProductKeys) {
	    Product pd = stationaryProducts.get(pKey);
	    if (aKey != pKey) {
		System.out.printf("%d: %s %s\t%.2f\n", i, pd.getBrandName(), pd.getProductName(), pd.getPrice());
	    }
	    aKey = pKey;
	    keyMap.put(i, pKey);
	    i++;
	}
    }

    public List<Product> getProds(int index, int qauntity) {
	ArrayList<Product> pdList = new ArrayList<Product>();
	String pKey = keyMap.get(index);
	for (int i = 0; i < qauntity; i++) {
	    StationaryProd pd = stationaryProducts.get(pKey);
	    pdList.add(pd);
	}

	return pdList;
    }

    @Override
    public List<Product> getProducts() {
	List<Product> pList = null;

	return pList;
    }
}
