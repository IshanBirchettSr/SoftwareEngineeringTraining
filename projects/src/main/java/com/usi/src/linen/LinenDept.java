/**
 * 
 */
package linen;

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
public class LinenDept extends Department {
    String deptName = StoreConstants.deptNames.LINEN.name();
    List<String> linenRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold AutomotiveProd objects.
    HashMap<String, LinenProd> linenProducts;

    /**
     * 
     */
    public LinenDept() {
	super.setDeptName(deptName);
	DataCsvLoad unLoadTrucks = new DataCsvLoad();

	unLoadTrucks.loadData(StoreConstants.LINEN_TRUCK);
	linenRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(linenRecords);
	System.out.printf("%s Department open with %d products\n", deptName, linenRecords.size());
	keyMap = new HashMap<Integer, String>();
	linenProducts = new HashMap<String, LinenProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	for (String record : linenRecords) {
	    LinenProd lp = new LinenProd();
	    boolean recordToProductSuccessful = lp.recordToProduct(record);

	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(lp);
		int howMany = lp.getNumUnitsInstock();
		for (int i = 0; i < howMany; i++) {

		    linenProducts.put(prodKey + 1, lp);
		}

	    }
	    System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		    linenRecords.size(), linenProducts.size());

	    // TODO Auto-generated method stub
	}
    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> linenProductKeys = linenProducts.keySet();

	int totalProducts = linenProductKeys.size();
	int i = 1;
	for (String pKey : linenProductKeys) {
	    Product pd = linenProducts.get(pKey);
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
	    LinenProd pd = linenProducts.get(pKey);
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
