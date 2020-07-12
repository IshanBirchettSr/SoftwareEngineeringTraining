/**
 * 
 */
package plantbase;

import java.util.HashMap;
import java.util.List;

import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.StoreConstants;

/**
 * @author Allma M. Johnson
 *
 */
public class PlantBaseDept extends Department {
    String deptName = StoreConstants.deptNames.PLANTBASE.name();
    List<String> plantbaseRecords = null;
    // HashMap<K, V> to hold PlantBaseProd objects.
    HashMap<String, PlantBaseProd> plantbaseProducts;

    /**
     * Constructor
     */
    public PlantBaseDept() {
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.PLANT_BASE_TRUCK);
	plantbaseRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(plantbaseRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// plantbaseRecords.size());

	// PlantBase Product Load
	plantbaseProducts = new HashMap<String, PlantBaseProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : plantbaseRecords) {
	    PlantBaseProd pbp = new PlantBaseProd();
	    boolean recordToProductSuccessful = pbp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to plantbaseProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(pbp);
		plantbaseProducts.put(prodKey, pbp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		plantbaseRecords.size(), plantbaseProducts.size());

    }
}
