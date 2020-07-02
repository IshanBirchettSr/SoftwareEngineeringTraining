package toys;

import java.util.List;

import util.DataCsvLoad;
import util.Department;
import util.StoreConstants;

/**
 * @author amj
 *
 */
public class ToysDept extends Department {
    String deptName = StoreConstants.deptNames.TOYS.name();

    /**
     * 
     */
    public ToysDept() {
	// TODO Auto-generated constructor stub
	DataCsvLoad unLoadTrucks = new DataCsvLoad();

	unLoadTrucks.loadData(StoreConstants.TOYS_TRUCK);
	List<String> loadedRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(loadedRecords);
	System.out.printf("%s Department open with %d products\n", deptName, loadedRecords.size());
    }

    @Override
    protected int loadProduct() {
	// TODO Auto-generated method stub
	return 0;
    }

    // TODO Auto-generated method stub
}
