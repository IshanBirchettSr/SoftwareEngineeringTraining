package prescriptioneyeware;

import java.util.List;
import util.DataCsvLoad;
import util.Department;
import util.StoreConstants;

public class PrescriptionEyewareDept extends Department {
	 String deptName = StoreConstants.deptNames.PRESCRIPTION_EYEWARE.name();

	public PrescriptionEyewareDept() {
		
		DataCsvLoad unLoadTrucks = new DataCsvLoad();

		unLoadTrucks.loadData(StoreConstants.PRESCRIPTION_EYEWARE_TRUCK);
		List<String> prescriptionEyewareRecords = unLoadTrucks.getRecords();
		this.setLoadedRecords(prescriptionEyewareRecords);
		System.out.printf("%s Department open with %d products\n", deptName, prescriptionEyewareRecords.size());	
		}

}