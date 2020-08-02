/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package meat;

import java.util.HashMap;
import java.util.List;

import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

/**
 * @author ibirc and Allma M. Johnson
 *
 */
public class MeatDept extends Department {
    String deptName = StoreConstants.deptNames.MEAT.name();
    List<String> meatRecords = null;
    // HashMap<K, V> to hold MeatProd objects.
    HashMap<String, MeatProd> meatProducts;

    /**
     * Constructor
     */
    public MeatDept() {
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.MEAT_TRUCK);
	meatRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(meatRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// meatRecords.size());

	// Product MeatLoad
	meatProducts = new HashMap<String, MeatProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : meatRecords) {
	    MeatProd mp = new MeatProd();
	    boolean recordToProductSuccessful = mp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to autoProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(mp);
		meatProducts.put(prodKey, mp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		meatRecords.size(), meatProducts.size());

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
