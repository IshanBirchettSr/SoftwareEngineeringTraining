package books;

import java.util.HashMap;
import java.util.List;

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
    // HashMap<K, V> to hold BookProd objects.
    HashMap<String, BookProd> bookProducts;

    /**
     * Constructor
     */
    public BookDept() {
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.BOOK_TRUCK);
	bookRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(bookRecords);
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

	    // If it fails to convert any field, don't add that object to petcareProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(bp);
		bookProducts.put(prodKey, bp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		bookRecords.size(), bookProducts.size());

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
