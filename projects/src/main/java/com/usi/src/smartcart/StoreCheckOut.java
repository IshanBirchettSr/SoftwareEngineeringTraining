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

    public boolean checkoutCustomer(Customer cust, paymentType pt, double money, double moneyTendered) {
	// Get list of productions
	List<Product> custProdList = cust.getListOfProds();
	// send to print
	setProdList(custProdList);
	setCust(cust);
	setValueEnteredCash(moneyTendered);
	setPayType(pt);
	generateReceipt();
	return true;
    }

    public boolean checkoutCustomer(Customer cust, paymentType pt, String card) {
	// Get list of productions
	List<Product> custProdList = cust.getListOfProds();
	// send to print
	setProdList(custProdList);
	setCust(cust);
	setValueEnteredCard(card);
	setPayType(pt);
	generateReceipt();
	return true;
    }

    public boolean checkoutCustomer2(Customer cust, paymentType pt, double money, double moneyTendered) {
	// Get list of productions
	List<Product> custProdList = cust.getListOfProds();
	// send to print
	setProdList(custProdList);
	setCust(cust);
	setValueEnteredCash(moneyTendered);
	setPayType(pt);
	generateReceipt();
	emailReceipt();
	return true;
    }
}
