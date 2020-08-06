/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package meat;

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
 * @author ibirc and Allma M. Johnson
 *
 */
public class MeatDept extends Department {
    String deptName = StoreConstants.deptNames.MEAT.name();
    List<String> meatRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold MeatProd objects.
    HashMap<String, MeatProd> meatProducts;

    /**
     * Constructor
     */
    public MeatDept() {
	super.setDeptName(deptName);
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.MEAT_TRUCK);
	meatRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(meatRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// meatRecords.size());
	keyMap = new HashMap<Integer, String>();
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
		int howMany = mp.getNumUnitsInstock();
		for (int i = 0; i < howMany; i++) {

		    meatProducts.put(prodKey + 1, mp);
		}

	    }
	    System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		    meatRecords.size(), meatProducts.size());

	}
    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> meatProductKeys = meatProducts.keySet();

	int totalProducts = meatProductKeys.size();
	int i = 1;
	for (String pKey : meatProductKeys) {
	    Product pd = meatProducts.get(pKey);
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
	    MeatProd pd = meatProducts.get(pKey);
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
