package clothes;

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

public class ClothesDept extends Department {
	String deptName = StoreConstants.deptNames.CLOTHES.name();
	List<String> clothesRecords = null;
	HashMap<Integer, String> keyMap = null;
	// HashMap<K, V> to hold ClothesProd objects.
	HashMap<String, ClothesProd> clothesProducts;

	/**
	 * Constructor
	 */
	public ClothesDept() {
		super.setDeptName(deptName);
		// Record Load
		DataCsvLoad unLoadTrucks = new DataCsvLoad();
		unLoadTrucks.loadData(StoreConstants.CLOTHES_TRUCK);
		clothesRecords = unLoadTrucks.getRecords();
		this.setLoadedRecords(clothesRecords);
		keyMap = new HashMap<Integer, String>();
		// System.out.printf("%s Department open with %d records\n", deptName,
		// clothesRecords.size());

		// Clothes Product Load
		clothesProducts = new HashMap<String, ClothesProd>();
		loadProducts();
	}

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : clothesRecords) {
	    ClothesProd ccp = new ClothesProd();
	    boolean recordToProductSuccessful = ccp.recordToProduct(record);
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(ccp);
		int howMany = ccp.getNumUnitsInstock();
		for (int i = 0; i < howMany; i++) {
		    // System.out.println(prodKey);
		    clothesProducts.put(prodKey + i, ccp);

		}
		System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
				clothesRecords.size(), clothesProducts.size());

	}

	@Override
	public void listProducts() {
		String aKey = null;
		Set<String> clothesProductKeys = clothesProducts.keySet();

		int totalProducts = clothesProductKeys.size();
		int i = 1;
		for (String pKey : clothesProductKeys) {
			Product pd = clothesProducts.get(pKey);
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
			ClothesProd pd = clothesProducts.get(pKey);
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
