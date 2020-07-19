/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package bakery;

import java.util.HashMap;
import java.util.List;

import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.StoreConstants;

/**
 * @author ibirc
 *
 */
public class BakeryDept extends Department {
    String deptName = StoreConstants.deptNames.BAKERY.name();
    List<String> bakeryRecords = null;
    HashMap<String, BakeryProd> bakeryProducts;

    /**
     * Constructor
     */
    public BakeryDept() {

	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.BAKERY_TRUCK);
	bakeryRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(bakeryRecords);

	bakeryProducts = new HashMap<String, BakeryProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	for (String record : bakeryRecords) {
	    BakeryProd bp = new BakeryProd();
	    boolean recordToProductSuccessful = bp.recordToProduct(record);

	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(bp);
		bakeryProducts.put(prodKey, bp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		bakeryRecords.size(), bakeryProducts.size());

	// TODO Auto-generated method stub
    }

}
