/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package electronics;

import java.util.HashMap;
import java.util.List;

import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

/**
 * @author malac
 *
 */
public class ElectronicsDept extends Department {
    String deptName = StoreConstants.deptNames.ELECTRONICS.name();
    List<String> electronicsRecords = null;
// HashMap<K, V> to hold ElectronicsProd objects.
    HashMap<String, ElectronicsProd> electronicsProducts;

    /**
     * Constructor
     */
    public ElectronicsDept() {
// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.ELECTRONICS_TRUCK);
	electronicsRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(electronicsRecords);
// System.out.printf("%s Department open with %d records\n", deptName,
// autoRecords.size());

// Automotive Product Load
	electronicsProducts = new HashMap<String, ElectronicsProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
// Load products
	for (String record : electronicsRecords) {
	    ElectronicsProd ep = new ElectronicsProd();
	    boolean recordToProductSuccessful = ep.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to autoProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(ep);
		electronicsProducts.put(prodKey, ep);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		electronicsRecords.size(), electronicsProducts.size());

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
