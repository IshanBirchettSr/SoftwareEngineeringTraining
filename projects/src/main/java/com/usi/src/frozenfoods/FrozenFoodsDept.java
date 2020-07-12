
package frozenfoods;
import java.util.HashMap;
import java.util.List;
import util.DataCsvLoad;
import util.Department;
import util.StoreConstants;

public class FrozenFoodsDept extends Department {
	String deptName = StoreConstants.deptNames.FROZEN_FOODS.name();
	List<String> electronicsRecords = null;
// HashMap<K, V> to hold ElectronicsProd objects.
	HashMap<String, FrozenFoodsProd> FrozenFoodsProducts;

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

    }
}
