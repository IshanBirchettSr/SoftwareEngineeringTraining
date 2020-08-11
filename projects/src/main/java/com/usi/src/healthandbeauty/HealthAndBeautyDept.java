/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package healthandbeauty;

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
 * @author ibirc and chich
 *
 */
public class HealthAndBeautyDept extends Department {
    String deptName = StoreConstants.deptNames.HEALTH_AND_BEAUTY.name();
    List<String> healthAndBeautyRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold HousewaresProd objects.
    HashMap<String, HealthAndBeautyProd> healthAndBeautyProducts;

    /**
     * Constructor
     */
    public HealthAndBeautyDept() {
	super.setDeptName(deptName);
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.HEALTH_AND_BEAUTY_TRUCK);
	healthAndBeautyRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(healthAndBeautyRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// autoRecords.size());
	keyMap = new HashMap<Integer, String>();
	// Housewares Product Load
	healthAndBeautyProducts = new HashMap<String, HealthAndBeautyProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : healthAndBeautyRecords) {
	    HealthAndBeautyProd hwp = new HealthAndBeautyProd();
	    boolean recordToProductSuccessful = hwp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to housewaresProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(hwp);
		int howMany = hwp.getNumUnitsInstock();
		for (int i = 0; i < howMany; i++) {

		    healthAndBeautyProducts.put(prodKey + 1, hwp);
		}

	    }
	    System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		    healthAndBeautyRecords.size(), healthAndBeautyProducts.size());
	}
    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> healthAndBeautyProductKeys = healthAndBeautyProducts.keySet();

	int totalProducts = healthAndBeautyProductKeys.size();
	int i = 1;
	for (String pKey : healthAndBeautyProductKeys) {
	    Product pd = healthAndBeautyProducts.get(pKey);
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
	    HealthAndBeautyProd pd = healthAndBeautyProducts.get(pKey);
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
