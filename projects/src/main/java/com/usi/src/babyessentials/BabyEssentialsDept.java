/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package babyessentials;

import java.util.HashMap;
import java.util.List;

import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.StoreConstants;

/**
 * @author chich
 *
 */
public class BabyEssentialsDept extends Department {
	String deptName = StoreConstants.deptNames.BABY_ESSENTIALS.name();
	List<String> babyEssentialsRecords = null;
	// HashMap<K, V> to hold BabyEssentialsProd objects.
	HashMap<String, BabyEssentialsProd> BabyEssentialsProducts;

	/**
	 * Constructor
	 */
	public BabyEssentialsDept() {
		// Record Load
		DataCsvLoad unLoadTrucks = new DataCsvLoad();
		unLoadTrucks.loadData(StoreConstants.BABY_ESSENTIALS_TRUCK);
		babyEssentialsRecords = unLoadTrucks.getRecords();
		this.setLoadedRecords(babyEssentialsRecords);
		// System.out.printf("%s Department open with %d records\n", deptName,
		// autoRecords.size());

		// Baby Essentials Product Load
		BabyEssentialsProducts = new HashMap<String, BabyEssentialsProd>();
		loadProducts();
	}

	@Override
	protected void loadProducts() {
		// Load products
		for (String record : babyEssentialsRecords) {
			BabyEssentialsProd bp = new BabyEssentialsProd();
			boolean recordToProductSuccessful = bp.recordToProduct(record);

			// If it fails to convert any field, don't add that object to
			// babyEssentialsProducts
			if (recordToProductSuccessful == true) {
				String prodKey = ProdKeyGen.genKey(bp);
				BabyEssentialsProducts.put(prodKey, bp);
			}
		}
		System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
				babyEssentialsRecords.size(), BabyEssentialsProducts.size());

	}
}
