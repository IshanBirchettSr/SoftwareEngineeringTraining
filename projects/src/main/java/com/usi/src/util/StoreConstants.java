package util;

public class StoreConstants {
	// Store Name
	public static final String STORE_NAME = "Shoppers Super Store";
	public static final String APP_HOME = "C:/usi-git/SoftwareEngineeringTraining/projects/src/main/java/com/usi";

	// Membership Daily Discount
	public static final int TODAYS_MEMBER_DISCOUNT = 5;
	public static final String VEGETABLE_TRUCK = APP_HOME + "/src/resources/vegetables.txt";

	public static enum DeptNames {
		AUTOMOTIVE("automotive"), BABY_ESSENTIALS("babyessentials"), BAKERY("bakery"), BEDDING("bedding"),  BEVERAGES("beverages"),
		BIKES("bikes"), BOOKS("books"), CANNED_GOODS("cannedgoods"), CLEANING_SUPPLIES("cleaningsupplies"),
		CLOTHES("clothes"), CUSTOMER_SERVICE("customerservice"), DAIRY("dairy"), DELI("deli"), ELECTRONICS("electronics"), EXAMPLES("examples"), FRAGRANCE("fragrance"), FROZEN_FOODS("frozenfoods"), 
		GARDEN("garden"), HAIR_CARE("haircare"), HEALTH_AND_BEAUTY("healthandbeauty"), HOUSEWARES("housewares"), LINEN("linen"), LUGGAGE("luggage"), MEAT("meat"),
		PANTRY("pantry"), PETCARE("petcare"), PHARMACY("pharmacy"), PRESCRIPTION_EYEWARE("perscriptioneyeware"), PRODUCE("produce"), RESOURCES("resources"), SEAFOOD("seafood"), SHOPPING_CART("shoppingcart"),
		STATIONARY("stationary"), TOILETRIES("toiletries"), TOYS("toys"), TUPPERWARE("tupperware"), UTIL("util");
		
		 * @param string
		 */
		DeptNames(String string) {
			// TODO Auto-generated constructor stub
		
	}
}
