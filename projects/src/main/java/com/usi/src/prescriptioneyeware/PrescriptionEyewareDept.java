package prescriptioneyeware;
import java.util.HashMap;
import java.util.List;
import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

public class PrescriptionEyewareDept extends Department {
    String deptName = StoreConstants.deptNames.PRESCRIPTION_EYEWARE.name();
    List<String> prescriptioneyewareRecords = null;
    // HashMap<K, V> to hold PrescriptioneyewareProd objects.
    HashMap<String, PrescriptionEyewareProd> prescriptioneyewareProducts;   
    /**
     * Constructor
     */
    public PrescriptionEyewareDept() {
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.PRESCRIPTION_EYEWARE_TRUCK);
	List<String> prescriptionEyewareRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(prescriptionEyewareRecords);
	System.out.printf("%s Department open with %d products\n", deptName, prescriptionEyewareRecords.size());
    }

    @Override
    protected void loadProducts() {
	// TODO Auto-generated method stub
    	for (String record : prescriptioneyewareRecords) {
    		PrescriptionEyewareProd ep = new PrescriptionEyewareProd();
    	    boolean recordToProductSuccessful = ep.recordToProduct(record);

    	    if (recordToProductSuccessful == true) {
    		String prodKey = ProdKeyGen.genKey(ep);
    		prescriptioneyewareProducts.put(prodKey, ep);
    	    }
    	}
    	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
    			prescriptioneyewareRecords.size(), prescriptioneyewareProducts.size());

    	}

    @Override
    public void listProducts() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public List<Product> getProducts() {
	// TODO Auto-generated method stub
	return null;
    }
    	}

