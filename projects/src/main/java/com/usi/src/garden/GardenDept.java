/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package garden;

import java.util.List;

import util.DataCsvLoad;
import util.Department;
import util.StoreConstants;

/**
 * @author chich
 *
 */

public class GardenDept extends Department {
    String deptName = StoreConstants.deptNames.GARDEN.name();

    /**
     * 
     */
    public GardenDept() {
	DataCsvLoad unLoadTrucks = new DataCsvLoad();

	unLoadTrucks.loadData(StoreConstants.GARDEN_TRUCK);
	List<String> gardenRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(gardenRecords);
	System.out.printf("%s Department open with %d products\n", deptName, gardenRecords.size());
    }
}