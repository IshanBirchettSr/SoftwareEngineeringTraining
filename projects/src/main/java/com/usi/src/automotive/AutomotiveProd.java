/**
 * 
 */
package automotive;

import util.Product;
import util.StoreConstants;
import util.StoreConstants.deptNames;

/**
 * @author Allma M. Johnson
 *
 */
public class AutomotiveProd extends Product {

    /**
     * 
     */
    public AutomotiveProd() {
	// TODO Auto-generated constructor stub
    }

    /*
     * ProductName , Department , UnitOfMeasure , Barcode , Quantity , Size , Brand
     * , Weight , Price , Color , Ingredient , NumUnitsInstock , Description
     * /*Automotive/Tires private String productName; private deptNames
     * deptNameEnum; private unitOfMeasure unitOfMeasureEnum; private String
     * barCode; private int quantity; private util.StoreConstants.size sizeEnum;
     * private String bandName; private weight weightEnum; private double price;
     * private util.StoreConstants.color colorEnum; private String ingredient;
     * private int numUnitsInstock; private String description;
     */

    @Override
    public boolean recordToProduct(String record) {
	// Records
	String[] fields = record.split(",");
	this.setProductName(fields[0].toString());
	String dName = fields[1].toString().trim();
	// deptNames compName = Enum.valueOf(deptNames.class, dName.toUpperCase());
	// this.setDeptNameEnum(compName);
	this.setDeptNameEnum(deptNames.valueOf(dName.toUpperCase()));
	this.setUnitOfMeasureEnum(StoreConstants.unitOfMeasure.valueOf(fields[2].trim()));
	String barCode = util.RandomUUID.getUuidString();
	this.setBarCode(barCode);
	int quantity = Integer.parseInt(fields[4].trim());
	this.setQuantity(quantity);
	this.setSize(fields[5].trim());
	this.setBrandName(fields[6].trim());
	this.setWeightEnum(StoreConstants.weight.valueOf(fields[7].trim()));
	double price = Double.parseDouble(fields[8].toString());
	this.setPrice(price);
	this.setColorEnum(StoreConstants.color.valueOf(fields[9].trim()));
	this.setIngredient(fields[10].toString());
	int units = Integer.parseInt(fields[11].toString());
	this.setNumUnitsInstock(units);
	this.setDescription(fields[12]);
	return true;
    }
}
