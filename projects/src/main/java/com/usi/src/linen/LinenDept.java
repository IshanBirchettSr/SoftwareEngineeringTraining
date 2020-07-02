/**
 * 
 */
package linen;

import java.util.List;

import util.DataCsvLoad;
import util.Department;
import util.StoreConstants;

/**
 * @author Allma M. Johnson
 *
 */
public class LinenDept extends Department {
    String deptName = StoreConstants.LINEN_TRUCK;

    /**
     * 
     */
    public LinenDept() {
	DataCsvLoad unLoadTrucks = new DataCsvLoad();

	unLoadTrucks.loadData(StoreConstants.LINEN_TRUCK);
	List<String> loadedRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(loadedRecords);
	System.out.printf("%s Department open with %d products\n", deptName, loadedRecords.size());
    }

    @Override
    protected int loadProduct() {
	// TODO Auto-generated method stub
	return 0;
    }

}
