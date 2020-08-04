/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package seafood;

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
 * @author Allma M. Johnson
 *
 */
public class SeafoodDept extends Department {
    String deptName = StoreConstants.deptNames.SEAFOOD.name();
    List<String> seafoodRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold SeafoodProd objects.
    HashMap<String, SeafoodProd> seafoodProducts;

    /**
     * Constructor
     */
    public SeafoodDept() {
	super.setDeptName(deptName);
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.SEAFOOD_TRUCK);
	seafoodRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(seafoodRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// seafoodRecords.size());

	// Seafood Product Load
	seafoodProducts = new HashMap<String, SeafoodProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : seafoodRecords) {
	    SeafoodProd sfp = new SeafoodProd();
	    boolean recordToProductSuccessful = sfp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to autoProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(sfp);
		seafoodProducts.put(prodKey, sfp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		seafoodRecords.size(), seafoodProducts.size());

    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> seafoodProductKeys = seafoodProducts.keySet();

	int totalProducts = seafoodProductKeys.size();
	int i = 1;
	for (String pKey : seafoodProductKeys) {
	    Product pd = seafoodProducts.get(pKey);
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
	    SeafoodProd pd = seafoodProducts.get(pKey);
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
