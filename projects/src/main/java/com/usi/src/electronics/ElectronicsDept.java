/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package electronics;

import java.util.List;

import util.DataCsvLoad;
import util.StoreConstants;

/**
 * @author malac
 *
 */
public class ElectronicsDept {
	String deptName = StoreConstants.deptNames.ELECTRONICS.name();
    List<String> electronicRecords = null;
	/**
	 * 
	 */
	public ElectronicsDept() {
		// TODO Auto-generated constructor stub
		DataCsvLoad unLoadTrucks = new DataCsvLoad();

		unLoadTrucks.loadData(StoreConstants.AUTOMOTIVE_TRUCK);
		List<String> electronicRecords = unLoadTrucks.getRecords();
		this.setElectronicRecords(electronicRecords);
		System.out.printf("%s Department open with %d products\n", deptName, electronicRecords.size());
	}
	private void setElectronicRecords(List<String> electronicRecords2) {
		// TODO Auto-generated method stub
		
	}
	
		// TODO Auto-generated method stub
		
	}
	
		// TODO Auto-generated method stub
		
	

	/**
	 * @param args
	 */
	


