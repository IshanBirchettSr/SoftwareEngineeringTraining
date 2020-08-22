package smartcart;

import java.util.List;

import customerservice.Customer;
import util.Product;
import util.StoreConstants.paymentType;

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

    public boolean checkoutCustomer(Customer cust, paymentType pt, String valueTender) {
	// Get list of productions
	List<Product> custProdList = cust.getListOfProds();
	// send to print
	setProdList(custProdList);
	setCust(cust);
	setValueEntered(valueTender);
	setPayType(pt);
	printReceipt();
	return true;
    }
}
