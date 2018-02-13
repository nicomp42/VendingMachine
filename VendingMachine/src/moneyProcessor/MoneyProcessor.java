package moneyProcessor;

import money.MoneyAmount;

/**
 * Process the coins entered by a customer
 * @author Bill Nicholson nicholdw@ucmail.uc.edu
 *
 */
public class MoneyProcessor {

	//private int nickels, dimes, quarters;
	private MoneyAmount machineMoneyAmount;
	//private int customerNickels, customerDimes, customerQuarters;
	private MoneyAmount customerMoneyAmount;
		
	public MoneyAmount getMachineMoneyAmount() {return machineMoneyAmount;}
	public MoneyAmount getCustomerMoneyAmount() {return customerMoneyAmount;}

	/**
	 * Constructor
	 * @param nickels Number of nickels in the change maker
	 * @param dimes Number of dimes in the change maker
	 * @param quarters Number of quarters in the change maker
	 */
	public MoneyProcessor(int nickels, int dimes, int quarters) {
		machineMoneyAmount = new  MoneyAmount();
		customerMoneyAmount = new MoneyAmount();
		machineMoneyAmount.setNickels(nickels);
		machineMoneyAmount.setDimes(dimes);
		machineMoneyAmount.setQuarters(quarters);
	}
	
	/**
	 * Process money inserted by a customer
	 * @param nickels number of nickels
	 * @param dimes number of dimes
	 * @param quarters number of quarters
	 * @return True if the money was added, False if it was rejected by the machine
	 */
	public Boolean acceptCustomerMoney(int customerNickels, int customerDimes, int customerQuarters) {
		if (customerNickels >= 0 && customerDimes >=0  && customerQuarters >= 0) {
			customerMoneyAmount.addNickels(customerNickels);
			customerMoneyAmount.addDimes(customerDimes);
			customerMoneyAmount.addQuarters(customerQuarters);
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Get the amount of money the customer has put into the machine
	 * @return the $ the customer has entered into the machine.
	 */
	public double getCustomerMoneyEntered() {
		return customerMoneyAmount.getNickels() * 5 + customerMoneyAmount.getDimes() * 10 + customerMoneyAmount.getQuarters() * 25;
	}
	/**
	 * Reset all the coins entered by the customer to zero
	 */
	public void refundCustomerMoney() {
		customerMoneyAmount.resetToZero();
	}
	
}
