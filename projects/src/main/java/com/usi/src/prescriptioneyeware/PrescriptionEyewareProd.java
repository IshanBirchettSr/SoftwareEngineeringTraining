package prescriptioneyeware;

import util.Product;
import util.StoreConstants;
import util.StoreConstants.deptNames;

	/**
	 * 
	 */
public class PrescriptionEyewareProd extends Product{
	
	public void PrescriptioneyewareProd() {
		// TODO Auto-generated constructor stub
	}

	@Override
		public boolean recordToProduct(String record) {try {
		    System.out.println(record);
		    String[] fields = record.split(",");
		    this.setProductName(fields[0].toString());
		    String dName = fields[1].toString().trim();
		    this.setDeptNameEnum(deptNames.valueOf(dName.toUpperCase().trim()));
		    this.setUnitOfMeasureEnum(StoreConstants.unitOfMeasure.valueOf(fields[2].trim()));
		    String barCode = util.RandomUUID.getUuidString();
		    this.setBarCode(barCode);
		    int quantity = Integer.parseInt(fields[4].trim());
		    this.setQuantity(quantity);
		    this.setSize(fields[5].trim());
		    this.setBrandName(fields[6].trim());
		    this.setWeight(fields[7].trim());
		    double price = Double.parseDouble(fields[8].toString());
		    this.setPrice(price);
		    this.setColorEnum(StoreConstants.color.valueOf(fields[9].trim()));
		    this.setIngredient(fields[10].trim());
		    int units = Integer.parseInt(fields[11].trim());
		    this.setNumUnitsInstock(units);
		    this.setDescription(fields[12].trim());
		    return true;
		} catch (IllegalArgumentException e) {
		    e.printStackTrace();
		    return false;
		}

}
}
