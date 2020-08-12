package cannedgoods;

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
 * @author chich
 *
 */

public class CannedGoodsDept extends Department {
    String deptName = StoreConstants.deptNames.CANNED_GOODS.name();
    List<String> cannedGoodsRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold AutomotiveProd objects.
    HashMap<String, CannedGoodsProd> cannedGoodsProducts;

    /**
     * Constructor
     */
    public CannedGoodsDept() {
	super.setDeptName(deptName);
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.CANNED_GOODS_TRUCKS);
	cannedGoodsRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(cannedGoodsRecords);
	keyMap = new HashMap<Integer, String>();
	// System.out.printf("%s Department open with %d records\n", deptName,
	// autoRecords.size());

	// Automotive Product Load
	cannedGoodsProducts = new HashMap<String, CannedGoodsProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : cannedGoodsRecords) {
	    CannedGoodsProd cp = new CannedGoodsProd();
	    boolean recordToProductSuccessful = cp.recordToProduct(record);

	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(cp);
		int howMany = cp.getNumUnitsInstock();
		for (int i = 0; i < howMany; i++) {
		    // System.out.println(prodKey);
		    cannedGoodsProducts.put(prodKey + i, cp);
		}
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		cannedGoodsRecords.size(), cannedGoodsProducts.size());

    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> cannedProductKeys = cannedGoodsProducts.keySet();

	int totalProducts = cannedProductKeys.size();
	int i = 1;
	for (String pKey : cannedProductKeys) {
	    Product pd = cannedGoodsProducts.get(pKey);
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
	    CannedGoodsProd pd = cannedGoodsProducts.get(pKey);
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
	Image cannedgoodsImage = new Image(StoreConstants.CANNEDGOODSDEPT);
	ImageView iv = new ImageView();
	iv.setImage(cannedgoodsImage);
	iv.setFitWidth(600);
	iv.setPreserveRatio(true);
	iv.setSmooth(true);
	iv.setCache(true);
	HBox furp = new HBox(iv);
	furp.setAlignment(Pos.CENTER);

	Scene cgdScene = new Scene(furp, 600, 575);
	// TODO Auto-generated method stub
	return cgdScene;
    }
}
