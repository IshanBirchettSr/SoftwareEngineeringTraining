package util;

public class StoreConstants {
    // Store Name
    public static final String STORE_NAME = "Shoppers Super Store";
    public static final String APP_HOME = "C:/usi-git/SoftwareEngineeringTraining/projects/src/main/java/com/usi";

    // Membership Daily Discount
    public static final int TODAYS_MEMBER_DISCOUNT = 5;
    public static final String VEGETABLE_TRUCK = APP_HOME + "/src/resources/vegetables.txt";

    public static enum deptNames {
	AUTOMOTIVE("automotive"), BABY_ESSENTIALS("babyessentials"), BAKERY("bakery"), BEDDING("bedding"),
	BEVERAGES("beverages"), BIKES("bikes"), BOOKS("books"), CANNED_GOODS("cannedgoods"),
	CLEANING_SUPPLIES("cleaningsupplies"), CLOTHES("clothes"), CUSTOMER_SERVICE("customerservice"), DAIRY("dairy"),
	DELI("deli"), ELECTRONICS("electronics"), EXAMPLES("examples"), FRAGRANCE("fragrance"),
	FROZEN_FOODS("frozenfoods"), GARDEN("garden"), HAIR_CARE("haircare"), HEALTH_AND_BEAUTY("healthandbeauty"),
	HOUSEWARES("housewares"), LINEN("linen"), LUGGAGE("luggage"), MEAT("meat"), PANTRY("pantry"),
	PETCARE("petcare"), PHARMACY("pharmacy"), PRESCRIPTION_EYEWARE("perscriptioneyeware"), PRODUCE("produce"),
	RESOURCES("resources"), SEAFOOD("seafood"), SHOPPING_CART("shoppingcart"), STATIONARY("stationary"),
	TOILETRIES("toiletries"), TOYS("toys"), TUPPERWARE("tupperware"), UTIL("util");

	/*
	 * @param string
	 */
	deptNames(String string) {
	    // TODO Auto-generated constructor stub

	}

	/**
	 * @author Allma M. Johnson
	 *
	 */

	public static enum color {
	    RED("red"), ORANGE("orange"), YELLOW("yellow"), GREEN("green"), BLUE("blue"), INDIGO("indigo"),
	    VIOLET("violet");

	    color(String string) {
		// TODO Auto-generated constructor stub
	    }
	}

	public static enum barcode {
	    CODE_139("code139"), CODE_128_A("code128a"), CODE128_B("code128b"), GS1_128("gs1-128"), UPCA("upca"),
	    UPCE("upce"), EAN13("ean13"), EAN8("ean8");

	    /**
	     * Unable to add a QR code, they are not accepted in Eclipse.
	     */

	    barcode() {
		// TODO Auto-generated constructor stub
	    }

	    barcode(String string) {
		// TODO Auto-generated constructor stub

		/**
		 * "barcode(String string) {" is for a linear brocode.
		 */
	    }
	}

	public static enum numberInStock {
	    AVAILABLE_STOCK("availablestock"), QUANTITY_OF_STOCK("quantityofstock"),
	    CURRENTLY_IN_STOCK("currentlyinstock"), LIMITED_STOCK("limitedstock"),
	    ON_DISPLAY_TO_ORDER("ondisplaytoorder"), OUT_OF_STOCK("outofstock"),
	    REAL_TIME_INVENTORY("realtimeinventory"), ORDERED("ordered"), EXPECTED_IN_STOCK_DATE("expectedinstockdate"),
	    QUANTITIES_OF_10_OR_LESS("quantitiesof10orless");

	    numberInStock(String string) {
		// TODO Auto-generated constructor stub
	    }
	}

	public static enum price {
	    BUNDLE_BULK("bundlebulk"), SLIDING_PRICE("slidingprice"), FLAT_PRICING("flatpricing"),
	    PRICE_POINTS("pricepoints"), DISCOUNTED_PRICE("discountedprice"), METERED_PRICE("meteredprice"),
	    LINE_PRICING("linepricing"), PSYCHOLOGICAL_PRICING("psychologicalpricing"),
	    CUSTOMARY_PRICE("customaryprice"), VARIABLE_PRICE("variableprice"), RECURRING_PRICE("recurringprice"),
	    MULTIDEMENTIONAL_PRICE("multidementionalprice"), CONTENGNACY_PRICE("contengnacyprice"),
	    NEGOTIATED_PRICE("negotiatedprice");

	    price(String string) {
		// TODO Auto-generated constructor stub
	    }
	}

	public static enum quantity {
	    ONE_TO_ONE_HUNDRED_THOUSAND("1-100,000");

	    quantity(String string) {
		// TODO Auto-generated constructor stub
	    }
	}

	public static enum size {
	    DOUBLE_EXTRA_SMALL("XXsmall"), EXTRA_SMALL("extrasmall"), SMALL("small"), MEDIUM("m"), LARGE("large"),
	    EXTRA_LARGE("xl"), DOS_LARGE("xxl"), TRES_LARGE("xxxl"), CUATRO_LARGE("xxxxl"), CINCO_lARGE("xxxxxxl");

	    size(String string) {
		// TODO Auto-generated constructor stub
	    }
	}

	public static enum unitOfMeasure {
	    POUNDS("lbs"), GRAMS("grams"), KILOGRAMS("kg"), MILIGRAMS("mg"), OUNCES("oz"), GALLONS("gal"),
	    FLUID_OUNCES("floz"), CUP("c"), PINTS("pts"), QUARTS("qts"), MILILITER("mil"), LITER("ltr"), TONS("tn");

	    unitOfMeasure(String string) {
		// TODO Auto-generated constructor stub

	    }
	}

	public static enum weight {
	    KILOGRAMS("kg"), TONS("kg"), GRAMS("g"), MACROGRAM("mg"), MICROGRAM("ug"), NANOGRAM("ng"), PICOGRAM("pg"),
	    UK_TON("ukton");

	    weight(String string) {
		// TODO Auto-generated constructor stub
	    }
	}
    }
}
