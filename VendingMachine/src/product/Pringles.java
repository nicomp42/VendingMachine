package product;

public class Pringles extends Product {

	
	public Pringles(String SKU, double cost, String flavor) throws Exception {
		super(SKU);
		setCost(cost);
		setFlavor(flavor);
	}

	private double cost;
	private String flavor;
	
	/**
	 * Get the cost of the Pringles
	 * @return the cost of the Pringles
	 */
	@Override
	public double getCost() {
		return cost;
	}

	/**
	 * Set the cost of the Pringles
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
		return getFlavor() + " " + "Pringles $" + getCost();
	}

	/**
	 * get the flavor of the Pringles
	 * @return The flavor
	 */
	public String getFlavor() {
		return flavor;
	}
	/**
	 * Define the flavor of the Pringles
	 * @param flavor The flavor of the Pringles
	 * @throws Exception 
	 */
	public void setFlavor(String flavor) throws Exception {
		if (flavor.trim().length() != 0) {
			this.flavor = flavor;
		} else {
			throw new Exception ("Pringles.setFlavor(): flavor cannot be blank");
		}
	}
}
