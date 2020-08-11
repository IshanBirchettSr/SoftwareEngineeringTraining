/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package electronics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

/**
 * @author malac
 *
 */
public class ElectronicsDept extends Department {
	String deptName = StoreConstants.deptNames.ELECTRONICS.name();
	List<String> electronicsRecords = null;
	HashMap<Integer, String> keyMap = null;
// HashMap<K, V> to hold ElectronicsProd objects.
	HashMap<String, ElectronicsProd> electronicsProducts;

	/**
	 * Constructor
	 */
	public ElectronicsDept() {
		super.setDeptName(deptName);
// Record Load
		DataCsvLoad unLoadTrucks = new DataCsvLoad();
		unLoadTrucks.loadData(StoreConstants.ELECTRONICS_TRUCK);
		electronicsRecords = unLoadTrucks.getRecords();
		this.setLoadedRecords(electronicsRecords);
// System.out.printf("%s Department open with %d records\n", deptName,
// autoRecords.size());
		keyMap = new HashMap<Integer, String>();
// Automotive Product Load
		electronicsProducts = new HashMap<String, ElectronicsProd>();
		loadProducts();
	}

	@Override
	protected void loadProducts() {
// Load products
		for (String record : electronicsRecords) {
			ElectronicsProd ep = new ElectronicsProd();
			boolean recordToProductSuccessful = ep.recordToProduct(record);

			// If it fails to convert any field, don't add that object to haircareProducts
			if (recordToProductSuccessful == true) {
				String prodKey = ProdKeyGen.genKey(ep);
				int howMany = ep.getNumUnitsInstock();
				for (int i = 0; i < howMany; i++) {

					electronicsProducts.put(prodKey + 1, ep);
				}

			}
		}
		System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
				electronicsRecords.size(), electronicsProducts.size());

	}

	@Override
	public void listProducts() {
		String aKey = null;
		Set<String> electronicProductKeys = electronicsProducts.keySet();

		int totalProducts = electronicProductKeys.size();
		int i = 1;
		for (String pKey : electronicProductKeys) {
			Product pd = electronicsProducts.get(pKey);
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
			ElectronicsProd pd = electronicsProducts.get(pKey);
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
		Image electricImage = new Image(StoreConstants.ELECTRONICSDEPT);
		ImageView iv = new ImageView();
		iv.setImage(electricImage);
		iv.setFitWidth(600);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
		iv.setCache(true);
		HBox ep = new HBox(iv);
		ep.setAlignment(Pos.CENTER);

		Scene eScene = new Scene(ep, 600, 575);

		return eScene;
	}
}
