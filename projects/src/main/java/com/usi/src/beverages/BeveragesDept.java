/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package beverages;

import java.util.HashMap;
import java.util.List;

import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

/**
 * @author ibirc and chich
 *
 */
public class BeveragesDept extends Department {

    String deptName = StoreConstants.deptNames.BEVERAGES.name();
    List<String> beveragesRecords = null;
    // HashMap<K, V> to hold BeveragesProd objects.
    HashMap<String, BeveragesProd> beveragesProducts;

    /**
     * Constructor
     */
    public BeveragesDept() {
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.BEVERAGES_TRUCK);
	beveragesRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(beveragesRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// pantryRecords.size());

	// Beverages Product Load
	beveragesProducts = new HashMap<String, BeveragesProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : beveragesRecords) {
	    BeveragesProd ap = new BeveragesProd();
	    boolean recordToProductSuccessful = ap.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to beveragesProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(ap);
		beveragesProducts.put(prodKey, ap);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		beveragesRecords.size(), beveragesProducts.size());

    }

    @Override
    public void listProducts() {
	// TODO Auto-generated method stub

    }

    @Override
    public List<Product> getProducts() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Product> getProds(int index, int quantity) {
	// TODO Auto-generated method stub
	return null;
    }
}
