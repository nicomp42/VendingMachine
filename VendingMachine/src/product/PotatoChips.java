package product;

/**
 * A bag of potato chips that can be sold in the vending machine
 * @author Bill Nicholson nicholdw@ucmail.uc.edu
 *
 */
public class PotatoChips extends Product {

	private double cost;
	
	/**
	 * Constructor
	 * @param SKU Stock Keeping Unit of the Potato Chips
	 */
	public PotatoChips(String SKU, double cost) {
		super(SKU);
		setCost(cost);
	}

	@Override
	public double getCost() {
		return cost;
	}

	@Override
	public void setCost(double cost) {
		this.cost = cost;
	}
	/**
	 * Return a string representation of the object
	 */
	public String toString() {
		return "Potato Chips $" + getCost();
	}

}
