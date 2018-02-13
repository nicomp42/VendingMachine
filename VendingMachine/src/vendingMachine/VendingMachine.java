package vendingMachine;

import java.util.ArrayList;

import dispenser.Dispenser;
import money.MoneyAmount;
import moneyProcessor.MoneyProcessor;
import product.Product;
/**
 * Model a Vending Machine
 * @author Bill Nicholson nicholdw@ucmail.uc.edu
 *
 */
public class VendingMachine {
	
	private MoneyProcessor moneyProcessor;
	private ArrayList<Dispenser> dispensers;
	private int columnsPerRow;
	
	/**
	 * Constructor
	 * @param numberOfDispensers total number of Dispenser objects in the vending machine
	 * @param nickels number of nickels in the machine
	 * @param dimes number of dimes in the machine
	 * @param quarters number of quarters in the machine
	 * @param columnsPerRow number of Dispeners in each row of the machine
	 * @throws Exception 
	 */
	public VendingMachine(int numberOfDispensers, int nickels, int dimes, int quarters, int columnsPerRow) throws Exception {
		moneyProcessor = new MoneyProcessor(nickels, dimes, quarters);
		dispensers = new ArrayList<Dispenser>();
		this.columnsPerRow = columnsPerRow;
		int row = 0;
		int column = 0;
		for (int i = 0; i < numberOfDispensers; i++) {
			dispensers.add(new Dispenser(row, column));
			column++;
			if (column >= columnsPerRow) {
				column = 0;
				row++;
			}
		}
	}
	/**
	 * The customer has inserted some money
	 * @param customerNickels
	 * @param customerDimes
	 * @param customerQuarters
	 */
	public void acceptCustomerMoney(int customerNickels, int customerDimes, int customerQuarters) {
		moneyProcessor.acceptCustomerMoney(customerNickels, customerDimes, customerQuarters);
	}
	/**
	 * Stock the vending machine with a product 
	 * @param product The product to stock
	 * @param row the row of the dispenser
	 * @param column the column of the dispenser
	 * @return True if it worked, False otherwise
	 */
	public Boolean AddProduct(Product product, int row, int column) {
		Boolean status = false;
		int adjustedRow = row - 1;
		int adjustedColumn = column - 1;
		//double customerMoneyEntered = moneyProcessor.getCustomerMoneyEntered();
		try {
			int index = adjustedRow * columnsPerRow + adjustedColumn;
			(dispensers.get(index)).addProduct(product);
			status = true;
		} catch (Exception ex) {}
		return status;
	}
	/**
	 * Accept the row/column pressed by the user 
	 * @param row The 1-based row number
	 * @param column The 1-based column number
	 * @return The product that was dispensed, or null if not enough money was entered or the selected Dispenser is empty
	 */
	public Product acceptCustomerInput(int row, int column) {
		Product product = null;
		int adjustedRow = row - 1;
		int adjustedColumn = column - 1;
		//double customerMoneyEntered = moneyProcessor.getCustomerMoneyEntered();
		try {
			int index = adjustedRow * columnsPerRow + adjustedColumn;
			product = (dispensers.get(index)).getNextProduct();
			double changeDue = moneyProcessor.getCustomerMoneyEntered() - product.getCost();
			if (changeDue >= 0) {
				// Customer has entered enough money. Dispense the product and the change
				(dispensers.get(index)).dispenseProduct();
				moneyProcessor.getMachineMoneyAmount().add(moneyProcessor.getCustomerMoneyAmount());	// Add all the customer money to the machine
				MoneyAmount tmp = new MoneyAmount();
				MoneyAmount change = MoneyAmount.subtract(moneyProcessor.getCustomerMoneyAmount(), product.getCost(), tmp);
				dispenseChange(change);
				moneyProcessor.getCustomerMoneyAmount().resetToZero();
			} else {
				// Customer has not entered enough money
			}
		} catch (Exception ex) {}

		return product;
	}
	/**
	 * The vending machine owes the customer money. 
	 * @param change The amount to give to the customer
	 */
	private void dispenseChange(MoneyAmount change) {
		moneyProcessor.getMachineMoneyAmount().remove(change); 
	}
	/**
	 * Get the number of dispensers in the vending machine
	 * @return The number of dispensers in the vending machine
	 */
	public int getNumberOfDispensers() {return dispensers.size();}

	public int getNickelsInMachine() {return moneyProcessor.getMachineMoneyAmount().getNickels();}
	public int getDimesInMachine() {return moneyProcessor.getMachineMoneyAmount().getDimes();}
	public int getQuartersInMachine() {return moneyProcessor.getMachineMoneyAmount().getQuarters();}
	
	public int getNickelsInsertedByCustomer() {return moneyProcessor.getCustomerMoneyAmount().getNickels();}
	public int getDimesInsertedByCustomer() {return moneyProcessor.getCustomerMoneyAmount().getDimes();}
	public int getQuartersInsertedByCustomer() {return moneyProcessor.getCustomerMoneyAmount().getQuarters();}
	
	public String toString() {
		return "Change box contains $" + moneyProcessor.getMachineMoneyAmount().toString() + 
			   "\nCustomer has entered $" + moneyProcessor.getCustomerMoneyAmount().toString();
	}
	
	public ArrayList<String> getContents() {
		ArrayList<String> contents = new ArrayList<String>();
		int row = 0;
		int column = 0;
		for (int i = 0; i < dispensers.size(); i++) {
			int index = row * columnsPerRow + column;
			Dispenser dispenser = dispensers.get(index);
			contents.add(dispenser.toString());
			column++;
			if (column >= columnsPerRow) {
				column = 0;
				row++;
			}
		}	
		return contents;
	}
	
	
}
