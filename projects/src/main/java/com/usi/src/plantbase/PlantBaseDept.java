/**
 * 
 */
package plantbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import automotive.AutomotiveProd;
import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

/**
 * @author Allma M. Johnson
 *
 */
public class PlantBaseDept extends Department {
    String deptName = StoreConstants.deptNames.PLANTBASE.name();
    List<String> plantbaseRecords = null;
    HashMap<Integer, String>keyMap = null;
    // HashMap<K, V> to hold PlantbaseProd objects.
    HashMap<String, PlantBaseProd> plantbaseProducts;

    /**
     * Constructor
     */
    public PlantBaseDept() {
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.PLANTBASE_TRUCK);
	plantbaseRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(plantbaseRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// plantbaseRecords.size());

	// PlantBaseAutomotive Product Load
	plantbaseProducts = new HashMap<String, PlantBaseProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : plantbaseRecords) {
	    PlantBaseProd pp = new PlantBaseProd();
	    boolean recordToProductSuccessful = pp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to plantbaseProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(pp);
		plantbaseProducts.put(prodKey, pp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		plantbaseRecords.size(), plantbaseProducts.size());

    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> plantBaseProductKeys = plantbaseProducts.keySet();

	int totalProducts = plantBaseProductKeys.size();
	int i = 1;
	for (String pKey : plantBaseProductKeys) {
	    Product pd = plantbaseProducts.get(pKey);
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
	    PlantBaseProd pd = plantbaseProducts.get(pKey);
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
