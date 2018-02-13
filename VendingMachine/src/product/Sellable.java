package product;

/**
 * Things that a product should do in order to be sellable
 * @author Bill Nicholson nicholdw@ucmail.uc.edu
 *
 */
public interface Sellable {
	
	/**
	 * Get the cost of the product
	 * @return Current cost of the product
	 */
	public double getCost();
	/**
	 * Set the cost of the product
	 * @param cost New cost of the product
	 */
	public void setCost(double cost);
	
	
}
