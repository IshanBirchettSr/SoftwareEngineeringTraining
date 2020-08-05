package luggage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

public class LuggageDept extends Department {
    String deptName = StoreConstants.deptNames.LUGGAGE.name();
    List<String> luggageRecords = null;
    HashMap<Integer, String> keyMap = null;
// HashMap<K, V> to hold LuggageProd objects.
    HashMap<String, LuggageProd> LuggageProducts;

    /**
     * Constructor
     */
    public void LugggageDept() {
	super.setDeptName(deptName);
// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.LUGGAGE_TRUCK);
	luggageRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(luggageRecords);
// System.out.printf("%s Department open with %d records\n", deptName,
// autoRecords.size());
	keyMap = new HashMap<Integer, String>();
// Automotive Product Load
	LuggageProducts = new HashMap<String, LuggageProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// TODO Auto-generated method stub
	// Load products
	for (String record : luggageRecords) {
	    LuggageProd lp = new LuggageProd();
	    boolean recordToProductSuccessful = lp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to luggageProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(lp);
		int howMany = lp.getQuantity();
		for (int i = 0; i < howMany; i++) {
		 
		LuggageProducts.put(prodKey+1, lp);
	    }
		
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		luggageRecords.size(), LuggageProducts.size());
	}
    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> luggageProductKeys = LuggageProducts.keySet();

	int totalProducts = luggageProductKeys.size();
	int i = 1;
	for (String pKey : luggageProductKeys) {
	    Product pd = LuggageProducts.get(pKey);
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
	    LuggageProd pd = LuggageProducts.get(pKey);
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
