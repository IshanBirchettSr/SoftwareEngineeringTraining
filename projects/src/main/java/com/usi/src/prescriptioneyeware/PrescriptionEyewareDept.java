package prescriptioneyeware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

public class PrescriptionEyewareDept extends Department {
    String deptName = StoreConstants.deptNames.PRESCRIPTION_EYEWARE.name();
    List<String> prescriptioneyewareRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold PrescriptioneyewareProd objects.
    HashMap<String, PrescriptionEyewareProd> prescriptioneyewareProducts;

    /**
     * Constructor
     */
    public PrescriptionEyewareDept() {
	super.setDeptName(deptName);
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

	    // If it fails to convert any field, don't add that object to autoProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(ep);
		int howMany = ep.getQuantity();
		for (int i = 0; i < howMany; i++) {
		    // System.out.println(prodKey);
		    prescriptioneyewareProducts.put(prodKey + i, ep);
		}
		// autoProducts.put(prodKey, ap);

	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		prescriptioneyewareRecords.size(), prescriptioneyewareProducts.size());

    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> prescriptionProductKeys = prescriptioneyewareProducts.keySet();

	int totalProducts = prescriptionProductKeys.size();
	int i = 1;
	for (String pKey : prescriptionProductKeys) {
	    Product pd = prescriptioneyewareProducts.get(pKey);
	    if (aKey != pKey) {
		System.out.printf("%d: %s %s\t%.2f\n", i, pd.getBrandName(), pd.getProductName(), pd.getPrice());
	    }
	    aKey = pKey;
	    keyMap.put(i, pKey);
	    i++;
	}
    }

    public List<Product> getProds(int index, int quantity) {
	ArrayList<Product> pdList = new ArrayList<Product>();
	String pKey = keyMap.get(index);
	for (int i = 0; i < quantity; i++) {
	    PrescriptionEyewareProd pd = prescriptioneyewareProducts.get(pKey);
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
