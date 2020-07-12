package deli;

import java.util.HashMap;
import java.util.List;
import util.DataCsvLoad;
import util.Department;
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
    	// autoRecords.size());

    	// Automotive Product Load
    	deliProducts = new HashMap<String, DeliProd>();
    	loadProducts();
        }

    @Override
    protected void loadProducts() {
	// TODO Auto-generated method stub
    }

}