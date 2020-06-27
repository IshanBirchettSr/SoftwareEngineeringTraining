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
import util.Department;
import util.StoreConstants;

/**
 * @author malac
 *
 */
public class ElectronicsDept extends Department {
	String deptName = StoreConstants.deptNames.ELECTRONICS.name();
	/**
	 * 
	 */
	public ElectronicsDept() {
		// TODO Auto-generated constructor stub
		DataCsvLoad unLoadTrucks = new DataCsvLoad();

		unLoadTrucks.loadData(StoreConstants.ELECTRONICS_TRUCK);
		List<String> electronicRecords = unLoadTrucks.getRecords();
		this.setLoadedRecords(electronicRecords);
		System.out.printf("%s Department open with %d products\n", deptName, electronicRecords.size());
	}
	
		// TODO Auto-generated method stub
		
	}
	
		// TODO Auto-generated method stub
		
	

	/**
	 * @param args
	 */
	


