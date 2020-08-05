/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package beverages;

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
 * @author ibirc and chich
 *
 */
public class BeveragesDept extends Department {

	String deptName = StoreConstants.deptNames.BEVERAGES.name();
	List<String> beveragesRecords = null;
	HashMap<Integer, String> keyMap = null;
	// HashMap<K, V> to hold BeveragesProd objects.
	HashMap<String, BeveragesProd> beveragesProducts;

	/**
	 * Constructor
	 */
	public BeveragesDept() {
		super.setDeptName(deptName);
		// Record Load
		DataCsvLoad unLoadTrucks = new DataCsvLoad();
		unLoadTrucks.loadData(StoreConstants.BEVERAGES_TRUCK);
		beveragesRecords = unLoadTrucks.getRecords();
		this.setLoadedRecords(beveragesRecords);
		// System.out.printf("%s Department open with %d records\n", deptName,
		// pantryRecords.size());
		keyMap = new HashMap<Integer, String>();
		// Beverages Product Load
		beveragesProducts = new HashMap<String, BeveragesProd>();
		loadProducts();
	}

	@Override
	protected void loadProducts() {
		// Load products
		for (String record : beveragesRecords) {
			BeveragesProd bp = new BeveragesProd();
			boolean recordToProductSuccessful = bp.recordToProduct(record);

			if (recordToProductSuccessful == true) {
				String prodKey = ProdKeyGen.genKey(bp);
				int howMany = bp.getQuantity();
				for (int i = 0; i < howMany; i++) {
					// System.out.println(prodKey);
					beveragesProducts.put(prodKey + i, bp);
				}
			}
		}
		System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
				beveragesRecords.size(), beveragesProducts.size());

	}

	@Override
	public void listProducts() {
		String aKey = null;
		Set<String> beverageProductKeys = beveragesProducts.keySet();

		int totalProducts = beverageProductKeys.size();
		int i = 1;
		for (String pKey : beverageProductKeys) {
			Product pd = beveragesProducts.get(pKey);
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
			BeveragesProd pd = beveragesProducts.get(pKey);
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
