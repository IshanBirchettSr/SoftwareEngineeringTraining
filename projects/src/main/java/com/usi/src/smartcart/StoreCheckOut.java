package smartcart;

import java.util.List;

import customerservice.Customer;
import util.Product;

/**
 * @author Allma M. Johnson
 *
 */

public class StoreCheckOut extends Receipt {

    /**
     * @param prodCheckout
     */

    public StoreCheckOut() {

    }

    public boolean checkoutCustomer(Customer cust) {
	// Get list of productions
	List<Product> custProdList = cust.getListOfProds();
	// send to print
	setProdList(custProdList);
	printReceipt();
	return true;
    }
}
