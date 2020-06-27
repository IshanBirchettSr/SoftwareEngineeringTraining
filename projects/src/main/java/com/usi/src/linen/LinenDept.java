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
	String deptName = StoreConstants.deptNames.LINEN.name();
	List<String> linenRecords = null;
	
	/**
	 * 
	 */
	public LinenDept() {
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	
	unLoadTrucks.loadData(StoreConstants.LINEN_TRUCK);
	List<String> linenRecords = unLoadTrucks.getRecords();
	this.setLinenRecords(linenRecords);
	System.out.printf("%s Department open with %d products\n", deptName, linenRecords.size());
		
	}

	private void setLinenRecords(List<String> linenRecords2) {
		// TODO Auto-generated method stub
		
	}

}
