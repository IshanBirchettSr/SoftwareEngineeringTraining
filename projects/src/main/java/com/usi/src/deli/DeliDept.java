package deli;

import java.util.HashMap;
import java.util.List;

import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

/**
 * @author Allma M. Johnson and Roxanne L. Earnest
 *
 */
public class DeliDept extends Department {
    String deptName = StoreConstants.deptNames.DELI.name();
    List<String> deliRecords = null;
    // HashMap<K, V> to hold DeliProd objects.
    HashMap<String, DeliProd> deliProducts;

    /**
     * Constructor
     */
    public DeliDept() {
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.DELI_TRUCK);
	deliRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(deliRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// deliRecords.size());

	// Deli Product Load
	deliProducts = new HashMap<String, DeliProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// TODO Auto-generated method stub
	// Load products
	for (String record : deliRecords) {
	    DeliProd dep = new DeliProd();
	    boolean recordToProductSuccessful = dep.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to autoProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(dep);
		deliProducts.put(prodKey, dep);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		deliRecords.size(), deliProducts.size());

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
