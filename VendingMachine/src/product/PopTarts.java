package product;

public class PopTarts extends Product {

	public PopTarts(String SKU, double cost) {
		super(SKU);
		setCost(cost);
	}

	private double cost;
	
	/**
	 * Get the cost of the PopTarts
	 * @return the cost of the PopTarts
	 */
	@Override
	public double getCost() {
		return cost;
	}

	/**
	 * Set the cost of the PopTarts
	 * @param cost cost of the Pop Tarts
	 */
	@Override
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	/**
	 * Return a string representation of the object
	 */
	public String toString() {
		return "Pop Tarts $" + getCost();
	}
}
