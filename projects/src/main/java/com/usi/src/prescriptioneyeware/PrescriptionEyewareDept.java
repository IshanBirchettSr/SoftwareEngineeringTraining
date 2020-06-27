

package prescriptioneyeware;

import java.util.List;

import util.DataCsvLoad;
import util.Department;
import util.StoreConstants;
/**
 * @author malac
 *
 */

public class PrescriptionEyewareDept extends Department { 
	 String deptName = StoreConstants.deptNames.PRESCRIPTION_EYEWARE.name();
	    List<String> autoRecords = null;
	/**
	 * @param args
	 * @return 
	 */
	public void PresriptionEyewareDept() {
			DataCsvLoad unLoadTrucks = new DataCsvLoad();

			unLoadTrucks.loadData(StoreConstants.PRESCRIPTIONEYEWARE_TRUCK);
			List<String> autoRecords = unLoadTrucks.getRecords();
			this.setAutoRecords(autoRecords);
			System.out.printf("%s Department open with %d products\n", deptName, autoRecords.size());

	}

}
