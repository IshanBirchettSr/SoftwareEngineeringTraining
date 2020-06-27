/**
 * 
 */
package haircare;

import java.util.List;

import util.DataCsvLoad;
import util.Department;
import util.StoreConstants;

/**
 * @author Allma M. Johnson
 *
 */

public class HairCareDept extends Department {
	 String deptName = StoreConstants.deptNames.HAIR_CARE.name();
	    List<String> autoRecords = null;
	/**
	 * 
	 */

		  public HairCareDept() {
				DataCsvLoad unLoadTrucks = new DataCsvLoad();

				unLoadTrucks.loadData(StoreConstants.HAIRCARE_TRUCK);
				List<String> autoRecords = unLoadTrucks.getRecords();
				this.setAutoRecords(autoRecords);
				System.out.printf("%s Department open with %d products\n", deptName, autoRecords.size());
			    
	}
}