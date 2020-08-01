/**
 * 
 */
package furniture;

import java.util.HashMap;
import java.util.List;

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
    // HashMap<K, V> to hold FurnitureProd objects.
    HashMap<String, FurnitureProd> furnitureProducts;

    /**
     * Constructor
     */
    public FurnitureDept() {
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.FURNITURE_TRUCK);
	furnitureRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(furnitureRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// furnitureRecords.size());

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
		furnitureProducts.put(prodKey, fp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		furnitureRecords.size(), furnitureProducts.size());

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
