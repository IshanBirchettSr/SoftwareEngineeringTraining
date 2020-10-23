package util;

import javafx.scene.layout.Background;

public class StoreConstants {
    // Software Development Company Name
    public static final String SDCN = "USI ";
    // Store Name
    public static final String STORE_NAME = SDCN + "SUPERSTORE";
    public static final String APP_HOME = "C:/usi-git/SoftwareEngineeringTraining/projects/src/main/java/com/usi";

    // Membership Daily Discount
    public static final double TODAYS_MEMBER_DISCOUNT = 0.05;
    public static final double AARP_DISCOUNT = 0.03;

    // State Sales Tax
    public static final double STATE_SALES_TAX = 0.063;

    // USI Product Key Unique Identifier Formatter
    // The following format string consist of the following parameters:
    // Product Name + Unique Bar Code
    public static final String PROD_KEY_FORMATTER = "%s-%s";
    /*
     * //emailPDFs
     * 
     */
    public static final String EMAILPDF = "file:///" + APP_HOME + "/emailReceipt.pdf";
    /*
     * Truck Constants for importing department records Please keep Department
     * Records in alphabetical order
     */

    public static final String AUTOMOTIVE_TRUCK = APP_HOME + "/src/resources/Automotive.txt";
    public static final String BABY_ESSENTIALS_TRUCK = APP_HOME + "/src/resources/BabyEssentials.txt";
    public static final String BAKERY_TRUCK = APP_HOME + "/src/resources/Bakery.txt";
    public static final String BEDDING_TRUCK = APP_HOME + "/src/resources/Bedding.txt";
    public static final String BEVERAGES_TRUCK = APP_HOME + "/src/resources/Beverages.txt";
    public static final String BIKES_TRUCK = APP_HOME + "/src/resources/Bikes.txt";
    public static final String BOOK_TRUCK = APP_HOME + "/src/resources/Books.txt";
    public static final String CANNED_GOODS_TRUCKS = APP_HOME + "/src/resources/CannedGoods.txt";
    public static final String CLEANINGSUPPLIES_TRUCK = APP_HOME + "/src/resources/CleaningSupplies.txt";
    public static final String CLOTHES_TRUCK = APP_HOME + "/src/resources/Clothes.txt";
    public static final String DELI_TRUCK = APP_HOME + "/src/resources/Deli.txt";
    public static final String DAIRY_TRUCK = APP_HOME + "/src/resources/Dairy.txt";
    public static final String ELECTRONICS_TRUCK = APP_HOME + "/src/resources/Electronics.txt";
    public static final String FRAGRANCE_TRUCK = APP_HOME + "/src/resources/Fragrance.txt";
    public static final String FROZEN_FOODS_TRUCK = APP_HOME + "/src/resources/FrozenFoods.txt";
    public static final String FURNITURE_TRUCK = APP_HOME + "/src/resources/Furniture.txt";
    public static final String GARDEN_TRUCK = APP_HOME + "/src/resources/Garden.txt";
    public static final String HAIR_CARE_TRUCK = APP_HOME + "/src/resources/Haircare.txt";
    public static final String HEALTH_AND_BEAUTY_TRUCK = APP_HOME + "/src/resources/HealthandBeauty.txt";
    public static final String HOUSEWARES_TRUCK = APP_HOME + "/src/resources/Housewares.txt";
    public static final String LINEN_TRUCK = APP_HOME + "/src/resources/Linen.txt";
    public static final String LUGGAGE_TRUCK = APP_HOME + "/src/resources/Luggage.txt";
    public static final String MEAT_TRUCK = APP_HOME + "/src/resources/Meat.txt";
    public static final String PANTRY_TRUCK = APP_HOME + "/src/resources/Pantry.txt";
    public static final String PETCARE_TRUCK = APP_HOME + "/src/resources/Petcare.txt";
    public static final String PHARMACY_TRUCK = APP_HOME + "/src/resources/Pharmacy.txt";
    public static final String PLANTBASE_TRUCK = APP_HOME + "/src/resources/PlantBase.txt";
    public static final String PRESCRIPTION_EYEWARE_TRUCK = APP_HOME + "/src/resources/Prescriptioneyeware.txt";
    public static final String PRODUCE_TRUCK = APP_HOME + "/src/resources/Produce.txt";
    public static final String SEAFOOD_TRUCK = APP_HOME + "/src/resources/Seafood.txt";
    public static final String SHOE_TRUCK = APP_HOME + "/src/resources/Shoe.txt";
    public static final String STATIONARY_TRUCK = APP_HOME + "/src/resources/Stationary.txt";
    public static final String TOILETRIES_TRUCK = APP_HOME + "/src/resources/Toiletries.txt";
    public static final String TOYS_TRUCK = APP_HOME + "/src/resources/Toys.txt";
    public static final String TUPPERWARE_TRUCK = APP_HOME + "/src/resources/Tupperware.txt";
    public static final String STYLE1 = "file:///" + APP_HOME + "/src/resources/style.css";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/superstore?allowPublicKeyRetrieval=true&useSSL=false";
    public static final String THING1 = "admin";
    public static final String THING2 = "SuperStore1!1";
    public static final String QUERY1 = "INSERT INTO MEMBERSHIP (first_name, middle_name, last_name, email_address, street_address, city, state, postal_code, phone_number, aarp, date_of_membership)"
	    + " VALUES ( '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s','%s')";
    /*
     * Department Images
     * 
     */
    public static final String STORE_ENTRANCE = "file:///" + APP_HOME + "/images/SuperStoreEntrance.png";
    public static final String STORE_MAP = "file:///" + APP_HOME + "/images/StoreMap.png";
    public static final String MCARD = "file:///" + APP_HOME + "/images/MCard%s.png";
    public static final String COVID_CASHIER = "file:///" + APP_HOME + "/images/covid_cashier.png";
    public static final String VIEW_CART = "file:///" + APP_HOME + "/images/ShoppingCart.png";
    public static final String SC_ICON_EMPTY = "file:///" + APP_HOME + "/images/sc3.png";
    public static final String SC_ICON_FULL = "file:///" + APP_HOME + "/images/sc1.png";
    public static final String SC_ICON_VISA = "file:///" + APP_HOME + "/images/visa.png";
    public static final String SC_ICON_MASTERCARD = "file:///" + APP_HOME + "/images/mastercard.png";
    public static final String SC_ICON_EBT = "file:///" + APP_HOME + "/images/ebt.png";
    public static final String SC_ICON_CASH = "file:///" + APP_HOME + "/images/cash.png";
    public static final String SC_ICON_PAY = "file:///" + APP_HOME + "/images/payIcon.png";
    public static final String SC_ICON_CANCEL = "file:///" + APP_HOME + "/images/cancel_icon.png";
    public static final String AUTOMOTIVEDEPT = "file:///" + APP_HOME + "/images/Automotive_Dept.png";
    public static final String BABYESSENTIALSDEPT = "file:///" + APP_HOME + "/images/BabyEssentials_Dept.png";
    public static final String BAKERYDEPT = "file:///" + APP_HOME + "/images/Bakery_Dept.png";
    public static final String BEDDINGDEPT = "file:///" + APP_HOME + "/images/Bedding_Dept.png";
    public static final String BEVERAGESDEPT = "file:///" + APP_HOME + "/images/Beverages_Dept.png";
    public static final String BIKESDEPT = "file:///" + APP_HOME + "/images/Bikes_Dept.png";
    public static final String BOOKSDEPT = "file:///" + APP_HOME + "/images/Books_Dept.png";
    public static final String CANNEDGOODSDEPT = "file:///" + APP_HOME + "/images/Cannedgoods_Dept.png";
    public static final String CAUTION = "file:///" + APP_HOME + "/images/CautionTape.png";
    public static final String CLEANINGSUPPLIESDEPT = "file:///" + APP_HOME + "/images/CleaningSupplies_Dept.png";
    public static final String CLOTHESDEPT = "file:///" + APP_HOME + "/images/Clothes_Dept.png";
    public static final String DAIRYDEPT = "file:///" + APP_HOME + "/images/Dairy_Dept.png";
    public static final String DELIDEPT = "file:///" + APP_HOME + "/images/Deli_Dept.png";
    public static final String DEPTCLOSED = "file:///" + APP_HOME + "/images/DepartmentClosed.png";
    public static final String ELECTRONICSDEPT = "file:///" + APP_HOME + "/images/Electronics_Dept.png";
    public static final String FRAGRANCEDEPT = "file:///" + APP_HOME + "/images/Fragrance_Dept.png";
    public static final String FROZENFOODSDEPT = "file:///" + APP_HOME + "/images/Frozenfoods_Dept.png";
    public static final String FURNITUREDEPT = "file:///" + APP_HOME + "/images/Furniture_Dept.png";
    public static final String GARDENDEPT = "file:///" + APP_HOME + "/images/Garden_Dept.png";
    public static final String HAIRCAREDEPT = "file:///" + APP_HOME + "/images/Hair_Care_Dept.png";
    public static final String HEALTHANDBEAUTYDEPT = "file:///" + APP_HOME + "/images/Health_and_beauty_Dept.png";
    public static final String HOUSEWARESDEPT = "file:///" + APP_HOME + "/images/Housewares_Dept.png";
    public static final String LINENDEPT = "file:///" + APP_HOME + "/images/Linen_Dept.png";
    public static final String LUGGAGEDEPT = "file:///" + APP_HOME + "/images/Luggage_Dept.png";
    public static final String MEATDEPT = "file:///" + APP_HOME + "/images/Meat_Dept.png";
    public static final String PANTRYDEPT = "file:///" + APP_HOME + "/images/Pantry_Dept.png";
    public static final String PETCAREDEPT = "file:///" + APP_HOME + "/images/Petcare_Dept.png";
    public static final String PHARMACYDEPT = "file:///" + APP_HOME + "/images/Pharmacy_Dept.png";
    public static final String PLANTBASEDEPT = "file:///" + APP_HOME + "/images/PlantBase_Dept.png";
    public static final String PRESCRIPTIONEYEWAREDEPT = "file:///" + APP_HOME + "/images/PrescriptionEyeware_Dept.png";
    public static final String PRODUCEDEPT = "file:///" + APP_HOME + "/images/Produce_Dept.png";
    public static final String SEAFOODDEPT = "file:///" + APP_HOME + "/images/Seafood_Dept.png";
    public static final String SHOEDEPT = "file:///" + APP_HOME + "/images/Shoe_Dept.png";
    public static final String STATIONARYDEPT = "file:///" + APP_HOME + "/images/Stationary_Dept.png";
    public static final String TOILETRIESDEPT = "file:///" + APP_HOME + "/images/Toiletry_Dept.png";
    public static final String TOYSDEPT = "file:///" + APP_HOME + "/images/Toys_Dept.png";
    public static final String TUPPERWAREDEPT = "file:///" + APP_HOME + "/images/Tupperware_Dept.png";
    public static final String CASH_REGISTER = "file:///" + APP_HOME + "/images/Cashregister.png";
    /*
     * Product Image
     */
    public static final String PRODUCT_IMAGE = "file:///" + APP_HOME + "/images" + "/%s_prod_%s_%s.png";
    public static final String PRODUCT_VIDEO = "file:///" + APP_HOME + "/images" + "/%s_prod_%s_%s.mp4";
    public static final String PRODUCT_VIDEO_Check = APP_HOME + "/images" + "/%s_prod_%s_%s.mp4";

    /*
     * New Member Profile Photos
     */
    public static final String MEMBERSHIP_PROFILE_IMAGE = "./images/profile_images/profile_%s.png";
    public static final String NEW_MEMBERSHIP_PROFILE_IMAGE = "file:///" + APP_HOME
	    + "/images/profile_images/profile_%s.png";
    public static final String MEMBERSHIP_DEFAULT_PROFILE_IMAGE = "file:///" + APP_HOME
	    + "/images/profile_images/smile4camera2.png";
    public static final String CAMERA_CLICK_SOUND = "file:///" + APP_HOME + "/images/camera_click.mp3";
    public static final String MEMBERSHIPCARD_RECORDS = APP_HOME + "/src/resources/MembershipCard.txt";
    public static final String MEMBERSHIPCARD_RECORD_FORMATTER = "\n%s, %c, %s, %s, %s, %s, %s, %s, %s, %s, %s";

    /*
     * Please keep deptNames in alphabetical order
     */
    public static enum deptNames {
	AUTOMOTIVE("automotive"), BABY_ESSENTIALS("babyessentials"), BAKERY("bakery"), BEDDING("bedding"),
	BEVERAGES("beverages"), BIKES("bikes"), BOOKS("books"), CANNED_GOODS("cannedgoods"),
	CLEANING_SUPPLIES("cleaningsupplies"), CLOTHES("clothes"), CUSTOMER_SERVICE("customerservice"), DAIRY("dairy"),
	DELI("deli"), ELECTRONICS("electronics"), FRAGRANCE("fragrance"), FROZEN_FOODS("frozenfoods"),
	FURNITURE("furniture"), GARDEN("garden"), HAIR_CARE("haircare"), HEALTH_AND_BEAUTY("healthandbeauty"),
	HOUSEWARES("housewares"), LINEN("linen"), LUGGAGE("luggage"), MEAT("meat"), PANTRY("pantry"),
	PETCARE("petcare"), PHARMACY("pharmacy"), PLANTBASE("plantbase"), PRESCRIPTION_EYEWARE("perscriptioneyeware"),
	PRODUCE("produce"), SEAFOOD("seafood"), SHOE("shoe"), SHOPPING_CART("shoppingcart"), STATIONARY("stationary"),
	TOILETRIES("toiletries"), TOYS("toys"), TUPPERWARE("tupperware");

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
     * @author Allma M. Johnson
     *
     */

    public static enum color {
	RED("red"), ORANGE("orange"), YELLOW("yellow"), GREEN("green"), BLUE("blue"), INDIGO("indigo"),
	VIOLET("violet"), BLACK("black"), MULTI_COLOR("multi-color"), MOCHA("mocha"), NAVY("navy"), PINK("pink"),
	WHITE("white"), BEIGE("beige"), BROWN("brown"), TEAL("teal"), GOLD("gold"), SILVER("silver"), PEWTER("pewter"),
	ECRU("ecru"), COFFEE("coffee"), MAGENTA("magenta"), MARBLE("marble"), GRAY("gray"), SALMON("salmon"),
	PURPLE("purple"), TAN("tan"), BRONZE("bronze"), NA("na"), CREME("creme"), CLEAR("clear"), TORTOISE("tortoise"),
	LEOPARD("leopard");

	color(String string) {

	}
    }

    public static enum barcode {
	CODE_139("code139"), CODE_128_A("code128a"), CODE128_B("code128b"), GS1_128("gs1-128"), UPCA("upca"),
	UPCE("upce"), EAN13("ean13"), EAN8("ean8");

	/**
	 * Unable to add a QR code, they are not accepted in Eclipse.
	 */

	barcode() {

	}

	barcode(String string) {

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

	}
    }

    public static enum quantity {
	ONE_TO_ONE_HUNDRED_THOUSAND("1-100,000");

	quantity(String string) {

	}
    }

    public static enum size {
	DOUBLE_EXTRA_SMALL("xxsmall"), EXTRA_SMALL("extrasmall"), SMALL("small"), MEDIUM("m"), LARGE("large"),
	EXTRA_LARGE("xl"), DOS_LARGE("xxl"), TRES_LARGE("xxxl"), CUATRO_LARGE("xxxxl"), CINCO_lARGE("xxxxxl"),
	LOAF("loaf"), NA("na");

	size(String string) {

	}
    }

    public static enum unitOfMeasure {
	POUNDS("lbs"), GRAMS("grams"), KILOGRAMS("kg"), MILIGRAMS("mg"), OUNCES("oz"), GALLONS("gal"),
	FLUID_OUNCES("floz"), CUP("c"), COUNT("ct"), PINTS("pts"), QUARTS("qts"), MILILITER("mil"), LITER("ltr"),
	TONS("tn"), NA("na");

	unitOfMeasure(String string) {

	}
    }

    public static enum weight {
	KILOGRAMS("kg"), TONS("kg"), GRAMS("g"), MACROGRAM("mg"), MICROGRAM("ug"), NANOGRAM("ng"), PICOGRAM("pg"),
	UK_TON("ukton"), NA("na");

	weight(String string) {

	}
    }

    public static enum paymentType {
	VISA("visacard"), MASTERCARD("mastercard"), EBT("ebtcard"), CASH("cash");

	/**
	 * @param string
	 */
	paymentType(String string) {
	    // TODO Auto-generated constructor stub
	}
    }
}