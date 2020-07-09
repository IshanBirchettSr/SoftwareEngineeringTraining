/**
 * 
 */
package haircare;
import java.util.HashMap;
import java.util.List;

import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.StoreConstants;


/**
 * @author Allma M. Johnson, Roxanne L. Earnest
 *
 */
public class HairCareDept extends Department {
    public enum Hair_CareProduct {

	}

	String deptName = StoreConstants.deptNames.HAIR_CARE.name();
    List<String>  haircareRecords = null;
    // HashMap<K, V> to hold HairCareProd objects.
    HashMap<String, HaircareProd> haircareProd;

    /**
     * Constructor
     */
    public HairCareDept() {
	// Record Load
    DataCsvLoad unLoadTrucks = new DataCsvLoad();
    unLoadTrucks.loadData(StoreConstants.HAIR_CARE_TRUCK);
	this.setLoadedRecords(haircareRecords);
	System.out.printf("%s Department open with %d products\n", deptName, haircareRecords.size());
	
	//HairCare Product Load
	haircareProd = new HashMap<String, HaircareProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load Products= 
    for (String record : haircareRecords) {
    	HaircareProd hp = new HaircareProd();
    	boolean recordToProductSuccessful = hp.recordToProduct(record);
    	
    	// If it fails to convert any field, don't add that object to autoProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(hp);
		haircareProd.put(prodKey, hp); 
		}
    }
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		haircareRecords.size(), haircareProd.size());

	}
}
