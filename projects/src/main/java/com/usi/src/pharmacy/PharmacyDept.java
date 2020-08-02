/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package pharmacy;

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
public class PharmacyDept extends Department {

    String deptName = StoreConstants.deptNames.PHARMACY.name();
    List<String> pharmacyRecords = null;
    // HashMap<K, V> to hold PharmacyProd objects.
    HashMap<String, PharmacyProd> pharmacyProducts;

    /**
     * Constructor
     */
    public PharmacyDept() {
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.PHARMACY_TRUCK);
	pharmacyRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(pharmacyRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// pharmacyRecords.size());

	pharmacyProducts = new HashMap<String, PharmacyProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : pharmacyRecords) {
	    PharmacyProd pp = new PharmacyProd();
	    boolean recordToProductSuccessful = pp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to autoProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(pp);
		pharmacyProducts.put(prodKey, pp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		pharmacyRecords.size(), pharmacyProducts.size());

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
