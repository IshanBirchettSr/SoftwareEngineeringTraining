package books;

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
 * @author chich
 *
 */

public class BookDept extends Department {

    String deptName = StoreConstants.deptNames.BOOKS.name();
    List<String> bookRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold BookProd objects.
    HashMap<String, BookProd> bookProducts = null;

    /**
     * Constructor
     */
    public BookDept() {
	super.setDeptName(deptName);
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.BOOK_TRUCK);
	bookRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(bookRecords);
	keyMap = new HashMap<Integer, String>();
	// System.out.printf("%s Department open with %d records\n", deptName,
	// bookRecords.size());

	// Book Product Load
	bookProducts = new HashMap<String, BookProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : bookRecords) {
	    BookProd bp = new BookProd();
	    boolean recordToProductSuccessful = bp.recordToProduct(record);
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(bp);
		int howMany = bp.getNumUnitsInstock();
		for (int i = 0; i < howMany; i++) {
		    // System.out.println(prodKey);
		    bookProducts.put(prodKey + i, bp);
		}
		// autoProducts.put(prodKey, ap);

	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		bookRecords.size(), bookProducts.size());

    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> bookProductKeys = bookProducts.keySet();

	int totalProducts = bookProductKeys.size();
	int i = 1;
	for (String pKey : bookProductKeys) {
	    Product pd = bookProducts.get(pKey);
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
	    BookProd pd = bookProducts.get(pKey);
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
