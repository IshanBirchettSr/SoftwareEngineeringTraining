/**
 * 
 */
package haircare;

import java.util.List;

import util.DataCsvLoad;
import util.Department;
import util.StoreConstants;

/**
 * @author Allma M. Johnson, Roxanne L. Earnest
 *
 */

public class HairCareDept extends Department {
    String deptName = StoreConstants.deptNames.HAIR_CARE.name();

    /**
     * 
     */
    public HairCareDept() {
	DataCsvLoad unLoadTrucks = new DataCsvLoad();

	unLoadTrucks.loadData(StoreConstants.HAIR_CARE_TRUCK);
	List<String> hairCareRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(hairCareRecords);
	System.out.printf("%s Department open with %d products\n", deptName, hairCareRecords.size()); // TODO
												      // Auto-generated
	// constructor stub
    }

    @Override
    protected void loadProducts() {
	// TODO Auto-generated method stub
    }
}