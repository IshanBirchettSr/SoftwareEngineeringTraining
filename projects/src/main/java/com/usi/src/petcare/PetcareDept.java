package petcare;

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

public class PetcareDept extends Department {
    String deptName = StoreConstants.deptNames.PETCARE.name();
    List<String> petcareRecords = null;
    // HashMap<K, V> to hold PetcareProd objects.
    HashMap<String, PetcareProd> petcareProducts;

    /**
     * Constructor
     */
    public PetcareDept() {
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.PETCARE_TRUCK);
	petcareRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(petcareRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// petcareRecords.size());

	// Petcare Product Load
	petcareProducts = new HashMap<String, PetcareProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : petcareRecords) {
	    PetcareProd pp = new PetcareProd();
	    boolean recordToProductSuccessful = pp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to petcareProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(pp);
		petcareProducts.put(prodKey, pp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		petcareRecords.size(), petcareProducts.size());

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
