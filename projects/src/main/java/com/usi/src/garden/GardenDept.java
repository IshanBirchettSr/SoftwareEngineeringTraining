/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package garden;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

/**
 * @author chich
 *
 */

public class GardenDept extends Department {
    String deptName = StoreConstants.deptNames.GARDEN.name();
    List<String> gardenRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold GardenProd objects.
    HashMap<String, GardenProd> gardenProducts;

    /**
     * Constructor
     */
    public GardenDept() {
	super.setDeptName(deptName);
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.GARDEN_TRUCK);
	gardenRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(gardenRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// autoRecords.size());

	// Automotive Product Load
	gardenProducts = new HashMap<String, GardenProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : gardenRecords) {
	    GardenProd gp = new GardenProd();
	    boolean recordToProductSuccessful = gp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to autoProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(gp);
		gardenProducts.put(prodKey, gp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		gardenRecords.size(), gardenProducts.size());

    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> gardenProductKeys = gardenProducts.keySet();

	int totalProducts = gardenProductKeys.size();
	int i = 1;
	for (String pKey : gardenProductKeys) {
	    Product pd = gardenProducts.get(pKey);
	    if (aKey != pKey) {
		System.out.printf("%d: %s %s\t%.2f\n", i, pd.getBrandName(), pd.getProductName(), pd.getPrice());
	    }
	    aKey = pKey;
	    keyMap.put(i, pKey);
	    i++;
	}
    }

    public List<Product> getProds(int index, int quantity) {
	ArrayList<Product> pdList = new ArrayList<Product>();
	String pKey = keyMap.get(index);
	for (int i = 0; i < quantity; i++) {
	    GardenProd pd = gardenProducts.get(pKey);
	    pdList.add(pd);
	}

	return pdList;
    }

    @Override
    public List<Product> getProducts() {
	List<Product> pList = null;

	return pList;
    }
}
