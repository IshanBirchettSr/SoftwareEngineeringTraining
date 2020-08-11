/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package pharmacy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javafx.scene.Scene;
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
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold PharmacyProd objects.
    HashMap<String, PharmacyProd> pharmacyProducts;

    /**
     * Constructor
     */
    public PharmacyDept() {
	super.setDeptName(deptName);
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.PHARMACY_TRUCK);
	pharmacyRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(pharmacyRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// autoRecords.size());
	keyMap = new HashMap<Integer, String>();
	// Automotive Product Load
	pharmacyProducts = new HashMap<String, PharmacyProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : pharmacyRecords) {
	    PharmacyProd pp = new PharmacyProd();
	    boolean recordToProductSuccessful = pp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to pharmacyProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(pp);
		int howMany = pp.getNumUnitsInstock();
		for (int i = 0; i < howMany; i++) {

		    pharmacyProducts.put(prodKey + 1, pp);
		}
	    }

	    System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		    pharmacyRecords.size(), pharmacyProducts.size());
	}
    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> pharmacyProductKeys = pharmacyProducts.keySet();

	int totalProducts = pharmacyProductKeys.size();
	int i = 1;
	for (String pKey : pharmacyProductKeys) {
	    Product pd = pharmacyProducts.get(pKey);
	    if (aKey != pKey) {
		System.out.printf("%d: %s %s\t%.2f\n", i, pd.getBrandName(), pd.getProductName(), pd.getPrice());
	    }
	    aKey = pKey;
	    keyMap.put(i, pKey);
	    i++;
	}
    }

    public List<Product> getProds(int index, int qauntity) {
	ArrayList<Product> pdList = new ArrayList<Product>();
	String pKey = keyMap.get(index);
	for (int i = 0; i < qauntity; i++) {
	    PharmacyProd pd = pharmacyProducts.get(pKey);
	    pdList.add(pd);
	}

	return pdList;
    }

    @Override
    public List<Product> getProducts() {
	List<Product> pList = null;

	return pList;
    }

    @Override
    public Scene getScene() {
	// TODO Auto-generated method stub
	return null;
    }
}
