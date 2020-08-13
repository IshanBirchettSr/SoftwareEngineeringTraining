package cleaningsupplies;

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
 * @author Allma M. Johnson
 *
 */

public class CleaningSuppliesDept extends Department {

	String deptName = StoreConstants.deptNames.CLEANING_SUPPLIES.name();
	List<String> cleaningsuppliesRecords = null;
	HashMap<Integer, String> keyMap = null;
	// HashMap<K, V> to hold CleaningSuppliesProd objects.
	HashMap<String, CleaningSuppliesProd> cleaningsuppliesProducts;

	/**
	 * Constructor
	 */
	public CleaningSuppliesDept() {
		super.setDeptName(deptName);
		// Record Load
		DataCsvLoad unLoadTrucks = new DataCsvLoad();
		unLoadTrucks.loadData(StoreConstants.CLEANINGSUPPLIES_TRUCK);
		cleaningsuppliesRecords = unLoadTrucks.getRecords();
		this.setLoadedRecords(cleaningsuppliesRecords);
		keyMap = new HashMap<Integer, String>();
		// System.out.printf("%s Department open with %d records\n", deptName,
		// cleaningsuppliesRecords.size());

		// CleaningSupplies Product Load
		cleaningsuppliesProducts = new HashMap<String, CleaningSuppliesProd>();
		loadProducts();
	}

	@Override
	protected void loadProducts() {
		// Load products
		for (String record : cleaningsuppliesRecords) {
			CleaningSuppliesProd csp = new CleaningSuppliesProd();
			boolean recordToProductSuccessful = csp.recordToProduct(record);

			if (recordToProductSuccessful == true) {
				String prodKey = ProdKeyGen.genKey(csp);
				int howMany = csp.getNumUnitsInstock();
				for (int i = 0; i < howMany; i++) {
					// System.out.println(prodKey);
					cleaningsuppliesProducts.put(prodKey + i, csp);
				}
			}
		}
		System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
				cleaningsuppliesRecords.size(), cleaningsuppliesProducts.size());

	}

	@Override
	public void listProducts() {
		String aKey = null;
		Set<String> cleaningProductKeys = cleaningsuppliesProducts.keySet();

		int totalProducts = cleaningProductKeys.size();
		int i = 1;
		for (String pKey : cleaningProductKeys) {
			Product pd = cleaningsuppliesProducts.get(pKey);
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
			CleaningSuppliesProd pd = cleaningsuppliesProducts.get(pKey);
			pdList.add(pd);
		}

		return pdList;
	}

	@Override
	public Scene getScene() {
		Image cleaningsuppliesImage = new Image(StoreConstants.CLEANINGSUPPLIES);
		ImageView iv = new ImageView();
		iv.setImage(cleaningsuppliesImage);
		iv.setFitWidth(600);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
		iv.setCache(true);
		HBox fp = new HBox(iv);
		fp.setAlignment(Pos.CENTER);

		Scene csdScene = new Scene(fp, 600, 575);
		return csdScene;
	}

	@Override
	public List<Product> getProducts() {
		// TODO Auto-generated method stub
		return null;
	}

}