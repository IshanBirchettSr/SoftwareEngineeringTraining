package automotive;

import java.util.HashMap;
import java.util.List;

import util.DataCsvLoad;
import util.Department;
import util.StoreConstants;

/**
 * @author Allma M. Johnson
 *
 */
public class AutomotiveDept extends Department {
    String deptName = StoreConstants.deptNames.AUTOMOTIVE.name();
    // HashMap<K, V> to hold AutomotiveProd objects.
    HashMap<String, AutomotiveProd> autoProducts;

    /**
     * 
     */
    public AutomotiveDept() {
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.AUTOMOTIVE_TRUCK);
	List<String> autoRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(autoRecords);
	System.out.printf("%s Department open with %d records\n", deptName, autoRecords.size());
	autoProducts = new HashMap<String, AutomotiveProd>();
    }

    @Override
    protected int loadProduct() {
	// TODO Auto-generated method stub
	return 0;
    }

}