
package frozenfoods;

import java.util.HashMap;
import java.util.List;

import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.StoreConstants;

public class FrozenFoodsDept extends Department {
    String deptName = StoreConstants.deptNames.FROZEN_FOODS.name();
    List<String> frozenFoodsRecords = null;
// HashMap<K, V> to hold ElectronicsProd objects.
    HashMap<String, FrozenFoodsProd> frozenFoodsProducts;

    /**
     * @return
     * 
     */
    public void FrozenFoodsDept() {
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.FROZEN_FOODS_TRUCK);
	List<String> frozenFoodsRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(frozenFoodsRecords);
	System.out.printf("%s Department open with %d products\n", deptName, frozenFoodsRecords.size());

    }

    @Override
    protected void loadProducts() {
	// TODO Auto-generated method stub
	// Load products
	for (String record : frozenFoodsRecords) {
	    FrozenFoodsProd ffp = new FrozenFoodsProd();
	    boolean recordToProductSuccessful = ffp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to autoProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(ffp);
		frozenFoodsProducts.put(prodKey, ffp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		frozenFoodsRecords.size(), frozenFoodsProducts.size());

    }

}
