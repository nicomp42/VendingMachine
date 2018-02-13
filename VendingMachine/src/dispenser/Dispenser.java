package dispenser;
import java.util.ArrayList;
import product.Product;

/**
 * Product dispenser, part of a vending machine
 * @author Bill Nicholson nicholdw@ucmail.uc.edu
 *
 */
public class Dispenser {
	private ArrayList<Product> products;		// has-a relationship
	private int row, column;
	private static final int maxProducts = 10; 
	/**
	 * Constructor.
	 * The upper left dispenser in a vending machine is logical row 0, column 0.
	 * @param row The row in the vending machine where the dispenser is. 0-based number.
	 * @param column The column in the vending machine where the dispenser is. 0-based number.
	 * @throws Exception 
	 */
	public Dispenser(int row, int column) throws Exception {
		if (row >= 0 && column >= 0) {
			this.setRow(row);
			this.setColumn(column);
			products = new ArrayList<Product>();
		} else {
			throw new Exception ("Dispener.Dispenser: row and column must be >= 0");
		}
	}

	/**
	 * Add a product to the dispenser
	 * @param product The Product to be added
	 * @throws Exception if dispenser is full and the product cannot be added to it
	 */
	public void addProduct(Product product) throws Exception {
		if (products.size() < maxProducts) {
			products.add(product);
		} else {
			throw new Exception ("Dispenser.addProduct(): dispenser is full.");
		}
	}
	/**
	 * Get the next product waiting to be dispensed
	 * @return The next Product to be dispensed, or null if none. It is also removed from the Dispenser
	 */
	public Product dispenseProduct() {
		Product product = null;
		int idx = products.size() - 1;
		if (idx > -1) {
			product = products.get(idx);
			products.remove(idx);
		}
		return product;
	}
	/**
	 * Peek at the next product that will be dispensed. It is not actually dispensed.
	 * @return The next product to be dispensed, or null if none.
	 */
	public Product getNextProduct() {
		Product product = null;
		int idx = products.size() - 1;
		if (idx > -1) {
			product = products.get(idx); 
		}
		return product;
	}
	/**
	 * @return The String representation of the Dispenser
	 */
	public String toString() {
		return products.toString();
	}

	/**
	 * Get the row of the Dispenser
	 * @return The row. 1-based number
	 */
	public int getRow() {
		return row;
	}
	/**
	 * Get the column of the Dispenser
	 * @return The column
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * Set the row of the Dispenser 
	 * @param row 1-based row number
	 */
	private void setRow(int row) {
		this.row = row;
	}
	/**
	 * Set the column of the dispenser
	 * @param column 1-based column number
	 */
	private void setColumn(int column) {
		this.column = column;
	}
}
