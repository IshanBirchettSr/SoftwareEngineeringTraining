package util;

public class StoreConstants {
	// Store Name
	public static final String STORE_NAME = "Shoppers Super Store";
	public static final String APP_HOME = "C:/usi-git/SoftwareEngineeringTraining/projects/src/main/java/com/usi";

	// Membership Daily Discount
	public static final int TODAYS_MEMBER_DISCOUNT = 5;
	public static final String VEGETABLE_TRUCK = APP_HOME + "/src/resources/vegetables.txt";

	public static enum DeptNames {
		AUTOMOTIVE("automotive"), BABY_ESSENTIALS("babyessentials"), BAKERY("Bakery"), BEDDING("Bedding"),
		BIKES("bikes"), BOOKS("Books"), CANNED_GOODS("cannedgoods"), CLEANING_SUPPLIES("cleaningsupplies"),
		CLOTHES("clothes"), CUSTOMER_SERVICE("customerservice"), DAIRY("dairy");

		/**
		 * @param string
		 */
		DeptNames(String string) {
			// TODO Auto-generated constructor stub
		}
	}
}
