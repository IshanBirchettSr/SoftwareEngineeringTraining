/**
 * 
 */
package linen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

/**
 * @author Allma M. Johnson and Chich
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

	@Override
	public Scene getScene() {
		String imageKey = String.format("Welcome to the Linen Department!");
		Text welcomeTxt = new Text(imageKey);
		welcomeTxt.setText(imageKey);
		welcomeTxt.setX(50.00);
		welcomeTxt.setY(80.00);
		welcomeTxt.setFill(Color.BLUE);
		welcomeTxt.setFont(Font.font("Verdana", FontPosture.REGULAR, 20));
		HBox lg = new HBox(20, welcomeTxt);
		lg.setAlignment(Pos.TOP_CENTER);

		Image linenImage = new Image(StoreConstants.LINENDEPT);
		ImageView iv = new ImageView();
		iv.setImage(linenImage);
		iv.setFitWidth(600);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
		iv.setCache(true);
		HBox lp = new HBox(iv);
		lp.setAlignment(Pos.CENTER);

		VBox lBox = new VBox(20, lg, iv);

		String goIn = String.format("Would you like to shop the Linen Department?");
		Text shopTxt = new Text(goIn);
		shopTxt.setText(goIn);
		shopTxt.setX(50.00);
		shopTxt.setY(80.00);
		shopTxt.setFill(Color.BLUE);
		shopTxt.setFont(Font.font("Rockwell", FontPosture.REGULAR, 20));

		Label comeIn = new Label(goIn);
		comeIn.setAlignment(Pos.BOTTOM_CENTER);

		Button Enter = new Button("YES!");
		EventHandler<ActionEvent> yesEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				System.out.println("Welcome!");

			}
		};
		Enter.setOnAction(yesEvent);

		Button noIDoNot = new Button("Next Department Please");
		Enter.setAlignment(Pos.BOTTOM_CENTER);
		noIDoNot.setAlignment(Pos.BOTTOM_CENTER);
		EventHandler<ActionEvent> noEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("No");

			}
		};

		noIDoNot.setOnAction(noEvent);

		HBox paneCharacter = new HBox(20, comeIn, Enter, noIDoNot);
		paneCharacter.setPadding(new Insets(10));
		// Add the Character and Actor panes to a VBox
		VBox el = new VBox(10, lp, paneCharacter);
		el.setAlignment(Pos.CENTER);

		lBox.getChildren().add(paneCharacter);

		Scene lScene = new Scene(lBox, 600, 575);

		// TODO Auto-generated method stub
		return lScene;
	}
}
