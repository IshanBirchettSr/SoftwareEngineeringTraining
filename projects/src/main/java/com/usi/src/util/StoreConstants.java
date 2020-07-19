package util;

public class StoreConstants {
    /**
     * @author ibirc
     *
     */

    // Software Development Company Name
    public static final String SDCN = "usi";
    // Store Name
    public static final String STORE_NAME = "Shoppers Super Store";
    public static final String APP_HOME = "C:/usi-git/SoftwareEngineeringTraining/projects/src/main/java/com/usi";

    // Membership Daily Discount
    public static final int TODAYS_MEMBER_DISCOUNT = 5;

    // USI Product Key Unique Identifier Formatter
    // The following format string consist of the following parameters:
    // SDCN + Department Enum + Band Name + Product Name + Weight + Color Enum
    public static final String PROD_KEY_FORMATTER = SDCN + "::%s:%s:%s:%s:%s:%s";

    // Truck Constants for importing department records
    public static final String VEGETABLE_TRUCK = APP_HOME + "/src/resources/vegetables.txt";
    public static final String AUTOMOTIVE_TRUCK = APP_HOME + "/src/resources/Automotive.txt";
    public static final String LINEN_TRUCK = APP_HOME + "/src/resources/Linen.txt";
    public static final String ELECTRONICS_TRUCK = APP_HOME + "/src/resources/Electronics.txt";
    public static final String HAIR_CARE_TRUCK = APP_HOME + "/src/resources/Haircare.txt";
    public static final String PRESCRIPTION_EYEWARE_TRUCK = APP_HOME + "/src/resources/Prescriptioneyeware.txt";
    public static final String TOYS_TRUCK = APP_HOME + "/src/resources/Toys.txt";
    public static final String GARDEN_TRUCK = APP_HOME + "/src/resources/Garden.txt";
    public static final String FROZEN_FOODS_TRUCK = APP_HOME + "/src/resources/FrozenFoods.txt";
    public static final String BAKERY_TRUCK = APP_HOME + "/src/resources/Bakery.txt";
    public static final String BABY_ESSENTIALS_TRUCK = APP_HOME + "/src/resources/BabyEssentials.txt";
    public static final String CANNED_GOODS_TRUCKS = APP_HOME + "/src/resources/CannedGoods.txt";
    public static final String DELI_TRUCK = APP_HOME + "/src/resources/Deli.txt";
    public static final String DAIRY_TRUCK = APP_HOME + "/src/resources/Dairy.txt";
    public static final String FRAGRANCE_TRUCK = APP_HOME + "/src/resources/Fragrance.txt";
    public static final String LUGGAGE_TRUCK = APP_HOME + "/src/resources/Luggage.txt";
    public static final String PRODUCE_TRUCK = APP_HOME + "/src/resources/Produce.txt";
    public static final String PETCARE_TRUCK = APP_HOME + "/src/resources/Petcare.txt";
    public static final String PANTRY_TRUCK = APP_HOME + "/src/resources/Pantry.txt";
    public static final String BOOK_TRUCK = APP_HOME + "/src/resources/Books.txt";
    public static final String BEVERAGES_TRUCK = APP_HOME + "/src/resources/Beverages.txt";
    public static final String STATIONARY_TRUCK = APP_HOME + "/src/resources/Stationary.txt";
    public static final String TOILETRIES_TRUCK = APP_HOME + "/src/resources/Toiletries.txt";
<<<<<<< Updated upstream
    public static final String BEDDING_TRUCK = APP_HOME + "/src/resources/Bedding.txt";
	
=======
    public static final String TOYS_TRUCK = APP_HOME + "/src/resources/Toys.txt";
    public static final String TUPPERWARE_TRUCK = APP_HOME + "/src/resources/Tupperware.txt";
>>>>>>> Stashed changes

    private String dName;

    public static enum deptNames {
	AUTOMOTIVE("automotive"), BABY_ESSENTIALS("babyessentials"), BAKERY("bakery"), BEDDING("bedding"),
	BEVERAGES("beverages"), BIKES("bikes"), BOOKS("books"), CANNED_GOODS("cannedgoods"),
	CLEANING_SUPPLIES("cleaningsupplies"), CLOTHES("clothes"), CUSTOMER_SERVICE("customerservice"), DAIRY("dairy"),
	DELI("deli"), ELECTRONICS("electronics"), FRAGRANCE("fragrance"), FROZEN_FOODS("frozenfoods"), GARDEN("garden"),
	HAIR_CARE("haircare"), HEALTH_AND_BEAUTY("healthandbeauty"), HOUSEWARES("housewares"), LEOPARD("leopard"),
	LINEN("linen"), LUGGAGE("luggage"), MEAT("meat"), PANTRY("pantry"), PETCARE("petcare"), PHARMACY("pharmacy"),
	PLANTBASE("plantbase"), PRESCRIPTION_EYEWARE("perscriptioneyeware"), PRODUCE("produce"), SEAFOOD("seafood"),
	SHOPPING_CART("shoppingcart"), STATIONARY("stationary"), TOILETRIES("toiletries"), TORTOISE("tortoise"),
	TOYS("toys"), TUPPERWARE("tupperware");

	private String dName;

	/*
	 * @param string inDept
	 */
	deptNames(String inDept) {
	    this.dName = inDept;

	}

	public String getDept() {
	    return dName;
	}
    }

    /**
     * @author amj
     *
     */

    public static enum color {
	RED("red"), ORANGE("orange"), YELLOW("yellow"), GREEN("green"), BLUE("blue"), INDIGO("indigo"),
	VIOLET("violet"), BLACK("black"), MULTI_COLOR("multi-color"), MOCHA("mocha"), NAVY("navy"), PINK("pink"),
	WHITE("white"), BEIGE("beige"), BROWN("brown"), TEAL("teal"), GOLD("gold"), SILVER("silver"), PEWTER("pewter"),
	ECRU("ecru"), COFFEE("coffee"), MAGENTA("magenta"), MARBLE("marble"), GRAY("gray"), SALMON("salmon"),
	PURPLE("purple"), TAN("tan"), BRONZE("bronze"), NA("na"), CREME("creme"), CLEAR("clear");

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
	     * "barcode(String string) {" is for a linear barcode.
	     */
	}
    }

    public static enum numberInStock {
	AVAILABLE_STOCK("availablestock"), QUANTITY_OF_STOCK("quantityofstock"), CURRENTLY_IN_STOCK("currentlyinstock"),
	LIMITED_STOCK("limitedstock"), ON_DISPLAY_TO_ORDER("ondisplaytoorder"), OUT_OF_STOCK("outofstock"),
	REAL_TIME_INVENTORY("realtimeinventory"), ORDERED("ordered"), EXPECTED_IN_STOCK_DATE("expectedinstockdate"),
	QUANTITIES_OF_10_OR_LESS("quantitiesof10orless");

	numberInStock(String string) {
	    // TODO Auto-generated constructor stub
	}
    }

    public static enum price {
	BUNDLE_BULK("bundlebulk"), SLIDING_PRICE("slidingprice"), FLAT_PRICING("flatpricing"),
	PRICE_POINTS("pricepoints"), DISCOUNTED_PRICE("discountedprice"), METERED_PRICE("meteredprice"),
	LINE_PRICING("linepricing"), PSYCHOLOGICAL_PRICING("psychologicalpricing"), CUSTOMARY_PRICE("customaryprice"),
	VARIABLE_PRICE("variableprice"), RECURRING_PRICE("recurringprice"),
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
	DOUBLE_EXTRA_SMALL("xxsmall"), EXTRA_SMALL("extrasmall"), SMALL("small"), MEDIUM("m"), LARGE("large"),
	EXTRA_LARGE("xl"), DOS_LARGE("xxl"), TRES_LARGE("xxxl"), CUATRO_LARGE("xxxxl"), CINCO_lARGE("xxxxxl"),
	LOAF("loaf"), NA("na");

	size(String string) {
	    // TODO Auto-generated constructor stub
	}
    }

    public static enum unitOfMeasure {
	POUNDS("lbs"), GRAMS("grams"), KILOGRAMS("kg"), MILIGRAMS("mg"), OUNCES("oz"), GALLONS("gal"),
	FLUID_OUNCES("floz"), CUP("c"), PINTS("pts"), QUARTS("qts"), MILILITER("mil"), LITER("ltr"), TONS("tn"),
	NA("na");

	unitOfMeasure(String string) {
	    // TODO Auto-generated constructor stub

	}
    }

    public static enum weight {
	KILOGRAMS("kg"), TONS("kg"), GRAMS("g"), MACROGRAM("mg"), MICROGRAM("ug"), NANOGRAM("ng"), PICOGRAM("pg"),
	UK_TON("ukton"), NA("na");

	weight(String string) {
	    // TODO Auto-generated constructor stub
	}
    }
}