package product;

/**
 * A box of Skittles that can be sold in the vending machine
 * @author nicomp
 *
 */
public class Skittles extends Product {
	/**
	 * Constructor
	 * @param SKU Stock Keeping Unit of the Skittles
	 */
	public Skittles(String SKU, double cost) {
		super(SKU);
		setCost(cost);
	}

	private double cost;
	
	/**
	 * Get the cost of the Skittles
	 * @return the cost of the Skittles
	 */
	@Override
	public double getCost() {
		return cost;
	}

	/**
	 * Set the cost of the Skittles
	 * @param cost the cost of the Skittles
	 */
	@Override
	public void setCost(double cost) {
		this.cost = cost;
	}
	/**
	 * Return a string representation of the object
	 */
	public String toString() {
		return "Skittles $" + getCost();
	}
}
