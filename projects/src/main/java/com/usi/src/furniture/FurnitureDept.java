/**
 * 
 */
package furniture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

/**
 * @author chich and Allma M. Johnson
 *
 */
public class FurnitureDept extends Department {
    String deptName = StoreConstants.deptNames.FURNITURE.name();
    List<String> furnitureRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold FurnitureProd objects.
    HashMap<String, FurnitureProd> furnitureProducts;

    /**
     * Constructor
     */
    public FurnitureDept() {
	super.setDeptName(deptName);
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.FURNITURE_TRUCK);
	furnitureRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(furnitureRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// furnitureRecords.size());
	keyMap = new HashMap<Integer, String>();
	// Furniture Product Load
	furnitureProducts = new HashMap<String, FurnitureProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : furnitureRecords) {
	    FurnitureProd fp = new FurnitureProd();
	    boolean recordToProductSuccessful = fp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to furnitureProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(fp);
		int howMany = fp.getNumUnitsInstock();
		for (int i = 0; i < howMany; i++) {

		    furnitureProducts.put(prodKey + 1, fp);
		}

	    }
	    System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		    furnitureRecords.size(), furnitureProducts.size());

	}
    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> furnitureProductKeys = furnitureProducts.keySet();

	int totalProducts = furnitureProductKeys.size();
	int i = 1;
	for (String pKey : furnitureProductKeys) {
	    Product pd = furnitureProducts.get(pKey);
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
	    FurnitureProd pd = furnitureProducts.get(pKey);
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
