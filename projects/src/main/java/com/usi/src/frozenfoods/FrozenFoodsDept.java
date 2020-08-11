
package frozenfoods;

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
 * @author and Allma M. Johnson
 *
 */

public class FrozenFoodsDept extends Department {
	String deptName = StoreConstants.deptNames.FROZEN_FOODS.name();
	List<String> frozenFoodsRecords = null;
	HashMap<Integer, String> keyMap = null;
// HashMap<K, V> to hold ElectronicsProd objects.
	HashMap<String, FrozenFoodsProd> frozenFoodsProducts;

	/**
	 * @return
	 * 
	 */

	public FrozenFoodsDept() {
		super.setDeptName(deptName);
		DataCsvLoad unLoadTrucks = new DataCsvLoad();
		unLoadTrucks.loadData(StoreConstants.FROZEN_FOODS_TRUCK);
		frozenFoodsRecords = unLoadTrucks.getRecords();
		this.setLoadedRecords(frozenFoodsRecords);
		System.out.printf("%s Department open with %d products\n", deptName, frozenFoodsRecords.size());
		keyMap = new HashMap<Integer, String>();
		frozenFoodsProducts = new HashMap<String, FrozenFoodsProd>();
		loadProducts();
	}

	@Override
	protected void loadProducts() {
		// TODO Auto-generated method stub
		// Load products
		for (String record : frozenFoodsRecords) {
			FrozenFoodsProd ffp = new FrozenFoodsProd();
			boolean recordToProductSuccessful = ffp.recordToProduct(record);

			// If it fails to convert any field, don't add that object to
			// frozenFoodsProducts
			if (recordToProductSuccessful == true) {
				String prodKey = ProdKeyGen.genKey(ffp);
				int howMany = ffp.getNumUnitsInstock();
				for (int i = 0; i < howMany; i++) {

					frozenFoodsProducts.put(prodKey + i, ffp);

				}

			}
			System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
					frozenFoodsRecords.size(), frozenFoodsProducts.size());

		}
	}

	@Override
	public void listProducts() {
		String aKey = null;
		Set<String> frozenfoodsProductKeys = frozenFoodsProducts.keySet();

		int totalProducts = frozenfoodsProductKeys.size();
		int i = 1;
		for (String pKey : frozenfoodsProductKeys) {
			Product pd = frozenFoodsProducts.get(pKey);
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
			FrozenFoodsProd pd = frozenFoodsProducts.get(pKey);
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
		Image frozenFoodImage = new Image(StoreConstants.FROZENFOODSDEPT);
		ImageView iv = new ImageView();
		iv.setImage(frozenFoodImage);
		iv.setFitWidth(600);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
		iv.setCache(true);
		HBox ffp = new HBox(iv);
		ffp.setAlignment(Pos.CENTER);

		Scene ffScene = new Scene(ffp, 600, 575);
		// TODO Auto-generated method stub
		return ffScene;
	}
}
