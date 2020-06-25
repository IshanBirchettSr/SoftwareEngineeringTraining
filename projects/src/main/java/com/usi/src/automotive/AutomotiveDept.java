package automotive;

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
    List<String> autoRecords = null;

    /**
     * 
     */
    public AutomotiveDept() {
	DataCsvLoad unLoadTrucks = new DataCsvLoad();

	unLoadTrucks.loadData(StoreConstants.AUTOMOTIVE_TRUCK);
	List<String> autoRecords = unLoadTrucks.getRecords();
	this.setAutoRecords(autoRecords);
	System.out.printf("%s Department open with %d products\n", deptName, autoRecords.size());
    }

}