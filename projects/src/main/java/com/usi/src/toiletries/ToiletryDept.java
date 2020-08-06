/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package toiletries;

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
public class ToiletryDept extends Department {

    String deptName = StoreConstants.deptNames.TOILETRIES.name();
    List<String> toletriesRecords = null;
    HashMap<Integer, String> keyMap = null;
// HashMap<K, V> to hold ElectronicsProd objects.
    HashMap<String, ToiletryProd> ToiletryProducts;

    /**
     * Constructor
     */
    public ToiletryDept() {
	super.setDeptName(deptName);
// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.TOILETRIES_TRUCK);
	toletriesRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(toletriesRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// autoRecords.size());
	keyMap = new HashMap<Integer, String>();
	// Automotive Product Load
	ToiletryProducts = new HashMap<String, ToiletryProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
// Load products
	for (String record : toletriesRecords) {
	    ToiletryProd tp = new ToiletryProd();
	    boolean recordToProductSuccessful = tp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to toiletryProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(tp);

		int howMany = tp.getNumUnitsInstock();
		for (int i = 0; i < howMany; i++) {

		    ToiletryProducts.put(prodKey + 1, tp);
		}
	    }

	    System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		    toletriesRecords.size(), ToiletryProducts.size());

	}
    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> toiletryProductKeys = ToiletryProducts.keySet();

	int totalProducts = toiletryProductKeys.size();
	int i = 1;
	for (String pKey : toiletryProductKeys) {
	    Product pd = ToiletryProducts.get(pKey);
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
	    ToiletryProd pd = ToiletryProducts.get(pKey);
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
