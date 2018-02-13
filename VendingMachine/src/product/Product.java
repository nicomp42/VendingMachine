package product;

/**
 * Base class for all products sold in the vending machine
 * @author Bill Nicholson nicholdw@ucmail.uc.edu
 *
 */
public abstract class Product implements Sellable {
	private String SKU;
	
	/**
	 * Constructor
	 * @param SKU The SKU of the product
	 */
	public Product(String SKU) {
		this.SKU = SKU;
	}
	/**
	 * Get the SKU
	 * @return The SKU of the product
	 */
	public String getSKU() {return SKU;}
	
	/**
	 * Convert the product to a String
	 * @return The String representation of the product
	 */
	public String toString() {
		return getSKU();
	}
}
