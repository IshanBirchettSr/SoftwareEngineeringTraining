/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package bakery;

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
public class BakeryDept extends Department {
    String deptName = StoreConstants.deptNames.BAKERY.name();
    List<String> bakeryRecords = null;
    HashMap<Integer, String> keyMap = null;
    HashMap<String, BakeryProd> bakeryProducts;

    /**
     * Constructor
     */
    public BakeryDept() {
	super.setDeptName(deptName);
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

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> bakeryProductKeys = bakeryProducts.keySet();

	int totalProducts = bakeryProductKeys.size();
	int i = 1;
	for (String pKey : bakeryProductKeys) {
	    Product pd = bakeryProducts.get(pKey);
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
	    BakeryProd pd = bakeryProducts.get(pKey);
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
