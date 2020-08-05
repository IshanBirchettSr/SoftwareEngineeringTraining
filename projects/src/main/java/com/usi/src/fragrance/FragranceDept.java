package fragrance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

public class FragranceDept extends Department {
	String deptName = StoreConstants.deptNames.FRAGRANCE.name();
	List<String> fragranceRecords = null;
	HashMap<Integer, String> keyMap = null;
	// HashMap<K, V> to hold FragranceProd objects.
	HashMap<String, FragranceProd> fragranceProducts;

	/**
	 * Constructor
	 */
	public FragranceDept() {
		super.setDeptName(deptName);
		// Record Load
		DataCsvLoad unLoadTrucks = new DataCsvLoad();
		unLoadTrucks.loadData(StoreConstants.FRAGRANCE_TRUCK);
		fragranceRecords = unLoadTrucks.getRecords();
		this.setLoadedRecords(fragranceRecords);
		// System.out.printf("%s Department open with %d records\n", deptName,
		// autoRecords.size());
		keyMap = new HashMap<Integer, String>();
		// Fragrance Product Load
		fragranceProducts = new HashMap<String, FragranceProd>();
		loadProducts();
	}

	@Override
	protected void loadProducts() {
		// TODO Auto-generated method stub
		// Load products
		for (String record : fragranceRecords) {
			FragranceProd fdd = new FragranceProd();
			boolean recordToProductSuccessful = fdd.recordToProduct(record);

			// If it fails to convert any field, don't add that object to autoProducts
			if (recordToProductSuccessful == true) {
				String prodKey = ProdKeyGen.genKey(fdd);
				int howMany = fdd.getQuantity();
				for (int i = 0; i < howMany; i++) {

					fragranceProducts.put(prodKey + 1, fdd);
				}

			}
		}
		System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
				fragranceRecords.size(), fragranceProducts.size());

	}

	@Override
	public void listProducts() {
		String aKey = null;
		Set<String> fragranceProductKeys = fragranceProducts.keySet();

		int totalProducts = fragranceProductKeys.size();
		int i = 1;
		for (String pKey : fragranceProductKeys) {
			Product pd = fragranceProducts.get(pKey);
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
			FragranceProd pd = fragranceProducts.get(pKey);
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
